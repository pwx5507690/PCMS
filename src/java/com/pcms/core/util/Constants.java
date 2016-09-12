package com.pcms.core.util;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

public class Constants {
	public static final String IMG_TEMP_PATH = "imgtemp";
	public static final String IMG_HEAD_PATH = "imgHeadPath";
	public static final String IMG_CARD_PATH = "imgCardPath";
	public static final String IMG_HEAD_SHOW = "showheadpath";
	public static final String IMG_CARD_SHOW = "showcardpath";
	public static final String SCORELINE = "scoreline";
	public static final String T_LIB = "t_lib";
	public static final int YES = 0;//是
	public static final int NO = 1;//否
	//是否打印 0：未打印  1：已打印
	public static final int IS_PRINT_0 = 0;
	public static final int IS_PRINT_1 = 1;
	
	/**
	 * 禁用启用状态
	 * 0.禁用  1.启用 99.已删除
	 */
	public static final int IS_ABLE_0=0;
	public static final int IS_ABLE_1=1;
	public static final int IS_ABLE_99=99;
	//是否缴费  0=未缴费 1=已缴费
	public static final int IS_PAY_0 = 0;
	public static final int IS_PAY_1 = 1;
	
	//导入包结束标识
	public static final String NETSIGN =  "netsign";
	
	//机构类型
	public static final int ORG_TYPE = 4;
	//======================节点Key===================
	public static final int NODE_COLLECT_INFO =1;//信息采集
	//民族
	public static final int NATION = 12;
	//性别
	public static final int GENDER=1;
	//学历
	public static final int EDUCATION=0;
	//政治面貌
	public static final int POLITIC=13;
	//是否可用
	public static final int ISABLE=2;
	//缴费设置
	public static final int PAY_SET_TYPE = 5;
	//支付方式
	public static final int PAY_TYPE = 8;
	//报名方式
	public static final int APPLY_TYPE = 9;
	//考试计划状态
	public static final int EXAM_PLAN_STATUS = 10;
	//报考信息
	public static final int EXAM_SIGN_UP = 15;
	//报考信息
	public static final int PROCESSINFO = 16;
	//部考试中心
	public static final int ORG_TYPE1 = 1;
	//省级考试机构
	public static final int ORG_TYPE2 = 2;
	//操作类型:添加
	public static final String addType="insert";
	//操作类型:修改
	public static final String editType="update";
	//准考证模板
	public static final int MODEL = 17;
	//准考证模板-计算机应用考试
	public static final String COMPUTER = "type1";
	
	//==============================考生角色功能类型==========================
	public static  final int STUDENT = 1;//考试报名信息菜单
	public static  final int EXAMNT = 2;// 考生考试类型菜单
	//===============================报名信息状态============================
	/**
	        报名信息采集	1
		报名管理	2
		在线缴费	3
		打印准考证	4
		资格预审	5
		现场审核	6
		考场编排	7
		流程结束99
	 */
	
	public static final int APPLY_STATUS1 = 1;
	public static final int APPLY_STATUS2 = 2;
	public static final int APPLY_STATUS3 = 3;
	public static final int APPLY_STATUS4 = 4;
	public static final int APPLY_STATUS5 = 5;
	public static final int APPLY_STATUS6 = 6;
	public static final int APPLY_STATUS7 = 7;
	//人员成绩已发布状态
	public static final int APPLY_STATUS_99 = 99;

	//默认的部中心机构编码
	public static final String CENTER_ORGCODE = "1001";

	//===========================区域级别=====================================
	//级别
	public static final int AREA_LEVEL_CENTER = 1;//部中心
	public static final int AREA_LEVEL_PROVINCE = 2;//省
	public static final int AREA_LEVEL_AREA = 3;//考区
	public static final int AREA_LEVEL_SPOT = 5;//考点
	//===========================考试计划状态==================================
	/**
	 * 考试计划状态:未上报
	 */
	public static final int EXAM_START =0;
	/**
	 * 考试计划状态:已上报
	 */
	public static final int EXAM_REPORT =1;
	/**
	 * 考试计划状态://已退回
	 */
	public static final int EXAM_RETURN =2;
	/**
	 * 考试计划状态://已发布
	 */
	public static final int EXAM_ISSUE =3;
	/**
	 * 考试计划状态://取消发布
	 */
	public static final int EXAM_UNISSUE =4;
	/**
	 * 考试计划状态://撤销
	 */
	public static final int EXAM_UNDO =5;
	
	/**
	 * 考试计划状态://已编排
	 */
	public static final int EXAM_ARRANGE =6;
	/**
	 * 考试计划状态://报名结束
	 */
	public static final int EXAM_APPLY_END =99;
	
	//==========================SignApply======================================
	public static final int SPOT_STATUA_0 =0;//未使用
	public static final int SPOT_STATUA_1 =1;//已关联考试计划
	public static final int SPOT_STATUA_2 =2;//已编排
	//=========================按时间段编排方式================================
	public static final String APPLY_BY_USER = "1";
	public static final String APPLY_BY_ROOM = "2";
	public static final String APPLY_BY_AVERAGE = "3";

