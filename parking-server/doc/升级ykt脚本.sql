DROP VIEW
IF EXISTS vw_park_car_in;

CREATE VIEW vw_park_car_in AS SELECT
	`a`.`id` AS `id`,
	`a`.`guid` AS `guid`,
	`a`.`card_id` AS `card_id`,
	`a`.`c_flag` AS `c_flag`,
	`a`.`card_no` AS `card_no`,
	`a`.`card_type` AS `card_type`,
	`a`.`emp_name` AS `emp_name`,
	`a`.`in_time` AS `in_time`,
	`a`.`in_user_name` AS `in_user_name`,
	`a`.`car_no` AS `car_no`,
	`a`.`assistant_car_no` AS `assistant_car_no`,
	`a`.`in_pic` AS `in_pic`,
	`a`.`back_in_pic` AS `back_in_pic`,
	`a`.`small_pic` AS `small_pic`,
	`a`.`assistant_small_pic` AS `assistant_small_pic`,
	`a`.`car_no_type` AS `car_no_type`,
	`a`.`small` AS `small`,
	`a`.`discount_no` AS `discount_no`,
	`a`.`type_id` AS `type_id`,
	`a`.`discount_time` AS `discount_time`,
	`a`.`makeup` AS `makeup`,
	`a`.`makeup_user_name` AS `makeup_user_name`,
	`a`.`is_locked` AS `is_locked`,
	`a`.`in_way` AS `in_way`,
	(
		CASE ifnull(`a`.`in_way`, 0)
		WHEN 0 THEN
			'正常入场'
		WHEN 1 THEN
			'校正入场'
		WHEN 2 THEN
			'手工入场'
		WHEN 3 THEN
			'扫码入场'
		WHEN 4 THEN
			'脱机记录'
		WHEN 5 THEN
			'相机异常记录'
		WHEN 6 THEN
			'异常开闸'
		ELSE
			'未知'
		END
	) AS `in_Way_name`,
	`b`.`a_custname` AS `a_custname`
FROM
	(
		`park_car_in` `a`
		LEFT JOIN `park_account_type` `b` ON (
			(
				`a`.`card_type` = `b`.`a_type`
			)
		)
	);

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
-- ----------------------------
-- VIEW structure for vw_park_car_out
-- ----------------------------
DROP VIEW
IF EXISTS vw_park_car_out;

CREATE VIEW vw_park_car_out AS SELECT
	`a`.`id` AS `id`,
	`a`.`in_id` AS `in_id`,
	`a`.`guid` AS `guid`,
	`a`.`in_mach_no` AS `in_mach_no`,
	`a`.`out_mach_no` AS `out_mach_no`,
	`a`.`ykt_id` AS `ykt_id`,
	`a`.`card_id` AS `card_id`,
	`a`.`c_flag` AS `c_flag`,
	`a`.`card_no` AS `card_no`,
	`a`.`in_car_no` AS `in_car_no`,
	`a`.`back_in_car_no` AS `back_in_car_no`,
	`a`.`out_car_no` AS `out_car_no`,
	`a`.`back_out_car_no` AS `back_out_car_no`,
	`a`.`car_no_type` AS `car_no_type`,
	`a`.`card_type` AS `card_type`,
	`a`.`free_type` AS `free_type`,
	`a`.`in_time` AS `in_time`,
	`a`.`out_time` AS `out_time`,
	`a`.`in_pic` AS `in_pic`,
	`a`.`back_in_pic` AS `back_in_pic`,
	`a`.`out_pic` AS `out_pic`,
	`a`.`back_out_pic` AS `back_out_pic`,
	`a`.`in_user_name` AS `in_user_name`,
	`a`.`out_user_name` AS `out_user_name`,
	`a`.`credentials_pic` AS `credentials_pic`,
	`a`.`account_charge` AS `account_charge`,
	`a`.`pay_charge` AS `pay_charge`,
	`a`.`balance_money` AS `balance_money`,
	`a`.`discount_no` AS `discount_no`,
	`a`.`type_id` AS `type_id`,
	`a`.`discount_time` AS `discount_time`,
	`a`.`order_num` AS `order_num`,
	`a`.`memo` AS `memo`,
	`a`.`is_load` AS `is_load`,
	(
		CASE
		WHEN (`a`.`free_type` > 0) THEN
			0
		ELSE
			(
				`a`.`account_charge` - `a`.`pay_charge`
			)
		END
	) AS `dis_amount`,
	(
		CASE
		WHEN (
			ifnull(`a`.`free_type`, 0) > 0
		) THEN
			(
				`a`.`account_charge` - `a`.`pay_charge`
			)
		ELSE
			0
		END
	) AS `free_amount`,
	`b`.`channel_name` AS `in_channel_name`,
	`c`.`channel_name` AS `out_channel_name`,
	`d`.`a_custname` AS `card_type_name`,
	`a`.`out_way` AS `out_way`,
	(
		CASE `a`.`out_way`
		WHEN 0 THEN
			'正常出场'
		WHEN 1 THEN
			'手工放行'
		WHEN 2 THEN
			'异常放行'
		WHEN 3 THEN
			'扫码出场'
		WHEN 4 THEN
			'脱机记录'
		WHEN 5 THEN
			'相机异常记录'
		WHEN 6 THEN
			'异常开闸'
		ELSE
			'未知'
		END
	) AS `out_way_name`,
	`e`.`free_name` AS `free_name`
