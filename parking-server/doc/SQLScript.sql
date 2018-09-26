-- ============================================= 
-- 脚本名称：CS一卡通执行脚本
-- 建立日期：2015-08-22 
-- 编 写 者：老王
-- 版    本: V1.0  
-- ============================================= 



-- |**************************************************************************************|
-- |*******************************| 创建表(begin) |**************************************|
-- |**************************************************************************************|

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2016-07-07>  
-- 功能说明： <云平台-锁车配置表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Yun_AutoLockingSet') IS NOT NULL
	PRINT '该表已存在'--DROP TABLE dbo.Yun_AutoLockingSet
ELSE
begin
CREATE TABLE [dbo].[Yun_AutoLockingSet](
	[CarNo] [nvarchar](10) NOT NULL PRIMARY KEY,	--车牌号
	[LockType] [tinyint] NOT NULL,					--锁车类型 0-自动锁车；1-取消自动锁车
	[LockTimeSpan]  [int] NULL,						--锁车间隔时长 如果车主通过远程解锁动作，如果超过该设置时间出场再次验证：出场时间-上次解锁时间>锁车间隔时长,不允许放行，需要车主再次发起解锁操操作
	[LastUnLockTime] [datetime] NOT NULL			--上次解锁时间
)
end
GO

IF NOT EXISTS( select * from syscolumns where id=object_id('Yun_AutoLockingSet') and name='CardNo')
BEGIN
ALTER TABLE  Yun_AutoLockingSet ADD   CardNo VARCHAR(50)
END
GO

IF NOT EXISTS( select * from syscolumns where id=object_id('Yun_AutoLockingSet') and name='CardID')
BEGIN
ALTER TABLE  Yun_AutoLockingSet ADD   CardID VARCHAR(50)
END
Go


-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2016-03-28>  
-- 功能说明： <指纹控制器>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID('Door_Fingerprints','U') is not null
	PRINT '该表已存在'--DROP TABLE Door_Fingerprints
else
begin
CREATE TABLE  Door_Fingerprints
(
	id  INT IDENTITY(1,1) PRIMARY KEY,
	TerminalID int not null,						 --指纹设备编号
	TerminalName varchar(20) not null,				 --指纹设备名称
	SerialNo varchar(20) not null,					 --指纹设备序列号
	TerMinalIP varchar(20) not null					 --指纹设备IP
)
end
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2016-03-28>  
-- 功能说明： <指纹信息>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID('Door_FingerprintInfo','U') is not null
	PRINT '该表已存在'--DROP TABLE Door_FingerprintInfo
else
begin
CREATE TABLE  Door_FingerprintInfo
(
	id int identity(1,1) primary key,
	YktID int not null,								 --卡信息ID
	FingerprintBuff Image	 not null,				 --指纹数据
	filepath varchar(100) not null
)
end
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <BS对接表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID('DC_UserDoor','U') is not null
	PRINT '该表已存在'--DROP TABLE DC_UserDoor
else
begin
--用户门禁关联关系
CREATE TABLE  DC_UserDoor
(
	id  INT IDENTITY(1,1) PRIMARY KEY,
	ContID VARCHAR(50) not null,					 --控制器ID
	DoorNo   INT not null,							 --门序号
	UserID INT not null,							 --物业云用户ID
	[STATUS] INT not null default(0),				 --0  未获取 1  已获取
	OpenDoorTime datetime not null default(getdate())--开门时间
)
end
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <系统登录用户表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID('Sys_LoginUser','U') is not null
	PRINT '该表已存在'--DROP TABLE Sys_LoginUser
ELSE
begin
	CREATE TABLE [dbo].[Sys_LoginUser](
		[LoginName] [nvarchar](20) null,		--登录名/工号
		[UserName] [nvarchar](20) NULL,			--名称
		[pssword] [varchar](50) NULL,			--密码
		[OperaCarNo] [varchar](10) NULL,		--操作卡卡号
		[right] [nvarchar](20) NULL,			--权限序列(1一卡通/2停车场/3门禁/4消费/5考勤/6巡更/7访客
		[Popedomlist] [varchar](2000) NULL,		--菜单权限序列（一卡通300个，子系统50个）
		[Level] [tinyint] NULL,					--操作员级别(0操作组1财务组2管理组100系统管理员)
		[LoginType] [tinyint] NULL,				--登录方式（0、密码；1、刷卡登录；2、密码+刷卡）
		[LoadType] [tinyint] NULL,				--进入方式（1监控界面）
		[IsStop] [tinyint] NULL,				--是否停用
		[memo] [nvarchar](50) NULL,				--备注
		[UserType] [int] NULL					--用户类型
	) ON [PRIMARY]
end
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <系统权限表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID('Sys_Function','U') is not null
	PRINT '该表已存在'--DROP TABLE Sys_Function
ELSE
begin
	CREATE TABLE [dbo].[Sys_Function](
		[FunCode] [varchar](10) NOT NULL,					--权限编号(一级01、02,二级0101、0102、0001、0002)
		[FunName] [nvarchar](30) NOT NULL,					--功能名称
		[FunType] [tinyint] NULL,							--权限类型(0功能1权限)
		[FunNo] [int] NOT NULL,								--权限值78
		[FunSon] [tinyint] NULL,							--是否有子权限（0代表一个权限，1代表4个权限）
		[FunModName] [varchar](50) NOT NULL,				--模块名称方便程序调用(URL)
		[FunRight] [varchar](8) default('11111111') NOT NULL	--功能模块权限：1公共/2停車場/3門禁/4消費/5考勤/6巡更/7訪客
	) ON [PRIMARY]
end
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <CS一卡通卡管理中心部门信息表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID('Per_Org','U') is not null
	DROP TABLE Per_Org
go
CREATE TABLE [dbo].[Per_Org](
	[DeptID] [nvarchar](10) NULL,
	[DeptName] [nvarchar](20) NULL,
	[TopDeptID] [nvarchar](10) NULL
) ON [PRIMARY]

GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2017-04-05>  
-- 功能说明： <CS一卡通卡管理中心参数设置表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID('Sys_Parameters','U') is not null
	PRINT '该表已存在'--DROP TABLE Sys_Parameters
ELSE
CREATE TABLE [dbo].[Sys_Parameters](
	[ParameterID] [int] NOT NULL,               -- 编号
	[ParameterName] [nvarchar](100) NULL,       --字段名称
	[ParameterValue] [varchar](5000) NULL,      --
	[TypeID] [int] NULL,
	[IsEdit] [bit] NOT NULL,
	[Remark] [nvarchar](1000) NULL,
	[CreateDate] [datetime] NULL,
	[ModifyDate] [datetime] NULL,
	[ModifyUserName] [nvarchar](20) NULL,
 CONSTRAINT [PK_Sys_Parameters] PRIMARY KEY CLUSTERED 
(
	[ParameterID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-用户组权限>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID('Ykt_PowerPlan','U') is not null
	PRINT '该表已存在'--DROP TABLE Ykt_PowerPlan
ELSE
begin
	CREATE TABLE [dbo].[Ykt_PowerPlan](
		[PlanName] [varchar](20) NOT NULL,					--账户名称
		[right] [varchar](10) NULL,							--权限序列(停车场/门禁)
		[Foregift] [money] NULL,							--押金
		[Plan1] [tinyint] NULL,								--车场级别
		[Plan2] [tinyint] NULL,								--门禁级别
		[Plan3] [tinyint] NULL,								--消费级别
		[Plan4] [tinyint] NULL,								--
		[Plan5] [tinyint] NULL,								--
		[Plan6] [tinyint] NULL,								--
		[Plan7] [tinyint] NULL,								--
		[Remark] [nvarchar](50) NULL,						--备注
		[UserDate] [datetime] NULL,							--操作时间
		[UserName] [nvarchar](20) NULL						--操作员
	) ON [PRIMARY]
end
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <系统操作日志>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID('Ykt_Login','U') is not null
	PRINT '该表已存在'--DROP TABLE Ykt_Login
ELSE
begin
	CREATE TABLE [dbo].[Ykt_Login](
		[ID] [int] IDENTITY(1,1) NOT NULL,				--
		[LogDate] [datetime] NULL,						--操作日期
		[UserName] [nvarchar](20) NULL,					--操作员编号
		[Computer] [nvarchar](200) NULL,				--计算机名
		[LogObj] [nvarchar](200) NULL,					--操作对象
		[LogMod] [nvarchar](200) NULL,					--操作方法
		[LogCon] [nvarchar](500) NULL					--操作内容
	) ON [PRIMARY]
end	
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <卡充值信息记录>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID('Ykt_CardRsMoney','U') is not null
	PRINT '该表已存在'--DROP TABLE Ykt_CardRsMoney
ELSE
begin
	CREATE TABLE [dbo].[Ykt_CardRsMoney](
		[YKTID] [int] NOT NULL,					--开户ID号
		[SType] [tinykint] NULL,					--操作类型（0卡发行1卡延期2挂失3解挂4补发5退款6销户）
		[FrontDate] [datetime] NULL,		    --前结束日期（延期用）
		[NStartDate] [datetime] NULL,		    --新起始日期（延期用）
		[NEndDate] [datetime] NULL,		        --新终止日期（延期用）
		[BalanceMoney] [money] NULL,			--发生金额
		[PayType] [tinyint] NULL,				--付款方式(0现金1银联闪付2微信3支付宝)
		[Foregift] [money] NULL,				--押金
		[BeforeMoney] [money] NULL,				--发生前余额（储值卡用）
		[UserDate] [datetime] NULL,				--操作时间
		[UserName] [nvarchar](20) NULL,			--操作员
		[Remark] [nvarchar](50) NULL,			--备注
		[YRecordNumber] [varchar](100) NULL,	--云交易记录编号
		[WeChatNumber] [varchar](100) NULL,		--微信交易编号
		[CardBalance] [money] NULL,				--消费卡余额
		[PresentMoney] [money] NULL,				--赠送金额
		[ThirdNo] [varchar](50) NULL --第三方帐号
	) ON [PRIMARY]
end	
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <卡延期记录表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID('YKT_CardDelyInfo','U') is not null
	PRINT '该表已存在'--DROP TABLE YKT_CardDelyInfo
ELSE
begin
	CREATE TABLE [dbo].[YKT_CardDelyInfo](
	[Id] [int] IDENTITY(1,1) NOT NULL,			--延期ID号
	[yktid] [int] NOT NULL,						--一卡通ID
	[orgenddate] [datetime] NULL,				--延期前的有效结束日期
	[newenddate] [datetime] NULL,				--延期后的有效结束日期
	[stype] [tinyint] NULL,						--延期类型
	[userdate] [datetime] NULL,					--操作日期
	[username] [nvarchar](30) NULL,				--操作员
	[remark] [nvarchar](50) NULL)				--备注
end	
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场信息表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID('Ykt_CardIssuePark','U') is not null
	PRINT '该表已存在'-- Ykt_CardIssuePark
ELSE
begin
	CREATE TABLE [dbo].[Ykt_CardIssuePark](
		[YKTID] [int] NOT NULL,							--开户ID号
		[CardType] [tinyint] NOT NULL,					--卡类型
		[sType] [tinyint] NOT NULL,						--月租类型
		[CarNO] [nvarchar](10) NULL,					--车牌号
		[CarNoType] [tinyint] NULL,						--车牌类型
		[CarPlace] [nvarchar](10) NULL,					--车位
		[CarColour] [nvarchar](10) NULL,				--车颜色
		[StartDate] [datetime] NULL,					--有效起始日期
		[EndDate] [datetime] NULL,						--有效终止日期
		[BalanceMoney] [money] NULL,					--余额
		[PlanID] [tinyint] NOT NULL,					--权限级别
		[UserDate] [datetime] NULL,						--车场发行日期
		[UserName] [nvarchar](20) NULL,					--操作员
		[Remark] [nvarchar](50) NULL,					--车场备注
		[DownLoad] [varchar](255) NULL					--下载标记(控制器)
	) ON [PRIMARY]
end	
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁信息表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID('Ykt_CardIssueDoor','U') is not null
	PRINT '该表已存在'--drop table Ykt_CardIssueDoor
ELSE
begin
	CREATE TABLE [dbo].[Ykt_CardIssueDoor](
		[YKTID] [int] NOT NULL,								--开户ID号
		[CardType] [tinyint] NULL,							--卡类型
		[PlanID1] [tinyint] NULL,							--时段1
		[PlanID2] [tinyint] NULL,							--时段2
		[PlanID3] [tinyint] NULL,							--时段3
		[PlanID4] [tinyint] NULL,							--时段4
		[StartDate] [datetime] NULL,						--有效起始日期
		[EndDate] [datetime] NULL,							--有效终止日期
		[CardPwd] [varchar](10) NULL,						--用户密码
		[GradeID] [tinyint] NOT NULL,						--级别ID（32个级别）
		[UserDate] [datetime] NULL,							--发行日期
		[UserName] [nvarchar](20) NULL,						--操作员
		[Remark] [nvarchar](100) NULL,						--备注
		[DownLoad] [varchar](8000) NULL,					--下载标记(控制器)
		[FDownLoad] [varchar](8000) NULL,					--下载标记(指纹机)
		PlanID5 tinyint null,								--时段5
		PlanID6 tinyint null								--时段6
	) ON [PRIMARY]
end
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <卡信息表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID('Ykt_CardIssue','U') is not null
	PRINT '该表已存在'--drop table Ykt_CardIssue
ELSE
begin
	CREATE TABLE [dbo].[Ykt_CardIssue](
		[ID] [int] IDENTITY(1,1) NOT NULL,					--
		[YktID] [int] NOT NULL,								--开户ID号（唯一）
		[CardID] [varchar](20) NOT NULL,					--卡ID号(7Byte)
		[CFlag] [tinyint] NULL,								--卡介质(0为IC，1为ID，2IC做ID，3.身份证，4.纯车牌，5.ETC，6.CPU)
		[CardNO] [varchar](10) NULL,						--卡编号
		[PID] [int] NOT NULL,								--人事ID
		[PerName] [nvarchar](20) NULL,						--人事姓名
		[Foregift] [money] NULL,							--押金
		[States] [tinyint] NULL,							--卡状态（0正常、1正在挂失、2已挂失、3正在解挂、4已补卡、5挂失退款、6已销户
		[SysRight] [varchar](20) NULL,						--系统权限(车场；门禁；消费；考勤；巡更；访客；水控；）
		[Remark] [nvarchar](50) NULL,						--卡备注
		[UserDate] [datetime] NULL,							--发行日期
		[UserName] [nvarchar](20) NULL						--操作员
	) ON [PRIMARY]
end	
GO


-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <免费类型表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID('ParK_FreeType','U') is not null
	PRINT '该表已存在'-- ParK_FreeType
ELSE
begin
	CREATE TABLE [dbo].[ParK_FreeType](
		[FreeType] [tinyint] NULL,				--免费类型
		[FreeName] [nvarchar](20) NULL,			--免费类型名称
		[Memo] [nvarchar](50) NULL				--备注
	) ON [PRIMARY]
end
GO
-- ============================================= 
-- 程序编写： <谭飞> 
-- 建立日期： <2015-9-23>  
-- 功能说明： <车场-家庭组>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID('Park_FamilyGroup','U') is not null
	PRINT '该表已存在'-- drop table Park_FamilyGroup
else	
CREATE TABLE [dbo].[Park_FamilyGroup](
	[ID] [int] IDENTITY (1, 1) NOT NULL ,
	[PlaceNo] [nvarchar] (50) Not NULL ,	
	[PType] [tinyint] NULL ,      --0，按临时车入场  1禁止入场			
	[PlaceNum] [int]  NULL ,			 
	[PlaceIn] [int]  NULL ,				
	[StartDate] [Datetime]  NULL ,				
	[EndDate] [Datetime]  NULL ,				
	[sMemo] [nvarchar](100) NULL			
 CONSTRAINT [PK_Park_FamilyGroup] PRIMARY KEY CLUSTERED 
(
	[PlaceNo] ASC
) ON [PRIMARY]
)
GO


-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2017-02-11>  
-- 功能说明： <车场家庭组收费临时表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID('Park_FamilyGroupCharge','U') is not null
	PRINT '该表已存在'-- Park_FamilyGroupCharge
ELSE
begin
	CREATE TABLE [dbo].[Park_FamilyGroupCharge](
	    [ID] [int] IDENTITY(1,1) NOT NULL,				--编号
	    [InID] [int] NOT NULL,						--入场ID
		[YktID] [int] NOT NULL,						--开户ID号
		[InTime] [datetime] NULL,					--入场时间
		[PayCharge] [money] NULL,					--收费金额
		[CarNO] [nvarchar](10) NULL,			    --车牌号
		[InOut] [tinyint] default(0)  null          --是否出场
	) ON [PRIMARY]
end
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场岗亭表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID('Park_ControlSet','U') is not null
	PRINT '该表已存在'-- Park_ControlSet
ELSE
begin
	CREATE TABLE [dbo].[Park_ControlSet](
		[BoxID] [tinyint] NOT NULL,				--岗亭编号(1-64)
		[machNo] [tinyint] NOT NULL,			--控制器机号(1-255)
		[ContIP] [varchar](15) NOT NULL,		--控制器IP
		[ContName] [nvarchar](20) NOT NULL,		--控制器名称
		[DSn] [nvarchar](50) NULL,				--设备序列号
		[InOut] [tinyint] NULL,					--出入类型（0标准入口，1标准出口，2标准嵌套入口，3标准嵌套出口、4半嵌套入口，5半嵌套出口、6单通道）
		[StrobeNo] [tinyint] NULL,				--开闸机号
		[CardMach] [tinyint] NULL,				--卡机(0无、1有)
		[GateType] [tinyint] NULL,				--通道类型（0综合信道 1固定车信道，2临时卡通道）
		[smallParkNo] [tinyint] NULL,			--小车场编号
		[StrobeSet] [varchar](8) NULL,			--开闸设定(0自动，1确定)
		[videoList] [varchar](8) NULL,			--视讯序列
		[Enclosure] [varchar](8) NULL,			--附件序列
		[GateClose] [tinyint] NULL,				--关到位
		[HaveCar] [tinyint] NULL,				--有车读卡
		[NoMoney] [tinyint] NULL,				--禁止收费
		[SortID] [tinyint] NOT NULL,			--控制器排序
		[RightPlan] [varchar](8) NULL,			--有效级别
		[SoundValue] [tinyint] NULL,			--音量大小(0-100)
		[IsTmp] [tinyint] NULL,					--是否临时卡计费器
		[CardWorkModel] [varchar](8) NUll       --增加卡片工作模式
	) ON [PRIMARY]
end
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场级别表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID('Park_ControlPlan','U') is not null
	PRINT '该表已存在'-- Park_ControlPlan
ELSE
begin
	CREATE TABLE [dbo].[Park_ControlPlan](
		[PlanID] [tinyint] NOT NULL,					--级别ID
		[PlanName] [nvarchar](20) NULL,					--级别名称
		[RightList] [varchar](255) NULL					--控制器权限
	) ON [PRIMARY]
end	
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场中央收费表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID('Park_CentralCharge','U') is not null
	PRINT '该表已存在'-- Park_CentralCharge
ELSE
begin
	CREATE TABLE [dbo].[Park_CentralCharge](
	    [ID] [int] IDENTITY(1,1) NOT NULL,				--编号
		[BoxID] [tinyint] NOT NULL,					--收费岗亭编号
		[IsOut] [tinyint] NULL,						--是否出场
		[YktID] [int] NOT NULL,						--开户ID号
		[CardID] [varchar](20) NULL,				--卡ID（7Byte）
		[CFlag] [tinyint] NULL,						--卡介质(0为IC，1为ID，2IC做ID，3)
		[CardType] [tinyint] NOT NULL,				--卡类型
		[InTime] [datetime] NULL,					--入场时间
		[OVerTimeS] [datetime] NULL,				--逾时开始时间
		[PayCharge] [money] NULL,					--收费金额
		[PayChargeTime] [datetime] NULL,			--收费时间
		[UserName] [nvarchar](20) NULL,				--收费操作员
		[AccountCharge] [money] NULL,				--应收金额
		[CardNo] [varchar](10) NULL,				--卡编号
		[CarNO] [nvarchar](10) NULL,					--车牌号
		[FreeType] [tinyint]  NULL,					--免费类型
		[ZjPic] [nvarchar](255) NULL,					--证件图片路径
		[DiscountNo] [tinyint] NULL,					--打折机号
		[TypeID] [tinyint] NULL,						--模式ID
		[DiscountTime] [datetime] NULL,					--折扣时间
		[DisAmount] [money] NULL,						--折扣金额
		[PayType] [tinyint]  NULL,	                    --支付类型 0，现金支付，1微信支付,2，支付宝支付,3,银联闪付，4，交通卡支付，5,自助缴费机,6，其它
		[OrderNum] [nvarchar](100) NULL,                --交易订单号
		[UnusualMemo] [nvarchar](20) NULL				--异常原因（1车闸故障2卡遗失等等）
	) ON [PRIMARY]
end
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场出场统计表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID('Park_CarOutRpt','U') is not null
	PRINT '该表已存在'-- Park_CarOutRpt
ELSE
begin
	CREATE TABLE [dbo].[Park_CarOutRpt](
		[Username] [varchar](20) NULL,			--操作员
		[ImonthA] [int] NULL,					--月租卡A出场次数
		[ImonthB] [int] NULL,					--月租卡B出场次数
		[ImonthC] [int] NULL,					--月租卡C出场次数
		[ImonthD] [int] NULL,					--月租卡D出场次数
		[ImonthE] [int] NULL,					--预留
		[ImonthF] [int] NULL,					--预留
		[ImonthAvg] [int] NULL,					--月卡平均次数
		[IsumMonth] [int] NULL,					--月卡收费总次数
		[IFreeA] [int] NULL,					--免费卡A出场次数
		[IFreeB] [int] NULL,					--免费卡B出场次数
		[IVipCar] [int] NULL,					--VIP出场次数
		[IsumFree] [int] NULL,					--免费车出场次数
		[ITempA] [int] NULL,					--临免卡A出场次数
		[ITempB] [int] NULL,					--临免卡B出场次数
		[ITempC] [int] NULL,					--临免卡C出场次数
		[ITempD] [int] NULL,					--临免卡D出场次数
		[ISumTemp] [int] NULL,					--临面卡总出场次数
		[Imoney] [money] NULL,					--储值卡总金额
		[IMoneyA] [int] NULL,					--储值卡A出场次数
		[IMoneyB] [int] NULL,					--储值卡B出场次数
		[IMoneyC] [int] NULL,					--储值卡C出场次数
		[IMoneyD] [int] NULL,					--储值卡D出场次数
		[ISumMoney] [int] NULL,					--储值卡总出场次数
		[IMoneySum] [money] NULL,				--收费总金额
		[ITempFree] [int] NULL,					--临免卡总出场次数
		[IFreeCar] [int] NULL,					--免费卡总出场次数
		[ZSumCar] [int] NULL,					--总出场次数
		[ZSumMoney] [money] NULL,				--总收费金额
		[TUnOut] [money] NULL,					--定点收费金额
		[TOver] [money] NULL,					--超时收费金额
		[ZDisM] [money] NULL,					--总折扣金额
		[Preferential] [money] NULL				--总免费金额
	) ON [PRIMARY]
end
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场收费表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID('Park_TmpDaysMoneyC1','U') is not null
	PRINT '该表已存在'-- Park_TmpDaysMoneyC1
ELSE
begin
	CREATE TABLE [dbo].[Park_TmpDaysMoneyC1](
		[UserName] [nvarchar](20) NULL,			--操作员
		[CheckName] [nvarchar](20) NULL,		--审核员
		[InDate] [datetime] NULL,				--入场时间
		[OutDate] [datetime] NULL,				--收费时间
		[TA] [money] NULL,						--A类卡收费金额
		[TB] [money] NULL,						--B类卡收费金额
		[TC] [money] NULL,						--C类卡收费金额
		[TD] [money] NULL,						--D类卡收费金额
		[TF] [money] NULL,						--免费金额
		[Total] [money] NULL					--收费总金额
	) ON [PRIMARY]
end
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场出场表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID('Park_CarOut','U') is not null
BEGIN
	PRINT '该表已存在'-- Park_CarOut
END
ELSE
begin
	CREATE TABLE [dbo].[Park_CarOut](
		[ID] [int] IDENTITY(1,1) NOT NULL,				--编号
		[CarEventNo][int]  NULL,                        --车场事件序号
		[InID][int]  NULL,                              --入场ID
		[InMachNo] [tinyint] NOT NULL,					--入口机号
		[OutMachNo] [tinyint] NOT NULL,					--出口机号
		[YktID] [int] NOT NULL,							--开户ID号
		[CardID] [varchar](20) NULL,					--卡ID（7Byte）
		[CFlag] [tinyint] NULL,							--卡介质(0为IC，1为ID，2IC做ID，3)
		[CardNo] [varchar](10) NULL,					--卡编号
		[EmpName] [nvarchar](20) NULL,					--车主名称
		[CarNO] [nvarchar](10) NULL,					--车牌号
		[CarNoType] [tinyint] NULL,						--车牌类型
		[CardType] [tinyint] NOT NULL,					--卡类型
		[FreeType] [tinyint] NOT NULL,					--免费类型
		[InTime] [datetime] NULL,						--入场时间
		[InPic] [nvarchar](255) NULL,					--图片路径
		[InUserName] [varchar](20) NULL,				--入场操作员
		[CentralTime] [datetime] NULL,					--定点收费时间
		[OutTime] [datetime] NULL,						--出场时间
		[OutPic] [nvarchar](255) NULL,					--图片路径
		[OutUserName] [nvarchar](20) NULL,				--收费操作员
		[ZjPic] [nvarchar](255) NULL,					--证件图片路径
		[PayType] [tinyint] NULL,						--支付类型 0，现金支付，1微信支付,2，支付宝支付,3,银联闪付，4，交通卡支付，5,自助缴费机,6，其它
		[PayCharge] [money] NULL,						--收费金额
		[BalanceMoney] [money] NULL,					--卡上金额
		[DiscountNo] [tinyint] NULL,					--打折机号
		[TypeID] [tinyint] NULL,						--模式ID
		[DiscountTime] [datetime] NULL,					--折扣时间
		[DisAmount] [money] NULL,						--折扣金额
		[AccountCharge] [money] NULL,					--应收金额
		[IsOut] [tinyint] NULL,							--是否出场（中央收费时为0，出场后更新为1）
		[OrderNum] [nvarchar](100) NULL,                --交易订单号
		[UnusualMemo] [nvarchar](20) NULL,				--异常原因（1车闸故障2卡遗失等等）
		[OutWay] [tinyint] default(0) not null			--出场方式 0,表示正常出场，1，手工放行，2，异常放行 3,扫码出场 4,脱机记录，5，相机异常记录 6,异常开闸
	) ON [PRIMARY]
end
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2017-03-15>  
-- 功能说明： <车辆通行事件表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Park_PassEvent','U') is not null
	PRINT '该表已存在'-- Park_PassEvent
ELSE
	CREATE TABLE [dbo].[Park_PassEvent](
		[MachNo] [tinyint] NOT NULL,					--控制器机号
		[Event_ID] [tinyint] NOT NULL,					--事件类型   
		[Serial_Number] [int] NULL,				        --流水号
		[Event_Num] [int] NULL,					        --车辆事件序号
		[Pass_Time] [datetime] NULL,					--通过时间
		[InOutID] [int] NULL,					        --出入记录ID
		[Type] [tinyint] NULL,							--类型 0表入场  1表出场
		[TriggerType] [tinyint] NULL,                        --触发类型
		[FontCarNo1][varchar](10) NULL,                 --前车牌1
		[FontCarNo2][varchar](10) NULL,                 --前车牌2
		[BackCarNo][varchar](10) NULL,                  --后车牌
		[MediumType][tinyint] NULL,                     --介质类型 00-无，01-IC卡，02-IC转ID卡，03-ID卡，04-ETC，05-CPU，06-纸票，07-身份证UID
		[MediumData][varchar](20) NULL,                 --附加介质数据 十六进制，右对齐
		[ValidTime][varchar](20) NULL,                  --有效期限    
		[ChargeableMoney][int] NULL,                    --应收金额 单位为分
		[RealChargeMoney][int] NULL,                    --实收金额 单位为分
		[RemainMoney][int] NULL,                        --余额 单位为分
		[DiscountNumber][int] NULL,                     --打折机号
		[DiscountID][int] NULL,                         --折扣ID
		[WorkingModel][int] NULL,                       --工作模式 00-自动，01-在线，02-受控
		[BeforeCorrectCarNo][varchar](10) NULL,          --校正前车牌
		[ParkTime] [int] NULL,                           --停车时长
	    [InTime] [datetime] NULL,                        --入场时间
	    [DiscountTime] [datetime] NULL,                  --打折时间
	    [PayTime] [datetime] NULL                        --收费时间
	) ON [PRIMARY]
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场出场放弃记录表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID('Abandon_record','U') is not null
BEGIN
	PRINT '该表已存在'-- Abandon_record
	
END
ELSE
begin
	CREATE TABLE [dbo].[Abandon_record](
		[ID] [int] IDENTITY(1,1) NOT NULL,				--编号
		[InMachNo] [tinyint] NOT NULL,					--入口机号
		[OutMachNo] [tinyint] NOT NULL,					--出口机号
		[YktID] [int] NOT NULL,							--开户ID号
		[OutID] [int]  NULL,							--关联遥控开闸记录的ID 
		[CardID] [varchar](20) NULL,					--卡ID（7Byte）
		[CFlag] [tinyint] NULL,							--卡介质(0为IC，1为ID，2IC做ID，3)
		[CardNo] [varchar](10) NULL,					--卡编号
		[EmpName] [nvarchar](20) NULL,					--车主名称
		[CarNO] [nvarchar](10) NULL,					--车牌号
		[CarNoType] [tinyint] NULL,						--车牌类型
		[CardType] [tinyint] NOT NULL,					--卡类型
		[FreeType] [tinyint] NOT NULL,					--免费类型
		[InTime] [datetime] NULL,						--入场时间
		[InPic] [nvarchar](255) NULL,					--图片路径
		[InUserName] [varchar](20) NULL,				--入场操作员
		[CentralTime] [datetime] NULL,					--定点收费时间
		[OutTime] [datetime] NULL,						--出场时间
		[OutPic] [nvarchar](255) NULL,					--图片路径
		[OutUserName] [nvarchar](20) NULL,				--收费操作员
		[ZjPic] [nvarchar](255) NULL,					--证件图片路径
		[PayType] [tinyint] NULL,						--支付类型 0，现金支付，1微信支付,2，支付宝支付,3,银联闪付，4，交通卡支付，5,自助缴费机,6，其它
		[PayCharge] [money] NULL,						--收费金额
		[BalanceMoney] [money] NULL,					--卡上金额
		[DiscountNo] [tinyint] NULL,					--打折机号
		[TypeID] [tinyint] NULL,						--模式ID
		[DiscountTime] [datetime] NULL,					--折扣时间
		[DisAmount] [money] NULL,						--折扣金额
		[AccountCharge] [money] NULL,					--应收金额
		[IsOut] [tinyint] NULL,							--是否出场（中央收费时为0，出场后更新为1）
		[OrderNum] [nvarchar](100) NULL,                --交易订单号
		[UnusualMemo] [nvarchar](20) NULL,				--异常原因（1车闸故障2卡遗失等等）
		[OutWay] [tinyint] default(0) not null			--出场方式 0,表示正常出场，1，手工放行，2，异常放行
	) ON [PRIMARY]
end
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场车牌收费信息表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID('Park_CarNoInfo','U') is not null
	PRINT '该表已存在'-- Park_CarNoInfo
ELSE
begin
	CREATE TABLE [dbo].[Park_CarNoInfo](
		[CarNo] [nvarchar](10) NOT NULL,				--车牌号码
		[PeriodTime] [datetime] NOT NULL,				--收费截至时间
		[ChargeFee] [money] NULL,						--已收费用
		[InTime] [SmallDateTime] Null,					--上次计费入场时间 2015/09/01新加
		[oPeriodTime] [datetime] NULL,
	    [oChargeFee] [money] NULL,
	) ON [PRIMARY]
end
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场入场表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID('Park_CarIn','U') is not null
	PRINT '该表已存在'--drop table Park_CarIn
ELSE
begin
	CREATE TABLE [dbo].[Park_CarIn](
		[ID] [int] IDENTITY(1,1) NOT NULL,			--编号
		[CarEventNo][int]  NULL,                    --车场事件序号
		[MachNo] [tinyint] NOT NULL,				--控制器编号
		[YktID] [int] NOT NULL,						--开户ID号
		[CardID] [varchar](20) NULL,				--卡ID（7Byte）
		[CFlag] [tinyint] NULL,						--卡介质(0为IC，1为ID，2IC做ID，3)
		[CardNo] [varchar](10) NULL,				--卡编号
		[EmpName] [nvarchar](20) NULL,				--车主名称
		[CarNO] [nvarchar](10) NULL,				--车牌号
		[CardType] [tinyint] NOT NULL,				--卡类型
		[CarNoType] [tinyint] NULL,					--车牌类型
		[InTime] [datetime] NULL,					--入场时间
		[InUserName] [nvarchar](20) NULL,			--入场操作员
		[InPic] [nvarchar](255) NULL,				--图片路径
		[Small] [tinyint] NULL,						--小车场内
		[DiscountNo] [tinyint] NULL,				--打折机号
		[TypeID] [tinyint] NULL,					--模式ID
		[DiscountTime] [datetime] NULL,				--折扣时间
		[Makeup] [int] NULL,						--车牌补录标识 1-已补充 else-未补充
		[IsLocked] [tinyint] default(0) null,		--锁车标识 0-未锁 1-已锁
		[BackCarNO] [nvarchar](10) NULL,            --后车牌
		[BackInPic] [nvarchar](255) NULL,           --后车牌图片
		[InWay] [tinyint] default(0)  null            --入场方式 0正常入场，1校正入场 ，2手工入场 3,扫码入场 4,脱机记录，5，相机异常记录 6，异常开闸
	) ON [PRIMARY]
end
GO


-- ============================================= 
-- 程序编写： <王诚喜> 
-- 建立日期： <2016-12-26>  
-- 功能说明： <车场入场表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID('Park_CarInException','U') is not null
	PRINT '该表已存在'--drop table Park_CarInException
ELSE
begin
	CREATE TABLE [dbo].[Park_CarInException](
		[ID] [int] IDENTITY(1,1) NOT NULL,			--编号
		[MachNo] [tinyint] NOT NULL,				--控制器编号
		[YktID] [int] NOT NULL,						--开户ID号
		[CardID] [varchar](20) NULL,				--卡ID（7Byte）
		[CFlag] [tinyint] NULL,						--卡介质(0为IC，1为ID，2IC做ID，3)
		[CardNo] [varchar](10) NULL,				--卡编号
		[EmpName] [nvarchar](20) NULL,				--车主名称
		[CarNO] [nvarchar](10) NULL,				--车牌号
		[CardType] [tinyint] NOT NULL,				--卡类型
		[CarNoType] [tinyint] NULL,					--车牌类型
		[InTime] [datetime] NULL,					--入场时间
		[InUserName] [nvarchar](20) NULL,			--入场操作员
		[InPic] [nvarchar](255) NULL,				--图片路径
		[BInPic] [nvarchar](255) NULL,			    --原来入场图片
		[BInTime] [datetime] NULL ,                  --原有入场时间
		[InWay] [tinyint] default(0)  null         --入场方式 0,表示正常出场，1，校正入场 ，2，手工入场 3,非法开闸入场,4,入场回退 5,未授权入场
	) ON [PRIMARY]
end
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场卡类型表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Park_CardType','U') is not null
	PRINT '该表已存在'-- Park_CardType
ELSE
begin
	CREATE TABLE [dbo].[Park_CardType](
		[CType] [tinyint] NOT NULL,			--卡类ID
		[CName] [nvarchar](10) NULL,		--卡类名称
		[CCustName] [nvarchar](10) NULL,	--自定名称
		[NoUse] [tinyint] NULL,			    --是否不启用
		[IsSend] [tinyint] NULL,			--是否可发行
		[IDMode] [tinyint] NULL			    --ID模式(IC转ID用)
	) ON [PRIMARY]
end
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场岗亭摄像机设置表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Park_CamSet','U') is not null
	PRINT '该表已存在'-- Park_CamSet
ELSE
begin
	CREATE TABLE [dbo].[Park_CamSet](
		[BoxID] [int] NULL,						--排序号（1-8）
		[SortID] [tinyint] default(0) NOT NULL,	--岗亭ID
		[CamIP] [varchar](15) NOT NULL,			--摄像头IP
		[CamName] [nvarchar](20) NOT NULL,		--
		[CamPort] [int] NULL,					--设备名端口号卡类ID
		[FecPic] [tinyint] NULL,			    --是否人像
		[MachNo] [tinyint] NULL,				--控制器机号
		[ChannelID] [tinyint] NULL
	) ON [PRIMARY]
end
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场收费标准设置表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Park_ESMain','U') is not null
	PRINT '该表已存在'-- Park_ESMain
ELSE
	CREATE TABLE [dbo].[Park_ESMain](
		[ESNo] [tinyint] NOT NULL,					--编号（0标准，1通用标准I类，2通用标准II类，3深圳社会类）
		[CardType] [tinyint] NOT NULL,			--卡类
		[FreeMinute] [smallint] NOT NULL,			--免费分钟(最大1440)
		[FreeInclude] [tinyint] NULL,				--是否包含
		[ReCalc] [tinyint] NULL,					--整天之后计费有首段（用于：通用标准I类）
		[Manylimiting] [tinyint] NULL,				--多次出入限制（用于通用标准I类，便于上位机精确计费）
		[IsTimeAdd] [tinyint] NULL					--跨段补时（用于：通用标准II类）
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场收费标准设置表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Park_EsInfos','U') is not null
	PRINT '该表已存在'-- Park_EsInfos
ELSE
	CREATE TABLE [dbo].[Park_EsInfos](
		[EsNo] [tinyint] NOT NULL,					--编号（0标准收费类，1通用标准I类，2通用标准II类，3深圳社会类）
		[EsType] [tinyint] NOT NULL,				--模式（0普通收费 1超时收费）
		[CardType] [tinyint] NOT NULL,				--临时卡类（31~39）
		[EsInfo] [varchar](100) NULL				--下载字符串(16进制)
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场收费标准4设置表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Park_Es4_F','U') is not null
	PRINT '该表已存在'--drop table Park_Es4_F
ELSE
	CREATE TABLE [dbo].[Park_Es4_F](
		[CardType] [varchar](10) NOT NULL,	
		[NoWorkDayBegin] [varchar](8) NULL,				--非工作日1开始日期20130501
		[NoWorkDayend] [varchar](8) NULL,				--非工作日1结束日期20130503
		[WorkDayTBegin] [varchar](8) NULL,				--工作日2开始日期20131001
		[WorkDayTEnd] [varchar](8) NULL,				--工作日2结束日期20131007
		[PeakFieldBegin] [varchar](4) NULL,				--工作日高峰期开始
		[PeakFieldEnd] [varchar](4) NULL				--工作日高峰期结束
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场收费标准4设置表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Park_Es4','U') is not null
	PRINT '该表已存在'-- Park_Es4
ELSE
	CREATE TABLE [dbo].[Park_Es4](
		[CardType] [varchar](10) NOT NULL,			--卡类
		[NoWorkDayFirstHourAmount] [money] NULL,	--非工作日首小时收费
		[NoWorkDayChgUnit] [int] NULL,				--非工作日首小时后收费时间单位(固定60分钟)
		[NoWorkChgAmount] [money] NULL,				--非工作日首小时后时间单位收费金额
		[WorkDayFirstHourAmount] [money] NULL,		--工作日首小时收费金额
		[PeakChgUnit] [int] NULL,					--高峰期收费时间单位(固定30分钟)
		[PeakChgAmount] [money] NULL,				--高峰期单位时间收费金额
		[NotPeakChgUnit] [int] NULL,				--非高峰期收费时间单位(固定60分钟)
		[NotPeakChgAmount] [money] NULL,			--非高峰期单位时间收费金额
		[DayTopAmount] [money] NULL,				--全天最高收费
		[IsDate] [tinyint] NULL						--是否按天收费预设0
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场收费标准3设置表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Park_Es3','U') is not null
	PRINT '该表已存在'-- Park_Es3
ELSE
	CREATE TABLE [dbo].[Park_Es3](
		[CardType] [tinyint] NOT NULL,						--卡类
		[DayBegin] [varchar](5) NULL,						--白天开始 08:00
		[DayEnd] [varchar](5) NULL,							--白天结束 18:00
		[ChargingAmount1] [money] NULL,						--白天段单位时间收费金额
		[ChargingUnit1] [smallint] NULL,					--白天段收费时间单位 0按次收费,>按安时间单位收费(分钟)
		[NightBegin] [varchar](5) NULL,						--夜间段开始
		[NightEnd] [varchar](5) NULL,						--夜间段结束
		[ChargingAmount2] [money] NULL,						--夜间段单位时间收费金额
		[ChargingUnit2] [smallint] NULL,					--夜间段收费时间单位0 按次收费,>0按时间单位收费(分钟)
		[ChargingAmount3] [money] NULL,						--夜间段最高收费
		[ChargingAmount4] [money] NULL,						--全天最高收费
		[ChargingAmount5] [money] NULL						--白天最高收费（2015/09/01加）
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场收费标准2设置表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Park_Es2','U') is not null
	PRINT '该表已存在'-- Park_Es2
ELSE
	CREATE TABLE [dbo].[Park_Es2](
		[CardType] [tinyint] NOT NULL,					--卡类
		[ChargeReclHours] [tinyint] NULL,				--周期
		[ChLength1] [smallint] NULL,					--第一时段长度 分钟
		[ChUnit1] [smallint] NULL,						--第一段内收费时间单位 30分钟
		[ChAmount1] [money] NULL,						--第一段内单位时间收费金额
		[ChLength2] [smallint] NULL,					--第二时段长度 分钟
		[ChUnit2] [smallint] NULL,						--第二段内收费时间单位 30分钟
		[ChAmount2] [money] NULL,						--第二段内单位时间收费金额
		[ChLength3] [smallint] NULL,					--第三时段长度 分钟
		[ChUnit3] [smallint] NULL,						--第三段内收费时间单位 30分钟
		[ChAmount3] [money] NULL,						--第三段内单位时间收费金额
		[LastUnit] [smallint] NULL,						--剩余时段收费单位
		[LaseAmount] [money] NULL,						--剩余时段时间单位收费金额
		[HighAmount] [money] NULL						--全天最高收费金额
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场收费标准1设置表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Park_Es1','U') is not null
	PRINT '该表已存在'-- Park_Es1
ELSE
	CREATE TABLE [dbo].[Park_Es1](
		[CardType] [tinyint] NOT NULL,					--卡类
		[UnitType] [tinyint] NULL,						--收费单位（0——》元，1——》角）
		[TopMoney] [tinyint] NULL,						--全天最高收费
		[ChHour1] [tinyint] NULL,						--1小时收费金额
		[ChHour2] [tinyint] NULL,						--2小时收费金额
		[ChHour3] [tinyint] NULL,						--3小时收费金额
		[ChHour4] [tinyint] NULL,						--4小时收费金额
		[ChHour5] [tinyint] NULL,						--5小时收费金额
		[ChHour6] [tinyint] NULL,						--6小时收费金额
		[ChHour7] [tinyint] NULL,						--7小时收费金额
		[ChHour8] [tinyint] NULL,						--8小时收费金额
		[ChHour9] [tinyint] NULL,						--9小时收费金额
		[ChHour10] [tinyint] NULL,						--10小时收费金额
		[ChHour11] [tinyint] NULL,						--11小时收费金额
		[ChHour12] [tinyint] NULL,						--12小时收费金额
		[ChHour13] [tinyint] NULL,						--13小时收费金额
		[ChHour14] [tinyint] NULL,						--14小时收费金额
		[ChHour15] [tinyint] NULL,						--15小时收费金额
		[ChHour16] [tinyint] NULL,						--16小时收费金额
		[ChHour17] [tinyint] NULL,						--17小时收费金额
		[ChHour18] [tinyint] NULL,						--18小时收费金额
		[ChHour19] [tinyint] NULL,						--19小时收费金额
		[ChHour20] [tinyint] NULL,						--20小时收费金额
		[ChHour21] [tinyint] NULL,						--21小时收费金额
		[ChHour22] [tinyint] NULL,						--22小时收费金额
		[ChHour23] [tinyint] NULL,						--23小时收费金额
		[ChHour24] [tinyint] NULL,						--24小时收费金额
		[AZero] [Smallint] NULL,						--跨段收费金额	2015/09/01新加
		[AType] [tinyint] NULL							--跨段类型（0按24小时计算，1按过零点计算）2015/09/01新加
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场收费标准III类设置表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Park_Es5','U') is not null
	PRINT '该表已存在'-- Park_Es5
ELSE
	CREATE TABLE [dbo].[Park_Es5](
	[CardType] [tinyint] NOT NULL ,					--卡类
	[DayBegin] [varchar] (5) COLLATE Chinese_PRC_CI_AS NULL ,	--白天开始 08:00
	[DayEnd] [varchar] (5) COLLATE Chinese_PRC_CI_AS NULL ,		--白天结束 18:00
	[ChargingLength1] [smallint] NULL ,				--首段时长（分钟）
	[ChargingUnit1] [smallint] NULL ,				--首段收费单位（分钟）
	[ChargingAmount1] [money] NULL ,				--收费金额（分钟)
	[LastUnit1] [smallint] NULL ,					--之后收费单位(分钟)  0按次收费,>0安时间单位
	[LaseAmount1] [money]  NULL ,					--之后收费金额（元)
	[NightBegin] [varchar] (5) COLLATE Chinese_PRC_CI_AS NULL ,	--夜间段开始
	[NightEnd] [varchar] (5) COLLATE Chinese_PRC_CI_AS NULL ,	--夜间段结束
	[ChargingLength2] [smallint] NULL ,				--首段时长（分钟）
	[ChargingUnit2] [smallint] NULL ,				--首段收费单位（分钟）
	[ChargingAmount2] [money] NULL ,				--收费金额（元)
	[LastUnit2] [smallint] NULL ,					--之后收费单位(分钟) 0按次收费,>0安时间单位
	[LaseAmount2] [money] NULL ,					--之后收费金额（元)
	[ChargingAmount3] [money] NULL, 				--夜间最高收费（元)
	[ChargingAmount4] [money] NULL,  				--全天最高收费（元)
	[ChargingAmount5] [money] NULL  				--白天最高收费（元)
	) ON [PRIMARY]
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场收费标准四类设置表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Park_Es6','U') is not null
	PRINT '该表已存在'-- Park_Es2
ELSE
	CREATE TABLE [dbo].Park_Es6(
		[CardType] [tinyint] NOT NULL,					--卡类
		[ChargeReclHours] [smallint] NULL,				--周期
		[CountAmount] [money] NULL,						--计次收费金额
		[LateUnit] [smallint] NULL,						--计次之后时段收费单位
		[LateAmount] [money] NULL,						--计次之后时间单位收费金额
		[HighAmount] [money] NULL						--周期内最高收费金额
	) ON [PRIMARY]
GO

-- ============================================= 
-- 程序编写： <邓亚安> 
-- 建立日期： <2017-12-25>  
-- 功能说明： <车场代扣表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[AutoPayment]') AND type in (N'U'))
BEGIN
	CREATE TABLE AutoPayment(
	Id INT PRIMARY KEY IDENTITY(1,1) ,
	carNo  VARCHAR(20),--车牌号码
	agreementStatus INT, --车牌代扣状态，0：为支持代扣，1：为不支持代扣
	isLock  INT DEFAULT(0) ,--0  车在场内  1  不在场内
	modifyOn DATETIME DEFAULT(GETDATE())-- 修改时间
	) 
END

-- ============================================= 
-- 程序编写： <邓亚安> 
-- 建立日期： <2017-12-25>  
-- 功能说明： <用户待同步代扣账单表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[OrderInfo]') AND type in (N'U'))
BEGIN
	CREATE TABLE OrderInfo(
	Id INT PRIMARY KEY IDENTITY(1,1) ,
	carNo  VARCHAR(20),--车牌号码
	inTime DATETIME , --入场时间
    outTime DATETIME ,--出场时间
	accountAmount DECIMAL(18,2), --应收金额
	totalFee  DECIMAL(18,2), --应付金额
	disAmount DECIMAL(18,2), --折扣金额
	carNoColor VARCHAR(20),--车牌颜色
	tradeNo VARCHAR(100),--订单号  
	alipaySubject  VARCHAR(100),--订单标题   
	modifyOn DATETIME DEFAULT(GETDATE())-- 修改时间
	) 
END

-- ============================================= 
-- 程序编写： <邓亚安> 
-- 建立日期： <2017-12-25>  
-- 功能说明： <用户代扣账单表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[PaymentInfo]') AND type in (N'U'))
BEGIN
	CREATE TABLE PaymentInfo(
	Id INT PRIMARY KEY IDENTITY(1,1) ,
	parkingId VARCHAR(50),
	outParkingId VARCHAR(50),
	carNo  VARCHAR(20),--车牌号码
	inTime DATETIME , --入场时间
    outTime DATETIME ,--出场时间
	accountAmount DECIMAL(18,2), --应收金额
	totalFee  DECIMAL(18,2), --应付金额
	disAmount DECIMAL(18,2), --折扣金额
	carNoColor VARCHAR(20),--车牌颜色
	outTradeNo VARCHAR(100),--支付宝订单号  
	tradeNo VARCHAR(100),--订单号  
	alipaySubject  VARCHAR(100),--订单标题 
	alipayStatus INT DEFAULT(0), --0 代扣成功  1 代扣失败   
	modifyOn DATETIME DEFAULT(GETDATE())-- 修改时间
	) 
END

-- ============================================= 
-- 程序编写： <谭飞> 
-- 建立日期： <2015-09-18>  
-- 功能说明： <车场-打折设备表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Park_EquipmentS','U') is not null
	PRINT '该表已存在'-- DROP TABLE Park_EquipmentS
ELSE
	CREATE TABLE [dbo].[Park_EquipmentS](
		[EqID] [int] NOT NULL,							--设备ID
		[EqName] [varchar](20) NULL,					--设备名称
		[Memo] [varchar](50) NULL,						--备注
		[ClientNO] [varchar](50) NULL,					--终端标识 
		[DownLoad] [varchar](255) NULL					--下载标记(控制器)
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场打折记录表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Park_DisInfo','U') is not null
	PRINT '该表已存在'-- Park_DisInfo
ELSE
	CREATE TABLE [dbo].[Park_DisInfo](
		[EqID] [int] NOT NULL,						--设备ID
		[DiscountID] [int] NOT NULL,				--折扣ID
		[DisName] [varchar](20) NULL,				--折扣名称
		[DisType] [tinyint] NULL,					--折扣模式 1全免(0)  2 折扣率(<100) 3免时间(<65535) 4免金额(<65535)
		[DisAmount] [float] NULL,					--折扣值
		[DisRemark] [varchar](50) NULL				--备注
	) ON [PRIMARY]
GO
 ---打折明细表
  IF OBJECT_ID('Park_DisDetail','U') is not null
	PRINT '该表已存在'-- Park_DisDetail
  ELSE
  CREATE TABLE [dbo].[Park_DisDetail](
          [ID] [int] IDENTITY(1,1) NOT NULL,			--编号
          [OutType] [tinyint]  NULL,				    --出场类型 0,出场.1，中心
          [DiscountID] [int]    NULL,                   --打折模式ID
          [CardID] [varchar](20) NULL,				    --卡ID（）
          [OutTime][datetime] NULL,                     --出场时间
          [DiscountTime] [datetime] NULL,				--折扣时间
          [DisAmount] [float] NULL, 			        --折扣金额
          [Type] [tinyint] default(0)  null,   --0表示线下，1表示线下
          [OnlineId] [VARCHAR](50)
          )ON [PRIMARY]
   go       



-- ============================================= 
-- 程序编写： <邓亚安> 
-- 建立日期： <2017-06-09>  
-- 功能说明： <车场打折记录表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Park_DistributeCoupons','U') is not null
	PRINT '该表已存在'-- Park_DistributeCoupons
ELSE
    BEGIN
	CREATE TABLE Park_DistributeCoupons(
	[Id] INT IDENTITY(1,1) PRIMARY KEY ,        --主键
	[OnlineId] VARCHAR (50),                    --唯一id
	[OpenId] VARCHAR(50),                       --微信用户ID
	[CarNo] VARCHAR(20),                        --车牌号码
	[InTime] DATETIME,                          --入场时间
	[DiscountTime] [datetime] NULL,				--折扣时间
	[DiscountNo] [tinyint] NULL,				--打折机号
	[TypeID] [tinyint] NULL,					--模式ID
	[CouponId] VARCHAR(50),                     --线上优惠劵ID
	[CouponName] VARCHAR(200),                  --优惠劵名称
	[CommercialId] VARCHAR(50),                 --商铺编号
	[CommercialName] VARCHAR(200),              --商铺名称
	[CouponType] INT,                           --折扣类型
	[CouponAmount] VARCHAR(50),                 --折扣模式
	[UserName] VARCHAR(50),                     --操作用户
	[Description] VARCHAR(2000)                 --备注
	)
   END
GO




-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场吞卡记录表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('ParK_TunkaInfo','U') is not null
	PRINT '该表已存在'-- ParK_TunkaInfo
ELSE
	CREATE TABLE [dbo].[ParK_TunkaInfo](
		[CardID] [varchar](20) NOT NULL,			--卡ID号
		[CFlag] [tinyint] NOT NULL,					--卡介质(0为IC，1为ID，2IC做ID，3)
		[CardNo] [varchar](10) NULL,				--卡编号
		[MachNo] [tinyint] NOT NULL,				--控制器机号
		[CardType] [tinyint] NOT NULL,				--卡类型
		[TunkaType] [tinyint] NOT NULL,				--吞卡类型(0出卡机逾时卡，1出卡机场内卡，2出卡机无效卡，3收卡机无效卡)
		[TunkaTime] [datetime] NULL,				--吞卡时间
		[UserName] [nvarchar](20) NULL				--操作员
	) ON [PRIMARY]
GO

IF OBJECT_ID('ParK_SumUser','U') is not null
	PRINT '该表已存在'--drop table ParK_SumUser
ELSE
	CREATE TABLE [dbo].[ParK_SumUser](
		[BoxID] [tinyint] NOT NULL,			--岗亭编号
		[LoginDate] [datetime] NOT NULL,	--登入时间
		[ReliefDate] [datetime] NULL,		--交班时间
		[UserName] [nvarchar](20) NULL,		--交班操作员
		[ReUserName] [nvarchar](20) NULL,	--接班操作员
		[HandGate] [int] NULL,				--手动开闸
		[ComputerGate][int] null,           --电脑开闸
		[TempCardIn] [int] NULL,			--临时卡入场张数
		[TempCardOut] [int] NULL,			--临时卡出场张数
		[MonthCardIn][int] NULL,            --月租车入场数
		[MonthCardOut][int] NULL,           --月租车出场数
		[StoredCardNum][int]null,           --储值卡数
		[StoredCardMoney][money] NULL,      --储值卡收费金额
		[TempFree] [int] NULL,				--临免卡张数
		[FreeCarNo][int] NULL,              --免费卡数
		[FreeCharge] [money] NULL,			--免费金额
		[DisSum] [int] NULL,				--折扣张数
		[DisCharge] [money] NULL,			--折扣金额
		[PosMoney]  [money] NULL ,		    --POS消费（电商抵扣）
		[TotalCharge] [money] NULL,			--现金金额
		[TNominal] [int] NULL,			    --工本费
		[Account] [money] NULL,               --应收金额
		
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <未知>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('ParK_Preassign','U') is not null
	PRINT '该表已存在'-- ParK_Preassign
ELSE
	CREATE TABLE [dbo].[ParK_Preassign](
		[MachNo] [tinyint] NOT NULL,			--入口控制器机号
		[CardType] [tinyint] NOT NULL,			--卡类型
		[CarNO] [varchar](10) NULL,				--车牌号
		[CarNoType] [tinyint] NULL				--车牌类型
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场超时收费设置表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Park_OverTimeSet','U') is not null
	PRINT '该表已存在'-- Park_OverTimeSet
ELSE
	CREATE TABLE [dbo].[Park_OverTimeSet](
		[CardType] [tinyint] NOT NULL,				--卡类型
		[OverTimeMinute] [smallint] NULL,			--超时分钟
		[FreeInclude] [tinyint] NULL,				--超时分钟是否包括
		[OverTimeAmount] [money] NULL,				--收费金额（最大6553.5）
		[OverTimeUnit] [smallint] NULL,				--收费单位（最大1440分钟）
		[FreeMinute] [smallint] NULL				--免费分钟
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场月租卡金额设置表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Park_MonthSet','U') is not null
	PRINT '该表已存在'-- Park_MonthSet
ELSE
	CREATE TABLE [dbo].[Park_MonthSet](
		[CardType] [tinyint] NOT NULL,			--月租卡类型(11,12,13,14)
		[sType] [tinyint] NOT NULL,				--类型(0月卡1季度卡2半年卡3年卡)
		[ChargeMoney] [money] NULL,				--金额
		[Remark] [varchar](50) NULL				--备注
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场岗亭信息表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Park_LocalSet','U') is not null
	PRINT '该表已存在'-- Park_LocalSet
ELSE
	CREATE TABLE [dbo].[Park_LocalSet](
		[BoxID] [tinyint] NOT NULL,                                  --岗亭编号
		[BoxIP] [varchar](15) NOT NULL,                              --岗亭IP
		[BoxName] [nvarchar](20) NOT NULL,                           --岗亭名称
		[BoxType] [tinyint] NULL,								     --岗亭类型（0标准收费点1中央收费点2综合收费点）
		[ImageCarType] [tinyint] NULL,                               --影像类型（0无,1）
		[Imagetimelag] [int] NULL,                                   --图像对比延时（s）
		[smallImage] [tinyint] NULL,                                 --小图像
		[ZJType] [tinyint] NULL,                                     --证件抓拍类型(0无1USB摄像头)
		[ZJAV] [tinyint] NULL,										 --证件抓拍口(串口)
		[IDSpaceOfTime] [tinyint] NULL,                              --ID卡出入限制秒数
		[CardLater] [int] NULL,										 --取卡超时时间（s）
		[UseTicket] [tinyint] NULL,                                  --是否使用扫描枪
		[TicketCom] [tinyint] NULL,                                  --扫描枪端口
		[TicketOutNo] [tinyint] NULL,                                --扫描枪出口开闸机号
		[PlaceType] [tinyint] NULL,                                  --车位类型（混合车位、临时车位、固定车位）
		[PlaceMode] [tinyint] NULL,                                  --车位模式（大车场、小车场）
		[PlaceNum] [int] NULL,										 --车位数(总数量)
		[GateOverSee] [tinyint] NULL,                                --状态监控
		[CheckType] [tinyint] NULL,                                  --检测类型（0控制器1发行器）
		[CheckNo] [tinyint] NULL,									 --机号或串口号（1代表Com1）
		[OpenMachNo] [tinyint] NULL,                                 --开闸机号（用于临时卡计费器收费）
		[LoadTime] [tinyint] NULL,									 --自动加载时间
		[CarSopt] [tinyint] NULL,                                    --启用车牌识别
		[CarSoptIp] [varchar](15) NULL,                              --车牌识别主机IP
		[CarSoptPort] [smallint] NULL,                               --车牌识别端口
		[CarSortNum] [tinyint] NULL,                                 --置信度
		[CamUser] [nvarchar](15) null,                               --摄像机用户名
		[CamPass] [nvarchar](15) null,								 --摄像机密码
		[PicQuilty] [tinyint] null,									 --图片质量
		[PicSize] [tinyint] null,									 --图片分辨率
		[Other] [tinyint] NULL,										 --预留
		[TempCheckIP] [varchar](15) NULL,                            --
		[TempMachNo] [tinyint] NULL,                                  --
		[VIPCarNum] [int] NULL,
		[CarNoViewAddr] [int] NULL
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场用户消费统计表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('TmpCardMoneyC2','U') is not null
	PRINT '该表已存在'-- TmpCardMoneyC2
ELSE
	CREATE TABLE [dbo].[TmpCardMoneyC2] (
			[UserName] [nvarchar] (20) NULL ,			--操作员
			[CarNO] [nvarchar] (10)  NULL ,				--车牌号码
			[CardNO] [varchar] (10)  NULL ,				--卡片编号
			[MB] [Money] NULL ,							--收费标准
			[sTypeName] [varchar] (10)  NULL ,			--操作类型
			[SETime] [varchar] (11)  NULL ,				--期间
			[M1] [Money] NULL ,							--1月金额
			[M2] [Money] NULL ,							--2月金额
			[M3] [Money] NULL ,							--3月金额
			[M4] [Money] NULL ,							--4月金额
			[M5] [Money] NULL ,							--5月金额
			[M6] [Money] NULL ,							--6月金额
			[M7] [Money] NULL ,							--7月金额
			[M8] [Money] NULL ,							--8月金额
			[M9] [Money] NULL ,							--9月金额
			[M10] [Money] NULL ,						--10月金额
			[M11] [Money] NULL ,						--11月金额
			[M12] [Money] NULL ,						--12月金额
			[Total] [Money] NULL ,						--总金额
			[UserDate] [datetime] NULL ,				--操作时间
			[ReMark] [nvarchar] (50)  NULL,				--备注
			[MMM] [varchar] (12)  NULL					--标识收费的月
	) ON [PRIMARY]
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <状态监控表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Park_DeviceStatus','U') is not null
	PRINT '该表已存在'-- Park_DeviceStatus
ELSE
	CREATE TABLE [dbo].[Park_DeviceStatus](
	[DID] [int] NOT NULL,         --设备编号
	[DType] [int] NULL,           --设备类型
	[DIP] [varchar](50) NOT NULL,   --设备IP
	[BoxID] [int] NULL,             --岗亭编号
	[RoadGateState] [int] NULL,     --道闸状态 0-关到位，1-中间位，2-开到位
    [CardMachineState] [int] NULL,   --卡机状态 0-正常，1-少卡，2-无卡，3-吞卡箱满，4-故障，5-堵卡
    [PaperMachineState] [int] NULL,  --纸票机状态 0-正常，1-纸少，2-无纸，3-出票口有票，4-故障，5-忙，6-打印失败
    [PowerReset] [int] NULL,         --主板上电复位,自检结果和关健配置
    [GroudSenseUnnormal] [int] NULL,  --地感异常 00-恢复，01-异常
    [CameraUnnormal] [int] NULL,      --摄像机异常 00-恢复，01-异常 
    [ClientConnectUnnormal] [int] NULL,   --岗亭PC连接异常 00-恢复，01-异常
    [ServerConnectUnnormal] [int] NULL,   --服务器连接异常 00-恢复，01-异常
    [DisplaymoduleConnectUnnormal] [int] NULL, --显示模块连接异常 00-恢复，01-异常
    [VoicemoduleConnectUnnormal] [int] NULL,    --语音模块连接异常 00-恢复，01-异常
    [RoadGateConnectUnnormal] [int] NULL,       --道闸连接异常 00-恢复，01-异常
    [CardManchuneConnectUnnormal] [int] NULL,   --卡机连接异常 00-恢复，01-异常
    [PaperMachineConnectUnnormal] [int] NULL,   --纸票机连接异常 00-恢复，01-异常
    [MainBoardNetUnnormal] [int] NULL,          --主板网络异常 00-恢复，01-异常
    [SystemClockUnnormal] [int] NULL,           --系统时钟故障 00-恢复，01-异常
    [SystemStorageUnnormal] [int] NULL,         --系统存储故障 00-恢复，01-异常
	[OnlineTime] [datetime] NULL,     --在线时间
	[LastUpdateTime] [datetime] NULL,  --最后一次更新时间
	[Remark] [varchar](50) NULL,       --备注
 CONSTRAINT [PK_Park_DeviceStatus] PRIMARY KEY CLUSTERED 
(
	[DIP] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]



-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-日常计划表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Door_UsualPlan','U') is not null
	PRINT '该表已存在'-- Door_UsualPlan
ELSE
	CREATE TABLE [dbo].[Door_UsualPlan](
		[PlanNo] [tinyint] NOT NULL,					--计划编号  1-200
		[PlanName] [nvarchar](20) NULL,					--计划名称
		[week] [varchar](8) NOT NULL,					--星期一 .....星期日   0：无效，1有效  多一位用0补充
		[TimeZones1] [varchar](8) NULL,					--有效时区开始和结束时分:08301200 高位是开始，低位是结束
		[TimeZones2] [varchar](8) NULL,					--有效时区开始和结束时分:08301201 高位是开始，低位是结束
		[TimeZones3] [varchar](8) NULL,					--有效时区开始和结束时分:08301202 高位是开始，低位是结束
		[TimeZones4] [varchar](8) NULL,					--有效时区开始和结束时分:08301203 高位是开始，低位是结束
		[state] [tinyint] NULL,							--状态
		[Remarks] [nvarchar](100) NULL,					--备注
		[DownLoad] [varchar](1000) NULL,				--下载标记(控制器)
		[DelType] [tinyint] NULL						--
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-超级密码设置表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Door_Pwd','U') is not null
	PRINT '该表已存在'-- Door_Pwd
ELSE
	CREATE TABLE [dbo].[Door_Pwd](
		[ID] [int] IDENTITY(1,1) NOT NULL,				--主键ID
		[DoorPwd] [varchar](10) NOT NULL,				--胁迫密码
		[SuperPwd1] [varchar](10) NOT NULL,				--超级密码1
		[SuperPwd2] [varchar](10) NOT NULL,				--超级密码2
		[SuperPwd3] [varchar](10) NOT NULL,				--超级密码3
		[SuperPwd4] [varchar](10) NOT NULL,				--超级密码4
		[SuperPwd5] [varchar](10) NOT NULL,				--超级密码5
		[Remarks] [nvarchar](100) NULL,					--备注
		[Type] [tinyint] NULL							--是否启用
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-设防模式设置表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Door_Prevent','U') is not null
	PRINT '该表已存在'-- Door_Prevent
ELSE
	CREATE TABLE [dbo].[Door_Prevent](
		[ID] [int] IDENTITY(1,1) NOT NULL,			--主键ID
		[PID] [int] NOT NULL,						--跟主键一致
		[Name] [nvarchar](20) NULL,					--设防模式名称
		[ContID] [nvarchar](1000) NULL,				--控制器ID
		[State] [tinyint] NOT NULL,					--是否启用
		[BeginTime] [smalldatetime] NULL,			--开始日期
		[EndTime] [smalldatetime] NULL,				--结束日期
		[RepPeriod] [varchar](8) NULL,				--预留字段（无用）
		[TimeZones1] [varchar](16) NULL,			--时区1
		[TimeZones2] [varchar](16) NULL,			--时区2
		[TimeZones3] [varchar](16) NULL,			--时区3
		[TimeZones4] [varchar](16) NULL,			--时区4
		[TimeZones5] [varchar](16) NULL,			--时区5
		[TimeZones6] [varchar](16) NULL,			--时区6
		[TimeZones7] [varchar](16) NULL,			--时区7
		[Remarks] [nvarchar](100) NULL				--备注
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-用户组权限设置表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Door_PowerGradeList','U') is not null
	PRINT '该表已存在'-- Door_PowerGradeList
ELSE
	CREATE TABLE [dbo].[Door_PowerGradeList](
		[GradeID] [tinyint] NOT NULL,			--引用级别ID
		[ContID] [int] NOT NULL,				--控制器机号
		[DoorPower] [varchar](8) NULL			--01010000
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-用户组设置表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Door_PowerGrade','U') is not null
	PRINT '该表已存在'-- Door_PowerGrade
ELSE
	CREATE TABLE [dbo].[Door_PowerGrade](
		[GradeID] [tinyint] NOT NULL,				--级别ID
		[Grade] [nvarchar](10) NULL,				--级别名称
		[RightList] [varchar](1000) NULL,			--控制器权限
		[IsDel] [tinyint] NOT NULL default 0		--
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-特殊计划设置表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Door_PartPlan','U') is not null
	PRINT '该表已存在'-- Door_PartPlan
ELSE
	CREATE TABLE [dbo].[Door_PartPlan](
		[PartNo] [tinyint] NOT NULL,					--特殊计划编号
		[PartName] [nvarchar](20) NULL,					--特殊计划名称
		[GradeID] [varchar](32) NOT NULL,				--计划编号，001001100......日常计划有序性，针对1-32个日常计划
		[PartType] [tinyint] NULL,						--工作类型：1.工作日  2.假日 ； 选择“假日”时才能够选择时区
		[PartBeginTime] [smalldatetime] NULL,			--开始时间（年月日）
		[PartEndTime] [smalldatetime] NULL,				--结束时间（年月日）
		[PartEnabled] [tinyint] NULL,					--是否启用 0未启用，1启用。
		[TimeZones1] [varchar](8) NULL,					--有效时区开始和结束时分:08301200 高位是开始，低位是结束
		[TimeZones2] [varchar](8) NULL,					--有效时区开始和结束时分:08301201 高位是开始，低位是结束
		[TimeZones3] [varchar](8) NULL,					--有效时区开始和结束时分:08301202 高位是开始，低位是结束
		[TimeZones4] [varchar](8) NULL,					--有效时区开始和结束时分:08301203 高位是开始，低位是结束
		[Remarks] [nvarchar](100) NULL,					--备注
		[DownLoad] [varchar](1000) NULL,				--下载标记(控制器)
		[DelType] [tinyint] NULL						--
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-进出记录表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Door_OpenDoorRecord','U') is not null
	PRINT '该表已存在'-- drop table Door_OpenDoorRecord
ELSE
	CREATE TABLE [dbo].[Door_OpenDoorRecord](
		[ID] [int] IDENTITY(1,1) NOT NULL,								--主键自增ID
		[PID] [int] NOT NULL,											--系统自动编号（唯一）
		[ContID] [int] NOT NULL,										--控制器机号
		[DoorID] [int] NOT NULL,										--门ID
		[CardType] [nvarchar](20) NULL,									--卡类型
		[CardID] [varchar](20) NOT NULL,								--卡ID号
		[EventTime] [datetime] NOT NULL,								--开门时间
		[EventWay] [smallint] NULL,										--开门方式：1进门 2出门
		[EventType] [nvarchar](50) NULL,								--报警类型（详细查阅协议）
		[PerID] [varchar](15) NULL,										--用户编号
		[PerName] [nvarchar](20) NULL,									--用户姓名
		[PicPath] [nvarchar](255) NULL,									--本地图片路径
		[PicName] [nvarchar](100) NULL,									--图片名称
		[Extended] [nvarchar](50) NULL									--扩展字段
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-操作日志表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Door_Log','U') is not null
	PRINT '该表已存在'-- Door_Log
ELSE
	CREATE TABLE [dbo].[Door_Log](
		[ID] [int] IDENTITY(1,1) NOT NULL,				--ID主键
		[LogDate] [datetime] NULL,						--操作时间
		[UserName] [nvarchar](20) NULL,					--操作人
		[Computer] [nvarchar](200) NULL,				--IP地址
		[LogClass] [nvarchar](200) NULL,				--日志类型门禁
		[LogObject] [nvarchar](200) NULL,				--操作对象【操作页面标题，例如：添加设备管理】
		[LogModel] [nvarchar](200) NULL,				--操作模块【页面+增加、修改、删除。例如：Park_devices.Add】
		[LogContent] [nvarchar](500) NULL				--具体内容【例如：增加设备名称为[SN0001]，设备类型为[控制器]成功！】
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-区域表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Door_UserArea','U') is not null
	PRINT '该表已存在'-- Door_UserArea
ELSE
	CREATE TABLE [dbo].[Door_UserArea](
		[UserName] [nvarchar](20) NULL,			--用户名
		[AreaNo] [varchar](10) NULL				--区域编号
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-首卡开门设置表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Door_FirstCardOpenDoor','U') is not null
	PRINT '该表已存在'-- Door_FirstCardOpenDoor
ELSE
	CREATE TABLE [dbo].[Door_FirstCardOpenDoor] (
		[ID] [int] IDENTITY (1, 1) NOT NULL , 			--主键自增ID
		[Pid] [int] NOT NULL,							--系统自动编号（唯一）
		[FName] [nvarchar] (8) NULL,					--名称
		[RepPeriod] [varchar] (8)  NULL,        		--重复周期 01010101 周日...周三..周一 。  0：无效 1有效
		[TimeZones1] [varchar] (8)  NULL,       		--开始结束时分
		[TimeZones2] [varchar] (8)  NULL,     			--开始结束时分
		[TimeZones3] [varchar] (8)  NULL,     			--开始结束时分
		[TimeZones4] [varchar] (8)  NULL,  				--开始结束时分
		[Mode1] [varchar] (8) NULL,						--开门模式，低4位门控制方式：0-正常，1-常开，2-常闭 高4位认证方式：	0-刷卡开门1-刷卡或密码开门2-卡加密码开门
		[Mode2] [varchar] (8) NULL,						--开门模式，低4位门控制方式：0-正常，1-常开，2-常闭 高4位认证方式：	0-刷卡开门1-刷卡或密码开门2-卡加密码开门
		[Mode3] [varchar] (8) NULL,						--开门模式，低4位门控制方式：0-正常，1-常开，2-常闭 高4位认证方式：	0-刷卡开门1-刷卡或密码开门2-卡加密码开门
		[Mode4] [varchar] (8) NULL,						--开门模式，低4位门控制方式：0-正常，1-常开，2-常闭 高4位认证方式：	0-刷卡开门1-刷卡或密码开门2-卡加密码开门
		[Remarks] [nvarchar] (100)   NULL, 				--备注                             
		[Type] [tinyint] NOT NULL						--类型
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-多卡开门设置表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Door_ManyCardOpenDoor','U') is not null
	PRINT '该表已存在'-- Door_ManyCardOpenDoor
ELSE
	CREATE TABLE [dbo].[Door_ManyCardOpenDoor] (
		[ID] [int] IDENTITY (1, 1) NOT NULL ,      --主键自增ID
		[Pid] [int] NOT NULL,					   --系统自动编号（唯一）
		[ManyCardName] [nvarchar] (10)  NULL ,     --多卡开门组名称
		[CardID] [varchar] (200)   NULL ,		   --用户卡片ID,多个卡号中间用,隔开.最多个用户
		[Remarks] [nvarchar] (100)  NULL,          --备注
		[Type] [tinyint] NOT NULL				   --是否启用0.不启用1启用
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-定时任务设置表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Door_TimingTask','U') is not null
	PRINT '该表已存在'-- Door_TimingTask
ELSE
	CREATE TABLE [dbo].[Door_TimingTask] (
		[ID] [int]  NOT NULL ,					   --定时任务ID
		[ContID] [nvarchar] (1000)  NULL,		   --引用控制器ID，多个中间以半角逗号隔开
		[TimingTaskName] [nvarchar] (20) NULL ,    --名称
		[DoorNoStr] [varchar] (4) NULL ,           --门序号有效性，，门序号有效性Bit1-Bit4对应门-4
		[DoorOpenMode] [varchar] (8) NULL,         --开门模式，低位门控制方式：-正常，-常开，-常闭高位认证方式：	0-刷卡开门-刷卡或密码开门-卡加密码开门
		[OpenTime] [varchar] (4)  NULL,            --开始时分
		[OverTime] [varchar] (4)  NULL,            --结束时分
		[Remarks] [nvarchar] (100)  NULL,          --备注
		[RepPeriod] [varchar] (8)  NULL,           --重复周期01010101 周日...周三..周一。 0：无效1有效
		[Type] [tinyint] NULL,                     --定时任务是否启用0.不启用1启用
		[DownLoad] [varchar] (1000)  NULL 		   --下载标记(控制器)
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-反潜与互锁设置表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Door_FanAndLock','U') is not null
	PRINT '该表已存在'-- Door_FanAndLock
ELSE
	CREATE TABLE [dbo].[Door_FanAndLock] (
		[ID] [int] IDENTITY (1, 1) NOT NULL ,       --主键自增ID
		[ContSneak] [varchar] (2)  NULL,			--反潜回方式，：号门和号门 2：号门和号门 3：号门和号门，号门和号门
		[ContInterlock] [varchar] (2)  NULL,		--互锁方式，：号门和号门 2：号门和号门，号门和号门,3:号门和号门和号门和号门互锁
		[Remarks] [nvarchar] (100)  NULL			--备注
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-设备状态表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Door_StateSave','U') is not null
	PRINT '该表已存在'-- Door_StateSave
ELSE
	CREATE TABLE [dbo].[Door_StateSave] (
		[ContID] [int] NOT NULL ,	                --控制器机号
		[ContNo] [varchar] (20)  NULL,				--控制器编号
		[ContIP] [varchar] (20) NULL,               --控制器IP
		[DoorNo] [int] NOT NULL ,					--门序号
		[Content] [nvarchar] (200)  NULL,			--具体存储内容:详见协议
		[Type] [varchar] (20) NULL,    				--存储设备状态:详见协议
		[Time] [DateTime] NULL  					--时间：该时间是判断控制器是否在线，如果该时间和本机时间相差间隔超过规定时间为脱机
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-进出记录表(只存储最近1000条)>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Door_OpenDoorRecordTwo','U') is not null
	PRINT '该表已存在'-- Door_OpenDoorRecordTwo
ELSE
	CREATE TABLE [dbo].[Door_OpenDoorRecordTwo] (
		[ID] [int] IDENTITY (1, 1) NOT NULL ,                           --主键自增ID
		[PID] [int] NOT NULL,											--系统自动编号（唯一）
		[ContID] [int] NOT NULL ,										--控制器机号
		[DoorID] [int] NOT NULL ,					                    --门ID
		[CardType] [nvarchar] (20) NULL,                                --卡类型
		[CardID] [varchar] (20)  NOT NULL ,								--卡ID号
		[EventTime] [datetime] NOT NULL ,				                --开门时间
		[EventWay] [smallint] NULL,                                     --开门方式：进门2出门
		[EventType] [nvarchar](50)  NULL ,								--报警类型（详细查阅协议）
		[PerID] [varchar] (15)  NULL ,		    						--用户编号
		[PerName] [nvarchar] (20)  NULL,  	    						--用户姓名
		[PicPath] [nvarchar](255) NULL,									--本地图片路径
		[PicName] [nvarchar](100) NULL									--图片名称
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-事件记录表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Door_EventRecord','U') is not null
	PRINT '该表已存在'-- Door_EventRecord
ELSE
	CREATE TABLE [dbo].[Door_EventRecord] (
		[ID] [int] IDENTITY (1, 1) NOT NULL ,                           --主键自增ID
		[PID] [int] NOT NULL,											--系统自动编号（唯一）
		[ContID] [int] NOT NULL ,	                                    --控制器机号
		[DoorID] [int] NOT NULL ,				                    	--门ID
		[EventTime] [datetime] NOT NULL ,				                --事件发生时间
		[EventType] [tinyint] NULL ,					                --门状态（详细查阅协议）
		[Managed] [tinyint] NULL ,					                    --是否处理0:未处理1:已处理
		[UserID] [nvarchar] (20)  NULL 		--操作员
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-火警联动设置表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Door_FireConfig','U') is not null
	PRINT '该表已存在'-- Door_FireConfig
ELSE
	CREATE TABLE [dbo].[Door_FireConfig](
		MainContCode	nvarchar(20) not null,							--主设备编号
		MainContName	nvarchar(20) not null,							--主设备名称
		RefContCode		nvarchar(20) not null,							--关联设备编号
		RefContName		nvarchar(20) not null,							--关联设备名称
		RefContIP		varchar(20) not null,							--关联设备IP
		RefDoorPower	varchar(8) not null 							--关联设备门序号
	)ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-门密码设置表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Door_DoorPwd','U') is not null
	PRINT '该表已存在'-- Door_DoorPwd
ELSE
	CREATE TABLE [dbo].[Door_DoorPwd](
		[ID] [int] IDENTITY(1,1) NOT NULL, 					--主键自增ID
		[PID] [int] NOT NULL, 								--系统自动编号（唯一）
		[DoorPwd] [varchar](10) NOT NULL, 					--密码
		[DoorID] [int] NOT NULL, 							--门ID
		[PwdType] [tinyint] NULL, 							--密码类型  1超级密码 2胁迫密码
		[Download] [tinyint] NULL 							--是否已下载
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-地图监控图片设置表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Door_DoorMaps','U') is not null
	PRINT '该表已存在'-- Door_DoorMaps
ELSE
	CREATE TABLE [dbo].[Door_DoorMaps](
		[ID] [int] IDENTITY(1,1) NOT NULL, 				--地图ID
		[AreaNo] [varchar](10) NOT NULL, 				--区域编号
		[ContID] [int] NULL, 							--控制器机号
		[MapName] [nvarchar](30) NULL, 					--地图名称
		[MapFile] [nvarchar](200) NULL, 				--地图背景文件
		[MapRemark] [nvarchar](50) NULL 				--地图备注，暂时未用
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-地图监控门位置设置表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Door_DoorMapPlace','U') is not null
	PRINT '该表已存在'-- Door_DoorMapPlace
ELSE
	CREATE TABLE [dbo].[Door_DoorMapPlace](
		[ID] [int] IDENTITY(1,1) NOT NULL, 		--主键自增ID
		[PID] [int] NOT NULL, 					--系统自动编号（唯一）
		[DoorID] [int] NOT NULL, 				--门ID
		[MapID] [int] NOT NULL, 				--地图编号
		[DoorLocationX] [float] NOT NULL, 		--X坐标，位置比例
		[DoorLocationY] [float] NOT NULL, 		--Y坐标，位置比例
		[State] [tinyint] NULL 					--是否实时监控 0:否 1：是
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-门信息位置设置表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Door_Door','U') is not null
	PRINT '该表已存在'-- Door_Door
ELSE
	CREATE TABLE [dbo].[Door_Door](
		[ID] [int] IDENTITY(1,1) NOT NULL, 		--门编号自增
		[Pid] [int] NOT NULL, 					--系统自动编号（唯一）
		[DoorNo] [int] NOT NULL, 				--门序号
		[DoorName] [nvarchar](10) NULL, 		--门名称
		[ContID] [int] NOT NULL, 				--控制器机号
		[CardIndex] [int] NULL, 				--读卡器索引
		[CardName1] [nvarchar](20) NULL, 		--读卡器1名称
		[CardDescribe1] [nvarchar](50) NULL, 	--读卡器1描述
		[CardName2] [nvarchar](20) NULL, 		--读卡器2名称
		[CardDescribe2] [nvarchar](50) NULL, 	--读卡器2描述
		[DoorOpenMode] [varchar](8) NULL, 		--门状态，低4位门控制方式：0-正常，1-常开，2-常闭高4位认证方式：	0-刷卡开门1-刷卡或密码开门2-卡加密码开门
		[FirstCard] [tinyint] NULL, 			--首卡开门是否启用 0未启用，1启用
		[FirstCardID] [int] NULL, 				--关联首卡开门ID
		[ManyCard] [tinyint] NULL, 				--多卡开门是否启用 0未启用  1启用
		[ManyCardID] [int] NULL, 				--
		[SuperPwd] [tinyint] NULL, 				--是否启用超级密码
		[SuperPwdID] [int] NULL, 				--是否启用胁迫密码
		[Alarm] [varchar](8) NULL, 				--报警
		[DoorRemark] [nvarchar](100) NULL, 		--备注
		[DownLoad] [varchar](4) NULL 			--是否已下载
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-摄像机设置表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Door_CamSet','U') is not null
	PRINT '该表已存在'-- Door_CamSet
ELSE
	CREATE TABLE [dbo].[Door_CamSet](
		[CamID] [int] IDENTITY(1,1) NOT NULL, 			--摄像机ID
		[ComputerIP] [varchar](15) NOT NULL, 			--本机电脑IP
		[ContID] [tinyint] NOT NULL, 					--控制器机号
		[DoorNo] [tinyint] NOT NULL, 					--门序号
		[CamIP] [varchar](15) NOT NULL, 				--摄像机IP
		[CamName] [nvarchar](20) NOT NULL, 				--摄像机名称
		[CamType] [int] NULL,							--摄像机类型
		[CamUser] [nvarchar](20) null,					--登录用户名
		[CamPass] [nvarchar](20) null,					--登录密码
		[CamPort] [int] NULL, 							--摄像机端口号
		[FecPic] [tinyint] NULL, 						--预留字段（无用）
		[CamParaID] [int] NOT NULL, 					--摄像机参数ID
		CONSTRAINT [PK_CamID] PRIMARY KEY([CamID]) 		
	)
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-摄像机参数设置表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Door_CamParaLocalSet','U') is not null
	PRINT '该表已存在'-- Door_CamParaLocalSet
ELSE
	CREATE TABLE [dbo].[Door_CamParaLocalSet](
		[CamParaID] [int] identity(1,1) NOT NULL, 		--摄像机参数ID
		[ImageCarType] [tinyint] NULL, 					--摄像机类型
		[CamUser] [nvarchar](15) NULL, 					--摄像机用户名
		[CamPass] [nvarchar](15) NULL, 					--摄像机密码
		[PicQuilty] [tinyint] NULL, 					--图片质量
		[PicSize] [tinyint] NULL, 						--分辨率
		[smallImage] [tinyint] NULL, 					--是否人像画中画
		[Imagetimelag] [int] NULL, 						--画中画放大延时
		CONSTRAINT [PK_CamParaID] PRIMARY KEY([CamParaID])
	)
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-控制器参数设置表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Door_Cont','U') is not null
	PRINT '该表已存在'-- Door_Cont
ELSE
	CREATE TABLE [dbo].[Door_Cont](
		[ContNo] [nvarchar](20) NULL, 						--控制器机号
		[ContID] [int] NOT NULL, 							--控制器机号
		[ContName] [nvarchar](20) NULL, 					--控制器名称
		[ContType] [int] NULL,								--控制器类型
		[AreaNo] [varchar](10) NULL, 						--区域编号
		[ContIP] [varchar](20) NULL, 						--控制器IP
		[ContTimingTask] [tinyint] NULL, 					--定时任务是否启用 0.不启用 1启用
		[ContTimingTaskID] [nvarchar](100) NULL, 			--关联定时任务ID
		[ContSneak] [tinyint] NULL, 						--是否启用反潜回 0.不启用 1启用
		[ContSneakType] [tinyint] NULL, 					--反潜回方式，1：1号门和2号门  2：3号门和4号门  3：1号门和2号门，3号门和4号门
		[ContInterlock] [tinyint] NULL, 					--是否开启互锁模式  0.不启用 1启用
		[ContInterLockType] [tinyint] NULL, 				--互锁方式，1：1号门和2号门  2：3号门和4号门  3：1号门和2号门，3号门和4号门
		[DoorKeepInfo] [varchar](8) NULL, 					--Bit1按钮开门记录是否保存:0-不保存，1-保存Bit2	无效刷卡记录是否保存:0-不保存，1-保存Bit3	非法开门记录是否保存:0-不保存，1-保存 Bit4记录满是否自动覆盖:0-不覆盖，1-覆盖 Bit5-Bit8:预留填充0
		[DoorType] [varchar](8) NULL, 						--Bit1是否为双向门:0-单向门，1-双向门 Bit2是否允许远程开门:0-不允许，1-允许 Bit3-Bit8:预留	填充0
		[DoorOverTime] [tinyint] NULL, 						--门长时间未关报警时间
		[DoorOpenTime] [tinyint] NULL, 						--开门延时,最小2秒
		[DoorAlarm] [tinyint] NULL, 						--报警
		[DoorContent1] [varchar](32) NULL, 					--预留
		[ContRemarks] [nvarchar](100) NULL, 				--备注
		[DownLoad] [varchar](8) NULL 						--下载标志
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-区域设置表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Door_Area','U') is not null
	PRINT '该表已存在'-- Door_Area
ELSE
	CREATE TABLE [dbo].[Door_Area](
		[AreaNo] [varchar](10) NOT NULL, 				--区域编号
		[AreaName] [nvarchar](20) NULL, 				--区域名称
		[NodeNo] [nvarchar](10) NULL, 					--节点编号
		[AreaRemarks] [nvarchar](100) NULL, 			--备注
		[AreaWorkstations] [tinyint] NULL, 				--是否为工作站
		[AreaIP] [varchar](15) NULL 					--IP
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <访客-访客登记表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Guest_VPerson','U') is not null
	PRINT '该表已存在'-- Guest_VPerson
ELSE
	CREATE TABLE [dbo].[Guest_VPerson](
		[ID] [int] IDENTITY(1,1) NOT NULL, 			--编号
		[VistorID] [int] NULL, 						--访客编号
		[GusetNum] [varchar](20) NULL, 				--访客单号
		[GuestName] [nvarchar](10) NULL, 			--访客姓名
		[Sex] [nvarchar](2) NULL, 					--访客性别
		[IDType] [nvarchar](10) NULL, 				--证件类型
		[IDNumber] [varchar](30) NULL, 				--证件号码
		[IDImage] [varchar](50) NULL, 				--证件照片
		[IDAddress] [nvarchar](50) NULL, 			--地址
		[LeveaTime] [datetime] NULL, 				--离开时间
		[WriteTime] [datetime] NULL, 				--登记时间
		[GuestCardNo] [varchar](20) NULL, 			--访客卡号
		[UserName] [varchar](20) NULL, 				--登记员
		[Remark] [nvarchar](50) NULL, 				--备注
		[iStatus] [int] NULL, 						--状态
	 CONSTRAINT [PK_GUEST_VPERSON] PRIMARY KEY CLUSTERED ([ID] ))
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <访客-来访记录表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Guest_VisitorLogs','U') is not null
	PRINT '该表已存在'-- Guest_VisitorLogs
ELSE
	CREATE TABLE [dbo].[Guest_VisitorLogs](
		[ID] [int] IDENTITY(1,1) NOT NULL, 		--编号
		[GusetNum] [varchar](20) NULL, 			--单号
		[GuestType] [varchar](20) NULL, 		--访客类型
		[GName] [nvarchar](10) NULL, 			--访客姓名
		[Sex] [nvarchar](2) NULL, 				--访客性别
		[IDType] [nvarchar](10) NULL, 			--证件类型
		[IDNumber] [varchar](30) NULL, 			--证件号码
		[IDAddress] [nvarchar](50) NULL, 		--地址
		[IDImage] [varchar](50) NULL, 			--证件照片
		[GuestTime] [datetime] NULL, 			--来访时间
		[DesCompany] [nvarchar](50) NULL, 		--访问单位
		[GusrtReason] [nvarchar](50) NULL, 		--来访原因
		[GuestNum] [int] NULL, 					--来访人数
		[GuestPerson] [nvarchar](20) NULL, 		--来访人员
		[CarType] [nvarchar](20) NULL, 			--车辆类型
		[CarNo] [nvarchar](15) NULL, 			--车牌
		[InBoxName] [nvarchar](20) NULL, 		--进入门岗
		[OutBoxName] [nvarchar](20) NULL, 		--离开门岗
		[LeaveTime] [datetime] NULL, 			--离开时间
		[UserName] [varchar](20) NULL, 			--登记员
		[IsOrder] [tinyint] NULL, 				--是否预约
		[GuestCardNo] [varchar](20) NULL, 		--访客卡号
		[Remark] [nvarchar](50) NULL, 			--备注
		[DesDept] [nvarchar](20) NULL, 			--被访人部门
		[DesEmpID] [varchar](12) NULL, 			--被访人编号
		[DesName] [nvarchar](20) NULL, 			--被访人姓名
		[iStatus] [int] NULL, 		 			--状态
		[AddTime] [datetime] NULL, 				--添加时间
	 CONSTRAINT [PK_GUEST_VISITORLOGS] PRIMARY KEY CLUSTERED ([ID]))
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <访客-物品存取记录表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Guest_Res','U') is not null
	PRINT '该表已存在'-- Guest_Res
ELSE
	CREATE TABLE [dbo].[Guest_Res](
		[ID] [int] IDENTITY(1,1) NOT NULL, 			--编号
		[VisitorID] [int] NULL, 					--访客编号
		[GuestNum] [varchar](20) NULL, 				--访客单号
		[ResName] [nvarchar](50) NULL, 				--物品名称
		[Num] [int] NULL, 							--数量
		[ResUnit] [nvarchar](6) NULL, 				--单位
		[TakeTime] [datetime] NULL, 				--取走时间
		[WriteTime] [datetime] NULL, 				--登记时间
		[UserName] [varchar](20) NULL, 				--登记员
		[Remark] [varchar](50) NULL, 				--备注
	 CONSTRAINT [PK_GUEST_RES] PRIMARY KEY CLUSTERED ([ID]))
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <访客-未知>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Guest_iType','U') is not null
	PRINT '该表已存在'-- Guest_iType
ELSE
	CREATE TABLE [dbo].[Guest_iType](
		[ID] [int] IDENTITY(1,1) NOT NULL, 				--编号
		[iType] [int] NULL, 							--类别
		[iTypeName] [nvarchar](30) NULL, 				--类别名称
		[Remark] [nvarchar](100) NULL, 					--类别说明
	 CONSTRAINT [PK_GUEST_ITYPE] PRIMARY KEY CLUSTERED ([ID]))
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <访客-客户信息表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Guest_Company','U') is not null
	PRINT '该表已存在'-- Guest_Company
ELSE
	CREATE TABLE [dbo].[Guest_Company](
		[ID] [int] IDENTITY(1,1) NOT NULL, 			--编号
		[CoName] [varchar](50) NULL, 				--公司名称
		[CoNameEng] [varchar](100) NULL, 			--英文名称
		[CoPhoneNum] [varchar](20) NULL, 			--公司电话
		[CoFax] [varchar](20) NULL, 				--传真
		[CoMasterName] [varchar](20) NULL, 			--联系人
		[TelNumber] [varchar](20) NULL, 			--联系手机
		[CoAddress] [varchar](100) NULL, 			--公司地址
		[CoMail] [varchar](50) NULL, 				--邮箱
		[iType] [int] NULL, 						--类别【预留】
		[iStatus] [int] NULL, 						--状态【浏览】
		[Remark] [varchar](50) NULL, 				--备注
	 CONSTRAINT [PK_GUEST_COMPANY] PRIMARY KEY NONCLUSTERED ([ID]))
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <图片信息表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Per_Photo','U') is not null
	PRINT '该表已存在'-- Per_Photo
ELSE
	CREATE TABLE [dbo].[Per_Photo](
		[Pid] [int] NOT NULL, 					--
		[PhoNo] [tinyint] NULL, 				--类型(0人像照片1车身照片)
		[PhoImage] [image] NULL, 				--图像
		[ImageTime] [datetime] NULL, 			--图像时间
		[PhoPath] [nvarchar](255) NULL 			--图片路径
	) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <人员信息表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Per_Persons','U') is not null
	PRINT '该表已存在'-- Per_Persons
ELSE
	CREATE TABLE [dbo].[Per_Persons](
		[id] [int] IDENTITY(1,1) NOT NULL, 		--
		[Pid] [int] NOT NULL, 					--系统自动编号（唯一）
		[PerID] [varchar](15) NULL, 			--编号
		[PerName] [nvarchar](20) NULL, 			--姓名(唯一)
		[PerSbM] [varchar](10) NULL, 			--识别码
		[PerAddr] [nvarchar](50) NULL, 			--住址
		[PerTel] [nvarchar](30) NULL, 			--手机
		[LandlineNum] [varchar](20) null,		--座机号
		[ReMark] [nvarchar](100) NULL, 			--备注
		[PerIDNo] [nvarchar](18) NULL, 			--身份证号
		[PerSex] [nvarchar](2) NULL, 			--性别
		[BirthDay] [smalldatetime] NULL, 		--出生日期
		[PerNative] [nvarchar](10) NULL, 		--民族
		[PerPlace] [nvarchar](50) NULL, 		--籍贯
		[PerPost] [nvarchar](10) NULL, 			--邮编
		[Marry] [nvarchar](2) NULL, 			--婚姻状况
		[PerEmail] [nvarchar](18) NULL, 		--邮箱
		[PerHandTel] [nvarchar](18) NULL, 		--紧急联系人电话
		[Peremergency] [nvarchar](18) NULL, 	--紧急联系人
		[PerType] [tinyint] NULL, 				--员工类型
		[enterDate] [smalldatetime] NULL, 		--入职日期
		[FormatDate] [smalldatetime] NULL, 		--转正日期
		[EduLevel] [nvarchar](10) NULL, 		--学历
		[Member] [nvarchar](10) NULL, 			--政治面貌
		[DeptID] [nvarchar](10) NULL, 			--部门编号
		[Positions] [nvarchar](20) NULL, 		--职务
		[Isleave] [tinyint] NULL, 				--是否离职
		[leaveDate] [smalldatetime] NULL, 		--离职日期
		[leaveCause] [nvarchar](20) NULL, 		--离职原因
		[Memo] [nvarchar](200) NULL, 			--档案备注
		[CrUserDate] [datetime] NULL, 			--建立日期
		[CrUserName] [nvarchar](20) NOT NULL, 	--建立使用者
		[status] [tinyint] NULL, 				--记录状态（0新记录1已发卡2逻辑删除）
                [UpdateFlag] [bit] NULL, 
                ParentName nvarchar(20) NULL,               --家长姓名
                ParentTel varchar(20) NULL,                 --家长电话
                ParentIDCard varchar(20) NULL,              --家长身份证
                ParentRelation nvarchar(10) NULL            --家长与学生关系
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <未知>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Per_ParkSet','U') is not null
	PRINT '该表已存在'-- Per_ParkSet
ELSE
	CREATE TABLE [dbo].[Per_ParkSet](
		[Pid] [int] NOT NULL, 					--
		[CarNo] [nvarchar](10) NULL, 			--车牌号码
		[CarNoType] [tinyint] NULL, 			--车牌类型
		[CarPlace] [nvarchar](10) NULL, 		--车位
		[CarColour] [nvarchar](10) NULL, 		--车颜色
		[CardType] [tinyint] NULL, 				--预置卡类
		[SDate] [smalldatetime] NULL, 			--有效开始日期
		[EDate] [smalldatetime] NULL, 			--有效结束日期
		[Memo] [nvarchar](200) NULL 			--车场备注
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <部门信息表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Per_Dept','U') is not null
	PRINT '该表已存在'-- drop table Per_Dept
ELSE
	CREATE TABLE [dbo].[Per_Dept](
	[DeptID] [nvarchar](10) NULL, 				--部门编号
	[DeptName] [nvarchar](20) NULL, 			--部门名称
	[TopDeptID] [nvarchar](10) NULL, 			--上级部门
	[OrgId] [nvarchar](10) NULL,
	[UpdateFlag] [bit] NULL,
	[AutoID] [int] IDENTITY(1,1) NOT NULL
) ON [PRIMARY]
GO
  
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <版本信息表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Sys_Version','U') is not null
	PRINT '该表已存在'-- Sys_Version
ELSE
	CREATE TABLE [dbo].[Sys_Version](
		[VSoft] [varchar](20) NOT NULL,
		[VProgram] [varchar](20) NULL,
		[VDB] [varchar](20) NULL,
		[VType] [int] NOT NULL,
		[PublishDate] [datetime] NOT NULL,
		[CreateDate] [datetime] NOT NULL,
        [UpdateDate] [datetime] NOT NULL,
		[Comment] [nchar](100) NULL,
	 CONSTRAINT [PK_Sys_Version] PRIMARY KEY CLUSTERED([VSoft] ASC) ON [PRIMARY]
	) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <系统设置字典扩展表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Sys_Dict','U') IS NOT NULL
  PRINT '该表已存在'--drop table Sys_Dict
ELSE
BEGIN
	CREATE TABLE [dbo].[Sys_Dict](
		[DicID] [int] IDENTITY(1,1) NOT NULL, 
		[NodeID]  [varchar](10) NOT NULL,
		[ParentNodeID] [int] NOT NULL,
		[DependentNodeID] [varchar](100) NULL,
		[NodeName] [nvarchar](20) NOT NULL,
        [NodeValue][varchar](50) NOT NULL,
        [NodeValueDefault][varchar](50) NULL,
		[NodeDesc] [nvarchar](50) NULL,
		[NodeFlag] [varchar](50) NULL,
        [NodeFormat] [varchar](50) NULL,
		[IsDelete] [tinyint] NOT NULL,
		[OrderID]  numeric(8,2) NOT NULL,
		[OrgID] [varchar](20) NULL,
        [Comment] [varchar](200) NULL,
        [NodeIDOther]  [varchar](10) NULL,
		[NodeNameOther] [nvarchar](20) NULL,
        [NodeValueOther][varchar](50)  NULL,
        [LanguageCode] [varchar](20) NULL,
        [UserDate] datetime default(getdate()),  
	 CONSTRAINT [PK_Sys_Dict_1] PRIMARY KEY CLUSTERED 
(
	[DicID] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY],
 CONSTRAINT [IX_Sys_Dict] UNIQUE NONCLUSTERED 
(
	[NodeID] ASC,
	[LanguageCode] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
END
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-自动同步卡信息中间表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Syn_CardIssue','U') IS NOT NULL
  PRINT '该表已存在'-- Syn_CardIssue
ELSE
BEGIN
	CREATE TABLE [dbo].[Syn_CardIssue](
		[CardID] [varchar](50) NOT NULL,
		[CardIDOld] [varchar](50) NULL,
		[CardNO] [varchar](10) NULL,
		[CFlag] [tinyint] NULL,
		[Foregift] [money] NULL,
		[States] [tinyint] NULL,
        [StatesName] [nvarchar](10) NULL,
		[CardType] [tinyint] NULL,
		[StartDate] [smalldatetime] NULL,
		[EndDate] [smalldatetime] NULL,
		[PerID] [varchar](15) NOT NULL,
		[PerName] [nvarchar](20) NOT NULL,
		[Sex] [varchar](12) NULL,
        [DeptID] [varchar](15) NULL,
		[DeptName] [nvarchar](20) NULL,
		[DataUpdateDate] [datetime] NOT NULL,
		[CreateDate] [datetime] default(getdate()) NOT NULL,
	 CONSTRAINT [PK_DYN_CardIssue] PRIMARY KEY CLUSTERED 
	(
		[CardID] ASC,
        [DataUpdateDate] ASC
	) ON [PRIMARY]
	) ON [PRIMARY]
END
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <交班报表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('PKCarOutRpt','U') is not null
	PRINT '该表已存在'--  PKCarOutRpt
ELSE
	CREATE TABLE [dbo].[PKCarOutRpt] (
		[Username] [varchar] (20) COLLATE Chinese_PRC_CI_AS NULL ,
		[Gate1] [int] NULL ,
		[Gate2] [int] NULL ,
		[GateFee] [money] NULL ,
		[ITempA] [int] NULL ,
		[ITempB] [int] NULL ,
		[ITempC] [int] NULL ,
		[ITempD] [int] NULL ,
		[ExcepAmount] [money] NULL,	--例外收费
		[TUnOut] [money] NULL ,		--定点收费
		[TOver] [money] NULL ,		--超时收费	
		[Imoney] [money] NULL ,		--出口收费	
		[ImonthA] [int] NULL ,	
		[ImonthB] [int] NULL ,
		[ImonthC] [int] NULL ,
		[ImonthD] [int] NULL ,
		[ImonthE] [int] NULL ,
		[ImonthF] [int] NULL ,
		[ImonthAvg] [int] NULL ,	--月租停放数
		[IMoney0] [int] NULL ,
		[IMoneyA] [int] NULL ,
		[IMoneyB] [int] NULL ,
		[IMoneyC] [int] NULL ,
		[IMoneyD] [int] NULL ,
		[IMoneySum] [money] NULL ,
		[IFreeA] [int] NULL ,		
		[IFreeB] [int] NULL ,
		[IVipCar] [int] NULL ,
		[SumQuan] [int] NULL ,		--总流量
		[SumPayCharge][money] NULL ,--实收金额
		[ITempFree] [int] NULL ,	--临免车
		[FreeAmount] [money] NULL ,  --免费金额
		[DisAMount] [money] NULL ,	 --折扣金额
		[PosMoney]  [money] NULL ,		--POS消费（电商抵扣）
		[OnlinePay] [money] NULL ,		--网络支付
		[Preferential] [money] NULL,	--活动免费
		[TNominal] [int] NULL,			--工本费
		[Account] [money] NULL   --合计
	) ON [PRIMARY]
	GO
	
IF OBJECT_ID('Guest_TwoCodeRecord','U') is not null
	PRINT '该表已存在'-- drop TABLE Guest_TwoCodeRecord
ELSE
	CREATE TABLE [dbo].[Guest_TwoCodeRecord](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[VisitorName] [varchar](50) NULL,
	[sex] [varchar](50) NOT NULL,
	[IDCardNo] [varchar](50) NULL,
	[Phone] [varchar](50) NULL,
	[CarNo] [varchar](50) NULL,
	[HousingCode] [varchar](50) NULL,
	[inOutType] [varchar](50) NULL,
	[RegTime] [datetime] NULL,
	[StartTime] [datetime] NULL,
	[EndTime] [datetime] NULL,
	[Parameter] [int] NULL,
	[IP] [varchar](50) NULL,
	[TwoCode] [varchar](50) NULL,
	[SN] [varchar](50) NULL,
	[RoomNo] [varchar](50) NULL,
	[BuildingNo] [varchar](50) NULL,
	[InOutTime] [datetime] DEFAULT (getdate()) NULL,
	[LimitType] [varchar](50) NULL,
 CONSTRAINT [PK_Guest_TwoCodeRecord] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <访客-登记表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Guest_Reg','U') is not null
	PRINT '该表已存在'--  Guest_Reg
ELSE
begin
	CREATE TABLE [dbo].[Guest_Reg](
		[id] [int] IDENTITY(1,1) NOT NULL,
		[VisitorName] [varchar](50) NULL,					--访客姓名
		[sex] [varchar](10) NULL,							--性别
		[IDCardNo] [varchar](50) NULL,						--身份证号
		[CardICNo] [varchar](50) NULL,						--卡IC编号
		[Address] [nvarchar](100) NULL,						--户籍地址
		[IDPic] [image] NULL,								--身份证照片
		[Photo] [varchar](100) NULL,						--现场照片
		[company] [varchar](50) NULL,						--公司名称
		[Phone] [varchar](50) NULL,							--手机号码
		[Reason] [varchar](50) NULL,						--来访事由
		[Pid] [int] NULL,									--人员编号
		[CarNo] [varchar](50) NULL,							
		[Indate] [datetime] DEFAULT (getdate()) NULL,
		[Num] [int] NULL,
		[openType] [int] NULL
	) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
end
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <访客-预约表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Guest_Order','U') is not null
	PRINT '该表已存在'--  Guest_Order
ELSE
begin
	CREATE TABLE [dbo].[Guest_Order](
		[id] [int] IDENTITY(1,1) NOT NULL,
		[VisitorName] [varchar](50) NULL,			--访客姓名
		[IDCardNo] [varchar](100) NULL,				--访客身份证号
		[Pid] [int] NULL,							--被访人员编号
		[State] [varchar](50) NULL,					--预约状态
		[StartDate] [datetime] NOT NULL,			--到访开始时间
		[EndDate] [datetime] NOT NULL,				--到访结束时间
	 CONSTRAINT [PK_Visitor_Order] PRIMARY KEY CLUSTERED 
	(
		[id] ASC
	)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
	) ON [PRIMARY]
end
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <访客-控制器设置表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Guest_ContSet','U') is not null
	PRINT '该表已存在'-- drop table Guest_ContSet
ELSE
CREATE TABLE [dbo].[Guest_ContSet](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[ContID] [varchar](50) NULL,
	[ContIP] [varchar](50) NOT NULL,
	[InOutType] [varchar](50) NULL,
	[DoorUse] [varchar](50) NULL,
 CONSTRAINT [PK_Guest_ContSet] PRIMARY KEY CLUSTERED 
(
	[ContIP] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO


-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <访客-黑名单表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Guest_Blacklist','U') is not null
	PRINT '该表已存在'--  Guest_Blacklist
ELSE
begin
	CREATE TABLE [dbo].[Guest_Blacklist](
		[id] [int] IDENTITY(1,1) NOT NULL,
		[GuestName] [varchar](50) NULL,
		[IDCardNo] [varchar](50) NOT NULL,
	 CONSTRAINT [PK_Guest_Blacklist] PRIMARY KEY CLUSTERED 
	(
		[IDCardNo] ASC
	)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
	) ON [PRIMARY]
end
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Park_ChanelTemp') IS NOT NULL
	DROP TABLE dbo.Park_ChanelTemp
GO
CREATE TABLE [dbo].[Park_ChanelTemp](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[chanelName] [varchar](50) NOT NULL,
	[chanelNo] [int] NOT NULL,
 CONSTRAINT [PK_Park_ChanelTemp] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场-车场黑名单，免费车，自由通行车辆>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('ParK_CarBlackList','U') is not null
		PRINT '该表已存在'--  DROP TABLE ParK_CarBlackList
ELSE
begin
	CREATE TABLE [dbo].[ParK_CarBlackList] (
	[CarNO] [nvarchar] (10) COLLATE Chinese_PRC_CI_AS NOT NULL,  --车牌号码
	[CarNoType] [tinyint] NULL ,                                  --车牌类型(0无,1黑名单,2特种车辆)
	[IsStop] [tinyint] NULL ,                                     --类型(0无,1禁止通行,2通行免费,3自由通行)
	[Memo] [nvarchar] (50) COLLATE Chinese_PRC_CI_AS NULL ,       --备注
	[CrTime] [datetime] NULL ,                                    --创建时间
	[CrUserName] [nvarchar] (20) COLLATE Chinese_PRC_CI_AS NULL   --创建人
) ON [PRIMARY]
END
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场-月卡出入限制表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Park_EffetTimes') IS NOT NULL
	DROP TABLE dbo.Park_EffetTimes
GO
CREATE TABLE [dbo].[Park_EffetTimes](
	[CardType] [tinyint] NOT NULL,
	[BTime] [varchar](8) NULL,
	[Eime] [varchar](8) NULL,
	[sType] [tinyint] NULL,
	[Multiple] [tinyint] NULL
) ON [PRIMARY]
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场-待出、入车辆信号>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Park_Log') IS NOT NULL
	PRINT '该表已存在'--DROP TABLE dbo.Park_Log
else
begin
CREATE TABLE [dbo].[Park_Log](
	[ID] [int] IDENTITY(1,1) NOT NULL,      --流水号
	[device]  [tinyint] NOT NULL,           --设备号
	[CarNO] [nvarchar](10) NULL,            --车牌号
	[createdTime] [datetime] NOT NULL,      --时间
	[picture] [nvarchar](255) NULL,         --图片
	[Small] [tinyint] NULL,                 --大小车场 
	[channel]  [tinyint] NULL,
	[gate]  [tinyint] NULL,
	[steps] [tinyint] NULL
) ON [PRIMARY]
end
GO

-- |**************************************************************************************|
-- |*******************************| 创建表(end) |****************************************|
-- |**************************************************************************************|


IF OBJECT_ID ('dbo.CarNo_Materecord') IS NOT NULL
BEGIN
	PRINT '该表已存在'--  Guest_Reg
end
else
  begin
    CREATE TABLE [dbo].CarNo_Materecord(
	   [InCarNO] [nvarchar](10) NULL,  --入场识别车牌
	   [OutCarNO] [nvarchar](10) NULL, --出场识别车牌
	   [modifyInCarNO] [nvarchar](10) NULL, --入场修正车牌
	   [modifyOutCarNO] [nvarchar](10) NULL,     --出场修正车牌
	   [ID][int] NULL --出场ID
)   ON [PRIMARY]
  end
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2016-07-15>  
-- 功能说明： <车牌模糊匹配记录>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Vw_CarNo_Materecord') IS NOT NULL
	DROP VIEW dbo.Vw_CarNo_Materecord
GO
CREATE VIEW [dbo].[Vw_CarNo_Materecord]
	AS
	select a.InCarNO ,a.OutCarNO ,a.modifyInCarNO ,a.modifyOutCarNO ,b.InTime ,b.OutTime ,b.InPic ,b.OutPic 
	from CarNo_Materecord a left join Park_CarOut b on a.ID =b.ID 
	
GO


if exists (select * from sysobjects where id = OBJECT_ID('[Pb_CarBeLong]') and OBJECTPROPERTY(id, 'IsUserTable') = 1) DROP TABLE [Pb_CarBeLong] 
 CREATE TABLE [Pb_CarBeLong] (  [Cid] [int] IDENTITY (1, 1) NOT NULL , [Pro] [varchar] (50) NULL , [Tb] [varchar] (50) NULL , [sAdd] [varchar] (50) NULL )

 SET IDENTITY_INSERT [Pb_CarBeLong] ON

 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 1 , '西藏' , '藏A' , '拉萨市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 2 , '西藏' , '藏B' , '昌都地区' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 3 , '西藏' , '藏C' , '山南地区' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 4 , '西藏' , '藏D' , '日喀则地区' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 5 , '西藏' , '藏E' , '那曲地区' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 6 , '西藏' , '藏F' , '阿里地区' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 7 , '西藏' , '藏G' , '林芝地区' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 8 , '四川省' , '川A' , '成都市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 9 , '四川省' , '川B' , '绵阳市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 10 , '四川省' , '川C' , '自贡市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 11 , '四川省' , '川D' , '攀枝花市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 12 , '四川省' , '川E' , '泸州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 13 , '四川省' , '川F' , '德阳市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 14 , '四川省' , '川H' , '广元市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 15 , '四川省' , '川J' , '遂宁市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 16 , '四川省' , '川K' , '内江市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 17 , '四川省' , '川L' , '乐山市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 18 , '四川省' , '川M' , '资阳市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 19 , '四川省' , '川N' , '涪陵' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 20 , '四川省' , '川P' , '黔江行署' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 21 , '四川省' , '川Q' , '宜宾市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 22 , '四川省' , '川R' , '南充市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 23 , '四川省' , '川S' , '达州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 24 , '四川省' , '川T' , '雅安市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 25 , '四川省' , '川U' , '阿坝藏族羌族自治州' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 26 , '四川省' , '川V' , '甘孜藏族自治州' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 27 , '四川省' , '川W' , '凉山彝族自治州' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 28 , '四川省' , '川X' , '广安市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 29 , '四川省' , '川Y' , '巴中市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 30 , '四川省' , '川Z' , '眉山市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 31 , '湖北省' , '鄂A' , '武汉市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 32 , '湖北省' , '鄂B' , '黄石市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 33 , '湖北省' , '鄂C' , '十堰市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 34 , '湖北省' , '鄂D' , '荆州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 35 , '湖北省' , '鄂E' , '宜昌市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 36 , '湖北省' , '鄂F' , '襄樊市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 37 , '湖北省' , '鄂G' , '鄂州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 38 , '湖北省' , '鄂H' , '荆门市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 39 , '湖北省' , '鄂J' , '黄冈市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 40 , '湖北省' , '鄂K' , '孝感市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 41 , '湖北省' , '鄂L' , '咸宁市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 42 , '湖北省' , '鄂M' , '仙桃市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 43 , '湖北省' , '鄂N' , '潜江市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 44 , '湖北省' , '鄂P' , '神农架林区' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 45 , '湖北省' , '鄂Q' , '恩施土家族苗族自治州' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 46 , '湖北省' , '鄂R' , '天门市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 47 , '湖北省' , '鄂S' , '随州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 48 , '甘肃省' , '甘A' , '兰州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 49 , '甘肃省' , '甘B' , '嘉峪关市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 50 , '甘肃省' , '甘C' , '金昌市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 51 , '甘肃省' , '甘D' , '白银市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 52 , '甘肃省' , '甘E' , '天水市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 53 , '甘肃省' , '甘F' , '洒泉市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 54 , '甘肃省' , '甘G' , '张掖市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 55 , '甘肃省' , '甘H' , '武威市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 56 , '甘肃省' , '甘J' , '定西市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 57 , '甘肃省' , '甘K' , '陇南市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 58 , '甘肃省' , '甘L' , '平凉市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 59 , '甘肃省' , '甘M' , '庆阳市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 60 , '甘肃省' , '甘N' , '临夏回族' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 61 , '甘肃省' , '甘P' , '甘南藏族' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 62 , '江西省' , '赣A' , '南昌市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 63 , '江西省' , '赣B' , '赣州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 64 , '江西省' , '赣C' , '宜春市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 65 , '江西省' , '赣D' , '吉安市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 66 , '江西省' , '赣E' , '上饶市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 67 , '江西省' , '赣F' , '抚州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 68 , '江西省' , '赣G' , '九江市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 69 , '江西省' , '赣H' , '景德镇市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 70 , '江西省' , '赣J' , '萍乡市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 71 , '江西省' , '赣K' , '新余市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 72 , '江西省' , '赣L' , '鹰潭市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 73 , '江西省' , '赣M' , '南昌市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 74 , '贵州省' , '贵A' , '贵阳市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 75 , '贵州省' , '贵B' , '六盘水市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 76 , '贵州省' , '贵C' , '遵义市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 77 , '贵州省' , '贵D' , '铜仁市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 78 , '贵州省' , '贵E' , '黔西南布依族苗族自治州' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 79 , '贵州省' , '贵F' , '毕节市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 80 , '贵州省' , '贵G' , '安顺市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 81 , '贵州省' , '贵H' , '黔东南苗族侗族自治州' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 82 , '贵州省' , '贵J' , '黔南布依族苗族自治州' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 83 , '广西' , '桂A' , '南宁市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 84 , '广西' , '桂B' , '柳州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 85 , '广西' , '桂C' , '桂林市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 86 , '广西' , '桂D' , '梧州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 87 , '广西' , '桂E' , '北海市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 88 , '广西' , '桂F' , '南宁地区' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 89 , '广西' , '桂G' , '柳州地区' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 90 , '广西' , '桂H' , '桂林地区' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 91 , '广西' , '桂J' , '梧州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 92 , '广西' , '桂K' , '玉林市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 93 , '广西' , '桂L' , '百色市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 94 , '广西' , '桂M' , '河池市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 95 , '广西' , '桂N' , '钦州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 96 , '广西' , '桂P' , '防城港市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 97 , '黑龙江省' , '黑A' , '哈尔滨市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 98 , '黑龙江省' , '黑B' , '齐齐哈尔市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 99 , '黑龙江省' , '黑C' , '牡丹江市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 100 , '黑龙江省' , '黑D' , '佳木斯市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 101 , '黑龙江省' , '黑E' , '大庆市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 102 , '黑龙江省' , '黑F' , '伊春市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 103 , '黑龙江省' , '黑G' , '鸡西市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 104 , '黑龙江省' , '黑H' , '鹤岗市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 105 , '黑龙江省' , '黑J' , '双鸭山市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 106 , '黑龙江省' , '黑K' , '七台河市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 107 , '黑龙江省' , '黑L' , '松花江地区（已并入哈尔滨市，车牌未改）' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 108 , '黑龙江省' , '黑M' , '绥化市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 109 , '黑龙江省' , '黑N' , '黑河市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 110 , '黑龙江省' , '黑P' , '大兴安岭行' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 111 , '黑龙江省' , '黑R' , '农恳系统' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 112 , '上海市' , '沪A' , '上海市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 113 , '上海市' , '沪B' , '上海市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 114 , '上海市' , '沪C' , '上海市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 115 , '上海市' , '沪D' , '上海市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 116 , '上海市' , '沪R' , '崇明、长兴、横沙' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 117 , '吉林省' , '吉A' , '长春市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 118 , '吉林省' , '吉B' , '吉林市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 119 , '吉林省' , '吉C' , '四平市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 120 , '吉林省' , '吉D' , '辽源市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 121 , '吉林省' , '吉E' , '通化市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 122 , '吉林省' , '吉F' , '白山市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 123 , '吉林省' , '吉G' , '白城市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 124 , '吉林省' , '吉H' , '延边朝鲜族自治州' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 125 , '吉林省' , '吉J' , '松原市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 126 , '河北省' , '冀A' , '石家庄市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 127 , '河北省' , '冀B' , '唐山市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 128 , '河北省' , '冀C' , '秦皇岛市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 129 , '河北省' , '冀D' , '邯郸市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 130 , '河北省' , '冀E' , '刑台市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 131 , '河北省' , '冀F' , '保定市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 132 , '河北省' , '冀G' , '张家口市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 133 , '河北省' , '冀H' , '承德市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 134 , '河北省' , '冀J' , '沧州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 135 , '河北省' , '冀R' , '廊坊市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 136 , '河北省' , '冀S' , '沧州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 137 , '河北省' , '冀T' , '衡水市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 138 , '天津市' , '津A' , '天津市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 139 , '天津市' , '津B' , '天津市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 140 , '天津市' , '津C' , '天津市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 141 , '山西省' , '晋A' , '太原市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 142 , '山西省' , '晋B' , '大同市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 143 , '山西省' , '晋C' , '阳泉市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 144 , '山西省' , '晋D' , '长治市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 145 , '山西省' , '晋E' , '晋城市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 146 , '山西省' , '晋F' , '朔州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 147 , '山西省' , '晋G' , '雁北地区' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 148 , '山西省' , '晋H' , '忻州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 149 , '山西省' , '晋J' , '吕梁地区' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 150 , '山西省' , '晋K' , '晋中市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 151 , '山西省' , '晋L' , '临汾市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 152 , '山西省' , '晋M' , '运城市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 153 , '北京市' , '京A' , '北京市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 154 , '北京市' , '京B' , '北京市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 155 , '北京市' , '京C' , '北京市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 156 , '北京市' , '京E' , '北京市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 157 , '辽宁省' , '辽A' , '沈阳市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 158 , '辽宁省' , '辽B' , '大连市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 159 , '辽宁省' , '辽C' , '鞍山市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 160 , '辽宁省' , '辽D' , '抚顺市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 161 , '辽宁省' , '辽E' , '本溪市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 162 , '辽宁省' , '辽F' , '丹东市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 163 , '辽宁省' , '辽G' , '锦州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 164 , '辽宁省' , '辽H' , '营口市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 165 , '辽宁省' , '辽J' , '阜新市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 166 , '辽宁省' , '辽K' , '辽阳市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 167 , '辽宁省' , '辽L' , '盘锦市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 168 , '辽宁省' , '辽M' , '铁岭市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 169 , '辽宁省' , '辽N' , '朝阳市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 170 , '辽宁省' , '辽P' , '葫芦岛市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 171 , '山东省' , '鲁A' , '济南市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 172 , '山东省' , '鲁B' , '青岛市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 173 , '山东省' , '鲁C' , '淄博市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 174 , '山东省' , '鲁D' , '枣庄市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 175 , '山东省' , '鲁E' , '东营市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 176 , '山东省' , '鲁F' , '烟台市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 177 , '山东省' , '鲁G' , '潍坊市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 178 , '山东省' , '鲁H' , '济宁市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 179 , '山东省' , '鲁J' , '泰安市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 180 , '山东省' , '鲁K' , '威海市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 181 , '山东省' , '鲁L' , '日照市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 182 , '山东省' , '鲁M' , '滨州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 183 , '山东省' , '鲁N' , '德州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 184 , '山东省' , '鲁P' , '聊城市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 185 , '山东省' , '鲁Q' , '临沂市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 186 , '山东省' , '鲁R' , '荷泽市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 187 , '山东省' , '鲁S' , '莱芜市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 188 , '山东省' , '鲁U' , '青岛市增补' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 189 , '山东省' , '鲁V' , '潍坊市增补' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 190 , '内蒙古' , '蒙A' , '呼和浩特市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 191 , '内蒙古' , '蒙B' , '包头市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 192 , '内蒙古' , '蒙C' , '乌海市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 193 , '内蒙古' , '蒙D' , '赤峰市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 194 , '内蒙古' , '蒙E' , '呼伦贝尔市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 195 , '内蒙古' , '蒙F' , '兴安盟' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 196 , '内蒙古' , '蒙G' , '通辽市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 197 , '内蒙古' , '蒙H' , '锡林郭勒盟' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 198 , '内蒙古' , '蒙J' , '乌兰察布盟' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 199 , '内蒙古' , '蒙K' , '鄂尔多斯市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 200 , '内蒙古' , '蒙L' , '巴彦淖尔盟' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 201 , '内蒙古' , '蒙M' , '阿拉善盟' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 202 , '福建省' , '闽A' , '福州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 203 , '福建省' , '闽B' , '莆田市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 204 , '福建省' , '闽C' , '泉州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 205 , '福建省' , '闽D' , '厦门市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 206 , '福建省' , '闽E' , '漳州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 207 , '福建省' , '闽F' , '龙岩市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 208 , '福建省' , '闽G' , '三明市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 209 , '福建省' , '闽H' , '南平市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 210 , '福建省' , '闽J' , '宁德市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 211 , '福建省' , '闽K' , '省直系统' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 212 , '宁夏' , '宁A' , '银川市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 213 , '宁夏' , '宁B' , '石嘴山市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 214 , '宁夏' , '宁C' , '银南市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 215 , '宁夏' , '宁D' , '固原市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 216 , '宁夏' , '宁E' , '中卫市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 217 , '青海省' , '青A' , '西宁市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 218 , '青海省' , '青B' , '海东市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 219 , '青海省' , '青C' , '海北藏族自治州' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 220 , '青海省' , '青D' , '黄南藏族自治州' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 221 , '青海省' , '青E' , '海南藏族自治州' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 222 , '青海省' , '青F' , '果洛藏族自治州' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 223 , '青海省' , '青G' , '玉树藏族自治州' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 224 , '青海省' , '青H' , '海西蒙古族藏族自治州' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 225 , '海南省' , '琼A' , '海口市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 226 , '海南省' , '琼B' , '三亚市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 227 , '海南省' , '琼C' , '琼海市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 228 , '海南省' , '琼D' , '五指山市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 229 , '海南省' , '琼E' , '洋浦开发区' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 230 , '陕西省' , '陕A' , '西安市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 231 , '陕西省' , '陕B' , '铜川市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 232 , '陕西省' , '陕C' , '宝鸡市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 233 , '陕西省' , '陕D' , '威阳市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 234 , '陕西省' , '陕E' , '渭南市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 235 , '陕西省' , '陕F' , '汉中市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 236 , '陕西省' , '陕G' , '安康市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 237 , '陕西省' , '陕H' , '商洛市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 238 , '陕西省' , '陕J' , '延安市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 239 , '陕西省' , '陕K' , '榆林市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 240 , '陕西省' , '陕V' , '杨凌' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 241 , '江苏省' , '苏A' , '南京市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 242 , '江苏省' , '苏B' , '无锡市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 243 , '江苏省' , '苏C' , '徐州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 244 , '江苏省' , '苏D' , '常州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 245 , '江苏省' , '苏E' , '苏州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 246 , '江苏省' , '苏F' , '南通市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 247 , '江苏省' , '苏G' , '连云港市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 248 , '江苏省' , '苏H' , '淮安市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 249 , '江苏省' , '苏J' , '盐城市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 250 , '江苏省' , '苏K' , '扬州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 251 , '江苏省' , '苏L' , '镇江市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 252 , '江苏省' , '苏M' , '泰州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 253 , '江苏省' , '苏N' , '宿迁市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 254 , '安徽省' , '皖A' , '合肥市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 255 , '安徽省' , '皖B' , '芜湖市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 256 , '安徽省' , '皖C' , '蚌埠市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 257 , '安徽省' , '皖D' , '淮南市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 258 , '安徽省' , '皖E' , '马鞍山市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 259 , '安徽省' , '皖F' , '淮北市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 260 , '安徽省' , '皖G' , '铜陵市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 261 , '安徽省' , '皖H' , '安庆市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 262 , '安徽省' , '皖J' , '黄山市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 263 , '安徽省' , '皖K' , '阜阳市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 264 , '安徽省' , '皖L' , '宿县市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 265 , '安徽省' , '皖M' , '滁州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 266 , '安徽省' , '皖N' , '六安市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 267 , '安徽省' , '皖P' , '宜城市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 268 , '安徽省' , '皖Q' , '巢湖市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 269 , '安徽省' , '皖R' , '池州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 270 , '安徽省' , '皖S' , '亳州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 271 , '湖南省' , '湘A' , '长沙市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 272 , '湖南省' , '湘B' , '株州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 273 , '湖南省' , '湘C' , '湘潭市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 274 , '湖南省' , '湘D' , '衡阳市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 275 , '湖南省' , '湘E' , '邵阳市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 276 , '湖南省' , '湘F' , '岳阳市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 277 , '湖南省' , '湘G' , '张家界市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 278 , '湖南省' , '湘H' , '益阳市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 279 , '湖南省' , '湘J' , '常德市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 280 , '湖南省' , '湘K' , '娄底市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 281 , '湖南省' , '湘L' , '郴州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 282 , '湖南省' , '湘M' , '永州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 283 , '湖南省' , '湘N' , '怀化市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 284 , '湖南省' , '湘U' , '湘西土家族苗族自治州' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 285 , '新疆' , '新A' , '乌鲁木齐市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 286 , '新疆' , '新B' , '昌吉回族自治州' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 287 , '新疆' , '新C' , '石河子市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 288 , '新疆' , '新D' , '奎屯市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 289 , '新疆' , '新E' , '博尔塔拉蒙古自治州' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 290 , '新疆' , '新F' , '伊犁哈萨克自治州' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 291 , '新疆' , '新G' , '塔城市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 292 , '新疆' , '新H' , '阿勒泰市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 293 , '新疆' , '新J' , '克拉玛依市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 294 , '新疆' , '新K' , '吐鲁番市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 295 , '新疆' , '新L' , '哈密市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 296 , '新疆' , '新M' , '巴音郭愣蒙古自治州' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 297 , '新疆' , '新N' , '阿克苏市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 298 , '新疆' , '新P' , '克孜勒苏柯尔克孜自治州' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 299 , '新疆' , '新Q' , '喀什市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 300 , '新疆' , '新R' , '和田市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 301 , '重庆市' , '渝A' , '重庆市区（江南）' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 302 , '重庆市' , '渝B' , '重庆市区（江北）' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 303 , '重庆市' , '渝C' , '永川区' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 304 , '重庆市' , '渝F' , '万州区' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 305 , '重庆市' , '渝G' , '涪陵区' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 306 , '重庆市' , '渝H' , '黔江区' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 307 , '河南省' , '豫A' , '郑州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 308 , '河南省' , '豫B' , '开封市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 309 , '河南省' , '豫C' , '洛阳市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 310 , '河南省' , '豫D' , '平顶山市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 311 , '河南省' , '豫E' , '安阳市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 312 , '河南省' , '豫F' , '鹤壁市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 313 , '河南省' , '豫G' , '新乡市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 314 , '河南省' , '豫H' , '焦作市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 315 , '河南省' , '豫J' , '濮阳市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 316 , '河南省' , '豫K' , '许昌市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 317 , '河南省' , '豫L' , '漯河市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 318 , '河南省' , '豫M' , '三门峡市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 319 , '河南省' , '豫N' , '商丘市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 320 , '河南省' , '豫P' , '周口市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 321 , '河南省' , '豫Q' , '驻马店市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 322 , '河南省' , '豫R' , '南阳市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 323 , '河南省' , '豫S' , '信阳市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 324 , '河南省' , '豫U' , '济源市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 325 , '广东省' , '粤A' , '广州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 326 , '广东省' , '粤B' , '深圳市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 327 , '广东省' , '粤C' , '珠海市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 328 , '广东省' , '粤D' , '汕头市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 329 , '广东省' , '粤E' , '佛山市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 330 , '广东省' , '粤F' , '韶关市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 331 , '广东省' , '粤G' , '湛江市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 332 , '广东省' , '粤H' , '肇庆市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 333 , '广东省' , '粤J' , '江门市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 334 , '广东省' , '粤K' , '茂名市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 335 , '广东省' , '粤L' , '惠州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 336 , '广东省' , '粤M' , '梅州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 337 , '广东省' , '粤N' , '汕尾市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 338 , '广东省' , '粤P' , '河源市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 339 , '广东省' , '粤Q' , '阳江市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 340 , '广东省' , '粤R' , '清远市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 341 , '广东省' , '粤S' , '东莞市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 342 , '广东省' , '粤T' , '中山市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 343 , '广东省' , '粤U' , '潮州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 344 , '广东省' , '粤V' , '揭阳市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 345 , '广东省' , '粤W' , '云浮市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 346 , '广东省' , '粤X' , '顺德市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 347 , '广东省' , '粤Y' , '南海市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 348 , '广东省' , '粤Z' , '港澳进入内地车辆' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 349 , '云南省' , '云A' , '昆明市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 350 , '云南省' , '云A-V' , '东川市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 351 , '云南省' , '云C' , '昭通市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 352 , '云南省' , '云D' , '曲靖市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 353 , '云南省' , '云E' , '楚雄彝族' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 354 , '云南省' , '云F' , '玉溪市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 355 , '云南省' , '云G' , '红河哈尼族彝族自治州' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 356 , '云南省' , '云H' , '文山壮族苗族自治州' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 357 , '云南省' , '云J' , '思茅市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 358 , '云南省' , '云K' , '西双版纳傣族自治州' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 359 , '云南省' , '云L' , '大理白族自治州' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 360 , '云南省' , '云M' , '保山市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 361 , '云南省' , '云N' , '德宏傣族景颇族自治州' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 362 , '云南省' , '云P' , '丽江市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 363 , '云南省' , '云Q' , '怒江僳族自治州' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 364 , '云南省' , '云R' , '迪庆藏族自治州' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 365 , '云南省' , '云S' , '临沧地区' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 366 , '浙江省' , '浙A' , '杭州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 367 , '浙江省' , '浙B' , '宁波市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 368 , '浙江省' , '浙C' , '温州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 369 , '浙江省' , '浙D' , '绍兴市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 370 , '浙江省' , '浙E' , '湖州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 371 , '浙江省' , '浙F' , '嘉兴市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 372 , '浙江省' , '浙G' , '金华市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 373 , '浙江省' , '浙H' , '衢州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 374 , '浙江省' , '浙J' , '台州市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 375 , '浙江省' , '浙K' , '丽水市' )
 INSERT [Pb_CarBeLong] ( [Cid] , [Pro] , [Tb] , [sAdd] ) VALUES ( 376 , '浙江省' , '浙L' , '舟山市' )

 SET IDENTITY_INSERT [Pb_CarBeLong] OFF



-- |**************************************************************************************|
-- |******************************| 新增表字段(begin) |***********************************|
-- |**************************************************************************************|

/*示例
if not exists(select * from syscolumns where id=object_id('dbo.表名') and name='字段名')  --判断字段不存在则增加
	ALTER TABLE 表名 ADD 字段名 nvarchar(20) NULL
*/


-- |**************************************************************************************|
-- |******************************| 新增表字段(end) |*************************************|
-- |**************************************************************************************|




-- |**************************************************************************************|
-- |*******************************| 创建触发器(begin) |**********************************|
-- |**************************************************************************************|



-- ============================================= 
-- 程序编写： <谭飞> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <部门表Insert、Update触发器，部门编码、名称不能重复> 
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
if(OBJECT_ID('tgr_Per_Dept_insert','tr') is not null)
	drop trigger tgr_Per_Dept_insert
go
create TRIGGER [dbo].[tgr_Per_Dept_insert] 
ON [dbo].[Per_Dept] 
for INSERT,UPDATE
AS

if ( UPDATE([DeptID]) or update([DeptName]) )
Begin 
	if exists (Select [Per_Dept].[AutoID] from [Per_Dept],Inserted where [Per_Dept].[AutoID]<>inserted.[AutoID] and [Per_Dept].[DeptID] =Inserted.[DeptID]) 
	begin 
	  --Select * from [Per_Dept],Inserted where [Per_Dept].[DeptID] =Inserted.[DeptID]
		RAISERROR ('部门编码重复!', 16, 1)
		rollback
	end 
	if exists (Select [Per_Dept].[AutoID] from [Per_Dept],Inserted where [Per_Dept].[AutoID]<>inserted.[AutoID] and [Per_Dept].[DeptName] =Inserted.[DeptName]) 
	begin
		RAISERROR ('部门名称重复!', 16, 1)
		rollback
	end   
End
go

-- ============================================= 
-- 程序编写： <谭飞> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <人员信息[Per_Persons]触发器， 编号不能重复 > 
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
if(OBJECT_ID('tgr_Per_Persons_insert','tr') is not null)
	drop trigger tgr_Per_Persons_insert
go
create TRIGGER tgr_Per_Persons_insert 
ON Per_Persons 
for INSERT,UPDATE
AS
 if ( UPDATE(PerID))
Begin 
	if exists (Select Per_Persons.PerID from Per_Persons,Inserted where Per_Persons.id<>inserted.id and Per_Persons.PerID =Inserted.PerID) 
	begin 
 		RAISERROR ('职员编码重复!', 16, 1)
		rollback
	end  
End
GO	


-- ============================================= 
-- 程序编写： <谭飞> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场权限级别触发器， 级别名称不能重复 > 
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================  
if(OBJECT_ID('tgr_Park_ControlPlan_insert','tr') is not null)
	drop trigger tgr_Park_ControlPlan_insert
go
create TRIGGER [dbo].tgr_Park_ControlPlan_insert
ON [dbo].Park_ControlPlan 
for INSERT,UPDATE
AS

if ( UPDATE(PlanName))
Begin  
	if exists (Select Park_ControlPlan.PlanID from Park_ControlPlan,Inserted where Park_ControlPlan.PlanID<>inserted.PlanID and Park_ControlPlan.PlanName =Inserted.PlanName) 
	begin
		RAISERROR ('该级别名称已经被使用!', 16, 1)
		rollback
	end   
End

go
 
-- |**************************************************************************************|
-- |*******************************| 创建触发器(end) |************************************|
-- |**************************************************************************************|



-- |**************************************************************************************|
-- |******************************| 新增索引(begin) |*************************************|
-- |**************************************************************************************|


GO
IF not exists(select 1 from sys.indexes where name='Idx_PKCarOut' and [object_id] = OBJECT_ID('[dbo].[Park_CarOut]'))
begin
	CREATE NONCLUSTERED INDEX [Idx_PKCarOut] ON [dbo].[Park_CarOut] 
	(
		[ID] ASC
	)
end
GO
IF not exists(select 1 from sys.indexes where name='Idx_PKCarOut_1' and [object_id] = OBJECT_ID('[dbo].[Park_CarOut]'))
BEGIN
	CREATE NONCLUSTERED INDEX [Idx_PKCarOut_1] ON [dbo].[Park_CarOut] 
	(
		[CardID] ASC,
		[InTime] DESC
	)
end
GO
IF not exists(select 1 from sys.indexes where name='Idx_PKCarOut_2' and [object_id] = OBJECT_ID('[dbo].[Park_CarOut]'))
begin
	CREATE NONCLUSTERED INDEX [Idx_PKCarOut_2] ON [dbo].[Park_CarOut] 
	(
		[CardID] ASC,
		[OutTime] DESC
	)
end
GO
IF not exists(select 1 from sys.indexes where name='Idx_PKCarOut_3' and [object_id] = OBJECT_ID('[dbo].[Park_CarOut]'))
begin
	CREATE NONCLUSTERED INDEX [Idx_PKCarOut_3] ON [dbo].[Park_CarOut] 
	(
		[CardNo] ASC,
		[OutTime] DESC
	)
end
GO
IF not exists(select 1 from sys.indexes where name='Idx_PKCarOut_4' and [object_id] = OBJECT_ID('[dbo].[Park_CarOut]'))
begin
	CREATE NONCLUSTERED INDEX [Idx_PKCarOut_4] ON [dbo].[Park_CarOut] 
	(
		[CardType] ASC,
		[CarNO] ASC
	)
end
GO
IF not exists(select 1 from sys.indexes where name='Idx_PKCarOut_5' and [object_id] = OBJECT_ID('[dbo].[Park_CarOut]'))
begin
	CREATE NONCLUSTERED INDEX [Idx_PKCarOut_5] ON [dbo].[Park_CarOut] 
	(
		[FreeType] ASC
	)
end
GO
IF not exists(select 1 from sys.indexes where name='Idx_PKCarOut_6' and [object_id] = OBJECT_ID('[dbo].[Park_CarOut]'))
begin
	CREATE NONCLUSTERED INDEX [Idx_PKCarOut_6] ON [dbo].[Park_CarOut] 
	(
		[TypeID] ASC
	)
end
GO
IF not exists(select 1 from sys.indexes where name='ContID_index' and [object_id] = OBJECT_ID('[dbo].[Door_OpenDoorRecord]'))
	CREATE INDEX ContID_index ON dbo.Door_OpenDoorRecord (ContID)
GO
IF not exists(select 1 from sys.indexes where name='DoorID_index' and [object_id] = OBJECT_ID('[dbo].[Door_OpenDoorRecord]'))
	CREATE INDEX DoorID_index ON dbo.Door_OpenDoorRecord (DoorID)
GO
IF not exists(select 1 from sys.indexes where name='EventTime_index' and [object_id] = OBJECT_ID('[dbo].[Door_OpenDoorRecord]'))
	CREATE INDEX EventTime_index ON dbo.Door_OpenDoorRecord (EventTime)
GO
IF not exists(select 1 from sys.indexes where name='ContID_index' and [object_id] = OBJECT_ID('[dbo].[Door_OpenDoorRecordTwo]'))
	CREATE INDEX ContID_index ON dbo.Door_OpenDoorRecordTwo (ContID)
GO
IF not exists(select 1 from sys.indexes where name='DoorID_index' and [object_id] = OBJECT_ID('[dbo].[Door_OpenDoorRecordTwo]'))
	CREATE INDEX DoorID_index ON dbo.Door_OpenDoorRecordTwo (DoorID)
GO
IF not exists(select 1 from sys.indexes where name='EventTime_index' and [object_id] = OBJECT_ID('[dbo].[Door_OpenDoorRecordTwo]'))
	CREATE INDEX EventTime_index ON dbo.Door_OpenDoorRecordTwo (EventTime)
GO	
IF not exists(select 1 from sys.indexes where name='YKTID_index' and [object_id] = OBJECT_ID('[dbo].[Ykt_CardIssueDoor]'))
	CREATE INDEX YKTID_index ON dbo.Ykt_CardIssueDoor (YKTID)
GO
IF not exists(select 1 from sys.indexes where name='id_index' and [object_id] = OBJECT_ID('[dbo].[Ykt_CardIssue]'))
	CREATE INDEX id_index ON dbo.Ykt_CardIssue (id)
GO
IF not exists(select 1 from sys.indexes where name='YKTID_index' and [object_id] = OBJECT_ID('[dbo].[Ykt_CardIssue]'))
	CREATE INDEX YKTID_index ON dbo.Ykt_CardIssue (YKTID)
GO

-- |**************************************************************************************|
-- |******************************| 新增索引(end) |***************************************|
-- |**************************************************************************************|





-- |**************************************************************************************|
-- |******************************| 新增函数(begin) |*************************************|
-- |**************************************************************************************|

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <查询各节点的父路径函数(从子到父)>
-- 调用示例： <SELECT dbo.[f_ykt_GetDeptPath](@deptid) > 
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID ('dbo.f_ykt_GetDeptPath') IS NOT NULL
	DROP FUNCTION dbo.f_ykt_GetDeptPath
GO
	create function f_ykt_GetDeptPath(@deptid varchar(10)) returns varchar(100)
	as
	begin
	  declare @re_str as varchar(100)
	  set @re_str = ''
	  select @re_str = DeptName from Per_Dept where DeptID = @deptid
	  while exists (select 1 from Per_Dept where DeptID = @deptid and TopDeptID <> '0')
		begin
		  select @deptid = b.DeptID , @re_str = b.DeptName + '/' + @re_str from Per_Dept a , Per_Dept b where a.DeptID = @deptid and a.TopDeptID = b.DeptID
		end
	  return @re_str
	end
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <根据参数@iType返回对应的权限>
-- 调用示例： <SELECT dbo.[Sys_GetGroupRight](@iType) > 
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID ('dbo.Sys_GetGroupRight') IS NOT NULL
	DROP FUNCTION dbo.Sys_GetGroupRight
GO
	CREATE Function [dbo].[Sys_GetGroupRight](@iType tinyint)
	Returns Varchar(500)	 
	as
	Begin
		Return case @iType
			when 0 then REPLICATE('0', 500)
			when 1 then REPLICATE('0', 500)
			when 2 then REPLICATE('1', 500)
				   else REPLICATE('0', 500)
			end
	End
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <未知>
-- 调用示例： <SELECT dbo.[Ykt_GETMMM](@Amount,@Charge,@LastM) > 
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID ('dbo.Ykt_GETMMM') IS NOT NULL
	DROP FUNCTION dbo.Ykt_GETMMM
GO
	Create Function [dbo].[Ykt_GETMMM](@Amount Money,@Charge Money,@LastM Tinyint)
	Returns Varchar(12)                  --实收金额    月租    有效结束日期     
	 
	as
	Begin
		Declare @TTT varchar(12)
		Declare @DiffMMM int --间隔月份
		if @Charge>0
			Begin
				Select @DiffMMM=Abs(Convert(int,@Amount/@Charge))
				if @DiffMMM=0 and @Amount<>0
						Select @DiffMMM=1
				if @DiffMMM=0 
						Select @TTT='000000000000'
				else
					Begin
						if @DiffMMM>12
							Select @TTT='111111111111'
						else
						Begin
							if @LastM>=@DiffMMM
								Select @TTT=Replace(Space(@LastM-@DiffMMM)+SubString('111111111111',@LastM-@DiffMMM+1,@DiffMMM)+Space(12-@LastM),Space(1),'0')
							Else
								Select @TTT=Left('111111111111',@LastM) +Left('000000000000',12-@DiffMMM) + Right('111111111111',@DiffMMM-@LastM)
						end
					end
			end
		else
				Select @TTT='000000000000'
		Return @TTT
	End
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <返回状态标识对应的说明>
-- 调用示例： <SELECT dbo.[Park_GetCardStates](@States) > 
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID ('dbo.Park_GetCardStates') IS NOT NULL
	DROP FUNCTION dbo.Park_GetCardStates
GO
	CREATE Function [dbo].[Park_GetCardStates](@States tinyint)
	Returns varchar(10)
	 
	as
	Begin
		declare @strState as varchar(10)
		Select @strState=
			case @States
			when 0 then '正常'
			when 1 then '正在挂失'
			when 2 then '已挂失' 
			when 3 then '正在解挂'
			when 4 then '已补卡'
			when 5 then '挂失退款'
			when 6 then '已销户'
			else ''
			end

		Return @strState
	End
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场收费计算出入场时间差>
-- 调用示例： <SELECT dbo.[Park_FeeDayM](@InDate,@OutDate,@StartPeriod,@EndPeriod) > 
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID ('dbo.Park_FeeDayM') IS NOT NULL
	DROP FUNCTION dbo.Park_FeeDayM
GO
	CREATE Function [dbo].[Park_FeeDayM] (@InDate smallDatetime,@OutDate smallDatetime,@StartPeriod smallDatetime,@EndPeriod smallDatetime)
	Returns int 
	 
	as
	Begin
		Declare @TmpSPeriod SmallDatetime
		Declare @TmpEPeriod SmallDatetime
		Declare @MM int
		if @OutDate<=@InDate return 0
		if @EndPeriod<=@StartPeriod return 0
		Select @TmpSPeriod=@StartPeriod , @TmpEPeriod=@EndPeriod
			Set @MM=Case 
			WHEN  @InDate<=@TmpSPeriod and @OutDate>=@TmpEPeriod  then  datediff(mi,@TmpSPeriod,@TmpEPeriod)
			WHEN  @TmpSPeriod<=@InDate and @TmpEPeriod>=@OutDate then  datediff(mi,@InDate,@OutDate)
			WHEN  @InDate<@TmpSPeriod and @OutDate<@TmpEPeriod and @OutDate>@TmpSPeriod then datediff(mi,@TmpSPeriod,@OutDate)
			WHEN   @InDate>@TmpSPeriod and @InDate<@TmpEPeriod and  @OutDate>@TmpEPeriod then datediff(mi,@InDate,@TmpEPeriod)
				Else  0
						END	
		return @MM

	End
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场-检查下载状态>
-- 调用示例： <SELECT dbo.[Park_CheckLoadStates](@LoadStates,@LocalStates) > 
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID ('dbo.Park_CheckLoadStates') IS NOT NULL
	DROP FUNCTION dbo.Park_CheckLoadStates
GO
	CREATE Function [dbo].[Park_CheckLoadStates](@LoadStates varchar(255),@LocalStates varchar(255))
	Returns tinyint
	 
	as
	Begin
		declare @i as int
		Declare @R as tinyint
		select @i=1,@R=0
		Select @LoadStates=Left(@LoadStates+ REPLICATE('0', 255),255)
		while @i<=255
				begin
			if substring(@LocalStates,@i,1)='1'
				Begin
					if substring(@LoadStates,@i,1)='0'
						Begin
							Select @R=1
								BREAK
						end
					else
							Select @R=0					
				end
			Select @i=@i+1
			end 	
		Return @R
	End
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <字符串左侧填充指定字符>
-- 调用示例： <SELECT dbo.[PadLeft](@num,@paddingChar,@totalWidth) > 
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID ('dbo.PadLeft') IS NOT NULL
	DROP FUNCTION dbo.PadLeft
GO
	CREATE FUNCTION [dbo].[PadLeft](@num varchar(16),@paddingChar char(1),@totalWidth int)
	returns varchar(1000) as
	begin
	declare @curStr varchar(1000)
	select @curStr = isnull(replicate(@paddingChar,@totalWidth - len(isnull(@num ,0))), '') + @num
	return @curStr
	end
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <转16进制>
-- 调用示例： <SELECT dbo.[DecToHex](@tnDec) > 
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID ('dbo.DecToHex') IS NOT NULL
	DROP FUNCTION dbo.DecToHex
GO
	Create FUNCTION [dbo].[DecToHex] (@tnDec bigint)
		RETURNS varchar(10) 
		 
	AS
	begin
	declare @lcHex varchar(20),@lnCurNum bigint,@lcCurChr varchar(10),@i int
	select @lcHex=''
	select @i=1
	while @i=1
	begin
		select @lnCurNum=@tnDec%16 
		if @lnCurNum > 9  
			select @lcCurChr = char(@lnCurNum + 55)  
		else  
			select @lcCurChr = ltrim(rtrim(convert(char(1),@lnCurNum)))
		select @lcHex=ltrim(rtrim(@lcCurChr)) + ltrim(rtrim(@lcHex))  
		select @tnDec=@tnDec/16  
		if @tnDec <= 0  
			select @i=0
	end  
	return (ltrim(rtrim(@lcHex)))
	end
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <2进制转16进制>
-- 调用示例： <SELECT dbo.[BinToHex](@strbin) > 
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID ('dbo.BinToHex') IS NOT NULL
	DROP FUNCTION dbo.BinToHex
GO
	Create  FUNCTION [dbo].[BinToHex] (@strbin varchar(8000))
	RETURNS varchar(2000) 
	 
	AS
	begin
		declare @strtemp as varchar(1)
		declare @strHex as varchar(2000)
		declare @i as smallint
		declare @K as smallint

		set @strHex=''
		select @i=1,@K=0
		while @i<=len(@strbin)
			begin
			set @strtemp=
			case substring(@strbin,@i,4)
			when '0000' then '0'
			when '0001' then '1'
			when '0010' then '2' 
			when '0011' then '3'
			when '0100' then '4'
			when '0101' then '5'
			when '0110' then '6'
			when '0111' then '7'
			when '1000' then '8'
			when '1001' then '9'
			when '1010' then 'A'
			when '1011' then 'B'
			when '1100' then 'C'
			when '1101' then 'D'
			when '1110' then 'E'
			when '1111' then 'F'
			end
			set @strHex=@strHex+@strtemp
			Set @K=@K+1	
			set @i=@K*4+1
			end
		return (@strHex)
	end
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <未知>
-- 调用示例： <SELECT dbo.[Park_Fee24HC](@InDate,@OutDate,@StartPeriod,@EndPeriod) > 
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID ('dbo.Park_Fee24HC') IS NOT NULL
	DROP FUNCTION dbo.Park_Fee24HC
GO
	CREATE  Function [dbo].[Park_Fee24HC](@InDate varchar(16),@OutDate varchar(16),@StartPeriod varchar(5),@EndPeriod Varchar(5))
	Returns int 
	 
	as
	Begin
		Declare @InOne  tinyint
		Declare @KK tinyint
		if Convert(varchar(10),@OutDate,120)>Convert(varchar(10),@InDate,120)
				Select @InOne=1  --跨天
		else
				Select @InOne=0	

			Declare @TmpInDate varchar(16)
			Declare @TmpOutDate varchar(16)
			Declare @TmpSPeriod  varchar(16)
			Declare @TmpEPeriod varchar(16)

			Select @KK=0
			if  @InOne=0  --不跨天
			Begin
				Select @TmpInDate=right(Convert(varchar(16),@InDate,120),5) -- 16 22:01
				Select @TmpOutDate=right(Convert(varchar(16),@OutDate,120),5)-- 17 22:00
				if @StartPeriod>@EndPeriod --跨天**********
				Begin
					Select @KK=Case
						when @TmpInDate>=@EndPeriod and @TmpOutDate<=@StartPeriod 
						then 0
						when  @TmpInDate<@EndPeriod and @TmpOutDate>@StartPeriod 
						then 2
						else 1 end
				end
				else
					if  (@TmpInDate>=@StartPeriod and @TmpInDate<@EndPeriod) or (@TmpOutDate>@StartPeriod and @TmpOutDate<=@EndPeriod)
					Select @KK=1
			end
		else
				if @StartPeriod>@EndPeriod --跨天
				Begin
					Select @TmpInDate=Convert(varchar(16),@InDate,120) -- 16 22:01
					Select @TmpOutDate=Convert(varchar(16),@OutDate,120) -- 17 22:00

					Select @TmpSPeriod=Convert(varchar(11),@InDate,120) + @StartPeriod --16 22:00
					Select @TmpEPeriod=Convert(varchar(11),@InDate,120) + @EndPeriod   --16 08:00

					if @TmpInDate<@TmpEPeriod and @TmpOutDate>=@TmpSPeriod 
					Select @KK=2
					else 
					Begin
	  					Select @TmpSPeriod=Convert(varchar(11),@OutDate,120) + @StartPeriod --17 22:00
							Select @TmpEPeriod=Convert(varchar(11),@OutDate,120) + @EndPeriod   --17 08:00
						if @TmpInDate<=@TmpEPeriod and @TmpOutDate>@TmpSPeriod 
							Select @KK=2										
						else
							Select @KK=1
					end
				end
				else
				Begin
					Select @TmpInDate=Convert(varchar(16),@InDate,120)	-- 16 22:01
					Select @TmpOutDate=Convert(varchar(16),@OutDate,120)	-- 17 22:00

					Select @TmpSPeriod=Convert(varchar(11),@InDate,120) + @StartPeriod -- 16 08:00
					Select @TmpEPeriod=Convert(varchar(11),@InDate,120) + @EndPeriod   -- 16 22:00

					if  (@TmpInDate>=@TmpSPeriod and @TmpInDate<@TmpEPeriod) or (@TmpInDate<@TmpSPeriod and @TmpOutDate>@TmpEPeriod)
						select @KK=@KK+1

					Select @TmpSPeriod=Convert(varchar(11),@OutDate,120) + @StartPeriod -- 17 08:00
					Select @TmpEPeriod=Convert(varchar(11),@OutDate,120) + @EndPeriod   -- 17 22:00

					if  (@TmpOutDate>@TmpSPeriod and @TmpOutDate<=@TmpEPeriod)  or (@TmpInDate<@TmpSPeriod and @TmpOutDate>@TmpEPeriod)
					select @KK=@KK+1
				end
		Return @KK
	End
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <未知>
-- 调用示例： <SELECT dbo.[ParkFeeDayM](@InDate,@OutDate,@StartPeriod,@EndPeriod) > 
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID ('dbo.ParkFeeDayM') IS NOT NULL
	DROP FUNCTION dbo.ParkFeeDayM
GO
	CREATE Function [dbo].[ParkFeeDayM] (@InDate smallDatetime,@OutDate smallDatetime,@StartPeriod smallDatetime,@EndPeriod smallDatetime)
	Returns int 
	 
	as
	Begin
			Declare @TmpSPeriod SmallDatetime
			Declare @TmpEPeriod SmallDatetime
			Declare @MM int
		if @OutDate<=@InDate return 0
		if @EndPeriod<=@StartPeriod return 0
		Select @TmpSPeriod=@StartPeriod , @TmpEPeriod=@EndPeriod

			Set @MM=Case 
			WHEN  @InDate<=@TmpSPeriod and @OutDate>=@TmpEPeriod  then  datediff(mi,@TmpSPeriod,@TmpEPeriod)
			WHEN  @TmpSPeriod<=@InDate and @TmpEPeriod>=@OutDate then  datediff(mi,@InDate,@OutDate)
			WHEN  @InDate<@TmpSPeriod and @OutDate<@TmpEPeriod and @OutDate>@TmpSPeriod then datediff(mi,@TmpSPeriod,@OutDate)
			WHEN   @InDate>@TmpSPeriod and @InDate<@TmpEPeriod and  @OutDate>@TmpEPeriod then datediff(mi,@InDate,@TmpEPeriod)
				Else  0
						END	
		return @MM

	End
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <获取收费金额>
-- 调用示例： <SELECT dbo.[Park_GetMoney](@CardType,@InDate,@OutDate) > 
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID ('dbo.Park_GetMoney') IS NOT NULL
	DROP FUNCTION dbo.Park_GetMoney
GO
	CREATE Function [dbo].[Park_GetMoney](@CardType Integer,@InDate Datetime,@OutDate Datetime)
	Returns Money 
	 
	AS	--收费金额
	begin
		Declare @InTime as datetime
		declare @OutTime as datetime
		declare @ParkDates as integer		--停车天数
		declare @ParkMinis as integer		--停车分钟数
		declare @FreeMinute as integer   		--免费分钟数
		Declare @FreeInclude as tinyint 	--免费分钟不包含
		declare @ChargeReclcType as tinyint 		--循环周期
		declare @ChargeReclc as int 		--循环周期
		declare @TopAmount as money 		--最高收费
		declare @IsTimeAdd as integer		--跨段是否补时间
		declare @ManyOneTime as integer  	--24小时后是否还有首段
		Declare @ChargingLength1 as int		--首段时长
		Declare @ChargingUnit1 as integer		--收费单位
		Declare @ChargingAmount1 as money	--收费金额
		Declare @ChargingLength2 as integer		--二段时长
		Declare @ChargingUnit2 as integer		--收费单位
		Declare @ChargingAmount2 as money	--收费金额
		Declare @ChargingLength3 as integer		--三段时长
		Declare @ChargingUnit3 as integer		--收费单位
		Declare @ChargingAmount3 as money	--收费金额
		Declare @LastUnit as integer		--最后一段收费单位
		Declare @LaseAmount as money		--收费金额
		Declare @ChargeCardType as integer	--计费卡类
		Declare @ToDayMoney as money		--当天收费金额
		Declare @Amount money 			--收费金额
		select @Amount=0,@ParkMinis=0,@ParkDates=0,@ToDayMoney=0
		--if @CardType>39 or @CardType<30 --只有临时卡收费
		if @CardType<30 or (@CardType>39 and @CardType<50) or  @CardType>59  --卡类只有临时卡和储值卡
			return @Amount
		Select @ChargeCardType=@CardType
		select @InTime=@InDate,@OutTime=@OutDate
		--取数据（收费标准一）
		if not Exists(Select 1 from [Park_ESMain] where ESNo=1)
			return @Amount
		if not Exists(Select 1 from [Park_Es2] where CardType=@ChargeCardType)
			return @Amount
		Select @FreeMinute=Isnull(FreeMinute,0),@FreeInclude=Isnull(FreeInclude,0),@IsTimeAdd=Isnull(IsTimeAdd,0),@ManyOneTime=isnull(ReCalc,0) from [Park_ESMain] where ESNo=1 and CardType=@ChargeCardType --通用标准一
		select  @ChargeReclcType=ChargeReclHours,@ChargingLength1=ChLength1,@ChargingLength2=ChLength2,@ChargingLength3=ChLength3,
			@ChargingUnit1=ChUnit1,@ChargingUnit2=ChUnit2,@ChargingUnit3=ChUnit3,@ChargingAmount1=ChAmount1,@ChargingAmount2=ChAmount2,@ChargingAmount3=ChAmount3,
			@LastUnit=LastUnit,@LaseAmount=LaseAmount,@TopAmount=HighAmount
		from [Park_Es2] where CardType=@ChargeCardType
		--收费单位作除数不能为0
		if @ChargingUnit1=0 select @ChargingUnit1=60
		if @ChargingUnit2=0 select @ChargingUnit2=60
		if @ChargingUnit3=0 select @ChargingUnit3=60
		if @LastUnit=0 select @LastUnit=60
		if @ChargeReclcType=0 
			set @ChargeReclc=24
		else
			set @ChargeReclc=12
		select @ParkMinis=datediff(minute,@InTime,@OutTime)
		if @TopAmount>0 
			Begin
				Select @ParkDates=0,@ToDayMoney=0
				Select @ParkDates=@ParkMinis / (@ChargeReclc *60)
				Select @ParkMinis= @ParkMinis % (@ChargeReclc *60)  --剩余分钟数
				Select @Amount=@TopAmount*@ParkDates
				if @ParkMinis<=0 
					return @Amount
				--if @ParkMinis<=@FreeMinute return	--重新按每天计费
			end 
		if  @ParkDates>0 and  @ManyOneTime=0 --有最高收费 并且24小时后无首段
			begin
				if  @LastUnit>0
					Begin
						if @ParkMinis-@LastUnit>0
						Select @ToDayMoney=Ceiling(Cast((@ParkMinis) as Money)/@LastUnit)*@LaseAmount
						else
						Select @ToDayMoney=@LaseAmount
	    					if @ToDayMoney>@TopAmount and @TopAmount>0
							Select @Amount=@Amount + @TopAmount
						else
							Select @Amount=@Amount+@ToDayMoney	
					end	
				return	@Amount
			end
		--停车时间<1440分钟或无最高收费
		if @ChargingLength1>0 and @ChargingUnit1>0
			begin		
					if @ParkMinis-@ChargingUnit1>0 
						Begin
							if @ParkMinis-@ChargingLength1>0
							Begin
								Select @ToDayMoney=@ToDayMoney+Ceiling(Cast((@ChargingLength1) as Money)/@ChargingUnit1)*@ChargingAmount1
								Select @ParkMinis=@ParkMinis-@ChargingLength1
							end
				
							else
							Begin
								Select @ToDayMoney=@ToDayMoney+Ceiling(Cast((@ParkMinis) as Money)/@ChargingUnit1)*@ChargingAmount1
								if @ToDayMoney>@TopAmount and @TopAmount>0
									Select @Amount=@Amount+@TopAmount
								else
									Select @Amount=@Amount+@ToDayMoney
								Return @Amount
							end
						end
					else
						Begin
							Select @ToDayMoney=@ToDayMoney+@ChargingAmount1
							if @ToDayMoney>@TopAmount and @TopAmount>0
								Select @Amount=@Amount+@TopAmount
							else
								Select @Amount=@Amount+@ToDayMoney
							Return @Amount
						end
			end
		if @ChargingLength2>0 and @ChargingUnit2>0 
			Begin
				if @ParkMinis-@ChargingUnit2>0 
				Begin
					if @ParkMinis-@ChargingLength2>0
					Begin
						Select @ToDayMoney=@ToDayMoney+Ceiling(Cast((@ChargingLength2) as Money)/@ChargingUnit2)*@ChargingAmount2
						Select @ParkMinis=@ParkMinis-@ChargingLength2
					end
		
					else
					Begin
						Select @ToDayMoney=@ToDayMoney+Ceiling(Cast((@ParkMinis) as Money)/@ChargingUnit2)*@ChargingAmount2
						if @ToDayMoney>@TopAmount and @TopAmount>0
							Select @Amount=@Amount+@TopAmount
						else
							Select @Amount=@Amount+@ToDayMoney
						Return @Amount
					end
				end
				else
				Begin
					Select @ToDayMoney=@ToDayMoney+@ChargingAmount2
					if @ToDayMoney>@TopAmount and @TopAmount>0
						Select @Amount=@Amount+@TopAmount
					else
						Select @Amount=@Amount+@ToDayMoney
					Return @Amount
				end
			end

		if @ChargingLength3>0 and @ChargingUnit3>0 
			Begin
				if @ParkMinis-@ChargingUnit3>0 
				Begin
					if @ParkMinis-@ChargingLength3>0
					Begin
						Select @ToDayMoney=@ToDayMoney+Ceiling(Cast((@ChargingLength3) as Money)/@ChargingUnit3)*@ChargingAmount3
						Select @ParkMinis=@ParkMinis-@ChargingLength3
					end
		
					else
					Begin
						Select @ToDayMoney=@ToDayMoney+Ceiling(Cast((@ParkMinis) as Money)/@ChargingUnit3)*@ChargingAmount3
						if @ToDayMoney>@TopAmount and @TopAmount>0
							Select @Amount=@Amount+@TopAmount
						else
							Select @Amount=@Amount+@ToDayMoney
						Return @Amount
					end
				end
				else
				Begin
					Select @ToDayMoney=@ToDayMoney+@ChargingAmount3
					if @ToDayMoney>@TopAmount and @TopAmount>0
						Select @Amount=@Amount+@TopAmount
					else
						Select @Amount=@Amount+@ToDayMoney
					Return @Amount
				end
			end
		if  @LastUnit>0
			Begin
				if @ParkMinis-@LastUnit>0
				Select @ToDayMoney=@ToDayMoney+Ceiling(Cast((@ParkMinis) as Money)/@LastUnit)*@LaseAmount
				else
				Select @ToDayMoney=@ToDayMoney+@LaseAmount
			end

		if @ToDayMoney>@TopAmount and @TopAmount>0
			Select @ToDayMoney=@TopAmount
		Select @Amount=@Amount+@ToDayMoney
		Return @Amount
	end
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场-获取使用状态>
-- 调用示例： <SELECT dbo.[Park_DayState](@startdate,@EndDate) > 
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID ('dbo.Park_DayState') IS NOT NULL
	DROP FUNCTION dbo.Park_DayState
GO
	CREATE  FUNCTION [dbo].[Park_DayState] (@startdate datetime,@EndDate Datetime)
	RETURNS nvarchar(5)
	 
	AS
	begin
		declare @nowdate datetime;
		select @nowdate = GETDATE();
		if @nowdate<@startdate 
			Return '未到使用期'
		if Convert(Varchar(10),@nowdate,120)>Convert(Varchar(10),@enddate,120) 
			Return '已过期'
		declare @OverDays as int
		declare @HaveDays as int
				if exists(Select * from Sys_Parameters where ParameterName='固定卡可过期天数')
				Select @OverDays=(Select CAST( ParameterValue as int) from Sys_Parameters where ParameterName='固定卡可过期天数')
		Select @OverDays=isnull(@OverDays,0)
		if @OverDays=0 Select @OverDays=7
		Select @HaveDays=datediff(day,@nowdate,@EndDate)+1
		if @HaveDays>0 and @HaveDays<@OverDays
			Return '即将过期'
		Return '正常'
	end
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <二进制转16进制>
-- 调用示例： <SELECT dbo.[Varbin2hexstr](@bin) > 
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID ('dbo.Varbin2hexstr') IS NOT NULL
	DROP FUNCTION dbo.Varbin2hexstr
GO
	CREATE FUNCTION Varbin2hexstr(
	@bin varbinary(8000)
	)returns varchar(8000)
	as
	begin
		declare @re varchar(8000),@i int
		select @re='',@i=datalength(@bin)
		while @i>0
			select @re=substring('0123456789ABCDEF',substring(@bin,@i,1)/16+1,1)
					+substring('0123456789ABCDEF',substring(@bin,@i,1)%16+1,1)
					+@re
				,@i=@i-1
		-- return('0x'+@re)
		return @re
	end
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <10进制转16进制字符串>
-- 调用示例： <SELECT dbo.[Inttohex](@num) > 
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID ('dbo.Inttohex') IS NOT NULL
	DROP FUNCTION dbo.Inttohex
GO
	CREATE function [dbo].[Inttohex](@num bigint)  
	returns varchar(16)   
	begin  
		declare @num2 varbinary(8),@r varchar(50) 
		set @num2=convert(varbinary(8),@num)--直接转换为二进制 
		set @r= dbo.varbin2hexstr(@num2)--二进制转16进制字符串 
		return @r  
	end  
GO  
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <未知>
-- 调用示例： <SELECT dbo.[Park_Fee24HM](@InDate,@OutDate,@StartPeriod,@EndPeriod) > 
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID ('dbo.Park_Fee24HM') IS NOT NULL
	DROP FUNCTION dbo.Park_Fee24HM
GO
	CREATE Function [dbo].[Park_Fee24HM](@InDate smallDatetime,@OutDate smallDatetime,@StartPeriod  Varchar(8),@EndPeriod  Varchar(8))
	Returns int 
	 
	as
	Begin
		Declare  @mi1  int 
		Declare  @mi2  int 
		Declare  @mi3  int 
		Declare  @mi4  int 
		Declare @TmpSPeriod SmallDatetime
		Declare @TmpEPeriod SmallDatetime
		Declare @MidDate1 SmallDatetime
		Declare @MidDate2 SmallDatetime
		Declare @InOne  tinyint
			/*传递参数错误！！注意是24小时内 */
		if @OutDate=@InDate return 0
		if @EndPeriod=@StartPeriod return 0
		Select  @mi1=0,@mi2=0,@mi3=0,@mi4=0
		Select @MidDate1=Cast((Convert(varchar(10),@InDate,120) + ' 23:59:59') as smallDatetime)
		Select @MidDate2=Cast((Convert(varchar(10),@OutDate,120) + ' 00:00:00') as smallDatetime)

		if Convert(varchar(10),@OutDate,120)>Convert(varchar(10),@InDate,120)
				Select @InOne=1
		else
				Select @InOne=0	
		if @EndPeriod<@StartPeriod  --跨天
			Begin
				Select @TmpSPeriod=Cast((Convert(varchar(11),@InDate,120) + @StartPeriod) as smallDatetime)
				Select  @TmpEPeriod=Cast((Convert(varchar(11),Dateadd(dd,1,@InDate),120) + @EndPeriod) as smallDatetime)
				if @InOne=1
					Begin

						Select @mi1=dbo.Park_feeDayM(@InDate,@MidDate1,@TmpSPeriod,@MidDate1)
						Select @mi2=dbo.Park_feeDayM(@MidDate2,@OutDate,@MidDate2,@TmpEPeriod)

						Select @mi3=dbo.Park_feeDayM(@InDate,@MidDate1,
							Cast((Convert(varchar(10),@InDate,120) + ' 00:00:00') as smallDatetime),Cast((Convert(varchar(11),@InDate,120) +@EndPeriod) as smallDatetime))
						Select @mi4=dbo.Park_feeDayM(@MidDate2,@OutDate,
							Cast((Convert(varchar(11),@OutDate,120) + @StartPeriod) as smallDatetime),Cast((Convert(varchar(10),@OutDate,120) + ' 23:59:59') as smallDatetime))
					end
				else
					Begin
						Select @MidDate1=Cast((Convert(varchar(10),@InDate,120) + ' 23:59:59') as smallDatetime)
						Select @TmpSPeriod=Cast((Convert(varchar(11),@InDate,120) + @StartPeriod) as smallDatetime)

						Select @mi1=dbo.Park_feeDayM(@InDate,@OutDate,@TmpSPeriod,@MidDate1)  --第一时间段

						Select @MidDate2=Cast((Convert(varchar(10),@InDate,120) + ' 00:00:00') as smallDatetime)
						Select  @TmpEPeriod=Cast((Convert(varchar(11),@InDate,120) + @EndPeriod) as smallDatetime)

						Select @mi2=dbo.Park_feeDayM(@InDate,@OutDate,@MidDate2,@TmpEPeriod)  --第二时间段
					end
			end
		else
			Begin
				Select @TmpSPeriod=Cast((Convert(varchar(11),@InDate,120) + @StartPeriod) as smallDatetime)
				Select  @TmpEPeriod=Cast((Convert(varchar(11),@InDate,120) + @EndPeriod) as smallDatetime)
				if @InOne=1
					Begin
						Select @mi1=dbo.Park_feeDayM(@InDate,@MidDate1,@TmpSPeriod,@TmpEPeriod)

						Select @TmpSPeriod=Cast((Convert(varchar(11),@OutDate,120) + @StartPeriod) as smallDatetime)
						Select  @TmpEPeriod=Cast((Convert(varchar(11),@OutDate,120) + @EndPeriod) as smallDatetime)
						Select @mi2=dbo.Park_feeDayM(@MidDate2,@OutDate,@TmpSPeriod,@TmpEPeriod)

					end
				else
					Begin
						Select @mi1=dbo.Park_feeDayM(@InDate,@OutDate,@TmpSPeriod,@TmpEPeriod)
					end
			end
		Return @mi1+@mi2+@mi3+@mi4
	End
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <未知>
-- 调用示例： <SELECT dbo.[Park_Fee24HMK](@InDate,@OutDate,@StartPeriod,@EndPeriod) > 
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID ('dbo.Park_Fee24HMK') IS NOT NULL
	DROP FUNCTION dbo.Park_Fee24HMK
GO
	CREATE Function [dbo].[Park_Fee24HMK](@InDate smallDatetime,@OutDate smallDatetime,@StartPeriod varchar(8),@EndPeriod varchar(8))
	Returns int 
	 
	as
	Begin
		if (@OutDate<=@InDate)  return 0

		declare @ParkMinis as integer   	--总时间
		declare @ParkDates as integer   	--总天数
		Declare @MidMinute as int           --一天内间隔分钟数
		Declare @TMinute as int 		--总包含时间

		Declare @MidDate1 SmallDatetime
		Declare @MidDate2 SmallDatetime
		Declare @OneTwo as tinyint

		if @StartPeriod>@EndPeriod  --跨天
			Begin
				Select @MidDate1=Cast(('2001-01-01 ' + Left(@StartPeriod,5)+':00') as smallDatetime)
				Select @MidDate2=Cast(('2001-01-02 ' + Left(@EndPeriod,5)+':00') as smallDatetime)
				Select @MidMinute=datediff(minute,@MidDate1,@MidDate2)
				Select @OneTwo=1
			end
		else
			Begin
				Select @MidDate1=Cast(('2001-01-01 ' + Left(@StartPeriod,5)+':00') as smallDatetime)
				Select @MidDate2=Cast(('2001-01-01 ' + Left(@EndPeriod,5)+':00') as smallDatetime)
				Select @OneTwo=0
			end
		Select @MidMinute=datediff(minute,@MidDate1,@MidDate2)

		select @ParkMinis=datediff(minute,@InDate,@OutDate)
		Select @ParkDates=@ParkMinis / 1440
		Select @TMinute=0
		if @ParkDates>0 
			Begin
				Select @TMinute=@ParkDates*@MidMinute
				select @InDate=dateadd(Day,@ParkDates,@InDate) --入场时间向后推移
			end

		Select	@TMinute=@TMinute+dbo.Park_Fee24HM(@InDate,@OutDate,@StartPeriod,@EndPeriod)

		return @TMinute
	End
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <未知>
-- 调用示例： <SELECT dbo.[Park_Get24HMAmount](@InDate,@OutDate,@StartPeriod,@EndPeriod,@TmpFee,@UnitTime) > 
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID ('dbo.Park_Get24HMAmount') IS NOT NULL
	DROP FUNCTION dbo.Park_Get24HMAmount
GO
	CREATE Function [dbo].[Park_Get24HMAmount](@InDate smallDatetime,@OutDate smallDatetime,@StartPeriod  Varchar(8),@EndPeriod  Varchar(8),@TmpFee money,@UnitTime as int)
	Returns money 
	 
	as
	Begin
		Declare  @Amount money
		Declare  @mi int  --分钟数

		if @UnitTime=0 select @UnitTime=60   
		Select @Amount=0
		Select @mi=datediff(mi,@InDate,@OutDate)
		Begin
			Select @mi=dbo.Park_Fee24HMK(@InDate,@OutDate,@StartPeriod,@EndPeriod)
			Select @Amount=CEILING(cast(@mi as money)/@UnitTime)*@TmpFee
		end
		Return  @Amount
	End
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <未知>
-- 调用示例： <SELECT dbo.[Park_GetSperateMonthCharge](@stype,@chargemoney) > 
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID ('dbo.Park_GetSperateMonthCharge') IS NOT NULL
	DROP FUNCTION dbo.Park_GetSperateMonthCharge
GO
	CREATE FUNCTION Park_GetSperateMonthCharge
	(
		-- Add the parameters for the function here
		@stype int,
		@chargemoney money
	)
	returns money
	AS
	BEGIN
		declare @monthMoney money
		if(@stype=0)
		begin
		set @monthMoney=@chargemoney
		end
		else if(@stype=1)
		begin
		set @monthMoney=@chargemoney/3
		end
		else if(@stype=2)
		begin
		set @monthMoney=@chargemoney/6
		end
		else if(@stype=3)
		begin
		set @monthMoney=@chargemoney/12
		end
		return @monthMoney
	END
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <未知>
-- 调用示例： <SELECT dbo.[Park_Get24HCAmount](@InDate,@OutDate,@StartPeriod,@EndPeriod,@TmpFee) > 
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID ('dbo.Park_Get24HCAmount') IS NOT NULL
	DROP FUNCTION dbo.Park_Get24HCAmount
GO
	CREATE  Function [dbo].[Park_Get24HCAmount](@InDate smallDatetime,@OutDate smallDatetime,@StartPeriod  Varchar(5),@EndPeriod  Varchar(5),@TmpFee money)
	Returns money 
	 
	as
	Begin
		Declare @TmpInDate Varchar(16)
		Declare @TmpOutDate Varchar(16)

		Select @TmpInDate=Left(Convert(Varchar(19),@InDate,120),14)+substring(Convert(Varchar(19),@InDate,120),15,2)
		Select @TmpOutDate=Left(Convert(Varchar(19),@OutDate,120),14)+substring(Convert(Varchar(19),@OutDate,120),15,2)
		Return dbo.Park_Fee24HC(@TmpInDate,@TmpOutDate,@StartPeriod,@EndPeriod)*@TmpFee
	End
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <分隔函数>
-- 调用示例： <SELECT dbo.[f_splitSTR](@s,@split) > 
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
if exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[f_splitSTR]') and xtype in (N'FN', N'IF', N'TF'))
	drop function [dbo].[f_splitSTR]
GO
--方法1：循环截取法
CREATE FUNCTION f_splitSTR(
@s   varchar(8000),   --待分拆的字符串
@split varchar(10)     --数据分隔符
)RETURNS @re TABLE(col varchar(100))
AS
BEGIN
 DECLARE @splitlen int
 SET @splitlen=LEN(@split+'a')-2
 WHILE CHARINDEX(@split,@s)>0
 BEGIN
  INSERT @re VALUES(LEFT(@s,CHARINDEX(@split,@s)-1))
  SET @s=STUFF(@s,1,CHARINDEX(@split,@s)+@splitlen,'')
 END
 INSERT @re VALUES(@s)
 RETURN
END
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <判断此权限是否全部控制器都已下载，是-1；否-0>
-- 调用示例： <SELECT dbo.[IsNeedDownload](@Download,@noList) > 
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
if exists (select 1 from dbo.sysobjects where id = object_id(N'dbo.IsNeedDownload') and xtype in (N'FN', N'IF', N'TF'))
	DROP FUNCTION dbo.IsNeedDownload
GO
CREATE FUNCTION [dbo].[IsNeedDownload](@Download varchar(100),@noList varchar(100))
	returns int as
	begin
		declare @n int, @rut int, @len int
		SELECT @len = COUNT(num) from (SELECT row_number()over(ORDER BY col) num,* from f_splitSTR(@noList,',')) A
		set @n=1
		set @rut=0
		while @n <= @len
			begin
				declare @macno int
				select @macno = col from (SELECT row_number()over(ORDER BY col) num,* from f_splitSTR(@noList,',')) A where num = @n
				set @rut= convert(int, substring(@Download, @macno, 1))+@rut
				set @n=@n+1
			end
			if @rut=@len
			BEGIN
				return 1
			END
				return 0
	end
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <访客->
-- 调用示例： <SELECT dbo.[GetControlName](@machNo) > 
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID ('dbo.GetControlName') IS NOT NULL
	DROP FUNCTION dbo.GetControlName
GO
CREATE FUNCTION [dbo].[GetControlName](@machNo int)
	returns varchar(50) as
	begin
	declare @curStr varchar(50)
	select @curStr =right(ContIP,charindex('.',REVERSE(ContIP))-1)+' '+ContName from [Park_ControlSet] where machNo=@machNo
	return @curStr
	end
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <访客->
-- 调用示例： <SELECT dbo.[GetControlName](@machNo) > 
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID ('dbo.Fun_ChanelSet') IS NOT NULL
	DROP FUNCTION dbo.Fun_ChanelSet
GO
CREATE FUNCTION  [dbo].[Fun_ChanelSet](@BoxID int)
RETURNS @Table_ChanelSet
 TABLE 
(
    checked bit,[chanelName] varchar(50) ,chanelNo int,ContName varchar(50),machNo int,StrobeSet varchar(50),OtherSet varchar(50),Enclosure varchar(50), [InOut] varchar(50), CardMach varchar(50),StrobeName varchar(50),GateType varchar(50),smallParkNo int,CardWorkModel varchar(50) 
)
AS
BEGIN
    insert into @Table_ChanelSet select 1 as checked ,[chanelName],chanelNo,[dbo].[GetControlName](b.machNo) as ContName,b.machNo,b.StrobeSet,convert(varchar(10),b.HaveCar)+convert(varchar(10),b.GateClose)+convert(varchar(10),b.NoMoney)as OtherSet,b.Enclosure,case when b.[InOut]=0 then '标准入口' when b.[InOut]=1 then '标准出口' when b.[InOut]=2 then '标准嵌套入口' when b.[InOut]=3 then '标准嵌套出口' when b.[InOut]=4 then '半嵌套入口' when b.[InOut]=5 then '半嵌套出口' when b.[InOut]=6 then '单通道入' when b.[InOut]=7 then '单通道出' else '标准入口' end ,case when b.CardMach=0 then '无' when b.CardMach=1 then '有' else '' end as CardMach,[dbo].[GetControlName](b.StrobeNo) as StrobeName,case when b.GateType=0 then '综合通道' when b.GateType=1 then '固定车通道' when b.GateType=2 then '临时车通道' else '' end as GateType,b.smallParkNo,b.CardWorkModel from Park_ChanelTemp a 
    inner join (select * from Park_ControlSet where BoxID=@BoxID) b on a.[chanelNo]=b.SortID
	RETURN 
-- 出入类型 0标准入口，1标准出口，2标准嵌套入口，3标准嵌套出口、4半嵌套入口，5半嵌套出口、6单通道入,7单通道出
END

GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2016-01-24>  
-- 功能说明： <车场->
-- 调用示例： <SELECT dbo.[F_strcompare](string,string) > 
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <字符串相似度比较>
-- ============================================= 
IF OBJECT_ID ('dbo.F_strcompare') IS NOT NULL
	DROP FUNCTION dbo.F_strcompare
GO
CREATE FUNCTION [dbo].[F_strcompare](@str1 VARCHAR(8000),
                             @str2 VARCHAR(8000))
RETURNS NUMERIC
AS
  BEGIN
      DECLARE @i   INT,
              @j   INT,
              @k   INT,
              @ret VARCHAR(10)

      SELECT @i = MAX(strlen),
             @j = MIN(strlen),
             @k = 0
      FROM   (SELECT strlen=Len(@str1)
              UNION
              SELECT strlen=Len(@str2)) t

      IF( @j = 0 )
        RETURN 0

      WHILE @j > 0
        BEGIN
            IF Substring(@str1, @j, 1) = Substring(@str2, @j, 1)
              SET @k=@k + 1

            SET @j=@j - 1
        END

      SET @ret = CAST(@k * 100.0 / @i AS NUMERIC(3, 0))

      RETURN @ret
  END
go


-- |**************************************************************************************|
-- |******************************| 新增函数(end) |***************************************|
-- |**************************************************************************************|


go

-- |**************************************************************************************|
-- |******************************| 新增视图(begin) |*************************************|
-- |**************************************************************************************|


-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <当前日期视图>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID ('dbo.abc') IS NOT NULL
	DROP VIEW dbo.abc
GO
	create view dbo.abc
	as
		select getdate() as adate
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-卡信息视图>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID ('dbo.Vw_Ykt_CardRsDoor') IS NOT NULL
	DROP VIEW dbo.Vw_Ykt_CardRsDoor
GO
	CREATE VIEW [dbo].[Vw_Ykt_CardRsDoor]
	AS
	SELECT     a.YKTID, a.SType, a.Foregift, a.UserDate, a.UserName, a.Remark, b.CardType, c.CardID, c.CardNO, c.PerName, CASE Isnull(a.sType, 0) 
							WHEN 0 THEN '开户' WHEN 2 THEN '挂失' WHEN 3 THEN '解挂' WHEN 4 THEN '补发' WHEN 6 THEN '销户' END AS stypeName, b.StartDate, b.EndDate
	FROM         dbo.YKT_CardIssueDoor AS b LEFT OUTER JOIN
							dbo.YKT_CardRsMoney AS a ON b.YKTID = a.YKTID LEFT OUTER JOIN
							dbo.Ykt_CardIssue AS c ON b.YKTID = c.YktID
	WHERE      a.SType<>1
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场-未知>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID ('dbo.Vw_Ykt_PowerPlan') IS NOT NULL
	DROP VIEW dbo.Vw_Ykt_PowerPlan
GO
	CREATE VIEW [dbo].[Vw_Ykt_PowerPlan]
	AS
	SELECT     a.PlanName, a.[right], a.Foregift, a.Plan1, a.Plan2, a.Plan3, a.Plan4, a.Plan5, a.Plan6, a.Plan7, a.Remark, a.UserDate, a.UserName, 
							b.PlanName AS cplanname, b.RightList
	FROM         dbo.Ykt_PowerPlan AS a INNER JOIN
							dbo.Park_ControlPlan AS b ON a.Plan1 = b.PlanID
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场-取车场卡的最新日期>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID ('dbo.Vw_Ykt_NewDateCardPark') IS NOT NULL
	DROP VIEW dbo.Vw_Ykt_NewDateCardPark
GO
	CREATE VIEW [dbo].[Vw_Ykt_NewDateCardPark]
	AS
	--取车场卡的最新日期
	SELECT b.CardID,b.CFlag,MAX(a.UserDate) AS UserDate
	FROM dbo.YKT_CardIssuePark a LEFT JOIN
			dbo.Ykt_CardIssue b ON a.YKTID = b.YKTID
	GROUP BY b.CardID,b.CFlag
GO
IF OBJECT_ID ('dbo.Vw_Ykt_cardissuepark') IS NOT NULL
	DROP VIEW dbo.Vw_Ykt_cardissuepark
GO
	CREATE VIEW [dbo].[Vw_Ykt_cardissuepark]
	AS
	SELECT     dbo.Ykt_CardIssuePark.YKTID, dbo.YKT_CardIssuePark.CardType, dbo.YKT_CardIssuePark.sType, dbo.YKT_CardIssuePark.CarNO, 
							dbo.YKT_CardIssuePark.CarNoType, dbo.YKT_CardIssuePark.CarPlace, dbo.YKT_CardIssuePark.CarColour, dbo.YKT_CardIssuePark.StartDate, 
							dbo.YKT_CardIssuePark.EndDate, dbo.YKT_CardIssuePark.BalanceMoney, dbo.YKT_CardIssuePark.PlanID, dbo.YKT_CardIssuePark.Remark, 
							dbo.Ykt_PowerPlan.Plan1, dbo.Ykt_PowerPlan.PlanName
	FROM         dbo.Ykt_CardIssuePark LEFT OUTER JOIN
							dbo.Ykt_PowerPlan ON dbo.YKT_CardIssuePark.PlanID = dbo.Ykt_PowerPlan.Plan1
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场-全部车场卡数据>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID ('dbo.Vw_Ykt_ALLCardIssuePark') IS NOT NULL
	DROP VIEW dbo.Vw_Ykt_ALLCardIssuePark
GO
	CREATE View [dbo].[Vw_Ykt_ALLCardIssuePark]
	AS
		--全部车场卡数据
	SELECT     a.ID, a.YktID, a.CardID, a.CFlag, a.CardNO, a.PID, a.PerName, a.Foregift, a.States, a.SysRight, a.Remark, a.UserDate, a.UserName, c.CType, c.CCustName AS CName, 
							b.YKTID AS ParkYKTID, b.CardType, b.sType, b.CarNO, b.CarNoType, b.CarPlace, b.CarColour, b.StartDate, b.EndDate, b.BalanceMoney, b.PlanID, 
							b.UserDate AS ParkUserData, b.UserName AS operater, b.Remark AS parkremark, b.DownLoad, c.NoUse, c.IsSend, c.IDMode, c.CCustName, d.PerID, 
							dbo.Park_GetCardStates(a.States) AS StatesName, e.RightList, d.PerAddr, d.PerTel, d.PerHandTel
	FROM         dbo.Ykt_CardIssuePark AS b LEFT OUTER JOIN
							dbo.Ykt_CardIssue AS a ON a.YKTID = b.YKTID LEFT OUTER JOIN
							dbo.Park_CardType AS c ON b.CardType = c.CType LEFT OUTER JOIN
							dbo.Per_Persons AS d ON a.PID = d.PID LEFT OUTER JOIN
							dbo.Park_ControlPlan AS e ON b.PlanID = e.PlanID
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场-最新车场卡数据>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID ('dbo.Vw_Ykt_NewCardIssuePark') IS NOT NULL
	DROP VIEW dbo.Vw_Ykt_NewCardIssuePark
GO
	CREATE VIEW [dbo].[Vw_Ykt_NewCardIssuePark]
	AS
		--最新车场卡数据
		Select b.* from Vw_Ykt_NewDateCardPark a Left Join 
		Vw_Ykt_ALLCardIssuePark b On a.CardID=b.CardID and a.CFlag=b.Cflag and a.UserDate=b.ParkUserData
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场-最新车场卡数据>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID ('dbo.Vw_Ykt_CardIssueNewInfo') IS NOT NULL
	DROP VIEW dbo.Vw_Ykt_CardIssueNewInfo
GO
	CREATE VIEW [dbo].[Vw_Ykt_CardIssueNewInfo]
	AS
	SELECT     a.ID, a.YktID, a.CardID, a.CFlag, a.CardNO, a.PID, a.PerName, a.Foregift, a.States, a.SysRight, a.Remark, a.UserDate, a.UserName, a.CType, a.CName, a.ParkYKTID, 
							a.CardType, a.sType, a.CarNO, a.CarNoType, a.CarPlace, a.CarColour, convert(varchar(10),a.StartDate,120) as StartDate, convert(varchar(10),a.EndDate,120) as EndDate, cast(a.BalanceMoney AS varchar(20)) as BalanceMoney, a.PlanID, ISNULL(c.PlanName,'无') PlanName, a.ParkUserData, a.operater, 
							a.parkremark, a.DownLoad, a.NoUse, a.IsSend, a.IDMode, a.CCustName, a.PerID, a.StatesName, a.RightList, a.PerAddr, a.PerTel, 
							CASE WHEN LEFT(a.CName, 1) = '临' OR
							LEFT(a.CName, 1) = '储' OR
							a.states <> 0 THEN a.StatesName ELSE dbo.Park_DayState(a.startdate, a.enddate) END AS Newstate, isnull(b.ChargeMoney,0) ChargeMoney
	FROM         dbo.Vw_Ykt_NewCardIssuePark AS a INNER JOIN
							dbo.Park_ControlPlan AS c ON a.PlanID = c.PlanID LEFT OUTER JOIN
							dbo.Park_MonthSet AS b ON b.CardType = a.CardType AND b.sType = a.sType
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID ('dbo.Vw_Ykt_CardIssueParkByDept') IS NOT NULL
	DROP VIEW dbo.Vw_Ykt_CardIssueParkByDept
GO
	CREATE VIEW [dbo].[Vw_Ykt_CardIssueParkByDept]
	AS
	SELECT      a.ID, a.YktID, a.CardID, a.CFlag, a.CardNO, a.PID, a.PerName, a.Foregift, a.States, a.SysRight, a.Remark, a.UserDate, a.UserName, a.CType, a.CName, a.ParkYKTID, 
			a.CardType, a.sType, a.CarNO, a.CarNoType, a.CarPlace, a.CarColour, a.StartDate, a.EndDate, a.BalanceMoney, a.PlanID, a.PlanName, a.ParkUserData, a.operater, 
			a.parkremark, a.DownLoad, a.NoUse, a.IsSend, a.IDMode, a.CCustName, a.PerID, a.StatesName, a.RightList, a.PerAddr, a.PerTel, 
			a.Newstate, a.ChargeMoney, b.DeptID,c.DeptName
	FROM    dbo.Vw_Ykt_CardIssueNewInfo AS a INNER JOIN
			dbo.Per_Persons as b ON a.PID = b.id	LEFT JOIN
			dbo.Per_Dept as c ON b.DeptID = c.DeptID	
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场-纠正深圳社会类收费标准视图>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID ('dbo.Vw_Park_ES4') IS NOT NULL
	DROP VIEW dbo.Vw_Park_ES4
GO
	CREATE VIEW [dbo].[Vw_Park_ES4]
	AS
	SELECT     dbo.Park_Es4.CardType, dbo.Park_Es4.NoWorkDayFirstHourAmount, dbo.Park_Es4.NoWorkDayChgUnit, dbo.Park_Es4.NoWorkChgAmount, 
							dbo.Park_Es4.WorkDayFirstHourAmount, dbo.Park_Es4.PeakChgUnit, dbo.Park_Es4.PeakChgAmount, dbo.Park_Es4.NotPeakChgUnit, 
							dbo.Park_Es4.NotPeakChgAmount, dbo.Park_Es4.DayTopAmount, dbo.Park_Es4.IsDate, dbo.Park_Es4_F.NoWorkDayBegin, 
							dbo.Park_Es4_F.NoWorkDayend, dbo.Park_Es4_F.WorkDayTBegin, dbo.Park_Es4_F.WorkDayTEnd, dbo.Park_Es4_F.PeakFieldBegin, 
							dbo.Park_Es4_F.PeakFieldEnd
	FROM         dbo.Park_Es4 left JOIN
							dbo.Park_Es4_F on dbo.Park_Es4.CardType = dbo.Park_Es4_F.CardType
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场-车辆出场表视图>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID ('dbo.Vw_Park_CarOut') IS NOT NULL
	DROP VIEW dbo.Vw_Park_CarOut
GO
	CREATE VIEW [dbo].[Vw_ParK_CarOut]
	AS  --车辆出场表视图
	SELECT     a.ID, a.InMachNo, a.OutMachNo, a.YktID, a.CardID, a.CFlag, a.CardNo, a.EmpName, a.CarNO, a.CarNoType, a.CardType, a.FreeType, a.InTime, a.InPic, 
							a.InUserName, a.CentralTime, a.OutTime, a.OutPic, a.OutUserName, a.ZjPic, a.PayType, a.PayCharge, a.BalanceMoney, a.DiscountNo, a.TypeID, 
							a.DiscountTime, CASE WHEN a.FreeType > 0 THEN 0 ELSE (a.Accountcharge - a.paycharge) END AS DisAmount, a.AccountCharge, a.IsOut, a.UnusualMemo, 
							CASE WHEN isnull(a.FreeType, 0) > 0 THEN (a.Accountcharge - a.paycharge) ELSE 0 END AS FreeAmount, b.ContName AS InControlName, 
							c.ContName AS OutControlName, g.CCustName AS CardTypeName, h.FreeName,a.OutWay,CASE a.OutWay WHEN 0 THEN '正常出场' WHEN 1 THEN '手工放行' 
							WHEN 2 THEN '异常放行' WHEN 3 THEN '扫码出场' WHEN 4 THEN '脱机记录' WHEN 5 THEN '相机异常记录' WHEN 6 THEN '异常开闸'ELSE '未知' END AS OutWayName
	FROM         dbo.ParK_CarOut AS a LEFT OUTER JOIN
							dbo.Park_ControlSet AS b ON a.InMachNo = b.machNo LEFT OUTER JOIN
							dbo.Park_ControlSet AS c ON a.OutMachNo = c.machNo LEFT OUTER JOIN
							dbo.Park_CardType AS g ON a.CardType = g.CType LEFT OUTER JOIN
							dbo.ParK_FreeType AS h ON a.FreeType = h.FreeType
GO



-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场出场放弃视图>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Vw_Abandon_record') IS NOT NULL
	DROP VIEW dbo.Vw_Abandon_record
GO
	CREATE VIEW [dbo].[Vw_Abandon_record]
	as
	SELECT     a.ID, a.InMachNo, a.OutMachNo, a.YktID, a.CardID, a.CFlag, a.CardNo, a.EmpName, a.CarNO, a.CarNoType, a.CardType, a.FreeType, a.InTime, a.InPic, 
							a.InUserName, a.CentralTime, a.OutTime, a.OutPic, a.OutUserName, a.ZjPic, a.PayType, a.PayCharge, a.BalanceMoney, a.DiscountNo, a.TypeID, 
							a.DiscountTime, CASE WHEN a.FreeType > 0 THEN 0 ELSE a.DisAmount END AS DisAmount, a.AccountCharge, a.IsOut, a.UnusualMemo, 
							CASE WHEN isnull(a.FreeType, 0) > 0 THEN (a.Accountcharge - a.paycharge) ELSE 0 END AS FreeAmount, b.ContName AS InControlName, 
							c.ContName AS OutControlName, g.CCustName AS CardTypeName, h.FreeName,a.OutWay,CASE a.OutWay WHEN 0 THEN '正常出场' WHEN 1 THEN '手工放行' ELSE '校正出场' END AS OutWayName
	FROM         dbo.Abandon_record AS a LEFT OUTER JOIN
							dbo.Park_ControlSet AS b ON a.InMachNo = b.machNo LEFT OUTER JOIN
							dbo.Park_ControlSet AS c ON a.OutMachNo = c.machNo LEFT OUTER JOIN
							dbo.Park_CardType AS g ON a.CardType = g.CType LEFT OUTER JOIN
							dbo.ParK_FreeType AS h ON a.FreeType = h.FreeType
							
UNION ALL
	SELECT     a.ID, a.InMachNo, a.OutMachNo, a.YktID, a.CardID, a.CFlag, a.CardNo, a.EmpName, a.CarNO, a.CarNoType, a.CardType, a.FreeType, a.InTime, a.InPic, 
							a.InUserName, a.CentralTime, a.OutTime, a.OutPic, a.OutUserName, a.ZjPic, a.PayType, a.PayCharge, a.BalanceMoney, a.DiscountNo, a.TypeID, 
							a.DiscountTime, CASE WHEN a.FreeType > 0 THEN 0 ELSE a.DisAmount END AS DisAmount, a.AccountCharge, a.IsOut, a.UnusualMemo, 
							CASE WHEN isnull(a.FreeType, 0) > 0 THEN (a.Accountcharge - a.paycharge) ELSE 0 END AS FreeAmount, b.ContName AS InControlName, 
							c.ContName AS OutControlName, g.CCustName AS CardTypeName, h.FreeName,a.OutWay,CASE a.OutWay WHEN 0 THEN '正常出场' WHEN 1 THEN '手工放行' ELSE '校正出场' END AS OutWayName
	FROM   [Vw_ParK_CarOut] AS a LEFT OUTER JOIN
							dbo.Park_ControlSet AS b ON a.InMachNo = b.machNo LEFT OUTER JOIN
							dbo.Park_ControlSet AS c ON a.OutMachNo = c.machNo LEFT OUTER JOIN
							dbo.Park_CardType AS g ON a.CardType = g.CType LEFT OUTER JOIN
							dbo.ParK_FreeType AS h ON a.FreeType = h.FreeType where a.CardType <3 and a.OutMachNo in ( select machNo from Park_ControlSet where InOut in( 1,3,5) )
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场-车辆入场信息视图>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID ('dbo.Vw_Park_CarIn') IS NOT NULL
	DROP VIEW dbo.Vw_Park_CarIn
GO
	CREATE   VIEW [dbo].[Vw_Park_CarIn]
	AS
	SELECT a.ID ,a.MachNo,a.YktID,a.CardID,a.CFlag,a.CardNo,a.EmpName,a.CarNO,a.CardType,a.CarNoType,a.InTime,a.InUserName ,a.InPic,a.Small,
	a.DiscountNo,a.TypeID,a.DiscountTime,a.Makeup,a.IsLocked,b.ContName AS InControlName,a.BackCarNO ,a.BackInPic ,a.CarEventNo,
			e.CCustName AS CardTypeName,a.InWay,CASE IsNull(a.InWay ,0) WHEN 0 THEN '正常入场' WHEN 1 THEN '校正入场' WHEN 2 THEN '手工入场' WHEN 3 THEN '扫码入场' 
			WHEN 4 THEN '脱机记录' WHEN 5 THEN '相机异常记录'WHEN 6 THEN '异常开闸' ELSE '未知' END AS InWayName
	FROM dbo.ParK_CarIn a LEFT  JOIN
			dbo.Park_ControlSet b ON a.MachNo = b.MachNo LEFT  JOIN
			dbo.Park_CardType e ON a.CardType = e.CType
GO


-- ============================================= 
-- 程序编写： <王诚喜> 
-- 建立日期： <2016-12-26>  
-- 功能说明： <车场-车辆入场信息视图>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF OBJECT_ID ('dbo.Vw_Park_CarInException') IS NOT NULL
	DROP VIEW dbo.Vw_Park_CarInException
GO
	CREATE   VIEW [dbo].[Vw_Park_CarInException]
	AS
	SELECT a.ID ,a.MachNo,a.YktID,a.CardID,a.CFlag,a.CardNo,a.EmpName,a.CarNO,a.CardType,a.CarNoType,a.InTime,a.InUserName ,a.InPic,a.BInTime,a.BInPic,
	b.ContName AS InControlName,e.CCustName AS CardTypeName,a.InWay,CASE a.InWay WHEN 0 THEN '正常入场' WHEN 1 THEN '校正入场' WHEN 2 THEN '手工入场' 
	WHEN 3 THEN '异常开闸' WHEN 4 THEN '入场回退' WHEN 5 THEN '未授权入场' ELSE '未知' END AS InWayName
	FROM dbo.Park_CarInException a LEFT  JOIN
			dbo.Park_ControlSet b ON a.MachNo = b.MachNo LEFT  JOIN
			dbo.Park_CardType e ON a.CardType = e.CType
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场-车辆缴费信息视图>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
 IF OBJECT_ID ('dbo.Vw_Park_CarCharge') IS NOT NULL
DROP VIEW dbo.Vw_Park_CarCharge
GO
CREATE VIEW [dbo].[Vw_Park_CarCharge]
AS
SELECT j.BoxID , a.CardID, a.CardNo, a.CarNo, a.CardType, a.InTime, a.OutTime, a.OutUserName as UserName , a.PayCharge,a.AccountCharge,
     Case WHen Isnull(a.FreeType,0)>0 then 0 else (a.Accountcharge-a.PayCharge) end AS DisAmount,
     Case WHen Isnull(a.FreeType,0)>0 then (a.Accountcharge-a.PayCharge) else 0 end AS FreeAmount,
      g.CCustName AS CardTypeName, h.FreeName AS FreeName,'出口收费' as ChargeType, a.InPic AS InPic, a.OutPic AS OutPic,
      (Case Isnull(a.PayType ,0) WHEN 0 then '现金支付' WHEN 1 then '微信支付' WHEN 2 then '支付宝支付' WHEN 3 then '银联闪付'
      WHEN 4 then '交通卡支付' WHEN 5 then '自助缴费机' else '其它' end ) as PayType, a.OrderNum as OrderNum,ISNULL ( a.BalanceMoney ,0) as BalanceMoney
FROM dbo.Park_CarOut a LEFT JOIN
      dbo.Park_CardType g ON a.CardType = g.CType LEFT JOIN
      dbo.ParK_FreeType h ON a.FreeType = h.FreeType LEFT JOIN
      dbo.Park_ControlSet j ON a.OutMachNo =j.machNo 
UNION ALL
SELECT a.BoxID , a.CardID, a.CardNo, a.CarNo, a.CardType, a.InTime, a.PayChargeTime AS OutTime, a.UserName, a.PayCharge,a.AccountCharge, 
Case WHen Isnull(a.FreeType,0)>0 then 0 else (a.Accountcharge-a.PayCharge) end AS DisAmount,
     Case WHen Isnull(a.FreeType,0)>0 then (a.Accountcharge-a.PayCharge) else 0 end AS FreeAmount,
                      g.CCustName AS CardTypeName, h.FreeName AS FreeName, (CASE WHEN isnull(a.OverTimeS, '2010-01-01') 
                      <= '2010-01-01' THEN '定点收费' ELSE '逾时收费' END) AS ChargeType, i.InPic AS InPic, i.OutPic AS OutPic,
                      (Case Isnull(a.PayType ,0) WHEN 0 then '现金支付' WHEN 1 then '微信支付' WHEN 2 then '支付宝支付' WHEN 3 then '银联闪付'
      WHEN 4 then '交通卡支付' WHEN 5 then '自助缴费机' else '其它' end ) as PayType, a.OrderNum as OrderNum,0 as BalanceMoney
FROM         Park_CentralCharge a LEFT JOIN
                      dbo.Park_CardType g ON a.CardType = g.CType LEFT JOIN
                      dbo.ParK_FreeType h ON a.FreeType = h.FreeType  
                      LEFT JOIN dbo.Park_CarOut i ON a.CardID=i.CardID and a.InTime=i.InTime  and a.CardNo =i.CardNo 
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2016-06-13>  
-- 功能说明： <车场-出场费用明细视图>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- ============================================= 
IF  EXISTS (SELECT 1 FROM sys.views WHERE object_id = OBJECT_ID(N'[dbo].[Vw_Interface_ParkOutChargeDetail]'))
DROP VIEW [dbo].[Vw_Interface_ParkOutChargeDetail]
GO
CREATE VIEW [dbo].[Vw_Interface_ParkOutChargeDetail]
	AS  --出场费用明细视图
	SELECT a.InMachNo, a.OutMachNo,a.YktID,a.CFlag,a.CardID, a.CardNo, a.CarNo, a.CardType, 
	a.InTime,b.ContName AS InControlName,a.InUserName, a.OutTime, c.ContName AS OutControlName,a.OutUserName, a.AccountCharge,a.PayCharge,
     (select ISNULL(SUM(AccountCharge),a.AccountCharge) FROM Park_CentralCharge WHERE CardID = a.CardID AND InTime = a.InTime GROUP BY CardID,InTime) as AccountCharge1,
     (select ISNULL(SUM(PayCharge),a.PayCharge) FROM Park_CentralCharge WHERE CardID = a.CardID AND InTime = a.InTime GROUP BY CardID,InTime) as PayCharge1,
     Case WHen Isnull(a.TypeID,0)>0 then (a.Accountcharge-a.PayCharge) else 0 end AS DisAmount,
     Case WHen Isnull(a.FreeType,0)>0 then (a.Accountcharge-a.PayCharge) else 0 end AS FreeAmount,
      g.CCustName AS CardTypeName, h.FreeName AS FreeName, a.InPic AS InPic, a.OutPic AS OutPic,
      (Case Isnull(a.PayType ,0) WHEN 0 then '现金支付' WHEN 1 then '微信支付' WHEN 2 then '支付宝支付' WHEN 3 then '银联闪付'
      WHEN 4 then '交通卡支付' WHEN 5 then '自助缴费机' else '其它' end ) as PayType, 
      ISNULL((select  ISNULL(OrderNum,'') + ',' from Park_CentralCharge WHERE CardID = a.CardID AND InTime = a.InTime for xml path('')),a.OrderNum) as OrderNum
	FROM dbo.Park_CarOut a LEFT OUTER JOIN
	  dbo.Park_ControlSet AS b ON a.InMachNo = b.machNo LEFT OUTER JOIN
	  dbo.Park_ControlSet AS c ON a.OutMachNo = c.machNo LEFT OUTER JOIN
      dbo.Park_CardType g ON a.CardType = g.CType LEFT OUTER JOIN
      dbo.ParK_FreeType h ON a.FreeType = h.FreeType where a.CardType>20

GO					
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场-状态监控视图>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Vw_Park_DeviceStatus') IS NOT NULL
	DROP VIEW dbo.Vw_Park_DeviceStatus
GO
	CREATE VIEW [dbo].[Vw_Park_DeviceStatus]
	AS
	SELECT a.ContName, b.BoxName, c.DID, c.DType, c.DIP, c.BoxID ,c.RoadGateState
      ,c.CardMachineState,c.PaperMachineState,c.PowerReset,c.GroudSenseUnnormal,c.CameraUnnormal,c.ClientConnectUnnormal,c.ServerConnectUnnormal
      ,c.DisplaymoduleConnectUnnormal,c.VoicemoduleConnectUnnormal,c.RoadGateConnectUnnormal,c.CardManchuneConnectUnnormal,c.PaperMachineConnectUnnormal
      ,c.MainBoardNetUnnormal,c.SystemClockUnnormal ,c.SystemStorageUnnormal,c.OnlineTime, c.LastUpdateTime, c.Remark		
	FROM dbo.Park_ControlSet AS a LEFT OUTER JOIN
			dbo.Park_LocalSet AS b ON a.BoxID = b.BoxID LEFT OUTER JOIN
			dbo.Park_DeviceStatus AS c ON a.machNo = c.DID

GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场-设备视图>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Vw_Park_Devices') IS NOT NULL
	DROP VIEW dbo.Vw_Park_Devices
GO
	CREATE VIEW [dbo].[Vw_Park_Devices]
AS
	 SELECT 0 AS ID, BoxID AS DID, BoxIP AS DIP, 0 AS DType, 0 AS Dport, BoxName AS DName, 0 AS TopID,1 as ChannelID,-1 as InOut,'' as DSn
	FROM         Park_LocalSet
	UNION ALL
	SELECT     0 AS ID, MachNo AS DID, ContIP AS DIP, 1 AS DType, 0 AS DPort, ContName AS DName, BoxID AS TopID,1 as ChannelID ,InOut,DSn
	--case when InOut=0 OR InOut=2 or InOut=4 then 0 when InOut=1 OR InOut=3 or InOut=5 then 1  else 0  end as InOut
	FROM         Park_ControlSet
	UNION ALL
	SELECT     0 AS ID, a.SortID AS DID, CamIP AS DIP, 2 AS DType, CamPort AS DPort, CamName AS DName, b.MachNo AS TopID,ChannelID,b.InOut,'' as DSn
	--case when b.InOut=0 OR b.InOut=2 or b.InOut=4 then 0 when b.InOut=1 OR b.InOut=3 or b.InOut=5 then 1  else 0 end as InOut
	FROM         Park_CamSet a left join Park_ControlSet b on a.MachNo=b.machNo
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <-打折明细>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================

IF OBJECT_ID ('dbo.Vw_Park_DisDetail') IS NOT NULL
	DROP VIEW dbo.Vw_Park_DisDetail
GO
CREATE VIEW [dbo].[Vw_Park_DisDetail]
	AS
	select a.*,c.InTime,c.CardNo,c.InUserName, c.OutUserName, b.EqID,b.DisName,b.DisRemark,case when b.DisType=1 then '全免' when b.DisType=4 then '折扣率' when b.DisType=3 then '免小时' else '免金额' end as DisType,c.AccountCharge,c.PayCharge,d.EqName,c.CarNO from Park_DisDetail a 
	left join Park_DisInfo b on a.DiscountID=b.DiscountID
	left join Park_EquipmentS d on b.EqID=d.EqID
	left join Park_CarOut c on a.DiscountTime=c.DiscountTime and a.OutTime=c.OutTime where a.OutType =0
    UNION ALL
    select a.*,c.InTime,c.CardNo,c.UserName, c.UserName AS OutUserName,b.EqID,b.DisName,b.DisRemark,case when b.DisType=1 then '全免' when b.DisType=4 then '折扣率' when b.DisType=3 then '免小时' else '免金额' end as DisType,c.AccountCharge,c.PayCharge,d.EqName,c.CarNO from Park_DisDetail a 
	left join Park_DisInfo b on a.DiscountID=b.DiscountID
	left join Park_EquipmentS d on b.EqID=d.EqID
	left join Park_CentralCharge c on a.DiscountTime=c.DiscountTime and a.OutTime=c.PayChargeTime  where a.OutType =1

GO


--IF OBJECT_ID ('dbo.Vw_Park_DisDetail') IS NOT NULL
--	DROP VIEW dbo.Vw_Park_DisDetail
--GO
--CREATE VIEW [dbo].[Vw_Park_DisDetail]
--	AS
--	select a.*,c.InTime,c.CardNo,c.InUserName, c.OutUserName, b.EqID,b.DisName,b.DisRemark,case when b.DisType=1 then '全免' when b.DisType=4 then '折扣率' when b.DisType=3 then '免小时' else '免金额' end as DisType,c.AccountCharge,c.PayCharge,d.EqName,c.CarNO from Park_DisDetail a 
--	left join Park_DisInfo b on a.DiscountID=b.DiscountID
--	left join Park_EquipmentS d on b.EqID=d.EqID
--	left join Park_CarOut c on a.DiscountTime=c.DiscountTime and a.OutTime=c.OutTime where a.OutType =0
--    UNION ALL
--    select a.*,c.InTime,c.CardNo,c.UserName, c.UserName AS OutUserName,b.EqID,b.DisName,b.DisRemark,case when b.DisType=1 then '全免' when b.DisType=4 then '折扣率' when b.DisType=3 then '免小时' else '免金额' end as DisType,c.AccountCharge,c.PayCharge,d.EqName,c.CarNO from Park_DisDetail a 
--	left join Park_DisInfo b on a.DiscountID=b.DiscountID
--	left join Park_EquipmentS d on b.EqID=d.EqID
--	left join Park_CentralCharge c on a.DiscountTime=c.DiscountTime and a.OutTime=c.PayChargeTime  where a.OutType =1
--GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-卡信息视图>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Vw_Door_Persons') IS NOT NULL
	DROP VIEW dbo.Vw_Door_Persons
GO
	CREATE VIEW [dbo].[Vw_Door_Persons]
	AS
	SELECT     a.YktID, b.YKTID AS Expr1, c.Pid, a.PID AS Expr2, a.CardID, 
							c.id, c.PerID, c.PerName, c.PerSbM, c.PerAddr, c.PerTel, 
							c.ReMark, c.PerIDNo, c.PerSex, c.BirthDay, c.PerNative, c.PerPlace, 
							c.PerPost, c.Marry, c.PerEmail, c.PerHandTel, c.Peremergency, 
							c.PerType, c.enterDate, c.FormatDate, c.EduLevel, c.Member, 
							c.DeptID AS Expr3, c.Positions, c.Isleave, c.leaveDate, c.leaveCause, 
							c.Memo, c.CrUserDate, c.CrUserName, c.status,c.ParentName,c.ParentTel,c.ParentIDCard,c.ParentRelation, 
							d.DeptID, d.DeptName,b.GradeID, CASE Isnull(b.CardType, 0) 
							WHEN 60 THEN '通行卡' WHEN 70 THEN '管理卡' WHEN 78 THEN '警卫卡' WHEN 79 THEN '巡查卡' END AS DoorCardType, b.StartDate, 
							b.EndDate, a.States,b.UserDate,b.UserName
	FROM         dbo.Ykt_CardIssue a INNER JOIN
							dbo.YKT_CardIssueDoor b ON a.YktID = b.YKTID INNER JOIN
							dbo.Per_Persons c ON a.PID = c.Pid LEFT OUTER JOIN
							dbo.Per_Dept d ON c.DeptID = d.DeptID
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-创建门禁摄像机与控制器视图>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Vw_Door_CamCont') IS NOT NULL
	DROP VIEW dbo.Vw_Door_CamCont
GO
	CREATE VIEW [dbo].[Vw_Door_CamCont]
	AS
	SELECT     dbo.Door_CamSet.CamID, dbo.Door_CamSet.ComputerIP, dbo.Door_Cont.ContID, dbo.Door_Cont.ContNo, dbo.Door_Cont.ContName, dbo.Door_Cont.ContIP, 
						  dbo.Door_CamSet.CamIP, dbo.Door_CamSet.CamName, dbo.Door_CamSet.CamPort
	FROM         dbo.Door_CamSet INNER JOIN
						  dbo.Door_Cont ON dbo.Door_CamSet.ContID = dbo.Door_Cont.ContID

GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <人员信息视图>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('vw_per_PerList') IS NOT NULL
	DROP VIEW dbo.vw_per_PerList
GO
	CREATE VIEW [dbo].[vw_per_PerList]
	AS
	SELECT     a.PerName, a.PerSex, a.PerID, a.DeptID, a.PerTel,a.LandlineNum, a.PerHandTel, CASE WHEN b.DeptName IS NULL THEN '无' ELSE b.deptname END AS DeptName, 
                      CASE WHEN d .DeptName IS NULL THEN '无' ELSE d .deptname END AS OrgName, a.Positions, a.PerNative, a.id, a.Isleave, a.PerAddr, c.PhoPath, a.Pid, a.status, 
                      a.PerType, a.CrUserDate, a.CrUserName, a.BirthDay, a.EduLevel, a.enterDate, b.OrgId,a.PerSbM,a.ReMark,a.PerIDNo,a.PerPlace,a.PerPost,
			  a.Marry,a.PerEmail,a.Peremergency,a.FormatDate,a.Member,a.leaveDate,a.leaveCause,a.Memo,
			  b.DeptID AS Expr1,a.ParentName,a.ParentTel,a.ParentIDCard,a.ParentRelation
FROM         dbo.Per_Persons AS a LEFT OUTER JOIN
                      dbo.Per_Dept AS b ON b.DeptID = a.DeptID LEFT OUTER JOIN
                      dbo.Per_Photo AS c ON a.id = c.Pid LEFT OUTER JOIN
                      dbo.Per_Org AS d ON b.OrgId = d.DeptID
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-进出记录视图>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('Vw_DoorInOut_Persons') IS NOT NULL
	DROP VIEW dbo.Vw_DoorInOut_Persons
GO
	CREATE VIEW [dbo].[Vw_DoorInOut_Persons]
	AS
	SELECT 
    a.ID,a.PID,a.ContID,a.DoorID,a.CardType,a.CardID,a.EventTime,a.EventWay,a.EventType,a.PicPath,a.PicName,
    b.PerName,b.PerSex,b.PerID,b.DeptID,b.PerTel,b.PerHandTel,b.DeptName,b.Positions,b.PerNative,b.id as PersonsID ,b.Isleave,b.PerAddr,b.PhoPath,b.Pid as PersonsPID,b.status,b.PerType,b.CrUserDate,b.CrUserName,b.BirthDay,b.EduLevel,b.enterDate,b.PerSbM,b.ReMark,b.PerIDNo,b.PerPlace,b.PerPost,b.Marry,b.PerEmail,b.Peremergency,b.FormatDate,b.Member,b.leaveDate,b.leaveCause,b.Memo,b.Expr1,b.ParentName,b.ParentTel,b.ParentIDCard,b.ParentRelation
 FROM Door_OpenDoorRecord a LEFT JOIN  vw_per_PerList b ON a.PerID=b.PerID
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-进出记录视图2>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('Vw_Door_OpenDoorRecordTwo') IS NOT NULL
	DROP VIEW dbo.Vw_Door_OpenDoorRecordTwo
GO
	CREATE VIEW [dbo].[Vw_Door_OpenDoorRecordTwo]
	AS
	SELECT 
    a.ID,a.PID,a.ContID,a.DoorID,a.CardType,a.CardID,a.EventTime,a.EventWay,a.EventType,a.PicPath,a.PicName,
    b.PerName,b.PerSex,b.PerID,b.DeptID,b.PerTel,b.PerHandTel,b.DeptName,b.Positions,b.PerNative,b.id as PersonsID ,b.Isleave,b.PerAddr,b.PhoPath,b.Pid as PersonsPID,b.status,b.PerType,b.CrUserDate,b.CrUserName,b.BirthDay,b.EduLevel,b.enterDate,b.PerSbM,b.ReMark,b.PerIDNo,b.PerPlace,b.PerPost,b.Marry,b.PerEmail,b.Peremergency,b.FormatDate,b.Member,b.leaveDate,b.leaveCause,b.Memo,b.Expr1,b.ParentName,b.ParentTel,b.ParentIDCard,b.ParentRelation
 FROM Door_OpenDoorRecordTwo a lEFT JOIN  vw_per_PerList b ON a.PerID=b.PerID
GO
 
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <卡信息视图>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Vw_CardIssueInfo') IS NOT NULL
	DROP VIEW dbo.Vw_CardIssueInfo
GO
	CREATE VIEW [dbo].[Vw_CardIssueInfo]
	AS
	SELECT     a.YktID, a.CardID, a.CardNO, a.States, a.SysRight, a.Foregift, d.PerName, d.PerAddr, b.DoorCardType, c.CName, c.CarNO, c.CarColour, 
							c.BalanceMoney, b.StartDate, b.EndDate, c.CarPlace, c.CFlag, a.UserDate, a.Remark, c.CarNoType, d.PerID, c.CType, c.sType, c.PlanID, 
							c.StartDate AS p_sdate, c.EndDate AS p_edate
	FROM         dbo.Ykt_CardIssue AS a LEFT OUTER JOIN
							dbo.Vw_Door_Persons AS b ON a.YktID = b.YktID LEFT OUTER JOIN
							dbo.Vw_Ykt_NewCardIssuePark AS c ON a.YktID = c.YktID LEFT OUTER JOIN
							dbo.Per_Persons AS d ON a.PID = d.Pid

GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <卡充值信息视图>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Vw_Ykt_CardRsMoney') IS NOT NULL
	DROP VIEW dbo.Vw_Ykt_CardRsMoney
GO
	CREATE VIEW [dbo].[Vw_Ykt_CardRsMoney]
	as
	SELECT     a.YKTID, a.SType, a.FrontDate, a.NStartDate, a.NEndDate, a.BalanceMoney, a.PayType, a.Foregift, a.BeforeMoney, a.UserDate, a.UserName, 
							a.Remark, CASE Isnull(a.PayType, 0) WHEN 0 THEN '现金支付' WHEN 1 THEN '银联闪付' WHEN 2 THEN '微信支付' ELSE '支付宝支付' END AS PaytypeName, CASE Isnull(a.sType, 0) 
							WHEN 0 THEN '开户' WHEN 1 THEN '延期' WHEN 2 THEN '挂失' WHEN 3 THEN '解挂' WHEN 4 THEN '补发' WHEN 5 THEN '退款' when 6 then '销户' when 8 then '充值' when 10 then '消费充值' when 11 then '消费退款' else '未知' END AS
							stypeName, b.PerAddr, b.PerName, b.CName, b.Foregift AS Expr1, b.CarNO, b.YktID AS Expr2, b.CardID, b.CardNO, c.DoorCardType, b.CType, 
							b.sType AS monthModel,YRecordNumber,WeChatNumber
	FROM         dbo.Ykt_CardRsMoney AS a LEFT OUTER JOIN
							dbo.Vw_Ykt_ALLCardIssuePark AS b ON a.YKTID = b.YktID LEFT OUTER JOIN
							Vw_Door_Persons as c on a.YKTID=c.YktID 
GO
 
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场-交班记录视图>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Vw_ParK_JiaoBJiL') IS NOT NULL
	DROP VIEW dbo.Vw_ParK_JiaoBJiL
GO
create view Vw_ParK_JiaoBJiL
as
select  a.BoxID,b.BoxName, LoginDate,ReliefDate,UserName,ReUserName,HandGate,TempCardIn,TempCardOut,FreeCharge,DisSum,DisCharge,TotalCharge from ParK_SumUser a left join Park_LocalSet b
on b.BoxID=a.BoxID
go

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <未知>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Vw_CardIssueInfoNew') IS NOT NULL
	DROP VIEW dbo.Vw_CardIssueInfoNew
GO
	CREATE VIEW [dbo].[Vw_CardIssueInfoNew]
	AS
	SELECT     a.YktID, a.CardID, a.CardNO, a.States, a.SysRight, a.Foregift,d.Pid,e.DeptName, d.PerName, d.PerAddr, b.DoorCardType, c.CName, c.CarNO, c.CarColour, 
							c.BalanceMoney, b.StartDate, b.EndDate, c.CarPlace, c.CFlag, a.UserDate, a.Remark, c.CarNoType, d.PerID, c.CType, c.sType, c.PlanID, 
							c.StartDate AS p_sdate, c.EndDate AS p_edate,
                                                        d.ParentName,d.ParentRelation
	FROM         dbo.Ykt_CardIssue AS a LEFT OUTER JOIN
							dbo.Vw_Door_Persons AS b ON a.YktID = b.YktID LEFT OUTER JOIN
							dbo.Vw_Ykt_NewCardIssuePark AS c ON a.YktID = c.YktID LEFT OUTER JOIN
							dbo.Per_Persons AS d ON a.PID = d.Pid LEFT OUTER JOIN
							dbo.Per_Dept as e ON d.DeptID = e.Deptid
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <访客-访客登记视图>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Vw_Guest_RegInfo') IS NOT NULL
	DROP VIEW dbo.Vw_Guest_RegInfo
GO
CREATE VIEW [dbo].[Vw_Guest_RegInfo]
AS
SELECT a.[id]
      ,[VisitorName]
      ,[sex]
      ,[IDCardNo]
      ,'80'+substring([CardICNo],3,14) as [CardICNo]
      ,[Address]
      ,[IDPic]
      ,[Photo]
      ,[company]
      ,[Phone]
      ,[Reason]
      ,a.[Pid]
      ,[CarNo]
      ,[Indate]
      ,[Num]
      ,[openType],b.PerName ,case when a.openType=0 then '身证份'   when a.openType=1 then '临时卡' else '出入证'  end as openTypeName from Guest_Reg a  left join Per_Persons b  on a.pid=b.pid

GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2016-06-12>  
-- 功能说明： <访客-访客登记视图>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Vw_ParkFree') IS NOT NULL
	DROP VIEW dbo.Vw_ParkFree
GO
Create VIEW [dbo].[Vw_ParkFree]
AS
select a.CardType, a.CardID ,a.CardNo ,a.CardTypeName ,a.CarNO , (a.AccountCharge -a.PayCharge ) as FreeAmount  ,a.InTime , a.OutTime ,a.OutUserName  as UserName,ISNULL( a.FreeName,b.UnusualMemo ) as FreeName,a.FreeType
from  dbo.Vw_Park_CarOut a Left Join
dbo.Park_CentralCharge b on a.InTime=b.InTime and a.CardID=b.CardID
where a.CardType =40
Union
 select a.CardType, a.CardID ,a.CardNo ,b.CCustName  ,a.CarNO , (a.AccountCharge -a.PayCharge ) as FreeAmount ,a.InTime , a.PayChargeTime  ,a.UserName as UserName, a.UnusualMemo  as FreeName,a.FreeType
from  dbo.Park_CentralCharge a Left Join							
dbo.Park_CardType  b ON a.CardType = b.CType 						
 where a.CardType =40
Go


-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2017-02-11>  
-- 功能说明： <家庭组内场内数量>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
IF OBJECT_ID ('dbo.Vw_FamilyGroup_CarIn') IS NOT NULL
	DROP VIEW dbo.Vw_FamilyGroup_CarIn
GO
CREATE VIEW [dbo].Vw_FamilyGroup_CarIn
	AS
select a.ID,MachNo,a.YktID,a.CardID,a.CFlag,a.CardNo,a.EmpName,a.CarNO,a.CardType,a.CarNoType,a.InTime,a.InUserName,a.InPic ,a.CarEventNo,
a.Small,a.DiscountNo,a.TypeID,a.DiscountTime,a.IsLocked,b.PerAddr
 from Park_CarIn  a left join Vw_Ykt_NewCardIssuePark b on a.YktID =b.YktID 
go


-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2017-02-11>  
-- 功能说明： <家庭组明细>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
IF OBJECT_ID ('dbo.Vw_FamilyGroup_CarOut') IS NOT NULL
	DROP VIEW dbo.Vw_FamilyGroup_CarOut
GO
CREATE VIEW [dbo].Vw_FamilyGroup_CarOut
	AS
SELECT     a.ID, a.InMachNo, a.OutMachNo, a.YktID, a.CardID, a.CFlag, a.CardNo, a.EmpName, a.CarNO, a.CarNoType, a.CardType, a.FreeType, a.InTime, a.InPic, 
							a.InUserName, a.CentralTime, a.OutTime, a.OutPic, a.OutUserName, a.ZjPic, a.PayType, a.PayCharge, a.BalanceMoney, a.DiscountNo, a.TypeID, 
							a.DiscountTime, CASE WHEN a.FreeType > 0 THEN 0 ELSE (a.Accountcharge - a.paycharge) END AS DisAmount, a.AccountCharge, a.IsOut, a.UnusualMemo, 
							CASE WHEN isnull(a.FreeType, 0) > 0 THEN (a.Accountcharge - a.paycharge) ELSE 0 END AS FreeAmount, b.ContName AS InControlName ,
							c.ContName AS OutControlName, g.CCustName AS CardTypeName,k.PlaceNo 
	FROM         dbo.ParK_CarOut AS a LEFT OUTER JOIN
							dbo.Park_ControlSet AS b ON a.InMachNo = b.machNo LEFT OUTER JOIN
							dbo.Park_ControlSet AS c ON a.OutMachNo = c.machNo LEFT OUTER JOIN
							dbo.Park_CardType AS g ON a.CardType = g.CType LEFT OUTER JOIN
							dbo.ParK_FreeType AS h ON a.FreeType = h.FreeType left join
                       Vw_Ykt_NewCardIssuePark as f on a.YktID =f.YktID left join
                       Park_FamilyGroup as k on f.PerAddr =k.PlaceNo 
go


-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2016-04-26>  
-- 功能说明： <访客-未知>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，场内车辆打折视图>
-- =============================================

IF OBJECT_ID ('Vw_ParkInDiscount') IS NOT NULL
	DROP VIEW dbo.Vw_ParkInDiscount
GO
CREATE   VIEW [dbo].[Vw_ParkInDiscount]
AS
SELECT     a.ID, a.MachNo, a.CardID, a.CardNo, a.EmpName, a.CarNO, a.CarNoType, a.CardType, a.InTime, a.InUserName, a.Small, a.DiscountNo, a.TypeID, 
                      a.DiscountTime,  b.EqID,b.DisType,b.DisAmount,c.EqName as EquipmentName
FROM         dbo.Park_CarIn AS a LEFT OUTER JOIN
                      dbo.Park_DisInfo AS b ON b.EqID = a.DiscountNo and b.DiscountID=a.TypeID LEFT OUTER JOIN
                      dbo.Park_EquipmentS AS c ON c.EqID = b.EqID 

GO


-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <访客-未知>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Vw_Guest_OrderInfo') IS NOT NULL
	DROP VIEW dbo.Vw_Guest_OrderInfo
GO
CREATE VIEW [dbo].[Vw_Guest_OrderInfo]
AS
SELECT a.*,b.PerName    from Guest_Order a  left join Per_Persons b  on a.pid=b.pid
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <访客-控制器设置信息视图>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Vw_Guest_ContSetInfo') IS NOT NULL
	DROP VIEW dbo.Vw_Guest_ContSetInfo
GO
CREATE VIEW [dbo].[Vw_Guest_ContSetInfo]
AS
SELECT a.ContID,a.ContIP,a.ContName,case when b.InOutType='0' then '进' else '出' end as InOutType ,case when b.ContIP is null then convert(bit,0) else convert(bit,1) end as sel from Door_Cont a
left join Guest_ContSet b on a.ContIP=b.ContIP
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <一卡通-卡下载视图 修正IsNeedDownload判断是否下载的机制，传入机号的拼接字符串>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Vw_YKT_CardIssueParkDownload') IS NOT NULL
	DROP VIEW dbo.Vw_YKT_CardIssueParkDownload
GO
CREATE VIEW [dbo].[Vw_YKT_CardIssueParkDownload]
AS
select * from ( select a.*,case when SUBSTRING(a.download,1,(select MAX(machNo) from Park_ControlSet))=SUBSTRING(a.RightList,1,(select MAX(machNo) from Park_ControlSet)) then 1 else 0 end as IsLoad
 from [dbo].[Vw_Ykt_ALLCardIssuePark] a
 ) a
where a.IsLoad=0-- and a.CFlag<>4
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <??>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Vw_Park_BoxSet') IS NOT NULL
	DROP VIEW dbo.Vw_Park_BoxSet
GO
CREATE VIEW [dbo].[Vw_Park_BoxSet]
AS
 select [chanelName] from  Park_ChanelTemp
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.VW_Door_StateSave') IS NOT NULL
	DROP VIEW dbo.VW_Door_StateSave
GO
 CREATE VIEW [dbo].[VW_Door_StateSave]
AS
	SELECT c.AreaName,a.ContID,b.ContName,a.ContNo,a.ContIP,a.DoorNo,d.DoorName,a.Content,a.[Type],a.[Time] from Door_StateSave a 
	 RIGHT JOIN Door_Cont b on a.ContID = b.ContID
	 RIGHT JOIN Door_Area c ON b.AreaNo = c.AreaNo
	 RIGHT JOIN Door_Door d ON a.DoorNo = d.DoorNo and a.ContID = d.ContID
	 
GO

-- |**************************************************************************************|
-- |******************************| 新增视图(end) |***************************************|
-- |**************************************************************************************|







-- |**************************************************************************************|
-- |******************************| 新增存储过程(begin) |*********************************|
-- |**************************************************************************************|

-- ============================================= 
-- 程序编写： <谭飞> 
-- 建立日期： <2017-03-01>  
-- 功能说明： <门禁-验证导入数据中的部门路径，不存在则创建返回最底层部门编号>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及述二行一次>
-- =============================================
IF OBJECT_ID('Sp_Door_ExistsDeptPath') is not null
	DROP PROCEDURE Sp_Door_ExistsDeptPath
go
CREATE PROCEDURE [dbo].[Sp_Door_ExistsDeptPath] 	
	@DeptPath varchar(100)		
AS
	DECLARE @deptname varchar(20)
	DECLARE @icount int = 0
	DECLARE @DeptCode varchar(10) = ''
	declare @cursor cursor;--游标
	set @cursor=cursor for 
	SELECT col FROM dbo.f_splitSTR(@DeptPath,'/')	
	open @cursor
	fetch next from @cursor into @deptname;
	while @@FETCH_STATUS=0
	begin
	  IF(@icount = 0)
	  begin
		IF exists(SELECT DeptID FROM Per_Dept where DeptName = @deptname AND TopDeptID = '0')
			SELECT @DeptCode = DeptID FROM Per_Dept where DeptName = @deptname AND TopDeptID = '0'
		else
		begin
			DECLARE @ret varchar(10)=''
			declare @String decimal(4,0),@subret varchar(10)
			WHILE(@ret = '')
			begin
				select @String=(9000*rand() + 1000)
				select @subret = upper(char(cast(rand()*26+97 as int))) + CONVERT(varchar(10),@String)
				IF not exists(SELECT * FROM Per_Dept where DeptID = @subret)
				BEGIN
					set @ret = @subret
				END
			end
			insert into Per_Dept(DeptID,DeptName,TopDeptID,OrgId,UpdateFlag) VALUES(@ret,@deptname,'0','A8888',1)
			IF @@rowcount > 0
			begin
				set @DeptCode = @ret
			end
		end
	  end
	  else
	  begin
		IF exists(SELECT DeptID FROM Per_Dept where DeptName = @deptname AND TopDeptID = @DeptCode)
			SELECT @DeptCode = DeptID FROM Per_Dept where DeptName = @deptname AND TopDeptID = @DeptCode
		else
		begin
			DECLARE @ret1 varchar(10)=''
			declare @String1 decimal(4,0),@subret1 varchar(10)
			WHILE(@ret1 = '')
			begin
				select @String1=(9000*rand() + 1000)
				select @subret1 = upper(char(cast(rand()*26+97 as int))) + CONVERT(varchar(10),@String1)
				IF not exists(SELECT * FROM Per_Dept where DeptID = @subret1)
				BEGIN
					set @ret1 = @subret1
				END
			end
			insert into Per_Dept(DeptID,DeptName,TopDeptID,OrgId,UpdateFlag) VALUES(@ret1,@deptname,@DeptCode,'A8888',1)
			IF @@rowcount > 0
			begin
				set @DeptCode = @ret1
			end
		end
	  end
	  set @icount = @icount + 1
	  fetch next from @cursor into @deptname;
	end
	close @cursor
	deallocate @cursor	
	SELECT @DeptCode as DeptCode
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <一卡通->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Ykt_CardQuantityReport') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Ykt_CardQuantityReport
GO
	CREATE PROCEDURE Sp_Ykt_CardQuantityReport
		-- Add the parameters for the stored procedure here
		@begintime datetime,--开始日期
		@endtime datetime --结束日期
	AS
	BEGIN
		-- SET NOCOUNT ON added to prevent extra result sets from
		-- interfering with SELECT statements.
	select (select count(*) from Park_CardType where ctype <=b.ctype) as rowindex,b.CCustName AS CName,
	SUM(1)as cardcount,SUM(case when states=1 or states=2 then 1 else 0 end) as losecardcount,
	SUM(case when States=4 or States=5 or States=6 then 1 else 0 end) as refundcardcount,
	SUM(case when Newstate='已过期' then 1 else 0 end) as overdatecount,
	SUM(case when Newstate<>'已过期' then 1 else 0 end) as nomalcardcount,
	SUM(1)- SUM(case when States=4 or States=5 or States=6 then 1 else 0 end) as acturecardcount 
	from dbo.Vw_YKT_CardIssueNewInfo as a 
	right outer join Park_CardType as b on a.ctype=b.ctype 
	where  b.NoUse=0 and b.IsSend=1 and a.UserDate between '2014-01-01' and '2014-12-31' group by b.CCustName,b.CType
	order by rowindex

	END
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <一卡通->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Ykt_DoorCardMend') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Ykt_DoorCardMend
GO
	CREATE PROCEDURE [dbo].[Sp_Ykt_DoorCardMend]
		-- Add the parameters for the stored procedure here
		@YKTID int
		
	AS
	BEGIN
		--错误代号
		declare @errorcode int
		--新插入的一卡通编号
		declare @newyktid int

		--开始复制一行一卡通门禁副表信息
		if(@errorcode=0)
		begin
		insert into dbo.YKT_CardIssueDoor select YKTID=@newyktid,CardType,PlanID1,PlanID2,PlanID3,PlanID4,StartDate,EndDate,CardPwd,GradeID,UserDate,UserName,Remark,DownLoad,FDownLoad,PlanID5,PlanID6 from dbo.YKT_CardIssueDoor where YKTID=@YKTID
		select @errorcode=@@ERROR
		end
	END
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <一卡通-挂失、解挂GradID不设置为0.IF @STATES <> 1 and @STATES<>3>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Ykt_CardRsMoney_SimpleCardOperate') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Ykt_CardRsMoney_SimpleCardOperate
GO
	Create PROCEDURE [dbo].[Sp_Ykt_CardRsMoney_SimpleCardOperate]
	@YKTID int,
	@STYPE TINYINT,
	@FRONTDATE SMALLDATETIME,
	@STARTDATE SMALLDATETIME,
	@ENDDATE SMALLDATETIME,
	@BALANCEMONEY MONEY,
	@PAYTYPE TINYINT,
	@FOREGIFT MONEY,
	@BEFOREMONEY MONEY,
	@USERDATE DATETIME,
	@USERNAME VARCHAR(20),
	@REMARK NVARCHAR(50),
	@STATES TINYINT
	
AS
BEGIN
	declare @errorcode int
	select @errorcode=@@ERROR
	begin tran
	if(@errorcode=0)
	begin
	--添加用户充值记录
	insert into dbo.YKT_CardRsMoney(YKTID,SType,FrontDate,NStartDate,NEndDate,BalanceMoney,PayType,Foregift,BeforeMoney,UserDate,UserName,Remark) values (@YKTID,@STYPE,@FRONTDATE,@STARTDATE,@ENDDATE,@BALANCEMONEY,@PAYTYPE,@FOREGIFT,@BEFOREMONEY,@USERDATE,@USERNAME,@REMARK)
	select @errorcode=@@ERROR
	end

	if(@errorcode=0)
	--执行修改车场卡片表中的卡片状态2挂失3解挂5退款6销户
	begin
	update dbo.YKT_CardIssue set [States]=@STATES,UserDate=GETDATE() where YKTID=@YKTID
	select @errorcode=@@ERROR	
	end
	
     
	
	if(@errorcode=0)
	--执行修改车场卡片表中的卡片状态2挂失3解挂5退款6销户
	begin
	--只有消费的情况，直接更新为正常
	if (@STATES=3)
		update dbo.YKT_CardIssue set [States]=0,UserDate=GETDATE() where YKTID=@YKTID and SysRight='00100000000000000000'
		select @errorcode=@@ERROR	
	end
	
	if(@errorcode=0)
	--执行重置下载状态
	begin
	update dbo.YKT_CardIssuePark set [DownLoad]=dbo.PadLeft('0', '0', 255),UserDate=GETDATE() where YKTID=@YKTID
	select @errorcode=@@ERROR
	end
	if(@errorcode=0)
	--门禁退卡，级别GradeID=0，下载时直接从控制器清除。
	begin
    IF @STATES <> 1 and @STATES<>3
	   update dbo.YKT_CardIssueDoor set [GradeID]=0,UserDate=GETDATE() where YKTID=@YKTID
	select @errorcode=@@ERROR
	end
	if(@errorcode=0)
	commit transaction
	else
	rollback transaction

	return @errorcode
END
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <一卡通-挂失、解挂GradID不设置为0.IF @STATES <> 1 and @STATES<>3>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Interface_SimpleCardOperate') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Interface_SimpleCardOperate
GO
	CREATE PROCEDURE [dbo].[Sp_Interface_SimpleCardOperate]
		@CarNo varchar(20),
		@Type TINYINT
	AS
	BEGIN
		declare @errorcode int
		declare @status int,@YKTID int,@STYPE int,@REMARK varchar(50)
		declare @STATES TINYINT
		if exists(SELECT 1 from Ykt_CardIssuePark where (CardType BETWEEN 10 AND 20) and CarNO = @CarNo)
		BEGIN
			SELECT @YKTID=YKTID from Ykt_CardIssuePark where (CardType BETWEEN 10 AND 20) and CarNO = @CarNo
			IF(@Type = 0)
			begin
				set @STATES = 1
				set @STYPE = 2
				set @REMARK = '挂失'
			end
			IF(@Type = 1)
			begin
				set @STATES = 3
				set @STYPE = 3
				set @REMARK = '解挂'
			end
			IF(@Type = 2)
			begin
				set @STATES = 6
				set @STYPE = 6
				set @REMARK = '销卡'
			end
			select @errorcode=@@ERROR
			begin tran
			if(@errorcode=0)
			begin
				--添加用户充值记录
				insert into dbo.YKT_CardRsMoney(YKTID,SType,FrontDate,NStartDate,NEndDate,BalanceMoney,PayType,Foregift,BeforeMoney,UserDate,UserName,Remark) values 
				(@YKTID,@STYPE,GETDATE(),GETDATE(),GETDATE(),0,0,0,0,GETDATE(),'云平台用户',@REMARK)
				select @errorcode=@@ERROR
			end

			if(@errorcode=0)
			--执行修改车场卡片表中的卡片状态2挂失3解挂5退款6销户
			begin
				update dbo.YKT_CardIssue set [States]=@STATES where YKTID=@YKTID
				select @errorcode=@@ERROR	
			end
			if(@errorcode=0)
			--执行重置下载状态
			begin
				update dbo.YKT_CardIssuePark set [DownLoad]=dbo.PadLeft('0', '0', 255) where YKTID=@YKTID
				select @errorcode=@@ERROR
			end
			if(@errorcode=0)
			begin
				set @status = 0
				commit transaction
			end
			else
			begin
				set @status = 2
				rollback transaction
			end
		END
		ELSE
			set @status = 1

		select @status
	END
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <一卡通->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Ykt_CardMend') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Ykt_CardMend
GO
	CREATE PROCEDURE [dbo].[Sp_Ykt_CardMend]
	-- Add the parameters for the stored procedure here
	@YKTID int,
	@CARDID VARCHAR(20),
	@CARDNO VARCHAR(10),
	@USERNAME varchar(20),
	@USERDATE DATETIME
	
AS
BEGIN
	--错误代号
	declare @errorcode int
	--新插入的一卡通编号
	declare @newyktid int
	select @errorcode=@@ERROR
	begin tran
	--开始复制一行一卡通主表信息,新卡片状态改为正常状态,将新卡的卡号和卡编号同时进行修改
	if(@errorcode=0)
	begin
	update YKT_CardIssuePark set DownLoad=dbo.PadLeft('0','0',255) where yktid=@YKTID
	insert into Ykt_CardIssue select YktID,CardID=@CARDID,CFlag,CardNO=@CARDNO,PID,PerName,Foregift,States=0,SysRight,Remark,UserDate=@USERDATE,UserName=@USERNAME from Ykt_CardIssue where YktID=@YKTID		
	select @newyktid=@@IDENTITY
	select @errorcode=@@ERROR		
	end
	---修改复制的一条一卡通的一卡通编号
	if(@errorcode=0)
	begin
	update Ykt_CardIssue set YktID=@newyktid WHERE ID=@newyktid
	select @errorcode=@@ERROR
	end
	--将原卡片的状态改为挂失状态
	if(@errorcode=0)
	begin
	update dbo.Ykt_CardIssue set States=4 where ID=@YKTID
	select @errorcode=@@ERROR
	end

	--开始复制一行一卡通副表信息
	if(@errorcode=0)
	begin
	insert into dbo.YKT_CardIssuePark select YKTID=@newyktid,CardType,sType,CarNO,CarNoType,CarPlace,CarColour,StartDate,EndDate,BalanceMoney,PlanID,UserDate=@USERDATE,UserName=@USERNAME,Remark,DownLoad from dbo.YKT_CardIssuePark where YKTID=@YKTID
	select @errorcode=@@ERROR
	end
	--开始复制一行一卡通交易信息
	if(@errorcode=0)
	begin
	insert into dbo.YKT_CardRsMoney(YKTID,SType,FrontDate,NStartDate,NEndDate,BalanceMoney,PayType,Foregift,BeforeMoney,UserDate,UserName,Remark,YRecordNumber,WeChatNumber,CardBalance,PresentMoney) 
	select YKTID=@newyktid,SType=4,FrontDate,NStartDate,NEndDate,BalanceMoney,PayType,Foregift,BeforeMoney,UserDate=@USERDATE,UserName=@USERNAME,Remark='已补卡',YRecordNumber=NULL,WeChatNumber=NULL,CardBalance,PresentMoney  from YKT_CardRsMoney where YKTID=@YKTID and SType=0
	select @errorcode=@@ERROR																																									
	end
	--修改门禁副表信息
	if(@errorcode=0)
	begin
	insert into dbo.YKT_CardIssueDoor select YktID=@newyktid,[CardType],[PlanID1],[PlanID2],[PlanID3],[PlanID4],[StartDate],[EndDate],[CardPwd],[GradeID],[UserDate],[UserName],[Remark],[DownLoad],FDownLoad,PlanID5,PlanID6 from YKT_CardIssueDoor WHERE YktID=@YKTID
	select @errorcode=@@ERROR	
	end
	if(@errorcode=0)
	commit transaction
	else
	rollback transaction

	return @errorcode
END

GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <一卡通->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Ykt_CardIssue_ReWriteCardContent') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Ykt_CardIssue_ReWriteCardContent
GO
	CREATE PROCEDURE [dbo].[Sp_Ykt_CardIssue_ReWriteCardContent]
		-- Add the parameters for the stored procedure here
		@pid int,
		@orginpid int,
		@pername nvarchar(20),
		@remark nvarchar(50),
		@planid tinyint,
		@carno nvarchar(10),
		@carnotype tinyint,
		@carcolour nvarchar(10),
		@carplace nvarchar(10),
		@parkremark nvarchar(50),
		@yktid int,
		@balancemoney money,
		@sysright varchar(20)
	AS
	BEGIN
		--错误代号
		declare @errorcode int
		select @errorcode=@@ERROR
		begin tran
		if(@errorcode=0)
		begin
		--如果两次人事有变化，需要重新更新人事状态
		if(@pid!=@orginpid)
		begin
		update dbo.Per_Persons set [status]=0 where Pid=@orginpid
		select @errorcode=@@ERROR
		end
		if(@errorcode=0)
		begin
		update dbo.Per_Persons set [status]=1 where Pid=@pid
		select @errorcode=@@ERROR
		end
		if(@errorcode=0)
		begin
		update Ykt_CardIssue set  pid=@pid,pername=@pername,sysright=@sysright,remark=@remark where id=@yktid
		select @errorcode=@@ERROR
		end
		if(@errorcode=0)
		begin
		update YKT_CardIssuePark set [CarNO]=@carno,[carnotype]=@carnotype,[CarPlace]=@carplace,[CarColour]=@carcolour,[BalanceMoney]=@balancemoney,[PlanID]=@planid,[Remark]=@parkremark where yktid=@yktid
		update YKT_CardIssuePark set [DownLoad]=dbo.PadLeft('0','0',255) where yktid=@yktid
		select @errorcode=@@ERROR
		end

		end
		if(@errorcode=0)
		commit transaction
		else
		rollback transaction

		return @errorcode
	END
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <一卡通->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Ykt_CardIssue_ReCharge') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Ykt_CardIssue_ReCharge
GO
	CREATE PROCEDURE [dbo].[Sp_Ykt_CardIssue_ReCharge]
		-- Add the parameters for the stored procedure here
		@YKTID int,							--开户ID号
		@STYPE TINYINT,						--操作类型（0卡发行1卡延期2挂失3解挂4补发5退款6销户）
		@FRONTDATE DATETIME,				--前结束日期（延期用）
		@STARTDATE DATETIME,				--新起始日期（延期用）
		@ENDDATE DATETIME,					--新终止日期（延期用）
		@BALANCEMONEY MONEY,				--发生金额
		@PAYTYPE TINYINT,					--付款方式(0现金1银联闪付2微信3支付宝)
		@FOREGIFT MONEY,					--押金
		@BEFOREMONEY MONEY,					--发生前余额（储值卡用）
		@USERDATE DATETIME,					--操作时间
		@USERNAME VARCHAR(20),				--操作员
		@REMARK NVARCHAR(50),				--备注
		@YRecordNumber varchar(20)=NULL,	--云交易记录编号
		@WeChatNumber varchar(20)=NULL		--微信交易编号
	AS
	BEGIN
		declare @errorcode int
		select @errorcode=@@ERROR
		begin tran
		if(@errorcode=0)
		begin
		--添加用户充值记录
		insert into dbo.YKT_CardRsMoney(YKTID,SType,FrontDate,NStartDate,NEndDate,BalanceMoney,PayType,Foregift,BeforeMoney,UserDate,UserName,Remark,YRecordNumber,WeChatNumber) values (@YKTID,@STYPE,@FRONTDATE,@STARTDATE,@ENDDATE,@BALANCEMONEY,@PAYTYPE,@FOREGIFT,@BEFOREMONEY,@USERDATE,@USERNAME,@REMARK,@YRecordNumber,@WeChatNumber)
		select @errorcode=@@ERROR
		end

		if(@errorcode=0)
		--执行修改车场卡片副表中的开始日期,结束日期,储值金额,BalanceMoney=@BALANCEMONEY
		begin
		update dbo.YKT_CardIssuePark set EndDate=@ENDDATE,[DownLoad]=dbo.PadLeft('0', '0', 255),UserDate=GETDATE() where YKTID=@YKTID
		select @errorcode=@@ERROR	
		end

		if(@errorcode=0)
		commit transaction
		else
		rollback transaction

		return @errorcode
	END
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <一卡通->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Ykt_CardIssue_PublishCard') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Ykt_CardIssue_PublishCard
GO
	CREATE PROCEDURE [dbo].[Sp_Ykt_CardIssue_PublishCard]
		-- Add the parameters for the stored procedure here
		@cardid varchar(20),
		@cflag tinyint,
		@cardno varchar(10),
		@pid int,
		@pername nvarchar(20),
		@foregift money,
		@states tinyint,
		@sysright varchar(20),
		@remark nvarchar(50),
		@userdate datetime,
		@username nvarchar(20),

		@cardtype tinyint,
		@stype tinyint,
		@carno nvarchar(10),
		@carnotype tinyint,
		@carplace nvarchar(10),
		@carcolor varchar(10),
		@startdate datetime,
		@enddate datetime,
		@balancemoney money,
		@planid tinyint,
		@parkremark nvarchar(50),
		@download varchar(255),
		@paytype tinyint,
		@hasright int
	AS
	BEGIN
		--错误代号
		declare @errorcode int
		--一卡通编号
		declare @yktid int
		select @errorcode=@@ERROR
		begin tran
		if(@errorcode=0)
		begin
		--添加主卡表信息
		insert into dbo.Ykt_CardIssue(YktID,CardID,CFlag,CardNO,PID,PerName,Foregift,States,SysRight,Remark,UserDate,UserName) values (0,@cardid,@cflag,@cardno,@pid,@pername,@foregift,@states,@sysright,@remark,@userdate,@username)
		select @yktid=@@IDENTITY
		select @errorcode=@@ERROR
		end
		if(@errorcode=0)
		begin
		--修改YKT编号
		update dbo.Ykt_CardIssue set YktID=@yktid where ID=@yktid
		select @errorcode=@@ERROR
		end
		if(@hasright=1 and @errorcode=0)
		begin
		--添加副表信息
		insert into dbo.YKT_CardIssuePark(YKTID,CardType,sType,CarNO,CarNoType,CarPlace,CarColour,StartDate,EndDate,BalanceMoney,PlanID,UserDate,UserName,Remark,DownLoad) values (@yktid,@cardtype,@stype,@carno,@carnotype,@carplace,@carcolor,@startdate,@enddate,@balancemoney,@planid,@userdate,@username,@parkremark,@download)
		select @errorcode=@@ERROR	
		end
		--添加交易信息
		if(@errorcode=0)
		begin
		insert into dbo.YKT_CardRsMoney(YKTID,SType,FrontDate,NStartDate,NEndDate,BalanceMoney,PayType,Foregift,BeforeMoney,UserDate,UserName,Remark) values (@yktid,0,@startdate,@startdate,@enddate,@balancemoney,@paytype,@foregift,0,@userdate,@username,'开户')
		select @errorcode=@@ERROR	
		end
		if(@errorcode=0)
		begin
		--更改用户发卡状态
		update dbo.Per_Persons set [status]=1 where [id]=@pid
		select @errorcode=@@ERROR
		end
		if(@errorcode=0)
		commit transaction
		else
		rollback transaction

		return @errorcode
	END
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <一卡通->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.sp_CreateNewCard') IS NOT NULL
	DROP PROCEDURE dbo.sp_CreateNewCard
GO
	CREATE PROC  [dbo].[sp_CreateNewCard]
@CarNo VARCHAR(50),
@CarId VARCHAR(50),
@cardTypeName Varchar(50),
@startDate datetime,
@endDate datetime,
@perName varchar(20),
@BalanceMoney money,
@Operater varchar(20)
AS
BEGIN
	DECLARE @DeperID VARCHAR(50)
	DECLARE @cardType INT = 11
	IF EXISTS (SELECT TOP 1 * FROM dbo.Per_Dept WHERE DeptName LIKE '平台客户')
	BEGIN
		SELECT TOP 1 @DeperID=DeptID FROM dbo.Per_Dept WHERE DeptName LIKE '平台客户'  ORDER BY DeptID
	END
	ELSE
	BEGIN
		INSERT INTO dbo.Per_Dept( DeptID ,DeptName ,TopDeptID ,OrgId ,UpdateFlag)
		VALUES  ( 'A9999' , '平台客户', '0', N'','0')
		SET @DeperID=@@IDENTITY
	END
	DECLARE @perID VARCHAR(20)
	IF EXISTS (SELECT TOP 1 * FROM dbo.Per_Persons WHERE PerName LIKE @CarNo)
	BEGIN
		SELECT TOP 1 @perID=ID FROM dbo.Per_Persons WHERE PerName LIKE @CarNo  ORDER BY id DESC
	END
	ELSE
	BEGIN
		DECLARE @count INT =0;
		SELECT @count=COUNT(id)+1 FROM dbo.Per_Persons
		--SELECT @count
		INSERT INTO dbo.Per_Persons(Pid,PerID,PerName,DeptID,Positions,PerAddr,PerTel,LandlineNum,PerSex,PerNative,EduLevel,Isleave,PerType,ReMark,[status],CrUserDate,CrUserName)
		VALUES  (@count,@CarNo,@perName,@DeperID,'员工','','','','男','汉', '大专',0,1,'[API接口发行]',1,getdate(),@Operater)
		SET @perID=@@IDENTITY
	END

	DECLARE @yktID INT 

	INSERT INTO dbo.Ykt_CardIssue( YktID ,CardID ,CFlag ,CardNO ,PID ,PerName ,
			  Foregift ,States ,SysRight ,Remark ,UserDate ,UserName)
	VALUES  ( 0 , @CarId ,4 ,@CarNo , @perID ,@perName , 0.00 ,0 ,'10000000000000000000' , N'[API发行]' ,GETDATE() , @Operater )
	SET @yktID=@@IDENTITY
	update Ykt_CardIssue SET YktID = @yktID where ID = @yktID
	SELECT top 1 @cardType = Isnull(CType,11) from Park_CardType where CName = @cardTypeName
	INSERT INTO dbo.Ykt_CardIssuePark
			( YKTID ,
			  CardType ,
			  sType ,
			  CarNO ,
			  CarNoType ,
			  CarPlace ,
			  CarColour ,
			  StartDate ,
			  EndDate ,
			  BalanceMoney ,
			  PlanID ,
			  UserDate ,
			  UserName ,
			  Remark ,
			  DownLoad
			)
	VALUES  ( @yktID , -- YKTID - int
			  @cardType, -- CardType - tinyint
			  0 , -- sType - tinyint
			  @CarNo , -- CarNO - nvarchar(10)
			  0 , -- CarNoType - tinyint
			  N'' , -- CarPlace - nvarchar(10)
			  N'' , -- CarColour - nvarchar(10)
			  @startDate , -- StartDate - datetime
			  @endDate , -- EndDate - datetime
			  0 , -- BalanceMoney - money
			  1 , -- PlanID - tinyint
			  GETDATE() , -- UserDate - datetime
			  @Operater , -- UserName - nvarchar(20)
			  N'API Create' , -- Remark - nvarchar(50)
			  '000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000'  -- DownLoad - varchar(255)
			)
	INSERT INTO dbo.Ykt_CardRsMoney
			( YKTID ,
			  SType ,
			  FrontDate ,
			  NStartDate ,
			  NEndDate ,
			  BalanceMoney ,
			  PayType ,
			  Foregift ,
			  BeforeMoney ,
			  UserDate ,
			  UserName ,
			  Remark
			)
	VALUES  ( @yktID, -- YKTID - int
			  0 , -- SType - tinyint
			   @startDate , -- FrontDate - smalldatetime
			   @startDate , -- NStartDate - smalldatetime
			   @endDate , -- NEndDate - smalldatetime
			  @BalanceMoney , -- BalanceMoney - money
			  0 , -- PayType - tinyint
			  0 , -- Foregift - money
			  0 , -- BeforeMoney - money
			  GETDATE() , -- UserDate - datetime
			  @Operater , -- UserName - nvarchar(20)
			  N'API Create'  -- Remark - nvarchar(50)
			)
     SELECT @yktID yktID
END
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <一卡通->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Ykt_ParkLocalSet_DeleteDataByBoxId') IS NOT NULL
	DROP PROCEDURE dbo.Sp_YKT_ParkLocalSet_DeleteDataByBoxId
GO
	CREATE PROCEDURE [dbo].[Sp_YKT_ParkLocalSet_DeleteDataByBoxId]
		@boxId tinyint,
		@id tinyint
		
	AS
	BEGIN
		--错误代号
		declare @errorcode int
		select @errorcode=@@ERROR

		begin tran

		if(@errorcode=0)
		begin
		delete from dbo.Park_LocalSet where BoxID=@boxId
		select @errorcode=@@ERROR
		end

		if(@errorcode=0)
		begin
		delete from Park_devices where ID=@id
		select @errorcode=@@ERROR
		end

		if(@errorcode=0)
		begin
		delete from Park_ControlSet where  BoxID=@boxId
		select @errorcode=@@ERROR	
		end

		if(@errorcode=0)
		commit transaction
		else
		rollback transaction

		return @errorcode
	END
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <一卡通->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Ykt_GetMaxPlanID') IS NOT NULL
	DROP PROCEDURE dbo.Sp_YKT_GetMaxPlanID
GO
	CREATE    PROCEDURE [dbo].[Sp_YKT_GetMaxPlanID]
	
	AS
		Declare @MaxID int
		Declare @SumRs Int
		Declare @TSQL Varchar(200)
		Declare @I int
		select @MaxID=0,@SumRs=0
		if Not exists(Select 1 from Park_ControlPlan)
			Return 1
		Select @MaxID=Max(PlanID),@SumRs=COUNT(*) from Park_ControlPlan
		if @MaxID=@SumRs Return @MaxID+1
		else
			Begin
				select @I=1
				while 1=1
					Begin
						if exists(Select 1 from Park_ControlPlan where PlanID=@I)
							set @I=@I+1
						else
							Return @I
					end 
			end
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <一卡通->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Ykt_GetCardSumMoney') IS NOT NULL
	DROP PROCEDURE dbo.Sp_YKT_GetCardSumMoney
GO
	CREATE PROCEDURE [dbo].[Sp_YKT_GetCardSumMoney]
		@sDay  datetime, 	--开始时间
		@eDay  datetime,	--结束时间
		@SumMode tinyint,	--统计方式 0 按卡类 1 按操作员
		@DayMode Tinyint	--统计时段 0 按天,1按月
	 
	AS
	--金额统计报表
	if @DayMode=0
		begin
			if @SumMode =0 
				select  UserName as SumType,
					sum(case when sType=0 and PayType=0 then Balancemoney else 0 end) as aMoney1,--开户金额
					sum(case when sType=1 and PayType=0 then Balancemoney else 0 end) as bMoney1,--延期金额
					sum(case when sType=4 and PayType=0 then  Balancemoney else 0 end) as cMoney1,--补发金额
					sum(case when (sType=0 or sType=4) and PayType=0 then Foregift else 0 end) as	YMoney1,--押金
					sum(case when PayType=0 then Balancemoney+Foregift else 0 end)  as	SMoney1, --实收金额
					sum(case when sType=0 and PayType=1 then Balancemoney else 0 end) as aMoney2,--开户金额
					sum(case when sType=1 and PayType=1 then Balancemoney else 0 end) as bMoney2,--延期金额
					sum(case when sType=4 and PayType=1 then  Balancemoney else 0 end) as cMoney2,--补发金额
					sum(case when (sType=0 or sType=4) and PayType=1 then Foregift else 0 end) as	YMoney2,--押金
					sum(case when PayType=1 then Balancemoney+Foregift else 0 end)  as	SMoney2, --实收金额
					sum(case when sType>4 then  -Balancemoney else 0 end) as dMoney	--退款金额(退款固定为现金)
					from YKT_CardRsMoney  where UserDate between @sDay and @eDay group by UserName
			else
				select  CName  as SumType,
					sum(case when sType=0 and PayType=0 then Balancemoney else 0 end) as aMoney1,--开户金额
					sum(case when sType=1 and PayType=0 then Balancemoney else 0 end) as bMoney1,--延期金额
					sum(case when sType=4 and PayType=0 then  Balancemoney else 0 end) as cMoney1,--补发金额
					sum(case when (sType=0 or sType=4) and PayType=0 then Foregift else 0 end) as	YMoney1,--押金
					sum(case when PayType=0 then Balancemoney+Foregift else 0 end)  as	SMoney1, --实收金额
					sum(case when sType=0 and PayType=1 then Balancemoney else 0 end) as aMoney2,--开户金额
					sum(case when sType=1 and PayType=1 then Balancemoney else 0 end) as bMoney2,--延期金额
					sum(case when sType=4 and PayType=1 then  Balancemoney else 0 end) as cMoney2,--补发金额
					sum(case when (sType=0 or sType=4) and PayType=1 then Foregift else 0 end) as	YMoney2,--押金
					sum(case when PayType=1 then Balancemoney+Foregift else 0 end)  as	SMoney2, --实收金额
					sum(case when sType>4 then  -Balancemoney else 0 end) as dMoney	--退款金额(退款固定为现金)
					from Vw_YKT_CardRsMoney where UserDate between @sDay and @eDay group by CName
		end

	else
		begin
				select  convert(char(10),UserDate,120) as SumType,
					sum(case when sType=0 and PayType=0 then Balancemoney else 0 end) as aMoney1,--开户金额
					sum(case when sType=1 and PayType=0 then Balancemoney else 0 end) as bMoney1,--延期金额
					sum(case when sType=4 and PayType=0 then  Balancemoney else 0 end) as cMoney1,--补发金额
					sum(case when (sType=0 or sType=4) and PayType=0 then Foregift else 0 end) as	YMoney1,--押金
					sum(case when PayType=0 then Balancemoney+Foregift else 0 end)  as	SMoney1, --实收金额
					sum(case when sType=0 and PayType=1 then Balancemoney else 0 end) as aMoney2,--开户金额
					sum(case when sType=1 and PayType=1 then Balancemoney else 0 end) as bMoney2,--延期金额
					sum(case when sType=4 and PayType=1 then  Balancemoney else 0 end) as cMoney2,--补发金额
					sum(case when (sType=0 or sType=4) and PayType=1 then Foregift else 0 end) as	YMoney2,--押金
					sum(case when PayType=1 then Balancemoney+Foregift else 0 end)  as	SMoney2, --实收金额
					sum(case when sType>4 then  -Balancemoney else 0 end) as dMoney	--退款金额(退款固定为现金)
					from YKT_CardRsMoney  where UserDate between @sDay and @eDay group by convert(char(10),UserDate,120)
		end
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <一卡通->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Park_CarInCheck') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Park_CarInCheck
GO
	CREATE PROCEDURE [dbo].[Sp_Park_CarInCheck]
		@CardID Varchar(20),				--卡ID号
		@CFlag tinyint,						--卡介质(0为IC，1为ID，2IC做ID，3纸票)	
		@Small tinyint,						--小车场(0大车场 1小车场）
		@InTime DateTime,					--入场时间
		@IDSpaceOfTime tinyint				--ID卡出入限制秒数
		 
	AS
		set nocount on
		Declare @MaxOutTime Datetime
		Declare @MaxInTime Datetime
		if @CFlag=0 Or @CFlag=3  --IC卡 纸票
			Begin
				if Exists(Select 1 from dbo.ParK_CarIn where CardID=@CardID and CFlag=@CFlag and InTime=@InTime) 
					return -5 --重复记录
				else
					return 0
			end
		else --ID卡
			--已存在此入场记录
			if  Exists(Select 1 from dbo.ParK_CarIn where CardID=@CardID and CFlag=@CFlag and Small=@Small) 
				Begin
					Select 	@MaxInTime=(Select top 1 InTime from dbo.ParK_CarIn where CardID=@CardID and CFlag=@CFlag and Small=@Small) 		
					if DateDiff(s,@MaxInTime,@InTime)<=3 
						return -5  --重复记录
					else
						return -3  --有入场记录		
				end
			else
				Begin
					if @CFlag<3 and @IDSpaceOfTime>0
						Begin
							Select @MaxOutTime=(Select Max(OutTime) from dbo.ParK_CarOut where CardID=@CardID and CFlag=@CFlag) 				
							if Isnull(@MaxOutTime,'2010-01-01')='2010-01-01'
								Return 0 --
							if DateDiff(s,@MaxOutTime,@InTime)<=@IDSpaceOfTime
								Return -4 --此ID卡不可入场（出入限制）！
						end
				end
		Return 0
		set nocount off
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <一卡通->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_ParkEs1_Add') IS NOT NULL
	DROP PROCEDURE dbo.Sp_ParkEs1_Add
GO
	CREATE PROCEDURE [dbo].[Sp_ParkEs1_Add]
		-- Add the parameters for the stored procedure here
		--主表参数
		@FreeMinute smallint, 
		@FreeInclude tinyint,
		--参数
		@CardType tinyint,
		@UnitType tinyint,
		@TopMoney tinyint,
		@ChHour1 tinyint,
		@ChHour2 tinyint,
		@ChHour3 tinyint,
		@ChHour4 tinyint,
		@ChHour5 tinyint,
		@ChHour6 tinyint,
		@ChHour7 tinyint,
		@ChHour8 tinyint,
		@ChHour9 tinyint,
		@ChHour10 tinyint,
		@ChHour11 tinyint,
		@ChHour12 tinyint,
		@ChHour13 tinyint,
		@ChHour14 tinyint,
		@ChHour15 tinyint,
		@ChHour16 tinyint,
		@ChHour17 tinyint,
		@ChHour18 tinyint,
		@ChHour19 tinyint,
		@ChHour20 tinyint,
		@ChHour21 tinyint,
		@ChHour22 tinyint,
		@ChHour23 tinyint,
		@ChHour24 tinyint,
		@esInfo varchar(100),
		@AZero smallint,
		@AType tinyint
	AS
	BEGIN
		-- SET NOCOUNT ON added to prevent extra result sets from
		-- interfering with SELECT statements.
		--错误代号
		declare @errorcode int
		--记录行数
		declare @datacount int
		select @errorcode=@@ERROR
		begin tran
		if(@errorcode=0)
		begin
		select @datacount=COUNT(1) from dbo.Park_Es1 where CardType=@CardType
			if(@datacount>0)
			begin--修改
			update dbo.Park_Es1 set CardType=@CardType,UnitType=@UnitType,TopMoney=@TopMoney,ChHour1=@ChHour1,ChHour2=@ChHour2,ChHour3=@ChHour3,ChHour4=@ChHour4,ChHour5=@ChHour5,ChHour6=@ChHour6,ChHour7=@ChHour7,ChHour8=@ChHour8,ChHour9=@ChHour9,ChHour10=@ChHour10,ChHour11=@ChHour11,ChHour12=@ChHour12,ChHour13=@ChHour13,ChHour14=@ChHour14,ChHour15=@ChHour15,ChHour16=@ChHour16,ChHour17=@ChHour17,ChHour18=@ChHour18,ChHour19=@ChHour19,ChHour20=@ChHour20,ChHour21=@ChHour21,ChHour22=@ChHour22,ChHour23=@ChHour23,ChHour24=@ChHour24,AZero=@AZero,AType=@AType where CardType = @CardType
			select @errorcode=@@ERROR
			end
			else
			begin
			insert into dbo.Park_Es1(CardType,UnitType,TopMoney,ChHour1,ChHour2,ChHour3,ChHour4,ChHour5,ChHour6,ChHour7,ChHour8,ChHour9,ChHour10,ChHour11,ChHour12,ChHour13,ChHour14,ChHour15,ChHour16,ChHour17,ChHour18,ChHour19,ChHour20,ChHour21,ChHour22,ChHour23,ChHour24,AZero,AType) values (@CardType,@UnitType,@TopMoney,@ChHour1,@ChHour2,@ChHour3,@ChHour4,@ChHour5,@ChHour6,@ChHour7,@ChHour8,@ChHour9,@ChHour10,@ChHour11,@ChHour12,@ChHour13,@ChHour14,@ChHour15,@ChHour16,@ChHour17,@ChHour18,@ChHour19,@ChHour20,@ChHour21,@ChHour22,@ChHour23,@ChHour24,@AZero,@AType)
			select @errorcode=@@ERROR	
			end
		end
		if(@errorcode=0)
		begin
			select @datacount=COUNT(1) from dbo.Park_ESMain where ESNo=0 and CardType=@CardType
			if(@datacount>0)
			begin
			update dbo.Park_ESMain set FreeMinute=@FreeMinute,FreeInclude=@FreeInclude where ESNo=0 and CardType=@CardType
			select @errorcode=@@ERROR
			end
			else
			begin
			insert into dbo.Park_ESMain(ESNo,FreeMinute,FreeInclude,CardType) values (0,@FreeMinute,@FreeInclude,@CardType)
			select @errorcode=@@ERROR
			end
		end
		--执行下位机下载同步操作
		if(@errorcode=0)
		begin
		select @datacount=COUNT(1) from dbo.Park_EsInfos where CardType=@CardType and EsType=0 and ESNo=0
			if(@datacount>0)
			begin
			update dbo.Park_EsInfos set EsInfo=@esInfo where CardType=@CardType and EsType=0 and ESNo=0
			select @errorcode=@@ERROR
			end
			else
			begin
			insert into dbo.Park_EsInfos(CardType,EsInfo,EsNo,EsType) values (@CardType,@esInfo,0,0)
			select @errorcode=@@ERROR
			end
		end
		if(@errorcode=0)
		commit transaction
		else
		rollback transaction

		return @errorcode
	END
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <一卡通->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Park_OverTimeSet_Add') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Park_OverTimeSet_Add
GO
	CREATE PROCEDURE [dbo].[Sp_Park_OverTimeSet_Add]
		-- Add the parameters for the stored procedure here
		@CardType tinyint,
		@OverTimeMinute smallint,
		@FreeInclude tinyint,
		@OverTimeAmount money,
		@OverTimeUnit smallint,
		@FreeMinute smallint,
		@esNo int,
		@esInfo varchar(100)
		
	AS
	BEGIN
		-- SET NOCOUNT ON added to prevent extra result sets from
		-- interfering with SELECT statements.
		--错误代号
		declare @errorcode int
		--标识数据库中是否有一条记录
		declare @datecount int
		--查找错误编号
		select @errorcode=@@ERROR
		begin tran
		if(@errorcode=0)
		begin--执行超时收费记录
			select @datecount = COUNT(1) from dbo.Park_OverTimeSet where CardType=@CardType
			if(@datecount>0)
			begin
				update dbo.Park_OverTimeSet set OverTimeMinute=@OverTimeMinute,FreeInclude=@FreeInclude,OverTimeAmount=@OverTimeAmount,OverTimeUnit=@OverTimeUnit,FreeMinute=@FreeMinute where CardType=@CardType
				select @errorcode=@@ERROR		
			end
			else
			begin
				insert into dbo.Park_OverTimeSet(CardType,OverTimeMinute,FreeInclude,OverTimeAmount,OverTimeUnit,FreeMinute) values (@CardType,@OverTimeMinute,@FreeInclude,@OverTimeAmount,@OverTimeUnit,@FreeMinute)
				select @errorcode=@@ERROR
			end
		end

		--执行下位机下载同步操作
		if(@errorcode=0)
		begin
		select @datecount=COUNT(1) from dbo.Park_EsInfos where CardType=@CardType and EsType=1 and ESNo=@esNo
			if(@datecount>0)
			begin
			update dbo.Park_EsInfos set EsInfo=@esInfo where CardType=@CardType and EsType=1 and ESNo=@esNo
			select @errorcode=@@ERROR
			end
			else
			begin
			insert into dbo.Park_EsInfos(CardType,EsInfo,EsNo,EsType) values (@CardType,@esInfo,@esNo,1)
			select @errorcode=@@ERROR
			end
		end
		if(@errorcode=0)
		commit transaction
		else
		rollback transaction
		return @errorcode
	END
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <一卡通->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Park_NoStandards') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Park_NoStandards
GO
	Create  PROCEDURE [dbo].[Sp_Park_NoStandards]
	@CarNo nvarchar(10)='88888888',	--车牌号码
	@CardType  integer,		--卡类
	@InTime datetime,		--入场时间
	@OutTime datetime,		--出场时间
	@EsType tinyint=0,		--收费类型
	@Amount  money output 		--收费金额
	 
	AS
	declare @FreeMinute as integer   	--免费时间
	declare @FreeInclude as tinyint 	--是否包含免费时间 
	declare @TopAmount as money		--一天最高收费额 
	declare @Xs as tinyint 	--是否包含免费时间 
	declare @ParkDates as integer		--停车天数
	declare @ParkHours as integer		--停车小时数
	declare @ParkMinis as integer		--停车分钟数
	Declare @HourString as varchar(72)
	declare @ZeroType as tinyint		--跨天类型
	declare @ZeroAmount as money		--跨天收费
	Declare @ZeroNums as integer		--天数
	Declare @ZeroDate as Datetime
	Declare @ZeroMinis as integer
	Declare @ZeroMinis2 as integer
	Select @InTime=Cast(Convert(varchar(17),@InTime,120)+'00' as smalldatetime),@OutTime=Cast(Convert(varchar(17),@OutTime,120)+'00' as smalldatetime)
		set @Amount=0
		if (@OutTime<=@InTime)  return
		if @CardType<30 or (@CardType>39 and @CardType<50) or  @CardType>59  return --卡类只有临时卡
		Select @FreeMinute=Isnull(FreeMinute,0),@FreeInclude=Isnull(FreeInclude,0) from Park_ESMain where ESNo=@EsType and CardType=@CardType --标准收费类
		if datediff(minute,@InTime,@OutTime)<=@FreeMinute return
		if Not Exists(Select * from Park_Es1 where CardType=@CardType) return
		Select @TopAmount=isnull(TopMoney,0),@Xs=isnull(UnitType,0),@ZeroAmount=isnull(AZero,0),@ZeroType=Isnull(AType,0) from Park_Es1 where CardType=@CardType --标准收费类
		Select @HourString=Right('000'+Convert(varchar(3),ChHour1),3)+Right('000'+Convert(varchar(3),ChHour2),3)+Right('000'+Convert(varchar(3),ChHour3),3)
		+Right('000'+Convert(varchar(3),ChHour4),3)+Right('000'+Convert(varchar(3),ChHour5),3)+Right('000'+Convert(varchar(3),ChHour6),3)
		+Right('000'+Convert(varchar(3),ChHour7),3)+Right('000'+Convert(varchar(3),ChHour8),3)+Right('000'+Convert(varchar(3),ChHour9),3)
		+Right('000'+Convert(varchar(3),ChHour10),3)+Right('000'+Convert(varchar(3),ChHour11),3)+Right('000'+Convert(varchar(3),ChHour12),3)
		+Right('000'+Convert(varchar(3),ChHour13),3)+Right('000'+Convert(varchar(3),ChHour14),3)+Right('000'+Convert(varchar(3),ChHour15),3)
		+Right('000'+Convert(varchar(3),ChHour16),3)+Right('000'+Convert(varchar(3),ChHour17),3)+Right('000'+Convert(varchar(3),ChHour18),3)
		+Right('000'+Convert(varchar(3),ChHour19),3)+Right('000'+Convert(varchar(3),ChHour20),3)+Right('000'+Convert(varchar(3),ChHour21),3)
		+Right('000'+Convert(varchar(3),ChHour22),3)+Right('000'+Convert(varchar(3),ChHour23),3)+Right('000'+Convert(varchar(3),ChHour24),3)
			from Park_Es1 where CardType=@CardType --标准收费类	
	if @ZeroType=0 
		Select @ZeroNums=datediff(minute,@InTime,@OutTime)/1440
	else
		Select @ZeroNums=datediff(dd,@InTime,@OutTime)
	
	if @FreeMinute>0 and @FreeInclude>0
		Begin  
			select @InTime=dateadd(minute,@FreeMinute,@InTime) --入场时间向后推移
		end
	set @ParkMinis=datediff(minute,@InTime,@OutTime)

	if @ZeroType=1 and datediff(DAY,@InTime,@OutTime)>0
		Begin
			Set @ParkDates=datediff(DAY,@InTime,@OutTime)-1
			Set @ParkMinis=@ParkMinis-@ParkDates*1440
		
			Select @ZeroDate=Convert(datetime,Convert(Varchar(10),@OutTime,120)+' 00:00:00')
			Select @ZeroMinis=datediff(minute,@ZeroDate,@OutTime)
			Select @ZeroMinis2=@ParkMinis-@ZeroMinis
			Set @Amount=0
			if @ZeroMinis>0
				begin
					set @ParkHours= @ZeroMinis / 60    --小时数
					Set @ParkMinis= @ZeroMinis % 60		--剩余分钟数（<60）
					if @ParkMinis>0 set @ParkHours=@ParkHours+1
					Set @Amount=Convert(money,SubString(@HourString,(@ParkHours-1)*3+1,3))
				end
			if @ZeroMinis2 >0
				begin
					set @ParkHours= @ZeroMinis2 / 60    --小时数
					Set @ParkMinis= @ZeroMinis2 % 60		--剩余分钟数（<60）
					if @ParkMinis>0 set @ParkHours=@ParkHours+1
					Set @Amount=@Amount+Convert(money,SubString(@HourString,(@ParkHours-1)*3+1,3))
				end
		end
	else
		Begin
			Set @ParkDates=@ParkMinis / 1440   --天数
			Set @ParkMinis= @ParkMinis % 1440  --剩余分钟数
			set @ParkHours= @ParkMinis / 60    --小时数
			Set @ParkMinis= @ParkMinis % 60		--剩余分钟数（<60）
			if @ParkMinis>0 set @ParkHours=@ParkHours+1
			Set @Amount=Convert(money,SubString(@HourString,(@ParkHours-1)*3+1,3))
		end
	Select @Amount=@Amount+@ParkDates*@TopAmount+@ZeroNums*@ZeroAmount
	if @Xs=1 select @Amount=@Amount/10
	if (@@ERROR<>0) or @Amount<0
		select @Amount=0
		return
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <一卡通->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Park_ControlSetGetSortId') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Park_ControlSetGetSortId
GO
	CREATE PROCEDURE [dbo].[Sp_Park_ControlSetGetSortId]
		-- Add the parameters for the stored procedure here
		@boxId int
		
	AS
	BEGIN
		Declare @MaxID int
		Declare @SumRs Int
		Declare @I int
		select @MaxID=0,@SumRs=0
		if Not exists(Select 1 from Park_ControlSet where BoxID=@boxId)
			Return 1
		Select @MaxID=Max(SortID),@SumRs=COUNT(1) from Park_ControlSet where BoxID=@boxId
		if @MaxID=@SumRs Return @MaxID+1
		else
			Begin
				select @I=1
				while 1=1
					Begin
						if exists(Select BoxID from Park_ControlSet where BoxID=@boxId and SortID=@I)
							set @I=@I+1
						else
							Return @I
					end 
			end
	END
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <一卡通->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Park_ControlSet_UpdateControlSort') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Park_ControlSet_UpdateControlSort
GO
	create PROCEDURE [dbo].[Sp_Park_ControlSet_UpdateControlSort]
		-- Add the parameters for the stored procedure here
		@preid int,
		@lid int,
		@type varchar(10),
		@sortId tinyint,
		@boxid int
		
	AS
	BEGIN
		--错误代号
		declare @errorcode int
		select @errorcode=@@ERROR
		begin tran
		if(@type='middle')
			begin
			if(@errorcode=0)
			begin
				update dbo.Park_ControlSet set SortID=SortID-1 where BoxID=@boxid and machNo=@preid
				select @errorcode=@@ERROR
			end
			if(@errorcode=0)
			begin
				update dbo.Park_ControlSet set SortID=SortID+1 where BoxID=@boxid and machNo=@lid
				select @errorcode=@@ERROR
			end
		end
		else if(@type='top')
		begin
			if(@errorcode=0)
			begin
			update dbo.Park_ControlSet set SortID=1 where BoxID=@boxid and SortID=@sortId
			update dbo.Park_ControlSet set SortID=SortID+1 where BoxID=@boxid and SortID<@sortId and machNo!=@preid
			--update dbo.Park_CamSet set SortID=SortID-1 where SortID>@sortId
			select @errorcode=@@ERROR
			end
		end
		else if(@type='bottom')
		begin
		update dbo.Park_ControlSet set SortID=(select COUNT(1) from Park_ControlSet where BoxID=@boxid)  where BoxID=@boxid and SortID=@sortId
		update dbo.Park_ControlSet set SortID=SortID-1 where BoxID=@boxid and SortID>=@sortId and machNo!=@preid
		select @errorcode=@@ERROR
		end
		if(@errorcode=0)
		commit transaction
		else
		rollback transaction

		return @errorcode
	END
GO


IF OBJECT_ID ('dbo.FunFee4') IS NOT NULL
	DROP Function dbo.FunFee4
GO
CREATE Function [dbo].[FunFee4](@InDate varchar(16),@OutDate varchar(16),@StartPeriod varchar(5),@EndPeriod Varchar(5))
Returns int 
With ENCRYPTION 
as
Begin
   Declare @InOne  tinyint
   Declare @KK tinyint

	if Convert(varchar(10),@OutDate,120)>Convert(varchar(10),@InDate,120)
			Select @InOne=1  --跨天
	else
			Select @InOne=0	

	  Declare @TmpInDate varchar(16)
	  Declare @TmpOutDate varchar(16)
	  Declare @TmpSPeriod  varchar(16)
	  Declare @TmpEPeriod varchar(16)

	  Select @KK=0
	 if  @InOne=0  --不跨天
		Begin
		  Select @TmpInDate=right(Convert(varchar(16),@InDate,120),5) -- 16 22:01
		  Select @TmpOutDate=right(Convert(varchar(16),@OutDate,120),5)-- 17 22:00
		  if @StartPeriod>@EndPeriod --跨天**********
			Begin
				Select @KK=Case
				 when @TmpInDate>=@EndPeriod and @TmpOutDate<=@StartPeriod 
					then 0
				 when  @TmpInDate<@EndPeriod and @TmpOutDate>@StartPeriod 
					then 2
				 else 1 end
			end
		  else
			  if  (@TmpInDate>=@StartPeriod and @TmpInDate<@EndPeriod) or (@TmpOutDate>@StartPeriod and @TmpOutDate<=@EndPeriod) or (@TmpInDate<@StartPeriod and @TmpOutDate>@EndPeriod)
				Select @KK=1
		end
	else
		  if @StartPeriod>@EndPeriod --跨天
			Begin
			  Select @TmpInDate=Convert(varchar(16),@InDate,120) -- 16 22:01
			  Select @TmpOutDate=Convert(varchar(16),@OutDate,120) -- 17 22:00

			  Select @TmpSPeriod=Convert(varchar(11),@InDate,120) + @StartPeriod --16 22:00
			  Select @TmpEPeriod=Convert(varchar(11),@InDate,120) + @EndPeriod   --16 08:00
			
			  if @TmpInDate<@TmpEPeriod and @TmpOutDate>=@TmpSPeriod 
				Select @KK=2
			  else 
				Begin
				  	Select @TmpSPeriod=Convert(varchar(11),@OutDate,120) + @StartPeriod --17 22:00
	 			        Select @TmpEPeriod=Convert(varchar(11),@OutDate,120) + @EndPeriod   --17 08:00
					if @TmpInDate<=@TmpEPeriod and @TmpOutDate>@TmpSPeriod 
						Select @KK=2										
					else
						Select @KK=1
				end
			end
		  else
			Begin
			  Select @TmpInDate=Convert(varchar(16),@InDate,120)	-- 16 22:01
			  Select @TmpOutDate=Convert(varchar(16),@OutDate,120)	-- 17 22:00

			  Select @TmpSPeriod=Convert(varchar(11),@InDate,120) + @StartPeriod -- 16 08:00
			  Select @TmpEPeriod=Convert(varchar(11),@InDate,120) + @EndPeriod   -- 16 22:00

			  if  (@TmpInDate>=@TmpSPeriod and @TmpInDate<@TmpEPeriod) or (@TmpInDate<@TmpSPeriod and @TmpOutDate>@TmpEPeriod)
				 select @KK=@KK+1

			  Select @TmpSPeriod=Convert(varchar(11),@OutDate,120) + @StartPeriod -- 17 08:00
			  Select @TmpEPeriod=Convert(varchar(11),@OutDate,120) + @EndPeriod   -- 17 22:00

			  if  (@TmpOutDate>@TmpSPeriod and @TmpOutDate<=@TmpEPeriod)  or (@TmpInDate<@TmpSPeriod and @TmpOutDate>@TmpEPeriod)
				select @KK=@KK+1

			end
	
	Return @KK
End

GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <一卡通->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Park_ChargeB') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Park_ChargeB
GO
CREATE  PROCEDURE Sp_Park_ChargeB
@CarNo nvarchar(10)='8888888888', --车牌号码
@CardType integer,		--卡类
@InTime datetime,   		--入场时间
@OutTime datetime,		--出场时间
@EsType tinyint =2,		--车场类型 0 大车场 1 小车场
@Amount money output		--收费金额
 AS
declare @DayBegin as varchar(8) 	--白天开始时间
declare @DayEnd as varchar(8)		--白天结束时间
declare @DayAmount as money		--白天收费标准
declare @DayUnitType as smallint		--白天计费方式

Declare @TmpAmout as money
Declare @TmpInt as smallint
declare @TmpBegin as varchar(8)
declare @TmpEnd as varchar(8)
Declare @TmpTopAmount as money 

declare @NightBegin as varchar(8)	--夜晚开始时间
declare @NightEnd as varchar(8)		--夜晚结束时间
declare @NightAmount as money		--夜晚收费标准
declare @NightUnitType as smallint	--夜晚计费方式

Declare @NightTopAmount as money	--夜晚最高收费
Declare @NightToDayAmount as money 	--剩余夜晚段收费金额
Declare @DayTopAmount as money		--白天最高收费
Declare @nn1 as int
Declare @nn2 as int

declare @FreeMini as smallint		--免费分钟
declare @TopAmount as money  		--最高收费		加载的最高消费
declare @TopCons as money  		--最高消费		按时间段 计算最高消费
declare @ToDayAmount as money		--当天收费
declare @IsInclude as smallint		--不含有
declare @IsTimeAdd as smallint 		--时间跨段补偿 0 无 1有
Declare @TmpDate1 as SmallDatetime	--TmpDate1
Declare @TmpDate2 as SmallDatetime	--TmpDate2
Declare @TmpTime as varchar(8)
Declare @AmountCard as integer		--计费卡类
Declare @TParkMini as int		--总停车分钟
declare @DayMins as integer		--白天段分钟数
declare @NightMins as integer		--夜间段分钟数
select @Amount=0,@ToDayAmount=0,@DayMins=0,@NightMins=0,@TmpTime='',@NightToDayAmount=0,@NightTopAmount=0,@DayTopAmount=0
if @CardType<30 or (@CardType>39 and @CardType<50) or  @CardType>59  --卡类只有临时卡和储值卡
	return
Select @AmountCard=@CardType
Select @InTime=Cast(Convert(varchar(17),@InTime,120)+'00' as smalldatetime),@OutTime=Cast(Convert(varchar(17),@OutTime,120)+'00' as smalldatetime)
Select @TParkMini=datediff(minute,@InTime,@OutTime)

if @TParkMini<=0 
	return
	if not Exists(Select * from [Park_ESMain] where ESNo=2 and CardType=@AmountCard)
		return
	if not Exists(Select * from [Park_Es3] where CardType=@AmountCard)
		return
	Select @FreeMini=Isnull(FreeMinute,0),@IsInclude=Isnull(FreeInclude,0),@IsTimeAdd=isnull(istimeadd,0) from [Park_ESMain] where ESNo=2 and CardType=@AmountCard --通用标准二类
		Select 	@DayBegin=Isnull(DayBegin,'00:00'),@DayEnd=Isnull(DayEnd,'00:00'),@DayAmount=Isnull(ChargingAmount1,0),@DayUnitType=Isnull(ChargingUnit1,0),
			@NightBegin=Isnull(NightBegin,'00:00'),@NightEnd=Isnull(NightEnd,'00:00'),@NightAmount=Isnull(ChargingAmount2,0),@NightUnitType=Isnull(ChargingUnit2,0)
			,@NightTopAmount=Isnull(ChargingAmount3,0),@TopAmount=Isnull(ChargingAmount4,0)
			from [Park_Es3] where CardType=@AmountCard
	if @NightTopAmount>0  and  @TopAmount>0 --白天时段最高收费
			Set @DayTopAmount=@TopAmount-@NightTopAmount				
	
--处理免费时间
if @TParkMini<=@FreeMini
	return
if @FreeMini>0 and @IsInclude>0
	Begin  --时间向后推移
		Select @TParkMini=@TParkMini-@FreeMini
		Select @InTime=DATEADD(mi,@FreeMini,@InTime)
	end

if @DayBegin>@NightBegin  --交换白天与夜间时段
	Begin
		Select @TmpBegin=@DayBegin, @TmpEnd=@DayEnd, @TmpAmout=@DayAmount, @TmpInt=@DayUnitType, @TmpTopAmount=@DayTopAmount
		Select @DayBegin=@NightBegin,@DayEnd=@NightEnd,@DayAmount=@NightAmount,@DayUnitType=@NightUnitType,@DayTopAmount=@NightTopAmount 
		Select @NightBegin=@TmpBegin,@NightEnd=@TmpEnd,@NightAmount=@TmpAmout,@NightUnitType=@TmpInt,@NightTopAmount=@TmpTopAmount
	end	
Select @DayBegin=@DayBegin + ':00'

Select @DayEnd=@DayEnd + ':00'

Select @TmpDate1=Cast(('2009-08-18 '+@DayBegin) as SmallDatetime)

if @DayBegin<@DayEnd
	Select @TmpDate2=Cast(('2009-08-18 '+@DayEnd) as SmallDatetime)
else
	Select @TmpDate2=Cast(('2009-08-19 '+@DayEnd) as SmallDatetime)		
Select @DayMins=DateDiff(mi,@TmpDate1,@TmpDate2) 	--白天分钟数
select @NightMins=1440 - @DayMins			--夜间分钟数
if @TopAmount>0						--如果有最高收费，则先把整数天计算
	begin
		if @TParkMini>=1440  
			begin
				Select @Amount=(@TParkMini/1440)* @TopAmount
				Select @InTime=dateadd(dd,(@TParkMini/1440),@InTime)
				if @TParkMini % 1440 = 0 return
			end
		--跨段补费 
		if @DayUnitType=0 and @NightUnitType=0 and @IsTimeAdd=1
			Begin
				Select @TParkMini=datediff(minute,@InTime,@OutTime)
				if @TParkMini>@NightMins and @TParkMini>@DayMins
					Begin
						Select @Amount = @Amount + @TopAmount
						Return
					end
				Select @nn1=dbo.FunFee4(convert(char(16),@InTime,120),convert(char(16),@outTime,120),Left(@DayBegin,5),Left(@DayEnd,5))			--经过白天段的次数
				Select @nn2=dbo.FunFee4(convert(char(16),@InTime,120),convert(char(16),@OutTime,120),Left(@NightBegin,5),Left(@NightEnd,5))		--经过晚上段的次数
				if @NightAmount>@DayAmount
					Begin
						if @nn1>0 and @nn2=0
							Select @Amount = @Amount + @DayAmount
						else
							Select @Amount = @Amount + @NightAmount
					end
				else
					Begin
						if @nn1=0 and @nn2>0	
							Select @Amount = @Amount + @NightAmount
						else
							Select @Amount = @Amount + @DayAmount
					end
					--select @nn1,@nn2,@DayBegin,@DayEnd,@NightBegin,@NightEnd
					Return
			end
	end
--滚动计费
--select @InTime,@OutTime,@DayBegin,@DayEnd,@DayMins,@NightMins,@DayUnitType,@IsTimeAdd,@DayAmount,@DayUnitType,@NightAmount,@NightUnitType
while @InTime< @OutTime
	begin
		select @TmpTime=right(convert(char(19),@InTime,120),8)
		if @TmpTime>=@DayBegin and @TmpTime<@DayEnd 	--入场时间在白天段,从白天段开始滚
			begin
				if @DayUnitType = 0 		--白天按次,不出理补时
					begin
						select @ToDayAmount=@ToDayAmount +@DayAmount
						Select @TmpDate2=Cast((convert(char(11),@InTime,120)+@DayEnd) as SmallDatetime)
						if @DayEnd<@Daybegin  --跨天
							Select @TmpDate2=dateadd(D,1,@TmpDate2)
						select @InTime=@TmpDate2
					end
				else		--白天按时间单位处理补时情况
					begin
						select @ToDayAmount=@ToDayAmount +@DayAmount
						if @IsTimeAdd>0 --补时
							Select @InTime=dateadd(mi,@DayUnitType,@InTime)			--按一个收费单位滚
						else
							begin
								Select @TmpDate1=dateadd(mi,@DayUnitType,@InTime) 	--按整个白天段分钟数滚
								Select @TmpDate2=Cast((convert(char(11),@InTime,120)+@DayEnd) as SmallDatetime)
								if datediff(mi,@TmpDate2,@TmpDate1)<@DayUnitType and datediff(mi,@TmpDate2,@TmpDate1)>0 
										select @InTime=@TmpDate2
								else
									Begin								
									if @DayEnd<@Daybegin  --跨天
										Select @TmpDate2=dateadd(D,1,@TmpDate2)
									if @TmpDate1>@TmpDate2 
										select @InTime=@TmpDate2
									else
										select @InTime=@TmpDate1
									end
							end
					end 						
			end
		else		--入场时间在夜间段,从夜间段开始滚
			begin
				if @NightUnitType = 0 --夜间按次
					begin
						select @NightToDayAmount=@NightToDayAmount +@NightAmount
						if @TmpTime<@NightEnd 
							Select @TmpDate2=Cast((convert(char(11),@InTime,120)+@NightEnd) as SmallDatetime)
						if @TmpTime>=@Nightbegin
							begin 
								Select @TmpDate2=Cast((convert(char(11),@InTime,120)+@NightEnd) as SmallDatetime)
								Select @TmpDate2=dateadd(D,1,@TmpDate2)
							end			
						--if @NightEnd<@Nightbegin  --跨天
							--Select @TmpDate2=dateadd(D,1,@TmpDate2)
						select @InTime=@TmpDate2
					end
				else		--夜间按时间单位
					begin
						select @NightToDayAmount=@NightToDayAmount +@NightAmount
						if @IsTimeAdd>0
							Select @InTime=dateadd(mi,@NightUnitType,@InTime)--按一个收费单位滚
						else
							begin
								Select @TmpDate1=dateadd(mi,@NightUnitType,@InTime) --按整个夜间段分钟数滚
								Select @TmpDate2=Cast((convert(char(11),@InTime,120)+@NightEnd) as SmallDatetime)
								if datediff(mi,@TmpDate2,@TmpDate1)<@NightUnitType and datediff(mi,@TmpDate2,@TmpDate1)>0 
										select @InTime=@TmpDate2
								else
									Begin
										if @NightEnd<@Nightbegin  --跨天
											Select @TmpDate2=dateadd(D,1,@TmpDate2)
										if @TmpDate1>@TmpDate2 
											select @InTime=@TmpDate2
										else
											select @InTime=@TmpDate1
									end
							end
					end 						
			end	
	end
if @NightTopAmount>0 and @TopAmount>0  --晚上时段最高收费
	begin
		if  @NightToDayAmount>@NightTopAmount
			set @NightToDayAmount=@NightTopAmount
	end 
if @DayTopAmount>0 --白天时段最高收费
	begin
		if  @ToDayAmount>@DayTopAmount
			set @ToDayAmount=@DayTopAmount
	end 

set @ToDayAmount=@ToDayAmount+@NightToDayAmount
if @TopAmount>0	--如果有最高收费
	begin
		if @ToDayAmount>@TopAmount --是否超过最高收费
			select @ToDayAmount=@TopAmount
	end
select @Amount=@Amount+@ToDayAmount
--select @Amount --as 收费金额,@InTime as 入场时间
Return

Go

---通用标准III收费2015/09/01
IF OBJECT_ID ('dbo.Sp_Park_ChargeC') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Park_ChargeC
GO
	/******** 
		标准收费三:分段多时段收费标准
	********/
	Create  PROCEDURE Sp_Park_ChargeC
	@CarNo nvarchar(10)='8888888888',	--车牌号码
	@CardType integer,					--卡类
	@InTime datetime,   				--入场时间
	@OutTime datetime,					--出场时间
	@EsType tinyint=4 ,					--收费类型
	@Amount money output				--收费金额
	 AS
	declare @DayBegin as varchar(8) 	--白天开始时间
	declare @DayEnd as varchar(8)		--白天结束时间
	declare @DayAmount as money			--白天收费标准  之后收费金额
	declare @DayUnitType as smallint		--白天计费方式   0按次收费,>0安时间单位
	declare @sLen1 as smallint			--首段时长（分钟）
	declare @sUnit1 as smallint			--首段收费单位（分钟）
	declare @sAmount1 as Money			----收费金额

	Declare @TmpAmout as money
	Declare @TmpInt as smallint
	declare @TmpBegin as varchar(8)
	declare @TmpEnd as varchar(8)
	Declare @TmpTopAmount as money 
	declare @TmpsLen as smallint			
	declare @TmpsUnit as smallint			
	declare @TmpsAmount as Money			

	declare @NightBegin as varchar(8)	--夜晚开始时间
	declare @NightEnd as varchar(8)		--夜晚结束时间
	declare @NightAmount as money		--夜晚收费标准
	declare @NightUnitType as smallint	--夜晚计费方式 0按次收费,>0安时间单位
	declare @sLen2 as smallint			--首段时长（分钟）
	declare @sUnit2 as smallint			--首段收费单位（分钟）
	declare @sAmount2 as Money			----收费金额

	Declare @NightTopAmount as money	--夜晚最高收费
	Declare @NightToDayAmount as money 	--剩余夜晚段收费金额
	Declare @DayTopAmount as money		--白天最高收费
	Declare @IsS as tinyint             --是否有首时间


	declare @FreeMini as smallint		--免费分钟
	declare @TopAmount as money  		--最高收费		加载的最高消费
	declare @TopCons as money  		--最高消费		按时间段 计算最高消费
	declare @ToDayAmount as money		--当天收费
	declare @IsInclude as smallint		--不含有
	declare @IsTimeAdd as smallint 		--时间跨段补偿 0 无 1有
	Declare @TmpDate1 as SmallDatetime	--TmpDate1
	Declare @TmpDate2 as SmallDatetime	--TmpDate2
	Declare @TmpTime as varchar(8)
	Declare @AmountCard as integer		--计费卡类
	Declare @TParkMini as int			--总停车分钟
	declare @DayMins as integer			--白天段分钟数
	declare @NightMins as integer		--夜间段分钟数
	select @Amount=0,@ToDayAmount=0,@DayMins=0,@NightMins=0,@TmpTime='',@NightToDayAmount=0,@NightTopAmount=0,@DayTopAmount=0
	if @CardType<30 or (@CardType>39 and @CardType<50) or  @CardType>59  --卡类只有临时卡和储值卡
		return
	Select @AmountCard=@CardType
	Select @InTime=Cast(Convert(varchar(17),@InTime,120)+'00' as smalldatetime),@OutTime=Cast(Convert(varchar(17),@OutTime,120)+'00' as smalldatetime)
	Select @TParkMini=datediff(minute,@InTime,@OutTime)
	if @TParkMini<=0 
		return
			if not Exists(Select 1 from [Park_ESMain] where ESNo=@EsType and CardType=@AmountCard)
				return
			if not Exists(Select 1 from [Park_Es5] where CardType=@AmountCard)
				return
			Select @FreeMini=Isnull(FreeMinute,0),@IsInclude=Isnull(FreeInclude,0),@IsTimeAdd=isnull(istimeadd,0) from [Park_ESMain] where ESNo=@EsType and CardType=@AmountCard --标准二类
			Select 	@DayBegin=Isnull(DayBegin,'00:00'),@DayEnd=Isnull(DayEnd,'00:00'),@DayAmount=Isnull(LaseAmount1,0),@DayUnitType=Isnull(LastUnit1,0),
				@sLen1=Isnull(ChargingLength1,0),@sUnit1=Isnull(ChargingUnit1,0),@sAmount1=Isnull(ChargingAmount1,0),
				@NightBegin=Isnull(NightBegin,'00:00'),@NightEnd=Isnull(NightEnd,'00:00'),@NightAmount=Isnull(LaseAmount2,0),@NightUnitType=Isnull(LastUnit2,0),
				@sLen2=Isnull(ChargingLength2,0),@sUnit2=Isnull(ChargingUnit2,0),@sAmount2=Isnull(ChargingAmount2,0),
				@NightTopAmount=Isnull(ChargingAmount3,0),@TopAmount=Isnull(ChargingAmount4,0),@DayTopAmount=Isnull(ChargingAmount5,0)
				from [Park_Es5]  where CardType=@AmountCard

	--处理免费时间
	if @TParkMini<=@FreeMini
		return
	if @FreeMini>0 and @IsInclude>0
		Begin  --时间向后推移
			Select @TParkMini=@TParkMini-@FreeMini
			Select @InTime=DATEADD(mi,@FreeMini,@InTime)
		end

	if @DayBegin>@NightBegin  --交换白天与夜间时段
		Begin
			Select @TmpBegin=@DayBegin, @TmpEnd=@DayEnd, @TmpAmout=@DayAmount, @TmpInt=@DayUnitType, @TmpTopAmount=@DayTopAmount,@TmpsLen=@sLen1,@TmpsUnit=@sUnit1,@TmpsAmount=@sAmount1
			Select @DayBegin=@NightBegin,@DayEnd=@NightEnd,@DayAmount=@NightAmount,@DayUnitType=@NightUnitType,@DayTopAmount=@NightTopAmount,@sLen1=@sLen2,@sUnit1=@sUnit2,@sAmount1=@sAmount2
			Select @NightBegin=@TmpBegin,@NightEnd=@TmpEnd,@NightAmount=@TmpAmout,@NightUnitType=@TmpInt,@NightTopAmount=@TmpTopAmount,@sLen2=@TmpsLen,@sUnit2=@TmpsUnit,@sAmount2=@TmpsAmount
		end	
		
	Select @DayBegin=@DayBegin + ':00'
	Select @DayEnd=@DayEnd + ':00'
	Select @TmpDate1=Cast(('2009-08-18 '+@DayBegin) as SmallDatetime)
	if @DayBegin<@DayEnd
		Select @TmpDate2=Cast(('2009-08-18 '+@DayEnd) as SmallDatetime)
	else
		Select @TmpDate2=Cast(('2009-08-19 '+@DayEnd) as SmallDatetime)		
	Select @DayMins=DateDiff(mi,@TmpDate1,@TmpDate2) 	--白天分钟数
	select @NightMins=1440 - @DayMins			--夜间分钟数
	if @TopAmount>0						--如果有最高收费，则先把整数天计算
		begin
			if @TParkMini>=1440  
				begin
					Select @Amount=(@TParkMini/1440)* @TopAmount
					Select @InTime=dateadd(dd,(@TParkMini/1440),@InTime)
				end
		end
	Select @IsS=1
	--滚动计费
	--select @InTime,@OutTime,@DayBegin,@DayEnd,@DayMins,@NightMins,@DayUnitType,@IsTimeAdd,@DayAmount,@DayUnitType,@NightAmount,@NightUnitType
	while @InTime< @OutTime
		begin
			select @TmpTime=right(convert(char(19),@InTime,120),8)
			if @TmpTime>=@DayBegin and @TmpTime<@DayEnd 	--入场时间在白天段,从白天段开始滚
				begin
					if @DayUnitType = 0 		--白天按次,不处理补时
						begin
							select @ToDayAmount=@ToDayAmount +@DayAmount
							Select @TmpDate2=Cast((convert(char(11),@InTime,120)+@DayEnd) as SmallDatetime)
							if @DayEnd<@Daybegin  --跨天
								Select @TmpDate2=dateadd(D,1,@TmpDate2)
							select @InTime=@TmpDate2
						end
					else		--白天按时间单位处理补时情况
						begin
							if @IsS=1 and @sLen1>0 and @sUnit1>0
								Begin
									Select @TmpDate2=Cast((convert(char(11),@InTime,120)+@DayEnd) as SmallDatetime)
									
									if @TmpDate2<@OutTime  --存在补时
											Select @TmpsLen=datediff(mi,@InTime,@TmpDate2)
									else
											Select @TmpsLen=datediff(mi,@InTime,@OutTime)
									if @TmpsLen>@sLen1
										Select @TmpsUnit=Ceiling(Cast(@sLen1 as money) / @sUnit1)	
									else
										Select @TmpsUnit=Ceiling(Cast(@TmpsLen as money) / @sUnit1)
									select @ToDayAmount=@ToDayAmount + @sAmount1 * @TmpsUnit 
									if @TmpDate2<@OutTime and @IsTimeAdd>0  --存在补时
										Select @InTime=dateadd(mi,@sUnit1*@TmpsUnit,@InTime)			--按收费单位滚	
									else
										Begin
											if @TmpsLen>@sLen1
												Select @InTime=dateadd(mi,@sLen1,@InTime)
											else
												Select @InTime=dateadd(mi,@TmpsLen,@InTime)
										end	
									Select  @IsS=0  --首小时计算后，不再计算
								end
							else
								begin
									select @ToDayAmount=@ToDayAmount +@DayAmount
									if @IsTimeAdd>0 --补时
										Select @InTime=dateadd(mi,@DayUnitType,@InTime)			--按一个收费单位滚
									else
										begin
											Select @TmpDate1=dateadd(mi,@DayUnitType,@InTime) 	--按整个白天段分钟数滚
											Select @TmpDate2=Cast((convert(char(11),@InTime,120)+@DayEnd) as SmallDatetime)
											if datediff(mi,@TmpDate2,@TmpDate1)<@DayUnitType and datediff(mi,@TmpDate2,@TmpDate1)>0 
													select @InTime=@TmpDate2
											else
												Begin								
													if @DayEnd<@Daybegin  --跨天
														Select @TmpDate2=dateadd(D,1,@TmpDate2)
													if @TmpDate1>@TmpDate2 
														select @InTime=@TmpDate2
													else
														select @InTime=@TmpDate1
												end							
										end
								end
						end 						
				end
			else		--入场时间在夜间段,从夜间段开始滚
				begin
					if @NightUnitType = 0 --夜间按次
						begin
							select @NightToDayAmount=@NightToDayAmount +@NightAmount
							if @TmpTime<@NightEnd 
								Select @TmpDate2=Cast((convert(char(11),@InTime,120)+@NightEnd) as SmallDatetime)
							if @TmpTime>=@Nightbegin
								begin 
									Select @TmpDate2=Cast((convert(char(11),@InTime,120)+@NightEnd) as SmallDatetime)
									Select @TmpDate2=dateadd(D,1,@TmpDate2)
								end			
							--if @NightEnd<@Nightbegin  --跨天
								--Select @TmpDate2=dateadd(D,1,@TmpDate2)
							select @InTime=@TmpDate2
						end
					else		--夜间按时间单位
				
					
						begin
							if @IsS=1 and @sLen2>0 and @sUnit2>0
								Begin
									Select @TmpDate2=Cast((convert(char(11),@InTime,120)+@NightEnd) as SmallDatetime)
									if @TmpDate2<@InTime
											Select @TmpDate2=dateadd(D,1,@TmpDate2)
									if @TmpDate2<@OutTime  --存在补时
											Select @TmpsLen=datediff(mi,@InTime,@TmpDate2)
									else
											Select @TmpsLen=datediff(mi,@InTime,@OutTime)
									if @TmpsLen>@sLen2
										Select @TmpsUnit=Ceiling(Cast(@sLen2 as money) / @sUnit2)	
									else
										Select @TmpsUnit=Ceiling(Cast(@TmpsLen as money) / @sUnit2)
									select @NightToDayAmount=@NightToDayAmount + @sAmount2 * Cast(@TmpsUnit as money) 
									if @TmpDate2<@OutTime and @IsTimeAdd>0  --存在补时
										Select @InTime=dateadd(mi,@sUnit2*@TmpsUnit,@InTime)			--按收费单位滚	
									else
										Begin
											if @TmpsLen>@sLen2
												Select @InTime=dateadd(mi,@sLen2,@InTime)
											else
												Select @InTime=dateadd(mi,@TmpsLen,@InTime)
										end								
									Select  @IsS=0  --首小时计算后，不再计算
								end
							else
								begin
									select @NightToDayAmount=@NightToDayAmount +@NightAmount
									if @IsTimeAdd>0
										Select @InTime=dateadd(mi,@NightUnitType,@InTime)--按一个收费单位滚
									else
										begin
											Select @TmpDate1=dateadd(mi,@NightUnitType,@InTime) --按整个夜间段分钟数滚
											Select @TmpDate2=Cast((convert(char(11),@InTime,120)+@NightEnd) as SmallDatetime)
											if datediff(mi,@TmpDate2,@TmpDate1)<@NightUnitType and datediff(mi,@TmpDate2,@TmpDate1)>0 
													select @InTime=@TmpDate2
											else
												Begin
													if @NightEnd<@Nightbegin  --跨天
														Select @TmpDate2=dateadd(D,1,@TmpDate2)
													if @TmpDate1>@TmpDate2 
														select @InTime=@TmpDate2
													else
														select @InTime=@TmpDate1
												end
										end
								end
						end 						
				end	
		end
	if @NightTopAmount>0 and @TopAmount>0  --晚上时段最高收费
		begin
			if  @NightToDayAmount>@NightTopAmount
				set @NightToDayAmount=@NightTopAmount
		end 
	if @DayTopAmount>0 --白天时段最高收费
		begin
			if  @ToDayAmount>@DayTopAmount
				set @ToDayAmount=@DayTopAmount
		end 

	set @ToDayAmount=@ToDayAmount+@NightToDayAmount
	if @TopAmount>0	--如果有最高收费
		begin
			if @ToDayAmount>@TopAmount --是否超过最高收费
				select @ToDayAmount=@TopAmount
		end
	select @Amount=@Amount+@ToDayAmount
	--select @Amount --as 收费金额,@InTime as 入场时间
	Return

GO

---通用标准IIII收费2016/11/18

IF OBJECT_ID ('dbo.Sp_Park_ChargeD') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Park_ChargeD
GO

Create     PROCEDURE Sp_Park_ChargeD
@CarNo nvarchar(10)='8888888888',	--车牌号码
@CardType  integer,	
@InTime datetime,	
@OutTime datetime,	
@EsType tinyint=5,		--收费类型(20150901)
@Amount  money output 	
With ENCRYPTION
AS
select @Amount=0
if (@OutTime<=@InTime)
begin
   set @Amount=0
   return
end
declare @ParkDates as integer		--停车周期
declare @ParkSumMinu as integer		--停车分钟数
declare @ParkMinis as integer		--剩余分钟数
declare @FreeMinute as integer   	--免费费仲数
declare @FreeInclude as smallint		--是否包含
 	
declare @TopAmount as money			--周期最高收费
declare @LowAmount as money			--周期最低收费
declare @ChargeReclHours as smallint --周期小时数
Declare @ChargingUnit as integer	--收费单位
Declare @ChargingAmount as money	--收费金额

Declare @AmountA as Money			--临时收费金额

Select @AmountA=0
if @CardType<30 or (@CardType>39 and @CardType<50) or  @CardType>59 
	return

Select @InTime=Cast(Convert(varchar(17),@InTime,120)+'00' as smalldatetime),@OutTime=Cast(Convert(varchar(17),@OutTime,120)+'00' as smalldatetime)

--从数据库中取值
Select @FreeMinute=Isnull(FreeMinute,0),@FreeInclude=Isnull(FreeInclude,0) from [Park_ESMain] where ESNo=5 and CardType=@CardType --标准四类
select @TopAmount=HighAmount,@LowAmount=ISNULL(CountAmount,0),@ChargingUnit=LateUnit, @ChargingAmount=LateAmount,@ChargeReclHours=ChargeReclHours from [Park_Es6] where CardType=@CardType
if datediff(minute,@InTime,@OutTime)<=@FreeMinute
    begin
		select @Amount=0
		return
    end
if @FreeMinute>0 and @FreeInclude>0
	Begin  
		select @InTime=dateadd(minute,@FreeMinute,@InTime) 
	end
select @ParkSumMinu=datediff(minute,@InTime,@OutTime)
Select @ParkDates=@ParkSumMinu / (@ChargeReclHours *60)
Select @ParkMinis= @ParkSumMinu % (@ChargeReclHours *60)  --剩余分钟数
if @ParkDates=0 and @LowAmount>0
	Begin
		Select @Amount=@LowAmount
		Return
	end
if @TopAmount>0
	Begin
		Select @Amount=@ParkDates*@TopAmount
		Select @AmountA=Ceiling(Cast((@ParkMinis) as Money)/@ChargingUnit)*@ChargingAmount
		if @AmountA>@TopAmount
			Select @AmountA=@TopAmount
		Select @Amount=@Amount+@AmountA
	end
else
		Select @Amount=Ceiling(Cast((@ParkSumMinu) as Money)/@ChargingUnit)*@ChargingAmount

if (@@ERROR<>0) or @Amount<0
   	select @Amount=0

	return

GO



-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <一卡通->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_PKCarRecordDay') IS NOT NULL
	DROP PROCEDURE dbo.Sp_PKCarRecordDay
GO
create PROCEDURE  Sp_PKCarRecordDay         --日报表
	  @OperatorNO varchar(20),            --操作员卡号(日用)
	  @firstdate datetime,                --起始日期
	  @lastdate datetime,                 --结束日期
	  @Flag int                          --表示按年、月、日查询的标致
	
AS
declare @Daybegin as datetime
    truncate table PKCarOutRpt
    if @Flag=1    --查找各类型卡车的车辆数并按操作员分组
	begin
       Insert into PKCarOutRpt(Username,ITempA,ExcepAmount,Imoney,ImonthA,IMoney0,IMoneySum,IFreeA,ITempFree,FreeAmount,DisAMount,PosMoney,OnlinePay,Preferential,TNominal,Account)
	        Select ltrim(rtrim(UserName)),
		Sum(Case when ChargeType='出口收费' and Cardtype In (21,22,23,24,31,32,33,34,40,61,62,63,64) then 1 else 0 end) ItempA,
		0 ExcepAmount,
		Sum(Case when  ((CardType>20 and CardType<40) or (CardType>60 and CardType<70)) and IsNull(PayCharge,0)>0 then IsNull(PayCharge,0) else 0 end) Imoney,  -- --临时卡和月临卡出口收费金额
		Sum(Case when ChargeType='出口收费' and Cardtype in (11,12,13,14,15,16) then 1 else 0 end) ImonthA,
		sum(Case when ChargeType='出口收费' and CardType in (50,51,52,53,54) then 1 else 0 end) IMoney0,
		sum(Case when ChargeType='出口收费' and CardType in (51,52,53,54) then Isnull(PayCharge,0) else 0 end) ImoneySum,
		Sum(Case when ChargeType='出口收费' and Cardtype in (41,42,5) then 1 else 0 end) IFreeA,
		Sum(Case when ChargeType='出口收费' and Cardtype=40 then 1 else 0 end) ITempFree, --临免卡数
		sum(Isnull(FreeAmount,0)) FreeAmount,
		sum(Isnull(DisAmount,0)) DisAmount,
		0 PosMoney,
		0 OnlinePay,0 Preferential,0 TNominal,
		Sum(Case when  ((CardType>20 and CardType<41) or (CardType>60 and CardType<70)) and IsNull(AccountCharge,0)>0 then IsNull(AccountCharge,0) else 0 end) Account  -- --临时卡和月临卡出口收费金额
               from Vw_Park_CarCharge
               Where Cardtype In (5,10,11,12,13,14,15,16,21,22,23,24,31,32,33,34,40,41,42,50,51,52,53,54,61,62,63,64) and (OutTime between  @firstdate and @lastdate) 
               group by  ltrim(rtrim(UserName))
 	   UpDate PKCarOutRpt Set SumQuan=ItempA+ImonthA+IMoney0+IFreeA+Isnull(b.L1,0)+Isnull(b.L2,0),SumPayCharge=TUnout+TOver+Imoney,
 		Gate1=Isnull(b.L1,0),Gate2=Isnull(b.L2,0) from PKCarOutRpt a Left Join
		(Select rtrim(ltrim(OutUserName)) as UserName,Sum(Case when CardType In (1,2,3,4) then 1 else 0 end) as L1,Sum(Case when CardType In (2) then 1 else 0 end) as L2 from ParK_CarOut 
		where  OutTime Between  @firstdate and @lastdate 
		Group By rtrim(ltrim(OutUserName))) b
		On a.Username=b.UserName
		
		Update PKCarOutRpt set ImonthAvg=Isnull(b.ImonthAvg,0) from PKCarOutRpt a Left Join 
		(Select rtrim(ltrim(OutuserName)) as Username,Count(DISTINCT CardID) ImonthAvg  From Vw_ParK_CarOut where (Cardtype>10 and Cardtype<20) and (OutTime between  @firstdate and @lastdate)  Group by  rtrim(ltrim(OutuserName))) b
 		On a.userName=b.UserName
 		IF  not exists(select 1 from PKCarOutRpt)
 		BEGIN
 			Insert into PKCarOutRpt(Username) VALUES(NULL)
 		END
	end

   if @Flag=2   --按月计算
	begin
       Insert into PKCarOutRpt(Username,ITempA,ExcepAmount,Imoney,ImonthA,IMoney0,IMoneySum,IFreeA,ITempFree,FreeAmount,DisAMount,PosMoney,OnlinePay,Preferential,TNominal,Account)
	 Select Right(Convert(Varchar(10),a.Username,120),5),ITempA,ExcepAmount,Imoney,ImonthA,IMoney0,IMoneySum,IFreeA,ITempFree,FreeAmount,DisAMount,PosMoney,OnlinePay,Preferential,TNominal,Account
         from (Select  Convert(Varchar(10),OutTime,120) Username,
		Sum(Case when ChargeType='出口收费' and Cardtype In (21,22,23,24,31,32,33,34,40,61,62,63,64) then 1 else 0 end) ItempA,
		0 ExcepAmount,
		Sum(Case when  Cardtype>20 and Cardtype<40 and IsNull(PayCharge,0)>0 then IsNull(PayCharge,0) else 0 end) Imoney,  -- --临时卡和月临卡出口收费金额
		Sum(Case when ChargeType='出口收费' and Cardtype in (11,12,13,14,15,16) then 1 else 0 end) ImonthA,
		sum(Case when ChargeType='出口收费' and CardType in (50,51,52,53,54) then 1 else 0 end) IMoney0,
		sum(Case when ChargeType='出口收费' and CardType in (51,52,53,54) then Isnull(PayCharge,0) else 0 end) ImoneySum,
		Sum(Case when ChargeType='出口收费' and Cardtype in (41,42,5) then 1 else 0 end) IFreeA,
		Sum(Case when ChargeType='出口收费' and Cardtype=40 then 1 else 0 end) ITempFree, --临免卡数
		sum(Isnull(FreeAmount,0)) FreeAmount,
		sum(Isnull(DisAmount,0)) DisAmount,
		0 PosMoney,
		0 OnlinePay,0 Preferential,0 TNominal,
		Sum(Case when  ((CardType>20 and CardType<41) or (CardType>60 and CardType<70)) and IsNull(AccountCharge,0)>0 then IsNull(AccountCharge,0) else 0 end) Account  -- --临时卡和月临卡出口收费金额
               From Vw_Park_CarCharge
               Where Cardtype In (5,10,11,12,13,14,15,16,21,22,23,24,31,32,33,34,40,41,42,50,51,52,53,54,61,62,63,64) and OutTime between  @firstdate and @lastdate
	       group by Convert(Varchar(10),OutTime,120) ) a
	       Order By a.Username
	       
	  UpDate PKCarOutRpt Set SumQuan=ItempA+ImonthA+IMoney0+IFreeA+Isnull(b.L1,0)+Isnull(b.L2,0),SumPayCharge=TUnout+TOver+Imoney,
 		Gate1=Isnull(b.L1,0),Gate2=Isnull(b.L2,0) from PKCarOutRpt a Left Join
		(Select Right(Convert(Varchar(10),OutTime,120),5) as UserName,Sum(Case when CardType In (1,2,3,4) then 1 else 0 end) as L1,Sum(Case when CardType In (2) then 1 else 0 end) as L2 from ParK_CarOut 
		where  OutTime Between  @firstdate and @lastdate 
		Group By Right(Convert(Varchar(10),OutTime,120),5)) b
		On a.Username=b.UserName
		
	 Update PKCarOutRpt set ImonthAvg=Isnull(b.ImonthAvg,0)  from PKCarOutRpt a Left Join 
		(Select  Right(Convert(Varchar(10),OutTime,120),5) Username,Count(DISTINCT CardID) ImonthAvg  From Vw_ParK_CarOut where (Cardtype>10 and Cardtype<20) and  (OutTime between  @firstdate and @lastdate)
	        Group by  Right(Convert(Varchar(10),OutTime,120),5)) b On a.userName=b.UserName

	end
  /*
  if @Flag=3   --按年计算
	Begin
         Insert into PKCarOutRpt(Username,ImonthA,ImonthB,ImonthC,ImonthD,ImonthE,ImonthF,IsumMonth,IFreeA,IFreeB,IVipCar,IsumFree,ITempA,ITempB,ITempC,ITempD,ISumTemp,Imoney,
	 IMoneyA,IMoneyB,IMoneyC,IMoneyD,ISumMoney,IMoneySum,ITempFree,ZSumCar,ZSumMoney,TUnOut,TOver,ZDisM,Preferential,TNominal)
         Select right('0'+Convert(varchar(2),month(OutTime)),2) Username,
		Sum(Case when Cardtype=11 then 1 else 0 end) ImonthA,
		Sum(Case when Cardtype=12 then 1 else 0 end) ImonthB,
		Sum(Case when Cardtype=13 then 1 else 0 end) ImonthC,
		Sum(Case when Cardtype=14 then 1 else 0 end) ImonthD,
		Sum(Case when Cardtype=15 then 1 else 0 end) ImonthE,
		Sum(Case when Cardtype=16 then 1 else 0 end) ImonthF,
		Sum(Case when (Cardtype>10 and Cardtype<20) then 1 else 0 end) Isummonth,
		Sum(Case Cardtype when 41 then 1 else 0 end) IFreeA,
		Sum(Case Cardtype when 42 then 1 else 0 end) IFreeB,
		Sum(Case Cardtype when 5 then 1 else 0 end) IVipCar,
		Sum(Case when (Cardtype>40 and Cardtype<50) then 1 else 0 end) Isumfree,
		Sum(Case when ChargeType='出口收费' and (Cardtype=31 or Cardtype=21) then 1 else 0 end) ItempA,
		Sum(Case when ChargeType='出口收费' and (Cardtype=32 or Cardtype=22) then 1 else 0 end) ItempB,
		Sum(Case when ChargeType='出口收费' and (Cardtype=33 or Cardtype=23) then 1 else 0 end) ItempC,
		Sum(Case when ChargeType='出口收费' and (Cardtype=34 or Cardtype=24) then 1 else 0 end) ItempD,
		Sum(Case when ChargeType='出口收费' and Cardtype>20 and Cardtype<40 then 1  else 0 end) ISumTemp, --临时卡中包含月临卡
		Sum(Case when  ChargeType='出口收费' and Cardtype>20 and Cardtype<40 and IsNull(PayCharge,0)>0 then IsNull(PayCharge,0) else 0 end) Imoney,  -- --临时卡和月临卡出口收费金额
		Sum(Case Cardtype when 51 then 1 else 0 end) IMoneyA,
		Sum(Case Cardtype when 52 then 1 else 0 end) IMoneyB,
		Sum(Case Cardtype when 53 then 1 else 0 end) IMoneyC,
		Sum(Case Cardtype when 54 then 1 else 0 end) IMoneyD,
		sum(Case when (Cardtype>49 and Cardtype<60) then 1 else 0 end) ISumMoney, --储值卡车数
		sum(Case when (Cardtype>50 and Cardtype<60)  then Isnull(PayCharge,0) else 0 end) ImoneySum, --储值卡金额
		Sum(Case when Cardtype=40 then 1 else 0 end) ITempFree, --临免卡数
		Sum(Case when (ChargeType='出口收费' and (Cardtype=5 or Cardtype>10 and Cardtype< 60)) then 1  else 0 end) ZSumCar,  --出口车数
		sum(Case when (Cardtype>20 and Cardtype<40)and IsNull(PayCharge,0)>0  then Isnull(PayCharge,0) else 0 end) ZSummoney,
		Sum(Case when  ChargeType='定点收费' and IsNull(PayCharge,0)>0 then IsNull(PayCharge,0) else 0 end) TUnout,  --chargetype=1定点收费 ,chargetype=0出口收费
		Sum(Case when  ChargeType='逾时收费' and IsNull(PayCharge,0)>0 then IsNull(PayCharge,0) else 0 end) TOver,   --chargetype=2逾时收费
		sum(Isnull(DisAmount,0)) ZDisM,sum(Isnull(FreeAmount,0)) Preferential,sum(Isnull(Nominalfee,0)) TNominal
               From Vw_ParkOutCharge
               Where OutTime between  @firstdate and @lastdate
               group by right('0'+Convert(varchar(2),month(OutTime)),2)
	 Update PKCarOutRpt set ImonthAvg=Isnull(b.ImonthAvg,0)  from PKCarOutRpt a Left Join 
		(Select right('0'+Convert(varchar(2),month(OutTime)),2) Username,Count(DISTINCT CardID) ImonthAvg  From Vw_ParkOut where (Cardtype>10 and Cardtype<20) and  (OutTime between  @firstdate and @lastdate) 
	        Group by  right('0'+Convert(varchar(2),month(OutTime)),2)) b On a.userName=b.UserName

	end
   */

 
   if @Flag=2 or @Flag=4
	begin
		select @Daybegin=@firstdate
               	while @Daybegin<@lastdate
		begin	
			if not exists(select Username from PKCarOutRpt where Username=right(convert(varchar(10),@Daybegin,120),5))
			begin					
       				insert into PKCarOutRpt(Username) values(right(convert(varchar(10),@Daybegin,120),5))
			end
			select @Daybegin = DateAdd(D, 1, @Daybegin)
		end
	end
	
	select * from PKCarOutRpt order by Username
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <一卡通->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Park_CamSetGetSortId') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Park_CamSetGetSortId
GO
	CREATE PROCEDURE [dbo].[Sp_Park_CamSetGetSortId]
		-- Add the parameters for the stored procedure here
		@boxId int
		
	AS
	BEGIN
		Declare @MaxID int
		Declare @SumRs Int
		Declare @I int
		select @MaxID=0,@SumRs=0
		if Not exists(Select 1 from Park_CamSet where BoxID=@boxId)
			Return 1
		Select @MaxID=Max(SortID),@SumRs=COUNT(*) from Park_CamSet where BoxID=@boxId
		if @MaxID=@SumRs Return @MaxID+1
		else
			Begin
				select @I=1
				while 1=1
					Begin
						if exists(Select BoxID from Park_CamSet where BoxID=@boxId and SortID=@I)
							set @I=@I+1
						else
							Return @I
					end 
			end
	END
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <一卡通->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Park_CamSet_UpdateCamSort') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Park_CamSet_UpdateCamSort
GO
	CREATE PROCEDURE [dbo].[Sp_Park_CamSet_UpdateCamSort]
		-- Add the parameters for the stored procedure here
		@prename nvarchar(20),
		@lname nvarchar(20),
		@type varchar(10),
		@sortId tinyint,
		@boxid int
		
	AS
	BEGIN
		--错误代号
		declare @errorcode int
		select @errorcode=@@ERROR
		begin tran
		if(@type='middle')
			begin
			if(@errorcode=0)
			begin
				update dbo.Park_CamSet set SortID=SortID-1 where BoxID=@boxId and CamName=@prename
				select @errorcode=@@ERROR
			end
			if(@errorcode=0)
			begin
				update dbo.Park_CamSet set SortID=SortID+1 where BoxID=@boxId and CamName=@lname
				select @errorcode=@@ERROR
			end
		end
		else if(@type='top')
		begin
			if(@errorcode=0)
			begin
			update dbo.Park_CamSet set SortID=1 where BoxID=@boxId and SortID=@sortId
			update dbo.Park_CamSet set SortID=SortID+1 where BoxID=@boxId and SortID<@sortId and CamName!=@prename
			--update dbo.Park_CamSet set SortID=SortID-1 where SortID>@sortId
			select @errorcode=@@ERROR
			end
		end
		else if(@type='bottom')
		begin
		update dbo.Park_CamSet set SortID=(select COUNT(1) from Park_CamSet where BoxID=@boxId) where BoxID=@boxId and SortID=@sortId
		update dbo.Park_CamSet set SortID=SortID-1 where BoxID=@boxId and SortID>=@sortId and CamName!=@prename
		select @errorcode=@@ERROR
		end
		if(@errorcode=0)
		commit transaction
		else
		rollback transaction

		return @errorcode
	END
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <一卡通->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Park_CalcCount') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Park_CalcCount
GO
	CREATE PROCEDURE [dbo].[Sp_Park_CalcCount]
		@UserName nVarchar(20),
		@sDate Datetime,
		@OpenGate int Out,			--非法开闸
		@TempIn int Out,			--临时卡入场
		@TempOut int Out,			--临时卡出场
		@PayMoney Money Out,		--现金金额
		@FreeSum Int Out,			--免费张数
		@FreeMoney Money Out,		--免费金额
		@DisSum int Out,			--折扣张数
		@DisMoney Money Out			--折扣金额
	 
	AS
		set nocount on
		Declare @m11 Money --现金金额
		Declare @m12 Money
		Declare @m13 Money
		Declare @m21 Money --免费金额
		Declare @m22 Money
		Declare @m31 Money --折扣金额
		Declare @m32 Money
		Declare @m33 Money
		Declare @L1 int
		Declare @L21 int	--临时卡入场张数
		Declare @L22 int
		Declare @L3 int
		Declare @L41 int --免费张数
		Declare @L42 int
		Declare @L51 int --折扣张数
		Declare @L52 int
		Declare @L53 int
		Declare @TDate Datetime
		Set @TDate=GetDate()
		Select @m11=Sum(Isnull(PayCharge,0)),@m21=SUM(Case When isnull(FreeType,0)>0 then Isnull(AccountCharge,0)-Isnull(PayCharge,0)  else 0 end),
			@m31=SUM(Isnull(DisAmount,0)),@L41=Sum(Case when isnull(Freetype,0)>0 then 1 else 0 end),@L51=Sum(Case when isnull(TypeID,0)>0 then 1 else 0 end) from [ParK_CarOut] 
			where  Isnull(PayType,0)=0 and OutUserName=@UserName and Isnull(AccountCharge,0)>0 and OutTime Between @sDate and @TDate 

		Select @m12=Sum(Isnull(PayCharge,0)),@m22=SUM(Case When isnull(FreeType,0)>0 then Isnull(AccountCharge,0)-Isnull(PayCharge,0)  else 0 end),
			@m32=SUM(Isnull(DisAmount,0)),@L42=Sum(Case when isnull(Freetype,0)>0 then 1 else 0 end),@L52=Sum(Case when isnull(TypeID,0)>0 then 1 else 0 end) from [ParK_CarOut] 
			where  Isnull(AccountCharge,0)>0 and Isnull(PayType,0)=1  and OutUserName=@UserName and CentralTime Between @sDate and @TDate

		Select @m13=Sum(Isnull(PayCharge,0)),@m33=SUM(Isnull(AccountCharge,0)-Isnull(PayCharge,0)),@L53=SUM(Case when Isnull(AccountCharge,0)-Isnull(PayCharge,0)>0 then 1 else 0 end) from [ParK_CentralCharge] 
			where Isnull(AccountCharge,0)>0 and [UserName]=@UserName and [PayChargeTime] Between @sDate and @TDate 

		--L1非法开闸数量，临时卡出场张数L3 
		Select @L1=Sum(Case when CardType In (1,2,3,4) then 1 else 0 end),@L3=Sum(Case when CardType In (31,32,33,34) then 1 else 0 end)
				from [ParK_CarOut] 
			where  OutUserName=@UserName and OutTime Between @sDate and GetDate()

		Select @L21=Sum(Case when CardType In (31,32,33,34) then 1 else 0 end)
				from [ParK_CarIn] 
			where  [InUserName]=@UserName and InTime Between @sDate and GetDate()

		Select @L22=Sum(Case when CardType In (31,32,33,34) then 1 else 0 end)
				from [ParK_CarOut] 
			where  Isnull(PayType,0)=0 and [InUserName]=@UserName and InTime Between @sDate and GetDate()


		Select @OpenGate=Isnull(@L1,0),@TempIn=Isnull(@L21,0)+Isnull(@L22,0),@TempOut=Isnull(@L3,0),@PayMoney=Isnull(@m11,0)+Isnull(@m12,0)+Isnull(@m13,0),
				@FreeSum=Isnull(@L41,0)+Isnull(@L42,0),@FreeMoney=Isnull(@m21,0)+Isnull(@m22,0),@DisSum=Isnull(@L51,0)+Isnull(@L52,0)+Isnull(@L53,0),
				@DisMoney=Isnull(@m31,0)+Isnull(@m32,0)+Isnull(@m33,0)
		set nocount off
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <一卡通->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Park_CalcCarQuan') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Park_CalcCarQuan
GO
	CREATE PROCEDURE [dbo].[Sp_Park_CalcCarQuan]
		@TypeID tinyint					--0大车场 1小车场 
	 
	AS
		set nocount on
		if @TypeID=0
			Select Sum(Case when CardType in (31,32,33,34)  then 1 else 0 end) as TmpCar,Sum(Case when CardType in (11,12,13,14,15,16,21,22,23,24)  then 1 else 0 end) as MonthCar,
			Sum(Case when CardType in (51,52,53,54)  then 1 else 0 end) as MoneyCar,Sum(Case when CardType in (41,42)  then 1 else 0 end) as FreeCar
			from [ParK_CarIn] where CardType<>3 and Small=0 and [machNo] in (Select [machNo] from [Park_ControlSet] where Inout=0 or Inout=4 )
		if @TypeID=1
			Select Sum(Case when CardType in (31,32,33,34)  then 1 else 0 end) as TmpCar,Sum(Case when CardType in (11,12,13,14,15,16,21,22,23,24)  then 1 else 0 end) as MonthCar,
			Sum(Case when CardType in (51,52,53,54)  then 1 else 0 end) as MoneyCar,Sum(Case when CardType in (41,42)  then 1 else 0 end) as FreeCar
				from [ParK_CarIn] where CardType<>3 and Small=1 and [machNo] in (Select [machNo] from [Park_ControlSet] where Inout=2 or Inout=4)

		if @TypeID>1
			Select 0,0,0,0
		set nocount off
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <一卡通->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Get_insert_sql') IS NOT NULL
	DROP PROCEDURE dbo.Get_insert_sql
GO
	Create PROCEDURE [dbo].[Get_insert_sql] 
	(
		@tablename varchar(256)
		,@where varchar(8000)=null
	,@orderby varchar(8000)=null
	)
	as
	begin
	set nocount on
	declare @sqlstr varchar(8000)
	declare @sqlstr1 varchar(8000)
	declare @sqlstr2 varchar(8000)
	SELECT '/*------------------table: ' + @tablename + '--------*/'
	IF OBJECTPROPERTY(OBJECT_ID(@tablename), 'TableHasIdentity') = 1
	BEGIN
	SELECT 'SET IDENTITY_INSERT ' + @tablename + ' ON '
	SELECT 'GO '
	END
	--    set @where=replace(@where,'''','''''')
	select @sqlstr='select ''INSERT INTO '+@tablename
	select @sqlstr1=''
	select @sqlstr2=' ('
	select @sqlstr1= ' VALUES ( ''+'
	select @sqlstr1=@sqlstr1+col+'+'',''+' ,@sqlstr2=@sqlstr2+name +',' from (select case 
	--    when a.xtype =173 then 'case when '+a.name+' is null then ''NULL'' else '+'convert(varchar('+convert(varchar(4),a.length*2+2)+'),'+a.name +')'+' end'
	when a.xtype =104 then 'case when '+a.name+' is null then ''NULL'' else '+'convert(varchar(1),'+a.name +')'+' end'
	when a.xtype =175 then 'case when '+a.name+' is null then ''NULL'' else '+'''''''''+'+'replace('+a.name+','''''''','''''''''''')' + '+'''''''''+' end'
	when a.xtype =36 then 'case when '+a.name+' is null then ''NULL'' else '+'''''''''+'+'replace('+a.name+','''''''','''''''''''')' + '+'''''''''+' end'
	when a.xtype =35 or a.xtype = 99 then 'case when '+a.name+' is null then ''NULL'' else '+'''''''''+'+'replace(convert(varchar(8000),'+a.name+'),'''''''','''''''''''')' + '+'''''''''+' end'
	when a.xtype =61  then 'case when '+a.name+' is null then ''NULL'' else '+'''''''''+'+'convert(varchar(23),'+a.name +',101)'+ '+'''''''''+' end'
	when a.xtype =106 then 'case when '+a.name+' is null then ''NULL'' else '+'convert(varchar('+convert(varchar(4),a.xprec+2)+'),'+a.name +')'+' end'
	when a.xtype =62  then 'case when '+a.name+' is null then ''NULL'' else '+'convert(varchar(23),'+a.name +',2)'+' end'
	when a.xtype =56  then 'case when '+a.name+' is null then ''NULL'' else '+'convert(varchar(11),'+a.name +')'+' end'
	when a.xtype =60  then 'case when '+a.name+' is null then ''NULL'' else '+'convert(varchar(22),'+a.name +')'+' end'
	when a.xtype =239 then 'case when '+a.name+' is null then ''NULL'' else '+'''''''''+'+'replace('+a.name+','''''''','''''''''''')' + '+'''''''''+' end'
	when a.xtype =108 then 'case when '+a.name+' is null then ''NULL'' else '+'convert(varchar('+convert(varchar(4),a.xprec+2)+'),'+a.name +')'+' end'
	when a.xtype =231 then 'case when '+a.name+' is null then ''NULL'' else '+'''N''''''+'+'replace('+a.name+','''''''','''''''''''')' + '+'''''''''+' end'
	when a.xtype =59  then 'case when '+a.name+' is null then ''NULL'' else '+'convert(varchar(23),'+a.name +',2)'+' end'
	when a.xtype =58  then 'case when '+a.name+' is null then ''NULL'' else '+'''''''''+'+'convert(varchar(23),'+a.name +',101)'+ '+'''''''''+' end'
	when a.xtype =52  then 'case when '+a.name+' is null then ''NULL'' else '+'convert(varchar(12),'+a.name +')'+' end'
	when a.xtype =122 then 'case when '+a.name+' is null then ''NULL'' else '+'convert(varchar(22),'+a.name +')'+' end'
	when a.xtype =48  then 'case when '+a.name+' is null then ''NULL'' else '+'convert(varchar(6),'+a.name +')'+' end'
	--    when a.xtype =165 then 'case when '+a.name+' is null then ''NULL'' else '+'convert(varchar('+convert(varchar(4),a.length*2+2)+'),'+a.name +')'+' end'
	when a.xtype =167 then 'case when '+a.name+' is null then ''NULL'' else '+'''''''''+'+'replace('+a.name+','''''''','''''''''''')' + '+'''''''''+' end'
	else '''NULL'''
	end as col,a.colid,a.name
	from syscolumns a where a.id = OBJECT_ID(@tablename) and a.xtype <>189 and a.xtype <>34 and a.xtype <>35
	)t order by colid

	select @sqlstr=@sqlstr+left(@sqlstr2,len(@sqlstr2)-1)+') '+left(@sqlstr1,len(@sqlstr1)-3)+')''+'''' from '+@tablename
	if @where is not null
	select @sqlstr=@sqlstr+' where '+@where
	if @orderby is not null
	select @sqlstr=@sqlstr+' order by '+@orderby
	--print @sqlstr
	exec( @sqlstr)
	SELECT 'GO '
	IF OBJECTPROPERTY(OBJECT_ID(@tablename), 'TableHasIdentity') = 1
	BEGIN
	SELECT 'SET IDENTITY_INSERT ' + @tablename + ' OFF '
	SELECT 'GO '
	SELECT ' '
	END    
	set nocount off
	end
GO


-- ============================================= 
-- 程序编写： <李君> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <一卡通-车场收费标准三添加>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('Sp_ParkEs5_Add') IS NOT NULL
	DROP PROCEDURE dbo.Sp_ParkEs5_Add
GO
Create PROCEDURE [dbo].[Sp_ParkEs5_Add]
@FreeMinute smallint, 
@FreeInclude tinyint,
@IsTimeAdd tinyint,
@CardType tinyint,
@DayBegin varchar(5),
@DayEnd varchar(5),
@ChargingLength1 smallint,
@ChargingUnit1 smallint,
@ChargingAmount1 money,
@LastUnit1 smallint,
@LaseAmount1 money,
@NightBegin varchar(5),
@NightEnd varchar(5),
@ChargingLength2 smallint,
@ChargingUnit2 smallint,
@ChargingAmount2 money,
@LastUnit2 smallint,
@LaseAmount2 money,
@ChargingAmount3 money,
@ChargingAmount4 money,
@ChargingAmount5 money,
@esInfo varchar(100)

 AS 
 	
	--错误代号
		declare @errorcode int
		--记录行数
		declare @datacount int
		select @errorcode=@@ERROR
		begin tran
		if(@errorcode=0)
		begin
		select @datacount=COUNT(1) from dbo.Park_Es5 where CardType=@CardType
		if(@datacount>0)
			begin--修改
			UPDATE [Park_Es5] SET 
	[CardType] = @CardType,[DayBegin] = @DayBegin,[DayEnd] = @DayEnd,[ChargingLength1] = @ChargingLength1,[ChargingUnit1] = @ChargingUnit1,[ChargingAmount1] = @ChargingAmount1,[LastUnit1] = @LastUnit1,[LaseAmount1] = @LaseAmount1,[NightBegin] = @NightBegin,[NightEnd] = @NightEnd,[ChargingLength2] = @ChargingLength2,[ChargingUnit2] = @ChargingUnit2,[ChargingAmount2] = @ChargingAmount2,[LastUnit2] = @LastUnit2,[LaseAmount2] = @LaseAmount2,[ChargingAmount3] = @ChargingAmount3,[ChargingAmount4] = @ChargingAmount4,[ChargingAmount5] = @ChargingAmount5
	WHERE CardType=@CardType

			select @errorcode=@@ERROR		
			end
		else
			begin--添加
			INSERT INTO [Park_Es5](
	[CardType],[DayBegin],[DayEnd],[ChargingLength1],[ChargingUnit1],[ChargingAmount1],[LastUnit1],[LaseAmount1],[NightBegin],[NightEnd],[ChargingLength2],[ChargingUnit2],[ChargingAmount2],[LastUnit2],[LaseAmount2],[ChargingAmount3],[ChargingAmount4],[ChargingAmount5]
	)VALUES(
	@CardType,@DayBegin,@DayEnd,@ChargingLength1,@ChargingUnit1,@ChargingAmount1,@LastUnit1,@LaseAmount1,@NightBegin,@NightEnd,@ChargingLength2,@ChargingUnit2,@ChargingAmount2,@LastUnit2,@LaseAmount2,@ChargingAmount3,@ChargingAmount4,@ChargingAmount5)
			select @errorcode=@@ERROR		
			end
		end
		--执行主表添加信息
		if(@errorcode=0)
		begin
		select @datacount=COUNT(1) from dbo.Park_ESMain where ESNo=4 and CardType=@CardType
			if(@datacount>0)
			begin--修改
			update dbo.Park_ESMain set FreeMinute=@FreeMinute,FreeInclude=@FreeInclude,IsTimeAdd=@IsTimeAdd where ESNo=4 and CardType=@CardType
			select @errorcode=@@ERROR	
			end
			else
			begin--添加
			insert into dbo.Park_ESMain(ESNo,FreeMinute,FreeInclude,IsTimeAdd,CardType) values (4,@FreeMinute,@FreeInclude,@IsTimeAdd,@CardType)
			select @errorcode=@@ERROR	
			end
		end

		--执行下位机下载同步操作
		if(@errorcode=0)
		begin
		select @datacount=COUNT(1) from dbo.Park_EsInfos where CardType=@CardType and EsType=0 and ESNo=4
			if(@datacount>0)
			begin
			update dbo.Park_EsInfos set EsInfo=@esInfo where CardType=@CardType and EsType=0 and ESNo=4
			select @errorcode=@@ERROR
			end
			else
			begin
			insert into dbo.Park_EsInfos(CardType,EsInfo,EsNo,EsType) values (@CardType,@esInfo,4,0)
			select @errorcode=@@ERROR
			end
		end

		if(@errorcode=0)
		commit transaction
		else
		rollback transaction

		return @errorcode

GO

-- ============================================= 
-- 程序编写： <李君> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <一卡通-车场收费标准四添加>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_ParkEs6_Add') IS NOT NULL
	DROP PROCEDURE dbo.Sp_ParkEs6_Add
GO
	CREATE PROCEDURE [dbo].[Sp_ParkEs6_Add]
		-- Add the parameters for the stored procedure here
		--主表参数
		@FreeMinute smallint,
		@FreeInclude tinyint,
		--表参数
		@CardType tinyint,
		@ChargeReclHours smallint,
		@CountAmount money,
		@LateUnit smallint,
		@LateAmount money,
		@HighAmount money,
		@esInfo varchar(100)
		
	AS
	BEGIN
		--错误代号
		declare @errorcode int
		--记录行数
		declare @datacount int
		select @errorcode=@@ERROR
		begin tran
		if(@errorcode=0)
		begin
		select @datacount=COUNT(1) from dbo.Park_Es6 where CardType=@CardType
			if(@datacount>0)
			begin
			update dbo.Park_Es6 set ChargeReclHours=@ChargeReclHours,CountAmount=@CountAmount,LateUnit=@LateUnit,LateAmount=@LateAmount,HighAmount=@HighAmount where CardType=@CardType
			select @errorcode=@@ERROR	
			end
			else
			begin
			insert into dbo.Park_Es6(CardType,ChargeReclHours,CountAmount,LateUnit,LateAmount,HighAmount) values (@CardType,@ChargeReclHours,@CountAmount,@LateUnit,@LateAmount,@HighAmount)
			select @errorcode=@@ERROR	
			end
		end
		if(@errorcode=0)
		begin
			select @datacount=COUNT(1) from dbo.Park_ESMain where ESNo=5 and CardType=@CardType
			if(@datacount>0)
			begin
				update dbo.Park_ESMain set FreeMinute=@FreeMinute,FreeInclude=@FreeInclude where ESNo=5 and CardType=@CardType
				select @errorcode=@@ERROR			
			end
			else
			begin
				insert into dbo.Park_ESMain(ESNo,FreeMinute,FreeInclude,CardType) values (5,@FreeMinute,@FreeInclude,@CardType)
				select @errorcode=@@ERROR		
			end
		end
		---执行下位机下载同步操作
		if(@errorcode=0)
		begin
		select @datacount=COUNT(1) from dbo.Park_EsInfos where CardType=@CardType and EsType=0 and ESNo=5
			if(@datacount>0)
			begin
			update dbo.Park_EsInfos set EsInfo=@esInfo where CardType=@CardType and EsType=0 and ESNo=5
			select @errorcode=@@ERROR
			end
			else
			begin
			insert into dbo.Park_EsInfos(CardType,EsInfo,EsNo,EsType) values (@CardType,@esInfo,5,0)
			select @errorcode=@@ERROR
			end
		end

		if(@errorcode=0)
		commit transaction
		else
		rollback transaction

		return @errorcode
	END
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <一卡通->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_ParkEs4_Add') IS NOT NULL
	DROP PROCEDURE dbo.Sp_ParkEs4_Add
GO
	CREATE PROCEDURE [dbo].[Sp_ParkEs4_Add]
		-- 主表参数
		@FreeMinute smallint,
		@FreeInclude tinyint,
		-- 深圳收费参数
		@CardType varchar(10),
		@NoWorkDayFirstHourAmount money,
		@NoWorkDayChgUnit int,
		@NoWorkChgAmount money,
		@WorkDayFirstHourAmount money,
		@PeakChgUnit int,
		@PeakChgAmount money,
		@NotPeakChgUnit int,
		@NotPeakChgAmount money,
		@DayTopAmount money,
		@IsDate tinyint,
		--收费副表参数
		@NoWorkDayBegin varchar(8),
		@NoWorkDayend varchar(8),
		@WorkDayTBegin varchar(8),
		@WorkDayTEnd varchar(8),
		@PeakFieldBegin varchar(4),
		@PeakFieldEnd varchar(4),
		@esInfo varchar(100)
	AS
	BEGIN
		--错误代号
		declare @errorcode int
		--标识数据库中是否有一条记录
		declare @datecount int
		--查找错误编号
		select @errorcode=@@ERROR
		begin tran
		if(@errorcode=0)
		begin
			select @datecount=COUNT(1) from Park_Es4 where CardType=@CardType
			--存在，进行修改操作
			if(@datecount>0)
			begin
			update dbo.Park_Es4 set NoWorkDayFirstHourAmount=@NoWorkDayFirstHourAmount,NoWorkDayChgUnit=@NoWorkDayChgUnit,NoWorkChgAmount=@NoWorkChgAmount,WorkDayFirstHourAmount=@WorkDayFirstHourAmount,PeakChgUnit=@PeakChgUnit,NotPeakChgAmount=@NotPeakChgAmount,PeakChgAmount=@PeakChgAmount,DayTopAmount=@DayTopAmount,[IsDate]=@IsDate where CardType=@CardType
			--查找错误编号
			select @errorcode=@@ERROR	
			end
			else--不存在，执行添加操作
			begin
			insert into dbo.Park_Es4(CardType,DayTopAmount,[IsDate],NoWorkChgAmount,NoWorkDayChgUnit,NoWorkDayFirstHourAmount,NotPeakChgAmount,NotPeakChgUnit,PeakChgAmount,PeakChgUnit,WorkDayFirstHourAmount) values (@CardType,@DayTopAmount,@IsDate,@NoWorkChgAmount,@NoWorkDayChgUnit,@NoWorkDayFirstHourAmount,@NotPeakChgAmount,@NotPeakChgUnit,@PeakChgAmount,@PeakChgUnit,@WorkDayFirstHourAmount)
			--查找错误编号
			select @errorcode=@@ERROR	
			end
		end
		--重置记录数
		set @datecount=0
		--上一步操作执行成功,进行下一步操作，添加主表信息
		if(@errorcode=0)
		begin
			select @datecount=COUNT(1) from dbo.Park_ESMain where [EsNo]=3 and CardType=@CardType
			if(@datecount>0)
			begin
				update dbo.Park_ESMain set FreeMinute=@FreeMinute,FreeInclude=@FreeInclude where ESNo=3 and CardType=@CardType
				--查找错误编号
				select @errorcode=@@ERROR	
			end
			else
			begin
				insert into dbo.Park_ESMain(ESNo,FreeMinute,FreeInclude,CardType) values (3,@FreeMinute,@FreeInclude,@CardType)
				--查找错误编号
				select @errorcode=@@ERROR			
			end
		end
		--重置记录数
		set @datecount=0
		--上一步操作执行成功,进行下一步操作，添加深圳收费副表信息
		if(@errorcode=0)
		begin
			select @datecount=COUNT(1) from dbo.Park_Es4_F where CardType = @CardType
			if(@datecount>0)--有执行修改，无，进行添加
			begin
			update dbo.Park_Es4_F set NoWorkDayBegin=@NoWorkDayBegin,NoWorkDayend=@NoWorkDayend,WorkDayTBegin=@WorkDayTBegin,WorkDayTEnd=@WorkDayTEnd,PeakFieldBegin=@PeakFieldBegin,PeakFieldEnd=@PeakFieldEnd where CardType = @CardType
			--查找错误编号
			select @errorcode=@@ERROR			
			end
			else
			begin
			insert into dbo.Park_Es4_F(CardType,NoWorkDayBegin,NoWorkDayend,WorkDayTBegin,WorkDayTEnd,PeakFieldBegin,PeakFieldEnd) values (@CardType,@NoWorkDayBegin,@NoWorkDayend,@WorkDayTBegin,@WorkDayTEnd,@PeakFieldBegin,@PeakFieldEnd)
			--查找错误编号
			select @errorcode=@@ERROR			
			end
		end

		--执行下位机下载同步操作
		if(@errorcode=0)
		begin
		select @datecount=COUNT(1) from dbo.Park_EsInfos where CardType=@CardType and EsType=0 and ESNo=3
			if(@datecount>0)
			begin
			update dbo.Park_EsInfos set EsInfo=@esInfo where CardType=@CardType and EsType=0 and ESNo=3
			select @errorcode=@@ERROR
			end
			else
			begin
			insert into dbo.Park_EsInfos(CardType,EsInfo,EsNo,EsType) values (@CardType,@esInfo,3,0)
			select @errorcode=@@ERROR
			end
		end

		--根据错误编号提交事务
		if(@errorcode=0)
		commit transaction
		else
		rollback transaction

		return @errorcode
	END
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <一卡通->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_ParkEs3_Add') IS NOT NULL
	DROP PROCEDURE dbo.Sp_ParkEs3_Add
GO
	CREATE PROCEDURE [dbo].[Sp_ParkEs3_Add]
		-- Add the parameters for the stored procedure here
		@FreeMinute smallint, 
		@FreeInclude tinyint,
		@IsTimeAdd tinyint,
		@CardType tinyint,
		@DayBegin varchar(5),
		@DayEnd varchar(5),
		@ChargingAmount1 money,
		@ChargingUnit1 smallint,
		@NightBegin varchar(5),
		@NightEnd varchar(5),
		@ChargingAmount2 money,
		@ChargingUnit2 smallint,
		@ChargingAmount3 money,
		@ChargingAmount4 money,
		@ChargingAmount5 money,
		@esInfo varchar(100)
		
	AS
	BEGIN
		-- SET NOCOUNT ON added to prevent extra result sets from
		-- interfering with SELECT statements.
		--错误代号
		declare @errorcode int
		--记录行数
		declare @datacount int
		select @errorcode=@@ERROR
		begin tran
		if(@errorcode=0)
		begin
		select @datacount=COUNT(1) from dbo.Park_Es3 where CardType=@CardType
		if(@datacount>0)
			begin--修改
			update dbo.Park_Es3 set DayBegin=@DayBegin,DayEnd=@DayEnd,ChargingAmount1=@ChargingAmount1,ChargingUnit1=@ChargingUnit1,NightBegin=@NightBegin,NightEnd=@NightEnd,ChargingAmount2=@ChargingAmount2,ChargingUnit2=@ChargingUnit2,ChargingAmount3=@ChargingAmount3,ChargingAmount4=@ChargingAmount4,ChargingAmount5=@ChargingAmount5 where CardType=@CardType
			select @errorcode=@@ERROR		
			end
		else
			begin--添加
			insert into dbo.Park_Es3(CardType,DayBegin,DayEnd,ChargingAmount1,ChargingUnit1,NightBegin,NightEnd,ChargingAmount2,ChargingUnit2,ChargingAmount3,ChargingAmount4,ChargingAmount5) values (@CardType,@DayBegin,@DayEnd,@ChargingAmount1,@ChargingUnit1,@NightBegin,@NightEnd,@ChargingAmount2,@ChargingUnit2,@ChargingAmount3,@ChargingAmount4,@ChargingAmount5)
			select @errorcode=@@ERROR		
			end
		end
		--执行主表添加信息
		if(@errorcode=0)
		begin
		select @datacount=COUNT(1) from dbo.Park_ESMain where ESNo=2 and CardType=@CardType
			if(@datacount>0)
			begin--修改
			update dbo.Park_ESMain set FreeMinute=@FreeMinute,FreeInclude=@FreeInclude,IsTimeAdd=@IsTimeAdd where ESNo=2 and CardType=@CardType
			select @errorcode=@@ERROR	
			end
			else
			begin--添加
			insert into dbo.Park_ESMain(ESNo,FreeMinute,FreeInclude,IsTimeAdd,CardType) values (2,@FreeMinute,@FreeInclude,@IsTimeAdd,@CardType)
			select @errorcode=@@ERROR	
			end
		end

		--执行下位机下载同步操作
		if(@errorcode=0)
		begin
		select @datacount=COUNT(1) from dbo.Park_EsInfos where CardType=@CardType and EsType=0 and ESNo=2
			if(@datacount>0)
			begin
			update dbo.Park_EsInfos set EsInfo=@esInfo where CardType=@CardType and EsType=0 and ESNo=2
			select @errorcode=@@ERROR
			end
			else
			begin
			insert into dbo.Park_EsInfos(CardType,EsInfo,EsNo,EsType) values (@CardType,@esInfo,2,0)
			select @errorcode=@@ERROR
			end
		end

		if(@errorcode=0)
		commit transaction
		else
		rollback transaction

		return @errorcode
	END
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2016-07-07>  
-- 功能说明： <车场-对外接口调用：操作优惠券>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及述二行一次>
-- =============================================
IF OBJECT_ID('Sp_Interface_Park_CouponOperate') is not null
	DROP PROCEDURE Sp_Interface_Park_CouponOperate
go
CREATE    PROCEDURE [dbo].[Sp_Interface_Park_CouponOperate]
		@merchantID int,   
		@couponID int,		
		@couponName varchar(20),
		@coupontype int,			-- 1全免(0)  2 折扣率(<100) 3免时间(<65535) 4免金额(<65535)
		@couponAmount float,
		@remark varchar(50),
		@method varchar(10)			--'add'|'upd'|'del'				
	AS
		declare @Status int = 0
		IF (@method = 'add' or @method = 'upd')
		begin
			if exists(Select 1 from Park_DisInfo where EqID = @merchantID and DiscountID = @couponID)
			begin
				update Park_DisInfo set EqID=@merchantID,DisName=@couponName,DisType=@coupontype,DisAmount=@couponAmount,DisRemark=@remark  where DiscountID=@couponID
				IF @@rowcount = 0
				begin
					set @Status = 2
				end
			end
			else
			begin
				insert into Park_DisInfo(EqID,DiscountID,DisName,DisType,DisAmount,DisRemark) values (@merchantID,@couponID,@couponName,@coupontype,@couponAmount,@remark)
				IF @@rowcount = 0
				begin
					set @Status = 1
				end
			end
		end
		else if @method = 'del'
		begin
			delete from Park_DisInfo where EqID = @merchantID and DiscountID = @couponID
			IF @@rowcount = 0
			begin
				set @Status = 3
			end
		end
		select @Status as Statu
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2016-07-07>  
-- 功能说明： <车场-对外接口调用：操作店铺信息>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及述二行一次>
-- =============================================
IF OBJECT_ID('Sp_Interface_Park_MerchantOperate') is not null
	DROP PROCEDURE Sp_Interface_Park_MerchantOperate
go
CREATE    PROCEDURE [dbo].[Sp_Interface_Park_MerchantOperate]
		@merchantID int,  	
		@merchantName varchar(20),
		@memo varchar(50),
		@method varchar(10)			--'add'|'upd'|'del'				
	AS
		declare @Status int = 0
		IF (@method = 'add' or @method = 'upd')
		begin
			if exists(Select 1 from Park_EquipmentS where EqID = @merchantID)
			begin
				update Park_EquipmentS set EqName=@merchantName,Memo=@memo,DownLoad=dbo.PadLeft('0', '0', 255) where EqID=@merchantID
				IF @@rowcount = 0
				begin
					set @Status = 2
				end
			end
			else
			begin
				insert into Park_EquipmentS(EqID,EqName,Memo,ClientNO,DownLoad) values (@merchantID,@merchantName,@memo,'',dbo.PadLeft('0', '0', 255))
				IF @@rowcount = 0
				begin
					set @Status = 1
				end
			end
		end
		else if @method = 'del'
		begin
			delete from Park_EquipmentS where EqID = @merchantID
			IF @@rowcount = 0
			begin
				set @Status = 3
			end
		end
		select @Status as Statu
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <一卡通->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_ParkEs2_Add') IS NOT NULL
	DROP PROCEDURE dbo.Sp_ParkEs2_Add
GO
	CREATE PROCEDURE [dbo].[Sp_ParkEs2_Add]
		-- Add the parameters for the stored procedure here
		--主表参数
		@FreeMinute smallint,
		@FreeInclude tinyint,
		@ReCalc tinyint,
		@Manylimiting tinyint,
		--表参数
		@CardType tinyint,
		@ChargeReclHours tinyint,
		@ChLength1 smallint,
		@ChUnit1 smallint,
		@ChAmount1 money,
		@ChLength2 smallint,
		@ChUnit2 smallint,
		@ChAmount2 money,
		@ChLength3 smallint,
		@ChUnit3 smallint,
		@ChAmount3 money,
		@LastUnit smallint,
		@LaseAmount money,
		@HighAmount money,
		@esInfo varchar(100)
		
	AS
	BEGIN
		--错误代号
		declare @errorcode int
		--记录行数
		declare @datacount int
		select @errorcode=@@ERROR
		begin tran
		if(@errorcode=0)
		begin
		select @datacount=COUNT(1) from dbo.Park_Es2 where CardType=@CardType
			if(@datacount>0)
			begin
			update dbo.Park_Es2 set ChargeReclHours=@ChargeReclHours,ChLength1=@ChLength1,ChUnit1=@ChUnit1,ChAmount1=@ChAmount1,ChLength2=@ChLength2,ChUnit2=@ChUnit2,ChAmount2=@ChAmount2,ChLength3=@ChLength3,ChUnit3=@ChUnit3,ChAmount3=@ChAmount3,LastUnit=@LastUnit,LaseAmount=@LaseAmount,HighAmount=@HighAmount where CardType=@CardType
			select @errorcode=@@ERROR	
			end
			else
			begin
			insert into dbo.Park_Es2(CardType,ChargeReclHours,ChLength1,ChUnit1,ChAmount1,ChLength2,ChUnit2,ChAmount2,ChLength3,ChUnit3,ChAmount3,LastUnit,LaseAmount,HighAmount) values (@CardType,@ChargeReclHours,@ChLength1,@ChUnit1,@ChAmount1,@ChLength2,@ChUnit2,@ChAmount2,@ChLength3,@ChUnit3,@ChAmount3,@LastUnit,@LaseAmount,@HighAmount)
			select @errorcode=@@ERROR	
			end
		end
		if(@errorcode=0)
		begin
			select @datacount=COUNT(1) from dbo.Park_ESMain where ESNo=1 and CardType=@CardType
			if(@datacount>0)
			begin
				update dbo.Park_ESMain set FreeMinute=@FreeMinute,FreeInclude=@FreeInclude,ReCalc=@ReCalc,Manylimiting=@Manylimiting where ESNo=1 and CardType=@CardType
				select @errorcode=@@ERROR			
			end
			else
			begin
				insert into dbo.Park_ESMain(ESNo,FreeMinute,FreeInclude,ReCalc,Manylimiting,CardType) values (1,@FreeMinute,@FreeInclude,@ReCalc,@Manylimiting,@CardType)
				select @errorcode=@@ERROR		
			end
		end
		---执行下位机下载同步操作
		if(@errorcode=0)
		begin
		select @datacount=COUNT(1) from dbo.Park_EsInfos where CardType=@CardType and EsType=0 and ESNo=1
			if(@datacount>0)
			begin
			update dbo.Park_EsInfos set EsInfo=@esInfo where CardType=@CardType and EsType=0 and ESNo=1
			select @errorcode=@@ERROR
			end
			else
			begin
			insert into dbo.Park_EsInfos(CardType,EsInfo,EsNo,EsType) values (@CardType,@esInfo,1,0)
			select @errorcode=@@ERROR
			end
		end

		if(@errorcode=0)
		commit transaction
		else
		rollback transaction

		return @errorcode
	END
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================

IF OBJECT_ID ('dbo.Sp_Park_ChargeA') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Park_ChargeA
GO
	CREATE PROCEDURE [dbo].[Sp_Park_ChargeA]
	@CarNo nvarchar(10),	--车牌号码
	@CardType  integer,		--卡类
	@InTime datetime,		--入场时间
	@OutTime datetime,		--出场时间
	@EsType tinyint=1,		--收费类型(20150901)
	@Amount  money output 	--收费金额
	 
	AS
	select @Amount=0
	if (@OutTime<=@InTime)
	begin
		set @Amount=0
		return
	end
	declare @FreeMinute as integer   	--免费时间
	declare @FreeInclude as tinyint 	--是否包含免费时间 
	declare @TopAmount as money		--一天最高收费额 
	Declare @PreInTime as Datetime 		--@PreInTime
	Declare @PeriodTime as Datetime
	Declare @AmountA as Money			--@AmountA
	Declare @AmountB as Money			--@AmountB
	Declare @AmountC as Money			--@AmountC
	Declare @ManyLimit as integer		--24小时多次入场收费达到最高收费后不再收费限制
	Declare @oPeriodTime as datetime
	Declare @PInTime as Datetime
	Select @AmountA=0,@AmountB=0,@AmountC=0
    
	Select @InTime=Cast(Convert(varchar(17),@InTime,120)+'00' as smalldatetime),@OutTime=Cast(Convert(varchar(17),@OutTime,120)+'00' as smalldatetime)
	if @CardType<30 or (@CardType>39 and @CardType<50) or  @CardType>59  --卡类只有临时卡和储值卡
		return
	Select @FreeMinute=Isnull(FreeMinute,0),@FreeInclude=Isnull(FreeInclude,0),@ManyLimit=isnull(ManyLimiting,0) from [Park_ESMain] where ESNo=1 and CardType=@CardType --通用标准一
	select @TopAmount=HighAmount from [Park_Es2] where CardType=@CardType
	if datediff(minute,@InTime,@OutTime)<=@FreeMinute
		begin
		select @Amount=0
		return
		end
	if @FreeMinute>0 and @FreeInclude>0
		Begin  
			select @InTime=dateadd(minute,@FreeMinute,@InTime) --入场时间向后推移
		end
	Select @Amount=dbo.Park_GetMoney(@CardType,@InTime,@OutTime)
	if @ManyLimit=0 --无24小时多次入场收费达到最高收费后不再收费限制
		return
	--有24小时多次入场收费达到最高收费后不再收费限制
	if @CarNo='' or Len(@CarNo)<=4 
		Begin
			Return
		end 
	if @TopAmount<=0  return
	if Not Exists(Select * from Park_CarNoInfo where CarNo=@CarNo)
	Begin
		Insert  Park_CarNoInfo(CarNo,PeriodTime,ChargeFee,InTime) Values (@CarNo,dateadd(dd,1,@InTime),0,@InTime)
		Select @PreInTime=dateadd(dd,1,@InTime),@AmountA=0,@oPeriodTime=Null
	end
else
	Begin
		Select @PreInTime=PeriodTime,@AmountA=ChargeFee,@PInTime=InTime,@oPeriodTime=oPeriodTime ,@PeriodTime =PeriodTime from Park_CarNoInfo where CarNo=@CarNo		--获取数据
		if @PInTime=@InTime 
			Begin
				if @oPeriodTime is Null
					Begin
						Delete from Park_CarNoInfo where CarNo=@CarNo		--获取数据
						Insert  Park_CarNoInfo(CarNo,PeriodTime,ChargeFee,InTime) Values (@CarNo,dateadd(dd,1,@InTime),0,@InTime)
						Select @PreInTime=dateadd(dd,1,@InTime),@AmountA=0
						
					end
				else
					Begin
						UpDate Park_CarNoInfo Set PeriodTime=oPeriodTime,ChargeFee=oChargeFee,InTime=Null where CarNo=@CarNo  --数据还原
						Select @PreInTime=PeriodTime,@AmountA=ChargeFee from Park_CarNoInfo where CarNo=@CarNo				 --重新获取数据
					end
			end
	end
if @InTime>=@PreInTime --前
	Begin
	    Select @PreInTime=dateadd(dd,1,@InTime),@AmountA=0
	    UpDate Park_CarNoInfo Set oPeriodTime=PeriodTime,oChargeFee=ChargeFee where CarNo=@CarNo						--保存老数据
		UpDate Park_CarNoInfo Set PeriodTime=@PreInTime,ChargeFee=0,InTime=@InTime where CarNo=@CarNo				--更新数据
	end
if @PreInTime>=@OutTime --后
	begin
		if @AmountA+@Amount<=@TopAmount
			Select @AmountA=@AmountA+@Amount
		else
			Begin
				Select @Amount=@TopAmount-@AmountA
				select @AmountA=@TopAmount
			end
		UpDate Park_CarNoInfo Set oPeriodTime=PeriodTime,oChargeFee=ChargeFee where CarNo=@CarNo  --保存老数据
		update Park_CarNoInfo Set ChargeFee=@AmountA,InTime=@InTime  where CarNo=@CarNo			 --更新数据
	end
else			--中
	Begin
		Select @AmountB=dbo.Park_GetMoney(@CardType,@InTime,@PreInTime)
		Select @AmountC=dbo.Park_GetMoney(@CardType,@PreInTime,@OutTime)
		if @AmountA+@AmountB>@TopAmount
			Begin
				Select @AmountB=@TopAmount-@AmountA
				if @AmountB<0 select @AmountB=0
				if @AmountB+@AmountC<@Amount
					Select  @Amount=@AmountB+@AmountC
			end
		if @Amount>=@TopAmount	--超出最高收费，从当前入场时间开始推算
			Begin
			    if(@Amount=@TopAmount)
				 Begin
				   if(@InTime<@PeriodTime)
					  begin
					      if(@PInTime=@InTime)---同一次查询的时候
							  begin
								  Select @PreInTime=@PeriodTime
						          if(@AmountC >0)
						              Select @AmountA=@AmountC
						          else
						              Select @AmountA=@TopAmount
							  end 
					      else
					         begin
							  if( @AmountC >0)
								 begin
									 Select @AmountA=@AmountC
									 Select @PreInTime=dateadd(dd,(datediff(minute,@InTime,@OutTime) / 1440)+1,@PeriodTime)
								 end
							  else
								 begin
									 Select @AmountA=@TopAmount
									 Select @PreInTime=dateadd(dd,(datediff(minute,@InTime,@OutTime) / 1440),@PeriodTime)
								 end 
						     end
					  end
				   else
				       begin
						   Select @PreInTime=dateadd(dd,(datediff(minute,@InTime,@OutTime) / 1440),@InTime)  
						   Select @AmountA=@Amount
					   end
				  end 
			    else 
					begin
						if(@AmountC>@TopAmount)
							begin
								Select @AmountA=@AmountC-floor(datediff(minute,@PreInTime,@OutTime) / 1440)*@TopAmount
							end
						else
							begin
								Select @AmountA=@AmountC
							end 
						if(@InTime<@PeriodTime)
						  begin
						   if(@PInTime=@InTime)---同一次查询的时候
						   begin
						      Select @PreInTime=@PeriodTime
						   end
						   else
							   begin
							   Select @PreInTime=dateadd(dd,(datediff(minute,@PreInTime,@OutTime) / 1440)+1,@PeriodTime) 
								--   if(@AmountC>=@TopAmount)
								--	  Select @PreInTime=dateadd(dd,(datediff(minute,@PreInTime,@OutTime) / 1440)+1,@PeriodTime) 
								--   else
								--      begin
								--         select @InTime,@OutTime,@PeriodTime,@AmountC,@TopAmount,@Amount
								--	     Select @PreInTime=dateadd(dd,(datediff(minute,@InTime,@OutTime) / 1440)+1,@PeriodTime)
								--	  end
								end 
							end  
						else 
						  begin 
						      Select @PreInTime=dateadd(dd,(datediff(minute,@InTime,@OutTime) / 1440)+1,@InTime)
						  end
					end	
			   end
		else
			Begin
			    if(@Amount>0) 
			    begin
			       if(@InTime<@PeriodTime)
			          Select @PreInTime=dateadd(dd,(datediff(minute,@InTime,@OutTime) / 1440)+1,@PeriodTime) 
			       else
			          Select @PreInTime=dateadd(dd,(datediff(minute,@InTime,@OutTime) / 1440)+1,@InTime)   
			    end
				Select @AmountA=@AmountC
				--Select @AmountA=(@Amount-@AmountB)-floor(datediff(minute,@PreInTime,@OutTime) / 1440)*@TopAmount
				--Select @PreInTime=dateadd(dd,(datediff(minute,@PreInTime,@OutTime) / 1440)+1,@PreInTime)
			end
		UpDate Park_CarNoInfo Set oPeriodTime=PeriodTime,oChargeFee=ChargeFee where CarNo=@CarNo	--保存老数据
		Update Park_CarNoInfo Set PeriodTime=@PreInTime,ChargeFee=@AmountA,InTime=@InTime  where CarNo=@CarNo 				--更新数据					
	end
if @AmountA=0  Delete Park_CarNoInfo where CarNo=@CarNo 		
	if (@@ERROR<>0) or @Amount<0
		select @Amount=0

		return
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Park_CarRecordDay') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Park_CarRecordDay
GO
	CREATE PROCEDURE  [dbo].[Sp_Park_CarRecordDay]         --日报表
			@OperatorNO varchar(20),            --操作员卡号(日用)
			@firstdate datetime,                --起始日期
			@lastdate datetime,                 --结束日期
			@Flag int                           --表示按年、月、日查询的标致
	 
	AS
		declare @Daybegin as datetime
		truncate table ParK_CarOutRpt
		if @Flag=1    --查找各类型卡车的车辆数并按操作员分组
		begin
				Insert into ParK_CarOutRpt(Username,ImonthA,ImonthB,ImonthC,ImonthD,IsumMonth,IFreeA,IFreeB,IVipCar,IsumFree,ITempA,ITempB,ITempC,ITempD,ISumTemp,Imoney,
			IMoneyA,IMoneyB,IMoneyC,IMoneyD,ISumMoney,IMoneySum,ITempFree,ZSumCar,ZSumMoney,TUnOut,TOver,ZDisM,Preferential)
				Select UserName,
			Sum(Case when Cardtype=11 then 1 else 0 end) ImonthA,
			Sum(Case when Cardtype=12 then 1 else 0 end) ImonthB,
			Sum(Case when Cardtype=13 then 1 else 0 end) ImonthC,
			Sum(Case when Cardtype=14 then 1 else 0 end) ImonthD,
			Sum(Case when (Cardtype>10 and Cardtype<20) then 1 else 0 end) Isummonth,
			Sum(Case Cardtype when 41 then 1 else 0 end) IFreeA,
			Sum(Case Cardtype when 42 then 1 else 0 end) IFreeB,
			Sum(Case Cardtype when 7 then 1 else 0 end) IVipCar,
			Sum(Case when (Cardtype>40 and Cardtype<50) then 1 else 0 end) Isumfree,
			Sum(Case when ChargeType='出口收费' and (Cardtype=31 or Cardtype=21) then 1 else 0 end) ItempA,
			Sum(Case when ChargeType='出口收费' and (Cardtype=32 or Cardtype=22) then 1 else 0 end) ItempB,
			Sum(Case when ChargeType='出口收费' and (Cardtype=33 or Cardtype=23) then 1 else 0 end) ItempC,
			Sum(Case when ChargeType='出口收费' and (Cardtype=34 or Cardtype=24) then 1 else 0 end) ItempD,
			Sum(Case when ChargeType='出口收费' and Cardtype>20 and Cardtype<40 then 1  else 0 end) ISumTemp, --临时卡中包含月临卡
			Sum(Case when  ChargeType='出口收费' and Cardtype>20 and Cardtype<40 and IsNull(PayCharge,0)>0 then IsNull(PayCharge,0) else 0 end) Imoney,  -- --临时卡和月临卡出口收费金额
			Sum(Case Cardtype when 51 then 1 else 0 end) IMoneyA,
			Sum(Case Cardtype when 52 then 1 else 0 end) IMoneyB,
			Sum(Case Cardtype when 53 then 1 else 0 end) IMoneyC,
			Sum(Case Cardtype when 54 then 1 else 0 end) IMoneyD,
			sum(Case when (Cardtype>50 and Cardtype<60) then 1 else 0 end) ISumMoney, --储值卡车数
			sum(Case when (Cardtype>50 and Cardtype<60)  then Isnull(PayCharge,0) else 0 end) ImoneySum, --储值卡金额
			Sum(Case when Cardtype=40 then 1 else 0 end) ITempFree, --临免卡数
			Sum(Case when (ChargeType='出口收费' and (Cardtype=5 or Cardtype>10 and Cardtype< 60)) then 1  else 0 end) ZSumCar,  --出口车数
			sum(Case when (Cardtype>20 and Cardtype<40) and IsNull(PayCharge,0)>0  then Isnull(PayCharge,0) else 0 end) ZSummoney,
			Sum(Case when  ChargeType='定点收费' and IsNull(PayCharge,0)>0 then IsNull(PayCharge,0) else 0 end) TUnout,  --chargetype=1定点收费 ,chargetype=0出口收费
			Sum(Case when  ChargeType='逾时收费' and IsNull(PayCharge,0)>0 then IsNull(PayCharge,0) else 0 end) TOver,   --chargetype=2逾时收费
			sum(Isnull(DisAmount,0)) ZDisM,sum(Isnull(FreeAmount,0)) Preferential
					from Vw_Park_CarCharge
					Where Isnull(UserName,'') Like '%' +@OperatorNO+'%'  and (OutTime between  @firstdate and @lastdate) 
					group by  UserName

			Update ParK_CarOutRpt set ImonthAvg=Isnull(b.ImonthAvg,0) from ParK_CarOutRpt a Left Join 
			(Select OutuserName as Username,Count(DISTINCT CardID) ImonthAvg  From ParK_CarOut where (Cardtype>10 and Cardtype<20) and Isnull(OutuserName,'') Like '%' +@OperatorNO+'%'  and (OutTime between  @firstdate and @lastdate)  Group by  OutuserName) b
			On a.userName=b.UserName
		end
		if @Flag=2   --按月计算
		begin
				Insert into ParK_CarOutRpt(Username,ImonthA,ImonthB,ImonthC,ImonthD,IsumMonth,IFreeA,IFreeB,IVipCar,IsumFree,ITempA,ITempB,ITempC,ITempD,ISumTemp,Imoney,
			IMoneyA,IMoneyB,IMoneyC,IMoneyD,ISumMoney,IMoneySum,ITempFree,ZSumCar,ZSumMoney,TUnOut,TOver,ZDisM,Preferential)
			Select Right(Convert(Varchar(10),a.Username,120),5),ImonthA,ImonthB,ImonthC,ImonthD,IsumMonth,IFreeA,IFreeB,IVipCar,IsumFree,ITempA,ITempB,ITempC,ITempD,ISumTemp,Imoney,
			IMoneyA,IMoneyB,IMoneyC,IMoneyD,ISumMoney,IMoneySum,ITempFree,ZSumCar,ZSumMoney,TUnOut,TOver,ZDisM,Preferential
				from (Select  Convert(Varchar(10),OutTime,120) Username,
			Sum(Case when Cardtype=11 then 1 else 0 end) ImonthA,
			Sum(Case when Cardtype=12 then 1 else 0 end) ImonthB,
			Sum(Case when Cardtype=13 then 1 else 0 end) ImonthC,
			Sum(Case when Cardtype=14 then 1 else 0 end) ImonthD,
			Sum(Case when Cardtype=15 then 1 else 0 end) ImonthE,
			Sum(Case when Cardtype=16 then 1 else 0 end) ImonthF,
			Sum(Case when (Cardtype>10 and Cardtype<20) then 1 else 0 end) Isummonth,
			Sum(Case Cardtype when 41 then 1 else 0 end) IFreeA,
			Sum(Case Cardtype when 42 then 1 else 0 end) IFreeB,
			Sum(Case Cardtype when 7 then 1 else 0 end) IVipCar,
			Sum(Case when (Cardtype>40 and Cardtype<50) then 1 else 0 end) Isumfree,
			Sum(Case when ChargeType='出口收费' and (Cardtype=31 or Cardtype=21) then 1 else 0 end) ItempA,
			Sum(Case when ChargeType='出口收费' and (Cardtype=32 or Cardtype=22) then 1 else 0 end) ItempB,
			Sum(Case when ChargeType='出口收费' and (Cardtype=33 or Cardtype=23) then 1 else 0 end) ItempC,
			Sum(Case when ChargeType='出口收费' and (Cardtype=34 or Cardtype=24) then 1 else 0 end) ItempD,
			Sum(Case when ChargeType='出口收费' and Cardtype>20 and Cardtype<40 then 1  else 0 end) ISumTemp, --临时卡中包含月临卡
			Sum(Case when  ChargeType='出口收费' and Cardtype>20 and Cardtype<40 and IsNull(PayCharge,0)>0 then IsNull(PayCharge,0) else 0 end) Imoney,  -- --临时卡和月临卡出口收费金额
			Sum(Case Cardtype when 51 then 1 else 0 end) IMoneyA,
			Sum(Case Cardtype when 52 then 1 else 0 end) IMoneyB,
			Sum(Case Cardtype when 53 then 1 else 0 end) IMoneyC,
			Sum(Case Cardtype when 54 then 1 else 0 end) IMoneyD,
			sum(Case when (Cardtype>50 and Cardtype<60) then 1 else 0 end) ISumMoney, --储值卡车数
			sum(Case when (Cardtype>50 and Cardtype<60)  then Isnull(PayCharge,0) else 0 end) ImoneySum, --储值卡金额
			Sum(Case when Cardtype=40 then 1 else 0 end) ITempFree, --临免卡数
			Sum(Case when (ChargeType='出口收费' and (Cardtype=5 or Cardtype>10 and Cardtype< 60)) then 1  else 0 end) ZSumCar,  --出口车数
			sum(Case when (Cardtype>20 and Cardtype<40) and IsNull(PayCharge,0)>0  then Isnull(PayCharge,0) else 0 end) ZSummoney,
			Sum(Case when  ChargeType='定点收费' and IsNull(PayCharge,0)>0 then IsNull(PayCharge,0) else 0 end) TUnout,  --chargetype=1定点收费 ,chargetype=0出口收费
			Sum(Case when  ChargeType='逾时收费' and IsNull(PayCharge,0)>0 then IsNull(PayCharge,0) else 0 end) TOver,   --chargetype=2逾时收费
			sum(Isnull(DisAmount,0)) ZDisM,sum(Isnull(FreeAmount,0)) Preferential
					From Vw_Park_CarCharge
					Where Isnull(UserName,'')<>'' and  (OutTime between  @firstdate and @lastdate)
				group by Convert(Varchar(10),OutTime,120) ) a
				Order By a.Username
			Update ParK_CarOutRpt set ImonthAvg=Isnull(b.ImonthAvg,0)  from ParK_CarOutRpt a Left Join 
			(Select  Right(Convert(Varchar(10),OutTime,120),5) Username,Count(DISTINCT CardID) ImonthAvg  From ParK_CarOut where (Cardtype>10 and Cardtype<20) and 
			Isnull(OutuserName,'')<>'' and  (OutTime between  @firstdate and @lastdate) 
				Group by  Right(Convert(Varchar(10),OutTime,120),5)) b On a.userName=b.UserName

		end
		if @Flag=3   --按年计算
		Begin
				Insert into ParK_CarOutRpt(Username,ImonthA,ImonthB,ImonthC,ImonthD,IsumMonth,IFreeA,IFreeB,IVipCar,IsumFree,ITempA,ITempB,ITempC,ITempD,ISumTemp,Imoney,
			IMoneyA,IMoneyB,IMoneyC,IMoneyD,ISumMoney,IMoneySum,ITempFree,ZSumCar,ZSumMoney,TUnOut,TOver,ZDisM,Preferential)
				Select right('0'+Convert(varchar(2),month(FeeTime)),2) Username,
			Sum(Case when Cardtype=11 then 1 else 0 end) ImonthA,
			Sum(Case when Cardtype=12 then 1 else 0 end) ImonthB,
			Sum(Case when Cardtype=13 then 1 else 0 end) ImonthC,
			Sum(Case when Cardtype=14 then 1 else 0 end) ImonthD,
			Sum(Case when (Cardtype>10 and Cardtype<20) then 1 else 0 end) Isummonth,
			Sum(Case Cardtype when 41 then 1 else 0 end) IFreeA,
			Sum(Case Cardtype when 42 then 1 else 0 end) IFreeB,
			Sum(Case Cardtype when 7 then 1 else 0 end) IVipCar,
			Sum(Case when (Cardtype>40 and Cardtype<50) then 1 else 0 end) Isumfree,
			Sum(Case when ChargeType='出口收费' and (Cardtype=31 or Cardtype=21) then 1 else 0 end) ItempA,
			Sum(Case when ChargeType='出口收费' and (Cardtype=32 or Cardtype=22) then 1 else 0 end) ItempB,
			Sum(Case when ChargeType='出口收费' and (Cardtype=33 or Cardtype=23) then 1 else 0 end) ItempC,
			Sum(Case when ChargeType='出口收费' and (Cardtype=34 or Cardtype=24) then 1 else 0 end) ItempD,
			Sum(Case when ChargeType='出口收费' and Cardtype>20 and Cardtype<40 then 1  else 0 end) ISumTemp, --临时卡中包含月临卡
			Sum(Case when  ChargeType='出口收费' and Cardtype>20 and Cardtype<40 and IsNull(PayCharge,0)>0 then IsNull(PayCharge,0) else 0 end) Imoney,  -- --临时卡和月临卡出口收费金额
			Sum(Case Cardtype when 51 then 1 else 0 end) IMoneyA,
			Sum(Case Cardtype when 52 then 1 else 0 end) IMoneyB,
			Sum(Case Cardtype when 53 then 1 else 0 end) IMoneyC,
			Sum(Case Cardtype when 54 then 1 else 0 end) IMoneyD,
			sum(Case when (Cardtype>50 and Cardtype<60) then 1 else 0 end) ISumMoney, --储值卡车数
			sum(Case when (Cardtype>50 and Cardtype<60)  then Isnull(PayCharge,0) else 0 end) ImoneySum, --储值卡金额
			Sum(Case when Cardtype=40 then 1 else 0 end) ITempFree, --临免卡数
			Sum(Case when (ChargeType='出口收费' and (Cardtype=5 or Cardtype>10 and Cardtype< 60)) then 1  else 0 end) ZSumCar,  --出口车数
			sum(Case when (Cardtype>20 and Cardtype<40) and IsNull(PayCharge,0)>0  then Isnull(PayCharge,0) else 0 end) ZSummoney,
			Sum(Case when  ChargeType='定点收费' and IsNull(PayCharge,0)>0 then IsNull(PayCharge,0) else 0 end) TUnout,  --chargetype=1定点收费 ,chargetype=0出口收费
			Sum(Case when  ChargeType='逾时收费' and IsNull(PayCharge,0)>0 then IsNull(PayCharge,0) else 0 end) TOver,   --chargetype=2超时收费
			sum(Isnull(DisAmount,0)) ZDisM,sum(Isnull(FreeAmount,0)) Preferential
					From Vw_ParkOutCharge
					Where Isnull(UserName,'')<>'' and  (FeeTime between  @firstdate and @lastdate) 
					group by right('0'+Convert(varchar(2),month(FeeTime)),2)
			Update ParK_CarOutRpt set ImonthAvg=Isnull(b.ImonthAvg,0)  from ParK_CarOutRpt a Left Join 
			(Select right('0'+Convert(varchar(2),month(OutTime)),2) Username,Count(DISTINCT CardID) ImonthAvg  From ParK_CarOut where (Cardtype>10 and Cardtype<20) and 
					Isnull(OutuserName,'')<>'' and  (OutTime between  @firstdate and @lastdate) 
				Group by  right('0'+Convert(varchar(2),month(OutTime)),2)) b On a.userName=b.UserName

		end
		if @Flag=2
		begin
			select @Daybegin=@firstdate
   					while @Daybegin<@lastdate
			begin	
				if not exists(select Username from ParK_CarOutRpt where Username=right(convert(varchar(10),@Daybegin,120),5))
				begin					
						insert into ParK_CarOutRpt(Username) values(right(convert(varchar(10),@Daybegin,120),5))
				end
				select @Daybegin = DateAdd(D, 1, @Daybegin)

			end
		end
		if @Flag=3
		begin
			select @Daybegin=@firstdate
   					while @Daybegin<@lastdate
			begin	
				if not exists(select Username from ParK_CarOutRpt where Username=right('0'+Convert(varchar(2),month(@Daybegin)),2))	
				begin					
						insert into ParK_CarOutRpt(Username) values(right('0'+Convert(varchar(2),month(@Daybegin)),2))
				end
				select @Daybegin = DateAdd(M, 1, @Daybegin)
			end
		end
		select * from ParK_CarOutRpt
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场-入场>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Park_CarIn') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Park_CarIn
GO
	CREATE  PROCEDURE [dbo].[Sp_Park_CarIn]
		@MachNo tinyint ,						--控制器编号
		@CardID Varchar(20),					--卡ID号
		@CFlag tinyint,							--卡介质(0为IC，1为ID，2IC做ID，3纸票)	
		@CardType tinyint,						--卡类型
		@InTime datetime,						--入场时间
		@InUserName nvarchar(20), 				--入场操作员
		@InOut tinyint							--出入类型(0标准入口，1标准出口，2标准嵌套入口，3标准嵌套出口、4半嵌套入口，5半嵌套出口、6单通道)
		 
	AS
		--取返回值 -1 重复记录 0更新 >0入场纪录ID
		Declare @CardNo varchar(10)				--卡编号
		Declare @EmpID nvarchar(15)				--车主编号
		Declare @EmpName nvarchar(20)			--车主名称
		Declare @CarNO nvarchar(10)				--车牌号
		Declare @CarNoType tinyint				--车牌类型
		Declare @YktID int						--卡开户ID

		Declare @IDNo int
		Declare @Small tinyint

		set nocount on
		if @InOut=2 or @InOut=4
			Set @Small=1
		else
			Set @Small=0

		--已存在此入场记录
		if  Exists(Select 1 from Park_CarIn where CFlag=@CFlag and MachNo=@MachNo and CardID=@CardID and InTime=@InTime and Small=@Small) return -1

		if @CFlag=0 --IC
			Begin
				if  Exists(Select 1 from ParK_CarOut where InMachNo=0 and CFlag=@CFlag and  CardID=@CardID and InTime=@InTime and Isnull(InUserName,'')='') 
					Begin
			
						UpDate ParK_CarOut Set InMachNo=@MachNo,InUserName=@InUserName where CFlag=@CFlag and InMachNo=0 and CardID=@CardID and InTime=@InTime and Isnull(InUserName,'')=''
						Return 0
					end
			end

		if @CFlag=3
			Begin
				if  Exists(Select 1 from ParK_CarOut where CFlag=@CFlag and CardID=@CardID and InTime=@InTime and Isnull(InUserName,'')='') 
					Begin
			
						UpDate ParK_CarOut Set InUserName=@InUserName where CFlag=@CFlag and CardID=@CardID and InTime=@InTime and Isnull(InUserName,'')=''
						Return 0
					end
			end
		if @CFlag<>0 and @CFlag<>3 --ID记录
			Begin
					--重复记录
				if  Exists(Select 1 from Park_CarIn where MachNo=@MachNo and CardID=@CardID and DateDiff(s,InTime,@InTime)<=3 ) 
						Return -1

				if  Exists(Select Top 1 1 from ParK_CarOut where CFlag=@CFlag and InMachNo=0 and CardID=@CardID and OutTime>@InTime and Isnull(InUserName,'')='' Order By OutTime) 
					Begin
						Select @IDNo=(Select Top 1 ID from ParK_CarOut where CFlag=@CFlag and InMachNo=0 and CardID=@CardID and OutTime>@InTime and Isnull(InUserName,'')='' Order By OutTime)
						UpDate ParK_CarOut Set InMachNo=@MachNo,InUserName=@InUserName,InTime=@InTime where ID=@IDNo
						Return 0
					end
			end

		if @CFlag=3
			Select @YktID=0,@CardNo=substring(@CardID,5,10),@EmpName='临时用户',@CarNO='',@CarNoType=0
		else
			Begin
				if exists(Select [ID] from Vw_YKT_NewCardIssuePark where CardID=@CardID and CFlag=@CFlag)
					Select @YktID=YKTID,@CardNo=CardNo,@EmpName=PerName,@CarNO=CarNO,@CarNoType=CarNoType from Vw_YKT_NewCardIssuePark where CardID=@CardID and CFlag=@CFlag
				else
					Select @YktID=0,@CardNo=substring(@CardID,5,10),@EmpName='无此用户',@CarNO='',@CarNoType=0
			end
		if  @Small=0 and Exists(Select 1 from Park_CarIn where CFlag=@CFlag and CardID=@CardID and InTime=@InTime and isnull(Small,0)=0) 
			Begin
				Select @IDNo=(Select Top 1 ID from Park_CarIn where CFlag=@CFlag and CardID=@CardID and InTime=@InTime and isnull(Small,0)=0)
				UpDate Park_CarIn Set MachNo=@MachNo,CardNo=@CardNo,EmpName=@EmpName,CarNO=@CarNO,CarNoType=@CarNoType,CardType=@CardType,InUserName=@InUserName,Small=0 where ID=@IDNo
				Return @IDNo
			end
		else
			begin
				insert into Park_CarIn(MachNo,YKTID,CardID,CFlag,CardNo,EmpName,CarNO,CardType,CarNoType,InTime,InUserName,Small)
					values(@MachNo,@YKTID,@CardID,@CFlag,@CardNo,@EmpName,@CarNO,@CardType,@CarNoType,@InTime,@InUserName,@Small)
				Return @@IDENTITY
			end
		set nocount off
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_ParK_CarOutReal') IS NOT NULL
	DROP PROCEDURE dbo.Sp_ParK_CarOutReal
GO
	CREATE    PROCEDURE [dbo].[Sp_ParK_CarOutReal]
		@OutMachNo tinyint,				--出口控制器编号
		@CardID Varchar(20),			--卡ID号
		@CFlag tinyint,					--卡介质(0为IC，1为ID，2IC做ID，3纸票 4纯车牌)
		@CardType tinyint,				--卡类型
		@FreeType tinyint,				--免费车类型
		@InTime datetime,				--入场时间
		@OutTime datetime,				--出场时间
		@OutUserName varchar(20),		--出场操作员
		@PayCharge Money,				--收费金额
		@BalanceMoney Money,			--卡上金额
		@DiscountNo tinyint,			--打折机号
		@TypeID  tinyint, 				--模式ID
		@DiscountTime datetime,			--折扣时间
		@AccountCharge  Money,			--应收金额
		@OutPic nVarchar(255) ,			--出场图片路径
		@UnusualMemo nvarchar(20), 		--异常备注
		@InOut tinyint,					--出入类型(0标准入口，1标准出口，2标准嵌套入口，3标准嵌套出口、4半嵌套入口，5半嵌套出口、6单通道)
		@DisAmount  Money,               --打折金额
		@PayType tinyint=0,              --支付类型
		@OrderNum nvarchar(20),          --交易流水号
		@CarEventNo int,                 --出场事件序列号
		@OutWay  tinyint=0              --0,表示正常出场，1，手工放行，2，异常放行
		
	AS
		set nocount on
		Declare @InID int						--入场记录ID
		Declare @YktID int						--开户ID号
		Declare @InInOut tinyint		        --入口控制器类型
		Declare @InControlID tinyint			--入口控制器编号
		Declare @InPic nvarchar(255)			--入场图片
		Declare @CardNo varchar(10)				--卡编号
		Declare @EmpName nvarchar(20)			--车主名称
		Declare @CarNO nvarchar(10)				--车牌号
		Declare @CarNoType tinyint				--车牌类型
		Declare @InUserName varchar(20)			--入场操作员
		Declare @oDiscountNo  tinyint 			--打折机号
		Declare @oTypeID  tinyint 				--模式ID
		Declare @oDiscountTime datetime			--折扣时间
		if exists(Select * from ParK_CarOut where CFlag=@CFlag and CardID=@CardID and OutTime=@OutTime) return -1
		Select @oDiscountNo=0,@oTypeID=0,@oDiscountTime=Null
		Select @InControlID=0,@CardNo=substring(@CardID,5,10),@EmpName='临时用户',@CarNO='',@CarNoType=0,@InUserName='',@InPic='',@YktID=0
		if exists(Select * from ParK_CarIn where CFlag=@CFlag and CardID=@CardID and InTime=@InTime)
			Begin
				Select @YktID=YktID,@InID=[ID],@InControlID=MachNo,@CardNo=CardNo,@EmpName=EmpName,@CarNO=CarNO,@CarNoType=CarNoType,
					@InTime=InTime,@InUserName=InUserName,@oDiscountNo=DiscountNo,@oTypeID=TypeID,@oDiscountTime=DiscountTime,@InPic=InPic from ParK_CarIn where CFlag=@CFlag and CardID=@CardID and InTime=@InTime
				Select @InInOut=InOut from dbo.Park_ControlSet where MachNo=@InControlID
				if @InInOut = 4 and @InOut=3 --入口为半嵌套入口，出口为嵌套出口
					Update ParK_CarIn set small=0 where [ID]=@InID --半嵌套入口记录更改为大车场记录
				else
					Delete from ParK_CarIn where [ID]=@InID  --删除原入场数据
			end 
		else
			if exists(Select * from Vw_YKT_NewCardIssuePark where CFlag=@CFlag and CardID=@CardID)
					Select @YktID=YktID,@CardNo=CardNo,@EmpName=PerName,@CarNO=CarNO,@CarNoType=CarNoType from Vw_YKT_NewCardIssuePark where CFlag=@CFlag and CardID=@CardID

		if @DiscountNo=0 	--折扣
			Select  @DiscountNo=Isnull(@oDiscountNo,0),@TypeID=isnull(@oTypeID,0),@DiscountTime=@oDiscountTime 
		if @InOut=5  --半嵌套出口 
			Delete from ParK_CarIn where CFlag=@CFlag and CardID=@CardID and  InTime<@InTime --删除大车场IC入场数据
		if @CardType>30 and  @CardType<35 --临时卡出场
			Begin
				--是否存在定点记录
				if  exists(Select * from ParK_CarOut where CFlag=@CFlag and CardID=@CardID and InTime=@InTime and PayType=0 and IsOut=0)
					Begin	
						UpDate ParK_CarOut Set OutTime=@OutTime,IsOut=1 where CFlag=@CFlag and CardID=@CardID and InTime=@InTime
						Update ParK_CentralCharge Set  isOut=1  where CFlag=@CFlag and CardID=@CardID and @InTime=@InTime
						Return 0
					end 		
			end
		if @InTime<='2010-01-01' Select @InTime=NUll
		if(@CardType >50 and @CardType<60) update Ykt_CardIssuePark set BalanceMoney = @BalanceMoney  where YKTID=@YktID
		insert into ParK_CarOut(InMachNo,OutMachNo,YKTID,CardID,CFlag,CardNo,EmpName,CarNO,CarNoType,CardType,FreeType,InTime,InUserName,OutTime,CentralTime,OutUserName,PayCharge,BalanceMoney,DiscountNo,TypeID,DiscountTime,AccountCharge,PayType,IsOut,UnusualMemo,InPic,OutPic,DisAmount,OrderNum,CarEventNo,OutWay,InID)
				values(@InControlID,@OutMachNo,@YKTID,@CardID,@CFlag,@CardNo,@EmpName,@CarNO,@CarNoType,@CardType,@FreeType,@InTime,@InUserName,@OutTime,@OutTime,@OutUserName,@PayCharge,@BalanceMoney,@DiscountNo,@TypeID,@DiscountTime,@AccountCharge,@PayType,1,@UnusualMemo,@InPic,@OutPic,@DisAmount,@OrderNum,@CarEventNo,@OutWay,@InID)
		Return @@IDENTITY
		set nocount off
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================

IF OBJECT_ID ('dbo.Sp_Interface_Park_CarOutReal') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Interface_Park_CarOutReal
GO
	CREATE    PROCEDURE [dbo].[Sp_Interface_Park_CarOutReal]
		@InID int,						--入场记录ID
		@OutMachNo tinyint,				--出口控制器编号
		@FreeType tinyint,				--免费车类型
		@OutTime datetime,				--出场时间
		@OutUserName varchar(20),		--出场操作员
		@PayCharge Money,				--收费金额
		@BalanceMoney Money,			--卡上金额
		@DiscountNo tinyint,			--打折机号
		@TypeID  tinyint, 				--模式ID
		@DiscountTime datetime,			--折扣时间
		@AccountCharge  Money,			--应收金额
		@OutPic nVarchar(255) ,			--出场图片路径
		@UnusualMemo nvarchar(20), 		--异常备注
		@DisAmount  Money,               --打折金额
		@PayType tinyint=0,              --支付类型
		@OrderNum nvarchar(20)           --交易流水号
	AS
		set nocount on
		Declare @YktID int						--开户ID号
		Declare @InInOut tinyint		        --入口控制器类型
		Declare @InControlID tinyint			--入口控制器编号
		Declare @InPic nvarchar(255)			--入场图片
		Declare @CardNo varchar(10)				--卡编号
		Declare @EmpName nvarchar(20)			--车主名称
		Declare @CarNO nvarchar(10)				--车牌号
		Declare @CarNoType tinyint				--车牌类型
		Declare @InUserName varchar(20)			--入场操作员
		Declare @oDiscountNo  tinyint 			--打折机号
		Declare @oTypeID  tinyint 				--模式ID
		Declare @oDiscountTime datetime			--折扣时间
		Declare @InTime datetime				--入场时间
		Declare @CardID Varchar(20) 			--卡ID号
		Declare @InOut tinyint
		Declare @CFlag tinyint					--卡介质(0为IC，1为ID，2IC做ID，3纸票 4纯车牌)
		Declare @CardType tinyint				--卡类型
		SELECT TOP 1 @InOut = InOut from Park_ControlSet where machNo = @OutMachNo
		if exists(Select * from ParK_CarOut where CFlag=@CFlag and CardID=@CardID and OutTime=@OutTime) return -1
		Select @oDiscountNo=0,@oTypeID=0,@oDiscountTime=Null
		Select @InControlID=0,@CardNo=substring(@CardID,5,10),@EmpName='临时用户',@CarNO='',@CarNoType=0,@InUserName='',@InPic='',@YktID=0
		Select @YktID=YktID,@CFlag=CFlag,@CardType=CardType,@InTime=InTime,@CardID=CardID,@InControlID=MachNo,@CardNo=CardNo,@EmpName=EmpName,@CarNO=CarNO,@CarNoType=CarNoType,
					@InTime=InTime,@InUserName=InUserName,@oDiscountNo=DiscountNo,@oTypeID=TypeID,@oDiscountTime=DiscountTime,@InPic=InPic from ParK_CarIn where ID = @InID
		Select @InInOut=InOut from dbo.Park_ControlSet where MachNo=@InControlID
		if @InInOut = 4 and @InOut=3 --入口为半嵌套入口，出口为嵌套出口
			Update ParK_CarIn set small=0 where [ID]=@InID --半嵌套入口记录更改为大车场记录
		else
			Delete from ParK_CarIn where [ID]=@InID  --删除原入场数据
					
		if @DiscountNo=0 	--折扣
			Select  @DiscountNo=Isnull(@oDiscountNo,0),@TypeID=isnull(@oTypeID,0),@DiscountTime=@oDiscountTime 
		if @InOut=5  --半嵌套出口 
			Delete from ParK_CarIn where CFlag=@CFlag and CardID=@CardID and  InTime<@InTime --删除大车场IC入场数据
		if @CardType>30 and  @CardType<35 --临时卡出场
			Begin
				--是否存在定点记录
				if  exists(Select * from ParK_CarOut where CFlag=@CFlag and CardID=@CardID and InTime=@InTime and PayType=0 and IsOut=0)
					Begin	
						UpDate ParK_CarOut Set OutTime=@OutTime,IsOut=1 where CFlag=@CFlag and CardID=@CardID and InTime=@InTime
						Update ParK_CentralCharge Set  isOut=1  where CFlag=@CFlag and CardID=@CardID and @InTime=@InTime
						Return 0
					end 		
			end
		if @InTime<='2010-01-01' Select @InTime=NUll
		insert into ParK_CarOut(InMachNo,OutMachNo,YKTID,CardID,CFlag,CardNo,EmpName,CarNO,CarNoType,CardType,FreeType,InTime,InUserName,OutTime,CentralTime,OutUserName,PayCharge,BalanceMoney,DiscountNo,TypeID,DiscountTime,AccountCharge,PayType,IsOut,UnusualMemo,InPic,OutPic,DisAmount,OrderNum)
				values(@InControlID,@OutMachNo,@YKTID,@CardID,@CFlag,@CardNo,@EmpName,@CarNO,@CarNoType,@CardType,@FreeType,@InTime,@InUserName,@OutTime,@OutTime,@OutUserName,@PayCharge,@BalanceMoney,@DiscountNo,@TypeID,@DiscountTime,@AccountCharge,@PayType,1,@UnusualMemo,@InPic,@OutPic,@DisAmount,@OrderNum)
		select @@IDENTITY
		set nocount off
GO


-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场-出场>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Park_CarOut') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Park_CarOut
GO
	CREATE  PROCEDURE [dbo].[Sp_Park_CarOut]
		@OutMachNo tinyint,			--出口控制器编号
		@CardID Varchar(20),			--卡ID号
		@CFlag tinyint,					--卡介质(0为IC，1为ID，2IC做ID，3纸票)	
		@CardType tinyint,				--卡类型
		@FreeType tinyint,				--免费车类型
		@InTime datetime,				--入场时间
		@OutTime datetime,				--出场时间
		@OutUserName varchar(20),		--出场操作员
		@PayCharge Money,				--收费金额
		@BalanceMoney Money,			--卡上金额
		@DiscountNo tinyint,			--打折机号
		@TypeID  tinyint, 				--模式ID
		@DiscountTime datetime,			--折扣时间
		@AccountCharge  Money,			--应收金额
		@InOut tinyint,					--出入类型(0标准入口，1标准出口，2标准嵌套入口，3标准嵌套出口、4半嵌套入口，5半嵌套出口、6单通道)
		@ETCCarNo nvarchar(10),				--车牌号
		@CarEventNo int,                 --出场事件序列号
		@OutWay  tinyint=0              --0,表示正常出场，1，手工放行，2，异常放行
		 
	AS
		set nocount on
		Declare @InID int					
		Declare @YktID int						--开户ID号
		Declare @InInOut tinyint			        --入口控制器类型
		Declare @InMachNo tinyint				--入口控制器编号
		Declare @InPic nvarchar(255)			--入场图片
		Declare @CardNo varchar(10)				--卡编号
		Declare @EmpName nvarchar(20)				--车主名称
		Declare @CarNO nvarchar(10)				--车牌号
		Declare @CarNoType tinyint				--车牌类型
		Declare @InUserName varchar(20)				--入场操作员

		Declare @oDiscountNo  tinyint 				--打折机号
		Declare @oTypeID  tinyint 				--模式ID
		Declare @oDiscountTime datetime				--折扣时间
		Declare @NewID int

		if exists(Select * from Park_CarOut where CFlag=@CFlag and CardID=@CardID and OutTime=@OutTime) return -1
        if @CFlag=4
           Begin
              Select @YKTID=0,@InMachNo=0,@CardNo=@ETCCarNo,@EmpName='临时用户',@CarNO=@ETCCarNo,@CarNoType=0,@InUserName='',@InPic='',@YktID=0
           end
        else
           Begin
		       Select @YKTID=0,@InMachNo=0,@CardNo=substring(@CardID,5,10),@EmpName='临时用户',@CarNO='',@CarNoType=0,@InUserName='',@InPic='',@YktID=0
           end
		if @InOut=1 or  @InOut=6 --直接出
			Begin
				select @InID=0 
				if @CFlag>0 --不为IC
					Begin
						if exists(Select  * from Park_CarIn where Small=0 and CFlag=@CFlag and CardID=@CardID and InTime<@OutTime)
							Select @InID=(Select Top 1 [ID] from Park_CarIn where CFlag=@CFlag and CardID=@CardID and small=0 and InTime<@OutTime Order By InTime Desc)
					end
				else
					Begin
						if exists(Select * from Park_CarIn where Small=0 and CFlag=@CFlag and CardID=@CardID and InTime=@InTime)
						Select @InID=(Select Top 1 [ID] from Park_CarIn where Small=0 and CFlag=@CFlag and CardID=@CardID and InTime=@InTime)
					end
				if @InID>0
					Begin
						Select @YktID=YktID,@InID=[ID],@InMachNo=MachNo,@CardNo=CardNo,@EmpName=EmpName,@CarNO=CarNO,@CarNoType=CarNoType,@InPic=InPic,
							@InTime=InTime,@InUserName=InUserName,@oDiscountNo=DiscountNo,@oTypeID=TypeID,@oDiscountTime=DiscountTime  from Park_CarIn where [ID]=@InID
						Delete from Park_CarIn where [ID]=@InID  --删除原入场数据
					end 
				else
					Begin
						if exists(Select [ID] from Vw_YKT_NewCardIssuePark where CFlag=@CFlag and CardID=@CardID)
							Select @YKTID=YKTID,@CardNo=CardNo,@EmpName=PerName,@CarNO=CarNO,@CarNoType=CarNoType from Vw_YKT_NewCardIssuePark where CFlag=@CFlag and CardID=@CardID
					end

				if @DiscountNo=0 	--折扣
					Select  @DiscountNo=Isnull(@oDiscountNo,0),@TypeID=isnull(@oTypeID,0),@DiscountTime=@oDiscountTime 
			end	
		else
			Begin
				Select @InID=0
				if exists(Select Top 1 * from Park_CarIn where CFlag=@CFlag and CardID=@CardID and small=1 and InTime<@OutTime Order By InTime Desc)
					Select @InID=(Select Top 1 [ID] from Park_CarIn where CFlag=@CFlag and CardID=@CardID and small=1 and InTime<@OutTime Order By InTime Desc)
				if @InID>0
					Begin
						Select @YktID=YktID,@InID=[ID],@InMachNo=MachNo,@CardNo=CardNo,@EmpName=EmpName,@CarNO=CarNO,@CarNoType=CarNoType,
							@InTime=InTime,@InUserName=InUserName,@oDiscountNo=DiscountNo,@oTypeID=TypeID,@oDiscountTime=DiscountTime  from Park_CarIn where ID=@InID 
						Select @InInOut=InOut from dbo.Park_ControlSet where MachNo=@InMachNo
						if @InInOut=4 and @InOut=3 --入口为半嵌套入口，出口为嵌套出口
							Update Park_CarIn set small=0 where ID=@InID --半嵌套入口记录更改为大车场记录
						else
							Delete from Park_CarIn where [ID]=@InID  --删除原入场数据
					end 
				else
					if exists(Select * from Vw_YKT_NewCardIssuePark where CFlag=@CFlag and CardID=@CardID)
							Select @YktID=YktID,@CardNo=CardNo,@EmpName=PerName,@CarNO=CarNO,@CarNoType=CarNoType from Vw_YKT_NewCardIssuePark where CFlag=@CFlag and CardID=@CardID
				if @InOut=5 --半嵌套出口 
					Begin
						Delete from Park_CarIn where CFlag=@CFlag and CardID=@CardID and  InTime<@InTime --删除大车场IC入场数据
					end
			end
		if @InTime<='2010-01-01' Select @InTime=Null
			--是否存在定点记录
		if @CFlag=0 and @CardType>30 and  @CardType<35
			if  exists(Select * from ParK_CarOut where CFlag=@CFlag and CardID=@CardID and InTime=@InTime and PayType=0 and IsOut=0)
				Begin	--更新记录的出入标志
					UpDate ParK_CarOut Set OutTime=@OutTime,IsOut=1 where CFlag=@CFlag and CardID=@CardID and InTime=@InTime
					Update ParK_CentralCharge Set  isOut=1 where CFlag=@CFlag and CardID=@CardID and @InTime=@InTime
					Return 0
				end 
		insert into ParK_CarOut(InMachNo,OutMachNo,YKTID,CardID,CFlag,CardNo,EmpName,CarNO,CarNoType,CardType,FreeType,InTime,InUserName,OutTime,CentralTime,OutUserName,PayCharge,BalanceMoney,DiscountNo,TypeID,DiscountTime,AccountCharge,PayType,IsOut,InPic,InID,CarEventNo,OutWay)
				values(@InMachNo,@OutMachNo,@YKTID,@CardID,@CFlag,@CardNo,@EmpName,@CarNO,@CarNoType,@CardType,@FreeType,@InTime,@InUserName,@OutTime,@OutTime,@OutUserName,@PayCharge,@BalanceMoney,@DiscountNo,@TypeID,@DiscountTime,@AccountCharge,0,1,@InPic,@InID,@CarEventNo,@OutWay)
		Return @@IDENTITY
		set nocount off

GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_ParK_CarInReal') IS NOT NULL
	DROP PROCEDURE dbo.Sp_ParK_CarInReal
GO
	CREATE PROCEDURE [dbo].[Sp_ParK_CarInReal]
		@MachNo tinyint ,					--控制器编号
		@CardID Varchar(20), 				--卡ID号
		@CFlag tinyint,						--卡介质(0为IC，1为ID，2IC做ID，3纸票 4纯车牌)	
		@CardType tinyint,					--卡类型
		@InTime datetime,					--入场时间
		@InUserName nvarchar(20), 			--入场操作员
		@CarNO nvarchar(10),				--车牌号
		@CarNoType tinyint,					--车牌类型
		@InPic nvarchar(255),				--入场图片
		@InOut tinyint,					    --出入类型(0标准入口，1标准出口，2标准嵌套入口，3标准嵌套出口、4半嵌套入口，5半嵌套出口、6单通道)
		@CarEventNo int,                    --车辆事件序例号
		@InWay  tinyint                     --入场方式
		With ENCRYPTION 
	AS
		--返回-1已经存在出场记录，>0入场记录ID
		Declare @CardNo varchar(10)				--卡编号
		Declare @EmpName nvarchar(20)			--车主名称
		Declare @YKTID int
		Declare @IDNo int
		Declare @Small tinyint
		Declare @IsLocked tinyint = 0
		set nocount on
		if @InOut=2 or @InOut=4
			Set @Small=1
		else
			Set @Small=0

		if @CFlag>0
			--主要用于多进多
			Delete from ParK_CarIn where CFlag=@CFlag and CardID=@CardID and Small=@Small
		else
			--已存在此入场记录
			if  Exists(Select 1 from ParK_CarIn where MachNo=@MachNo and CFlag=@CFlag and CardID=@CardID and InTime=@InTime and Small=@Small) return -1
		if @CFlag=3
			Select @YKTID=0,@CardNo=Substring(@CardID,9,8),@EmpName='临时用户'
		else
			Begin
				if exists(Select [ID] from Vw_YKT_NewCardIssuePark where  CFlag=@CFlag and CardID=@CardID)
					Select @YKTID=YKTID,@CardNo=CardNo,@EmpName=PerName from Vw_YKT_NewCardIssuePark where  CFlag=@CFlag and CardID=@CardID
				else
					Select @YKTID=0,@CardNo=@CarNO,@EmpName='临时用户'		
			end 
		--处理自动锁车逻辑
		IF @Small = 0
		BEGIN
			if exists(Select 1 from Yun_AutoLockingSet where CarNo = @CarNO AND LockType = 0)
			begin
				set @IsLocked = 1
			end
		end
		insert into ParK_CarIn(MachNo,YKTID,CardID,CFlag,CardNo,EmpName,CarNO,CarNoType,CardType,InTime,InUserName,InPic,Small,IsLocked,CarEventNo,InWay)
				values(@MachNo,@YKTID,@CardID,@CFlag,@CardNo,@EmpName,@CarNO,@CarNoType,@CardType,@InTime,@InUserName,@InPic,@Small,@IsLocked,@CarEventNo,@InWay)
		Return @@IDENTITY
		set nocount off
GO



-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Interface_Park_CarInReal') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Interface_Park_CarInReal
GO
	CREATE PROCEDURE [dbo].[Sp_Interface_Park_CarInReal]
		@MachNo tinyint ,					--控制器编号
		@CardID Varchar(20), 				--卡ID号
		@CFlag tinyint,						--卡介质(0为IC，1为ID，2IC做ID，3纸票 4纯车牌)	
		@CardType tinyint,					--卡类型
		@InTime datetime,					--入场时间
		@InUserName nvarchar(20), 			--入场操作员
		@CarNO nvarchar(10),				--车牌号
		@CarNoType tinyint,					--车牌类型
		@InPic nvarchar(255)				--入场图片
		With ENCRYPTION 
	AS
		--返回-1已经存在出场记录，>0入场记录ID
		Declare @CardNo varchar(10)				--卡编号
		Declare @EmpName nvarchar(20)			--车主名称
		Declare @YKTID int
		Declare @IDNo int
		Declare @Small tinyint
		Declare @InOut tinyint
		SELECT TOP 1 @InOut = InOut from Park_ControlSet where machNo = @MachNo

		set nocount on
		if @InOut=2 or @InOut=4
			Set @Small=1
		else
			Set @Small=0

		if @CFlag>0
			--主要用于多进多
			Delete from ParK_CarIn where CFlag=@CFlag and CardID=@CardID and Small=@Small
		else
			--已存在此入场记录
			if  Exists(Select * from ParK_CarIn where MachNo=@MachNo and CFlag=@CFlag and CardID=@CardID and InTime=@InTime and Small=@Small) return -1
		if @CFlag=3
			Select @YKTID=0,@CardNo=Substring(@CardID,5,10),@EmpName='临时用户'
		else
			Begin
				if exists(Select [ID] from Vw_YKT_NewCardIssuePark where  CFlag=@CFlag and CardID=@CardID)
					Select @YKTID=YKTID,@CardNo=CardNo,@EmpName=PerName from Vw_YKT_NewCardIssuePark where  CFlag=@CFlag and CardID=@CardID
				else
					Select @YKTID=0,@CardNo=@CarNO,@EmpName='临时用户'		
			end 

		insert into ParK_CarIn(MachNo,YKTID,CardID,CFlag,CardNo,EmpName,CarNO,CarNoType,CardType,InTime,InUserName,InPic,Small)
				values(@MachNo,@YKTID,@CardID,@CFlag,@CardNo,@EmpName,@CarNO,@CarNoType,@CardType,@InTime,@InUserName,@InPic,@Small)
		SELECT @@IDENTITY
		set nocount off
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_CardMoneyC2') IS NOT NULL
	DROP PROCEDURE dbo.Sp_CardMoneyC2
GO
	CREATE PROCEDURE Sp_CardMoneyC2
	@SDate datetime,        --开始时间
	@EDate datetime,        --结束时间
	@UserName nvarchar(20),        --操作员
	@CardType Tinyint        --卡类
	
	AS
		truncate table TmpCardMoneyC2
		if @UserName=''
			Begin
				if @CardType>0
				Insert into TmpCardMoneyC2(UserName,CarNO,CardNO,MB,sTypeName,SETime,Total,UserDate,ReMark,MMM)
				SELECT a.Username,a.CarNo,a.CardNo,dbo.park_GetSperateMonthCharge(c.stype,c.ChargeMoney),a.sTypeName,
				Right(Convert(Varchar(10),a.NstartDate,102),5) + '-' + Right(Convert(Varchar(10),a.NEndDate,102),5) as SETime,
					a.BalanceMoney as Total,a.UserDate,a.Remark,
					dbo.YKT_GETMMM(a.BalanceMoney,dbo.park_GetSperateMonthCharge(c.stype,c.ChargeMoney),Month(a.NEndDate))
				FROM Vw_YKT_CardRsMoney a LEFT OUTER JOIN
					dbo.Park_MonthSet c ON a.ctype=c.CardType and a.monthModel=c.sType 
				where a.ctype=@CardType and a.NStartDate<=@SDate and @EDate<=a.NEndDate Order By a.Username,a.UserDate
				else  
				Insert into TmpCardMoneyC2(UserName,CarNO,CardNO,MB,sTypeName,SETime,Total,UserDate,ReMark,MMM)
				SELECT a.Username,a.CarNo,a.CardNo,dbo.park_GetSperateMonthCharge(c.stype,c.ChargeMoney),a.sTypeName,
				Right(Convert(Varchar(10),a.NstartDate,102),5) + '-' + Right(Convert(Varchar(10),a.NEndDate,102),5) as SETime,
					a.BalanceMoney as Total,a.UserDate,a.Remark,
					dbo.YKT_GETMMM(a.BalanceMoney,dbo.park_GetSperateMonthCharge(c.stype,c.ChargeMoney),Month(a.NEndDate))
				FROM Vw_YKT_CardRsMoney a LEFT OUTER JOIN
					dbo.Park_MonthSet c ON a.ctype=c.CardType and a.monthModel=c.sType 
				where (a.ctype>10 and a.ctype<17) and a.NStartDate<=@SDate and @EDate<=a.NEndDate Order By a.Username,a.UserDate
			end
			else
			Begin
					if @CardType>0
				Insert into TmpCardMoneyC2(UserName,CarNO,CardNO,MB,sTypeName,SETime,Total,UserDate,ReMark,MMM)
				SELECT a.Username,a.CarNo,a.CardNo,dbo.park_GetSperateMonthCharge(c.stype,c.ChargeMoney),a.sTypeName,
				Right(Convert(Varchar(10),a.NstartDate,102),5) + '-' + Right(Convert(Varchar(10),a.NEndDate,102),5) as SETime,
					a.BalanceMoney as Total,a.UserDate,a.Remark,
					dbo.YKT_GETMMM(a.BalanceMoney,dbo.park_GetSperateMonthCharge(c.stype,c.ChargeMoney),Month(a.NEndDate))
				FROM Vw_YKT_CardRsMoney a LEFT OUTER JOIN
					dbo.Park_MonthSet c ON a.ctype=c.CardType and a.monthModel=c.sType 
				where a.ctype=@CardType and a.NStartDate<=@SDate and @EDate<=a.NEndDate and a.Username=@Username Order By a.UserDate
				else
				Insert into TmpCardMoneyC2(UserName,CarNO,CardNO,MB,sTypeName,SETime,Total,UserDate,ReMark,MMM)
				SELECT a.Username,a.CarNo,a.CardNo,dbo.park_GetSperateMonthCharge(c.stype,c.ChargeMoney),a.sTypeName,
				Right(Convert(Varchar(10),a.NstartDate,102),5) + '-' + Right(Convert(Varchar(10),a.NEndDate,102),5) as SETime,
					a.BalanceMoney as Total,a.UserDate,a.Remark,
					dbo.YKT_GETMMM(a.BalanceMoney,dbo.park_GetSperateMonthCharge(c.stype,c.ChargeMoney),Month(a.NEndDate))
				FROM Vw_YKT_CardRsMoney a LEFT OUTER JOIN
					dbo.Park_MonthSet c ON a.ctype=c.CardType and a.monthModel=c.sType 
				where (a.ctype>10 and a.ctype<20) and a.NStartDate<=@SDate and @EDate<=a.NEndDate  and a.Username=@Username Order By a.UserDate
			end
		if Exists(Select Top 1 * from TmpCardMoneyC2)
			Begin
				UpDate TmpCardMoneyC2 Set M1=Case when Total>0 then MB else -MB End where SubString(MMM,1,1)='1' and Total<>0
				UpDate TmpCardMoneyC2 Set M2=Case when Total>0 then MB else -MB End where SubString(MMM,2,1)='1' and Total<>0
				UpDate TmpCardMoneyC2 Set M3=Case when Total>0 then MB else -MB End where SubString(MMM,3,1)='1' and Total<>0
				UpDate TmpCardMoneyC2 Set M4=Case when Total>0 then MB else -MB End where SubString(MMM,4,1)='1' and Total<>0
				UpDate TmpCardMoneyC2 Set M5=Case when Total>0 then MB else -MB End where SubString(MMM,5,1)='1' and Total<>0
				UpDate TmpCardMoneyC2 Set M6=Case when Total>0 then MB else -MB End where SubString(MMM,6,1)='1' and Total<>0
				UpDate TmpCardMoneyC2 Set M7=Case when Total>0 then MB else -MB End where SubString(MMM,7,1)='1' and Total<>0
				UpDate TmpCardMoneyC2 Set M8=Case when Total>0 then MB else -MB End where SubString(MMM,8,1)='1' and Total<>0
				UpDate TmpCardMoneyC2 Set M9=Case when Total>0 then MB else -MB End where SubString(MMM,9,1)='1' and Total<>0
				UpDate TmpCardMoneyC2 Set M10=Case when Total>0 then MB else -MB End where SubString(MMM,10,1)='1' and Total<>0
				UpDate TmpCardMoneyC2 Set M11=Case when Total>0 then MB else -MB End where SubString(MMM,11,1)='1' and Total<>0
				UpDate TmpCardMoneyC2 Set M12=Case when Total>0 then MB else -MB End where SubString(MMM,12,1)='1' and Total<>0
				--Insert into TmpCardMoneyC2
				--        Select UserName+Char(9),'','',Null,'','小   计',
				--            Sum(IsNull(M1,0)),Sum(IsNull(M2,0)),Sum(IsNull(M3,0)),Sum(IsNull(M4,0)),
				--            Sum(IsNull(M5,0)),Sum(IsNull(M6,0)),Sum(IsNull(M7,0)),Sum(IsNull(M8,0)),
				--            Sum(IsNull(M9,0)),Sum(IsNull(M10,0)),Sum(IsNull(M11,0)),Sum(IsNull(M12,0)),
				--            Sum(IsNull(Total,0)),getdate(),'',''
				--             From TmpCardMoneyC2 Group BY UserName  
			end
			select (select count(*) from TmpCardMoneyC2 where UserDate <=t1.UserDate) as rowindex ,* FROM TmpCardMoneyC2 t1 order by rowindex
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场-临时车收费日明细>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Park_DaysMoneyC1') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Park_DaysMoneyC1
GO	
	CREATE PROCEDURE [dbo].[Sp_Park_DaysMoneyC1]
	@SDate datetime,	--开始时间
	@EDate datetime,	--结束时间
	@UserName nvarchar(20)	--操作员
	
	AS
		truncate table Park_TmpDaysMoneyC1

		if @UserName=''
			Insert into Park_TmpDaysMoneyC1(UserName,CheckName,InDate,OutDate,TA,TB,TC,TD,TF,Total)
				Select UserName,'',InTime,OutTime,
			Case when (Cardtype=31 or  Cardtype=21 or Cardtype=61) and PayCharge>0 then PayCharge else 0 end TA,
			Case when (Cardtype=32 or  Cardtype=22 or Cardtype=62) and PayCharge>0 then PayCharge else 0 end TB,
			Case when (Cardtype=33 or  Cardtype=23 or Cardtype=63) and PayCharge>0 then PayCharge else 0 end TC,
			Case when (Cardtype=34 or  Cardtype=24 or Cardtype=64) and PayCharge>0 then PayCharge else 0 end TD,
			Case when AccountCharge-PayCharge=0 then 0 else AccountCharge-PayCharge end as TF,
			PayCharge as Total
					from Vw_Park_CarCharge
				where ( (CardType>20 and CardType<25) Or Cardtype=40 Or (CardType>30 and CardType<35) or CardType>60 ) and (OutTime Between @SDate and @EDate)
			Order By UserName,OutTime
		else
			Insert into Park_TmpDaysMoneyC1(UserName,CheckName,InDate,OutDate,TA,TB,TC,TD,TF,Total)
				Select UserName,'',InTime,OutTime,
			Case when (Cardtype=31 or  Cardtype=21 or Cardtype=61) and PayCharge>0 then PayCharge else 0 end TA,
			Case when (Cardtype=32 or  Cardtype=22 or Cardtype=62) and PayCharge>0 then PayCharge else 0 end TB,
			Case when (Cardtype=33 or  Cardtype=23 or Cardtype=63) and PayCharge>0 then PayCharge else 0 end TC,
			Case when (Cardtype=34 or  Cardtype=24 or Cardtype=64) and PayCharge>0 then PayCharge else 0 end TD,
			Case when AccountCharge-PayCharge=0 then 0 else AccountCharge-PayCharge end as TF,
			PayCharge as Total
					from Vw_Park_CarCharge
				where ((CardType>20 and CardType<25) Or Cardtype=40 Or (CardType>30 and CardType<35) or CardType>60) and Username=@Username and (OutTime Between @SDate and @EDate)
			Order By OutTime
		if Exists(Select Top 1 * from Park_TmpDaysMoneyC1)
			Insert into Park_TmpDaysMoneyC1
				Select UserName+Char(13),'小 计',Null,Null,
				Sum(IsNull(TA,0)),Sum(IsNull(TB,0)),Sum(IsNull(TC,0)),Sum(IsNull(TD,0)),
				Sum(IsNull(TF,0)),Sum(IsNull(Total,0))
					From Park_TmpDaysMoneyC1 Group BY UserName

		Select UserName,CheckName,InDate,OutDate,
		TA,TB,TC,TD,Total,TF From Park_TmpDaysMoneyC1 Order By UserName,OutDate
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Park_SZCommerce') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Park_SZCommerce
GO
	Create PROCEDURE Sp_Park_SZCommerce
	@CarNo nvarchar(10)='8888888888', --车牌号码
	@CardType integer,			--卡类
	@InTime datetime,		--入场时间
	@OutTime datetime,		--出场时间
	@ParkType as integer=0,		--车场类型 0 大车场 1 小车场 ,
	@Amount  as money output	--收费金额
	
	AS
	SET DATEFIRST 7
	if (@OutTime<=@InTime)
		begin
				set @Amount =0
				return
		end
	declare @FreeMinute as integer
	declare @break_datetime as varchar(128)
	declare @m as integer--高峰期
	declare @n as integer--高峰期
	declare @m_1 as integer--非高峰期
	declare @n_1 as integer--非高峰期
	declare @m_2 as integer--(天余数)
	declare @n_2 as integer--(天数)
	declare @d as integer--(天数)
	declare @wd_FirstHour as float	--工作日第一小时费用
	declare @Nwd_FirstHour as float --非工作日第一小时费用
	declare @dayFee as float 	--每天费用
	declare @NoWday_Dtime as int 		--非工作日收费时段(分钟为单位)
	declare @NoWday_DtimeFee as money 	--非工作日时段收费额
	declare @NotGF_Dtime as int 		--非高峰期收费时段(分钟为单位)
	declare @NotGF_DtimeFee as money	--非高峰期时段收费额
	declare @GF_Dtime as int 		--高峰期收费时段(分钟为单位)
	declare @GF_DtimeFee as money 		--高峰期时段收费额
	declare @GF_Beg_H as int 		--高峰期起始小时
	declare @GF_Beg_M as int 		--高峰期起始分钟
	declare @GF_End_H as int 		--高峰期截至小时
	declare @GF_End_M as int 		--高峰期截至分钟
	declare @GZ_MonthBeg1 as int 	--特殊非工作日起始月
	declare @GZ_DayBeg1 as int 	--特殊非工作日起始日
	declare @GZ_MonthEnd1 as int 	--特殊非工作日结束月
	declare @GZ_DayEnd1 as int 	--特殊非工作日结束日
	declare @GZ_MonthBeg2 as int 	--特殊工作日起始月
	declare @GZ_DayBeg2 as int 	--特殊工作日起始日
	declare @GZ_MonthEnd2 as int	--特殊工作日结束月
	declare @GZ_DayEnd2 as int 	--特殊工作日结束日
	declare @GFDatetime as datetime
	declare @GFDatetime2 as datetime
	declare @FirstTimeBetween as int	--首段收费时间(分钟)
	declare @FirstTimeN as int 		--判断时间间隔是否超过首段
	declare @FirstTimeM as int  		--判断时间间隔是否超过首段
	declare @IncludeBool as tinyint 	--是否包含免费时间 
	Declare @PIsDate as tinyint 
	declare @InTime2 as datetime 	--入场时间
	declare @InTime3 as datetime
	declare @m1 as money		--本次出场单独计费金额 @m1
	declare @m2 as money		--本次出场实收金额 @m2
	declare @c2 as money		--已收金额 @c2	
	--select @CardType =31,@InTime='2009-09-21 20:00:00',@OutTime='2009-09-22 09:00:00',@ParkType=0
	Select @InTime=Cast(Convert(varchar(17),@InTime,120)+'00' as smalldatetime),@OutTime=Cast(Convert(varchar(17),@OutTime,120)+'00' as smalldatetime)
	select @Amount =0
	if @CardType<30 or (@CardType>39 and @CardType<50) or  @CardType>59  --卡类只有临时卡和储值卡
		return

	select @FreeMinute=FreeMinute,@IncludeBool=FreeInclude from Park_ESMain where ESNO=3 and CardType = @CardType
	select top 1 @GZ_MonthBeg1=month(NoWorkDayBegin),@GZ_DayBeg1=day(NoWorkDayBegin),@GZ_MonthEnd1=month(NoWorkDayend),@GZ_DayEnd1=day(NoWorkDayend),
			@GZ_MonthBeg2=month(WorkDayTBegin),@GZ_DayBeg2=day(WorkDayTBegin),@GZ_MonthEnd2=month(WorkDayTEnd),@GZ_DayEnd2=day(WorkDayTEnd),
			@FirstTimeBetween=60  from Park_Es4_F
			where CardType = @CardType
	--if (@IncludeBool<>1) set @FreeMinute=-99999
	if datediff(minute,@InTime,@OutTime)<=@FreeMinute --免费时间(以分钟为单位)
		begin
		select @Amount =0
		return
		end
	if @IncludeBool=1	--不包含免费时间
		begin
			select @InTime=dateadd(minute,@FreeMinute,@InTime)
		end

	select @n_2=datediff(minute,@InTime,@OutTime)/1440--天
	select @m_2=datediff(minute,@InTime,@OutTime) % 1440

	select @wd_FirstHour=WorkDayFirstHourAmount,@NoWday_Dtime=60, @NoWday_DtimeFee=NoWorkChgAmount,@Nwd_FirstHour=NoWorkDayFirstHourAmount,@NotGF_Dtime=60,@NotGF_DtimeFee=NotPeakChgAmount,@GF_Dtime=30,@GF_DtimeFee=PeakChgAmount,@dayFee=DayTopAmount,@PIsDate=isnull([ISDATE],0),@GF_Beg_H=cast(left(PeakFieldBegin,2) as integer), @GF_Beg_M=cast(right(PeakFieldBegin,2) as integer),@GF_End_H=cast(left(PeakFieldEnd,2) as integer),@GF_End_M=cast(right(PeakFieldEnd,2) as integer) from   vw_park_ES4 where CardType = @CardType   --计费单位30分钟，可修改

	set @n=(select case @GF_Dtime when 0 then 0 else (datediff(minute,@InTime,@OutTime)/@GF_Dtime) end)		--高峰时段
	set @m=(select case @GF_Dtime when 0 then 0 else (datediff(minute,@InTime,@OutTime) % @GF_Dtime) end)

	set @n_1=(select case @NotGF_Dtime when 0 then 0 else (datediff(minute,@InTime,@OutTime)/@NotGF_Dtime) end)	--非高峰时段
	set @m_1=(select case @NotGF_Dtime when 0 then 0 else (datediff(minute,@InTime,@OutTime) % @NotGF_Dtime) end)

	set @FirstTimeN=datediff(minute,@InTime,@OutTime) / 60 --首段1小时,如果修改首段,修改60即可
	set @FirstTimeM=datediff(minute,@InTime,@OutTime) % 60

	set @GFDatetime=convert(datetime,convert(varchar(10),@InTime,126)+space(1)+convert(varchar(2),@GF_Beg_H)+':'+convert(varchar(2),@GF_Beg_M)+':'+convert(varchar(2),DATEPART(ss,@InTime)))
	set @GFDatetime2=convert(datetime,convert(varchar(10),@InTime,126)+space(1)+convert(varchar(2),@GF_End_H)+':'+convert(varchar(2),@GF_End_M)+':'+convert(varchar(2),DATEPART(ss,@InTime)))
	if  (@PIsDate<=0)
		begin  
				--本次出场单独计费金额 @m1
			declare @GZDayBeg1 as datetime
			declare @GZDayEnd1 as datetime
			declare @GZDayBeg2 as datetime
			declare @GZDayEnd2 as datetime

			set @GZDayBeg1=convert(datetime,convert(varchar(10),datepart(yy,@InTime))+'-'+convert(varchar(2),@GZ_MonthBeg1)+'-'+convert(varchar(2),@GZ_DayBeg1))
			set @GZDayEnd1=convert(datetime,convert(varchar(10),datepart(yy,@InTime))+'-'+convert(varchar(2),@GZ_MonthEnd1)+'-'+convert(varchar(2),@GZ_DayEnd1))

			set @GZDayBeg2=convert(datetime,convert(varchar(10),datepart(yy,@InTime))+'-'+convert(varchar(2),@GZ_MonthBeg2)+'-'+convert(varchar(2),@GZ_DayBeg2))
			set @GZDayEnd2=convert(datetime,convert(varchar(10),datepart(yy,@InTime))+'-'+convert(varchar(2),@GZ_MonthEnd2)+'-'+convert(varchar(2),@GZ_DayEnd2))
				if ((@FirstTimeN=0) or (@FirstTimeN=1 and @FirstTimeM=0))  --1个小时内               
					if (convert(datetime,convert(varchar(10),@InTime,126)) Between @GZDayBeg2 and @GZDayEnd2) --特殊工作日
					OR  ( datepart(dw,@InTime)<7 and datepart(dw,@InTime)>1 and ( convert(datetime,convert(varchar(10),@InTime,126))<@GZDayBeg1 or convert(datetime,convert(varchar(10),@InTime,126))>@GZDayEnd1 ) )
											begin
													--工作日
													if  (@InTime>=@GFDatetime and @InTime<@GFDatetime2)--判断是否高峰期
                                          					set @m1=@wd_FirstHour  --第一个时段的费用 高峰期
										else
															set @m1=@NotGF_DtimeFee-- 第一个时段的费用   非高峰期
											end
								else
											begin
												set @m1=@Nwd_FirstHour    --非工作日首小时费用
											end
					else
								begin
										--超过首段,先计算首段的费用(1个小时)
									if (convert(datetime,convert(varchar(10),@InTime,126)) Between @GZDayBeg2 and @GZDayEnd2) --特殊工作日
						OR ( datepart(dw,@InTime)<7 and datepart(dw,@InTime)>1 and ( convert(datetime,convert(varchar(10),@InTime,126))<@GZDayBeg1 or convert(datetime,convert(varchar(10),@InTime,126))>@GZDayEnd1 ) )
												begin
													--工作日
													if  (@InTime>=@GFDatetime and @InTime<@GFDatetime2)--判断是否高峰期
									set @m1=@wd_FirstHour  -- 高峰期
													else
									set @m1=@NotGF_DtimeFee-- 非高峰期
												end
										else
												set @m1=@Nwd_FirstHour	--非工作日第一小时的费用
			
		
										set @InTime2=dateadd(mi,60,@InTime)---首段1小时,如果修改首段,修改60即可                           
										select @m1,@InTime2
						while(datediff(mi,@InTime2,@OutTime)>0)  --循环时间(工作日分高峰期/非高峰期)
											begin
							--在这里添加为了避免跨年产生的错误
							set @GZDayBeg1=convert(datetime,convert(varchar(10),datepart(yy,@InTime2))+'-'+convert(varchar(2),@GZ_MonthBeg1)+'-'+convert(varchar(2),@GZ_DayBeg1))
							set @GZDayEnd1=convert(datetime,convert(varchar(10),datepart(yy,@InTime2))+'-'+convert(varchar(2),@GZ_MonthEnd1)+'-'+convert(varchar(2),@GZ_DayEnd1))

							set @GZDayBeg2=convert(datetime,convert(varchar(10),datepart(yy,@InTime2))+'-'+convert(varchar(2),@GZ_MonthBeg2)+'-'+convert(varchar(2),@GZ_DayBeg2))
							set @GZDayEnd2=convert(datetime,convert(varchar(10),datepart(yy,@InTime2))+'-'+convert(varchar(2),@GZ_MonthEnd2)+'-'+convert(varchar(2),@GZ_DayEnd2))

											if (convert(datetime,convert(varchar(10),@InTime2,126)) Between @GZDayBeg2 and @GZDayEnd2) --特殊工作日
								OR ( datepart(dw,@InTime2)<7 and datepart(dw,@InTime2)>1 and ( convert(datetime,convert(varchar(10),@InTime2,126))<@GZDayBeg1 or convert(datetime,convert(varchar(10),@InTime2,126))>@GZDayEnd1 ) )
								begin
										--工作日
									set @GFDatetime=convert(datetime,convert(varchar(10),@InTime2,126)+space(1)+convert(varchar(2),@GF_Beg_H)+':'+convert(varchar(2),@GF_Beg_M)+':'+convert(varchar(2),DATEPART(ss,@InTime2)))
									set @GFDatetime2=convert(datetime,convert(varchar(10),@InTime2,126)+space(1)+convert(varchar(2),@GF_End_H)+':'+convert(varchar(2),@GF_End_M)+':'+convert(varchar(2),DATEPART(ss,@InTime2)))
																	if  (@InTime2>=@GFDatetime and @InTime2<@GFDatetime2)--判断是否高峰期
																			begin
																					set @m1=@m1+@GF_DtimeFee
																					set @InTime2=dateadd(mi,@GF_Dtime,@InTime2)
																			end
																	else
																			begin
																					set @m1=@m1+@NotGF_DtimeFee
																					set @InTime2=dateadd(mi,@NotGF_Dtime,@InTime2)
																			end
								end
							else
								begin
																	--非工作日
																	set @m1=@m1+@NoWday_DtimeFee
											set @InTime2=dateadd(mi,@NoWday_Dtime,@InTime2)
								end
											end
								end
		end
	else--按天计算费用
		begin
			select @PIsDate,@n_2
				if (@n_2=0)
						set @d=1
				else if (@m_2>0)
						set @d=@n_2+1
				else
						set @d=@n_2
				set @m1=@d*@dayFee  --三类地区按天计算
		end
	if @@ERROR<>0 
					select @Amount =0
	else
		select @Amount =@m1

	select @Amount

	return

GO
 
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-更新某个控制器下载标记为0>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Door_UpdateContFlag') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Door_UpdateContFlag
GO
	CREATE PROCEDURE [dbo].[Sp_Door_UpdateContFlag]
	@contid int
	AS
	BEGIN
	declare @errorcode int
	update dbo.YKT_CardIssueDoor set [DownLoad]=STUFF([DownLoad],@contid,1,'0') where substring(DownLoad,@contid,1)='1'
	select @errorcode=@@ERROR
	return @errorcode
	End
GO



-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： 新超时收费存储过程
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Park_OverTimeCharge') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Park_OverTimeCharge
GO
	CREATE  PROCEDURE [dbo].[Sp_Park_OverTimeCharge]
		@CardType  tinyint,		--卡类
		@CardID varchar(20),	--卡ID号	2015/09/01 新加
		@InTime datetime,		--入场时间
		@OutTime datetime,		--本次缴费
		@PayCharge  money output, --已缴金额 2015/09/01 新加
		@Amount  money output 	  --本次收费金额
	 
	AS
	Declare @OverTimeMinute smallint	--超时分钟
	Declare @FreeInclude tinyint		--超时分钟是否包括
	Declare @OverTimeAmount money		--收费金额（最大6553.5）
	Declare @OverTimeUnit  smallint 	--收费单位（最大1440分钟）
	Declare @CsDate as datetime
	Declare @CsMinute int
	Declare @SfType tinyint			--收费类别
	Declare @AccAmount money		--已缴费应收金额
	Declare @AccMoney money		    --应收金额
    Declare @CarNo  varchar(10)
	select @Amount=0,@PayCharge=0
--	Select @InTime=Cast(Convert(varchar(17),@InTime,120)+'00' as smalldatetime),@OutTime=Cast(Convert(varchar(17),@OutTime,120)+'00' as smalldatetime)
	if (@OutTime<=@InTime) 	return
--	Select @PayCharge=SUM(PayCharge),@AccAmount=SUM(AccountCharge),@CsDate=MAX(PayChargeTime) from dbo.PKCenterCharge where CardID=@CardID and InTime=@InTime  
--	Select @PayCharge=Isnull(@PayCharge,0),@AccAmount=ISNULL(@AccAmount,0)
	Select  @AccAmount=SUM(AccountCharge),@CsDate=MAX(PayChargeTime) from dbo.Park_CentralCharge where CardID=@CardID and InTime=@InTime
	select @CarNo=CarNo from dbo.Park_CentralCharge where CardID=@CardID and InTime=@InTime
	Select @AccAmount=ISNULL(@AccAmount,0)	
	Select @PayCharge=@AccAmount
	if  Exists(Select 1 from Park_OverTimeSet where CardType=@CardType)  
		Begin
			Select @OverTimeMinute=OverTimeMinute,@FreeInclude=FreeInclude,@OverTimeAmount=OverTimeAmount,@OverTimeUnit=OverTimeUnit from 	Park_OverTimeSet where CardType=@CardType
			Select @OverTimeMinute=Isnull(@OverTimeMinute,0),@FreeInclude=Isnull(@FreeInclude,0),@OverTimeAmount=Isnull(@OverTimeAmount,0),@OverTimeUnit=Isnull(@OverTimeUnit,0)
			if @OverTimeUnit<=0 Select @OverTimeUnit=30
			if @AccAmount>0 and @OverTimeAmount>0 --按超时收费标准计算
				Begin
					Select @CsMinute=DateDiff(n,@CsDate,@OutTime)
					if  @CsMinute<=@OverTimeMinute return
					if @FreeInclude=0
						Select @Amount = ((@CsMinute - 1) / @OverTimeUnit + 1) * @OverTimeAmount
					else
						Select @Amount = ((@CsMinute - 1 - @OverTimeMinute) / @OverTimeUnit + 1) * @OverTimeAmount		
					Return			
				end
		end
	Select @SfType=ParameterValue from Sys_Parameters where ParameterID=101  --0标准收费1通用标准I类2通用标准II类3深圳收费社会类
	Select @SfType=ISNULL(@SfType,0)

	
	if ISDATE(@CsDate)=1
		Begin
			Select @CsMinute=DateDiff(n,@CsDate,@OutTime)
			if  @CsMinute<=@OverTimeMinute return
		end
		
	if @SfType=0
		exec dbo.Sp_Park_NoStandards '',@CardType,@Intime,@Outtime,@SfType,@AccMoney out
	if @SfType=1
		exec dbo.Sp_Park_ChargeA @CarNo,@CardType,@Intime,@Outtime,@SfType,@AccMoney out
	if @SfType=2
		exec dbo.Sp_Park_ChargeB '',@CardType,@Intime,@Outtime,@SfType,@AccMoney out
	if @SfType=3
		exec dbo.Sp_Park_SZCommerce '',@CardType,@Intime,@Outtime,@SfType,@AccMoney out
	if @SfType=4
		exec dbo.Sp_Park_ChargeC '',@CardType,@Intime,@Outtime,@SfType,@AccMoney out
		
	Select @Amount=	@AccMoney-@AccAmount		


	if (@@ERROR<>0) or @Amount<0  select @Amount=0
	return


GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-获取控制器的所有门的最新记录信息>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.sp_door_GetNewRecord') IS NOT NULL
	DROP PROCEDURE dbo.sp_door_GetNewRecord
GO
	CREATE PROCEDURE sp_door_GetNewRecord
		-- Add the parameters for the stored procedure here
		@contid int
	AS
	BEGIN
		SELECT     a.ID, a.PID, a.ContID, a.DoorID, a.CardType, a.CardID, a.EventTime, a.EventWay, a.EventType, a.PicPath, a.PicName, b.PerName, b.PerSex, b.PerID, b.DeptID, 
						  b.DeptName, b.Positions, b.PhoPath
	FROM         (SELECT     ID, PID, ContID, DoorID, CardType, CardID, EventTime, EventWay, EventType, PerID, PerName, PicPath, PicName
						   FROM          dbo.Door_OpenDoorRecordTwo
						   WHERE  contid=@contid and     (ID IN
													  (SELECT     MAX(ID) AS Expr1
														FROM          dbo.Door_OpenDoorRecordTwo AS Door_OpenDoorRecordTwo_1
														GROUP BY DoorID))) AS a LEFT OUTER JOIN
						  dbo.vw_per_PerList AS b ON a.PID = b.Pid
	END
GO
 
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <千万数量级分页存储过程>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
If object_id('SP_Pagination')is not null
drop proc SP_Pagination
go
Create  PROCEDURE SP_Pagination
/**//*
***************************************************************
** 千万数量级分页存储过程 **
***************************************************************
参数说明:    www.2cto.com  
.Tables :表名称,视图(试图这边目前还有点小问题)
.PrimaryKey :主关键字
.Sort :排序语句，不带Order By 比如：NewsID Desc,OrderRows Asc
.CurrentPage :当前页码
.PageSize :分页尺寸
.Filter :过滤语句，不带Where 
.Group :Group语句,不带Group By
***************************************************************/
(
	@Tables varchar(2000),
	@PrimaryKey varchar(500),
	@Sort varchar(500) = NULL,
	@CurrentPage int = 1,
	@PageSize int = 5,
	@Fields varchar(2000) = '*',
	@Filter varchar(1000) = NULL,
	@Group varchar(1000) = NULL
	)
	AS
	BEGIN
		/**//*默认排序*/
		IF @Sort IS NULL OR @Sort = ''
			SET @Sort = @PrimaryKey
		--新增判断，避免NULL值异常进入 2015-04-23_tanf
		IF @Fields IS NULL OR @Fields = ''
			SET @Fields = '*'
		DECLARE @SortTable varchar(1000)
		DECLARE @SortName varchar(1000)
		DECLARE @strSortColumn varchar(1000)
		DECLARE @operator char(2)
		DECLARE @type varchar(1000)
		DECLARE @prec int
		/**//*设定排序语句.*/
		IF CHARINDEX('DESC',@Sort)>0
		BEGIN
		SET @strSortColumn = REPLACE(@Sort, 'DESC', '')
		SET @operator = '<='
		END
		ELSE
		BEGIN
		IF CHARINDEX('ASC', @Sort) = 0
		print '1'
		print REPLACE(@Sort, 'ASC', '')
		SET @strSortColumn = REPLACE(@Sort, 'ASC', '')
		print @strSortColumn
		SET @operator = '>='
		print @operator
		END
		IF CHARINDEX('.', @strSortColumn) > 0
		BEGIN
		SET @SortTable = SUBSTRING(@strSortColumn, 0, CHARINDEX('.',@strSortColumn))
		SET @SortName = SUBSTRING(@strSortColumn, CHARINDEX('.',@strSortColumn) + 1, LEN(@strSortColumn))
		END
		ELSE
		BEGIN
		SET @SortTable = @Tables
		SET @SortName = @strSortColumn
		print @SortTable
		print @SortName
		END
		SELECT @type=t.name, @prec=c.prec
		FROM sysobjects o 
		JOIN syscolumns c on o.id=c.id
		JOIN systypes t on c.xusertype=t.xusertype
		WHERE o.name = @SortTable AND c.name = @SortName
		--print @type
		--print @prec
		IF CHARINDEX('char', @type) > 0
		SET @type = @type + '(' + CAST(@prec AS varchar) + ')'
		DECLARE @strPageSize varchar(500)
		DECLARE @strStartRow varchar(500)
		DECLARE @strFilter varchar(1000)
		DECLARE @strSimpleFilter varchar(1000)
		DECLARE @strGroup varchar(1000)
		/**//*默认当前页*/
		IF @CurrentPage < 1
		SET @CurrentPage = 1
		/**//*设置分页参数.*/
		SET @strPageSize = CAST(@PageSize AS varchar(500))
		SET @strStartRow = CAST(((@CurrentPage - 1)*@PageSize + 1) AS varchar(500))
		/**//*筛选以及分组语句.*/
		IF @Filter IS NOT NULL AND @Filter != ''
		BEGIN
		SET @strFilter = ' WHERE ' + @Filter + ' '
		SET @strSimpleFilter = ' AND ' + @Filter + ' '
		END
		ELSE
		BEGIN
		SET @strSimpleFilter = ''
		SET @strFilter = ''
		END
		IF @Group IS NOT NULL AND @Group != ''
		SET @strGroup = ' GROUP BY ' + @Group + ' '
		ELSE
		SET @strGroup = ''
		/*print @type
		print @strStartRow
		print @strSortColumn
		print @Tables
		print @strFilter
		print @strGroup
		print @Sort*/
		/**//*执行查询语句*/
		EXEC(
		'
		DECLARE @SortColumn ' + @type + '
		SET ROWCOUNT ' + @strStartRow + '
		SELECT @SortColumn=' + @strSortColumn + ' FROM ' + @Tables + @strFilter + ' ' + @strGroup + ' ORDER BY ' + @Sort + '
		SET ROWCOUNT ' + @strPageSize + '
		SELECT ' + @Fields + ' FROM ' + @Tables + ' WHERE ' + @strSortColumn + @operator + ' @SortColumn ' + @strSimpleFilter + ' ' + @strGroup + ' ORDER BY ' + @Sort + '
		'
		)
	END	
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-获取需要下载的用户列表>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('Sp_Door_ContUsers') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Door_ContUsers
GO
	CREATE PROCEDURE [dbo].[Sp_Door_ContUsers]
	@contid int,		--控制器ID
	@seltype int = 0	--控制器下载类型：0-单条下载；1-批量下载(ps:批量下载去重复记录，相同卡号保留正常卡)
	AS
	BEGIN
        if(@seltype=0)
        begin		
		    select a.*,b.CardID,b.States
		    from YKT_CardIssueDoor as a 
			     INNER JOIN YKT_CardIssue as b ON a.YKTID=b.YktID
			     INNER JOIN Per_Persons AS c ON b.Pid=c.PID
		    where (GradeID=0 or GradeID in (select distinct GradeID from Door_PowerGrade where IsDel=0 and substring(RightList,@contid,1)='1'))
			      and substring(DownLoad,@contid,1)='0' 
		    order by YKTID
        end
        else
        begin
			update Ykt_CardIssueDoor set DownLoad=stuff(DownLoad , @contid , 1 ,'1')
				where (GradeID=0 or GradeID in (select distinct GradeID from Door_PowerGrade where IsDel=0 and substring(RightList,@contid,1)='1'))
				and substring(DownLoad,@contid,1)='0' 
				and YKTID IN 
				(
					select a.YKTID
					from YKT_CardIssueDoor as a 
					 INNER JOIN YKT_CardIssue as b ON a.YKTID=b.YktID
					 WHERE b.States = 6
					 UNION ALL
					 SELECT s.YKTID from 
					(
						select TOP 150000 a.YKTID,row_number() over (partition by b.CardID order by b.States) as group_idx
						from YKT_CardIssueDoor as a 
							 INNER JOIN YKT_CardIssue as b ON a.YKTID=b.YktID 
							 INNER JOIN Per_Persons AS c ON b.Pid=c.PID
						where (GradeID in (select distinct GradeID from Door_PowerGrade where IsDel=0 and substring(RightList,@contid,1)='1'))
					    and substring(DownLoad,@contid,1)='0' 
						order by a.YKTID
					) s WHERE s.group_idx = 2
				)
		    SELECT * from (select TOP 150000 a.*,b.CardID,b.States,row_number() over (partition by b.CardID order by b.States) as group_idx
				from YKT_CardIssueDoor as a 
					 INNER JOIN YKT_CardIssue as b ON a.YKTID=b.YktID
					 INNER JOIN Per_Persons AS c ON b.Pid=c.PID
				where (GradeID in (select distinct GradeID from Door_PowerGrade where IsDel=0 and substring(RightList,@contid,1)='1'))
					  and substring(DownLoad,@contid,1)='0' 
				order by a.YKTID) s WHERE s.group_idx = 1
        end
     End
Go

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <插入程序版本信息>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Sys_VersionIns') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Sys_VersionIns
GO
	/*
	 Create Person:szw
	 Create Date:2015-03-19
	 notes:插入版本信息
	*/
	CREATE PROC Sp_Sys_VersionIns (
		@VSoft VARCHAR(20),
		@VType INT,
		@PublishDate datetime,
		@VProgram VARCHAR(20)=NULL,
		@VDB VARCHAR(20)=NULL,	
		@Comment VARCHAR(100)=NULL
	)
	AS
	BEGIN
       IF NOT EXISTS(SELECT 1 FROM Sys_Version WHERE VSoft=@VSoft)
	   INSERT INTO Sys_Version(VSoft,VProgram,VDB,VType,PublishDate,CreateDate,UpdateDate,Comment)
	   VALUES(@VSoft,@VProgram,@VDB,@VType,@PublishDate,getdate(),getdate(),@Comment)
       ELSE
       Update Sys_Version SET UpdateDate=getdate() WHERE VSoft=@VSoft
	END
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-获取需要下载的信息数据集>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_LoadContParam') IS NOT NULL
	DROP PROCEDURE dbo.Sp_LoadContParam
GO
CREATE PROCEDURE [dbo].[Sp_LoadContParam]
		@ContID int
	AS
	BEGIN
		declare @download varchar(1000)
		DECLARE @hasCount int
		DECLARE @ContType int
		DECLARE @DoorContent1 varchar(32)
		DECLARE @ContIP varchar(50)
		create table #tab
		(
			ContID int,
			ContIP varchar(50),
			ParamType varchar(50),
			ParamState varchar(50),
			Content varchar(50)
		)
		--主办参数，设防模式，报警参数
		select @download = DownLoad,@ContType = ContType,@DoorContent1 = DoorContent1,@ContIP = ContIP from Door_Cont where ContID = @ContID
		if substring(@download,1,1) <> '1'
			insert INTO #tab VALUES(@ContID,@ContIP,'主板参数','正在下载..','')
		if substring(@download,2,1) <> '1'
			insert INTO #tab VALUES(@ContID,@ContIP,'报警设置','正在下载..','')
		if substring(@download,3,1) <> '1'
			insert INTO #tab VALUES(@ContID,@ContIP,'设防模式','正在下载..','')
		--日常计划
		SELECT @hasCount = COUNT(PlanNo) FROM Door_UsualPlan where substring(DownLoad,@ContID,1) = '0'
		IF @hasCount > 0
			insert INTO #tab VALUES(@ContID,@ContIP,'日常计划','正在下载..','')
		--特殊任务中的工作日
		SELECT @hasCount = COUNT(PartNo) FROM Door_PartPlan where PartType = 1 AND substring(DownLoad,@ContID,1) = '0'
		IF @hasCount > 0
			insert INTO #tab VALUES(@ContID,@ContIP,'工作日特殊计划','正在下载..','')
		--特殊任务中的假日
		SELECT @hasCount = COUNT(PartNo) FROM Door_PartPlan where PartType = 2 AND substring(DownLoad,@ContID,1) = '0'
		IF @hasCount > 0
			insert INTO #tab VALUES(@ContID,@ContIP,'假日特殊计划','正在下载..','')
		--嵌入式和一体式不需要：首卡开门、多卡开门、密码开门、定时任务、反潜和互锁、报警设置
		IF @ContType = 0
		begin
			--加载首卡开门
			SELECT @hasCount = COUNT(ID) FROM Door_Door where ContID = @ContID AND substring(DownLoad,1,1) = '0'
			IF @hasCount > 0
				insert INTO #tab VALUES(@ContID,@ContIP,'首卡开门','正在下载..','')
			--加载多卡开门
			SELECT @hasCount = COUNT(ID) FROM Door_Door where ContID = @ContID AND substring(DownLoad,2,1) = '0'
			IF @hasCount > 0
				insert INTO #tab VALUES(@ContID,@ContIP,'多卡开门','正在下载..','')
			--加载密码开门
			SELECT @hasCount = COUNT(ID) FROM Door_Door where ContID = @ContID AND substring(DownLoad,3,1) = '0'
			IF @hasCount > 0
				insert INTO #tab VALUES(@ContID,@ContIP,'密码开门','正在下载..','')
			--加载反潜和互锁
			--Can转换器的设备不支持该下载
			IF @DoorContent1 = '' or @DoorContent1 is null or substring(@DoorContent1,1,1) <> '1'
			begin
				if substring(@download,4,1) <> '1'
					insert INTO #tab VALUES(@ContID,@ContIP,'反潜和互锁','正在下载..','')
			end
			--加载定时任务
			SELECT @hasCount = COUNT(ID) FROM Door_TimingTask where ContID like '%,'+ CAST(@ContID AS varchar(50)) + ',%' AND substring(DownLoad,@ContID,1) = '0'
			IF @hasCount > 0
				insert INTO #tab VALUES(@ContID,@ContIP,'定时任务','正在下载..','')
		end
		SELECT ContID,ContIP,ParamType,ParamState,Content from #tab
		drop table #tab
	END
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-同步第三方接口数据>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Sys_TimmingSynData') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Sys_TimmingSynData
GO
	CREATE PROCEDURE Sp_Sys_TimmingSynData(
		@Types varchar(10)=''
	   ,@DBName varchar(50)=''
       )
	AS
	BEGIN
	declare @Insert varchar(1000), @select varchar(1000),@where varchar(200),@DataUpdateDate datetime,@strDate varchar(23)    
	IF(ISNULL(@Types,'')='8')
	  begin
       SET @where=' WHERE emp_card_sn IS NOT NULL '
	   SET @DataUpdateDate=(SELECT MAX(DataUpdateDate) FROM Syn_CardIssue)
	   IF(@DataUpdateDate IS NOT NULL)
	   BEGIN
		   SET @strDate=CONVERT(VARCHAR(10),@DataUpdateDate,120)+' '+CONVERT(VARCHAR(12),@DataUpdateDate,114)
		   SET @where=@where+' AND update_date>'''+@strDate+''''
	   END
	   SET @Insert=' INSERT INTO Syn_CardIssue(CardID,CardIDOld,CardNO,CFlag,StatesName,PerID,PerName,DeptName,DataUpdateDate)'
	   --SET @select=' SELECT TOP 1000 dbo.Inttohex(LTRIM(RTRIM(emp_card_sn))),LTRIM(RTRIM(emp_card_sn)),LEFT(LTRIM(RTRIM(emp_ic)),10),0,LTRIM(RTRIM(card_state)),LTRIM(RTRIM(emp_id)),LTRIM(RTRIM(emp_name)),LEFT(LTRIM(RTRIM(cc_desc)),10),update_date FROM ['+@DBName+'].[dbo].vw_EmpInfo '
	   SET @select=' SELECT TOP 1000 LTRIM(RTRIM(emp_card_sn)),LTRIM(RTRIM(emp_card_sn)),LEFT(LTRIM(RTRIM(emp_ic)),10),0,LTRIM(RTRIM(card_state)),LTRIM(RTRIM(emp_id)),LTRIM(RTRIM(emp_name)),LEFT(LTRIM(RTRIM(cc_desc)),10),update_date FROM ['+@DBName+'].[dbo].vw_EmpInfo '
	   EXEC(@Insert+@select+@where)
	  end
	END
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-导入第三方接口数据>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Sys_TimmingInData') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Sys_TimmingInData
GO
	CREATE PROCEDURE Sp_Sys_TimmingInData
	AS
		BEGIN
	   declare @Insert varchar(1000), @select varchar(1000),@where varchar(200),@CDate datetime,@strDate varchar(23),@newid int  
	   --1
       SET @where=''
	   SET @CDate=(SELECT MAX(UserDate) FROM ykt_CardIssue WHERE Remark='TimmingIn')
	   IF(@CDate IS NOT NULL)
	   BEGIN
		   SET @strDate=CONVERT(VARCHAR(10),@CDate,120)+' '+CONVERT(VARCHAR(12),@CDate,114)
		   SET @where=' WHERE CreateDate>'''+@strDate+''''
	   END
	   --2
	   create table #tbSyn_CardIssue (CardID varchar(50),CardNO varchar(10),CFlag tinyint,States tinyint,PerID varchar(15),PerName nvarchar(20),DeptName nvarchar(20),CreateDate DATETIME)
	   SET @Insert=' INSERT INTO #tbSyn_CardIssue' 
	   SET @SELECT=' SELECT CardID,CardNO,CFlag,case StatesName when ''正常'' then 0 else 6 end AS States,PerID,PerName,DeptName,CreateDate FROM Syn_CardIssue '	   
	   EXEC(@Insert+@select+@where+' ORDER BY DataUpdateDate')
	   --3
	   begin try 
	   begin transaction 
	       DECLARE C_tbSyn_CardIssue CURSOR 
	       read_only
	       FOR (SELECT CardID,CardNO,CFlag,States,PerID,PerName,DeptName,CreateDate FROM #tbSyn_CardIssue)
	       declare @CardID varchar(50),@CardNO varchar(10),@CFlag tinyint,@States tinyint,@PerID varchar(15),@PerName varchar(20),@DeptName varchar(20),@CreateDate DATETIME
	       OPEN C_tbSyn_CardIssue
	       FETCH NEXT FROM C_tbSyn_CardIssue 
	       INTO @CardID,@CardNO,@CFlag,@States,@PerID,@PerName,@DeptName,@CreateDate
	       WHILE @@FETCH_STATUS = 0
	       BEGIN
	            --dept
	            PRINT @CardID + '-' + @CardNO + '-' + @PerID + '-' + @PerName
	            DECLARE @deptid nvarchar(10),@Pid int
	            SET @deptid=(SELECT TOP 1 DeptID FROM Per_Dept WHERE DeptName=@DeptName)
				IF @deptid IS NULL
				 BEGIN 
				     SET @deptid=cast((select ISNULL(MAX(autoid),0)+1 from Per_Dept) as nvarchar(10))
				     INSERT INTO Per_Dept(DeptID,DeptName,TopDeptID)VALUES(@deptid,@DeptName,0)
				END
				--person
				SET @Pid=(SELECT top 1 pid FROM Per_Persons WHERE PerID=@PerID)
				IF @Pid IS NULL
				BEGIN 
				     SET @Pid=(select ISNULL(MAX(ID),0)+1 from Per_Persons)
				     INSERT INTO Per_Persons(Pid,PerID,PerName,PerType,DeptID,Isleave,[status],CrUserDate,CrUserName)VALUES(@Pid,@PerID,@PerName,1,@deptid,0,1,@CreateDate,'系统管理员')	
				END
				else
					update Per_Persons SET PerName = @PerName,DeptID = @deptid,CrUserDate = @CreateDate where PerID = @PerID
	             --Card
	            SET @newid=(SELECT TOP 1 [ID] FROM YKT_CardIssue WHERE CardID=@CardID AND States <> 6 ORDER BY UserDate DESC)
	            IF @newid IS NULL
	            begin
					INSERT INTO YKT_CardIssue(YktID,CardID,CFlag,CardNO,PID,PerName,States,SysRight,Remark,UserDate,UserName)
					       VALUES (0,@CardID,@CFlag,@CardNO,@Pid,@PerName,@States,'01000','TimmingIn',@CreateDate,'系统管理员')
					SET @newid=@@IDENTITY			
				    UPDATE YKT_CardIssue SET YktID=ID where ID=@newid	
				    IF NOT EXISTS(SELECT TOP 1 1 FROM Ykt_CardIssueDoor WHERE YKTID=@newid)
				    INSERT INTO Ykt_CardIssueDoor(YKTID,CardType,PlanID1,PlanID2,PlanID3,PlanID4,StartDate,EndDate,CardPwd,GradeID,UserDate,UserName,DownLoad)
				           VALUES (@newid,60,1,0,0,0,CAST(convert(varchar(10),getdate(),120) as datetime),DATEADD(year,5,CAST(convert(varchar(10),getdate(),120) as datetime)),'666666',CASE @States WHEN 6 THEN 0 ELSE 1 END,@CreateDate,'系统管理员',[dbo].[PadLeft]('0','0',1000))
				end
				ELSE
				begin
				     IF not exists(SELECT TOP 1 [ID] FROM YKT_CardIssue WHERE CardID=@CardID AND PID = @Pid AND States <> 6 ORDER BY UserDate DESC)
					 BEGIN
						 UPDATE YKT_CardIssue SET States = 6 where YktID in (SELECT [ID] FROM YKT_CardIssue WHERE CardID = @CardID AND States <> 6)
						 update Ykt_CardIssueDoor SET GradeID = 0 where YKTID in(SELECT [ID] FROM YKT_CardIssue WHERE CardID = @CardID AND States <> 6)
						 INSERT INTO YKT_CardIssue(YktID,CardID,CFlag,CardNO,PID,PerName,States,SysRight,Remark,UserDate,UserName)
							   VALUES (0,@CardID,@CFlag,@CardNO,@Pid,@PerName,@States,'01000','TimmingIn',@CreateDate,'系统管理员')
						SET @newid=@@IDENTITY
						UPDATE YKT_CardIssue SET YktID=ID where ID=@newid	
						IF NOT EXISTS(SELECT TOP 1 1 FROM Ykt_CardIssueDoor WHERE YKTID=@newid)
						INSERT INTO Ykt_CardIssueDoor(YKTID,CardType,PlanID1,PlanID2,PlanID3,PlanID4,StartDate,EndDate,CardPwd,GradeID,UserDate,UserName,DownLoad)
							   VALUES (@newid,60,1,0,0,0,CAST(convert(varchar(10),getdate(),120) as datetime),DATEADD(year,5,CAST(convert(varchar(10),getdate(),120) as datetime)),'666666',CASE @States WHEN 6 THEN 0 ELSE 1 END,@CreateDate,'系统管理员',[dbo].[PadLeft]('0','0',1000))
				    end
				    else
				    begin
						UPDATE YKT_CardIssue SET CFlag=@CFlag,CardNO=CardNO,PID=@Pid,PerName=@PerName,States=@States,Remark='TimmingIn',UserDate=@CreateDate,UserName='系统管理员'
					        where YKTID=@newid
						 IF NOT EXISTS(SELECT TOP 1 YKTID FROM Ykt_CardIssueDoor WHERE YKTID=@newid)
							   INSERT INTO Ykt_CardIssueDoor(YKTID,CardType,PlanID1,PlanID2,PlanID3,PlanID4,StartDate,EndDate,CardPwd,GradeID,UserDate,UserName,DownLoad)
							   VALUES (@newid,60,1,0,0,0,CAST(convert(varchar(10),getdate(),120) as datetime),DATEADD(year,5,CAST(convert(varchar(10),getdate(),120) as datetime)),'666666',CASE @States WHEN 6 THEN 0 ELSE 1 END,@CreateDate,'系统管理员',[dbo].[PadLeft]('0','0',1000))
						 ELSE 
							UPDATE Ykt_CardIssueDoor SET UserDate=@CreateDate,GradeID=CASE @States WHEN 6 THEN 0 ELSE 1 END,UserName='系统管理员',DownLoad=[dbo].[PadLeft]('0','0',1000) WHERE YKTID=@newid 
				    end
				 end
				 
				 FETCH NEXT FROM C_tbSyn_CardIssue 
	             INTO @CardID,@CardNO,@CFlag,@States,@PerID,@PerName,@DeptName,@CreateDate
		    END	 
		    CLOSE C_tbSyn_CardIssue
		    DEALLOCATE C_tbSyn_CardIssue

			DROP TABLE #tbSyn_CardIssue	
			commit transaction	
	   end try
	   begin catch
			--select ERROR_NUMBER() as errornumber
			rollback transaction 
	   end catch
	END
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <门禁-深圳收费标准商业类>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_ChargeSZCommerce') IS NOT NULL
	DROP PROCEDURE dbo.Sp_ChargeSZCommerce
GO
Create PROCEDURE  Sp_ChargeSZCommerce
@CarNo nvarchar(10)='8888888888', --车牌号码
@CardType integer,			--卡类
@InTime datetime,		--入场时间
@OutTime datetime,		--出场时间
@ParkType as integer=0,		--车场类型 0 大车场 1 小车场 ,
@Amount  as money output	--收费金额

AS
SET DATEFIRST 7
if (@OutTime<=@InTime)
	begin
	     set @Amount =0
	     return
	end

declare @FreeMinute as integer
declare @break_datetime as varchar(128)

declare @m as integer--高峰期
declare @n as integer--高峰期
declare @m_1 as integer--非高峰期
declare @n_1 as integer--非高峰期
declare @m_2 as integer--(天余数)
declare @n_2 as integer--(天数)
declare @d as integer--(天数)

declare @wd_FirstHour as float	--工作日第一小时费用
declare @Nwd_FirstHour as float --非工作日第一小时费用
declare @dayFee as float 	--每天费用

declare @NoWday_Dtime as int 		--非工作日收费时段(分钟为单位)
declare @NoWday_DtimeFee as money 	--非工作日时段收费额
declare @NotGF_Dtime as int 		--非高峰期收费时段(分钟为单位)
declare @NotGF_DtimeFee as money	--非高峰期时段收费额
declare @GF_Dtime as int 		--高峰期收费时段(分钟为单位)
declare @GF_DtimeFee as money 		--高峰期时段收费额
declare @GF_Beg_H as int 		--高峰期起始小时
declare @GF_Beg_M as int 		--高峰期起始分钟
declare @GF_End_H as int 		--高峰期截至小时
declare @GF_End_M as int 		--高峰期截至分钟

declare @GZ_MonthBeg1 as int 	--特殊非工作日起始月
declare @GZ_DayBeg1 as int 	--特殊非工作日起始日
declare @GZ_MonthEnd1 as int 	--特殊非工作日结束月
declare @GZ_DayEnd1 as int 	--特殊非工作日结束日

declare @GZ_MonthBeg2 as int 	--特殊工作日起始月
declare @GZ_DayBeg2 as int 	--特殊工作日起始日
declare @GZ_MonthEnd2 as int	--特殊工作日结束月
declare @GZ_DayEnd2 as int 	--特殊工作日结束日

declare @GFDatetime as datetime
declare @GFDatetime2 as datetime
declare @FirstTimeBetween as int	--首段收费时间(分钟)
declare @FirstTimeN as int 		--判断时间间隔是否超过首段
declare @FirstTimeM as int  		--判断时间间隔是否超过首段
declare @IncludeBool as tinyint 	--是否包含免费时间 
Declare @PIsDate as tinyint 
declare @InTime2 as datetime 	--入场时间
declare @InTime3 as datetime

declare @m1 as money		--本次出场单独计费金额 @m1
declare @m2 as money		--本次出场实收金额 @m2
declare @c2 as money		--已收金额 @c2	

--select @CardType =31,@InTime='2009-09-21 20:00:00',@OutTime='2009-09-22 09:00:00',@ParkType=0
Select @InTime=Cast(Convert(varchar(17),@InTime,120)+'00' as smalldatetime),@OutTime=Cast(Convert(varchar(17),@OutTime,120)+'00' as smalldatetime)
select @Amount =0
if @CardType<30 or (@CardType>39 and @CardType<50) or  @CardType>59  --卡类只有临时卡和储值卡
	return

select @FreeMinute=FreeMinute,@IncludeBool=FreeInclude from Park_ESMain where ESNO=3
select top 1 @GZ_MonthBeg1=month([NoWorkDayBegin]),@GZ_DayBeg1=day([NoWorkDayBegin]),@GZ_MonthEnd1=month([NoWorkDayend]),@GZ_DayEnd1=day([NoWorkDayend]),
		@GZ_MonthBeg2=month([NoWorkDayBegin]),@GZ_DayBeg2=day([NoWorkDayBegin]),@GZ_MonthEnd2=month([NoWorkDayend]),@GZ_DayEnd2=day([NoWorkDayend]),
		@FirstTimeBetween=60  from [Park_Es4_F] 
		where Cardtype=@CardType
--if (@IncludeBool<>1) set @FreeMinute=-99999
if datediff(minute,@InTime,@OutTime)<=@FreeMinute --免费时间(以分钟为单位)
    begin
	select @Amount =0
	return
    end
if @IncludeBool=1	--不包含免费时间
	begin
		select @InTime=dateadd(minute,@FreeMinute,@InTime)
	end

select @n_2=datediff(minute,@InTime,@OutTime)/1440--天
select @m_2=datediff(minute,@InTime,@OutTime) % 1440

select @wd_FirstHour=WorkDayFirstHourAmount,@NoWday_Dtime=60, @NoWday_DtimeFee=[NoWorkChgAmount],@Nwd_FirstHour=[NoWorkDayFirstHourAmount],@NotGF_Dtime=60,
	 @NotGF_DtimeFee=NotPeakChgAmount,@GF_Dtime=30,@GF_DtimeFee=PeakChgAmount,@dayFee=[NoWorkChgAmount],@PIsDate=isnull([IsDate],0),@GF_Beg_H=cast(left(PeakFieldBegin,2) as integer), @GF_Beg_M=cast(right(PeakFieldBegin,2) as integer),
	 @GF_End_H=cast(left(PeakFieldEnd,2) as integer),@GF_End_M=cast(right(PeakFieldEnd,2) as integer) from [Park_Es4] a,[Park_Es4_F] b where a.CardType = b.CardType and a.Cardtype=@CardType --计费单位30分钟，可修改
	
set @n=(select case @GF_Dtime when 0 then 0 else (datediff(minute,@InTime,@OutTime)/@GF_Dtime) end)		--高峰时段
set @m=(select case @GF_Dtime when 0 then 0 else (datediff(minute,@InTime,@OutTime) % @GF_Dtime) end)

set @n_1=(select case @NotGF_Dtime when 0 then 0 else (datediff(minute,@InTime,@OutTime)/@NotGF_Dtime) end)	--非高峰时段
set @m_1=(select case @NotGF_Dtime when 0 then 0 else (datediff(minute,@InTime,@OutTime) % @NotGF_Dtime) end)

set @FirstTimeN=datediff(minute,@InTime,@OutTime) / 60 --首段1小时,如果修改首段,修改60即可
set @FirstTimeM=datediff(minute,@InTime,@OutTime) % 60

set @GFDatetime=convert(datetime,convert(varchar(10),@InTime,126)+space(1)+convert(varchar(2),@GF_Beg_H)+':'+convert(varchar(2),@GF_Beg_M)+':'+convert(varchar(2),DATEPART(ss,@InTime)))
set @GFDatetime2=convert(datetime,convert(varchar(10),@InTime,126)+space(1)+convert(varchar(2),@GF_End_H)+':'+convert(varchar(2),@GF_End_M)+':'+convert(varchar(2),DATEPART(ss,@InTime)))
if  (@PIsDate<=0)
	begin  
	    	--本次出场单独计费金额 @m1
		declare @GZDayBeg1 as datetime
		declare @GZDayEnd1 as datetime
		declare @GZDayBeg2 as datetime
		declare @GZDayEnd2 as datetime

		set @GZDayBeg1=convert(datetime,convert(varchar(10),datepart(yy,@InTime))+'-'+convert(varchar(2),@GZ_MonthBeg1)+'-'+convert(varchar(2),@GZ_DayBeg1))
		set @GZDayEnd1=convert(datetime,convert(varchar(10),datepart(yy,@InTime))+'-'+convert(varchar(2),@GZ_MonthEnd1)+'-'+convert(varchar(2),@GZ_DayEnd1))

		set @GZDayBeg2=convert(datetime,convert(varchar(10),datepart(yy,@InTime))+'-'+convert(varchar(2),@GZ_MonthBeg2)+'-'+convert(varchar(2),@GZ_DayBeg2))
		set @GZDayEnd2=convert(datetime,convert(varchar(10),datepart(yy,@InTime))+'-'+convert(varchar(2),@GZ_MonthEnd2)+'-'+convert(varchar(2),@GZ_DayEnd2))
		 if ((@FirstTimeN=0) or (@FirstTimeN=1 and @FirstTimeM=0))  --1个小时内               
			 if (convert(datetime,convert(varchar(10),@InTime,126)) Between @GZDayBeg2 and @GZDayEnd2) --特殊工作日
			    OR  ( datepart(dw,@InTime)<7 and datepart(dw,@InTime)>1 and ( convert(datetime,convert(varchar(10),@InTime,126))<@GZDayBeg1 or convert(datetime,convert(varchar(10),@InTime,126))>@GZDayEnd1 ) )
                                     begin
                                                --工作日
                                              if  (@InTime>=@GFDatetime and @InTime<@GFDatetime2)--判断是否高峰期
                                                      	set @m1=@wd_FirstHour  --第一个时段的费用 高峰期
			                      else
                                                        set @m1=@NotGF_DtimeFee-- 第一个时段的费用   非高峰期
                                     end
                          else
                                     begin
                                          set @m1=@Nwd_FirstHour    --非工作日首小时费用
                                     end
	           else
	                     begin
	                             --超过首段,先计算首段的费用(1个小时)
	                            if (convert(datetime,convert(varchar(10),@InTime,126)) Between @GZDayBeg2 and @GZDayEnd2) --特殊工作日
					OR ( datepart(dw,@InTime)<7 and datepart(dw,@InTime)>1 and ( convert(datetime,convert(varchar(10),@InTime,126))<@GZDayBeg1 or convert(datetime,convert(varchar(10),@InTime,126))>@GZDayEnd1 ) )
	                                     begin
	                                           --工作日
	                                           if  (@InTime>=@GFDatetime and @InTime<@GFDatetime2)--判断是否高峰期
								set @m1=@wd_FirstHour  -- 高峰期
	                                           else
								set @m1=@NotGF_DtimeFee-- 非高峰期
	                                     end
	                             else
                                            set @m1=@Nwd_FirstHour	--非工作日第一小时的费用
					
				
	                              set @InTime2=dateadd(mi,60,@InTime)---首段1小时,如果修改首段,修改60即可                           
	                              select @m1,@InTime2
					while(datediff(mi,@InTime2,@OutTime)>0)  --循环时间(工作日分高峰期/非高峰期)
	                                   begin
						--在这里添加为了避免跨年产生的错误
						set @GZDayBeg1=convert(datetime,convert(varchar(10),datepart(yy,@InTime2))+'-'+convert(varchar(2),@GZ_MonthBeg1)+'-'+convert(varchar(2),@GZ_DayBeg1))
						set @GZDayEnd1=convert(datetime,convert(varchar(10),datepart(yy,@InTime2))+'-'+convert(varchar(2),@GZ_MonthEnd1)+'-'+convert(varchar(2),@GZ_DayEnd1))

						set @GZDayBeg2=convert(datetime,convert(varchar(10),datepart(yy,@InTime2))+'-'+convert(varchar(2),@GZ_MonthBeg2)+'-'+convert(varchar(2),@GZ_DayBeg2))
						set @GZDayEnd2=convert(datetime,convert(varchar(10),datepart(yy,@InTime2))+'-'+convert(varchar(2),@GZ_MonthEnd2)+'-'+convert(varchar(2),@GZ_DayEnd2))
	
			                            if (convert(datetime,convert(varchar(10),@InTime2,126)) Between @GZDayBeg2 and @GZDayEnd2) --特殊工作日
							OR ( datepart(dw,@InTime2)<7 and datepart(dw,@InTime2)>1 and ( convert(datetime,convert(varchar(10),@InTime2,126))<@GZDayBeg1 or convert(datetime,convert(varchar(10),@InTime2,126))>@GZDayEnd1 ) )
							begin
							        --工作日
								set @GFDatetime=convert(datetime,convert(varchar(10),@InTime2,126)+space(1)+convert(varchar(2),@GF_Beg_H)+':'+convert(varchar(2),@GF_Beg_M)+':'+convert(varchar(2),DATEPART(ss,@InTime2)))
								set @GFDatetime2=convert(datetime,convert(varchar(10),@InTime2,126)+space(1)+convert(varchar(2),@GF_End_H)+':'+convert(varchar(2),@GF_End_M)+':'+convert(varchar(2),DATEPART(ss,@InTime2)))
	                                                          if  (@InTime2>=@GFDatetime and @InTime2<@GFDatetime2)--判断是否高峰期
	                                                                   begin
	                                                                          set @m1=@m1+@GF_DtimeFee
	                                                                          set @InTime2=dateadd(mi,@GF_Dtime,@InTime2)
	                                                                   end
	                                                          else
	                                                                    begin
	                                                                          set @m1=@m1+@NotGF_DtimeFee
	                                                                          set @InTime2=dateadd(mi,@NotGF_Dtime,@InTime2)
	                                                                    end
							end
						else
							begin
                                                               --非工作日
                                       set @m1=@m1+@NoWday_DtimeFee
						               set @InTime2=dateadd(mi,@NoWday_Dtime,@InTime2)
							end
	                                    end
	                     end
	end
else--按天计算费用
	begin
		select @PIsDate,@n_2
	        if (@n_2=0)
	             set @d=1
	        else if (@m_2>0)
	             set @d=@n_2+1
	        else
	             set @d=@n_2
	             select @d,@dayFee
	        set @m1=@d*@dayFee  --三类地区按天计算
	end
if @@ERROR<>0 
             select @Amount =0
else
	select @Amount =@m1
select @Amount
return
GO


/*    
	在24小时之内,在某段时间内的分钟数 ******注意传递参数
*/
IF OBJECT_ID ('dbo.FunFee2') IS NOT NULL
	DROP Function dbo.FunFee2
GO
CREATE Function dbo.FunFee2(@InDate smallDatetime,@OutDate smallDatetime,@StartPeriod  Varchar(8),@EndPeriod  Varchar(8))
Returns int 
With ENCRYPTION 
as
Begin
   Declare  @mi1  int 
   Declare  @mi2  int 
   Declare  @mi3  int 
   Declare  @mi4  int 
   Declare @TmpSPeriod SmallDatetime
   Declare @TmpEPeriod SmallDatetime
   Declare @MidDate1 SmallDatetime
   Declare @MidDate2 SmallDatetime
   Declare @InOne  tinyint
   
	 /*传递参数错误！！24小时内 */
	if @OutDate=@InDate return 0
	if @EndPeriod=@StartPeriod return 0
	Select  @mi1=0,@mi2=0,@mi3=0,@mi4=0
	Select @MidDate1=Cast((Convert(varchar(10),@InDate,120) + ' 23:59:59') as smallDatetime)
	Select @MidDate2=Cast((Convert(varchar(10),@OutDate,120) + ' 00:00:00') as smallDatetime)

	if Convert(varchar(10),@OutDate,120)>Convert(varchar(10),@InDate,120)
			Select @InOne=1
	else
			Select @InOne=0	
	if @EndPeriod<@StartPeriod  --跨天
		Begin
			Select @TmpSPeriod=Cast((Convert(varchar(11),@InDate,120) + @StartPeriod) as smallDatetime)
			Select  @TmpEPeriod=Cast((Convert(varchar(11),Dateadd(dd,1,@InDate),120) + @EndPeriod) as smallDatetime)
			if @InOne=1
				Begin

					Select @mi1=dbo.funFee1(@InDate,@MidDate1,@TmpSPeriod,@MidDate1)
					Select @mi2=dbo.funFee1(@MidDate2,@OutDate,@MidDate2,@TmpEPeriod)

					Select @mi3=dbo.funFee1(@InDate,@MidDate1,
						Cast((Convert(varchar(10),@InDate,120) + ' 00:00:00') as smallDatetime),Cast((Convert(varchar(11),@InDate,120) +@EndPeriod) as smallDatetime))
					Select @mi4=dbo.funFee1(@MidDate2,@OutDate,
						Cast((Convert(varchar(11),@OutDate,120) + @StartPeriod) as smallDatetime),Cast((Convert(varchar(10),@OutDate,120) + ' 23:59:59') as smallDatetime))
				end
			else
				Begin
					Select @MidDate1=Cast((Convert(varchar(10),@InDate,120) + ' 23:59:59') as smallDatetime)
					Select @TmpSPeriod=Cast((Convert(varchar(11),@InDate,120) + @StartPeriod) as smallDatetime)

					Select @mi1=dbo.funFee1(@InDate,@OutDate,@TmpSPeriod,@MidDate1)  --第一时间段

					Select @MidDate2=Cast((Convert(varchar(10),@InDate,120) + ' 00:00:00') as smallDatetime)
					Select  @TmpEPeriod=Cast((Convert(varchar(11),@InDate,120) + @EndPeriod) as smallDatetime)

					Select @mi2=dbo.funFee1(@InDate,@OutDate,@MidDate2,@TmpEPeriod)  --第二时间段
				end
		end
	else
		Begin
			Select @TmpSPeriod=Cast((Convert(varchar(11),@InDate,120) + @StartPeriod) as smallDatetime)
			Select  @TmpEPeriod=Cast((Convert(varchar(11),@InDate,120) + @EndPeriod) as smallDatetime)
			if @InOne=1
				Begin
					Select @mi1=dbo.funFee1(@InDate,@MidDate1,@TmpSPeriod,@TmpEPeriod)

					Select @TmpSPeriod=Cast((Convert(varchar(11),@OutDate,120) + @StartPeriod) as smallDatetime)
					Select  @TmpEPeriod=Cast((Convert(varchar(11),@OutDate,120) + @EndPeriod) as smallDatetime)
					Select @mi2=dbo.funFee1(@MidDate2,@OutDate,@TmpSPeriod,@TmpEPeriod)

				end
			else
				Begin
					Select @mi1=dbo.funFee1(@InDate,@OutDate,@TmpSPeriod,@TmpEPeriod)
				end
		end
	Return @mi1+@mi2+@mi3+@mi4
End

GO


---20150922
IF OBJECT_ID ('dbo.FunFee1') IS NOT NULL
	DROP Function dbo.FunFee1
GO
CREATE Function dbo.FunFee1(@InDate smallDatetime,@OutDate smallDatetime,@StartPeriod smallDatetime,@EndPeriod smallDatetime)
Returns int 
With ENCRYPTION 
as
Begin
	  Declare @TmpSPeriod SmallDatetime
	  Declare @TmpEPeriod SmallDatetime
   
	if @OutDate<=@InDate return 0
	if @EndPeriod<=@StartPeriod return 0
	Select @TmpSPeriod=@StartPeriod , @TmpEPeriod=@EndPeriod
	Begin
	  return  Case 
		WHEN  @InDate<=@TmpSPeriod and @OutDate>=@TmpEPeriod  then  datediff(mi,@TmpSPeriod,@TmpEPeriod)
		WHEN  @TmpSPeriod<=@InDate and @TmpEPeriod>=@OutDate then  datediff(mi,@InDate,@OutDate)
		WHEN  @InDate<@TmpSPeriod and @OutDate<@TmpEPeriod and @OutDate>@TmpSPeriod then datediff(mi,@TmpSPeriod,@OutDate)
		WHEN   @InDate>@TmpSPeriod and @InDate<@TmpEPeriod and  @OutDate>@TmpEPeriod then datediff(mi,@InDate,@TmpEPeriod)
			Else  0
	             END	
	end

End
go

---获取在时间内的分钟数
IF OBJECT_ID ('dbo.SP_GetFunEffet') IS NOT NULL
	DROP PROCEDURE dbo.SP_GetFunEffet
GO
Create PROCEDURE dbo.SP_GetFunEffet
@InDate smallDatetime,
@OutDate smallDatetime,
@StartPeriod varchar(8),
@EndPeriod varchar(8),
@EffetMinute as int output 
as
Begin
    if (@OutDate<=@InDate)  return 0

    declare @ParkMinis as integer   	--总时间
    declare @ParkDates as integer   	--总天数
    Declare @MidMinute as int           --一天内间隔分钟数
    Declare @TMinute as int 		--总包含时间

    Declare @MidDate1 SmallDatetime
    Declare @MidDate2 SmallDatetime
    Declare @OneTwo as tinyint
   	
	if @StartPeriod>@EndPeriod  --跨天
		Begin
			Select @MidDate1=Cast(('2001-01-01 ' + Left(@StartPeriod,5)+':00') as smallDatetime)
			Select @MidDate2=Cast(('2001-01-02 ' + Left(@EndPeriod,5)+':00') as smallDatetime)
			Select @MidMinute=datediff(minute,@MidDate1,@MidDate2)
			Select @OneTwo=1
		end
	else
		Begin
			Select @MidDate1=Cast(('2001-01-01 ' + Left(@StartPeriod,5)+':00') as smallDatetime)
			Select @MidDate2=Cast(('2001-01-01 ' + Left(@EndPeriod,5)+':00') as smallDatetime)
			Select @OneTwo=0
		end
	Select @MidMinute=datediff(minute,@MidDate1,@MidDate2)

	select @ParkMinis=datediff(minute,@InDate,@OutDate)
	Select @ParkDates=@ParkMinis / 1440
	Select @EffetMinute=0
	if @ParkDates>0 
		Begin
			Select @TMinute=@ParkDates*@MidMinute
			select @InDate=dateadd(Day,@ParkDates,@InDate) --入场时间向后推移
		end
	
	Select	@EffetMinute=ISNULL(@TMinute,0)+dbo.FunFee2(@InDate,@OutDate,@StartPeriod,@EndPeriod)
	return @EffetMinute
End
GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场-交接班检测>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Park_LoginCheck') IS NOT NULL
	DROP PROCEDURE dbo.Park_LoginCheck
GO
CREATE PROCEDURE [dbo].[Park_LoginCheck] 
	(
		@username varchar(50),
		@BoxID varchar(50)
	)
	as
	begin
	 declare @logindate datetime,@tempdate varchar(20)
	 select top 1 @logindate=LoginDate from ParK_SumUser where Boxid=@BoxID and [ReUserName]  is  null order by LoginDate desc
	 select @tempdate = convert(varchar(20),GETDATE(),120)
	 if exists( select 1 from ParK_SumUser  where LoginDate=@logindate and UserName=@username and BoxID=@BoxID)
		 begin
			select top 1 * from ParK_SumUser where LoginDate=@logindate and UserName=@username and BoxID=@BoxID
		 end	
		 else
		 begin
		   if exists( select 1 from ParK_SumUser where LoginDate=@logindate and  BoxID=@BoxID)
				begin
					update ParK_SumUser set [ReUserName]=@username,ReliefDate=@tempdate where LoginDate=@logindate and BoxID=@BoxID
					insert into ParK_SumUser(UserName,BoxID,LoginDate,[HandGate]
      ,[TempCardIn]
      ,[TempCardOut]
      ,[TempFree]
      ,[FreeCharge]
      ,[DisSum]
      ,[DisCharge]
      ,[TotalCharge])values(@username,@BoxID,@tempdate,0,0,0,0,0,0,0,0)
					select * from ParK_SumUser where [ReUserName] is  null and UserName=@username and BoxID=@BoxID and LoginDate=@tempdate
				end
				else
				begin
					insert into ParK_SumUser(UserName,BoxID,LoginDate,[HandGate]
      ,[TempCardIn]
      ,[TempCardOut]
      ,[TempFree]
      ,[FreeCharge]
      ,[DisSum]
      ,[DisCharge]
      ,[TotalCharge])values(@username,@BoxID,@tempdate,0,0,0,0,0,0,0,0)
					select * from ParK_SumUser where [ReUserName] is  null and UserName=@username and BoxID=@BoxID and LoginDate=@tempdate
				end
		 end
	end
GO


-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场-检测是否存中央收费或超时收费 0无 1已缴费>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[Sp_ParK_CarCenterCheck]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[Sp_ParK_CarCenterCheck]
GO
CREATE   PROCEDURE dbo.Sp_ParK_CarCenterCheck
	@CardID Varchar(20),					--卡ID号
	@InTime Datetime,
	@JFTime DateTime out
	With ENCRYPTION
AS
	set nocount on
	Select @JFTime='2010-01-01'
	--已存在缴费记录
	if Exists(Select * from Park_CentralCharge where (CardID=@CardID and InTime=@InTime and PayType>0 and PayType <4 and OrderNum is Not null) or(CardID=@CardID and InTime=@InTime and PayType in(0,4,5,6)))
		begin
			Set @JFTime=(Select top 1 PayChargeTime from Park_CentralCharge where (CardID=@CardID and InTime=@InTime and PayType>0 and PayType <4 and OrderNum is Not null) or(CardID=@CardID and InTime=@InTime and PayType in(0,4,5,6)) Order By PayChargeTime desc)
			Select @JFTime=Isnull(@JFTime,'2010-01-01')
			Return 1	
		end
	else
		Return 0
	set nocount off
GO


-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场-取入场数据>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
if exists (select 1 from dbo.sysobjects where id = object_id(N'[dbo].[Sp_Park_GetInData]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
drop procedure [dbo].[Sp_Park_GetInData]
GO
CREATE     PROCEDURE dbo.Sp_Park_GetInData
	@CardID Varchar(20),				--卡ID号
	@InTime Varchar(19),				--入场时间
	@OutTime datetime,					--出场时间
	@InOut tinyint						--出入类型（1标准出口，4标准嵌套出口，6半嵌套出口，8独立出口，9单通道）
	With ENCRYPTION
AS
	set nocount on
	Declare @InRsNo int

	if IsDate(@InTime)=0
		Begin
			Select @InRsNo=0
			if @InOut=1 or @InOut=8 or @InOut=11
				Begin
					if exists(Select Top 1 * from Park_CarIn where CardID=@CardID and small=0 and InTime<@OutTime Order By InTime Desc)
						Select @InRsNo=(Select Top 1 [ID] from Park_CarIn where CardID=@CardID and small=0  and InTime<@OutTime Order By InTime Desc)
					else
						Begin  --可能有半嵌套入口记录
						  if exists(Select Top 1 * from Park_CarIn where CardID=@CardID and small=1 and InTime<@OutTime Order By InTime Desc)						
							Select @InRsNo=(Select Top 1 [ID] from Park_CarIn where CardID=@CardID and small=1  and InTime<@OutTime Order By InTime Desc)	
						end
				end
			if @InOut=4 or @InOut=6	or @InOut=12
				Begin
					if exists(Select Top 1 * from Park_CarIn where CardID=@CardID and small=1 and InTime<@OutTime Order By InTime Desc)
						Select @InRsNo=(Select Top 1 [ID] from Park_CarIn where CardID=@CardID and small=1 and InTime<@OutTime Order By InTime Desc)
				end
			Select * from Park_CarIn where ID=@InRsNo
		end
	else
		Begin
			if @InOut=4 or @InOut=6	or @InOut=12 --小车场出场 入场时间为大车场的时间
				Begin
					Select @InRsNo=0
					if exists(Select Top 1 * from Park_CarIn where CardID=@CardID and small=1 and InTime<@OutTime Order By InTime Desc)
						Select @InRsNo=(Select Top 1 [ID] from Park_CarIn where CardID=@CardID and small=1 and InTime<@OutTime Order By InTime Desc)
					Select * from Park_CarIn where ID=@InRsNo
				end
			else
				Select * from Park_CarIn  where  CardID=@CardID and InTime=@InTime
		end
	set nocount off
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场-贵宾车出场>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID('Sp_ParK_VIPCarOut') is not null
	DROP PROCEDURE Sp_ParK_VIPCarOut
go
CREATE    PROCEDURE [dbo].[Sp_ParK_VIPCarOut]
		@OutMachNo tinyint,	
		@CarNO	Varchar(20),		
		@CardID Varchar(20),			--卡ID号
		@CFlag tinyint,					--卡介质(0为IC，1为ID，2IC做ID，3纸票 4纯车牌)
		@OutTime datetime,				--出场时间
		@OutUserName varchar(20),		--出场操作员
		@TypeID  tinyint, 				--模式ID
		@OutPic nVarchar(255)=null ,			--出场图片路径			--异常备注
		@InOut tinyint					--出入类型(0标准入口，1标准出口，2标准嵌套入口，3标准嵌套出口、4半嵌套入口，5半嵌套出口、6单通道)
		--With ENCRYPTION 
	AS
		set nocount on
		Declare @InID int						--入场记录ID
		Declare @YktID int						--开户ID号
		Declare @InInOut tinyint		        --入口控制器类型
		Declare @InControlID tinyint			--入口控制器编号
		Declare @InPic nvarchar(255)			--入场图片
		Declare @CardNo varchar(10)				--卡编号
		Declare @EmpName nvarchar(20)			--车主名称
		Declare @InTime datetime				--车牌号
		Declare @CarNoType tinyint				--车牌类型
		Declare @InUserName varchar(20)			--入场操作员
		Declare @oDiscountNo  tinyint 			--打折机号
		Declare @oTypeID  tinyint 				--模式ID
		Declare @oDiscountTime datetime			--折扣时间
		if exists(Select 1 from ParK_CarOut where CFlag=@CFlag and CardID=@CardID and OutTime=@OutTime) return -1
		Select @oDiscountNo=0,@oTypeID=0,@oDiscountTime=Null
		Select @EmpName='贵宾车',@CarNoType=0
		if exists(Select 1 from ParK_CarIn where CarNO=@CarNO)
			begin
			
				Select @YktID=YktID,@InID=[ID],@InControlID=MachNo,@CardNo=CardNo,@EmpName=EmpName,@CarNO=CarNO,@CarNoType=CarNoType,
					@InTime=InTime,@InUserName=InUserName,@oDiscountNo=DiscountNo,@oTypeID=TypeID,@oDiscountTime=DiscountTime,@InPic=InPic  from ParK_CarIn where CarNO=@CarNO
				Delete from ParK_CarIn where [ID]=@InID  --删除原入场数据\				
			end
	
		insert into ParK_CarOut(InMachNo,OutMachNo,YKTID,CardID,CFlag,CardNo,EmpName,CarNO,CarNoType,CardType,FreeType,InTime,InUserName,OutTime,CentralTime,OutUserName,PayCharge,BalanceMoney,DiscountNo,TypeID,DiscountTime,AccountCharge,PayType,IsOut,UnusualMemo,InPic,OutPic)
				values(@InControlID,@OutMachNo,@YKTID,@CardID,@CFlag,@CardNo,@EmpName,@CarNO,@CarNoType,7,0,@InTime,@InUserName,@OutTime,@OutTime,@OutUserName,0,0,0,@TypeID,'',0,0,1,'',@InPic,@OutPic)
		Return @@IDENTITY
		set nocount off
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场-对外接口调用：检测车牌车是否在场内>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及述二行一次>
-- =============================================
IF OBJECT_ID('Sp_Interface_CheckCarIn') is not null
	DROP PROCEDURE Sp_Interface_CheckCarIn
go
CREATE    PROCEDURE [dbo].[Sp_Interface_CheckCarIn]
		@PlateNo Varchar(20) -- 车牌号码
	AS
		declare @Status int,@CarInTime varchar(19)
		if exists(Select 1 from ParK_CarIn where CarNO=@PlateNo)
		begin
			set @Status = 0
			Select TOP 1 @CarInTime = CONVERT(varchar(19),InTime,120) from ParK_CarIn where CarNO=@PlateNo
			IF isnull(@CarInTime,'') = ''
			begin
				set @Status = 2
				set @CarInTime = ''
			end	
		end
		else
		begin
			set @Status = 1
			set @CarInTime = ''
		end
		select @Status as [Status],@CarInTime as CarInTime
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场-对外接口调用：设置车锁>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及述二行一次>
-- =============================================
  IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Sp_Interface_SetLockedCar]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[Sp_Interface_SetLockedCar]
GO
 
CREATE    PROCEDURE [dbo].[Sp_Interface_SetLockedCar]

		@PlateNo Varchar(20),   -- 车牌号码

		@LockType TINYINT,		-- 需要设置的锁状态 0-未锁  1-已锁
        @CardId VARCHAR(50)='',
        @CardNo VARCHAR(50)=''
	AS

		declare @Status int,@CarInTime varchar(19)
        IF @PlateNo<>''
        BEGIN
           Select @CardId=CardID,@CardNo=CardNo from ParK_CarIn where CarNO=@PlateNo AND Small = 0
        END
        
        IF @CardId <>''
        BEGIN
        	 Select @PlateNo=CarNO,@CardNo=CardNo from ParK_CarIn where CardId=@CardId AND Small = 0
        END
        
        IF	@CardNo<>''
        BEGIN
        	 Select @PlateNo=CarNO,@CardId=CardID from ParK_CarIn where CardNo=@CardNo AND Small = 0
        END
        
        
		if exists(Select * from ParK_CarIn where CardID=@CardId AND Small = 0)

		begin

			set @Status = 0

			update ParK_CarIn SET IsLocked = @LockType where CardID=@CardId AND Small = 0

			IF @@rowcount = 0

			begin

				set @Status = 2

			end

			IF @LockType = 0

			begin

				if exists(Select * from Yun_AutoLockingSet where CardID=@CardId)

				begin

					update Yun_AutoLockingSet set LastUnLockTime = GETDATE() where CardID=@CardId

				end

			end

		end

		else

		begin

			set @Status = 1

		end

		select @Status



GO


-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场-对外接口调用：设置挂失、解挂>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及述二行一次>
-- =============================================
IF OBJECT_ID('Sp_Interface_SetLossCard') is not null
	DROP PROCEDURE Sp_Interface_SetLossCard
go
CREATE    PROCEDURE [dbo].[Sp_Interface_SetLossCard]
		@PlateNo Varchar(20),   -- 车牌号码
		@CardNo  varchar(20),	-- 卡号
		@UdpType tinyint,		-- 操作类型 0-挂失；1-解挂
		@SelType tinyint		-- 查询条件 0，车牌号，1卡号
	AS
		declare @Status int,@errorcode int = 0,@yktid int = 0,@CardStatus int
		IF @UdpType = 0
		begin
			set @CardStatus = 1
		end
		else
		begin
			set @CardStatus = 3
		end
		IF @SelType = 0
		begin
			SELECT TOP 1 @yktid = a.YKTID FROM Ykt_CardIssuePark a LEFT join Ykt_CardIssue b ON a.YKTID = b.YktID
			WHERE a.CarNO = @PlateNo and b.States <> 6
			IF isnull(@yktid,0) = 0
			begin
				set @yktid = 0
			end
		end
		else
		begin
			SELECT TOP 1 @yktid = a.YKTID FROM Ykt_CardIssuePark a LEFT join Ykt_CardIssue b ON a.YKTID = b.YktID
			WHERE b.CardNO = @CardNo and b.States <> 6
			IF isnull(@yktid,0) = 0
			begin
				set @yktid = 0
			end
		end
		IF @yktid > 0
		begin
			if(@errorcode=0)
			--执行修改车场卡片表中的卡片状态2挂失3解挂5退款6销户
			begin
				update dbo.YKT_CardIssue set [States]=@CardStatus where YKTID=@yktid
				select @errorcode=@@ERROR	
			end
			if(@errorcode=0)
			--执行重置下载状态车场
			begin
				update dbo.YKT_CardIssuePark set [DownLoad]=dbo.PadLeft('0', '0', 255) where YKTID=@yktid
				select @errorcode=@@ERROR
			end
			if(@errorcode=0)
			--执行重置下载状态门禁
			begin
				update dbo.YKT_CardIssueDoor set [DownLoad]=dbo.PadLeft('0', '0', 1000) where YKTID=@yktid
				select @errorcode=@@ERROR
			end
			IF @errorcode = 0
			begin
				set @Status = 0
			end
			else
			begin
				set @Status = 1
			end
		end
		else
		begin
			set @Status = 2
		end
		select @Status
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场-对外接口调用：获取可用的车卡充值类型>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及述二行一次>
-- =============================================
IF OBJECT_ID('Sp_Interface_GetRechargeType') is not null
	DROP PROCEDURE Sp_Interface_GetRechargeType
go
CREATE    PROCEDURE [dbo].[Sp_Interface_GetRechargeType]
		@PlateNo Varchar(20),   -- 车牌号码
		@CardNo  varchar(20),	-- 卡号
		@SelType tinyint		-- 查询条件 0，车牌号，1卡号
	AS
		declare @CardType int = 0
		IF @SelType = 0
		begin
			SELECT TOP 1 @CardType = a.CardType FROM Ykt_CardIssuePark a LEFT join Ykt_CardIssue b ON a.YKTID = b.YktID
			WHERE a.CarNO = @PlateNo and b.States <> 6
			IF isnull(@CardType,0) = 0
			begin
				set @CardType = 0
			end
		end
		else
		begin
			SELECT TOP 1 @CardType = a.CardType FROM Ykt_CardIssuePark a LEFT join Ykt_CardIssue b ON a.YKTID = b.YktID
			WHERE b.CardNO = @CardNo and b.States <> 6
			IF isnull(@CardType,0) = 0
			begin
				set @CardType = 0
			end
		end
		select ROW_NUMBER() OVER (ORDER BY d.Num ASC) as ID,d.Name,d.Num,d.Amount from
		(
			select b.CName as Name,a.CardType
				  ,case sType WHEN 0 THEN 1 WHEN 1 then 3 when 2 THEN 6 WHEN 3 THEN 12 END as Num
				  ,[ChargeMoney] as Amount
			  FROM Park_MonthSet a LEFT join Park_CardType b ON a.CardType = b.CType
			  UNION ALL
			SELECT '免费卡A' as Name,41 as CardType,1 as Num,0 as Amount
			UNION all
			SELECT '免费卡A' as Name,41 as CardType,3 as Num,0 as Amount
			UNION all
			SELECT '免费卡A' as Name,41 as CardType,6 as Num,0 as Amount
			UNION all
			SELECT '免费卡A' as Name,41 as CardType,12 as Num,0 as Amount
			UNION all
			SELECT '免费卡B' as Name,42 as CardType,1 as Num,0 as Amount
			UNION all
			SELECT '免费卡B' as Name,42 as CardType,3 as Num,0 as Amount
			UNION all
			SELECT '免费卡B' as Name,42 as CardType,6 as Num,0 as Amount
			UNION all
			SELECT '免费卡B' as Name,42 as CardType,12 as Num,0 as Amount
		) as d
		WHERE d.CardType = @CardType
GO

-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <车场-对外接口调用：车卡充值>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及述二行一次>
-- =============================================
IF OBJECT_ID('Sp_Interface_CardRecharge') is not null
	DROP PROCEDURE Sp_Interface_CardRecharge
go
 CREATE    PROCEDURE [dbo].[Sp_Interface_CardRecharge]
		@PlateNo Varchar(20),   -- 车牌号码
		@CardNo  varchar(20),	-- 卡号
		@StartDate datetime,	-- 新开始时间
		@EndDate datetime,		-- 新结束时间
		@ChargeMoney money,		-- 充值金额
		@SelType TINYINT,		-- 查询条件 0，车牌号，1卡号
        @TransactionID varchar(100)='',	--交易订单号
        @UserDate datetime=NULL, --充值时间,
        @PayType  INT = 2
	AS
		declare @Status int,@yktid int = 0
		IF @UserDate = null
			set @UserDate = GETDATE()
		IF @SelType = 0
		begin
			SELECT TOP 1 @yktid = a.YKTID FROM Ykt_CardIssuePark a LEFT join Ykt_CardIssue b ON a.YKTID = b.YktID
			WHERE a.CarNO = @PlateNo and b.States <> 6
			IF isnull(@yktid,0) = 0
			begin
				set @yktid = 0
			end
		end
		else
		begin
			SELECT TOP 1 @yktid = a.YKTID FROM Ykt_CardIssuePark a LEFT join Ykt_CardIssue b ON a.YKTID = b.YktID
			WHERE b.CardNO = @CardNo and b.States <> 6
			IF isnull(@yktid,0) = 0
			begin
				set @yktid = 0
			end
		end
		IF @yktid > 0
		BEGIN
		    declare @errorcode int
			select @errorcode=@@ERROR
		    IF ISNULL(@TransactionID,'')<>''
		    BEGIN
		        IF EXISTS(SELECT TOP 1 * FROM YKT_CardRsMoney WHERE YRecordNumber=@TransactionID)
		        BEGIN
		        	SET @errorcode=1
		        END
		    END
			if(@errorcode=0)
			begin
				--添加用户充值记录
				insert into dbo.YKT_CardRsMoney(YKTID,SType,FrontDate,NStartDate,NEndDate,BalanceMoney,PayType,Foregift,BeforeMoney,UserDate,UserName,Remark,YRecordNumber) 
				values (@yktid,1,@StartDate,@StartDate,@EndDate,@ChargeMoney,@PayType,0,0,@UserDate,'云平台服务','卡延期',@TransactionID)
				select @errorcode=@@ERROR
			end
			if(@errorcode=0)
			--执行修改车场卡片副表中的开始日期,结束日期,储值金额,BalanceMoney=@BALANCEMONEY
			begin
				update dbo.YKT_CardIssuePark set EndDate=@EndDate,[DownLoad]=dbo.PadLeft('0', '0', 255),UserDate=GETDATE() where YKTID=@yktid
				select @errorcode=@@ERROR	
			end
			if(@errorcode=0)
			begin			
				insert into YKT_CardDelyInfo(yktid,orgenddate,newenddate,stype,userdate,username,remark)
				values (@yktid,@StartDate,@EndDate,0,@UserDate,'云平台服务','车场延期')
				select @errorcode=@@ERROR
			end
			IF @errorcode = 0
			begin
				set @Status = 0
			end
			else
			begin
				set @Status = 1
			end
		end
		else
		begin
			set @Status = 2
		end
		select @Status

GO
-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2016-07-07>  
-- 功能说明： <车场-对外接口调用：设置车锁>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及述二行一次>
-- =============================================
 IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Sp_Interface_Park_SetAutoLock]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[Sp_Interface_Park_SetAutoLock]
GO

 
CREATE    PROCEDURE [dbo].[Sp_Interface_Park_SetAutoLock]

		@PlateNo Varchar(20),   -- 车牌号码

		@LockType tinyint,		-- 需要设置的锁状态 0-自动锁车  1-取消自动锁车

		@LockTimeSpan INT,		-- 锁车间隔时长
        @CardId VARCHAR(50)='',
        @CardNo VARCHAR(50)=''
	AS

		declare @Status int = 0
        
         IF @PlateNo<>''
        BEGIN
           Select @CardId=CardID,@CardNo=CardNo from ParK_CarIn where CarNO=@PlateNo AND Small = 0
        END
        
        IF @CardId <>''
        BEGIN
        	 Select @PlateNo=CarNO,@CardNo=CardNo from ParK_CarIn where CardId=@CardId AND Small = 0
        END
        
        IF	@CardNo<>''
        BEGIN
        	 Select @PlateNo=CarNO,@CardId=CardID from ParK_CarIn where CardNo=@CardNo AND Small = 0
        END
        
		if exists(Select * from Yun_AutoLockingSet where CardID=@CardId)

		begin

			update Yun_AutoLockingSet set LockType = @LockType,LockTimeSpan = @LockTimeSpan where CardID=@CardId

			IF @@rowcount = 0

			begin

				set @Status = 2

			end

		end

		else

		begin

			insert INTO Yun_AutoLockingSet(CarNo,CardNo,CardId,LockType,LockTimeSpan,LastUnLockTime)

			select @PlateNo,@CardNo,@CardId,@LockType,@LockTimeSpan,GETDATE()

			IF @@rowcount = 0

			begin

				set @Status = 2

			end

		end

		if exists(Select * from ParK_CarIn where CardID=@CardId AND Small = 0)

		begin

			IF @LockType = 0

			begin

				update ParK_CarIn set IsLocked = 1 WHERE CardID=@CardId AND Small = 0

			end

		end

		select @Status



GO


-- ============================================= 
-- 程序编写： <老王> 
-- 建立日期： <2015-08-24>  
-- 功能说明： <一卡通->
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('Sp_Ykt_GetRecordUID') IS NOT NULL
	DROP PROCEDURE Sp_Ykt_GetRecordUID
GO
	CREATE PROCEDURE [dbo].[Sp_Ykt_GetRecordUID]
		@RecordID int,		--刷卡记录ID
		@ContID int,		--控制器ID
		@DoorNo int			--门序号
	AS
	BEGIN
		declare @userid int,@recid int,@opentimeB datetime,@opentimeE datetime
		select @opentimeE = EventTime from Door_OpenDoorRecord where ID = @RecordID
		select @opentimeB = DATEADD(second,-5,@opentimeE)
		select @opentimeE = DATEADD(second,5,@opentimeE)
		select TOP 1 @recid = id,@userid = UserID FROM DC_UserDoor 
		where [STATUS] = 0 AND ContID = @ContID AND DoorNo = @DoorNo 
		AND OpenDoorTime between @opentimeB AND @opentimeE
		ORDER BY OpenDoorTime desc
		
		if ISNULL(@userid,0) > 0
		begin
			update Door_OpenDoorRecord SET Extended = @userid where ID = @RecordID
			update DC_UserDoor SET [STATUS] = 1 where id = @recid
		end
	END
GO


IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Yun_Card_NewAndPayment]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[Yun_Card_NewAndPayment] 
GO

CREATE proc [dbo].[Yun_Card_NewAndPayment] 
@carNo  varchar(10),        --车牌号码
@cardId varchar(20),        --卡片ID
@ownerName varchar(50),     --车主姓名
@ownerPhone varchar(50),    --车主电话
@ownerId varchar(50),       --车主编号
@beginDate datetime,        --开始时间 
@endDate datetime,          --结束时间
@departName varchar(200),   --部门名称
@cardtype int=41,  --41  免费卡
@address VARCHAR(50)='',
@CarTypeMoney  INT=0
as

--检测车卡信息是否存在
declare @yktid int=0,@owneroldId varchar(50)
if exists (select top 1 * from Ykt_CardIssuePark  where CarNO  = @carNo and sType <> 6)
begin
	select top 1  @yktid=YKTID from Ykt_CardIssuePark  where CarNO  = @carNo  and sType <> 6 order by YKTID desc
	
	select top 1 @owneroldId=PID from Ykt_CardIssue where YktID=@yktid
end


--延期
if @yktid!=0
begin
	Update Per_Persons set perName=@ownerName,PerTel=@ownerPhone,PerAddr=@address,CrUserDate=GETDATE() where PerID=@owneroldId
	Update Ykt_Cardissue set PerName=@ownerName where YktID=@yktid
	
	if(getdate()<(select top 1 Enddate from Ykt_CardIssuePark where YKTID=@yktid)) 
        Update Ykt_CardIssuePark set  EndDate=@endDate,UserDate=GETDATE() where YKTID=@yktid
    else 
        Update Ykt_CardIssuePark set StartDate=@beginDate,EndDate=@endDate,UserDate=GETDATE() where YKTID=@yktid
        
    insert into dbo.YKT_CardRsMoney(YKTID,SType,FrontDate,NStartDate,NEndDate,BalanceMoney,PayType,Foregift,BeforeMoney,UserDate,UserName,Remark) 
    select top 1 YKTID,1,@beginDate,@beginDate,@endDate,@CarTypeMoney,0,0,0,getdate(),'物业云平台','延期' from Ykt_Cardissue where  YktID=@yktid
end

--发卡
if @yktid=0
begin

declare 
@deptId varchar(50),
@pid int=0

 if exists(select DeptID from Per_Dept where DeptName =@departName)
 begin
     select top 1 @deptId=DeptID  from Per_Dept where DeptName =@departName
 end
 
 if  exists(select top 1 * from Per_Persons  where PerID=@ownerId )
 begin
    select top 1 @pid=id from Per_Persons  where PerID=@ownerId
 end
 
 if @pid=0
 begin
 	 insert into Per_Persons(Pid,PerID,PerName,PerAddr,PerTel,LandlineNum,ReMark,PerSex,PerNative,PerEmail,EduLevel,DeptID,CrUserDate,CrUserName,status)
	 values(0,@ownerId,@ownerName,@address,@ownerPhone,'','[存车牌快速发行(云端)]','男','汉',@carNo,'大专',@deptId,GETDATE(),'云端发行',1)
	 set @pid=@@IDENTITY
	 update Per_Persons  set Pid=@pid,UpdateFlag=1   where id=@pid
 end
 
declare @userdate datetime
set @userdate=GETDATE()

exec Sp_YKT_CardIssue_PublishCard @cardId,4,@carNo,@pid,@ownerName,0,0,'10000000000000000000','[物业云平台发行]',@userdate,'物业云平台',@cardtype,0,@carNo,0,'','',@beginDate,@endDate,@CarTypeMoney,1,'','000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000',0,1

end

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Yun_Car_BlackList]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[Yun_Car_BlackList]
GO

-- 添加信息黑名单 
create proc Yun_Car_BlackList 
@carNo  varchar(10),   --车牌号码
@carNoType int,        --车牌类型(0无,1黑名单,2特种车辆)
@isStop int,           --类型(0无,1禁止通行,2通行免费,3自由通行)
@memo varchar(50),     --备注
@userDate datetime,    --操作时间
@userName varchar(20)  --操作用户
as

if exists(select * from ParK_CarBlackList where CarNO=@carNo)
begin
update ParK_CarBlackList  set CarNoType=@carNoType,IsStop=@isStop,Memo=@memo
,CrTime=@userDate,CrUserName=@userName where CarNO=@carNo
end
else
begin
insert into ParK_CarBlackList(CarNO, CarNoType, IsStop, Memo, CrTime, CrUserName)
values(@carNo, @carNoType, @isStop, @memo, @userDate, @userName)
end
GO

 
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Yun_TemporaryPayChang]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[Yun_TemporaryPayChang]
GO
 CREATE Proc [dbo].[Yun_TemporaryPayChang]
@CarNO nvarchar(10),
@CardID  varchar(20),
@CardNo  varchar(20),
@InTime datetime,	
@OVerTimeS datetime,
@PayCharge money,
@PayChargeTime datetime,	
@AccountCharge money,
@DiscountNo int,
@TypeID int,
@DiscountTime datetime,
@DisAmount money,
@PayType varchar(20),
@OrderNum nvarchar(100),
@RecType int
as
-------------------------赋值begin-------------------------------

-------------------------赋值 end------------------------------

--------------------------------定义变量 begin---------------------
declare @newcarNo varchar(50),
@newInTime datetime,
@newCardId varchar(50),
@newCardNo varchar(50),
@newCardType varchar(50),
@id   int=0

--------------------------------定义变量 end---------------------
if @PayType=3    --  paytype 0 现金  1  微信支付  
set @PayType=1

----------------------------获取车牌号码 begin-----------------------

if @CarNO!=''
	set @newcarNo=@CarNO
if @CardID!=''
	select top 1 @newcarNo=CarNO,@CarNO=CarNO from Park_CarIn  where  CardID=@CardID  order by ID desc
if @CardNo!=''
	select top 1 @newcarNo=CarNO,@CarNO=CarNO from Park_CarIn  where  CardNo=@CardNo  order by ID desc
if @CardID!=''
	select top 1 @CarNO=CarNO from Park_CarOut  where  CardID=@CardID  order by ID desc
if @CardNo!=''
	select top 1 @CarNO=CarNO from Park_CarOut  where  CardNo=@CardNo  order by ID desc
----------------------------获取车牌号码 end-----------------------
--获取场内车辆信息
if exists(select top 1 * from Park_CarIn where  RIGHT(CarNO,6)=RIGHT(@newcarNo,6) and isnull(Small,0)=0 )
begin
	select top 1 @newInTime= InTime,@newCardId=CardID,@newCardNo=CardNo,@newCardType=CardType from Park_CarIn where  RIGHT(CarNO,6)=RIGHT(@newcarNo,6) and isnull(Small,0)=0   order by ID desc
end
--如果场内未取到结果则在出场表中获取
if isnull(@newCardId,'')=''
begin
	if exists(select top 1 * from Park_CarOut where  RIGHT(CarNO,6)=RIGHT(@newcarNo,6))
	begin
		select top 1 @newInTime= InTime,@newCardId=CardID,@newCardNo=CardNo,@newCardType=CardType from Park_CarOut where  RIGHT(CarNO,6)=RIGHT(@newcarNo,6)   order by ID desc
	end
END

IF @newCardType='41' OR @newCardType='42'
 SET @newCardType = '40'
--处理月林卡 
-- 月租入场，临时卡出场  
if isnull(@newCardId,'')=''
BEGIN
if exists(select top 1 * from Park_CarIn where  RIGHT(CarNO,6)=RIGHT(@newcarNo,6) and isnull(Small,0)=0  )
begin
	select top 1 @newInTime= InTime,@newCardId=CardID,@newCardNo=CardNo,@newCardType=CardType from Park_CarIn where  RIGHT(CarNO,6)=RIGHT(@newcarNo,6) and isnull(Small,0)=0   order by ID desc
end
END

--临时卡入场，月租出场
IF EXISTS (SELECT TOP 1 1 FROM dbo.Ykt_CardIssuePark WHERE RIGHT(CarNO,6)=RIGHT(@newcarNo,6))
BEGIN
 SELECT TOP 1 @newCardType=CardType FROM dbo.Ykt_CardIssuePark WHERE RIGHT(CarNO,6)=RIGHT(@newcarNo,6) ORDER BY YKTID DESC 
END

IF @newCardType>10 AND @newCardType<15
BEGIN
	SET @newCardType=@newCardType+10
END

--如果不存在该OrderNum数据,则执行插入语句
if isnull(@newCardId,'')<>''
begin
	if not exists(SELECT top 1  1 FROM  dbo.Park_CentralCharge  WHERE OrderNum=@OrderNum )
	begin
		if(@RecType=1)
		begin
			insert into Park_CentralCharge(BoxID,IsOut,YktID,CardID,CFlag,CardType,InTime,PayCharge,PayChargeTime,UserName,AccountCharge,CardNo,CarNO,OVerTimeS,DisAmount,PayType,OrderNum)
			values (0,0,0,@CardID,4,@newCardType,@newInTime,@PayCharge,@PayChargeTime,'云平台服务',@AccountCharge,@newCardNo,@newcarNo,@OVerTimeS,@DisAmount,@PayType,@OrderNum)
			set  @id= @@IDENTITY	
		end
		else
		begin
			if(@RecType > 1 and @DiscountNo > 0)
			begin
				insert into Park_DisDetail(OutType,DiscountID,CardID,OutTime,DiscountTime,DisAmount)
				values (1,@TypeID,@newCardId,@PayChargeTime,@DiscountTime,@DisAmount)
			end
			insert into Park_CentralCharge(BoxID,IsOut,YktID,CardID,CFlag,CardType,InTime,PayCharge,PayChargeTime,UserName,AccountCharge,CardNo,CarNO,FreeType,ZjPic,DiscountNo,TypeID,DiscountTime,DisAmount,UnusualMemo,PayType,OrderNum)
			values (0,0,0,@CardID,4,@newCardType,@newInTime,@PayCharge,@PayChargeTime,'云平台服务',@AccountCharge,@newCardNo,@newcarNo,0,'',@DiscountNo,@TypeID,@DiscountTime,@DisAmount,'',@PayType,@OrderNum)
			set  @id= @@IDENTITY
		end     
	end
	else
    begin
    SELECT top 1  @id=ID FROM  dbo.Park_CentralCharge  WHERE OrderNum=@OrderNum
    end
end

select @id

GO
-- ============================================= 
-- 程序编写： <李君> 
-- 建立日期： <2015-10-21>  
-- 功能说明： <访客-控制器设置信息视图>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Park_SumUser') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Park_SumUser
GO
Create PROCEDURE  [dbo].[Sp_Park_SumUser]         --日报表 
	  @firstdate datetime,                --起始日期
	  @lastdate datetime                --结束日期	
AS
 truncate table PKCarOutRpt
 Insert into PKCarOutRpt([UserName],[Gate1]
      ,[Gate2]
      ,[ITempA]
      ,[Imoney]
      ,[ImonthA]
      ,[ImonthAvg]
      ,[IMoney0]
      ,[IMoneySum]
      ,[IFreeA]
      ,[SumQuan]
      ,[ITempFree]
      ,[FreeAmount]
      ,[DisAMount]
      ,[PosMoney]
      ,[Account]
      ,[TNominal] 
)
    select [UserName]
      ,sum([HandGate]) as Gate1
	   ,sum([ComputerGate]) as Gate2
	   ,sum([TempCardOut]) as ITempA
	   ,sum([TotalCharge]) as Imoney
	   ,sum([MonthCardOut]) as ImonthA
		,sum([MonthCardIn]) as ImonthAvg
		,sum(StoredCardNum) as IMoney0
		,sum([StoredCardMoney]) as IMoneySum
		,sum([FreeCarNo]) as IFreeA		
		,sum(([HandGate]+[ComputerGate]+[TempCardOut]+[MonthCardOut]+StoredCardNum+ [FreeCarNo])) as SumQuan
		,sum([TempFree]) as ITempFree
		,sum([FreeCharge]) as FreeAmount
		,sum([DisCharge]) as DisAMount
		,sum([PosMoney]) as PosMoney
		 ,sum([Account]) as Account
		 ,sum([TNominal]) as TNominal
         from ParK_SumUser where [LoginDate] between @firstdate and @lastdate
     group by [UserName]
     IF  not exists(select 1 from PKCarOutRpt)
 		BEGIN
 			Insert into PKCarOutRpt(Username) VALUES(NULL)
 		END
 		
 		select * from PKCarOutRpt order by Username		
GO

-- ============================================= 
-- 程序编写： <李君> 
-- 建立日期： <2015-10-21>  
-- 功能说明： <相似度匹配>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('Park_GetCarInData1') IS NOT NULL
	DROP PROCEDURE Park_GetCarInData1
GO
Create PROCEDURE [dbo].[Park_GetCarInData1]
	@CarNO varchar(10)=null,
	@small int ,
	@flag int
AS

if @flag=0
begin
	if Exists(select 1 from Park_CarIn where right(CarNO,6)=right(@CarNO,6) and Small=@small and CFlag=4)  --前车牌识别
	    begin
		   SELECT top 1 ID,MachNo,YktID,CardID,CFlag,CardNo,EmpName,CarNO,CardType,CarNoType,InTime,InUserName,InPic,Small,DiscountNo,TypeID,DiscountTime,0 as datatype,IsLocked from Park_CarIn where right(CarNO,6)=right(@CarNO,6) and Small=@small and CFlag=4 order by InTime DESC
	    end
	else if Exists(select 1 from Park_CarIn where right(BackCarNO,6)=right(@CarNO,6) and Small=@small and CFlag=4) -- 后车牌识别
	
	    begin
	       SELECT top 1 ID,MachNo,YktID,CardID,CFlag,CardNo,EmpName,BackCarNO as CarNO,CardType,CarNoType,InTime,InUserName,BackInPic as InPic,Small,DiscountNo,TypeID,DiscountTime,0 as datatype,IsLocked from Park_CarIn where right(BackCarNO,6)=right(@CarNO,6) and Small=@small and CFlag=4 order by InTime DESC
	    end 
	    
	else
	begin
	SELECT dbo.F_strcompare(right(@CarNO,6),right(CarNO,6))as zxd1 ,dbo.F_strcompare(right(@CarNO,6),right(BackCarNO,6))as zxd2,
      ID,MachNo,YktID,CardID,CFlag,CardNo,EmpName,CarNO,CardType,CarNoType,InTime,InUserName,InPic,Small,DiscountNo,TypeID,DiscountTime,0 as datatype,IsLocked, BackCarNO,BackInPic into #a from Park_CarIn where Small=@small and CFlag=4;
			 
			 DECLARE @zxd1 int,@zxd2 int,@zxd int
			 select top 1 @zxd1=zxd1 from #a order by zxd1 desc --取第一条数据
			 select top 1 @zxd2=zxd2 from #a order by zxd2 desc
			 if(@zxd1>82 or @zxd2>82 )--差1位以内
			 begin 
			     if @zxd1>82 --可能是前车牌匹配，也可能是后车牌匹配，取匹配度
			        begin
			        select @zxd =@zxd1 
			        end
			     else
			      begin
			        select @zxd =@zxd2
			      end
			     select  ID,MachNo,YktID,CardID,CFlag,CardNo,EmpName,CardType,CarNoType,InTime,InUserName,Small,DiscountNo,TypeID,DiscountTime,IsLocked,1 as datatype,
			     case when zxd1>=zxd2  then  CarNO  else BackCarNO end as CarNO,case when zxd1>=zxd2  then  InPic  else BackInPic end as InPic
			     from  #a  where  zxd1=@zxd or zxd2=@zxd
		     end		 
			 else if((@zxd1>66 and @zxd1<82) or(@zxd2>66 and @zxd2<82))--差2位以内
			 begin
			     if @zxd1>66 and @zxd1<82 --可能是前车牌匹配，也可能是后车牌匹配，取匹配度
			        select @zxd =@zxd1 
			     else
			        select @zxd =@zxd2  
			    select  ID,MachNo,YktID,CardID,CFlag,CardNo,EmpName,CardType,CarNoType,InTime,InUserName,Small,DiscountNo,TypeID,DiscountTime,IsLocked,2 as datatype,
			     case when zxd1>=zxd2  then  CarNO  else BackCarNO end as CarNO,case when zxd1>=zxd2  then  InPic  else BackInPic end as InPic
			     from  #a  where  zxd1=@zxd or zxd2=@zxd
			  end   
			 else --错3位以上
			     select  ID,MachNo,YktID,CardID,CFlag,CardNo,EmpName,CarNO,CardType,CarNoType,InTime,InUserName,InPic,Small,DiscountNo,TypeID,DiscountTime,IsLocked,3 as datatype from #a where (@zxd1>30 or zxd2>30) and LEFT(CarNO,3)<>'NOP'  order by zxd1 desc, zxd2 desc,InTime desc;
			 drop table #a
	end
end
else if @flag=1
    begin 
	    SELECT 0 as ww,ID,MachNo,YktID,CardID,CFlag,CardNo,EmpName,CarNO,CardType,CarNoType,InTime,InUserName,InPic,Small,DiscountNo,TypeID,DiscountTime,4 as datatype,IsLocked,BackCarNO,BackInPic from Park_CarIn where CarNO like 'NOP%' and Small=@small and CFlag=4 order by ww desc,InTime desc;
    end
GO

-- ============================================= 
-- 程序编写： <李君> 
-- 建立日期： <2015-10-21>  
-- 功能说明： <相似度匹配>
-- 程序修改： <可选>  
-- 修改日期： <可选>  
-- 修改说明： <可选，每修改一次，请重复本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('Park_GetCarInData') IS NOT NULL
	DROP PROCEDURE Park_GetCarInData
GO
Create PROCEDURE [dbo].[Park_GetCarInData]
	@CarNO varchar(10)=null,
	@small int ,
	@flag int
AS

if @flag=0
begin
	if Exists(select 1 from Park_CarIn where right(CarNO,6)=right(@CarNO,6) and Small=@small and CFlag=4)
	begin
		SELECT top 1 ID,MachNo,YktID,CardID,CFlag,CardNo,EmpName,CarNO,CardType,CarNoType,InTime,InUserName,InPic,Small,DiscountNo,TypeID,DiscountTime,0 as datatype,IsLocked from Park_CarIn where right(CarNO,6)=right(@CarNO,6) and Small=@small and CFlag=4 order by InTime DESC
	end
	else
	begin

	SELECT dbo.F_strcompare(right(@CarNO,6),right(CarNO,6))as ww ,right(CarNO,6) as CardID1,[ID]
      ,[MachNo]
      ,[YktID]
      ,[CardID]
      ,[CFlag]
      ,[CardNo]
      ,[EmpName]
      ,[CarNO]
      ,[CardType]
      ,[CarNoType]
      ,[InTime]
      ,[InUserName]
      ,[InPic]
      ,[Small]
      ,[DiscountNo]
      ,[TypeID]
      ,[DiscountTime]
      ,[Makeup]
      ,[IsLocked] into #a from Park_CarIn where Small=@small and CFlag=4;
			 DECLARE @id int ,@ww int,@n int
			 select top 1 @id=id,@ww=ww  from #a order by ww desc;
			 if(@ww>82)--差1位以内
				select *,1 as datatype from #a where @ww=ww order by ww desc
			 else
			 if(@ww>66 and @ww<82)--差2位以内
			 select*,2 as datatype from #a where @ww=ww order by ww desc
			 else--错4位以上
			 select *,3 as datatype from #a where ww>30 order by ww desc;
			 drop table #a
	end
end
else if @flag=1
begin 
	SELECT 0 as ww,ID,MachNo,YktID,CardID,CFlag,CardNo,EmpName,CarNO,CardType,CarNoType,InTime,InUserName,InPic,Small,DiscountNo,TypeID,DiscountTime,4 as datatype,IsLocked from Park_CarIn where CarNO like 'NOP%' and Small=@small and CFlag=4 order by ww desc,InTime desc;
end
GO
-- ============================================= 
-- 程序編寫： <谭飞> 
-- 建立日期： <2015-12-26>  
-- 功能說明： <車場-出入口小时车流量统计>
-- 程序修改： <可選>  
-- 修改日期： <可選>  
-- 修改說明： <可選，每修改一次，請重復本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Park_CarInOutTrafficHour') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Park_CarInOutTrafficHour
GO
create PROCEDURE [dbo].[Sp_Park_CarInOutTrafficHour]
	@begindate datetime,
	@type tinyint = 0			--0:小时车流量统计；1:日车流量统计；2:月车流量统计
AS
	set NOCOUNT on
		declare @s nvarchar(4000),@s2 nvarchar(4000),@s3 nvarchar(4000),@ss nvarchar(4000),@sql nvarchar(4000)
		if exists(select 1 from tempdb..sysobjects where id=object_id('tempdb..#t1'))
			DROP TABLE #t1
		create table #t1([hour] int,title varchar(20))
		if exists(select 1 from tempdb..sysobjects where id=object_id('tempdb..#t2'))
				DROP TABLE #t2
		create table #t2(DTime varchar(50),[hour] varchar(20),hid int,InCount int,OutCount int,CCustName varchar(20))
		IF @type = 0--小时
		begin
			insert into #t1 SELECT 0,'00:00:00---01:00:00'
			insert into #t1 SELECT 1,'01:00:00---02:00:00'
			insert into #t1 SELECT 2,'02:00:00---03:00:00'
			insert into #t1 SELECT 3,'03:00:00---04:00:00'
			insert into #t1 SELECT 4,'04:00:00---05:00:00'
			insert into #t1 SELECT 5,'05:00:00---06:00:00'
			insert into #t1 SELECT 6,'06:00:00---07:00:00'
			insert into #t1 SELECT 7,'07:00:00---08:00:00'
			insert into #t1 SELECT 8,'08:00:00---09:00:00'
			insert into #t1 SELECT 9,'09:00:00---10:00:00'
			insert into #t1 SELECT 10,'10:00:00---11:00:00'
			insert into #t1 SELECT 11,'11:00:00---12:00:00'
			insert into #t1 SELECT 12,'12:00:00---13:00:00'
			insert into #t1 SELECT 13,'13:00:00---14:00:00'
			insert into #t1 SELECT 14,'14:00:00---15:00:00'
			insert into #t1 SELECT 15,'15:00:00---16:00:00'
			insert into #t1 SELECT 16,'16:00:00---17:00:00'
			insert into #t1 SELECT 17,'17:00:00---18:00:00'
			insert into #t1 SELECT 18,'18:00:00---19:00:00'
			insert into #t1 SELECT 19,'19:00:00---20:00:00'
			insert into #t1 SELECT 20,'20:00:00---21:00:00'
			insert into #t1 SELECT 21,'21:00:00---22:00:00'
			insert into #t1 SELECT 22,'22:00:00---23:00:00'
			insert into #t1 SELECT 23,'23:00:00---24:00:00'
			insert into #t2 SELECT ga.DTime,ga.hour,ga.hid,SUM(ga.InCount) as InCount,SUM(ga.OutCount) as OutCount,ga.CCustName FROM 
			(	select a.DTime,(CAST(a.[hour] AS varchar(8)) + '时') as [hour],a.hour as hid,isnull(a.data,0) as InCount,0 as OutCount,a.CCustName from
				(
					select convert(varchar(11),@begindate,120) + b.title as DTime,b.hour,da.data,c.CCustName from 
					(
						select count(DATEPART(HH,ta.InTime))data,DATEPART(HH,InTime) hour,ta.CardType from 
						(
							SELECT InTime,CardType FROM Park_CarIn
							UNION ALL
							SELECT InTime,CardType FROM Park_CarOut where IsOut IS NOT NULL
							UNION ALL
							SELECT InTime,CardType FROM Park_CarOut where IsOut IS NULL AND EmpName = '入口电脑开闸'
						) ta
						WHERE convert(varchar(10),ta.InTime,120) = convert(varchar(10),@begindate,120)
						group by DATEPART(HH,InTime),ta.CardType
					) da LEFT JOIN #t1 b ON da.[hour] = b.[hour]
					LEFT JOIN Park_CardType c ON da.CardType = c.CType
				) a
				UNION ALL
				select f.DTime,(CAST(f.[hour] AS varchar(8)) + '时') as [hour],f.hour as hid,0 as InCount,isnull(f.data,0) as OutCount,f.CCustName from
				(
					select convert(varchar(11),@begindate,120) + b.title as DTime,b.hour,da.data,cd.CCustName from 
					(
						select count(DATEPART(HH,ta.OutTime))data,DATEPART(HH,OutTime) hour,ta.CardType from 
						(
							SELECT OutTime,CardType FROM Park_CarOut where IsOut IS NOT NULL
							UNION ALL
							SELECT OutTime,CardType FROM Park_CarOut where IsOut IS NULL AND EmpName = '出口电脑开闸'
						) ta
						WHERE convert(varchar(10),ta.OutTime,120) = convert(varchar(10),@begindate,120)
						group by DATEPART(HH,OutTime),ta.CardType
					) da LEFT JOIN #t1 b ON da.[hour] = b.[hour]
					LEFT join Park_CardType cd ON da.CardType = cd.CType
				) f
			) ga group BY ga.DTime,ga.hour,ga.CCustName,ga.hid
			if exists(SELECT 1 from #t2)
			BEGIN
				Select @s=isnull(@s+',','') + 'Sum(IsNull(' + quotename(gcs.CCustName) + ',0)) as ' + quotename(gcs.CCustName + '-入') from
				#t2 gcs group by gcs.CCustName
				Select @s2=isnull(@s2+',','') + 'Sum(IsNull(' + quotename(gcs.CCustName) + ',0)) as ' + quotename(gcs.CCustName + '-出') from
				#t2 gcs group by gcs.CCustName
				Select @s3=isnull(@s3+',','')+quotename(gcs.CCustName) from
				#t2 gcs group by gcs.CCustName
				Select @ss=isnull(@ss+',','')+ quotename(gcs.CCustName + '-入') + ',' + quotename(gcs.CCustName + '-出') from
				#t2 gcs group by gcs.CCustName
				set @sql = 'select hid as ID,DTime as 日期,a.hour as 时间,' + @ss + ',入场总数,出场总数 from (select DTime,hour,hid,' + @s + ',入场总数,出场总数 from 
					(
						select a.*,b.入场总数,b.出场总数 from #t2 a LEFT join (select hour,入场总数=sum(InCount),出场总数=sum(OutCount) from #t2 GROUP by hour) b on a.hour = b. hour
					) as acs
					pivot (sum(InCount) for CCustName in(' + @s3 + ')) as privot
					group BY DTime,hour,hid,入场总数,出场总数) a 
					LEFT JOIN 
					(select hour,' + @s2 + ' from 
					(
						select a.*,b.入场总数,b.出场总数 from #t2 a LEFT join (select hour,入场总数=sum(InCount),出场总数=sum(OutCount) from #t2 GROUP by hour) b on a.hour = b. hour
					) as acs
					pivot (sum(acs.OutCount) for CCustName in(' + @s3 + ')) as privot
					group BY hour) b ON a.hour = b.hour order by hid'
				exec(@sql)
			END
			ELSE
			BEGIN
				select hour as ID,convert(varchar(11),@begindate,120) + title as 日期,(CAST([hour] AS varchar(8)) + '时') as 时间,0 as 入场总数,0 as 出场总数 from #t1 order BY hour
			END
		end
		else if @type = 1--日
		begin
			declare @i int=1,@allday int=31
			SELECT @allday = (32-DAY(@begindate+32-DAY(@begindate)))
			WHILE @i <= @allday
			BEGIN
				insert into #t1 SELECT @i, (CAST(@i AS varchar(8)) + '日')
				set @i = @i + 1
			END
			insert into #t2 SELECT ga.DTime,ga.hour,ga.hid,SUM(ga.InCount) as InCount,SUM(ga.OutCount) as OutCount,ga.CCustName FROM 
			(	select a.DTime,(CAST(a.[hour] AS varchar(8)) + '日') as [hour],a.hour as hid,isnull(a.data,0) as InCount,0 as OutCount,a.CCustName from
				(
					select convert(varchar(8),@begindate,120) + b.title as DTime,b.hour,da.data,c.CCustName from 
					(
						select count(DATEPART(dd,ta.InTime))data,DATEPART(dd,InTime) hour,ta.CardType from 
						(
							SELECT InTime,CardType FROM Park_CarIn
							UNION ALL
							SELECT InTime,CardType FROM Park_CarOut where IsOut IS NOT NULL
							UNION ALL
							SELECT InTime,CardType FROM Park_CarOut where IsOut IS NULL AND EmpName = '入口电脑开闸'
						) ta
						WHERE convert(varchar(7),ta.InTime,120) = convert(varchar(7),@begindate,120)
						group by DATEPART(dd,InTime),ta.CardType
					) da RIGHT JOIN #t1 b ON da.[hour] = b.[hour]
					LEFT JOIN Park_CardType c ON da.CardType = c.CType
				) a
				UNION ALL
				select f.DTime,(CAST(f.[hour] AS varchar(8)) + '日') as [hour],f.hour as hid,0 as InCount,isnull(f.data,0) as OutCount,f.CCustName from
				(
					select convert(varchar(8),@begindate,120) + b.title as DTime,b.hour,da.data,cd.CCustName from 
					(
						select count(DATEPART(dd,ta.OutTime))data,DATEPART(dd,OutTime) hour,ta.CardType from 
						(
							SELECT OutTime,CardType FROM Park_CarOut where IsOut IS NOT NULL
							UNION ALL
							SELECT OutTime,CardType FROM Park_CarOut where IsOut IS NULL AND EmpName = '出口电脑开闸'
						) ta
						WHERE convert(varchar(7),ta.OutTime,120) = convert(varchar(7),@begindate,120)
						group by DATEPART(dd,OutTime),ta.CardType
					) da RIGHT JOIN #t1 b ON da.[hour] = b.[hour]
					LEFT join Park_CardType cd ON da.CardType = cd.CType
				) f
			) ga group BY ga.DTime,ga.hour,ga.CCustName,ga.hid
			if exists(SELECT 1 from #t2)
			BEGIN
				Select @s=isnull(@s+',','') + 'Sum(IsNull(' + quotename(gcs.CCustName) + ',0)) as ' + quotename(gcs.CCustName + '-入') from
				#t2 gcs group by gcs.CCustName
				Select @s2=isnull(@s2+',','') + 'Sum(IsNull(' + quotename(gcs.CCustName) + ',0)) as ' + quotename(gcs.CCustName + '-出') from
				#t2 gcs group by gcs.CCustName
				Select @s3=isnull(@s3+',','')+quotename(gcs.CCustName) from
				#t2 gcs group by gcs.CCustName
				Select @ss=isnull(@ss+',','')+ quotename(gcs.CCustName + '-入') + ',' + quotename(gcs.CCustName + '-出') from
				#t2 gcs group by gcs.CCustName
				set @sql = 'select hid as ID,DTime as 日期,a.hour as 时间,' + @ss + ',入场总数,出场总数 from (select DTime,hour,hid,' + @s + ',入场总数,出场总数 from 
					(
						select a.*,b.入场总数,b.出场总数 from #t2 a LEFT join (select hour,入场总数=sum(InCount),出场总数=sum(OutCount) from #t2 GROUP by hour) b on a.hour = b. hour
					) as acs
					pivot (sum(InCount) for CCustName in(' + @s3 + ')) as privot
					group BY DTime,hour,hid,入场总数,出场总数) a 
					LEFT JOIN 
					(select hour,' + @s2 + ' from 
					(
						select a.*,b.入场总数,b.出场总数 from #t2 a LEFT join (select hour,入场总数=sum(InCount),出场总数=sum(OutCount) from #t2 GROUP by hour) b on a.hour = b. hour
					) as acs
					pivot (sum(acs.OutCount) for CCustName in(' + @s3 + ')) as privot
					group BY hour) b ON a.hour = b.hour order by hid'
				exec(@sql)
			END
			ELSE
			BEGIN
				select hour as ID,convert(varchar(7),@begindate,120) + title as 日期,(CAST([hour] AS varchar(8)) + '日') as 时间,0 as 入场总数,0 as 出场总数 from #t1 order BY hour
			END
		end
		else--月
		begin
			declare @z int=1,@allday1 int=12
			WHILE @z <= @allday1
			BEGIN
				insert into #t1 SELECT @z, (CAST(@z AS varchar(8)) + '月')
				set @z = @z + 1
			END
			insert into #t2 SELECT ga.DTime,ga.hour,ga.hid,SUM(ga.InCount) as InCount,SUM(ga.OutCount) as OutCount,ga.CCustName FROM 
			(	select a.DTime,(CAST(a.[hour] AS varchar(8)) + '月') as [hour],a.hour as hid,isnull(a.data,0) as InCount,0 as OutCount,a.CCustName from
				(
					select convert(varchar(5),@begindate,120) + b.title as DTime,b.hour,da.data,c.CCustName from 
					(
						select count(DATEPART(MM,ta.InTime))data,DATEPART(MM,InTime) hour,ta.CardType from 
						(
							SELECT InTime,CardType FROM Park_CarIn
							UNION ALL
							SELECT InTime,CardType FROM Park_CarOut where IsOut IS NOT NULL
							UNION ALL
							SELECT InTime,CardType FROM Park_CarOut where IsOut IS NULL AND EmpName = '入口电脑开闸'
						) ta
						WHERE convert(varchar(4),ta.InTime,120) = convert(varchar(4),@begindate,120)
						group by DATEPART(MM,InTime),ta.CardType
					) da RIGHT JOIN #t1 b ON da.[hour] = b.[hour]
					LEFT JOIN Park_CardType c ON da.CardType = c.CType
				) a
				UNION ALL
				select f.DTime,(CAST(f.[hour] AS varchar(8)) + '月') as [hour],f.hour as hid,0 as InCount,isnull(f.data,0) as OutCount,f.CCustName from
				(
					select convert(varchar(5),@begindate,120) + b.title as DTime,b.hour,da.data,cd.CCustName from 
					(
						select count(DATEPART(MM,ta.OutTime))data,DATEPART(MM,OutTime) hour,ta.CardType from 
						(
							SELECT OutTime,CardType FROM Park_CarOut where IsOut IS NOT NULL
							UNION ALL
							SELECT OutTime,CardType FROM Park_CarOut where IsOut IS NULL AND EmpName = '出口电脑开闸'
						) ta
						WHERE convert(varchar(4),ta.OutTime,120) = convert(varchar(4),@begindate,120)
						group by DATEPART(MM,OutTime),ta.CardType
					) da RIGHT JOIN #t1 b ON da.[hour] = b.[hour]
					LEFT join Park_CardType cd ON da.CardType = cd.CType
				) f
			) ga group BY ga.DTime,ga.hour,ga.CCustName,ga.hid

			if exists(SELECT 1 from #t2)
			BEGIN
				Select @s=isnull(@s+',','') + 'Sum(IsNull(' + quotename(gcs.CCustName) + ',0)) as ' + quotename(gcs.CCustName + '-入') from
				#t2 gcs group by gcs.CCustName
				Select @s2=isnull(@s2+',','') + 'Sum(IsNull(' + quotename(gcs.CCustName) + ',0)) as ' + quotename(gcs.CCustName + '-出') from
				#t2 gcs group by gcs.CCustName
				Select @s3=isnull(@s3+',','')+quotename(gcs.CCustName) from
				#t2 gcs group by gcs.CCustName
				Select @ss=isnull(@ss+',','')+ quotename(gcs.CCustName + '-入') + ',' + quotename(gcs.CCustName + '-出') from
				#t2 gcs group by gcs.CCustName
				
				set @sql = 'select hid as ID,DTime as 日期,a.hour as 时间,' + @ss + ',入场总数,出场总数 from (select DTime,hour,hid,' + @s + ',入场总数,出场总数 from 
					(
						select a.*,b.入场总数,b.出场总数 from #t2 a LEFT join (select hour,入场总数=sum(InCount),出场总数=sum(OutCount) from #t2 GROUP by hour) b on a.hour = b. hour
					) as acs
					pivot (sum(InCount) for CCustName in(' + @s3 + ')) as privot
					group BY DTime,hour,hid,入场总数,出场总数) a 
					LEFT JOIN 
					(select hour,' + @s2 + ' from 
					(
						select a.*,b.入场总数,b.出场总数 from #t2 a LEFT join (select hour,入场总数=sum(InCount),出场总数=sum(OutCount) from #t2 GROUP by hour) b on a.hour = b. hour
					) as acs
					pivot (sum(acs.OutCount) for CCustName in(' + @s3 + ')) as privot
					group BY hour) b ON a.hour = b.hour order by hid'
				exec(@sql)
			END
			ELSE
			BEGIN
				select hour as ID,convert(varchar(5),@begindate,120) + title as 日期,(CAST([hour] AS varchar(8)) + '月') as 时间,0 as 入场总数,0 as 出场总数 from #t1 order BY hour
			END
		end
	set NOCOUNT OFF
go


-- ============================================= 
-- 程序編寫： <谭飞> 
-- 建立日期： <2015-12-26>  
-- 功能說明： <車場-車位使用率條型圖>
-- 程序修改： <可選>  
-- 修改日期： <可選>  
-- 修改說明： <可選，每修改一次，請重復本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Park_ParkingusageHour') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Park_ParkingusageHour
GO
create PROCEDURE [dbo].[Sp_Park_ParkingusageHour]
	@Flag int,					--(0時，1日，2年)	
	@date datetime,
	@allnum numeric(10,2) output,
	@vipnum numeric(10,2) output,
	@tmpnum numeric(10,2) output
AS
	set NOCOUNT on
	Declare @InID int,@datebegin datetime,@dateend datetime,@C_PlaceNum int,@C_VIPCarNum int,@C_TmpCarNum int
	SELECT TOP 1  @C_PlaceNum = ISNULL(PlaceNum,0),@C_VIPCarNum = ISNULL(VIPCarNum,0),@C_TmpCarNum = (ISNULL(PlaceNum,0) - ISNULL(VIPCarNum,0)) FROM Park_LocalSet
	SELECT @datebegin = dateadd(ms,0,DATEADD(dd, DATEDIFF(dd,0,@date), 0)),@dateend = dateadd(ms,-3,DATEADD(dd, DATEDIFF(dd,-1,@date), 0))	
		if exists(select 1 from tempdb..sysobjects where id=object_id('tempdb..#a'))
			DROP TABLE #a
		create table #a(outid int,intime datetime,outtime datetime,[0] numeric(10,2),[1] numeric(10,2),[2] numeric(10,2),[3] numeric(10,2),[4] numeric(10,2),[5] numeric(10,2),[6] numeric(10,2),[7] numeric(10,2),[8] numeric(10,2),[9] numeric(10,2),[10] numeric(10,2),[11] numeric(10,2),[12] numeric(10,2),[13] numeric(10,2),[14] numeric(10,2),[15] numeric(10,2),[16] numeric(10,2),[17] numeric(10,2),[18] numeric(10,2),[19] numeric(10,2),[20] numeric(10,2),[21] numeric(10,2),[22] numeric(10,2),[23] numeric(10,2))
		insert into #a(outid,intime,outtime) select id,intime,outtime from dbo.Park_CarOut where outtime between @datebegin and @dateend
		DECLARE MyCursor CURSOR    
		FOR select outid,intime,outtime from #a  	
		--打开一个游标    
		OPEN MyCursor
		DECLARE @outid int ,@intime datetime ,@outtime datetime
		FETCH NEXT FROM  MyCursor INTO @outid, @intime,@outtime
		WHILE @@FETCH_STATUS =0
			BEGIN
				DECLARE @minutes int,@hourss int=0,@SHour int,@SMinute int
				IF DATEDIFF(day, @intime, @outtime) <= 0
				BEGIN
					select @minutes=DATEDIFF(minute, @intime,@outtime)
					SELECT @SHour = datename(Hour, @intime)
					SELECT @SMinute = datename(MI, @intime)
				end
				else
				begin
					select @minutes=DATEDIFF(minute, @datebegin,@outtime)
					SELECT @SHour = datename(Hour, @datebegin)
					SELECT @SMinute = datename(MI, @datebegin)
				end
				
				WHILE  @hourss < 24
				BEGIN
					--计算
					declare @tmpMi int
					declare @tmpSQL varchar(300)
					SET @tmpSQL = 'Update #a set [' + cast(@hourss as varchar(10)) + ']=0 where outid=' + cast(@outid as varchar(10))
					IF @SHour = @hourss and @minutes > 0
					begin
						IF @SMinute > 0
						begin
							if (@minutes + @SMinute) > 59
							begin
								set @minutes = @minutes - (60 - @SMinute)
								set @tmpMi = (60 - @SMinute)
							end
							else
							begin
								set @tmpMi = @minutes
								set @minutes = 0
							end
							set @SMinute = 0
						end
						else
						begin
							if @minutes >= 60
							begin
								set @minutes = @minutes - 60
								set @tmpMi = 60
							end
							else
							begin
								set @tmpMi = @minutes
								set @minutes = 0
							end
						end
						SET @tmpSQL = 'Update #a set [' + cast(@hourss as varchar(10)) + ']=' + cast(@tmpMi as varchar(10)) + ' where outid=' + cast(@outid as varchar(10))
						set @SHour = @SHour + 1
					end
					exec(@tmpSQL)
					set @hourss=@hourss+1
				END
				FETCH NEXT FROM  MyCursor INTO  @outid, @intime,@outtime
			END    
			--关闭游标
		CLOSE MyCursor
		--释放资源
		DEALLOCATE MyCursor
		IF @Flag = 0
		begin
			IF @C_VIPCarNum <> 0 and @C_TmpCarNum <> 0
			begin
				select '全部车位' as ParkSpace,cast((((isnull(SUM([0]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [0],cast((((isnull(SUM([1]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [1],cast((((isnull(SUM([2]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [2],cast((((isnull(SUM([3]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [3],cast((((isnull(SUM([4]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [4],cast((((isnull(SUM([5]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [5],cast((((isnull(SUM([6]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [6],cast((((isnull(SUM([7]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [7],cast((((isnull(SUM([8]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [8],cast((((isnull(SUM([9]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [9],cast((((isnull(SUM([10]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [10],cast((((isnull(SUM([11]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [11],cast((((isnull(SUM([12]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [12],cast((((isnull(SUM([13]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [13],cast((((isnull(SUM([14]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [14],cast((((isnull(SUM([15]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [15],cast((((isnull(SUM([16]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [16],cast((((isnull(SUM([17]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [17],cast((((isnull(SUM([18]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [18],cast((((isnull(SUM([19]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [19],cast((((isnull(SUM([20]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [20],cast((((isnull(SUM([21]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [21],cast((((isnull(SUM([22]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [22],cast((((isnull(SUM([23]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [23] from #a
				union ALL
				select '固定车位' as ParkSpace,cast((((isnull(SUM([0]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [0],cast((((isnull(SUM([1]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [1],cast((((isnull(SUM([2]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [2],cast((((isnull(SUM([3]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [3],cast((((isnull(SUM([4]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [4],cast((((isnull(SUM([5]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [5],cast((((isnull(SUM([6]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [6],cast((((isnull(SUM([7]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [7],cast((((isnull(SUM([8]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [8],cast((((isnull(SUM([9]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [9],cast((((isnull(SUM([10]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [10],cast((((isnull(SUM([11]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [11],cast((((isnull(SUM([12]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [12],cast((((isnull(SUM([13]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [13],cast((((isnull(SUM([14]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [14],cast((((isnull(SUM([15]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [15],cast((((isnull(SUM([16]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [16],cast((((isnull(SUM([17]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [17],cast((((isnull(SUM([18]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [18],cast((((isnull(SUM([19]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [19],cast((((isnull(SUM([20]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [20],cast((((isnull(SUM([21]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [21],cast((((isnull(SUM([22]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [22],cast((((isnull(SUM([23]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [23] from #a
				union ALL
				select '临时车位' as ParkSpace,cast((((isnull(SUM([0]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [0],cast((((isnull(SUM([1]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [1],cast((((isnull(SUM([2]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [2],cast((((isnull(SUM([3]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [3],cast((((isnull(SUM([4]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [4],cast((((isnull(SUM([5]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [5],cast((((isnull(SUM([6]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [6],cast((((isnull(SUM([7]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [7],cast((((isnull(SUM([8]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [8],cast((((isnull(SUM([9]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [9],cast((((isnull(SUM([10]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [10],cast((((isnull(SUM([11]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [11],cast((((isnull(SUM([12]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [12],cast((((isnull(SUM([13]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [13],cast((((isnull(SUM([14]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [14],cast((((isnull(SUM([15]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [15],cast((((isnull(SUM([16]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [16],cast((((isnull(SUM([17]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [17],cast((((isnull(SUM([18]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [18],cast((((isnull(SUM([19]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [19],cast((((isnull(SUM([20]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [20],cast((((isnull(SUM([21]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [21],cast((((isnull(SUM([22]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [22],cast((((isnull(SUM([23]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [23] from #a
			end
			ELSE IF @C_VIPCarNum = 0 and @C_TmpCarNum <> 0
			begin
				select '全部车位' as ParkSpace,cast((((isnull(SUM([0]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [0],cast((((isnull(SUM([1]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [1],cast((((isnull(SUM([2]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [2],cast((((isnull(SUM([3]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [3],cast((((isnull(SUM([4]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [4],cast((((isnull(SUM([5]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [5],cast((((isnull(SUM([6]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [6],cast((((isnull(SUM([7]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [7],cast((((isnull(SUM([8]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [8],cast((((isnull(SUM([9]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [9],cast((((isnull(SUM([10]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [10],cast((((isnull(SUM([11]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [11],cast((((isnull(SUM([12]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [12],cast((((isnull(SUM([13]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [13],cast((((isnull(SUM([14]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [14],cast((((isnull(SUM([15]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [15],cast((((isnull(SUM([16]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [16],cast((((isnull(SUM([17]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [17],cast((((isnull(SUM([18]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [18],cast((((isnull(SUM([19]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [19],cast((((isnull(SUM([20]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [20],cast((((isnull(SUM([21]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [21],cast((((isnull(SUM([22]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [22],cast((((isnull(SUM([23]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [23] from #a
				union ALL
				select '临时车位' as ParkSpace,cast((((isnull(SUM([0]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [0],cast((((isnull(SUM([1]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [1],cast((((isnull(SUM([2]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [2],cast((((isnull(SUM([3]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [3],cast((((isnull(SUM([4]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [4],cast((((isnull(SUM([5]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [5],cast((((isnull(SUM([6]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [6],cast((((isnull(SUM([7]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [7],cast((((isnull(SUM([8]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [8],cast((((isnull(SUM([9]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [9],cast((((isnull(SUM([10]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [10],cast((((isnull(SUM([11]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [11],cast((((isnull(SUM([12]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [12],cast((((isnull(SUM([13]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [13],cast((((isnull(SUM([14]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [14],cast((((isnull(SUM([15]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [15],cast((((isnull(SUM([16]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [16],cast((((isnull(SUM([17]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [17],cast((((isnull(SUM([18]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [18],cast((((isnull(SUM([19]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [19],cast((((isnull(SUM([20]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [20],cast((((isnull(SUM([21]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [21],cast((((isnull(SUM([22]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [22],cast((((isnull(SUM([23]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [23] from #a
			end
			ELSE IF @C_VIPCarNum <> 0 and @C_TmpCarNum = 0
			begin
				select '全部车位' as ParkSpace,cast((((isnull(SUM([0]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [0],cast((((isnull(SUM([1]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [1],cast((((isnull(SUM([2]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [2],cast((((isnull(SUM([3]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [3],cast((((isnull(SUM([4]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [4],cast((((isnull(SUM([5]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [5],cast((((isnull(SUM([6]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [6],cast((((isnull(SUM([7]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [7],cast((((isnull(SUM([8]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [8],cast((((isnull(SUM([9]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [9],cast((((isnull(SUM([10]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [10],cast((((isnull(SUM([11]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [11],cast((((isnull(SUM([12]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [12],cast((((isnull(SUM([13]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [13],cast((((isnull(SUM([14]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [14],cast((((isnull(SUM([15]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [15],cast((((isnull(SUM([16]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [16],cast((((isnull(SUM([17]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [17],cast((((isnull(SUM([18]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [18],cast((((isnull(SUM([19]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [19],cast((((isnull(SUM([20]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [20],cast((((isnull(SUM([21]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [21],cast((((isnull(SUM([22]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [22],cast((((isnull(SUM([23]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [23] from #a
				union ALL
				select '固定车位' as ParkSpace,cast((((isnull(SUM([0]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [0],cast((((isnull(SUM([1]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [1],cast((((isnull(SUM([2]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [2],cast((((isnull(SUM([3]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [3],cast((((isnull(SUM([4]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [4],cast((((isnull(SUM([5]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [5],cast((((isnull(SUM([6]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [6],cast((((isnull(SUM([7]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [7],cast((((isnull(SUM([8]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [8],cast((((isnull(SUM([9]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [9],cast((((isnull(SUM([10]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [10],cast((((isnull(SUM([11]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [11],cast((((isnull(SUM([12]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [12],cast((((isnull(SUM([13]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [13],cast((((isnull(SUM([14]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [14],cast((((isnull(SUM([15]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [15],cast((((isnull(SUM([16]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [16],cast((((isnull(SUM([17]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [17],cast((((isnull(SUM([18]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [18],cast((((isnull(SUM([19]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [19],cast((((isnull(SUM([20]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [20],cast((((isnull(SUM([21]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [21],cast((((isnull(SUM([22]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [22],cast((((isnull(SUM([23]),0)/60)/@C_VIPCarNum)*100) as numeric(10,2)) as [23] from #a
				union ALL
				select '临时车位' as ParkSpace,cast((((isnull(SUM([0]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [0],cast((((isnull(SUM([1]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [1],cast((((isnull(SUM([2]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [2],cast((((isnull(SUM([3]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [3],cast((((isnull(SUM([4]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [4],cast((((isnull(SUM([5]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [5],cast((((isnull(SUM([6]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [6],cast((((isnull(SUM([7]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [7],cast((((isnull(SUM([8]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [8],cast((((isnull(SUM([9]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [9],cast((((isnull(SUM([10]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [10],cast((((isnull(SUM([11]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [11],cast((((isnull(SUM([12]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [12],cast((((isnull(SUM([13]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [13],cast((((isnull(SUM([14]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [14],cast((((isnull(SUM([15]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [15],cast((((isnull(SUM([16]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [16],cast((((isnull(SUM([17]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [17],cast((((isnull(SUM([18]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [18],cast((((isnull(SUM([19]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [19],cast((((isnull(SUM([20]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [20],cast((((isnull(SUM([21]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [21],cast((((isnull(SUM([22]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [22],cast((((isnull(SUM([23]),0)/60)/@C_TmpCarNum)*100) as numeric(10,2)) as [23] from #a
			end
			ELSE
			begin
				select '全部车位' as ParkSpace,cast((((isnull(SUM([0]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [0],cast((((isnull(SUM([1]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [1],cast((((isnull(SUM([2]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [2],cast((((isnull(SUM([3]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [3],cast((((isnull(SUM([4]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [4],cast((((isnull(SUM([5]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [5],cast((((isnull(SUM([6]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [6],cast((((isnull(SUM([7]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [7],cast((((isnull(SUM([8]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [8],cast((((isnull(SUM([9]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [9],cast((((isnull(SUM([10]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [10],cast((((isnull(SUM([11]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [11],cast((((isnull(SUM([12]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [12],cast((((isnull(SUM([13]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [13],cast((((isnull(SUM([14]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [14],cast((((isnull(SUM([15]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [15],cast((((isnull(SUM([16]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [16],cast((((isnull(SUM([17]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [17],cast((((isnull(SUM([18]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [18],cast((((isnull(SUM([19]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [19],cast((((isnull(SUM([20]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [20],cast((((isnull(SUM([21]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [21],cast((((isnull(SUM([22]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [22],cast((((isnull(SUM([23]),0)/60)/@C_PlaceNum)*100) as numeric(10,2)) as [23] from #a
			end
		end
		else IF @Flag = 1
		begin
			IF @C_VIPCarNum <> 0 and @C_TmpCarNum <> 0
			begin
				select @allnum = ((((cast((((SUM([0])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([1])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([2])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([3])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([4])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([5])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([6])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([7])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([8])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([9])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([10])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([11])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([12])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([13])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([14])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([15])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([16])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([17])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([18])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([19])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([20])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([21])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([22])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([23])/60)/@C_PlaceNum)*100) as numeric(10,2)))/@C_PlaceNum)/24)*100) from #a
				select @vipnum = ((((cast((((SUM([0])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([1])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([2])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([3])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([4])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([5])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([6])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([7])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([8])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([9])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([10])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([11])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([12])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([13])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([14])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([15])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([16])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([17])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([18])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([19])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([20])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([21])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([22])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([23])/60)/@C_VIPCarNum)*100) as numeric(10,2)))/@C_VIPCarNum)/24)*100) from #a
				select @tmpnum = ((((cast((((SUM([0])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([1])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([2])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([3])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([4])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([5])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([6])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([7])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([8])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([9])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([10])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([11])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([12])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([13])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([14])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([15])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([16])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([17])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([18])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([19])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([20])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([21])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([22])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([23])/60)/@C_TmpCarNum)*100) as numeric(10,2)))/@C_TmpCarNum)/24)*100) from #a
			end
			ELSE IF @C_VIPCarNum = 0 and @C_TmpCarNum <> 0
			begin
				select @allnum = ((((cast((((SUM([0])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([1])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([2])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([3])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([4])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([5])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([6])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([7])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([8])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([9])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([10])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([11])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([12])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([13])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([14])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([15])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([16])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([17])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([18])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([19])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([20])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([21])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([22])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([23])/60)/@C_PlaceNum)*100) as numeric(10,2)))/@C_PlaceNum)/24)*100) from #a
				select @vipnum = 0
				select @tmpnum = ((((cast((((SUM([0])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([1])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([2])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([3])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([4])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([5])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([6])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([7])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([8])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([9])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([10])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([11])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([12])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([13])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([14])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([15])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([16])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([17])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([18])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([19])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([20])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([21])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([22])/60)/@C_TmpCarNum)*100) as numeric(10,2)) + cast((((SUM([23])/60)/@C_TmpCarNum)*100) as numeric(10,2)))/@C_TmpCarNum)/24)*100) from #a
			end
			ELSE IF @C_VIPCarNum <> 0 and @C_TmpCarNum = 0
			begin
				select @allnum = ((((cast((((SUM([0])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([1])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([2])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([3])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([4])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([5])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([6])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([7])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([8])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([9])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([10])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([11])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([12])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([13])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([14])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([15])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([16])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([17])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([18])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([19])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([20])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([21])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([22])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([23])/60)/@C_PlaceNum)*100) as numeric(10,2)))/@C_PlaceNum)/24)*100) from #a
				select @vipnum = ((((cast((((SUM([0])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([1])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([2])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([3])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([4])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([5])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([6])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([7])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([8])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([9])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([10])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([11])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([12])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([13])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([14])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([15])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([16])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([17])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([18])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([19])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([20])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([21])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([22])/60)/@C_VIPCarNum)*100) as numeric(10,2)) + cast((((SUM([23])/60)/@C_VIPCarNum)*100) as numeric(10,2)))/@C_VIPCarNum)/24)*100) from #a
				select @tmpnum = 0
			end
			ELSE
			begin
				select @allnum = ((((cast((((SUM([0])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([1])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([2])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([3])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([4])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([5])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([6])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([7])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([8])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([9])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([10])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([11])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([12])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([13])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([14])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([15])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([16])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([17])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([18])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([19])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([20])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([21])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([22])/60)/@C_PlaceNum)*100) as numeric(10,2)) + cast((((SUM([23])/60)/@C_PlaceNum)*100) as numeric(10,2)))/@C_PlaceNum)/24)*100) from #a
				select @vipnum = 0
				select @tmpnum = 0
			end
		end
	set NOCOUNT OFF

go
-- ============================================= 
-- 程序編寫： <谭飞> 
-- 建立日期： <2015-12-26>  
-- 功能說明： <訪客-控制器設置信息視圖>
-- 程序修改： <可選>  
-- 修改日期： <可選>  
-- 修改說明： <可選，每修改一次，請重復本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Park_Parkingusage') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Park_Parkingusage
GO
create PROCEDURE [dbo].[Sp_Park_Parkingusage]
	@Flag int,					--(0時，1日，2年)	
	@date datetime
AS
	set NOCOUNT on
	Declare @InID int,@datebegin datetime,@dateend datetime,@C_PlaceNum int,@C_VIPCarNum int,@C_TmpCarNum int
	SELECT TOP 1 @C_PlaceNum = PlaceNum,@C_VIPCarNum = VIPCarNum,@C_TmpCarNum = (PlaceNum - VIPCarNum) FROM [Park_LocalSet]
	if 	@Flag = 0--時
	begin	
		exec Sp_Park_ParkingusageHour 0,@date,null,NULL,null
	end
	else IF @Flag = 1--日
	begin
		declare @dayall numeric(10,2),@dayvip numeric(10,2),@daytmp numeric(10,2),@monthcount int,@tmpSql varchar(1000),@todayT int = 1,@tmpInsert varchar(1000),@todaytime datetime
		declare @tmpInsertB varchar(1000),@tmpInsertC varchar(1000)
		SELECT @todaytime = CONVERT(datetime,CONVERT(char(8),@date,120)+'1')
		select @monthcount = Day(DATEADD(month, DATEDIFF(month, '20141231', @date), '20141231'))
		if exists(select 1 from tempdb..sysobjects where id=object_id('tempdb..#month_T'))
			DROP TABLE #month_T
		create table #month_T(ParkSpace varchar(20))
		set @tmpSql = 'alter table #month_T add '
		set @tmpInsert = 'insert into #month_T values(''全部车位'','
		set @tmpInsertB = 'insert into #month_T values(''固定车位'','
		set @tmpInsertC = 'insert into #month_T values(''临时车位'','
		WHILE @todayT <= @monthcount
		begin
			exec Sp_Park_ParkingusageHour 1,@todaytime,@dayall output,@dayvip output,@daytmp output
			IF @todayT < @monthcount
			BEGIN
				set @tmpSql = @tmpSql + '[' + cast(@todayT AS varchar(10)) + '] numeric(10,2),'
				set @tmpInsert = @tmpInsert + cast(isnull(@dayall,0) AS varchar(20)) + ','
				set @tmpInsertB = @tmpInsertB + cast(isnull(@dayvip,0) AS varchar(20)) + ','
				set @tmpInsertC = @tmpInsertC + cast(isnull(@daytmp,0) AS varchar(20)) + ','
			end
			else
			begin
				set @tmpSql = @tmpSql + '[' + cast(@todayT AS varchar(10)) + '] numeric(10,2)'
				set @tmpInsert = @tmpInsert + cast(isnull(@dayall,0) AS varchar(20)) + ')'
				set @tmpInsertB = @tmpInsertB + cast(isnull(@dayvip,0) AS varchar(20)) + ')'
				set @tmpInsertC = @tmpInsertC + cast(isnull(@daytmp,0) AS varchar(20)) + ')'
			end
			set @todayT = @todayT + 1
			set @todaytime = DATEADD(d,1,@todaytime)
		end
		exec(@tmpSql)
		exec(@tmpInsert)
		exec(@tmpInsertB)
		exec(@tmpInsertC)
		EXEC('select * from #month_T')
	end
	
	
	set NOCOUNT off
go

-- ============================================= 
-- 程序編寫： <谭飞> 
-- 建立日期： <2016-05-09>  
-- 功能說明： <系统-清理业务数据>
-- 程序修改： <可選>  
-- 修改日期： <可選>  
-- 修改說明： <可選，每修改一次，請重復本行及上述二行一次>
-- =============================================
IF OBJECT_ID ('dbo.Sp_Init_All') IS NOT NULL
	DROP PROCEDURE dbo.Sp_Init_All
GO
	CREATE Procedure [dbo].[Sp_Init_All]
	@iType tinyint=0--0-删除全部;1-清除卡片资料;2-清除记录数据
	as 
	if @iType=1 or @iType=0 --清除卡片资料
		Begin
			--车场
			Truncate TABLE [dbo].[Ykt_CardIssue]		--卡信息
			Truncate TABLE [dbo].[YKT_CardIssuePark]	--车场卡资料
			Truncate TABLE [dbo].[YKT_CardRsMoney]		--卡充值信息
			--门禁
			Truncate TABLE [dbo].[YKT_CardIssueDoor]	--门禁卡资料
			Truncate TABLE [dbo].[Door_Log]				--门禁操作日志
			Truncate TABLE [dbo].[YKT_CardDelyInfo]		--卡延期信息
			Truncate TABLE [dbo].[Door_FingerprintInfo] --门禁指纹信息
			Truncate TABLE [dbo].[Door_Fingerprints]	--门禁指纹数据
			--访客
			Truncate TABLE [dbo].[Guest_Reg]			--访客登记信息
			Truncate TABLE [dbo].[Guest_Order]
			--人事信息
			UpDate	[dbo].[Per_Persons] Set [status]=0
		end
	if @iType=2 or @iType=0 --清除记录数据
		Begin
			--车场
			Truncate TABLE [dbo].[ParK_CarIn]		--入场纪录
			Truncate TABLE [dbo].[Park_CarNoInfo]	--车牌信息记录
			Truncate TABLE [dbo].[ParK_CarOut]		--出场纪录
			Truncate TABLE [dbo].[ParK_CentralCharge]--中央缴费记录
			Truncate TABLE [dbo].[ParK_TunkaInfo] --无效吞卡信息表
			Truncate TABLE [dbo].[ParK_SumUser]   --换班记录表
			Truncate TABLE [dbo].[Park_CarNoInfo] --多次出入限制收费表（用于深圳住宅类）
			Truncate TABLE [dbo].[ParK_Preassign] --入口预置
			Truncate TABLE [dbo].[Park_DisDetail] --车场打折纪录
			--门禁
			Truncate TABLE [dbo].[Door_OpenDoorRecord]--门禁刷卡记录1
			Truncate TABLE [dbo].[Door_OpenDoorRecordTwo]--门禁刷卡记录2
			--访客
			Truncate TABLE [dbo].[Guest_TwoCodeRecord]	--二维码扫码记录
		end
	if 	@iType=3 --清除已注销卡片资料
	  Begin
	        delete from [dbo].[Ykt_CardIssuePark]  where YKTID in(select YKTID from [dbo].[Ykt_CardIssue] where States=6)
	        delete from [dbo].[YKT_CardIssueDoor]  where YKTID in(select YKTID from [dbo].[Ykt_CardIssue] where States=6)
	        delete from [dbo].[Ykt_CardIssue] where States=6
	  end
		
GO
-- |**************************************************************************************|
-- |*******************************| 新增存储过程(end) |**********************************|
-- |**************************************************************************************|





-- |**************************************************************************************|
-- |*******************************| 初始数据处理(begin) |********************************|
-- |**************************************************************************************|

if not exists(select 1 from Sys_LoginUser)
begin
	insert into [Sys_LoginUser] values('','操作组','','',REPLICATE('1', 20),REPLICATE('1', 2000),0,0,0,1,'',1)
	insert into [Sys_LoginUser] values('','管理组','','',REPLICATE('1', 20),REPLICATE('1', 2000),1,0,0,1,'',1)
	insert into [Sys_LoginUser] values('','财务组','','',REPLICATE('1', 20),REPLICATE('1', 2000),2,0,0,1,'',1)
	insert into [Sys_LoginUser] values('Admin','系统管理员','P8HXptpYahQ=','',REPLICATE('1', 20),REPLICATE('1', 2000),100,0,0,0,'系统管理员,必须使用安全狗',0)
end
GO

--TRUNCATE TABLE Sys_Function
if not exists(select 1 from Sys_Function)
begin
	--管理中心
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'A', N'管理中心', 0, 0, 0, N'0',N'11111111')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'A01', N'系统管理', 0, 1, 0, N'0',N'11111111')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'A0101', N'系统设置', 0, 2, 1, N'0',N'11111111')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'A0102', N'修改密码', 0, 3, 1, N'0',N'11111111')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'A0103', N'用户权限', 0, 5, 1, N'0',N'11111111')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'A0104', N'数据备份', 0, 6, 1, N'0',N'11111111')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'A0105', N'数据还原', 0, 7, 1, N'0',N'11111111')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'A0106', N'初始化', 0, 8, 1, N'0',N'11111111')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'A0107', N'清理数据', 0, 9, 1, N'0',N'11111111')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'A0108', N'操作日志', 0, 10, 1, N'0',N'11111111')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'A0109', N'运维管理', 0, 11, 1, N'0',N'11111111')
	
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'A02', N'人事管理', 0, 21, 0, N'0',N'11111111')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'A0201', N'部门管理', 0, 22, 1, N'0',N'11111111')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'A0202', N'个人档案', 0, 23, 1, N'0',N'11111111')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'A0203', N'学生档案', 0, 24, 1, N'0',N'11111111')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'A0204', N'家庭组设置', 0, 25, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'A0205', N'单位管理', 0, 26, 1, N'0',N'11111111')
	
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'A03', N'设备检测', 0, 30, 0, N'0',N'00100000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'A0301', N'设备状态', 0, 31, 1, N'0',N'00100000')
	
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B', N'账户管理', 0, 40, 0, N'0',N'11111111')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B01', N'账户管理', 0, 41, 0, N'0',N'11111111')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B0101', N'发卡/改写', 0, 42, 1, N'0',N'11111111')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B0102', N'指纹发卡', 0, 43, 1, N'0',N'11111111')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B0103', N'纯车牌发行', 0, 44, 1, N'0',N'01000000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B0104', N'卡片初始化', 0, 45, 1, N'0',N'11111111')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B0105', N'一体式脱机发卡', 0, 46, 1, N'0',N'00100000')
	
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B02', N'账户维护', 0, 50, 0, N'0',N'11111111')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B0201', N'有卡延期', 0, 51, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B0202', N'无卡延期', 0, 52, 1, N'0',N'01000000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B0203', N'卡片挂失', 0, 53, 1, N'0',N'11111111')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B0204', N'卡片解挂', 0, 54, 1, N'0',N'11111111')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B0205', N'无卡解挂', 0, 55, 1, N'0',N'11111111')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B0206', N'卡片退卡', 0, 56, 1, N'0',N'11111111')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B0207', N'无卡退卡', 0, 57, 1, N'0',N'11111111')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B0208', N'卡片延期', 0, 58, 1, N'0',N'11111111')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B0209', N'消费充值退款', 0, 59, 1, N'0',N'00010000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B0210', N'车场卡批量延期', 0, 74, 1, N'0',N'01000000')
	
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B03', N'扩展功能', 0, 70, 0, N'0',N'11111111')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B0301', N'车场卡批量发行', 0, 71, 1, N'0',N'01000000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B0302', N'门禁卡读头发行', 0, 72, 1, N'0',N'00100000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B0303', N'制作系统卡', 0, 73, 1, N'0',N'01000000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B0308', N'车场卡批量延期', 0, 74, 1, N'0',N'01000000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B0309', N'第三方账号管理', 0, 75, 1, N'0',N'00010000')
	
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B04', N'报表查询', 0, 80, 0, N'0',N'11111111')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B0401', N'车场发行报表', 0, 81, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B0402', N'卡近况报表', 0, 82, 1, N'0',N'11111111')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B0403', N'固定卡资料报表', 0, 83, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B0404', N'月租明细报表', 0, 84, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B0405', N'卡操作报表', 0, 85, 1, N'0',N'11111111')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B0406', N'金额明细报表', 0, 86, 1, N'0',N'11111111')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B0407', N'金额汇总报表', 0, 87, 1, N'0',N'11111111')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B0410', N'卡数量统计表', 0, 88, 1, N'0',N'11111111')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B0411', N'充值分月明细', 0, 89, 1, N'0',N'11111111')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B0412', N'卡信息查询', 0, 90, 1, N'0',N'11111111')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B0413', N'门禁脱机卡报表', 0, 91, 1, N'0',N'00100000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B0414', N'脱机卡操作报表', 0, 92, 1, N'0',N'00100000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'B0415', N'车场金额日月报表', 0, 93, 1, N'0',N'01000000')
	--车场
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C', N'车场系统', 0, 100, 0, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C01', N'系统管理', 0, 101, 0, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0101', N'车场系统设置', 0, 102, 1, N'0',N'01000000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0102', N'岗亭设置', 0, 103, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0103', N'设备参数设置', 0, 104, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0104', N'收费标准设置', 0, 105, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0105', N'超时收费设置', 0, 106, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0106', N'打折模式设置', 0, 107, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0107', N'打折机设置', 0, 108, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0108', N'长期滞留车辆', 0, 109, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0109', N'收费测试', 0, 110, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0110', N'其他设置', 0, 111, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0111', N'车场权限级别', 0, 112, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0112', N'远程开闸', 0, 113, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0113', N'控制器设置', 0, 114, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0114', N'卡号查询', 0, 115, 1, N'0',N'01000000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0113', N'设备异常登记', 0, 114, 1, N'0',N'01000000')
	
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C04', N'报表查询', 0, 120, 0, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0401', N'出入明细查询', 0, 121, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0402', N'场内车辆查询', 0, 122, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0403', N'免费车辆查询', 0, 123, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0404', N'非法开闸查询', 0, 124, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0405', N'交班记录查询', 0, 125, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0406', N'异常入场查询', 0, 126, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0407', N'异常出场查询', 0, 127, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0408', N'收费明细查询', 0, 128, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0409', N'收费统计报表', 0, 129, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0410', N'打折明细报表', 0, 130, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0411', N'车位使用率', 0, 131, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0412', N'车流量统计', 0, 132, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0413', N'出场放弃数据明细', 0, 133, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0414', N'归属地车流量统计', 0, 134, 1, N'0',N'01000000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0415', N'家庭组明细查询', 0, 135, 1, N'0',N'01000000')
	
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C05', N'岗亭客户端', 0, 140, 0, N'0',N'01000000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0501', N'控制器设置', 0, 141, 1, N'0',N'01000000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0502', N'控制器状态', 0, 142, 1, N'0',N'01000000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0503', N'脱机数据提取', 0, 143, 1, N'0',N'01000000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0505', N'卡片检测', 0, 144, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0506', N'收费明细查询', 0, 145, 1, N'0',N'01000000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0507', N'出入明细查询', 0, 146, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0508', N'场内车辆查询', 0, 147, 1, N'0',N'01000000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0509', N'免费车辆查询', 0, 148, 1, N'0',N'01000000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0510', N'异常开闸查询', 0, 149, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0511', N'车牌补录', 0, 150, 1, N'0',N'01000000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0512', N'异常出场查询', 0, 151, 1, N'0',N'01000000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0513', N'异常入场查询', 0, 152, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0514', N'交班记录查询', 0, 153, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0515', N'手工入场', 0, 154, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0516', N'手工放行', 0, 155, 1, N'0',N'01000000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0517', N'入贵宾车', 0, 156, 1, N'0',N'01000000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0518', N'出贵宾车', 0, 157, 1, N'0',N'01000000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0519', N'地感复位', 0, 158, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0520', N'快速换班', 0, 159, 1, N'0',N'01000000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0521', N'检查故障', 0, 160, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0522', N'车牌补录', 0, 161, 1, N'0',N'01000000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0523', N'卡号查询', 0, 162, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0524', N'高峰模式', 0, 163, 1, N'0',N'01000000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0525', N'进出管控', 0, 164, 1, N'0',N'01000000')
	INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'C0526', N'人事电话薄', 0, 165, 1, N'0',N'01000000')
	--门禁系统
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'D', N'门禁系统', 0, 170, 0, N'0',N'00100000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'D01', N'设备管理', 0, 171, 0, N'0',N'00100000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'D0101', N'区域管理', 0, 172, 1, N'0',N'00100000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'D0102', N'控制器管理', 0, 173, 1, N'0',N'00100000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'D0103', N'门管理', 0, 174, 1, N'0',N'00100000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'D0104', N'用户权限模板管理', 0, 175, 1, N'0',N'00100000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'D0106', N'指纹读卡机管理', 0, 176, 1, N'0',N'00100000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'D0107', N'用户权限管理', 0, 177, 1, N'0',N'00100000')
	
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'D02', N'门禁设置', 0, 180, 0, N'0',N'00100000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'D0201', N'定时任务', 0, 181, 1, N'0',N'00100000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'D0202', N'首卡开门', 0, 182, 1, N'0',N'00100000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'D0203', N'多卡开门', 0, 183, 1, N'0',N'00100000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'D0205', N'反潜与互锁', 0, 184, 1, N'0',N'00100000')
	
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'D03', N'时段计划', 0, 190, 0, N'0',N'00100000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'D0301', N'用户时段', 0, 191, 1, N'0',N'00100000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'D0302', N'补班日管理', 0, 192, 1, N'0',N'00100000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'D0303', N'节假日管理', 0, 193, 1, N'0',N'00100000')
	
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'D04', N'监控管理', 0, 200, 0, N'0',N'00100000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'D0401', N'实时监控', 0, 201, 1, N'0',N'00100000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'D0402', N'电子地图监控', 0, 202, 1, N'0',N'00100000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'D0403', N'监控权限管理', 0, 203, 1, N'0',N'00100000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'D0404', N'通讯管理', 0, 204, 1, N'0',N'00100000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'D0405', N'视频监控', 0, 205, 1, N'0',N'00100000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'D0406', N'状态监控', 0, 206, 1, N'0',N'00100000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'D0407', N'开门权限查询', 0, 207, 1, N'0',N'00100000')
	
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'D05', N'报表查询', 0, 210, 0, N'0',N'00100000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'D0501', N'人员资料表查询', 0, 211, 1, N'0',N'00100000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'D0502', N'学生卡资料查询', 0, 212, 1, N'0',N'00100000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'D0503', N'门与群组人员查询', 0, 213, 1, N'0',N'00100000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'D0504', N'进出记录查询', 0, 214, 1, N'0',N'00100000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'D0505', N'报警记录查询', 0, 215, 1, N'0',N'00100000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'D0506', N'操作日志查询', 0, 216, 1, N'0',N'00100000')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'D0507', N'卡操作记录查询', 0, 217, 1, N'0',N'00100000')
	----房客
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'M', N'访客管理', 0, 220, 0, N'0',N'00000010')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'M0101', N'来访登记', 0, 221, 1, N'0',N'00000010')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'M01', N'访客应用', 0, 222, 1, N'0',N'00000010')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'M0102', N'访客预约', 0, 223, 1, N'0',N'00000010')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'M0103', N'访客查询', 0, 224, 1, N'0',N'00000010')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'M0104', N'访客黑名单', 0, 225, 1, N'0',N'00000010')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'M0105', N'门禁定义', 0, 226, 1, N'0',N'00000010')
	--INSERT [dbo].[Sys_Function] ([FunCode], [FunName], [FunType], [FunNo], [FunSon], [FunModName], [FunRight]) VALUES (N'M0106', N'二维码出入记录', 0, 227, 1, N'0',N'00000010')
	
end	
GO

if not exists(select 1 from Sys_Dict)
begin
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11,0,NULL,'基础数据','','',NULL,NULL,NULL,0,20000,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(1110,11,NULL,'系统设置','','',NULL,NULL,NULL,0,21000,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(111010,1110,NULL,'客户类型分类','','',NULL,NULL,NULL,0,21100,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11101001,111010,NULL,'企业','0','0',NULL,'Label',NULL,0,21101,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11101002,111010,NULL,'幼儿园','1','1',NULL,'Label',NULL,0,21102,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(111011,1110,NULL,'密钥类型分类','','',NULL,NULL,NULL,0,21200,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11101101,111011,NULL,'客户密钥','','',NULL,'TextBox',NULL,0,21201,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11101102,111011,NULL,'固定密钥','','',NULL,'TextBox',NULL,0,21202,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(111012,1110,NULL,'卡片发行器分类','','',NULL,NULL,NULL,0,21300,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11101201,111012,NULL,'无','0','0',NULL,'Label',NULL,0,21301,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11101202,111012,NULL,'一卡通发行器','1','1',NULL,'Label',NULL,0,21302,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11101203,111012,NULL,'控制器','2','2',NULL,'Label',NULL,0,21303,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11101204,111012,NULL,'蓝牙发行器','3','3',NULL,'Label',NULL,0,21304,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(111013,1110,NULL,'卡片发行器串口分类','','',NULL,NULL,NULL,0,21400,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11101301,111013,NULL,'自动','0','0',NULL,'Label',NULL,0,21401,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11101302,111013,NULL,'COM1','1','0',NULL,'Label',NULL,0,21402,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11101303,111013,NULL,'COM11','11','0',NULL,'Label',NULL,0,21403,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(111014,1110,NULL,'通讯方式分类','','',NULL,NULL,NULL,0,21500,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11101401,111014,NULL,'TCP/IP','1','1',NULL,'Label',NULL,0,21501,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11101402,111014,NULL,'CAN总线','2','2',NULL,'Label',NULL,0,21502,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(1111,11,NULL,'车场系统','','',NULL,NULL,NULL,0,22000,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(111110,1111,NULL,'车场模式分类','','',NULL,NULL,NULL,0,22100,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111001,111110,NULL,'标准车场','0','0',NULL,'Label',NULL,0,22101,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111002,111110,NULL,'嵌套车场','1','1',NULL,'Label',NULL,0,22102,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111003,111110,NULL,'单通道车场','2','2',NULL,'Label',NULL,0,22103,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(111111,1111,NULL,'收费模式分类','','',NULL,NULL,NULL,0,22200,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111101,111111,NULL,'标准收费模式','1','1',NULL,'Label',NULL,0,22201,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111102,111111,NULL,'不收费模式','2','2',NULL,'Label',NULL,0,22202,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111103,111111,NULL,'中央收费模式','3','3',NULL,'Label',NULL,0,22203,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(111112,1111,NULL,'卡机类型分类','','',NULL,NULL,NULL,0,22300,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111201,111112,NULL,'无卡机','0','0',NULL,'Label',NULL,0,22301,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111202,111112,NULL,'IC卡机','1','1',NULL,'Label',NULL,0,22301,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111203,111112,NULL,'ID卡机','2','2',NULL,'Label',NULL,0,22302,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111204,111112,NULL,'纸票机','3','3',NULL,'Label',NULL,0,22303,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(111113,1111,NULL,'收费标准分类','','',NULL,NULL,NULL,0,22400,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111301,111113,NULL,'标准收费类','0','0',NULL,'Label',NULL,0,22401,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111302,111113,NULL,'通用标准一类','1','1',NULL,'Label',NULL,0,22402,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111303,111113,NULL,'通用标准二类','2','2',NULL,'Label',NULL,0,22403,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111304,111113,NULL,'通用标准三类','3','3',NULL,'Label',NULL,0,22404,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111305,111113,NULL,'深圳社会类','4','4',NULL,'Label',NULL,0,22405,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(111114,1111,NULL,'折扣优惠分类','','',NULL,NULL,NULL,0,22500,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111401,111114,NULL,'无','0','0',NULL,'Label',NULL,0,22501,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111402,111114,NULL,'人工打折','1','1',NULL,'Label',NULL,0,22502,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111403,111114,NULL,'自动打折','2','2',NULL,'Label',NULL,0,22503,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111404,111114,NULL,'手动输入','3','3',NULL,'Label',NULL,0,22504,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111405,111114,NULL,'商家打折','4','4',NULL,'Label',NULL,0,22505,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(111115,1111,NULL,'月临时卡收费分类','','',NULL,NULL,NULL,0,22600,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111501,111115,NULL,'无','0','0',NULL,'Label',NULL,0,22601,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111502,111115,NULL,'临时卡A类','1','1',NULL,'Label',NULL,0,22602,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111503,111115,NULL,'临时卡B类','2','2',NULL,'Label',NULL,0,22603,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111504,111115,NULL,'临时卡C类','3','3',NULL,'Label',NULL,0,22604,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111505,111115,NULL,'临时卡D类','4','4',NULL,'Label',NULL,0,22605,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111506,111115,NULL,'对应卡类','5','5',NULL,'Label',NULL,0,22606,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(111116,1111,NULL,'票据打印分类','','',NULL,NULL,NULL,0,22700,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111601,111116,NULL,'无','0','0',NULL,'Label',NULL,0,22701,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111602,111116,NULL,'普通票据','1','1',NULL,'Label',NULL,0,22702,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111603,111116,NULL,'税控发票','2','2',NULL,'Label',NULL,0,22703,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(111117,1111,NULL,'车牌识别设备分类','','',NULL,NULL,NULL,0,22800,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111701,111117,NULL,'无','0','0',NULL,'Label',NULL,0,22800.01,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111702,111117,NULL,'车牌识别服务器','1','1',NULL,'Label',NULL,0,22800.02,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111703,111117,NULL,'HK车牌硬件识别','2','2',NULL,'Label',NULL,0,22800.03,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111704,111117,NULL,'DH车牌硬件识别','3','3',NULL,'Label',NULL,0,22800.04,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(111118,1111,NULL,'入口模式分类','','',NULL,NULL,NULL,0,22801,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111801,111118,NULL,'无卡模式','0','0',NULL,'Label',NULL,0,22801.01,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111802,111118,NULL,'辅助模式','1','1',NULL,'Label',NULL,0,22801.02,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111803,111118,NULL,'宽进模式','2','2','仅适用于ID卡','Label',NULL,0,22801.03,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111804,111118,NULL,'严进模式','3','3',NULL,'Label',NULL,0,22801.04,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(111119,1111,NULL,'出口模式分类','','',NULL,NULL,NULL,0,22802,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111901,111119,NULL,'无卡模式','0','0',NULL,'Label',NULL,0,22802.01,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111902,111119,NULL,'辅助模式','1','1',NULL,'Label',NULL,0,22802.02,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111903,111119,NULL,'宽出模式','2','2','仅适用于ID卡','Label',NULL,0,22802.03,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11111904,111119,NULL,'严出模式','3','3',NULL,'Label',NULL,0,22802.04,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(111120,1111,NULL,'纯车牌应用分类','','',NULL,NULL,NULL,0,22803,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11112001,111120,NULL,'无纯车牌','0','0',NULL,'Label',NULL,0,22803.01,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11112002,111120,NULL,'全部纯车牌','1','1',NULL,'Label',NULL,0,22803.02,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11112003,111120,NULL,'固定车纯车牌','2','2','仅适用于ID卡','Label',NULL,0,22803.03,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(111121,1111,NULL,'车牌识别类型分类','','',NULL,NULL,NULL,0,22804,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11112101,111121,NULL,'四位模糊识别','0','0',NULL,'Label',NULL,0,22804.01,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11112102,111121,NULL,'五位近似识别','1','1',NULL,'Label',NULL,0,22804.02,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11112103,111121,NULL,'六位精确识别','2','2',NULL,'Label',NULL,0,22804.03,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(1112,11,NULL,'门禁系统','','',NULL,NULL,NULL,0,23000,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(111210,1112,NULL,'文件保存路径网络模式分类','','',NULL,NULL,NULL,0,23100,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11121001,111210,NULL,'本机模式','0','0',NULL,'Label',NULL,0,23101,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11121002,111210,NULL,'局域网模式','1','1',NULL,'Label',NULL,0,23102,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(1120,11,NULL,'接口提供商','','',NULL,NULL,NULL,0,23200,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(112010,1120,NULL,'DB接口提供商类别','','',NULL,NULL,NULL,0,23210,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11201001,112010,NULL,'无','','',NULL,'Label',NULL,0,23211,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11201002,112010,NULL,'厦门易维公司','Sp_Sys_TimmingSynData,8,','Sp_Sys_TimmingSynData,8,',NULL,'Label',NULL,0,23212,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(112011,1120,NULL,'WEB接口提供商类别','','',NULL,NULL,NULL,0,23213,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(11201101,112011,NULL,'无','','',NULL,'Label',NULL,0,23214,'00000','zh-CN')

	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12,0,NULL,'环境设置','','',NULL,NULL,NULL,0,10000,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(1210,12,NULL,'系统设置','','',NULL,NULL,NULL,0,11000,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(121012,1210,NULL,'单位信息','','',NULL,NULL,NULL,0,11100,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12101201,121012,NULL,'单位名称','默认企业','默认企业',NULL,'TextBox',NULL,0,11101,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12101202,121012,NULL,'单位编号','00000','00000',NULL,'TextBox',NULL,0,11102,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12101203,121012,NULL,'密钥','111011@','111011@',NULL,'ComboBox',NULL,0,11103,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12101204,121012,NULL,'单位类型','111010@11101001','111010@11101001',NULL,'ComboBox',NULL,0,11104,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12101205,121012,NULL,'部门编号规则','332','332',NULL,'TextBox',NULL,0,11105,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12101207,121012,NULL,'启用CPU卡','0','0',NULL,'CheckBox',NULL,0,11106,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(121013,1210,NULL,'数据中心设置','','',NULL,NULL,NULL,0,11200,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12101301,121013,NULL,'数据中心名称','','',NULL,'TextBox',NULL,0,11201,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12101302,121013,NULL,'数据中心地址','','',NULL,'TextBox',NULL,0,11202,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(121014,1210,NULL,'卡片发行器设置','','',NULL,NULL,NULL,0,11300,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12101401,121014,NULL,'已选发行器','111012@11101201','111012@11101201',NULL,'ComboBox',NULL,0,11301,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12101402,121014,'12101401','已选发行器串口','111013@11101301','',NULL,'ComboBox',NULL,0,11302,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12101403,121014,'12101401','已选发行控制器IP','','',NULL,'TextBox',NULL,0,11303,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(121015,1210,NULL,'IC卡区号设置','','',NULL,NULL,NULL,0,11400,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12101501,121015,NULL,'车场区号','6','6',NULL,'NumericUpDown',NULL,0,11401,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12101502,121015,NULL,'门禁区号','5','5',NULL,'NumericUpDown',NULL,0,11402,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12101503,121015,NULL,'考勤区号','4','4',NULL,'NumericUpDown',NULL,0,11403,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12101504,121015,NULL,'消费信息区号','4','4',NULL,'NumericUpDown',NULL,0,11404,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12101505,121015,NULL,'消费钱包区号','8','8',NULL,'NumericUpDown',NULL,0,11405,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12101506,121015,NULL,'巡更区号','9','9',NULL,'NumericUpDown',NULL,0,11406,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12101507,121015,NULL,'水控区号','10','10',NULL,'NumericUpDown',NULL,0,11407,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(121016,1210,NULL,'通讯设置','','',NULL,NULL,NULL,0,11500,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12101601,121016,NULL,'通讯方式','111014@11101401','111014@11101401',NULL,'ComboBox',NULL,0,11501,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12101602,121016,NULL,'车场UDP上行端口','2200','2200',NULL,'TextBox',NULL,0,11502,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12101603,121016,NULL,'车场UDP下行端口','2100','2100',NULL,'TextBox',NULL,0,11503,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12101604,121016,NULL,'门禁UDP上行端口','2202','2202',NULL,'TextBox',NULL,0,11504,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12101605,121016,NULL,'门禁UDP下行端口','2100','2100',NULL,'TextBox',NULL,0,11505,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(1211,12,NULL,'车场系统设置','','',NULL,NULL,NULL,0,12000,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(121110,1211,NULL,'系统参数设置','','',NULL,NULL,NULL,0,12100,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111001,121110,NULL,'车场模式','111110@11111001','111110@11111001',NULL,'ComboBox',NULL,0,12101,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111002,121110,'12111201','大车场编号','0','0',NULL,'NumericUpDown',NULL,0,12102,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111003,121110,NULL,'收费模式','111111@11111101','111111@11111101',NULL,'ComboBox',NULL,0,12103,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111004,121110,NULL,'卡机类型','111112@11111201','111112@11111201',NULL,'ComboBox',NULL,0,12104,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111005,121110,NULL,'固定卡过期预警天数','1','1',NULL,'NumericUpDown',NULL,0,12105,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111006,121110,NULL,'允许固定卡过期天数','0','0','固定卡过期后还能使用的天数','NumericUpDown',NULL,0,12106,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111007,121110,NULL,'保存手动开闸记录','1','1','保存道闸被遥控器开闸的记录','CheckBox',NULL,0,12107,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111008,121110,NULL,'记录覆盖','0','0','如果记录已满则覆盖之前的记录(下位机)','CheckBox',NULL,0,12108,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111009,121110,NULL,'压地感抓拍','1','1','地感检测到有车情况进行图像抓拍','CheckBox',NULL,0,12109,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111010,121110,NULL,'在线显示车主照片','0','0','在线模式刷卡，岗亭可以看到车主的照片信息','CheckBox',NULL,0,12110,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111011,121110,NULL,'允许贵宾车出入场','1','1',NULL,'CheckBox',NULL,0,12111,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111012,121110,NULL,'整月延期','0','0','选中该项，月租卡延期的时候只能延期一个月','CheckBox',NULL,0,12112,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111013,121110,NULL,'一车一卡','0','0','发行月租卡片或免费卡的时候，一个车牌号只能对应一张卡片','CheckBox',NULL,0,12113,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111014,121110,NULL,'智能道闸记忆','0','0','存储器智能记录道闸的开关记录','CheckBox',NULL,0,12114,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111015,121110,NULL,'单次刷卡','0','0','压地感后只能刷一次卡，必须等地感消失以后才能继续再次刷卡','CheckBox',NULL,0,12115,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111016,121110,NULL,'白名单模式','0','0',NULL,'CheckBox',NULL,0,12116,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111017,121110,NULL,'收卡机在线控制','0','0',NULL,'CheckBox',NULL,0,12117,'00000','zh-CN')	
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(121111,1211,NULL,'收费口参数设置','','',NULL,NULL,NULL,0,12200,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111101,121111,NULL,'收费标准','111113@11111301','111113@11111301',NULL,'ComboBox',NULL,0,12201,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111102,121111,NULL,'折扣优惠','111114@11111401','111114@11111401',NULL,'ComboBox',NULL,0,12202,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111103,121111,NULL,'月临时卡收费','111115@11111501','111115@11111501','是否将月卡过期后转为临时卡用','ComboBox',NULL,0,12203,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111104,121111,NULL,'票据打印','111116@11111601','111116@11111601','出场的时候是否启用票据打印','ComboBox',NULL,0,12204,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111105,121111,NULL,'预置车牌','0','0','是否可以手动更改车牌收费','CheckBox',NULL,0,12205,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111106,121111,NULL,'预置卡类','0','0','是否可以更换临时车的卡类收费','CheckBox',NULL,0,12206,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111107,121111,NULL,'快速出场','0','0','在计费器上刷卡;纯车牌临时车,ETC临时车有效;如果不需要收费则自动开闸','CheckBox',NULL,0,12207,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111108,121111,NULL,'临时车免费','0','0',NULL,'CheckBox',NULL,0,12208,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111109,121111,'12111108','证件抓拍','0','0',NULL,'CheckBox',NULL,0,12209,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111110,121111,NULL,'播报车牌','0','0','仅在计费器上刷卡播报','CheckBox',NULL,0,12210,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111111,121111,NULL,'取消开闸也收费','0','0','此选项只针对ID卡','CheckBox',NULL,0,12211,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(121112,1211,NULL,'车牌识别设置','','',NULL,NULL,NULL,0,12300,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111201,121112,NULL,'车牌识别设备','111117@11111701','111117@11111701',NULL,'ComboBox',NULL,0,12301,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111202,121112,'12111201<>11111701','入口模式','111118@11111801','111118@11111801','宽进模式仅适用于ID卡','ComboBox',NULL,0,12302,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111203,121112,'12111201<>11111701','出口模式','111119@11111901','111119@11111901','宽出模式仅适用于ID卡','ComboBox',NULL,0,12303,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111204,121112,'12111201<>11111701','纯车牌应用','111120@11112001','111120@11112001',NULL,'ComboBox',NULL,0,12304,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111205,121112,NULL,'车牌识别类型','111121@11112102','111121@11112102',NULL,'ComboBox',NULL,0,12305,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111206,121112,NULL,'车牌不符提醒','0','0','仅适用于宽进宽出模式','CheckBox',NULL,0,12306,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12111207,121112,NULL,'无牌车自动入场','0','0',NULL,'CheckBox',NULL,0,12307,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(1212,12,NULL,'门禁系统设置','','',NULL,NULL,NULL,0,13000,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(121210,1212,NULL,'文件保存路径设置','','',NULL,NULL,NULL,0,13100,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12121001,121210,NULL,'文件保存路径网络模式','111210@11121001','111210@11121001',NULL,'ComboBox',NULL,0,13101,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12121002,121210,'12121001','图片文件保存路径','D:\DRFiles','D:\DRFiles',NULL,'TextBox',NULL,0,13102,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12121003,121210,'12121001','文件夹用户','','',NULL,'TextBox',NULL,0,13103,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12121004,121210,'12121001','文件夹密码','','',NULL,'TextBox',NULL,0,13104,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(121211,1212,NULL,'数据管理','','',NULL,NULL,NULL,0,13100,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12121105,121211,NULL,'启动定时下载卡相关数据','0@11','0@11',NULL,'TextBox',NULL,0,13105,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12121106,121211,NULL,'启动定时导入(出)卡相关数据','0@7','0@7',NULL,'TextBox',NULL,0,13106,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12121107,121211,NULL,'启动定时同步其它系统数据','0@13','0@13',NULL,'TextBox',NULL,0,13107,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12121109,121211,NULL,'指定开启监控服务的终端IP','','',NULL,'TextBox',NULL,0,13109,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12121110,121211,'12121105','指定开启定时下载服务的终端IP','','',NULL,'TextBox',NULL,0,13110,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12121111,121211,'12121106','指定定时导入导出服务的终端IP','','',NULL,'TextBox',NULL,0,13111,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12121112,121211,'12121107','指定开启定时同步服务的终端IP','','',NULL,'TextBox',NULL,0,13112,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(12121113,121211,NULL,'当前是否正在下载卡相关数据','0','0',NULL,'CheckBox',NULL,0,13113,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(13,0,NULL,'接口厂商设置','','',NULL,NULL,NULL,0,13213,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(1310,13,NULL,'门禁系统','','',NULL,NULL,NULL,0,13214,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(131010,1310,NULL,'软件接口提供商设置','','',NULL,NULL,NULL,0,13215,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(13101001,131010,NULL,'DB接口提供商设置','112010@11201001','112010@11201001',NULL,'ComboBox',NULL,0,13216,'00000','zh-CN')
	INSERT INTO Sys_Dict(NodeID,ParentNodeID,DependentNodeID,NodeName,NodeValue,NodeValueDefault,NodeDesc,NodeFlag,NodeFormat,IsDelete,OrderID,OrgID,LanguageCode)VALUES(13101002,131010,NULL,'WEB接口提供商设置','112011@11201101','112011@11201101',NULL,'ComboBox',NULL,0,13217,'00000','zh-CN')
end	
GO

IF not exists(SELECT 1 FROM Sys_Version)
	INSERT INTO Sys_Version(VSoft,VProgram,VDB,VType,PublishDate,CreateDate,UpdateDate,Comment)VALUES('V4.0.0.0','V4.0.0.0','V4.0.0.0',1,GETDATE (),GETDATE (),GETDATE (),'从CS门禁V3.0.2.4和YKT v2.0移植过来')
GO

if not Exists(Select 1 from  [ParK_FreeType])
BEGIN
	Insert Into [ParK_FreeType] values(1,'军车','')
	Insert Into [ParK_FreeType] values(2,'警车','')
	Insert Into [ParK_FreeType] values(3,'救护车','')
	Insert Into [ParK_FreeType] values(4,'工程抢险车','')
	Insert Into [ParK_FreeType] values(5,'消防车','')
	Insert Into [ParK_FreeType] values(6,'运钞车','')
end

GO

if not Exists(Select 1 from  [Park_CardType])
BEGIN
	Insert Into [Park_CardType] values(1,'手动开闸','手动开闸',0,0,0)
		Insert Into [Park_CardType] values(2,'计算机开闸','计算机开闸',0,0,0)
		Insert Into [Park_CardType] values(4,'记忆开闸','记忆开闸',0,0,0)
		Insert Into [Park_CardType] values(5,'挂失刷卡','挂失刷卡',0,0,0)
		Insert Into [Park_CardType] values(6,'逾时吞卡','逾时吞卡',0,0,0)
		Insert Into [Park_CardType] values(7,'贵宾车','贵宾车',0,0,0)
		Insert Into [Park_CardType] values(11,'月租卡A','月租卡A',0,1,0)
		Insert Into [Park_CardType] values(12,'月租卡B','月租卡B',0,1,0)
		Insert Into [Park_CardType] values(13,'月租卡C','月租卡C',0,1,0)
		Insert Into [Park_CardType] values(14,'月租卡D','月租卡D',0,1,0)
		Insert Into [Park_CardType] values(21,'月临卡A','月临卡A',0,0,0)
		Insert Into [Park_CardType] values(22,'月临卡B','月临卡B',0,0,0)
		Insert Into [Park_CardType] values(23,'月临卡C','月临卡C',0,0,0)
		Insert Into [Park_CardType] values(24,'月临卡D','月临卡D',0,0,0)
		Insert Into [Park_CardType] values(31,'临时卡A','临时卡A',0,1,0)
		Insert Into [Park_CardType] values(32,'临时卡B','临时卡B',0,1,0)
		Insert Into [Park_CardType] values(33,'临时卡C','临时卡C',0,1,0)
		Insert Into [Park_CardType] values(34,'临时卡D','临时卡D',0,1,0)
		Insert Into [Park_CardType] values(40,'临免卡','临免卡',0,0,0)
		Insert Into [Park_CardType] values(41,'免费卡A','免费卡A',0,1,0)
		Insert Into [Park_CardType] values(42,'免费卡B','免费卡B',0,1,0)
		Insert Into [Park_CardType] values(3,'操作卡','操作卡',0,1,0)
		Insert Into [Park_CardType] values(51,'储值卡A','储值卡A',0,1,0)
		Insert Into [Park_CardType] values(52,'储值卡B','储值卡B',0,1,0)
		Insert Into [Park_CardType] values(53,'储值卡C','储值卡C',0,1,0)
		Insert Into [Park_CardType] values(54,'储值卡D','储值卡D',0,1,0)
		Insert Into [Park_CardType] values(61,'储临卡A','储临卡A',0,0,0)
		Insert Into [Park_CardType] values(62,'储临卡B','储临卡B',0,0,0)
		Insert Into [Park_CardType] values(63,'储临卡C','储临卡C',0,0,0)
		Insert Into [Park_CardType] values(64,'储临卡D','储临卡D',0,0,0)
end
GO

if not Exists(Select 1 from  [Park_EffetTimes])
BEGIN
     INSERT [dbo].[Park_EffetTimes] ([CardType], [BTime], [Eime]) VALUES (11, N'08:00', N'18:00')
     INSERT [dbo].[Park_EffetTimes] ([CardType], [BTime], [Eime]) VALUES (12, N'08:00', N'18:00')
     INSERT [dbo].[Park_EffetTimes] ([CardType], [BTime], [Eime]) VALUES (13, N'08:00', N'18:00')
     INSERT [dbo].[Park_EffetTimes] ([CardType], [BTime], [Eime]) VALUES (14, N'08:00', N'18:00')
end
GO

if not Exists(Select 1 from  [Sys_Parameters])
BEGIN
--一卡通系统参数
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 1,'客户名称','默认企业',1,1,GETDATE(),GETDATE(),'系统管理员',''
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 2,'客户序号','',1,1,GETDATE(),GETDATE(),'系统管理员',''
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 3,'数据中心IP','192.168.1.1',1,1,GETDATE(),GETDATE(),'系统管理员','数据中心IP'
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 4,'部门编码规则','332',1,1,GETDATE(),GETDATE(),'系统管理员','部门编码规则'
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 5,'车场区号','6',1,1,GETDATE(),GETDATE(),'系统管理员','车场区号'
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 6,'门禁区号','13',1,1,GETDATE(),GETDATE(),'系统管理员','门禁区号'
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 7,'消费区号1','4',1,1,GETDATE(),GETDATE(),'系统管理员','消费区号1'
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 8,'消费区号2','8',1,1,GETDATE(),GETDATE(),'系统管理员','消费区号2'
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 9,'考勤区号','4',1,1,GETDATE(),GETDATE(),'系统管理员','考勤区号'
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 10,'巡更区号','9',1,1,GETDATE(),GETDATE(),'系统管理员','巡更区号'
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 11,'水控区号','10',1,1,GETDATE(),GETDATE(),'系统管理员','水控区号'
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 12,'梯控区号','7',1,1,GETDATE(),GETDATE(),'系统管理员','梯控区号'
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 13,'发行器类型','0',1,1,GETDATE(),GETDATE(),'系统管理员','0,无，1发行器，2控制器，3蓝牙发行器,4USB发行器'
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 14,'RFID卡类型','0',1,1,GETDATE(),GETDATE(),'系统管理员','RFID卡类型'
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 15,'发行器串口','0',1,1,GETDATE(),GETDATE(),'系统管理员','发行器串口'
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 16,'发行控制器IP','192.168.0.11',1,1,GETDATE(),GETDATE(),'系统管理员','发行控制器IP'
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 17,'上行端口','2200',1,1,GETDATE(),GETDATE(),'系统管理员','上行端口'
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 18,'下行端口',2100,1,1,GETDATE(),GETDATE(),'系统管理员','下行端口'
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 19,'门禁上行端口','2599',1,1,GETDATE(),GETDATE(),'系统管理员','门禁上行端口'
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 20,'门禁下行端口','2499',1,1,GETDATE(),GETDATE(),'系统管理员','门禁下行端口'
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 21,'安全狗数据','',1,1,GETDATE(),GETDATE(),'系统管理员','安全狗数据'
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 22,'客户密码','',1,1,GETDATE(),GETDATE(),'系统管理员','客户密码'
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 23,'模式','0',1,1,GETDATE(),GETDATE(),'系统管理员','模式'
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 24,'图片共享路径','D:\DRFiles',1,1,GETDATE(),GETDATE(),'系统管理员','图片共享路径'
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 25,'文件用户名','',1,1,GETDATE(),GETDATE(),'系统管理员','文件用户名'
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 26,'文件密码','',1,1,GETDATE(),GETDATE(),'系统管理员','文件密码'
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 27,'客户类型','0',1,1,GETDATE(),GETDATE(),'系统管理员','客户类型'
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 28,'车场TCP端口','2000',1,1,GETDATE(),GETDATE(),'系统管理员','车场TCP端口'



	--停车场系统参数
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 101,'收费标准','0',2,1,GETDATE(),GETDATE(),'系统管理员','收费标准' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 102,'车场模式','0',2,1,GETDATE(),GETDATE(),'系统管理员','0,标准;1,嵌套;2,单通道' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 103,'车场编号','0',2,1,GETDATE(),GETDATE(),'系统管理员','' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 104,'收费模式','0',2,1,GETDATE(),GETDATE(),'系统管理员','0标准2不收费1中央收费' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 105,'折扣优惠','0',2,1,GETDATE(),GETDATE(),'系统管理员','' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 106,'是否一车一卡','0',2,1,GETDATE(),GETDATE(),'系统管理员','' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 107,'告警天数','0',2,1,GETDATE(),GETDATE(),'系统管理员','' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 108,'票据打印','0',2,1,GETDATE(),GETDATE(),'系统管理员','' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 109,'压地感抓拍','0',2,1,GETDATE(),GETDATE(),'系统管理员','' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 110,'临时车免费','0',2,1,GETDATE(),GETDATE(),'系统管理员','0无1手工免费2证件抓拍' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 111,'是否保存手动开闸记录','0',2,1,GETDATE(),GETDATE(),'系统管理员','' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 112,'预置卡类收费','0',2,1,GETDATE(),GETDATE(),'系统管理员','' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 113,'预置车牌收费','0',2,1,GETDATE(),GETDATE(),'系统管理员','' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 114,'ID固定卡多进多出','0',2,1,GETDATE(),GETDATE(),'系统管理员','' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 115,'临时卡快速出场','0',2,1,GETDATE(),GETDATE(),'系统管理员','' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 116,'记录覆盖','0',2,1,GETDATE(),GETDATE(),'系统管理员','' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 117,'临时卡','0',2,1,GETDATE(),GETDATE(),'系统管理员','' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 118,'贵宾车','0',2,1,GETDATE(),GETDATE(),'系统管理员','' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 119,'月临卡','0',2,1,GETDATE(),GETDATE(),'系统管理员','' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 120,'预留设定','0',2,1,GETDATE(),GETDATE(),'系统管理员','8位Bit:1车牌模糊匹配提醒 2车场满位允许入场 3后车牌识别 4扫码出入场 5.IC卡转ID卡使用 6.无车牌取纸票' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 121,'预留设定','0',2,1,GETDATE(),GETDATE(),'系统管理员','8位Bit:1整月延期 2智能道闸记忆,3单次刷卡,4白名单模式，5播报车牌，6纯车牌发卡数据是否下发,7显示当班收费金额,8收费是否弹框' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 122,'固定卡可过期天数','10',2,1,GETDATE(),GETDATE(),'系统管理员','' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 123,'黑名单车辆是否可入','0',2,1,GETDATE(),GETDATE(),'系统管理员','' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 124,'卡机类型','0',2,1,GETDATE(),GETDATE(),'系统管理员','' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 125,'取消开闸是否放弃','0',2,1,GETDATE(),GETDATE(),'系统管理员','' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 126,'收费下载标志','0000000000000000000000000000000000000000000000000000000000000000',2,1,GETDATE(),GETDATE(),'系统管理员','' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 127,'超时收费下载标志','0000000000000000000000000000000000000000000000000000000000000000',2,1,GETDATE(),GETDATE(),'系统管理员','' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 128,'打折收费下载标志','0000000000000000000000000000000000000000000000000000000000000000',2,1,GETDATE(),GETDATE(),'系统管理员','' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 129,'系统设置下载标志','0000000000000000000000000000000000000000000000000000000000000000',2,1,GETDATE(),GETDATE(),'系统管理员','' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 130,'车牌识别设备','0',2,1,GETDATE(),GETDATE(),'系统管理员','车牌识别设备（0无1大华，2 海康,3大华透传）' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 131,'入口认证模式','0',2,1,GETDATE(),GETDATE(),'系统管理员','入口认证模式(0无 1辅助 2宽 3严)' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 132,'出口认证模式','0',2,1,GETDATE(),GETDATE(),'系统管理员','出口认证模式(0无 1辅助 2宽 3严)' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 133,'识别类型','1',2,1,GETDATE(),GETDATE(),'系统管理员','识别类型(0五位近似识别、1六位精确识别)' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 134,'纯车牌应用','0',2,1,GETDATE(),GETDATE(),'系统管理员','纯车牌应用(0-无纯车牌；1-全部纯车牌；2-固定车纯车牌)' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 135,'个性化语音设置','0',2,1,GETDATE(),GETDATE(),'系统管理员','个性化语音设置' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 136,'车位显示屏IP地址','192.168.1.1',2,1,GETDATE(),GETDATE(),'系统管理员','车位显示屏IP地址' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 137,'储临卡','0',2,1,GETDATE(),GETDATE(),'系统管理员','储临卡' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 138,'主板类型','0',2,1,GETDATE(),GETDATE(),'系统管理员','主板类型' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 139,'是否启用高峰模式','0',2,1,GETDATE(),GETDATE(),'系统管理员','是否启用高峰模式' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 140,'是否启用管控模式','0',2,1,GETDATE(),GETDATE(),'系统管理员','是否启用管控模式' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 141,'默认车牌前缀','',2,1,GETDATE(),GETDATE(),'系统管理员','默认车牌前缀' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 142,'纸票','',2,1,GETDATE(),GETDATE(),'系统管理员','纸票' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 143,'支付二维码','',2,1,GETDATE(),GETDATE(),'系统管理员','' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 144,'是否启用线下网络支付','0',2,1,GETDATE(),GETDATE(),'系统管理员','0不启用,1启用' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 145,'网络支付服务地址','',2,1,GETDATE(),GETDATE(),'系统管理员','网络支付服务地址' 
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 146,'补闸控制','0',2,1,GETDATE(),GETDATE(),'系统管理员','补闸控制'
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 147,'道闸接口','0',2,1,GETDATE(),GETDATE(),'系统管理员','道闸接口'
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 148,'启用道闸地感','0',2,1,GETDATE(),GETDATE(),'系统管理员','0禁止1启用'
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 151,'车场编号','',2,1,GETDATE(),GETDATE(),'系统管理员','车场编号'
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 152,'加密秘钥','',2,1,GETDATE(),GETDATE(),'系统管理员','加密秘钥'
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 153,'URL地址','',2,1,GETDATE(),GETDATE(),'系统管理员','URL地址'
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 154,'自由通行车牌首字符','',2,1,GETDATE(),GETDATE(),'系统管理员','自由通行车牌首字符'
	insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 155,'车场跟踪日志','',2,1,GETDATE(),GETDATE(),'系统管理员','0不启用,1启用'	
	--insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 156,'启用无感支付','0',2,1,GETDATE(),GETDATE(),'系统管理员','0不启用,1启用'
	--insert Sys_Parameters(ParameterID,ParameterName,ParameterValue,TypeID,IsEdit,CreateDate,ModifyDate,ModifyUserName,Remark)  select 157,'无感支付先付费','0',2,1,GETDATE(),GETDATE(),'系统管理员','0不启用,1启用'
	end
go


IF NOT EXISTS(SELECT 1 FROM Per_Org)
	insert into Per_Org(DeptID,DeptName,TopDeptID) values ('A8888','默认单位','0')
GO
IF not exists(SELECT 1 FROM Per_Dept)
	insert into Per_Dept(DeptID,DeptName,TopDeptID,OrgID,UpdateFlag) values ('A8888','人事部','0','A8888',0)

IF NOT EXISTS(SELECT 1 FROM Park_LocalSet)
	INSERT INTO Park_LocalSet (BoxID,BoxIP,BoxName,BoxType,ImageCarType,Imagetimelag,smallImage,ZJType,ZJAV,IDSpaceOfTime,CardLater,UseTicket,TicketCom,TicketOutNo,PlaceType,PlaceMode,PlaceNum,GateOverSee,CheckType,CheckNo,OpenMachNo,LoadTime,CarSopt,CarSoptIp,CarSoptPort,CarSortNum,Other,CamUser,CamPass)  VALUES (1,'127.0.0.1',N'默认岗亭',0,2,100,0,1,0,0,15,1,0,2,0,0,9999,1,0,3,2,1,0,'127.0.0.1',0,0,NULL,'admin','12345')
GO

IF NOT EXISTS(SELECT 1 FROM Park_ControlSet)
BEGIN
	INSERT INTO Park_ControlSet (BoxID,machNo,ContIP,ContName,DSn,InOut,StrobeNo,CardMach,GateType,smallParkNo,StrobeSet,videoList,Enclosure,GateClose,HaveCar,NoMoney,SortID,RightPlan,SoundValue,IsTmp,CardWorkModel)  VALUES ( 1,1,'192.168.0.46',N'入口',NULL,0,1,0,0,0,'00000000','00000000','11110000',0,0,0,1,'',100,0,'00000000')
	INSERT INTO Park_ControlSet (BoxID,machNo,ContIP,ContName,DSn,InOut,StrobeNo,CardMach,GateType,smallParkNo,StrobeSet,videoList,Enclosure,GateClose,HaveCar,NoMoney,SortID,RightPlan,SoundValue,IsTmp,CardWorkModel)  VALUES ( 1,2,'192.168.0.47',N'出口',NULL,1,2,0,0,0,'00000000','00000000','11110000',0,0,0,2,'',100,0,'00000000')
END
GO

IF NOT EXISTS(SELECT 1 FROM Park_CamSet)
BEGIN
	INSERT INTO Park_CamSet (BoxID,SortID,CamIP,CamName,CamPort,FecPic,MachNo)  VALUES ( 1,1,'192.168.1.129',N'入口摄像机',544,0,1)
	INSERT INTO Park_CamSet (BoxID,SortID,CamIP,CamName,CamPort,FecPic,MachNo)  VALUES ( 1,2,'192.168.1.129',N'出口摄像机',544,0,2)
END
GO

IF NOT EXISTS(SELECT 1 FROM Park_ControlPlan)
	INSERT INTO Park_ControlPlan (PlanID,PlanName,RightList)  VALUES ( 1,N'全部设备','11'+dbo.PadLeft('0','0',253))
GO



IF NOT EXISTS(SELECT 1 FROM Park_Es1)
	INSERT INTO Park_Es1 (CardType,UnitType,TopMoney,ChHour1,ChHour2,ChHour3,ChHour4,ChHour5,ChHour6,ChHour7,ChHour8,ChHour9,ChHour10,ChHour11,ChHour12,ChHour13,ChHour14,ChHour15,ChHour16,ChHour17,ChHour18,ChHour19,ChHour20,ChHour21,ChHour22,ChHour23,ChHour24,AZero,AType)  VALUES ( 31,1,240,10,20,30,40,50,60,70,80,90,100,110,120,130,140,150,160,170,180,190,200,210,220,230,240,0,0)
GO

IF NOT EXISTS(SELECT 1 FROM Park_MonthSet)
BEGIN
	INSERT INTO Park_MonthSet (CardType,sType,ChargeMoney,Remark)  VALUES ( 11,0,200.00,'按整月交费')
	INSERT INTO Park_MonthSet (CardType,sType,ChargeMoney,Remark)  VALUES ( 12,0,300.00,'按整月交费')
	INSERT INTO Park_MonthSet (CardType,sType,ChargeMoney,Remark)  VALUES ( 13,0,200.00,'按整月交费')
	INSERT INTO Park_MonthSet (CardType,sType,ChargeMoney,Remark)  VALUES ( 14,0,300.00,'按整月交费')
	INSERT INTO Park_MonthSet (CardType,sType,ChargeMoney,Remark)  VALUES ( 11,1,550.00,'按季度交费')
	INSERT INTO Park_MonthSet (CardType,sType,ChargeMoney,Remark)  VALUES ( 12,1,850.00,'按季度交费')
	INSERT INTO Park_MonthSet (CardType,sType,ChargeMoney,Remark)  VALUES ( 13,1,600.00,'按季度交费')
	INSERT INTO Park_MonthSet (CardType,sType,ChargeMoney,Remark)  VALUES ( 14,1,900.00,'按季度交费')
	INSERT INTO Park_MonthSet (CardType,sType,ChargeMoney,Remark)  VALUES ( 11,2,1050.00,'按半年交费')
	INSERT INTO Park_MonthSet (CardType,sType,ChargeMoney,Remark)  VALUES ( 12,2,1700.00,'按半年交费')
	INSERT INTO Park_MonthSet (CardType,sType,ChargeMoney,Remark)  VALUES ( 13,2,1100.00,'按半年交费')
	INSERT INTO Park_MonthSet (CardType,sType,ChargeMoney,Remark)  VALUES ( 14,2,1800.00,'按半年交费')
	INSERT INTO Park_MonthSet (CardType,sType,ChargeMoney,Remark)  VALUES ( 11,3,2000.00,'按整年交费')
	INSERT INTO Park_MonthSet (CardType,sType,ChargeMoney,Remark)  VALUES ( 12,3,3300.00,'按整年交费')
	INSERT INTO Park_MonthSet (CardType,sType,ChargeMoney,Remark)  VALUES ( 13,3,2100.00,'按整年交费')
	INSERT INTO Park_MonthSet (CardType,sType,ChargeMoney,Remark)  VALUES ( 14,3,3600.00,'按整年交费')
END
GO
IF NOT EXISTS(SELECT 1 FROM Park_EsInfos)
	INSERT INTO Park_EsInfos (EsNo,EsType,CardType,EsInfo)  VALUES ( 0,0,31,'000000F0400000000A141E28323C46505A646E78828C96A0AAB4BEC8D2DCE6F0')
GO


IF NOT EXISTS(SELECT 1 FROM Door_UsualPlan)
BEGIN
	insert into [dbo].[Door_UsualPlan] ([PlanNo],[PlanName],[week],[TimeZones1],[TimeZones2] ,[TimeZones3],[TimeZones4],[state] ,[Remarks],[DownLoad],[DelType]) 
			values(1,'全天开门','1234567','00000000','00000000','00000000','00000000',1,'','0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000',1)
	insert into [dbo].[Door_UsualPlan] ([PlanNo],[PlanName],[week],[TimeZones1],[TimeZones2] ,[TimeZones3],[TimeZones4],[state] ,[Remarks],[DownLoad],[DelType]) 
			values(2,'全天关门','1234567','00000000','00000000','00000000','00000000',1,'','0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000',1)
END

IF NOT EXISTS(SELECT 1 FROM Door_PowerGrade)
	insert into [dbo].[Door_PowerGrade] ([GradeID],[Grade],[RightList],[IsDel]) 
			values(1,'默认组','1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111',0)
GO

IF NOT EXISTS(SELECT 1 FROM Door_Area)
	insert into [dbo].[Door_Area]([AreaNo],[AreaName],[NodeNo],[AreaRemarks],[AreaWorkstations],[AreaIP]) values('A0001','本区域','0','',0,'')
GO

	--门禁摄像机全局定义
IF NOT EXISTS(SELECT 1 FROM Door_CamParaLocalSet)
	insert into [Door_CamParaLocalSet] ([ImageCarType],[CamUser],[CamPass],[PicQuilty],[PicSize],[smallImage],[Imagetimelag]) values (4,'admin','12345',80,0,0,100)
GO

IF NOT EXISTS(SELECT 1 FROM Park_ChanelTemp)
BEGIN
	INSERT into [dbo].[Park_ChanelTemp] ([chanelName], [chanelNo]) VALUES (N'第一道', 1)
	INSERT into [dbo].[Park_ChanelTemp] ([chanelName], [chanelNo]) VALUES (N'第二道', 2)
	INSERT into [dbo].[Park_ChanelTemp] ([chanelName], [chanelNo]) VALUES (N'第三道', 3)
	INSERT into [dbo].[Park_ChanelTemp] ([chanelName], [chanelNo]) VALUES (N'第四道', 4)
	INSERT into [dbo].[Park_ChanelTemp] ([chanelName], [chanelNo]) VALUES (N'第五道', 5)
	INSERT into [dbo].[Park_ChanelTemp] ([chanelName], [chanelNo]) VALUES (N'第六道', 6)
	INSERT into [dbo].[Park_ChanelTemp] ([chanelName], [chanelNo]) VALUES (N'第七道', 7)
	INSERT into [dbo].[Park_ChanelTemp] ([chanelName], [chanelNo]) VALUES (N'第八道', 8)
END

GO

UPDATE Sys_Function SET FunName = N'发卡/改写' WHERE FunCode = N'B0101'

-- |**************************************************************************************|
-- |*******************************| 初始数据处理(end) |**********************************|
-- |**************************************************************************************|








----------------一下内容为5.0线下与线上脚本整合添加----------------------
-------------------操作人：yxf-------------------------------------------
------------------------------------------------------------------------------------
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Door_PowerPersonList]') AND type in (N'U'))
DROP TABLE [dbo].[Door_PowerPersonList]
GO
 

CREATE TABLE [dbo].[Door_PowerPersonList](
	[YKTID] [int] NOT NULL,
	[ContID] [int] NOT NULL,
	[DoorPower] [varchar](50) NULL
) ON [PRIMARY]

GO



if Not exists(select * from syscolumns where id=object_id('Park_DistributeCoupons') and name='CardNo')
	Alter Table Park_DistributeCoupons ADD [CardNo] VARCHAR(50) 
GO


if Not exists(select * from syscolumns where id=object_id('Park_DistributeCoupons') and name='CardID')
	Alter Table Park_DistributeCoupons ADD [CardID] VARCHAR(50) 
GO



IF NOT EXISTS( select * from syscolumns where id=object_id('Ykt_CardRsMoney') and name='ID')
BEGIN
ALTER TABLE  Ykt_CardRsMoney ADD   ID INT IDENTITY(1,1) PRIMARY KEY
END
GO

IF NOT EXISTS( select * from syscolumns where id=object_id('ParK_SumUser') and name='ID')
BEGIN
ALTER TABLE  ParK_SumUser ADD   ID INT IDENTITY(1,1) PRIMARY KEY
END


IF OBJECT_ID('pushObjectResult','U') is not null
	PRINT '该表已存在'--DROP TABLE Sys_SiteRecord
ELSE
BEGIN
CREATE TABLE  pushObjectResult(
ID INT IDENTITY(1,1) PRIMARY KEY,
ObjectId   INT,  --表主键
Result INT  DEFAULT(0) ,--0 未返回  1成功
TableSource VARCHAR(100),--储存数据来源
CreateOn DATETIME,
ModifyOn DATETIME
)
END
GO

IF OBJECT_ID('pushCarInResult','U') is not null
	PRINT '该表已存在'--DROP TABLE Sys_SiteRecord
ELSE
BEGIN
CREATE TABLE  pushCarInResult(
ID INT IDENTITY(1,1) PRIMARY KEY,
ObjectId   INT,  --表主键
Result INT  DEFAULT(0) ,--0 未返回  1成功
TableSource VARCHAR(100),--储存数据来源
CreateOn DATETIME,
ModifyOn DATETIME
)
END
GO

IF OBJECT_ID('pushCarOutResult','U') is not null
	PRINT '该表已存在'--DROP TABLE Sys_SiteRecord
ELSE
BEGIN
CREATE TABLE  pushCarOutResult(
ID INT IDENTITY(1,1) PRIMARY KEY,
ObjectId   INT,  --表主键
Result INT  DEFAULT(0) ,--0 未返回  1成功
TableSource VARCHAR(100),--储存数据来源
CreateOn DATETIME,
ModifyOn DATETIME
)
END
GO

IF OBJECT_ID('pushCarCenterPayResult','U') is not null
	PRINT '该表已存在'--DROP TABLE Sys_SiteRecord
ELSE
BEGIN
CREATE TABLE  pushCarCenterPayResult(
ID INT IDENTITY(1,1) PRIMARY KEY,
ObjectId   INT,  --表主键
Result INT  DEFAULT(0) ,--0 未返回  1成功 2删除　3重新匹配 4 未找到
TableSource VARCHAR(100),--储存数据来源
PicUrl VARCHAR(100),--图片Url
CarNo VARCHAR(50),--车牌号码
CarTime DATETIME,--出入时间
CreateOn DATETIME,
ModifyOn DATETIME
)
END
GO


/****** Object:  Table [dbo].[pushCarPicResult]    Script Date: 08/12/2016 16:50:14 ******/
IF not  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pushCarPicResult]') AND type in (N'U'))
begin
CREATE TABLE [dbo].[pushCarPicResult](
ID INT IDENTITY(1,1) PRIMARY KEY,
ObjectId   INT,  --表主键
Result INT  DEFAULT(0) ,--0 未返回  1成功 2删除　3重新匹配 4 未找到
TableSource VARCHAR(100),--储存数据来源
PicUrl VARCHAR(100),--图片Url
CarNo VARCHAR(50),--车牌号码
CarTime DATETIME,--出入时间
CreateOn DATETIME,
ModifyOn DATETIME
)
end
GO



/*************************************add  by deng at 2016-8-15 10:12:00 begin**********************************************/
IF NOT EXISTS( select * from syscolumns where id=object_id('pushObjectResult') and name='YunResult')
BEGIN
ALTER TABLE pushObjectResult ADD   YunResult INT DEFAULT(0)
END
GO
/*************************************add  by deng at 2016-8-15 10:12:00 end**********************************************/





 /****** Object:  Table [dbo].[pushDiscountDetailResult]  折扣  Script Date: 08/12/2016 16:50:14 ******/
IF not  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pushDiscountDetailResult]') AND type in (N'U'))
begin
CREATE TABLE [dbo].[pushDiscountDetailResult](
ID INT IDENTITY(1,1) PRIMARY KEY,
ObjectId   INT,  --表主键
Result INT  DEFAULT(0) ,--0 未返回  1成功 2删除　3重新匹配 4 未找到
TableSource VARCHAR(100),--储存数据来源
CreateOn DATETIME,
ModifyOn DATETIME
)
end
GO


 /****** Object:  Table [dbo].[pushContactResult] 人员信息   Script Date: 08/12/2016 16:50:14 ******/
IF not  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pushContactResult]') AND type in (N'U'))
begin
CREATE TABLE [dbo].[pushContactResult](
ID INT IDENTITY(1,1) PRIMARY KEY,
ObjectId   INT,  --表主键
Result INT  DEFAULT(0) ,--0 未返回  1成功 2删除　3重新匹配 4 未找到
TableSource VARCHAR(100),--储存数据来源
CrUserDate DATETIME,--最后上传时间时间
CreateOn DATETIME,
ModifyOn DATETIME
)
end
GO

 /****** Object:  Table [dbo].[pushCardInfoResult] 卡片信息   Script Date: 08/12/2016 16:50:14 ******/
IF not EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pushCardInfoResult]') AND type in (N'U'))
begin
CREATE TABLE [dbo].[pushCardInfoResult](
ID INT IDENTITY(1,1) PRIMARY KEY,
ObjectId   INT,  --表主键
Result INT  DEFAULT(0) ,--0 未返回  1成功 2删除　3重新匹配 4 未找到
TableSource VARCHAR(100),--储存数据来源
CrUserDate DATETIME,--最后上传时间时间
CreateOn DATETIME,
ModifyOn DATETIME
)
end
GO


if not exists(select * from syscolumns where id=object_id('Park_CamSet') and name='ServerPort') 
	Alter table Park_CamSet ADD [ServerPort] [int] default 0
GO

if not exists(select * from syscolumns where id=object_id('Park_CamSet') and name='IsUpdate') 
	Alter table Park_CamSet ADD [IsUpdate] int default 0    
GO

if Not exists(select * from syscolumns where id=object_id('Park_CamSet') and name='ID')
	Alter Table Park_CamSet ADD [ID] [int] IDENTITY(1,1) NOT NULL
GO

if not exists(select * from syscolumns where id=object_id('Park_LocalSet') and name='IsUpdate') 
        alter table Park_LocalSet add IsUpdate int default 0
GO
if Not exists(select * from syscolumns where id=object_id('Park_LocalSet') and name='ID')
        alter table Park_LocalSet add ID int IDENTITY(1,1) PRIMARY KEY 
GO

if Not exists(select * from syscolumns where id=object_id('Park_ControlSet') and name='IsUpdate')
alter table Park_ControlSet add IsUpdate int default 0
GO
if Not exists(select * from syscolumns where id=object_id('Park_ControlSet') and name='ID')
alter table Park_ControlSet add ID int IDENTITY(1,1) PRIMARY KEY 
GO

if not exists(select * from syscolumns where id=object_id('Door_cont') and name='IsUpdate') 
	Alter table Door_cont ADD IsUpdate int default 0  

GO
    UPDATE Door_cont SET IsUpdate = 0	



if not exists(select * from syscolumns where id=object_id('Door_door') and name='IsUpdate') 
	Alter table Door_door ADD IsUpdate int default 0  

GO
    UPDATE Door_door SET IsUpdate = 0	

go

IF  EXISTS (SELECT * FROM sys.views WHERE object_id = OBJECT_ID(N'[dbo].[vw_yun_GetDoorPermission]'))
DROP VIEW [dbo].[vw_yun_GetDoorPermission]
GO
CREATE VIEW vw_yun_GetDoorPermission
AS
SELECT  'Y'+CONVERT(VARCHAR(50),v.YktID)+'C'+CONVERT(VARCHAR(50),c.ContID)+'D'+CONVERT(VARCHAR(50),d.DoorNo) AS ID,v.YktID,PerID,PerName,StartDate,EndDate,GradeID,States,CrUserDate AS  UserDate ,c.ContID,c.ContName,d.ID DoorId,d.DoorName
FROM dbo.Vw_Door_Persons   v, Door_PowerPersonList p,dbo.Door_Cont c,dbo.Door_Door d
WHERE v.YktID=p.YKTID AND p.ContID=c.ContID AND p.ContID=d.ContID AND right(left(isnull(p.DoorPower,'0'),d.DoorNo),1) =1  
GO

IF OBJECT_ID('pushDoorPermissionResult','U') is not null
	PRINT '该表已存在'--
ELSE
BEGIN
CREATE TABLE  pushDoorPermissionResult(
ID INT IDENTITY(1,1) PRIMARY KEY,
ObjectId   varchar(50),  --表主键
yktID INT,
Flag INT,--0 非默认组 1  默认值
Result INT  DEFAULT(0) ,--0 未返回  1成功
TableSource VARCHAR(100),--储存数据来源
CreateOn DATETIME,
ModifyOn DATETIME
)
END
GO

IF  EXISTS (SELECT * FROM sys.views WHERE object_id = OBJECT_ID(N'[dbo].[vw_yun_GetDoorDefaultPermission]'))
DROP VIEW [dbo].[vw_yun_GetDoorDefaultPermission]
GO
CREATE VIEW vw_yun_GetDoorDefaultPermission
AS
SELECT  'Y'+CONVERT(VARCHAR(50),v.YktID)+'C'+CONVERT(VARCHAR(50),c.ContID)+'D'+CONVERT(VARCHAR(50),d.DoorNo) AS ID ,v.YktID,PerID,PerName,StartDate,EndDate,v.GradeID,States,CrUserDate AS  UserDate ,c.ContID,c.ContName,d.ID DoorId,d.DoorName
FROM Vw_Door_Persons v,Door_PowerGrade p, dbo.Door_Cont c,dbo.Door_Door d
WHERE v.GradeID=p.GradeID AND v.GradeID=1 AND p.GradeID=1  AND c.ContID=d.ContID AND  right(left(isnull(p.RightList,'0'),d.DoorNo),1) =1  
GO

/*家庭组*/
IF NOT EXISTS( select * from syscolumns where id=object_id('Park_FamilyGroup') and name='ModifiedOn')
BEGIN
ALTER TABLE Park_FamilyGroup ADD ModifiedOn DATETIME DEFAULT(GETDATE())
ALTER TABLE Park_FamilyGroup ADD ModifiedBy VARCHAR(50) 
END
GO
UPDATE Park_FamilyGroup SET ModifiedOn=GETDATE()
GO


/****** Object:  Table [dbo].[pushDoorAccessResult]  门禁出入记录  Script Date: 08/12/2016 16:50:14 ******/
IF NOT EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[pushDoorAccessResult]') AND type in (N'U'))
BEGIN
CREATE TABLE [dbo].[pushDoorAccessResult](
ID INT IDENTITY(1,1) PRIMARY KEY,
ObjectId   INT,  --表主键
Result INT  DEFAULT(0) ,--0 未返回  1成功 2删除　3重新匹配 4 未找到
TableSource VARCHAR(100),--储存数据来源
CreateOn DATETIME,
ModifyOn DATETIME
)
END
GO

/****** Object:  View [dbo].[Vw_DoorAccessRecords]   Script Date: 10/09/2016 13:39:19 ******/
IF  EXISTS (SELECT * FROM sys.views WHERE object_id = OBJECT_ID(N'[dbo].[Vw_DoorAccessRecords]'))
DROP VIEW [dbo].[Vw_DoorAccessRecords]
GO

CREATE  VIEW  [dbo].[Vw_DoorAccessRecords] 
AS 
SELECT d.ID, d.PID,d. ContID, d.DoorID, CASE  d.CardType WHEN '96' THEN 60  WHEN '112' THEN 70   WHEN '120' THEN 70 
WHEN '121' THEN 79 WHEN '122' THEN 70  ELSE 0 END CardTypeID,
CASE  d.CardType WHEN '96' THEN '通行卡'  WHEN '112' THEN '管理卡'   WHEN '120' THEN '警卫卡' 
WHEN '121' THEN '巡检卡' WHEN '122' THEN '来宾卡'  ELSE '无效卡' END CardType , d.CardID, d.EventTime, 
d.EventWay, d.EventType, isnull(d.PerID,0) PerID, d.PerName, d.PicPath,d. PicName,c.ContIP,c.ContName,
isnull(d.Extended,0) Extended ,dd.DoorName
FROM  [Door_OpenDoorRecord] d LEFT JOIN dbo.Door_Door dd ON d.DoorID=dd.ID, dbo.Door_Cont c 
WHERE d.ContID=c.ContID 
GO

if Not exists(select * from syscolumns where id=object_id('ParK_CarBlackList') and name='ID')
    Alter Table ParK_CarBlackList ADD [ID] [int] IDENTITY(1,1) NOT NULL
GO

IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Yun_SpDistributeCoupons]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[Yun_SpDistributeCoupons]
GO
 
CREATE PROC [dbo].[Yun_SpDistributeCoupons]
@OnlineId VARCHAR(50),                    --唯一编号
@OpenId VARCHAR(50),                       --微信用户ID
@CarNo VARCHAR(20),                        --车牌号码
@InTime DATETIME,                          --入场时间
@CouponId VARCHAR(50),                     --线上优惠劵ID
@CouponName VARCHAR(200),                  --优惠劵名称
@CommercialId VARCHAR(50),                 --商铺编号
@CommercialName VARCHAR(200),              --商铺名称
@CouponType INT,                           --0 首段减免（小时）  1 减免金额（元）  2 打折减免（%）
@CouponAmount FLOAT,                       --zhek
@UserName VARCHAR(50),
@Description VARCHAR(2000),
@CardNo VARCHAR(20)='',                        --车牌号码
@CardID VARCHAR(100)=''                        --车牌号码
AS
--检测商家信息
DECLARE @eqid INT ,@discountid INT
IF  NOT EXISTS(SELECT * FROM dbo.Park_EquipmentS WHERE ClientNO=@CommercialId)
BEGIN
	DECLARE @eqmaxid INT 
	SELECT @eqmaxid=MAX(EqID) FROM dbo.Park_EquipmentS
	INSERT  dbo.Park_EquipmentS( EqID,EqName ,Memo ,ClientNO ,DownLoad)
	VALUES  ( ISNULL(@eqmaxid,0)+1,
	          @CommercialName , -- EqName - varchar(20)
	          '优惠劵系统下发', -- Memo - varchar(50)
	          @CommercialId , -- ClientNO - varchar(50)
	          ''  -- DownLoad - varchar(255)
	        )
	SET @eqid= isnull(@eqmaxid,0)+1 
END
ELSE
BEGIN
	SELECT TOP 1 @eqid=EqID FROM dbo.Park_EquipmentS WHERE ClientNO=@CommercialId
END
--检测优惠劵
IF @CouponType=0
	SET @CouponType=3
ELSE IF @CouponType=1 
	SET @CouponType=2
ELSE IF @CouponType=2
	SET @CouponType=4
ELSE IF @CouponType=3
	SET @CouponType=1
	
IF NOT EXISTS(SELECT * FROM dbo.Park_DisInfo WHERE DisRemark=@CouponId and EqID=@eqid)
BEGIN
	DECLARE @dismaxid INT 
	SELECT @dismaxid=MAX(DiscountID) FROM dbo.Park_DisInfo
	INSERT INTO dbo.Park_DisInfo
	        ( EqID ,
	          DiscountID ,
	          DisName ,
	          DisType ,
	          DisAmount ,
	          DisRemark
	        )
	VALUES  ( @eqid , -- EqID - int
	          ISNULL(@dismaxid,0)+1 , -- DiscountID - int
	          @CouponName , -- DisName - varchar(20)
	          @CouponType , -- DisType - tinyint                       1全免(0)  2 折扣率(<100) 3免时间(<65535) 4免金额(<65535)
	          @CouponAmount , -- DisAmount - float
	          @CouponId  -- DisRemark - varchar(50)
	        )
        SET @discountid=isnull( @dismaxid,0)+1
END
ELSE
BEGIN
	SELECT TOP 1 @discountid=DiscountID FROM dbo.Park_DisInfo WHERE DisRemark=@CouponId and EqID=@eqid
END
IF NOT EXISTS(SELECT TOP 1 1 FROM dbo.Park_CarIn WHERE (CarNO =@CarNo OR CardNo=@CardNo OR CardID=@CardID) AND InTime=@InTime AND ISNULL(TypeID,0)>0 )
BEGIN
	IF NOT EXISTS(SELECT TOP 1 * FROM dbo.Park_CentralCharge WHERE (CarNO =@CarNo OR CardID=@CardID OR CardNo=@CardNo) AND InTime=@InTime )
	begin
		IF NOT EXISTS(SELECT TOP 1 * FROM  Park_DistributeCoupons WHERE OnlineId= @OnlineId)
		BEGIN
		INSERT INTO Park_DistributeCoupons(OnlineId,OpenId,CarNo,CardNo,CardID,InTime,DiscountTime,DiscountNo,TypeID,CouponId,CouponName,CommercialId,CommercialName,CouponType,CouponAmount,UserName,Description)
		VALUES(@OnlineId,@OpenId,@CarNo,@CardNo,@CardID,@InTime,GETDATE(),@discountid,@eqid,@CouponId,@CouponName,@CommercialId,@CommercialName,@CouponType,@CouponAmount,@UserName,@Description)
		SELECT @@IDENTITY ID
		END
		ELSE
		BEGIN
		 SELECT TOP 1 ID FROM  Park_DistributeCoupons WHERE OnlineId= @OnlineId
		END
	END
	ELSE
	BEGIN
		SELECT -3 AS ID
	END
END
ELSE
BEGIN
	SELECT -2 AS ID
END

go


IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[yun_cardissue_operation]') AND type in (N'P', N'PC'))
DROP PROCEDURE [dbo].[yun_cardissue_operation]
GO
 
CREATE proc [dbo].[yun_cardissue_operation] 
@operationType int,
@contactId VARCHAR(50),
@contactName VARCHAR(50),
@carNO varchar(10),
@cardId varchar(20),
@cardTypeId varchar(10),
@cardTypeName varchar(20),
@newStartDate datetime,
@newEndDate datetime,
@rechargeAmount INT,
@carPlace varchar(10),
@carNoType varchar(10),
@carColour varchar(10),
@Description varchar(500),
@id varchar(100),
@paytype INT=0
AS
declare @yktid int=0,@owneroldId varchar(50)
if exists (select top 1 *  from dbo.Ykt_CardIssue c,Ykt_CardIssuePark p  WHERE c.YktID=p.YKTID AND CarNO  = @carNo  AND  States<>6 )
begin
	select top 1 @yktid=c.YKTID   from dbo.Ykt_CardIssue c,Ykt_CardIssuePark p  WHERE c.YktID=p.YKTID AND CarNO  = @carNo  AND  States<>6 order by c.YKTID desc
	select top 1 @owneroldId=PID from Ykt_CardIssue where YktID=@yktid
END

IF EXISTS(SELECT * FROM YKT_CardRsMoney WHERE WeChatNumber= @id)
BEGIN
RETURN;
END

IF @operationType =6
BEGIN
 UPDATE Ykt_CardIssue SET States=6,UserDate=GETDATE() WHERE YktID=@yktid
 insert into dbo.YKT_CardRsMoney(YKTID,SType,FrontDate,NStartDate,NEndDate,BalanceMoney,PayType,Foregift,BeforeMoney,UserDate,UserName,Remark,WeChatNumber) 
 select top 1 YKTID,6,GETDATE(),GETDATE(),GETDATE(),@rechargeAmount,@paytype,0,0,getdate(),'物业云平台','销户',@id from Ykt_Cardissue where  YktID=@yktid
END
ELSE
begin
--检测车卡信息是否存在
--延期
	if @yktid!=0
	begin
		Update Ykt_Cardissue set PerName=@contactName,PID=@contactId where YktID=@yktid
		if(getdate()<(select top 1 Enddate from Ykt_CardIssuePark where YKTID=@yktid)) 
			Update Ykt_CardIssuePark set BalanceMoney=@rechargeAmount, EndDate=@newEndDate,UserDate=GETDATE(),Remark=@Description,CarPlace=@carPlace,CarColour=@carColour where YKTID=@yktid
		else 
		BEGIN
			Update Ykt_CardIssuePark set BalanceMoney=@rechargeAmount,StartDate=@newStartDate,EndDate=@newEndDate,UserDate=GETDATE(),Remark=@Description,CarPlace=@carPlace,CarColour=@carColour where YKTID=@yktid
		END
			
		IF(@cardTypeId>50 AND @cardTypeId<55) 
		SET @rechargeAmount =0		
		insert into dbo.YKT_CardRsMoney(YKTID,SType,FrontDate,NStartDate,NEndDate,BalanceMoney,PayType,Foregift,BeforeMoney,UserDate,UserName,Remark,WeChatNumber) 
		select top 1 YKTID,1,@newStartDate,@newStartDate,@newEndDate,@rechargeAmount,@paytype,0,@rechargeAmount,getdate(),'物业云平台','延期' ,@id from Ykt_Cardissue where  YktID=@yktid

	end
	--发卡
	if @yktid=0
	begin
		declare  @pid int=0
		--if exists(select DeptID from Per_Dept where DeptName =@departName)
		--begin
		--	select top 1 @deptId=DeptID  from Per_Dept where DeptName =@departName
		--end
		if  exists(select top 1 * from Per_Persons  where PerID=@contactId )
		begin
			select top 1 @pid=id from Per_Persons  where PerID=@contactId
		end
		if @pid=0
		BEGIN
		SET @pid=0
		end
		declare @userdate datetime
		set @userdate=GETDATE()
		exec Sp_YKT_CardIssue_PublishCard @cardId,4,@carNo,@pid,@contactName,0,0,'10000000000000000000','[物业云平台发行]',@userdate,'物业云平台',@cardTypeId,0,@carNo,0,@carPlace,@carColour,@newStartDate,@newEndDate,@rechargeAmount,1,@Description,'000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000',@paytype,1
	    UPDATE dbo.Ykt_CardRsMoney  SET WeChatNumber=@id WHERE YKTID=@yktid
	end
END
go
----------------以上内容为5.0线下与线上脚本整合添加----------------------