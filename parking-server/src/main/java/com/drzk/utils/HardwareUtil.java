package com.drzk.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class HardwareUtil {

	/**
	 * Return Opertaion System Name;
	 * 
	 * @return os name.
	 */
	public static String getOsName() {
		String os = "";
		os = System.getProperty("os.name");
		return os;
	}

	/**
	 * Returns the MAC address of the computer.
	 * 
	 * @return the MAC address
	 */
	public static String getMACAddress() {
		String address = "";
		String os = getOsName();
		if (os.startsWith("Windows")) {
			try {
				String command = "cmd.exe /c ipconfig /all";
				Process p = Runtime.getRuntime().exec(command);
				BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(),"gbk"));
				String line;
				while ((line = br.readLine()) != null) {
					if (line.indexOf("Physical Address") > 0) {
						int index = line.indexOf(":");
						index += 2;
						address = line.substring(index);
						break;
					} else if (line.indexOf("物理地址") > 0) {
						int index = line.indexOf(":");
						index += 2;
						address = line.substring(index);
						break;
					}
				}
				br.close();
				return address.trim();
			} catch (IOException e) {
			}
		} else if (os.startsWith("Linux")) {
			String command = "/bin/sh -c ifconfig -a";
			Process p;
			try {
				p = Runtime.getRuntime().exec(command);
				BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line;
				while ((line = br.readLine()) != null) {
					if (line.indexOf("HWaddr") > 0) {
						int index = line.indexOf("HWaddr") + "HWaddr".length();
						address = line.substring(index);
						break;
					}
				}
				br.close();
			} catch (IOException e) {
			}
		}
		address = address.trim();
		return address;
	}

	/**
	 * 获取CPU号,多CPU时,只取第一个
	 * 
	 * @return
	 */
	public static String getCPUSerial() {
		String result = "";
		String os = getOsName();
		if (os.startsWith("Windows")) {
			try {
				File file = File.createTempFile("tmp", ".vbs");
				file.deleteOnExit();
				FileWriter fw = new java.io.FileWriter(file);

				String vbs = "On Error Resume Next \r\n\r\n" + "strComputer = \".\"  \r\n"
						+ "Set objWMIService = GetObject(\"winmgmts:\" _ \r\n"
						+ "    & \"{impersonationLevel=impersonate}!\\\\\" & strComputer & \"\\root\\cimv2\") \r\n"
						+ "Set colItems = objWMIService.ExecQuery(\"Select * from Win32_Processor\")  \r\n "
						+ "For Each objItem in colItems\r\n " + "    Wscript.Echo objItem.ProcessorId  \r\n "
						+ "    exit for  ' do the first cpu only! \r\n" + "Next                    ";

				fw.write(vbs);
				fw.close();
				Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
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
		} else if (os.startsWith("Linux")) {
			String CPU_ID_CMD = "dmidecode -t 4 | grep ID |sort -u |awk -F': ' '{print $2}'";
			Process p;
			try {
				p = Runtime.getRuntime().exec(new String[] { "sh", "-c", CPU_ID_CMD });// 管道
				BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line;
				while ((line = br.readLine()) != null) {
					result += line;
					break;
				}
				br.close();
			} catch (IOException e) {
			}
		}
		if (result.trim().length() < 1 || result == null) {
			result = "无CPU_ID被读取";
		}
		return result.trim();
	}

	/**
	 * Main Class.
	 * 
	 * @param args
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 */
	public static void main(String[] args) throws Exception {

		String macAddress = HardwareUtil.getMACAddress();
		String cpuSerial = HardwareUtil.getCPUSerial();
		System.out.println("macAddress:"+macAddress);
		System.out.println("cpuSerial:"+cpuSerial);

	}
}