FROM
	(
		(
			(
				(
					`park_car_out` `a`
					LEFT JOIN `park_channel_set` `b` ON (
						(
							`a`.`in_mach_no` = `b`.`mach_no`
						)
					)
				)
				LEFT JOIN `park_channel_set` `c` ON (
					(
						`a`.`out_mach_no` = `c`.`mach_no`
					)
				)
			)
			LEFT JOIN `park_account_type` `d` ON (
				(
					`a`.`card_type` = `d`.`a_type`
				)
			)
		)
		LEFT JOIN `park_free_type` `e` ON (
			(
				`a`.`free_type` = `e`.`free_type`
			)
		)
	);

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
-- ----------------------------
-- VIEW structure for vw_car_in_exception
-- ----------------------------
DROP VIEW
IF EXISTS vw_car_in_exception;

CREATE VIEW vw_car_in_exception AS SELECT
	`a`.`id` AS `id`,
	`a`.`mach_no` AS `mach_no`,
	`a`.`ykt_id` AS `ykt_id`,
	`a`.`card_id` AS `card_id`,
	`a`.`c_flag` AS `c_flag`,
	`a`.`card_no` AS `card_no`,
	`a`.`emp_name` AS `emp_name`,
	`a`.`car_no` AS `car_no`,
	`a`.`assistant_car_no` AS `assistant_car_no`,
	`a`.`card_type` AS `card_type`,
	`a`.`car_no_type` AS `car_no_type`,
	`a`.`in_time` AS `in_time`,
	`a`.`in_user_name` AS `in_user_name`,
	`a`.`in_pic` AS `in_pic`,
	`a`.`back_in_pic` AS `back_in_pic`,
	`a`.`small_pic` AS `small_pic`,
	`a`.`assistant_small_pic` AS `assistant_small_pic`,
	`a`.`b_in_time` AS `b_in_time`,
	`a`.`b_in_pic` AS `b_in_pic`,
	`a`.`b_back_in_pic` AS `b_back_in_pic`,
	`a`.`b_small_pic` AS `b_small_pic`,
	`a`.`b_assistant_small_pic` AS `b_assistant_small_pic`,
	`b`.`channel_name` AS `channel_name`,
	`c`.`a_custname` AS `a_custname`,
	`a`.`in_way` AS `in_way`,
	(
		CASE `a`.`in_way`
		WHEN 0 THEN
			'正常入场'
		WHEN 1 THEN
			'校正入场'
		WHEN 2 THEN
			'手工入场'
		WHEN 3 THEN
			'异常开闸'
		WHEN 4 THEN
			'入场回退'
		WHEN 5 THEN
			'未授权入场'
		END
	) AS `in_way_name`