	//==========================报名方式========================================
	//1：按时间段 2：按场次
	public static final int APPLY_TYPE_TIME = 1;
	public static final int APPLY_TYPE_ROOM = 2;
	//总缴费状态  
	//1.全部缴费成功。
	//2。有未完成缴费的订单。
	//3.待缴费 。
	//4.全部退费。
	//5,缴费失败。
	//6.有缴费失败的订单
	
	//=====================缴费的设置类型========================
	/**
	 * 缴费设置
	 * 0.全国统一
	 * 1.省市统一
	 * 2.考区统一
	 * 3.考点统一
	 */
	public static final int PAY_TYPE_0=0;
	public static final int PAY_TYPE_1=1;
	public static final int PAY_TYPE_2=2;
	public static final int PAY_TYPE_3=3;
	
	/**
	 * 业务管理员等级
	 * 1.部中心
	 * 2.省市
	 * 3.考区
	 * 5.考点
	 */
	public static final int ADMIN_TYPE_1=1;
	public static final int ADMIN_TYPE_2=2;
	public static final int ADMIN_TYPE_3=3;
	public static final int ADMIN_TYPE_5=5;
	/**
	 * t_order_info 报名订单表状态
	 * 1.未确认 。 2.已缴费。3.已失效 4.订单确认 5.掉单 6.提交支付 7.支付失败 8.掉单未支付
	 */
	public static final int ORDERINFO_STATUS_0=0;
	public static final int ORDERINFO_STATUS_1=1;
	public static final int ORDERINFO_STATUS_2=2;
	public static final int ORDERINFO_STATUS_3=3;
	public static final int ORDERINFO_STATUS_4=4;
	public static final int ORDERINFO_STATUS_5=5;
	public static final int ORDERINFO_STATUS_6=6;
	public static final int ORDERINFO_STATUS_7=7;
	public static final int ORDERINFO_STATUS_8=8;
	
	/**
	 * 订单状态为掉单
	 * 0.未确认。1.掉单支付成功。2.掉单支付失败
	 */
	public static final int ORDERINFO_TIME_OUT_STATUS_0=0;
	public static final int ORDERINFO_TIME_OUT_STATUS_1=1;
	public static final int ORDERINFO_TIME_OUT_STATUS_2=2;
	//缴费状态	
	public static final int PAY_STATUS_SUCCESS = 2;//缴费成功
	public static final int PAY_STATUS_FAIL = 7;//缴费失败
	/**
	 * 报名详情表的状态
	 * 1.待缴费
	 * 2.已缴费
	 * 3.已超时失效
	 * 4.缴费失败
	 * 超时时间
	 */
	public static final int APPLYDETAIL_STATUS_1=1;
	public static final int APPLYDETAIL_STATUS_2=2;
	public static final int APPLYDETAIL_STATUS_3=3;
	public static final int APPLYDETAIL_STATUS_4=4;
	public static final long APPLYDETAIL_TIME_OUT=120;
	/**
	 * 新闻类型 1.公共   2.公告 
	 */
	public static final int NEWS_TYPE_1=1;
	public static final int NEWS_TYPE_2=2;
	/** 工程项目磁盘配置目录  */
	public static final String HOME_PATH = "D:/cts/newsFile";
	
	public static final String dataexportpath="D:/cts/dataexport/";
	
	//public static final String SHOW_MAP_PATH="http://202.101.191.16:8099/map";
	public static final String SHOW_MAP_PATH="http://localhost:8080/map";
	public static final String IMG_PATH = "D:/cts/map";
	
	public static final String ABS_PATH = "D:/cts/upload";
	
	/**********************************支付平台***********************************/
	//支付宝	    1
	//银联支付	2
	//易宝支付	3
	//线下缴费          4
	public static final int PAY_ALIPAY = 1;
	public static final int PAY_CHINAPAY = 2;
	public static final int PAY_YEEPAY = 3;
	public static final int PAY_OFFLINE = 4;
	
	public static final int ISACC_0=0;//未对账
	public static final int ISACC_1=1;//对账成功
	public static final int ISACC_2=2;//对账不成功
	
	//角色类型1=系统管理员2=业务员
	public static final int ROLE_TYPE_ADMIN=1;
	public static final int ROLE_TYPE_BUSS=2;
	
	
	//数据包导出状态
	public static final int DATA_YES=1;//已导出
	public static final int DATA_NO=0;//未导出
	
	
	/**
	 * 支付类型, 0.全国统一 1,省市统一 2,考区统一,3考点统一
	 */
	public static final int EXAM_PAY_TYPE_0=0;
	public static final int EXAM_PAY_TYPE_1=1;
	public static final int EXAM_PAY_TYPE_2=2;
	public static final int EXAM_PAY_TYPE_3=3;
	
}


	