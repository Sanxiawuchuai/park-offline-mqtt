package com.drzk.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * ftp上传下载工具类
 */
public class FtpUtil {

    private static Logger log = LoggerFactory.getLogger( FtpUtil.class );
    private static String SEPARATOR = "/";

    /**
     * 向FTP服务器上传文件  basePath + File.separator + parkingNo + File.separator + storePath+File.separator+filename
     *
     * @param host      FTP服务器hostname
     * @param port      FTP服务器端口
     * @param username  FTP登录账号
     * @param password  FTP登录密码
     * @param basePath  FTP服务器基础目录
     * @param parkingNo 车场编号
     * @param storePath FTP服务器文件存放路径
     * @param filePath  本地图片路径
     * @param filename  上传到FTP服务器上的文件名
     * @return 成功返回服务器存储路径，否则返回null
     */
    public static String uploadFile(String host, int port, String username, String password, String basePath,
                                    String storePath, String filename, String filePath, String parkingNo) {
        FTPClient ftp = new FTPClient();
        InputStream input = null;
        try {
            ftp.connect( host, port );// 连接FTP服务器
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login( username, password );// 登录
            int reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion( reply )) {
                return null;
            }
            //切换目录
            if (!createDirectory( basePath, parkingNo, storePath, ftp )) {
                return null;
            }
            // 设置上传文件的类型为二进制类型
            ftp.setFileType( FTP.BINARY_FILE_TYPE );
            // 上传文件
            ftp.enterLocalPassiveMode();
            //加载图片
            input = new FileInputStream( new File( filePath ) );
            // 上传文件
            if (ftp.storeFile( filename, input )) {
                return basePath + SEPARATOR + parkingNo + SEPARATOR + storePath + SEPARATOR + filename;
            }
            return null;
        } catch (IOException e) {
            log.error( "上传图片文件异常：{}", e.getMessage(), e );
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    log.error( "上传图片关闭文件流异常：{}", e.getMessage(), e );
                }
            }
            if (ftp.isConnected()) {
                try {
                    ftp.logout();
                } catch (IOException e) {
                    log.error( "上传图片关闭ftp client异常：{}", e.getMessage(), e );
                }
                try {
                    ftp.disconnect();
                } catch (IOException e) {
                    log.error( "上传图片断开ftp client异常：{}", e.getMessage(), e );
                }
            }

        }

    }

    /**
     * 切换目录  如果不存在就先创建目录
     *
     * @param basePath  服务器跟路径
     * @param parkingNo 车场编号
     * @param filePath  每天新增的路径
     * @param ftpClient ftp client
     * @return 目录是否创建成功
     */
    private static boolean createDirectory(String basePath, String parkingNo, String filePath, FTPClient ftpClient) {
        boolean bool = false;
        try {
            String serverPath =basePath + SEPARATOR +  parkingNo + SEPARATOR + filePath;
            // 一天只创建一次目录
            boolean result = ftpClient.changeWorkingDirectory( serverPath );
            if (!result) {
                String[] dirs = (parkingNo + SEPARATOR + filePath).split( SEPARATOR );
                //按顺序检查目录是否存在，不存在则创建目录
                for (String dir : dirs) {
                    if (!ftpClient.changeWorkingDirectory( dir )) {
                        if (ftpClient.makeDirectory( dir )) {
                            if (!ftpClient.changeWorkingDirectory( dir )) {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    }
                }
            }
            return true;
        } catch (IOException e) {
            log.error( "上传图片文件异常：{}", e.getMessage(), e );
            return false;
        }
    }

    /**
     * Description: 向FTP服务器上传文件
     *
     * @param host     FTP服务器hostname
     * @param port     FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param basePath FTP服务器基础目录
     * @param filePath FTP服务器文件存放路径。文件的路径为basePath+filePath
     * @param filename 上传到FTP服务器上的文件名
     * @param input    输入流
     * @return 成功返回true，否则返回false
     */
    public static boolean uploadFile(String host, int port, String username, String password, String basePath,
                                     String filePath, String filename, InputStream input) {
        boolean result = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect( host, port );// 连接FTP服务器
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login( username, password );// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion( reply )) {
                ftp.disconnect();
                return result;
            }
            // 切换到上传目录
            // if (!ftp.changeWorkingDirectory(basePath + filePath)) {
            // // 如果目录不存在创建目录
            // String[] dirs = filePath.split("/");
            // String tempPath = basePath;
            // for (String dir : dirs) {
            // if (null == dir || "".equals(dir))
            // continue;
            // tempPath += "/" + dir;
            // if (!ftp.changeWorkingDirectory(tempPath)) { // 进不去目录，说明该目录不存在
            // if (!ftp.makeDirectory(tempPath)) { // 创建目录
            // // 如果创建文件目录失败，则返回
            // System.out.println("创建文件目录" + tempPath + "失败");
            // return result;
            // } else {
            // // 目录存在，则直接进入该目录
            // ftp.changeWorkingDirectory(tempPath);
            // }
            // }
            // }
            // }
            createMultiDirectory( basePath, filePath, ftp );

            // 设置上传文件的类型为二进制类型
            ftp.setFileType( FTP.BINARY_FILE_TYPE );
            // 上传文件
            ftp.enterLocalPassiveMode();
            if (!ftp.storeFile( filename, input )) {
                return result;
            }
            input.close();
            ftp.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;
    }

    public static boolean createMultiDirectory(String basePath, String filePath, FTPClient ftpClient) {
        boolean bool = false;
        try {
            String[] dirs = filePath.split( File.separator );
            boolean flag = ftpClient.changeWorkingDirectory( basePath );

            // 按顺序检查目录是否存在，不存在则创建目录
            for (int i = 0; dirs != null && i < dirs.length; i++) {
                if (!ftpClient.changeWorkingDirectory( dirs[i] )) {
                    if (ftpClient.makeDirectory( dirs[i] )) {
                        if (!ftpClient.changeWorkingDirectory( dirs[i] )) {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            }

            bool = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return bool;
        }
    }

    /**
     * Description: 从FTP服务器下载文件
     *
     * @param host       FTP服务器hostname
     * @param port       FTP服务器端口
     * @param username   FTP登录账号
     * @param password   FTP登录密码
     * @param remotePath FTP服务器上的相对路径
     * @param fileName   要下载的文件名
     * @param localPath  下载后保存到本地的路径
     * @return
     */
    public static boolean downloadFile(String host, int port, String username, String password, String remotePath,
                                       String fileName, String localPath) {
        boolean result = false;
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect( host, port );
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login( username, password );// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion( reply )) {
                ftp.disconnect();
                return result;
            }
            ftp.changeWorkingDirectory( remotePath );// 转移到FTP服务器目录
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs) {
                if (ff.getName().equals( fileName )) {
                    File localFile = new File( localPath + "/" + ff.getName() );

                    OutputStream is = new FileOutputStream( localFile );
                    ftp.retrieveFile( ff.getName(), is );
                    is.close();
                }
            }

            ftp.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;
    }

    // ftp上传文件测试main函数
    public static void main(String[] args) {
        try {
            //FileInputStream in = new FileInputStream( new File( "E:\\testUdp.dll" ) );
            String path = uploadFile(
                    "192.168.8.89",
                    21,
                    "vsftp",
                    "test123456",
                    "/home/vsftp",
                    "2018/09/17",
                    "timg4.jpg",
                    "D:\\images\\timg5.jpg",
                    "X251200121"
            );
            System.out.println( path );
            //"/home/vsftp",
            // "vsftp",
            //   "test123456",

//			String host = "192.168.1.41";
//			int port = 2121;
//			String username = "anonymous";
//			String password = "123456";
//			FTPClient ftp1 = new FTPClient();
//			int reply;
//			ftp1.connect(host, port);// 连接FTP服务器
//			// 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
//			ftp1.login(username, password);// 登录
//			reply = ftp1.getReplyCode();
//			if (!FTPReply.isPositiveCompletion(reply)) {
//				ftp1.disconnect();
//				System.out.println("登录失败");
//			}
//			
//			FTPClient ftp2 = new FTPClient();
//			int reply1;
//			ftp2.connect(host, port);// 连接FTP服务器
//			// 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
//			ftp2.login(username, password);// 登录
//			reply1 = ftp2.getReplyCode();
//			if (!FTPReply.isPositiveCompletion(reply1)) {
//				ftp2.disconnect();
//				System.out.println("登录失败");
//			}
//			
//			System.out.println(ftp1);
//			System.out.println(ftp2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