FROM
	(
		(
			`park_car_in_exception` `a`
			LEFT JOIN `park_channel_set` `b` ON (
				(
					`a`.`mach_no` = `b`.`mach_no`
				)
			)
		)
		LEFT JOIN `park_account_type` `c` ON (
			(
				(`c`.`a_code` = 'CARD_TYPE')
				AND (
					`a`.`card_type` = `c`.`a_type`
				)
			)
		)
	);

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
-- ----------------------------
-- VIEW structure for vw_abandon_record
-- ----------------------------
DROP VIEW
IF EXISTS vw_abandon_record;

CREATE VIEW vw_abandon_record AS SELECT
	`a`.`id` AS `id`,
	`a`.`in_mach_no` AS `in_mach_no`,
	`a`.`out_mach_no` AS `out_mach_no`,
	`a`.`ykt_id` AS `ykt_id`,
	`a`.`card_id` AS `card_id`,
	`a`.`c_flag` AS `c_flag`,
	`a`.`card_no` AS `card_no`,
	`a`.`car_no` AS `car_no`,
	`a`.`car_no_type` AS `car_no_type`,
	`a`.`card_type` AS `card_type`,
	`a`.`free_type` AS `free_type`,
	`a`.`in_time` AS `in_time`,
	`a`.`in_pic` AS `in_pic`,
	`a`.`in_user_name` AS `in_user_name`,
	`a`.`out_time` AS `out_time`,
	`a`.`out_pic` AS `out_pic`,
	`a`.`out_user_name` AS `out_user_name`,
	`a`.`zj_pic` AS `zj_pic`,
	`a`.`pay_charge` AS `pay_charge`,
	`a`.`balance_money` AS `balance_money`,
	`a`.`discount_no` AS `discount_no`,
	`a`.`type_id` AS `type_id`,
	`a`.`discount_time` AS `discount_time`,
	(
		CASE
		WHEN (`a`.`free_type` > 0) THEN
			0
		ELSE
			`a`.`dis_amount`
		END
	) AS `dis_amount`,
	`a`.`account_charge` AS `account_charge`,
	`a`.`memo` AS `memo`,
	(
		CASE
		WHEN (
			ifnull(`a`.`free_type`, 0) > 0
		) THEN
			(
				`a`.`account_charge` - `a`.`pay_charge`
			)
		ELSE
			0
		END
	) AS `free_amount`,
	`b`.`channel_name` AS `in_channel_name`,
	`c`.`channel_name` AS `out_channel_name`,
	`g`.`a_custname` AS `card_type_name`,
	`h`.`free_name` AS `free_name`,
	`a`.`out_way` AS `out_way`,
	(
		CASE `a`.`out_way`
		WHEN 0 THEN
			'正常出场'
		WHEN 1 THEN
			'手工放行'
		ELSE
			'校正出场'
		END
	) AS `out_way_name`
FROM
	(
		(
			(
				(
					`abandon_record` `a`
					LEFT JOIN `park_channel_set` `b` ON (
						(
							`a`.`in_mach_no` = `b`.`mach_no`
						)
					)
				)
				LEFT JOIN `park_channel_set` `c` ON (
					(
						`a`.`out_mach_no` = `c`.`mach_no`
					)
				)
			)
			LEFT JOIN `park_account_type` `g` ON (
				(
					(`g`.`a_code` = 'CARD_TYPE')
					AND (
						`a`.`card_type` = `g`.`a_type`
					)
				)
			)
		)
		LEFT JOIN `park_free_type` `h` ON (
			(
				`a`.`free_type` = `h`.`free_type`
			)
		)
	)
