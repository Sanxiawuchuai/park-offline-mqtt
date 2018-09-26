/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:46:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for abandon_record
-- ----------------------------
DROP TABLE IF EXISTS `abandon_record`;
CREATE TABLE `abandon_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `in_mach_no` tinyint(4) DEFAULT '0' COMMENT '入口机号',
  `out_mach_no` tinyint(4) DEFAULT '0' COMMENT '出口机号',
  `ykt_id` int(11) DEFAULT '0' COMMENT '开户ID号',
  `out_id` int(11) DEFAULT '0' COMMENT '关联遥控开闸记录的ID',
  `card_id` varchar(20) DEFAULT NULL COMMENT '卡ID（7Byte）',
  `c_flag` tinyint(4) DEFAULT '0' COMMENT '卡介质(0为IC，1为ID，2IC做ID，4纯车牌)',
  `card_no` varchar(10) DEFAULT NULL COMMENT '卡编号',
  `emp_name` varchar(20) DEFAULT NULL COMMENT '车主名称',
  `car_no` varchar(10) DEFAULT NULL COMMENT '车牌号',
  `car_no_type` tinyint(4) DEFAULT '0' COMMENT '车牌类型 0蓝色1黄色2白色3黑色',
  `card_type` tinyint(4) DEFAULT '0' COMMENT '卡类型',
  `free_type` tinyint(4) DEFAULT '0' COMMENT '免费类型',
  `in_time` datetime DEFAULT NULL COMMENT '入场时间',
  `in_pic` varchar(255) DEFAULT NULL COMMENT '入场主车牌图片路径',
  `in_user_name` varchar(20) DEFAULT NULL COMMENT '入场操作员',
  `central_time` datetime DEFAULT NULL COMMENT '定点收费时间',
  `out_time` datetime DEFAULT NULL COMMENT '出场时间',
  `out_pic` varchar(255) DEFAULT NULL COMMENT '出场主车牌图片路径',
  `out_user_name` varchar(20) DEFAULT NULL COMMENT '出场操作员',
  `zj_pic` varchar(255) DEFAULT NULL COMMENT '证件图片路径',
  `pay_type` tinyint(4) DEFAULT NULL COMMENT '支付类型0，现金支付，1微信支付,2，支付宝支付,3,银联闪付，4，交通卡支付，5,自助缴费机,6，其它',
  `pay_charge` float DEFAULT '0' COMMENT '实收金额',
  `balance_money` float DEFAULT '0' COMMENT '账户金额',
  `discount_no` tinyint(4) DEFAULT NULL COMMENT '打折机号',
  `type_id` tinyint(4) DEFAULT NULL COMMENT '折扣id',
  `discount_time` datetime DEFAULT NULL COMMENT '打折时间',
  `dis_amount` float DEFAULT '0' COMMENT '打折金额',
  `account_charge` float DEFAULT '0' COMMENT '应收金额',
  `is_out` tinyint(4) DEFAULT '0' COMMENT '是否出场（中央收费时为0，出场后更新为1）',
  `order_num` varchar(255) DEFAULT NULL COMMENT '订单号',
  `memo` varchar(255) DEFAULT NULL COMMENT '异常原因（1车闸故障2卡遗失等等）',
  `out_way` tinyint(4) DEFAULT '0' COMMENT '出场方式 0,表示正常出场，1，手工放行，2，异常放行',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:46:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for back_park_car_out
-- ----------------------------
DROP TABLE IF EXISTS `back_park_car_out`;
CREATE TABLE `back_park_car_out` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `in_id` varchar(255) DEFAULT NULL COMMENT '入场guid',
  `in_mach_no` tinyint(4) DEFAULT NULL COMMENT '入场机号',
  `out_mach_no` tinyint(4) DEFAULT NULL COMMENT '出场机号',
  `ykt_id` int(11) DEFAULT NULL COMMENT '开户id',
  `card_id` varchar(20) DEFAULT NULL COMMENT '卡id',
  `c_flag` tinyint(4) DEFAULT NULL COMMENT '卡介质(0为IC，1为ID，2IC做ID，3)',
  `card_no` varchar(10) DEFAULT NULL COMMENT '卡号',
  `in_car_no` varchar(10) DEFAULT NULL COMMENT '入场主车牌号',
  `back_in_car_no` varchar(10) DEFAULT NULL COMMENT '入场副车牌号',
  `out_car_no` varchar(10) DEFAULT NULL COMMENT '出场主车牌号',
  `back_out_car_no` varchar(10) DEFAULT NULL COMMENT '出场副车牌号',
  `car_no_type` tinyint(4) DEFAULT NULL COMMENT '车牌类型',
  `card_type` tinyint(4) DEFAULT NULL COMMENT '卡类型',
  `free_type` tinyint(4) DEFAULT NULL COMMENT '免费类型',
  `in_time` datetime DEFAULT NULL COMMENT '入场时间',
  `out_time` datetime DEFAULT NULL COMMENT '出场时间',
  `in_pic` varchar(255) DEFAULT NULL COMMENT '入场主车牌图片',
  `back_in_pic` varchar(255) DEFAULT NULL COMMENT '入场副车牌图片',
  `out_pic` varchar(255) DEFAULT NULL COMMENT '出场主车牌图片',
  `back_out_pic` varchar(255) DEFAULT NULL COMMENT '出场副车牌图片',
  `in_user_name` varchar(255) DEFAULT NULL COMMENT '入场操作员',
  `out_user_name` varchar(255) DEFAULT NULL COMMENT '出场操作员',
  `credentials_pic` varchar(255) DEFAULT NULL COMMENT '证件图片路径',
  `pay_type` tinyint(4) DEFAULT NULL COMMENT '支付类型0，现金支付，1微信支付,2，支付宝支付,3,银联闪付，4，交通卡支付，5,自助缴费机,6，其它',
  `account_charge` float DEFAULT '0' COMMENT '应收金额',
  `pay_charge` float DEFAULT '0' COMMENT '实收金额',
  `dis_amount` float DEFAULT '0' COMMENT '打折金额',
  `balance_money` float DEFAULT '0' COMMENT '账户金额',
  `discount_no` tinyint(4) DEFAULT NULL COMMENT '打折机号',
  `type_id` tinyint(4) DEFAULT NULL COMMENT '折扣id',
  `discount_time` datetime DEFAULT NULL COMMENT '打折时间',
  `order_num` varchar(255) DEFAULT NULL COMMENT '订单号',
  `out_way` tinyint(4) DEFAULT '0' COMMENT '出场类型',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `is_load` tinyint(4) DEFAULT '0' COMMENT '0未上传，1已上传',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:46:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for back_up_park_car_in
-- ----------------------------
DROP TABLE IF EXISTS `back_up_park_car_in`;
CREATE TABLE `back_up_park_car_in` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `guid` varchar(255) DEFAULT NULL COMMENT '入场唯一标识',
  `mach_no` int(11) DEFAULT NULL COMMENT '控制器编号',
  `ykt_id` int(11) DEFAULT NULL COMMENT '开户ID号',
  `card_id` varchar(20) DEFAULT NULL COMMENT '卡ID（7Byte）',
  `c_flag` tinyint(4) DEFAULT NULL COMMENT '卡介质(0为IC，1为ID，2IC做ID，3)',
  `card_no` varchar(10) DEFAULT NULL COMMENT '卡编号',
  `card_type` tinyint(4) DEFAULT NULL COMMENT '卡类型',
  `emp_name` varchar(20) DEFAULT NULL COMMENT '车主姓名',
  `in_time` datetime DEFAULT NULL COMMENT '入场时间',
  `in_user_name` varchar(20) DEFAULT NULL COMMENT '入场操作员',
  `car_no` varchar(10) DEFAULT NULL COMMENT '车牌号',
  `back_car_no` varchar(10) DEFAULT NULL COMMENT '后车牌',
  `in_pic` varchar(255) DEFAULT NULL COMMENT '小车场内',
  `back_in_pic` varchar(255) DEFAULT NULL COMMENT '后车牌图片',
  `car_no_type` tinyint(255) DEFAULT NULL COMMENT '车牌类型',
  `small` tinyint(4) DEFAULT NULL COMMENT '小车场内',
  `discount_no` tinyint(4) DEFAULT NULL COMMENT '打折机号',
  `type_id` tinyint(4) DEFAULT NULL COMMENT '打折机号',
  `discount_time` datetime DEFAULT NULL COMMENT '折扣时间',
  `makeup` tinyint(4) DEFAULT NULL COMMENT '车牌补录标识 1-已补充 else-未补充',
  `makeup_user_name` varchar(20) DEFAULT NULL COMMENT '车牌补录人',
  `is_locked` tinyint(4) DEFAULT NULL COMMENT '锁车标识 0-未锁 1-已锁',
  `in_way` tinyint(4) DEFAULT NULL COMMENT '入场方式 0正常入场，1校正入场 ，2手工入场 3,扫码入场 4,脱机记录，5，相机异常记录 6，异常开闸',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:46:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ch_region
-- ----------------------------
DROP TABLE IF EXISTS `ch_region`;
CREATE TABLE `ch_region` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(10) DEFAULT NULL COMMENT '父级id',
  `region_id` bigint(10) DEFAULT NULL COMMENT '区域编码',
  `region_parent_id` bigint(10) DEFAULT NULL COMMENT '父级区域编码',
  `region_name` varchar(100) DEFAULT NULL COMMENT '区域名称',
  `region_type` int(11) DEFAULT NULL COMMENT '1省 2市 3区',
  `zip_code` varchar(50) DEFAULT NULL COMMENT '邮政编码',
  `qu_hao` varchar(50) DEFAULT NULL COMMENT '区号',
  `status` bit(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11186 DEFAULT CHARSET=utf8 COMMENT='地区信息表';
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:46:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for park_account_type
-- ----------------------------
DROP TABLE IF EXISTS `park_account_type`;
CREATE TABLE `park_account_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `a_type` tinyint(4) DEFAULT NULL COMMENT '卡类ID',
  `a_code` varchar(50) NOT NULL COMMENT '参数编码名称',
  `a_name` varchar(10) DEFAULT NULL COMMENT '卡类名称',
  `a_custname` varchar(100) DEFAULT NULL COMMENT '自定名称',
  `no_use` tinyint(4) DEFAULT NULL COMMENT '是否不启用 0启用1不启用',
  `is_send` tinyint(4) DEFAULT NULL COMMENT '是否可发行',
  `id_mode` tinyint(4) DEFAULT NULL COMMENT 'ID模式(IC转ID用)',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_name` varchar(20) DEFAULT NULL COMMENT '创建人',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_name` varchar(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:46:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for park_cam_set
-- ----------------------------
DROP TABLE IF EXISTS `park_cam_set`;
CREATE TABLE `park_cam_set` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长ID',
  `box_id` int(11) DEFAULT NULL COMMENT '岗亭id',
  `sort_id` int(11) DEFAULT NULL COMMENT '顺序',
  `cam_ip` varchar(20) DEFAULT NULL COMMENT '相机ip',
  `cam_name` varchar(20) DEFAULT NULL COMMENT '相机名称',
  `cam_port` int(11) DEFAULT NULL COMMENT '相机端口',
  `fec_pic` tinyint(4) DEFAULT '0' COMMENT '是否是人像',
  `mach_no` int(11) DEFAULT NULL COMMENT '控制器编号',
  `channel_id` int(11) DEFAULT NULL COMMENT '通道id',
  `create_date` datetime DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改人',
  `modify_user_name` varchar(20) DEFAULT NULL COMMENT '修改时间',
  `is_load` tinyint(4) DEFAULT NULL COMMENT '是否上传0未上传1已上传',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:46:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for park_car_black_list
-- ----------------------------
DROP TABLE IF EXISTS `park_car_black_list`;
CREATE TABLE `park_car_black_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `car_no` varchar(10) DEFAULT NULL COMMENT '车牌号',
  `car_no_type` tinyint(4) DEFAULT NULL COMMENT '车牌类型(0无,1黑名单,2特种车辆)',
  `is_stop` tinyint(4) DEFAULT NULL COMMENT '类型(0无,1禁止通行,2通行免费,3自由通行)',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_name` varchar(20) DEFAULT NULL COMMENT '创建人',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_name` varchar(20) DEFAULT NULL COMMENT '修改人',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:46:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for park_car_in
-- ----------------------------
DROP TABLE IF EXISTS `park_car_in`;
CREATE TABLE `park_car_in` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `guid` varchar(255) DEFAULT NULL COMMENT '入场唯一标识',
  `mach_no` int(11) DEFAULT NULL COMMENT '入场机号',
  `ykt_id` int(11) NOT NULL COMMENT '开户id',
  `card_id` varchar(20) DEFAULT NULL COMMENT '卡ID',
  `c_flag` tinyint(4) DEFAULT NULL COMMENT '卡介质(0为IC，1为ID，2IC做ID，3)',
  `card_no` varchar(10) DEFAULT NULL COMMENT '卡号',
  `card_type` tinyint(4) NOT NULL COMMENT '卡类型',
  `emp_name` varchar(20) DEFAULT NULL COMMENT '车主姓名',
  `in_time` datetime DEFAULT NULL COMMENT '入场时间',
  `in_user_name` varchar(20) DEFAULT NULL COMMENT '入场操作员',
  `car_no` varchar(10) DEFAULT NULL COMMENT '主车牌号码',
  `assistant_car_no` varchar(10) DEFAULT NULL COMMENT '副车牌号码',
  `modify_car_no` varchar(10) DEFAULT NULL COMMENT '修正前的车牌',
  `in_pic` varchar(255) DEFAULT NULL COMMENT '主车牌图片路径',
  `back_in_pic` varchar(255) DEFAULT NULL COMMENT '副车牌图片路径',
  `small_pic` varchar(255) DEFAULT NULL COMMENT '主小车牌图片路径',
  `assistant_small_pic` varchar(255) DEFAULT NULL COMMENT '副小车牌图片路径',
  `car_no_type` tinyint(4) DEFAULT NULL COMMENT '0蓝色1黄色2白色3黑色',
  `small` tinyint(4) DEFAULT NULL COMMENT '0大车场1小车场',
  `discount_no` tinyint(4) DEFAULT NULL COMMENT '打折机号',
  `type_id` tinyint(4) DEFAULT NULL COMMENT '打折模式id',
  `discount_time` datetime DEFAULT NULL COMMENT '打折时间',
  `makeup` tinyint(4) DEFAULT '0' COMMENT '0未补录1已补录',
  `makeup_user_name` varchar(20) DEFAULT NULL COMMENT '车牌补录人',
  `is_locked` tinyint(4) DEFAULT '0' COMMENT '0未锁1已锁',
  `in_way` tinyint(4) DEFAULT '0' COMMENT '入场方式0正常入场，1校正入场，2手工入场 3,扫码入场 4,脱机记录，5，相机异常记录 6，异常开闸',
  `is_load` tinyint(4) DEFAULT '0' COMMENT '0未上传1正在上传,2已上传成功',
  PRIMARY KEY (`id`),
  KEY `idx_car_no` (`car_no`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:46:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for park_car_in_exception
-- ----------------------------
DROP TABLE IF EXISTS `park_car_in_exception`;
CREATE TABLE `park_car_in_exception` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `mach_no` tinyint(4) DEFAULT NULL COMMENT '控制器编号',
  `ykt_id` int(11) NOT NULL COMMENT '开户id',
  `card_id` varchar(20) DEFAULT NULL COMMENT '卡ID',
  `c_flag` tinyint(4) DEFAULT NULL COMMENT '卡介质(0为IC，1为ID，2IC做ID，3)',
  `card_no` varchar(10) DEFAULT NULL COMMENT '卡号',
  `emp_name` varchar(20) DEFAULT NULL COMMENT '车主姓名',
  `car_no` varchar(10) DEFAULT NULL COMMENT '主车牌号码',
  `assistant_car_no` varchar(10) DEFAULT NULL COMMENT '副车牌号码',
  `modify_car_no` varchar(10) DEFAULT NULL COMMENT '修正前的车牌',
  `card_type` tinyint(4) NOT NULL COMMENT '卡类型',
  `car_no_type` tinyint(4) DEFAULT NULL COMMENT '0蓝色1黄色2白色3黑色',
  `in_time` datetime DEFAULT NULL COMMENT '入场时间',
  `in_user_name` varchar(20) DEFAULT NULL COMMENT '入场操作员',
  `in_pic` varchar(255) DEFAULT NULL COMMENT '主车牌图片路径',
  `back_in_pic` varchar(255) DEFAULT NULL COMMENT '副车牌图片路径',
  `small_pic` varchar(255) DEFAULT NULL COMMENT '主小车牌图片路径',
  `assistant_small_pic` varchar(255) DEFAULT NULL COMMENT '副小车牌图片路径',
  `b_in_pic` varchar(255) DEFAULT NULL COMMENT '原主车牌图片路径',
  `b_back_in_pic` varchar(255) DEFAULT NULL COMMENT '原副车牌图片路径',
  `b_small_pic` varchar(255) DEFAULT NULL COMMENT '原主小车牌图片路径',
  `b_assistant_small_pic` varchar(255) DEFAULT NULL COMMENT '原副小车牌图片路径',
  `b_in_time` datetime DEFAULT NULL COMMENT '原入场时间',
  `in_way` tinyint(4) DEFAULT '0' COMMENT '入场方式0,表示正常出场，1，校正入场 ，2，手工入场 3,非法开闸入场,4,入场回退 5,未授权入场',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:47:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for park_car_out
-- ----------------------------
DROP TABLE IF EXISTS `park_car_out`;
CREATE TABLE `park_car_out` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `guid` varchar(255) DEFAULT NULL COMMENT '唯一标识',
  `in_id` varchar(255) DEFAULT NULL COMMENT '入场guid',
  `in_mach_no` tinyint(4) DEFAULT NULL COMMENT '入场机号',
  `out_mach_no` tinyint(4) DEFAULT NULL COMMENT '出场机号',
  `ykt_id` int(11) DEFAULT '0' COMMENT '开户id',
  `card_id` varchar(20) DEFAULT NULL COMMENT '卡id',
  `c_flag` tinyint(4) DEFAULT NULL COMMENT '卡介质(0为IC，1为ID，2IC做ID，3)',
  `card_no` varchar(10) DEFAULT NULL COMMENT '卡号',
  `in_car_no` varchar(10) DEFAULT NULL COMMENT '入场主车牌号',
  `back_in_car_no` varchar(10) DEFAULT NULL COMMENT '入场副车牌号',
  `out_car_no` varchar(10) DEFAULT NULL COMMENT '出场主车牌号',
  `back_out_car_no` varchar(10) DEFAULT NULL COMMENT '出场副车牌号',
  `car_no_type` tinyint(4) DEFAULT NULL COMMENT '车牌类型',
  `card_type` tinyint(4) DEFAULT NULL COMMENT '卡类型',
  `free_type` tinyint(4) DEFAULT NULL COMMENT '免费类型',
  `in_time` datetime DEFAULT NULL COMMENT '入场时间',
  `out_time` datetime DEFAULT NULL COMMENT '出场时间',
  `in_pic` varchar(255) DEFAULT NULL COMMENT '入场主车牌图片',
  `back_in_pic` varchar(255) DEFAULT NULL COMMENT '入场副车牌图片',
  `out_pic` varchar(255) DEFAULT NULL COMMENT '出场主车牌图片',
  `back_out_pic` varchar(255) DEFAULT NULL COMMENT '出场副车牌图片',
  `in_user_name` varchar(20) DEFAULT NULL COMMENT '入场操作员',
  `out_user_name` varchar(20) DEFAULT NULL COMMENT '出场操作员',
  `credentials_pic` varchar(255) DEFAULT NULL COMMENT '证件图片路径',
  `pay_type` tinyint(4) DEFAULT NULL COMMENT '支付类型0，现金支付，1微信支付,2，支付宝支付,3,银联闪付，4，交通卡支付，5,自助缴费机,6，其它',
  `account_charge` float DEFAULT '0' COMMENT '应收金额',
  `pay_charge` float DEFAULT '0' COMMENT '实收金额',
  `dis_amount` float DEFAULT '0' COMMENT '打折金额',
  `balance_money` float DEFAULT '0' COMMENT '账户金额',
  `discount_no` tinyint(4) DEFAULT NULL COMMENT '打折机号',
  `type_id` tinyint(4) DEFAULT NULL COMMENT '折扣id',
  `discount_time` datetime DEFAULT NULL COMMENT '打折时间',
  `order_num` varchar(100) DEFAULT NULL COMMENT '订单号',
  `out_way` tinyint(4) DEFAULT '0' COMMENT '出场类型',
  `memo` varchar(200) DEFAULT NULL COMMENT '备注',
  `is_load` tinyint(4) DEFAULT '0' COMMENT '0未上传，1正在上传，2上传成功',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:47:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for park_central_charge
-- ----------------------------
DROP TABLE IF EXISTS `park_central_charge`;
CREATE TABLE `park_central_charge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ykt_id` int(11) DEFAULT NULL COMMENT '开户id',
  `in_id` varchar(255) DEFAULT NULL COMMENT '入场guid',
  `box_id` tinyint(4) DEFAULT NULL COMMENT '岗亭编号',
  `card_id` varchar(20) DEFAULT NULL COMMENT '卡id',
  `c_flag` tinyint(4) DEFAULT NULL COMMENT '卡介质(0为IC，1为ID，2IC做ID，3)',
  `card_no` varchar(10) DEFAULT NULL COMMENT '卡号',
  `in_time` datetime DEFAULT NULL COMMENT '入场时间',
  `card_type` tinyint(4) DEFAULT NULL COMMENT '卡类型',
  `in_mach_no` int(11) DEFAULT NULL COMMENT '入场机号',
  `car_no` varchar(10) DEFAULT NULL COMMENT '主车牌',
  `back_car_no` varchar(10) DEFAULT NULL COMMENT '副车牌',
  `in_pic` varchar(255) DEFAULT NULL COMMENT '主车牌图片',
  `back_in_pic` varchar(255) DEFAULT NULL COMMENT '副车牌图片',
  `pay_charge_time` datetime DEFAULT NULL COMMENT '缴费时间',
  `over_time` datetime DEFAULT NULL COMMENT '超时时间',
  `free_type` int(11) DEFAULT NULL COMMENT '免费类型',
  `credentials_pic` varchar(255) DEFAULT NULL COMMENT '证件图片',
  `discount_no` int(11) DEFAULT NULL COMMENT '打折金额',
  `type_id` int(11) DEFAULT NULL COMMENT '折扣id',
  `discount_time` datetime DEFAULT NULL COMMENT '打折时间',
  `pay_user_name` varchar(20) DEFAULT NULL COMMENT '收费员',
  `pay_charge` float DEFAULT '0' COMMENT '实收金额',
  `account_charge` float DEFAULT '0' COMMENT '应收金额',
  `dis_amount` float DEFAULT '0' COMMENT '打折金额',
  `balance_money` float DEFAULT '0' COMMENT '账户金额',
  `order_num` varchar(255) DEFAULT NULL COMMENT '支付订单号',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `pay_type` tinyint(4) DEFAULT NULL COMMENT '支付类型0，现金支付，1微信支付,2，支付宝支付,3,银联闪付，4，交通卡支付，5,自助缴费机,6，其它',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:47:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for park_chanel_temp
-- ----------------------------
DROP TABLE IF EXISTS `park_chanel_temp`;
CREATE TABLE `park_chanel_temp` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `chanel_name` varchar(50) DEFAULT NULL COMMENT '通道名',
  `chanel_id` int(11) DEFAULT NULL COMMENT '通道号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:47:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for park_channel_set
-- ----------------------------
DROP TABLE IF EXISTS `park_channel_set`;
CREATE TABLE `park_channel_set` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `box_id` int(11) DEFAULT NULL COMMENT '岗亭编号',
  `mach_no` int(11) DEFAULT NULL COMMENT '机号',
  `channel_ip` varchar(20) DEFAULT NULL COMMENT '通道ip',
  `channel_name` varchar(20) DEFAULT NULL COMMENT '通道名',
  `dsn` varchar(100) DEFAULT NULL COMMENT '通道序列号',
  `in_out` tinyint(4) DEFAULT NULL COMMENT '出入类型（0标准入口，1标准出口，2标准嵌套入口，3标准嵌套出口、4半嵌套入口，5半嵌套出口、6单通道）',
  `gate_type` tinyint(4) DEFAULT NULL COMMENT '通道类型（0综合信道 1固定车信道，2临时卡通道）',
  `park_no` tinyint(4) DEFAULT NULL COMMENT '车场编号',
  `no_money` tinyint(4) DEFAULT NULL COMMENT '是否禁止收费',
  `strobe_set` varchar(8) DEFAULT NULL COMMENT '确定开闸',
  `strobe_no` tinyint(4) DEFAULT NULL COMMENT '开闸机号',
  `work_model` varchar(8) DEFAULT NULL COMMENT '工作模式',
  `enclosure` varchar(8) DEFAULT NULL COMMENT '附件序列',
  `video_list` varchar(8) DEFAULT NULL COMMENT '视频序列',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_name` varchar(20) DEFAULT NULL COMMENT '创建人',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_name` varchar(20) DEFAULT NULL COMMENT '修改人',
  `is_load` tinyint(4) DEFAULT '0' COMMENT '上传标记0未上传1正在上传2已上传',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:47:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for park_control_plan
-- ----------------------------
DROP TABLE IF EXISTS `park_control_plan`;
CREATE TABLE `park_control_plan` (
  `plan_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '级别id',
  `plan_name` varchar(20) DEFAULT NULL COMMENT '级别名称',
  PRIMARY KEY (`plan_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:47:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for park_control_plan_rel
-- ----------------------------
DROP TABLE IF EXISTS `park_control_plan_rel`;
CREATE TABLE `park_control_plan_rel` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `plan_id` int(11) DEFAULT NULL COMMENT '级别id',
  `mach_no` int(11) DEFAULT NULL COMMENT '控制器id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:47:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for park_device_status
-- ----------------------------
DROP TABLE IF EXISTS `park_device_status`;
CREATE TABLE `park_device_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mac_no` int(11) DEFAULT NULL COMMENT '设备编号',
  `mac_ip` varchar(20) DEFAULT NULL COMMENT '设备IP',
  `box_id` int(11) DEFAULT NULL COMMENT '岗亭编号',
  `road_gate_state` tinyint(4) DEFAULT NULL COMMENT '道闸状态 0-关到位，1-中间位，2-开到位',
  `card_machine_state` tinyint(4) DEFAULT NULL COMMENT '卡机状态 0-正常，1-少卡，2-无卡，3-吞卡箱满，4-故障，5-堵卡',
  `paper_machine_state` tinyint(4) DEFAULT NULL COMMENT '纸票机状态 0-正常，1-纸少，2-无纸，3-出票口有票，4-故障，5-忙，6-打印失败',
  `power_reset` tinyint(4) DEFAULT NULL COMMENT '主板上电复位,自检结果和关健配置',
  `groud_sense_unnormal` tinyint(4) DEFAULT NULL COMMENT '地感异常 00-恢复，01-异常',
  `camera_unnormal` tinyint(4) DEFAULT NULL COMMENT '摄像机异常 00-恢复，01-异常',
  `client_connect_unnormal` tinyint(4) DEFAULT NULL COMMENT '岗亭PC连接异常 00-恢复，01-异常',
  `server_connect_unnormal` tinyint(4) DEFAULT NULL COMMENT '服务器连接异常 00-恢复，01-异常',
  `Dis_module_con_unnormal` tinyint(4) DEFAULT NULL COMMENT '显示模块连接异常 00-恢复，01-异常',
  `voicemodule_con_unnormal` tinyint(4) DEFAULT NULL COMMENT '语音模块连接异常 00-恢复，01-异常',
  `road_gate_Con_unnormal` tinyint(4) DEFAULT NULL COMMENT '道闸连接异常 00-恢复，01-异常',
  `card_mach_con_unormal` tinyint(4) DEFAULT NULL COMMENT '卡机连接异常 00-恢复，01-异常',
  `paper_mach_con_unormal` tinyint(4) DEFAULT NULL COMMENT '纸票机连接异常 00-恢复，01-异常',
  `main_board_net_unnormal` tinyint(4) DEFAULT NULL COMMENT '主板网络异常 00-恢复，01-异常',
  `clock_unnormal` tinyint(4) DEFAULT NULL COMMENT '系统时钟故障 00-恢复，01-异常',
  `storage_unnormal` tinyint(4) DEFAULT NULL COMMENT '系统存储故障 00-恢复，01-异常',
  `online_time` datetime DEFAULT NULL COMMENT '在线时间',
  `last_update_time` datetime DEFAULT NULL COMMENT '最后一次更新时间',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:47:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for park_dis_detail
-- ----------------------------
DROP TABLE IF EXISTS `park_dis_detail`;
CREATE TABLE `park_dis_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `out_type` tinyint(4) DEFAULT NULL COMMENT '出场类型 0,出场.1，中心',
  `discount_id` int(11) DEFAULT NULL COMMENT '打折模式ID',
  `card_id` varchar(20) DEFAULT NULL COMMENT '卡id',
  `out_time` datetime DEFAULT NULL COMMENT '出场时间',
  `discount_time` datetime DEFAULT NULL COMMENT '打折时间',
  `dis_amount` float DEFAULT NULL COMMENT '打折金额',
  `dis_type` tinyint(4) DEFAULT NULL COMMENT '0表示线下，1表示线下',
  `online_id` varchar(50) DEFAULT NULL COMMENT '线上打折id号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:47:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for park_dis_info
-- ----------------------------
DROP TABLE IF EXISTS `park_dis_info`;
CREATE TABLE `park_dis_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eqid` int(11) DEFAULT NULL COMMENT '设备ID',
  `discount_id` int(11) DEFAULT NULL COMMENT '折扣ID',
  `discount_name` varchar(20) DEFAULT NULL COMMENT '折扣名称',
  `discount_type` tinyint(4) DEFAULT NULL COMMENT '折扣模式 1全免(0)  2 折扣率(<100) 3免时间(<65535) 4免金额(<65535)',
  `discount_amount` float DEFAULT NULL COMMENT '折扣值',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_name` varchar(20) DEFAULT NULL COMMENT '创建人',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_name` varchar(20) DEFAULT NULL COMMENT '修改人',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:48:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for park_dynamic_charge
-- ----------------------------
DROP TABLE IF EXISTS `park_dynamic_charge`;
CREATE TABLE `park_dynamic_charge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `charge_id` tinyint(4) DEFAULT NULL COMMENT '用来判断是否是同一个周期段',
  `car_type` tinyint(4) NOT NULL COMMENT '卡类型',
  `is_month_cross` tinyint(4) DEFAULT NULL COMMENT '是否月份跨段补时',
  `start_date` datetime DEFAULT NULL COMMENT '开始日期',
  `end_date` datetime DEFAULT NULL COMMENT '结束日期',
  `start_hour` datetime DEFAULT NULL COMMENT '开始时间',
  `end_hour` datetime DEFAULT NULL COMMENT '结束时间',
  `is_hour_cross` tinyint(4) DEFAULT NULL COMMENT '是否包含跨段补时',
  `is_periodic_charge` tinyint(4) DEFAULT NULL COMMENT '是否周期计费',
  `is_first_periodic` tinyint(4) DEFAULT NULL COMMENT '周期计费之后有首段',
  `is_first_cycle` tinyint(4) DEFAULT NULL COMMENT '是否首段',
  `charge_type` tinyint(4) DEFAULT NULL COMMENT '计费类型(0按时间计费，1：按次计费)',
  `top_money` int(4) DEFAULT NULL COMMENT '最高收费',
  `free_time` tinyint(4) DEFAULT NULL COMMENT '免费时间',
  `is_free_time` tinyint(4) DEFAULT NULL COMMENT '是否包含免费时间',
  `charge_unit` tinyint(4) DEFAULT NULL COMMENT '计费单位',
  `billing_amount` int(4) DEFAULT NULL COMMENT '计费金额',
  `time_length` tinyint(4) DEFAULT NULL COMMENT '时间长度',
  `sort` int(4) DEFAULT NULL COMMENT '顺序，用户确定同等级的展示的数据',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:48:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for park_effet_times
-- ----------------------------
DROP TABLE IF EXISTS `park_effet_times`;
CREATE TABLE `park_effet_times` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card_type` tinyint(4) DEFAULT NULL COMMENT '卡片类型',
  `b_time` datetime DEFAULT NULL COMMENT '开始时间',
  `e_time` datetime DEFAULT NULL COMMENT '结束时间',
  `s_type` tinyint(4) DEFAULT NULL COMMENT '类型',
  `multiple` tinyint(4) DEFAULT NULL COMMENT '时段功能',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:48:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for park_equipments
-- ----------------------------
DROP TABLE IF EXISTS `park_equipments`;
CREATE TABLE `park_equipments` (
  `eq_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长设备id',
  `eq_name` varchar(20) DEFAULT NULL COMMENT '设备名称',
  `memo` varchar(50) DEFAULT NULL COMMENT '备注',
  `client_no` varchar(50) DEFAULT NULL COMMENT '终端标识',
  `down_load` varchar(255) DEFAULT '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000' COMMENT '下载标记(控制器)',
  PRIMARY KEY (`eq_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:49:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for park_free_type
-- ----------------------------
DROP TABLE IF EXISTS `park_free_type`;
CREATE TABLE `park_free_type` (
  `free_type` int(11) NOT NULL AUTO_INCREMENT,
  `free_name` varchar(100) DEFAULT NULL COMMENT '免费类型名称',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_name` varchar(20) DEFAULT NULL COMMENT '创建人',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_name` varchar(20) DEFAULT NULL COMMENT '修改人',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`free_type`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:49:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for park_is_use
-- ----------------------------
DROP TABLE IF EXISTS `park_is_use`;
CREATE TABLE `park_is_use` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ykt_id` int(11) NOT NULL COMMENT '与开户主表关联',
  `card_id` varchar(20) DEFAULT NULL COMMENT '卡id',
  `card_no` varchar(10) DEFAULT NULL COMMENT '卡号',
  `car_no` varchar(10) DEFAULT NULL COMMENT '车牌号',
  `card_type` tinyint(4) DEFAULT NULL COMMENT '卡类型',
  `car_no_type` tinyint(4) DEFAULT NULL COMMENT '车牌类型',
  `car_place` varchar(10) DEFAULT NULL COMMENT '车位号',
  `car_color` varchar(10) DEFAULT NULL COMMENT '车颜色',
  `start_date` datetime DEFAULT NULL COMMENT '开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '结束时间',
  `down_load` varchar(255) DEFAULT NULL COMMENT '下载标记(控制器)',
  `is_load` tinyint(4) DEFAULT '0' COMMENT '上传标记',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_name` varchar(20) DEFAULT NULL COMMENT '创建人',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_name` varchar(20) DEFAULT NULL COMMENT '修改人',
  `is_use_type` tinyint(4) DEFAULT '0' COMMENT '0线下，1线上',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:49:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for park_local_set
-- ----------------------------
DROP TABLE IF EXISTS `park_local_set`;
CREATE TABLE `park_local_set` (
  `box_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '岗亭编号',
  `box_ip` varchar(20) DEFAULT NULL COMMENT '岗亭IP',
  `box_name` varchar(50) DEFAULT NULL COMMENT '岗亭名称',
  `box_type` tinyint(4) DEFAULT NULL COMMENT '岗亭类型0标准收费点1中央收费点2综合收费点',
  `ticket_com` tinyint(4) DEFAULT NULL COMMENT '扫描枪串口号',
  `place_type` tinyint(4) DEFAULT NULL COMMENT '车位类型（0混合车位、1临时车位、2固定车位）',
  `place_mode` tinyint(4) DEFAULT NULL COMMENT '车位模式（0大车场、小车场）',
  `place_num` int(11) DEFAULT NULL COMMENT '车位数(总数量)',
  `credentials_type` tinyint(4) DEFAULT '0' COMMENT '证件抓拍类型(0无1usb摄像头)',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_name` varchar(20) DEFAULT NULL COMMENT '创建人',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_name` varchar(20) DEFAULT NULL COMMENT '修改人',
  `is_load` tinyint(4) DEFAULT NULL COMMENT '是否上传0未上传1正在上传2上传成功',
  PRIMARY KEY (`box_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:49:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for park_month_set
-- ----------------------------
DROP TABLE IF EXISTS `park_month_set`;
CREATE TABLE `park_month_set` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增加id',
  `account_type` tinyint(4) DEFAULT NULL COMMENT '月租卡类型(11,12,13,14)',
  `s_type` tinyint(4) DEFAULT NULL COMMENT '类型(0月卡1季度卡2半年卡3年卡)',
  `charge_money` float DEFAULT NULL COMMENT '金额',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_name` varchar(20) DEFAULT NULL COMMENT '创建人',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_name` varchar(20) DEFAULT NULL COMMENT '修改人',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:49:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for park_standard_charge
-- ----------------------------
DROP TABLE IF EXISTS `park_standard_charge`;
CREATE TABLE `park_standard_charge` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card_type` tinyint(4) NOT NULL COMMENT '卡类型',
  `free_time` int(11) DEFAULT NULL COMMENT '免费时间',
  `is_free_time` tinyint(4) DEFAULT NULL COMMENT '是否包含免费时间',
  `unit_type` tinyint(4) DEFAULT NULL COMMENT '收费单位（0——》元，1——》角）',
  `is_unit_type` tinyint(4) DEFAULT NULL COMMENT '是否收费有小数',
  `top_money` int(4) DEFAULT NULL COMMENT '最高收费',
  `ch_hour1` tinyint(4) DEFAULT NULL COMMENT '1小时',
  `ch_hour2` tinyint(4) DEFAULT NULL COMMENT '2小时',
  `ch_hour3` tinyint(4) DEFAULT NULL COMMENT '3小时',
  `ch_hour4` tinyint(4) DEFAULT NULL COMMENT '4小时',
  `ch_hour5` tinyint(4) DEFAULT NULL COMMENT '5小时',
  `ch_hour6` tinyint(4) DEFAULT NULL COMMENT '6小时',
  `ch_hour7` tinyint(4) DEFAULT NULL COMMENT '7小时',
  `ch_hour8` tinyint(4) DEFAULT NULL COMMENT '8小时',
  `ch_hour9` tinyint(4) DEFAULT NULL COMMENT '9小时',
  `ch_hour10` tinyint(4) DEFAULT NULL COMMENT '10小时',
  `ch_hour11` tinyint(4) DEFAULT NULL COMMENT '11小时',
  `ch_hour12` tinyint(4) DEFAULT NULL COMMENT '12小时',
  `ch_hour13` tinyint(4) DEFAULT NULL COMMENT '13小时',
  `ch_hour14` tinyint(4) DEFAULT NULL COMMENT '14小时',
  `ch_hour15` tinyint(4) DEFAULT NULL COMMENT '15小时',
  `ch_hour16` tinyint(4) DEFAULT NULL COMMENT '16小时',
  `ch_hour17` tinyint(4) DEFAULT NULL COMMENT '17小时',
  `ch_hour18` tinyint(4) DEFAULT NULL COMMENT '18小时',
  `ch_hour19` tinyint(4) DEFAULT NULL COMMENT '19小时',
  `ch_hour20` tinyint(4) DEFAULT NULL COMMENT '20小时',
  `ch_hour21` tinyint(4) DEFAULT NULL COMMENT '21小时',
  `ch_hour22` tinyint(4) DEFAULT NULL COMMENT '22小时',
  `ch_hour23` tinyint(4) DEFAULT NULL COMMENT '23小时',
  `ch_hour24` tinyint(4) DEFAULT NULL COMMENT '24小时',
  `a_zero` int(6) DEFAULT NULL COMMENT '跨段收费金额',
  `is_a_zero` tinyint(4) DEFAULT NULL COMMENT '是否包含跨段收费金额',
  `a_type` tinyint(4) DEFAULT NULL COMMENT '跨段类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:49:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for park_sum_user
-- ----------------------------
DROP TABLE IF EXISTS `park_sum_user`;
CREATE TABLE `park_sum_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `box_id` int(11) DEFAULT NULL COMMENT '岗亭编号',
  `login_date` datetime DEFAULT NULL COMMENT '登入时间',
  `relief_date` datetime DEFAULT NULL COMMENT '交班时间',
  `user_name` varchar(20) DEFAULT NULL COMMENT '交班操作员',
  `relief_user_name` varchar(20) DEFAULT NULL COMMENT '接班操作员',
  `hand_gate` int(11) DEFAULT NULL COMMENT '手动开闸',
  `computer_gate` int(11) DEFAULT NULL COMMENT '计算机开闸',
  `temp_car_in` int(11) DEFAULT NULL COMMENT '临时车入场数',
  `temp_car_out` int(11) DEFAULT NULL COMMENT '临时车出场数',
  `month_car_in` int(11) DEFAULT NULL COMMENT '月租车入场数',
  `month_car_out` int(11) DEFAULT NULL COMMENT '月租车出场数',
  `stored_car_num` int(11) DEFAULT NULL COMMENT '储值卡数',
  `stored_car_money` float DEFAULT '0' COMMENT '储值车收费金额',
  `temp_free` int(11) DEFAULT NULL COMMENT '临免卡张数',
  `free_car_no` int(11) DEFAULT NULL COMMENT '免费卡数',
  `free_charge` float DEFAULT '0' COMMENT '免费金额',
  `dis_sum` int(11) DEFAULT NULL COMMENT '折扣张数',
  `dis_charge` float DEFAULT '0' COMMENT '折扣金额',
  `cash_charge` float DEFAULT '0' COMMENT '现金收费金额',
  `wechat_charge` float DEFAULT '0' COMMENT '微信收费金额',
  `alipay_charge` float DEFAULT '0' COMMENT '支付定收费金额',
  `unionpay_chagre` float DEFAULT '0' COMMENT '银联支付',
  `thirdpay_charge` float DEFAULT '0' COMMENT '第三方支付',
  `account` float DEFAULT '0' COMMENT '应收金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:49:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for park_traffic_statistics
-- ----------------------------
DROP TABLE IF EXISTS `park_traffic_statistics`;
CREATE TABLE `park_traffic_statistics` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `s_date` date DEFAULT NULL COMMENT '日期',
  `s_time` tinyint(4) NOT NULL COMMENT '小时',
  `hand_gate` int(11) DEFAULT '0' COMMENT '手动开闸',
  `computer_gate` int(11) DEFAULT '0' COMMENT '电脑开闸',
  `month_card_in_a` int(11) DEFAULT '0' COMMENT '月租卡A入',
  `month_card_out_a` int(11) DEFAULT '0' COMMENT '月租卡A出',
  `month_card_in_b` int(11) DEFAULT '0' COMMENT '月租卡B入',
  `month_card_out_b` int(11) DEFAULT '0' COMMENT '月租卡B出',
  `month_card_in_c` int(11) DEFAULT '0' COMMENT '月租卡C入',
  `month_card_out_c` int(11) DEFAULT '0' COMMENT '月租卡C出',
  `month_card_in_d` int(11) DEFAULT '0' COMMENT '月租卡D入',
  `month_card_out_d` int(11) DEFAULT '0' COMMENT '月租卡D出',
  `month_tmp_in_a` int(11) DEFAULT '0' COMMENT '月临卡A入',
  `month_tmp_out_a` int(11) DEFAULT '0' COMMENT '月临卡A出',
  `month_tmp_in_b` int(11) DEFAULT '0' COMMENT '月临卡B入',
  `month_tmp_out_b` int(11) DEFAULT '0' COMMENT '月临卡B出',
  `month_tmp_in_c` int(11) DEFAULT '0' COMMENT '月临卡C入',
  `month_tmp_out_c` int(11) DEFAULT '0' COMMENT '月临卡C出',
  `month_tmp_in_d` int(11) DEFAULT '0' COMMENT '月临卡D入',
  `month_tmp_out_d` int(11) DEFAULT '0' COMMENT '月临卡D出',
  `temp_card_in_a` int(11) DEFAULT '0' COMMENT '临时卡A入',
  `temp_card_out_a` int(11) DEFAULT '0' COMMENT '临时卡A出',
  `temp_card_in_b` int(11) DEFAULT '0' COMMENT '临时卡B入',
  `temp_card_out_b` int(11) DEFAULT '0' COMMENT '临时卡B出',
  `temp_card_in_c` int(11) DEFAULT '0' COMMENT '临时卡C入',
  `temp_card_out_c` int(11) DEFAULT '0' COMMENT '临时卡C出',
  `temp_card_in_d` int(11) DEFAULT '0' COMMENT '临时卡D入',
  `temp_card_out_d` int(11) DEFAULT '0' COMMENT '临时卡D出',
  `temp_free_in_card` int(11) DEFAULT '0' COMMENT '临免卡入',
  `temp_free_out_card` int(11) DEFAULT '0' COMMENT '临免卡出',
  `free_card_in_a` int(11) DEFAULT '0' COMMENT '免费卡A入',
  `free_card_out_a` int(11) DEFAULT '0' COMMENT '免费卡A出',
  `free_card_in_b` int(11) DEFAULT '0' COMMENT '免费卡B入',
  `free_card_out_b` int(11) DEFAULT '0' COMMENT '免费卡B出',
  `stored_card_in_a` int(11) DEFAULT '0' COMMENT '储值卡A入',
  `stored_card_out_a` int(11) DEFAULT '0' COMMENT '储值卡A出',
  `stored_card_in_b` int(11) DEFAULT '0' COMMENT '储值卡B入',
  `stored_card_out_b` int(11) DEFAULT '0' COMMENT '储值卡B出',
  `stored_card_in_c` int(11) DEFAULT '0' COMMENT '储值卡C入',
  `stored_card_out_c` int(11) DEFAULT '0' COMMENT '储值卡C出',
  `stored_card_in_d` int(11) DEFAULT '0' COMMENT '储值卡D入',
  `stored_card_out_d` int(11) DEFAULT '0' COMMENT '储值卡D出',
  `stored_temp_in_a` int(11) DEFAULT '0' COMMENT '储临卡A入',
  `stored_temp_out_a` int(11) DEFAULT '0' COMMENT '储临卡A出',
  `stored_temp_in_b` int(11) DEFAULT '0' COMMENT '储临卡B入',
  `stored_temp_out_b` int(11) DEFAULT '0' COMMENT '储临卡B出',
  `stored_temp_in_c` int(11) DEFAULT '0' COMMENT '储临卡C入',
  `stored_temp_out_c` int(11) DEFAULT '0' COMMENT '储临卡C出',
  `stored_temp_in_d` int(11) DEFAULT '0' COMMENT '储临卡D入',
  `stored_temp_out_d` int(11) DEFAULT '0' COMMENT '储临卡D出',
  `all_in_count` int(11) DEFAULT '0' COMMENT '入场总数',
  `all_out_count` int(11) DEFAULT '0' COMMENT '出场总数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:50:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for park_usage_statistics
-- ----------------------------
DROP TABLE IF EXISTS `park_usage_statistics`;
CREATE TABLE `park_usage_statistics` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `s_day` varchar(50) DEFAULT NULL COMMENT '日期(yyyy-MM-dd)',
  `card_type` tinyint(4) NOT NULL COMMENT '卡类型',
  `in_time` datetime DEFAULT NULL COMMENT '入场时间',
  `out_time` datetime DEFAULT NULL COMMENT '出场时间',
  `in_hour` tinyint(4) DEFAULT '0' COMMENT '入场小时',
  `out_hour` tinyint(4) DEFAULT '0' COMMENT '出场小时',
  `t_0` float DEFAULT '0' COMMENT '第0小时',
  `t_1` float DEFAULT '0' COMMENT '第1小时',
  `t_2` float DEFAULT '0' COMMENT '第2小时',
  `t_3` float DEFAULT '0' COMMENT '第3小时',
  `t_4` float DEFAULT '0' COMMENT '第4小时',
  `t_5` float DEFAULT '0' COMMENT '第5小时',
  `t_6` float DEFAULT '0' COMMENT '第6小时',
  `t_7` float DEFAULT '0' COMMENT '第7小时',
  `t_8` float DEFAULT '0' COMMENT '第8小时',
  `t_9` float DEFAULT '0' COMMENT '第9小时',
  `t_10` float DEFAULT '0' COMMENT '第10小时',
  `t_11` float DEFAULT '0' COMMENT '第11小时',
  `t_12` float DEFAULT '0' COMMENT '第12小时',
  `t_13` float DEFAULT '0' COMMENT '第13小时',
  `t_14` float DEFAULT '0' COMMENT '第14小时',
  `t_15` float DEFAULT '0' COMMENT '第15小时',
  `t_16` float DEFAULT '0' COMMENT '第16小时',
  `t_17` float DEFAULT '0' COMMENT '第17小时',
  `t_18` float DEFAULT '0' COMMENT '第18小时',
  `t_19` float DEFAULT '0' COMMENT '第19小时',
  `t_20` float DEFAULT '0' COMMENT '第20小时',
  `t_21` float DEFAULT '0' COMMENT '第21小时',
  `t_22` float DEFAULT '0' COMMENT '第22小时',
  `t_23` float DEFAULT '0' COMMENT '第23小时',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:50:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for per_dept
-- ----------------------------
DROP TABLE IF EXISTS `per_dept`;
CREATE TABLE `per_dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '部门编号',
  `dept_name` varchar(20) DEFAULT NULL COMMENT '部门名称',
  `top_dept_id` int(11) DEFAULT NULL COMMENT '上级部门',
  `org_id` varchar(20) DEFAULT NULL,
  `update_flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:50:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for per_persons
-- ----------------------------
DROP TABLE IF EXISTS `per_persons`;
CREATE TABLE `per_persons` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `per_id` varchar(15) DEFAULT NULL COMMENT '编号',
  `per_name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `per_id_code` varchar(20) DEFAULT NULL COMMENT '识别码',
  `per_addr` varchar(255) DEFAULT NULL COMMENT '住址',
  `wechat_no` varchar(255) DEFAULT NULL COMMENT '微信号',
  `per_tel` varchar(20) DEFAULT NULL COMMENT '手机',
  `land_line_num` varchar(20) DEFAULT NULL COMMENT '座机号',
  `per_id_no` varchar(20) DEFAULT NULL COMMENT '身份证',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别',
  `car_no` varchar(10) DEFAULT NULL COMMENT '车牌号',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `per_nation` varchar(10) DEFAULT NULL COMMENT '民族',
  `per_place` varchar(50) DEFAULT NULL COMMENT '籍贯',
  `per_zip_code` varchar(10) DEFAULT NULL COMMENT '邮编',
  `marry` varchar(10) DEFAULT NULL COMMENT '婚姻状况',
  `per_email` varchar(20) DEFAULT NULL COMMENT '邮箱',
  `per_type` tinyint(4) DEFAULT NULL COMMENT '员工类型',
  `enter_date` date DEFAULT NULL COMMENT '入职日期',
  `format_date` date DEFAULT NULL COMMENT '转正日期',
  `edu_level` varchar(100) DEFAULT NULL COMMENT '学历',
  `political_status` varchar(100) DEFAULT NULL COMMENT '政治面貌',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门编号',
  `positions` varchar(20) DEFAULT NULL COMMENT '职务',
  `is_leave` tinyint(4) DEFAULT NULL COMMENT '是否离职',
  `leave_date` date DEFAULT NULL COMMENT '离职日期',
  `leave_cause` varchar(255) DEFAULT NULL COMMENT '离职原因',
  `create_date` datetime DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `modify_date` datetime DEFAULT NULL COMMENT '修改人',
  `modify_user_name` varchar(20) DEFAULT NULL COMMENT '修改时间',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `isload` int(11) DEFAULT '0' COMMENT '是否上传',
  `per_pic` varchar(2000) DEFAULT NULL COMMENT '人员头像上传的路径',
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:50:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_function
-- ----------------------------
DROP TABLE IF EXISTS `sys_function`;
CREATE TABLE `sys_function` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fun_code` varchar(10) NOT NULL COMMENT '权限编号(一级01、02,二级0101、0102、0001、0002)',
  `fun_name` varchar(30) NOT NULL COMMENT '功能名称',
  `fun_type` tinyint(4) DEFAULT NULL COMMENT '权限类型(0功能1权限)',
  `fun_no` int(11) NOT NULL COMMENT '权限值',
  `fun_son` tinyint(4) DEFAULT NULL COMMENT '是否有子权限（0代表一个权限，1代表4个权限）',
  `fun_mod_name` varchar(50) DEFAULT NULL COMMENT '模块名称方便程序调用',
  `fun_right` varchar(10) DEFAULT '11111111' COMMENT '功能模块权限：1公共/2停車場/3門禁/4消費/5考勤/6巡更/7訪客',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_name` varchar(20) DEFAULT NULL COMMENT '创建人',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_name` varchar(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:50:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_login_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_user`;
CREATE TABLE `sys_login_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(20) DEFAULT NULL COMMENT '登录名/工号',
  `user_name` varchar(20) DEFAULT NULL COMMENT '名称',
  `pss_word` varchar(50) DEFAULT NULL COMMENT '密码',
  `opera_carNo` varchar(10) DEFAULT NULL COMMENT '操作卡卡号',
  `per_seq` varchar(20) DEFAULT NULL COMMENT '权限序列(1一卡通/2停车场/3门禁/4消费/5考勤/6巡更/7访客',
  `Popedom_list` varchar(2000) DEFAULT NULL COMMENT '菜单权限序列（一卡通300个，子系统50个）',
  `rank` tinyint(4) DEFAULT NULL COMMENT '操作员级别(0操作组1财务组2管理组100系统管理员)',
  `login_type` tinyint(4) DEFAULT NULL COMMENT '登录方式（0、密码；1、刷卡登录；2、密码+刷卡）',
  `load_type` tinyint(4) DEFAULT NULL COMMENT '进入方式（1监控界面）',
  `is_stop` tinyint(4) DEFAULT NULL COMMENT '是否停用',
  `memo` varchar(50) DEFAULT NULL COMMENT '备注',
  `user_type` int(11) DEFAULT NULL COMMENT '用户类型',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_name` varchar(20) DEFAULT NULL COMMENT '创建人',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_name` varchar(20) DEFAULT NULL COMMENT '修改人',
  `is_load` tinyint(4) DEFAULT '0' COMMENT '0未上传1已上传',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:50:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_parameters
-- ----------------------------
DROP TABLE IF EXISTS `sys_parameters`;
CREATE TABLE `sys_parameters` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parameter_code` varchar(60) DEFAULT NULL COMMENT '字段编码',
  `parameter_name` varchar(100) DEFAULT NULL COMMENT '字段名称',
  `parameter_value` varchar(5000) DEFAULT NULL COMMENT '字段值',
  `type_id` int(11) DEFAULT NULL COMMENT '1系统设置 2车场设置',
  `is_edit` int(11) DEFAULT NULL COMMENT '是否更改',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_name` varchar(20) DEFAULT NULL COMMENT '创建人',
  `modify_date` datetime DEFAULT NULL COMMENT '修改时间',
  `modify_user_name` varchar(20) DEFAULT NULL COMMENT '修改人',
  `memo` varchar(1000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `parameter_code_UNIQUE` (`parameter_code`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:50:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_version
-- ----------------------------
DROP TABLE IF EXISTS `sys_version`;
CREATE TABLE `sys_version` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `v_soft` varchar(400) DEFAULT NULL COMMENT '版本号',
  `v_type` tinyint(4) DEFAULT NULL COMMENT '类型0软件版本1硬件版本',
  `pulish_date` datetime DEFAULT NULL COMMENT '发布时间',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:50:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ykt_backup
-- ----------------------------
DROP TABLE IF EXISTS `ykt_backup`;
CREATE TABLE `ykt_backup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `back_date` datetime DEFAULT NULL COMMENT '备份的日期',
  `back_name` varchar(50) DEFAULT NULL COMMENT '备注文件名',
  `back_path` varchar(300) DEFAULT NULL COMMENT '备份路径',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:50:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ykt_card_issue
-- ----------------------------
DROP TABLE IF EXISTS `ykt_card_issue`;
CREATE TABLE `ykt_card_issue` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `c_flag` tinyint(4) DEFAULT NULL COMMENT '卡介质(0为IC，1为ID，2IC做ID，3.身份证，4.纯车牌，5.ETC，6.CPU)',
  `p_id` int(11) DEFAULT NULL COMMENT '人事ID',
  `foregift` float DEFAULT '0' COMMENT '押金',
  `balance_money` float DEFAULT '0' COMMENT '账户余额',
  `status` tinyint(4) DEFAULT '0' COMMENT '卡状态（0正常、1正在挂失、2已挂失、3正在解挂、4已补卡、5挂失退款、6已销户',
  `sys_right` varchar(255) DEFAULT NULL COMMENT '系统权限(车场；门禁；消费；考勤；巡更；访客；水控；）',
  `is_share` tinyint(4) DEFAULT NULL COMMENT '0不共享车位1共享车位',
  `place_num` varchar(20) DEFAULT NULL COMMENT '车位编号',
  `create_date` datetime DEFAULT NULL COMMENT '创建人',
  `create_user_name` varchar(20) DEFAULT NULL COMMENT '创建时间',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:50:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ykt_card_issue_park
-- ----------------------------
DROP TABLE IF EXISTS `ykt_card_issue_park`;
CREATE TABLE `ykt_card_issue_park` (
  `ykt_id` int(11) NOT NULL COMMENT 'id，与主卡id的值一样',
  `card_type` int(11) NOT NULL COMMENT '卡类型',
  `s_type` int(11) DEFAULT NULL COMMENT '月租类型',
  `car_no` varchar(10) DEFAULT NULL COMMENT '车牌号',
  `car_no_type` int(11) DEFAULT NULL COMMENT '车牌类型',
  `car_colour` varchar(10) DEFAULT NULL COMMENT '车颜色',
  `start_date` datetime DEFAULT NULL COMMENT '有效起始日期',
  `end_date` datetime DEFAULT NULL COMMENT '有效终止日期',
  `plan_id` int(11) NOT NULL COMMENT '权限级别',
  PRIMARY KEY (`ykt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:50:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ykt_card_issue_rel
-- ----------------------------
DROP TABLE IF EXISTS `ykt_card_issue_rel`;
CREATE TABLE `ykt_card_issue_rel` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `ykt_id` int(11) DEFAULT NULL COMMENT '卡id',
  `mach_no` int(11) DEFAULT NULL COMMENT '控制器id',
  `sign` int(11) DEFAULT NULL COMMENT '下载的标志（0表示未下载 1表示已下载）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='卡下载标记类';
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:51:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ykt_card_rsmoney
-- ----------------------------
DROP TABLE IF EXISTS `ykt_card_rsmoney`;
CREATE TABLE `ykt_card_rsmoney` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `yktid` int(11) NOT NULL COMMENT '开户ID号',
  `s_type` tinyint(4) DEFAULT NULL COMMENT '操作类型（0卡发行1卡延期2挂失3解挂4补发5退款6销户）',
  `front_date` datetime DEFAULT NULL COMMENT '前结束日期（延期用）',
  `new_start_date` datetime DEFAULT NULL COMMENT '新起始日期（延期用）',
  `new_end_date` datetime DEFAULT NULL COMMENT '新终止日期（延期用)',
  `balance_money` float DEFAULT NULL COMMENT '发生金额',
  `pay_type` tinyint(4) DEFAULT NULL COMMENT '付款方式(0现金1银联闪付2微信3支付宝)',
  `foregift` float DEFAULT NULL COMMENT '押金',
  `before_money` float DEFAULT NULL COMMENT '发生前余额（储值卡用）',
  `create_date` datetime DEFAULT NULL COMMENT '操作时间',
  `create_user_name` varchar(20) DEFAULT NULL COMMENT '操作员',
  `order_no` varchar(100) DEFAULT NULL COMMENT '交易订单号',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.89_3306
Source Server Version : 50720
Source Host           : 192.168.1.89:3306
Source Database       : ykt

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-08 08:51:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ykt_login
-- ----------------------------
DROP TABLE IF EXISTS `ykt_login`;
CREATE TABLE `ykt_login` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `LogDate` datetime DEFAULT NULL COMMENT '操作日期',
  `UserName` varchar(20) DEFAULT NULL COMMENT '操作员编号',
  `Computer` varchar(200) DEFAULT NULL COMMENT '计算机名（电脑IP）',
  `LogObj` varchar(200) DEFAULT NULL COMMENT '操作对象',
  `LogMod` varchar(200) DEFAULT NULL COMMENT '操作方法',
  `LogCon` varchar(500) DEFAULT NULL COMMENT '操作内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=381 DEFAULT CHARSET=utf8;
