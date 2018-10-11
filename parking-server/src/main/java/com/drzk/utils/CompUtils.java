package com.drzk.utils;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.*;
import java.util.*;


public class CompUtils {
    private static Logger log=Logger.getLogger("userLog");
    /**
     * 获取主板序列号
     * @return
     */
    public static String getMotherboardSN() {
        String result = "";
        try {
            File file = File.createTempFile("realhowto", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new FileWriter(file);

            String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
                    + "Set colItems = objWMIService.ExecQuery _ \n"
                    + "   (\"Select * from Win32_BaseBoard\") \n"
                    + "For Each objItem in colItems \n"
                    + "    Wscript.Echo objItem.SerialNumber \n"
                    + "    exit for  ' do the first cpu only! \n" + "Next \n";

            fw.write(vbs);
            fw.close();
            String path = file.getPath().replace("%20", " ");
            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + path);
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.trim();
    }

    /**
     * 获取硬盘序列号(该方法获取的是 盘符的逻辑序列号,并不是硬盘本身的序列号)
     * 硬盘序列号还在研究中
     * @param drive 盘符
     * @return
     */
    public static String getHardDiskSN(String drive) {
        String result = "";
        try {
            File file = File.createTempFile("realhowto", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new FileWriter(file);

            String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"
                    + "Set colDrives = objFSO.Drives\n"
                    + "Set objDrive = colDrives.item(\""
                    + drive
                    + "\")\n"
                    + "Wscript.Echo objDrive.SerialNumber"; // see note
            fw.write(vbs);
            fw.close();
            String path = file.getPath().replace("%20", " ");
            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + path);
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.trim();
    }

    /**
     * 获取CPU序列号
     * @return
     */
    public static String getCPUSerial() {
        String result = "";
        try {
            File file = File.createTempFile("tmp", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new FileWriter(file);
            String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
                    + "Set colItems = objWMIService.ExecQuery _ \n"
                    + "   (\"Select * from Win32_Processor\") \n"
                    + "For Each objItem in colItems \n"
                    + "    Wscript.Echo objItem.ProcessorId \n"
                    + "    exit for  ' do the first cpu only! \n" + "Next \n";

            fw.write(vbs);
            fw.close();
            String path = file.getPath().replace("%20", " ");
            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + path);
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
            file.delete();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        if (result.trim().length() < 1 || result == null) {
            result = "无CPU_ID被读取";
        }
        return result.trim();
    }

    private static List<String> getLocalHostLANAddress()	throws UnknownHostException, SocketException {
        List<String> ips = new ArrayList<String>();
        Enumeration<NetworkInterface> interfs = NetworkInterface.getNetworkInterfaces();
        while (interfs.hasMoreElements()) {
            NetworkInterface interf = interfs.nextElement();
            Enumeration<InetAddress> addres = interf.getInetAddresses();
            while (addres.hasMoreElements()) {
                InetAddress in = addres.nextElement();
                if (in instanceof Inet4Address) {
                    if(!"127.0.0.1".equals(in.getHostAddress())){
                        ips.add(in.getHostAddress());
                    }
                }
            }
        }
        return ips;
    }

    /**
     * MAC
     * 通过jdk自带的方法,先获取本机所有的ip,然后通过NetworkInterface获取mac地址
     * @return
     */
    public static String getMac() {
        try {
            String resultStr = "";
            List<String> ls = getLocalHostLANAddress();
            for(String str : ls){
                InetAddress ia = InetAddress.getByName(str);// 获取本地IP对象
                // 获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
                byte[] mac = NetworkInterface.getByInetAddress(ia)
                        .getHardwareAddress();
                // 下面代码是把mac地址拼装成String
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < mac.length; i++) {
                    if (i != 0) {
                        sb.append("-");
                    }
                    // mac[i] & 0xFF 是为了把byte转化为正整数
                    String s = Integer.toHexString(mac[i] & 0xFF);
                    sb.append(s.length() == 1 ? 0 + s : s);
                }
                // 把字符串所有小写字母改为大写成为正规的mac地址并返回
                resultStr += sb.toString().toUpperCase()+",";
            }
            return resultStr.substring(0,resultStr.lastIndexOf(","));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 获取服务器的IP地址
     * @return
     */
    public static String getIp(){
        String addr="";
        try {
            addr = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return addr;
    }

    /***************************linux*********************************/
    public static String executeLinuxCmd(String cmd)  {
        try {
            Runtime run = Runtime.getRuntime();
            Process process;
            process = run.exec(cmd);
            InputStream in = process.getInputStream();
            BufferedReader bs = new BufferedReader(new InputStreamReader(in));
            StringBuffer out = new StringBuffer();
            byte[] b = new byte[8192];
            for (int n; (n = in.read(b)) != -1;) {
                out.append(new String(b, 0, n));
            }

            in.close();
            process.destroy();
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param cmd 命令语句
     * @param record 要查看的字段
     * @param symbol 分隔符
     * @return
     */
    public static String getSerialNumber(String cmd ,String record,String symbol) {
        String execResult = executeLinuxCmd(cmd);
        String[] infos = execResult.split("\n");

        for(String info : infos) {
            info = info.trim();
            if(info.indexOf(record) != -1) {
                info.replace(" ", "");
                String[] sn = info.split(symbol);
                return sn[1];
            }
        }

        return null;
    }

    /**
     * 获取CPUID、硬盘序列号、MAC地址、主板序列号
     * @return
     */
    public static void getAllSn(){
        GlobalPark.sysMap.clear();          //清除参数对象值
        String os = System.getProperty("os.name");
        os = os.toUpperCase();

        Map<String, String> snVo = new HashMap<String, String>();
        if("LINUX".equals(os)) {
            String cpuid=getSerialNumber("dmidecode -t processor | grep 'ID'", "ID",":");
            String mainboardNumber=getSerialNumber("dmidecode |grep 'Serial Number'", "Serial Number",":");
            String diskNumber =getSerialNumber("fdisk -l", "Disk identifier",":");
            String mac =getSerialNumber("ifconfig -a", "ether"," ");
            String ip =getSerialNumber("ifconfig -a", "inet"," ");

            snVo.put("cpuid",cpuid.toUpperCase().replace(" ", ""));
            snVo.put("diskid",diskNumber.toUpperCase().replace(" ", ""));
            snVo.put("mac",mac.toUpperCase().replace(" ", ""));
            snVo.put("mainboard",mainboardNumber.toUpperCase().replace(" ", ""));
            snVo.put("ip",ip.toUpperCase().replace(" ",""));

        }else {
            String cpuid = CompUtils.getCPUSerial();
            String mainboard = CompUtils.getMotherboardSN();
            String disk = CompUtils.getHardDiskSN("c");
            String mac = CompUtils.getMac();
            String ip=CompUtils.getIp();

            snVo.put("cpuid",cpuid.toUpperCase().replace(" ", ""));
            snVo.put("diskid",disk.toUpperCase().replace(" ", ""));
            snVo.put("mac",mac.toUpperCase().replace(" ", ""));
            snVo.put("mainboard",mainboard.toUpperCase().replace(" ", ""));
            snVo.put("ip",ip.toUpperCase().replace(" ",""));
        }

        GlobalPark.sysMap.putAll(snVo);     //把相关存放到缓存对象中
    }


    /**
     * linux
     * cpuid : dmidecode -t processor | grep 'ID'
     * mainboard : dmidecode |grep 'Serial Number'
     * disk : fdisk -l
     * mac : ifconfig -a
     * @param args
     */
    public static void main(String[] args) {
        String cpuid = CompUtils.getMac();
        System.out.println(cpuid);
    }
}