UNION
	SELECT
		`a`.`id` AS `id`,
		`a`.`in_mach_no` AS `in_mach_no`,
		`a`.`out_mach_no` AS `out_mach_no`,
		`a`.`ykt_id` AS `ykt_id`,
		`a`.`card_id` AS `card_id`,
		`a`.`c_flag` AS `c_flag`,
		`a`.`card_no` AS `card_no`,
		`a`.`out_car_no` AS `car_no`,
		`a`.`car_no_type` AS `car_no_type`,
		`a`.`card_type` AS `card_type`,
		`a`.`free_type` AS `free_type`,
		`a`.`in_time` AS `in_time`,
		`a`.`in_pic` AS `in_pic`,
		`a`.`in_user_name` AS `in_user_name`,
		`a`.`out_time` AS `out_time`,
		`a`.`out_pic` AS `out_pic`,
		`a`.`out_user_name` AS `out_user_name`,
		`a`.`credentials_pic` AS `zj_pic`,
		`a`.`pay_charge` AS `pay_charge`,
		`a`.`balance_money` AS `balance_money`,
		`a`.`discount_no` AS `discount_no`,
		`a`.`type_id` AS `type_id`,
		`a`.`discount_time` AS `discount_time`,
		(
			CASE
			WHEN (`a`.`free_type` > 0) THEN
				0
			ELSE
				`a`.`dis_amount`
			END
		) AS `dis_amount`,
		`a`.`account_charge` AS `account_charge`,
		`a`.`memo` AS `memo`,
		(
			CASE
			WHEN (
				ifnull(`a`.`free_type`, 0) > 0
			) THEN
				(
					`a`.`account_charge` - `a`.`pay_charge`
				)
			ELSE
				0
			END
		) AS `free_amount`,
		`b`.`channel_name` AS `in_channel_name`,
		`c`.`channel_name` AS `out_channel_name`,
		`g`.`a_custname` AS `card_type_name`,
		`h`.`free_name` AS `free_name`,
		`a`.`out_way` AS `out_way`,
		(
			CASE `a`.`out_way`
			WHEN 0 THEN
				'正常出场'
			WHEN 1 THEN
				'手工放行'
			ELSE
				'校正出场'
			END
		) AS `out_way_name`
	FROM
		(
			(
				(
					(
						`vw_park_car_out` `a`
						LEFT JOIN `park_channel_set` `b` ON (
							(
								`a`.`in_mach_no` = `b`.`mach_no`
							)
						)
					)
					LEFT JOIN `park_channel_set` `c` ON (
						(
							`a`.`out_mach_no` = `c`.`mach_no`
						)
					)
				)
				LEFT JOIN `park_account_type` `g` ON (
					(
						(`g`.`a_code` = 'CARD_TYPE')
						AND (
							`a`.`card_type` = `g`.`a_type`
						)
					)
				)
			)
			LEFT JOIN `park_free_type` `h` ON (
				(
					`a`.`free_type` = `h`.`free_type`
				)
			)
		);

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
-- ----------------------------
-- VIEW structure for vw_handover_record
-- ----------------------------
DROP VIEW
IF EXISTS vw_handover_record;

CREATE VIEW vw_handover_record AS SELECT
	`a`.`box_id` AS `box_id`,
	`b`.`box_name` AS `box_name`,
	`a`.`login_date` AS `login_date`,
	`a`.`relief_date` AS `relief_date`,
	`a`.`user_name` AS `user_name`,
	`a`.`relief_user_name` AS `relief_user_name`,
	`a`.`hand_gate` AS `hand_gate`,
	`a`.`temp_car_in` AS `temp_car_in`,
	`a`.`temp_car_out` AS `temp_car_out`,
	`a`.`free_charge` AS `free_charge`,
	`a`.`dis_sum` AS `dis_sum`,
	`a`.`dis_charge` AS `dis_charge`,
	`a`.`cash_charge` AS `cash_charge`,
	`a`.`wechat_charge` AS `wechat_charge`,
	`a`.`alipay_charge` AS `alipay_charge`,
	`a`.`unionpay_chagre` AS `unionpay_chagre`,
	`a`.`thirdpay_charge` AS `thirdpay_charge`,
	`a`.`account` AS `account`,
	(
		(
			(
				(
					`a`.`cash_charge` + `a`.`wechat_charge`
				) + `a`.`alipay_charge`
			) + `a`.`unionpay_chagre`
		) + `a`.`thirdpay_charge`
	) AS `total_charge`
