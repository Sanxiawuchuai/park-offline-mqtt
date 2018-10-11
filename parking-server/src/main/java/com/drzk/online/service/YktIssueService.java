package com.drzk.online.service;

import com.drzk.online.vo.CarportAndCarVO;
import com.drzk.online.vo.ParkCaruser;
import com.drzk.online.vo.RenewalVO;

/**
 * @author zhangbin
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: 发行卡的相关实体类
 * @date 2018/9/17 15:08
 */
public interface YktIssueService {

    /**
     * 保存主表的相关数据
     * @param carportAndCarVO
     */
    public int saveYktIssue(CarportAndCarVO carportAndCarVO,String objectId,String perName,String perTel,Integer operationType);

    /**
     * 重新下发到硬件控制器
     */
    public void reloadCarGrantData();

    /**
     * 线上续费方法
     * @param renewalVO
     * @return
     */
    public int delay(RenewalVO renewalVO);

    /**
     * 云端批量导入的方法
     * @param parkCaruser
     * @return
     */
    public int importYkt(ParkCaruser parkCaruser);
}
