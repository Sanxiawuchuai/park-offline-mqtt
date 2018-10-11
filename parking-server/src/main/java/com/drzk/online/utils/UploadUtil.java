package com.drzk.online.utils;

import com.drzk.online.onlineVo.ParkConfigVo;
import com.drzk.utils.FtpUtil;
import com.drzk.utils.GlobalPark;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhangbin
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: FTP图片上传的方法
 * @date 2018/10/4 11:19
 */
public class UploadUtil {

    private static Logger log=LoggerFactory.getLogger(UploadUtil.class);

    /**
     * 线下同步图片到线上,成功则返回当前数据的uuid，失败返回空
     *
     * @param guid   当条数据UUID
     * @param imgPath 图片的路径
     * @return
     */
    public static String syFtpImg(String guid, String imgPath) {
        try {
            String host = GlobalPark.properties.getProperty(ParkConfigVo.VSFTP_HOST);
            String port = GlobalPark.properties.getProperty(ParkConfigVo.VSFTP_PORT);
            String username = GlobalPark.properties.getProperty(ParkConfigVo.VSFTP_USER);
            String password = GlobalPark.properties.getProperty(ParkConfigVo.VSFTP_PASSPW);
            String basePath = GlobalPark.properties.getProperty(ParkConfigVo.VSFTP_ONLINE_HOME);
            String parkingNo = GlobalPark.properties.getProperty(ParkConfigVo.PARKING_NO);
            String vsftpLocalHome = GlobalPark.properties.getProperty(ParkConfigVo.VSFTP_OFFLINE_HOME);
            String path = FtpUtil.uploadFile(host, port, username, password, basePath, vsftpLocalHome, imgPath, parkingNo);     //同步到云端
            if (path!=null) {     //上传失败
                return guid;
            }
        }catch (Exception e){
            log.error("图片上传异常：",e);
        }finally {
            return null;
        }
    }
}