FROM
	(
		`park_sum_user` `a`
		LEFT JOIN `park_local_set` `b` ON (
			(`a`.`box_id` = `b`.`box_id`)
		)
	);

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
-- ----------------------------
-- VIEW structure for vw_park_car_isuse
-- ----------------------------
DROP VIEW
IF EXISTS vw_park_car_isuse;

CREATE VIEW vw_park_car_isuse AS SELECT
	`a`.`id` AS `id`,
	`a`.`ykt_id` AS `ykt_id`,
	`a`.`card_id` AS `card_id`,
	`a`.`card_no` AS `card_no`,
	`a`.`car_no` AS `car_no`,
	`a`.`card_type` AS `card_type`,
	`a`.`car_no_type` AS `car_no_type`,
	`a`.`car_place` AS `car_place`,
	`a`.`car_color` AS `car_color`,
	`a`.`start_date` AS `start_date`,
	`a`.`end_date` AS `end_date`,
	`a`.`down_load` AS `down_load`,
	`a`.`is_load` AS `is_load`,
	`b`.`c_flag` AS `c_flag`,
	`b`.`p_id` AS `p_id`,
	`b`.`foregift` AS `foregift`,
	`b`.`balance_money` AS `balance_money`,
	`b`.`status` AS `status`,
	`b`.`sys_right` AS `sys_right`,
	`b`.`is_share` AS `is_share`,
	`b`.`place_num` AS `place_num`,
	`b`.`memo` AS `memo`,
	`a`.`create_date` AS `create_date`,
	`a`.`create_user_name` AS `create_user_name`,
	`a`.`modify_date` AS `modify_date`,
	`a`.`modify_user_name` AS `modify_user_name`,
	`a`.`is_use_type` AS `is_use_type`,
	`c`.`per_name` AS `per_name`,
	`c`.`per_id_code` AS `per_id_code`,
	`c`.`per_addr` AS `per_addr`,
	`c`.`wechat_no` AS `wechat_no`,
	`c`.`per_tel` AS `per_tel`,
	`c`.`land_line_num` AS `land_line_num`,
	`c`.`per_id_no` AS `per_id_no`,
	`c`.`sex` AS `sex`,
	`c`.`birthday` AS `birthday`,
	`c`.`per_nation` AS `per_nation`,
	`c`.`per_place` AS `per_place`,
	`c`.`per_zip_code` AS `per_zip_code`,
	`c`.`marry` AS `marry`,
	`c`.`per_email` AS `per_email`,
	`c`.`per_type` AS `per_type`,
	`c`.`enter_date` AS `enter_date`,
	`c`.`format_date` AS `format_date`,
	`c`.`edu_level` AS `edu_level`,
	`c`.`political_status` AS `political_status`,
	`c`.`positions` AS `positions`,
	`c`.`is_leave` AS `is_leave`,
	`c`.`leave_date` AS `leave_date`,
	`c`.`leave_cause` AS `leave_cause`
FROM
	(
		(
			`park_is_use` `a`
			LEFT JOIN `ykt_card_issue` `b` ON ((`a`.`ykt_id` = `b`.`id`))
		)
		LEFT JOIN `per_persons` `c` ON ((`b`.`p_id` = `c`.`pid`))
	);

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
-- ----------------------------
-- VIEW structure for vw_park_carcharge
-- ----------------------------
DROP VIEW
IF EXISTS vw_park_carcharge;

CREATE VIEW vw_park_carcharge AS SELECT
	`d`.`box_id` AS `box_id`,
	`a`.`card_id` AS `card_id`,
	`a`.`card_no` AS `card_no`,
	`a`.`out_car_no` AS `car_no`,
	`a`.`card_type` AS `card_type`,
	`a`.`in_time` AS `in_time`,
	`a`.`out_time` AS `out_time`,
	`a`.`out_user_name` AS `user_name`,
	`a`.`pay_charge` AS `pay_charge`,
	`a`.`account_charge` AS `account_charge`,
	(
		CASE
		WHEN (
			ifnull(`a`.`free_type`, 0) > 0
		) THEN
			0
		ELSE
			(
				`a`.`account_charge` - `a`.`pay_charge`
			)
		END
	) AS `dis_amount`,
	(
		CASE
		WHEN (
			ifnull(`a`.`free_type`, 0) > 0
		) THEN
			(
				`a`.`account_charge` - `a`.`pay_charge`
			)
		ELSE
			0
		END
	) AS `free_amount`,
	`b`.`a_custname` AS `card_type_name`,
	`c`.`free_name` AS `free_name`,
	'出口收费' AS `charge_type`,
	`a`.`in_pic` AS `in_pic`,
	`a`.`out_pic` AS `out_pic`,
	`a`.`pay_type` AS `pay_type`,
	(
		CASE ifnull(`a`.`pay_type`, 0)
		WHEN 0 THEN
			'现金支付'
		WHEN 1 THEN
			'微信支付'
		WHEN 2 THEN
			'支付宝支付'
		WHEN 3 THEN
			'银联闪付'
		WHEN 4 THEN
			'交通卡支付'
		WHEN 5 THEN
			'自助缴费机'
		ELSE
			'其它'
		END
	) AS `pay_type_name`,
	`a`.`order_num` AS `order_num`,
	ifnull(`a`.`balance_money`, 0) AS `balance_money`
FROM
	(
		(
			(
				`park_car_out` `a`
				LEFT JOIN `park_account_type` `b` ON (
					(
						`a`.`card_type` = `b`.`a_type`
					)
				)
			)
			LEFT JOIN `park_free_type` `c` ON (
				(
					`a`.`free_type` = `c`.`free_type`
				)
			)
		)
		LEFT JOIN `park_channel_set` `d` ON (
			(
				`a`.`out_mach_no` = `d`.`mach_no`
			)
		)
	)
UNION ALL
	SELECT
		`a`.`box_id` AS `box_id`,
		`a`.`card_id` AS `card_id`,
		`a`.`card_no` AS `card_no`,
		`a`.`car_no` AS `car_no`,
		`a`.`card_type` AS `card_type`,
		`a`.`in_time` AS `in_time`,
		`a`.`pay_charge_time` AS `out_time`,
		`a`.`pay_user_name` AS `user_name`,
		`a`.`pay_charge` AS `pay_charge`,
		`a`.`account_charge` AS `account_charge`,
		(
			CASE
			WHEN (
				ifnull(`a`.`free_type`, 0) > 0
			) THEN
				0
			ELSE
				(
					`a`.`account_charge` - `a`.`pay_charge`
				)
			END
		) AS `dis_amount`,
		(
			CASE
			WHEN (
				ifnull(`a`.`free_type`, 0) > 0
			) THEN
				(
					`a`.`account_charge` - `a`.`pay_charge`
				)
			ELSE
				0
			END
		) AS `free_amount`,
		`b`.`a_custname` AS `card_type_name`,
		`c`.`free_name` AS `free_name`,
		(
			CASE
			WHEN (
				ifnull(
					`a`.`over_time`,
					'2010-01-01'
				) <= '2010-01-01'
			) THEN
				'定点收费'
			ELSE
				'逾时收费'
			END
		) AS `charge_type`,
		`a`.`in_pic` AS `in_pic`,
		'' AS `out_pic`,
		`a`.`pay_type` AS `pay_type`,
		(
			CASE ifnull(`a`.`pay_type`, 0)
			WHEN 0 THEN
				'现金支付'
			WHEN 1 THEN
				'微信支付'
			WHEN 2 THEN
				'支付宝支付'
			WHEN 3 THEN
				'银联闪付'
			WHEN 4 THEN
				'交通卡支付'
			WHEN 5 THEN
				'自助缴费机'
			ELSE
				'其它'
			END
		) AS `pay_type_name`,
		`a`.`order_num` AS `order_num`,
		ifnull(`a`.`balance_money`, 0) AS `balance_money`
	FROM
		(
			(
				`park_central_charge` `a`
				LEFT JOIN `park_account_type` `b` ON (
					(
						`a`.`card_type` = `b`.`a_type`
					)
				)
			)
			LEFT JOIN `park_free_type` `c` ON (
				(
					`a`.`free_type` = `c`.`free_type`
				)
			)
		);

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
-- ----------------------------
-- VIEW structure for vw_park_dis_detail
-- ----------------------------
DROP VIEW
IF EXISTS vw_park_dis_detail;

CREATE VIEW vw_park_dis_detail AS SELECT
	`a`.`id` AS `id`,
	`a`.`out_type` AS `out_type`,
	`a`.`discount_id` AS `discount_id`,
	`a`.`card_id` AS `card_id`,
	`a`.`out_time` AS `out_time`,
	`a`.`discount_time` AS `discount_time`,
	`a`.`dis_amount` AS `dis_amount`,
	`a`.`dis_type` AS `dis_type`,
	`a`.`online_id` AS `online_id`,
	`c`.`in_time` AS `in_time`,
	`c`.`card_no` AS `card_no`,
	`c`.`in_user_name` AS `in_user_name`,
	`c`.`out_user_name` AS `out_user_name`,
	`b`.`eqid` AS `eqid`,
	`b`.`discount_name` AS `discount_name`,
	`b`.`memo` AS `memo`,
	(
		CASE `b`.`discount_type`
		WHEN 1 THEN
			'全免'
		WHEN 4 THEN
			'折扣率'
		WHEN 3 THEN
			'免小时'
		ELSE
			'免金额'
		END
	) AS `dis_type_name`,
	`c`.`account_charge` AS `account_charge`,
	`c`.`pay_charge` AS `pay_charge`,
	`d`.`eq_name` AS `eq_name`,
	`c`.`out_car_no` AS `car_no`,
	(
		CASE `a`.`dis_type`
		WHEN 0 THEN
			'线下打折'
		ELSE
			'线上打折'
		END
	) AS `type_name`
FROM
	(
		(
			(
				`park_dis_detail` `a`
				LEFT JOIN `park_dis_info` `b` ON (
					(
						`a`.`discount_id` = `b`.`discount_id`
					)
				)
			)
			LEFT JOIN `park_equipments` `d` ON ((`b`.`eqid` = `d`.`eq_id`))
		)
		LEFT JOIN `park_car_out` `c` ON (
			(
				(
					`a`.`card_id` = `c`.`card_id`
				)
				AND (
					`a`.`out_time` = `c`.`out_time`
				)
			)
		)
	)
WHERE
	(`a`.`out_type` = 0)
UNION
	SELECT
		`a`.`id` AS `id`,
		`a`.`out_type` AS `out_type`,
		`a`.`discount_id` AS `discount_id`,
		`a`.`card_id` AS `card_id`,
		`a`.`out_time` AS `out_time`,
		`a`.`discount_time` AS `discount_time`,
		`a`.`dis_amount` AS `dis_amount`,
		`a`.`dis_type` AS `dis_type`,
		`a`.`online_id` AS `online_id`,
		`c`.`in_time` AS `in_time`,
		`c`.`card_no` AS `card_no`,
		`c`.`pay_user_name` AS `pay_user_name`,
		`c`.`pay_user_name` AS `pay_user_name`,
		`b`.`eqid` AS `eqid`,
		`b`.`discount_name` AS `discount_name`,
		`b`.`memo` AS `memo`,
		(
			CASE `b`.`discount_type`
			WHEN 1 THEN
				'全免'
			WHEN 4 THEN
				'折扣率'
			WHEN 3 THEN
				'免小时'
			ELSE
				'免金额'
			END
		) AS `dis_type_name`,
		`c`.`account_charge` AS `account_charge`,
		`c`.`pay_charge` AS `pay_charge`,
		`d`.`eq_name` AS `eq_name`,
		`c`.`car_no` AS `car_no`,
		(
			CASE `a`.`dis_type`
			WHEN 0 THEN
				'线下打折'
			ELSE
				'线上打折'
			END
		) AS `type_name`
	FROM
		(
			(
				(
					`park_dis_detail` `a`
					LEFT JOIN `park_dis_info` `b` ON (
						(
							`a`.`discount_id` = `b`.`discount_id`
						)
					)
				)
				LEFT JOIN `park_equipments` `d` ON ((`b`.`eqid` = `d`.`eq_id`))
			)
			LEFT JOIN `park_central_charge` `c` ON (
				(
					(
						`a`.`card_id` = `c`.`card_id`
					)
					AND (
						`a`.`out_time` = `c`.`pay_charge_time`
					)
				)
			)
		)
	WHERE
		(`a`.`out_type` = 1);

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
-- ----------------------------
-- VIEW structure for vw_park_free
-- ----------------------------
DROP VIEW IF EXISTS vw_park_free;

CREATE VIEW vw_park_free AS SELECT
	`a`.`card_type` AS `card_type`,
	`a`.`card_id` AS `card_id`,
	`a`.`card_no` AS `card_no`,
	`a`.`card_type_name` AS `card_type_name`,
	`a`.`out_car_no` AS `car_no`,
	`a`.`free_amount` AS `free_amount`,
	`a`.`in_time` AS `in_time`,
	`a`.`out_time` AS `pay_charge_time`,
	`a`.`out_user_name` AS `pay_user_name`,
	ifnull(`a`.`free_name`, `b`.`memo`) AS `free_name`,
	`a`.`free_type` AS `free_type`,
	`a`.`credentials_pic` AS `credentials_pic`
FROM
	(
		`vw_park_car_out` `a`
		LEFT JOIN `park_central_charge` `b` ON (
			(
				(
					`a`.`card_id` = `b`.`card_id`
				)
				AND (
					`a`.`in_time` = `b`.`in_time`
				)
			)
		)
	)
WHERE
	(`a`.`card_type` = 40)
UNION
	SELECT
		`a`.`card_type` AS `card_type`,
		`a`.`card_id` AS `card_id`,
		`a`.`card_no` AS `card_no`,
		`b`.`a_custname` AS `card_type_name`,
		`a`.`car_no` AS `car_no`,
		`a`.`dis_amount` AS `free_amount`,
		`a`.`in_time` AS `in_time`,
		`a`.`pay_charge_time` AS `pay_charge_time`,
		`a`.`pay_user_name` AS `pay_user_name`,
		`a`.`memo` AS `free_name`,
		`a`.`free_type` AS `free_type`,
		`a`.`credentials_pic` AS `credentials_pic`
	FROM
		(
			`park_central_charge` `a`
			LEFT JOIN `park_account_type` `b` ON (
				(
					(`b`.`a_code` = 'CARD_TYPE')
					AND (
						`a`.`card_type` = `b`.`a_type`
					)
				)
			)
		)
	WHERE
		(`a`.`card_type` = 40)