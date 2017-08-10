package com.atar.enums;

/**
 * 
 ***************************************************************************************************************************************************************************** 
 * 消息枚举类
 * 
 * @author :Atar
 * @createTime:2014-6-13上午11:26:56
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: 包含错误消息，和每一个请求的消息
 ***************************************************************************************************************************************************************************** 
 */
public class EnumMsgWhat {
	/*
	 * 网络错误消息枚举 start ***************************************************************
	 */
	/** 没有找到反射方法 **/
	public static final int ENotFoundMethods_Msg = 0x1001;
	/** HTTP协议错误 **/
	public static final int EHttpProtocol_Msg = 0x1002;
	/** HTTPSocket通讯错误 **/
	public static final int EHttpIO_Msg = 0x1003;
	/** XML解解析错误 **/
	public static final int EXmlParser_Msg = 0x1004;
	/** XML通讯错误 **/
	public static final int EXmlIO_Msg = 0x1005;
	/** 没有找到反射类 **/
	public static final int EClassNotFound_Msg = 0x1006;
	/** 反调指针出错 **/
	public static final int EIllegalAccess_Msg = 0x1007;
	/** 反射安全出错 **/
	public static final int ESecurity_Msg = 0x1008;
	/** 手机网络不可用 **/
	public static final int EMobileNetUseless_Msg = 0x1009;
	/** 连接服务器超时 **/
	public static final int EConnectTimeout_Msg = 0x1010;
	/** 传入参数有空 **/
	public static final int EParamHasNull_Msg = 0x1011;
	/** 传入无效的参数 **/
	public static final int EParamUnInvalid_Msg = 0x1012;
	/** Json解析出错 **/
	public static final int EJsonParser_Msg = 0x1013;
	/** 未知错误异常 **/
	public static final int ENotDefine_Msg = 0x1014;
	/** 不显示提示toast 默认值用负数 **/
	public static final int NetWorkMsg1WhithoutToast = -0x1015;
	/** 显示提示toast 默认值用正数才显示 **/
	public static final int NetWorkMsg1WhithToast = 0x1016;
	/** 用于多线程，代表哪一个线程 默认值用负数 **/
	public static final int NetWorkThreadMsg2 = -0x1017;
	/** 服务器异常400 **/
	public static final int EHttpRequestFail400 = 0x1018;
	/** 服务器异常403 ip被封了 **/
	public static final int EHttpRequestFail403 = 0x1019;
	/** 服务器异常404 **/
	public static final int EHttpRequestFail404 = 0x1020;
	/** 服务器异常502 **/
	public static final int EHttpRequestFail502 = 0x1021;
	/** 服务器异常503 **/
	public static final int EHttpRequestFail503 = 0x1022;
	/** 服务器异常504 **/
	public static final int EHttpRequestFail504 = 0x1023;
	/** 服务器异常找不到主机host **/
	public static final int EUnknownHost_msg = 0x1024;
	/** 服务器异常找不到主机host **/
	public static final int EUnknownService_msg = 0x1025;
	/** 服务器异常找不到主机host **/
	public static final int EUnsupportedEncoding_msg = 0x1026;
	/** 服务器异常500 **/
	public static final int EHttpRequestFail500 = 0x1027;
	/** 默认http请求值 **/
	public static final int HttpDefaultRequest_Msg = 0x1029;
	/*
	 * 网络错误消息枚举 end *********************************************************************
	 */

	/********************** 网络错误信息枚举end*******预留100个 *************************************/

	/** 最小刷新延迟标志 */
	public static final int REFRESH_MIN_TIME = 0x1200;
	/** 正在刷新标志 */
	public static final int REFRESHING_VIEW = 0x1201;
	/** 去执行下拉刷新动作标志 */
	public static final int REFRESH_PULL_DOWN = 0x1202;
	/** 去执行上拉加载更多标志 */
	public static final int REFRESH_PULL_UP = 0x1203;
	/** 上拉下拉刷新操作 */
	public static final int REFRESH_HANDLER = 0x1204;
	/** 操作1 */
	public static final int REFRESH_HANDLER1 = 0x1205;
	/** 操作2 */
	public static final int REFRESH_HANDLER2 = 0x1206;
	/** 操作3 */
	public static final int REFRESH_HANDLER3 = 0x1207;
	/** 操作4 */
	public static final int REFRESH_HANDLER4 = 0x1208;
	/** 加载1 */
	public static final int REFRESH_LOADING1 = 0x1209;
	/** 加载2 */
	public static final int REFRESH_LOADING2 = 0x1210;
	/** 刷新中出现网络等问题后处理标志 */
	public static final int REFRESH_ERROR = 0x1211;
	/** 刷新完成标志处理提示 */
	public static final int REFRESH_COMPLETE = 0x1212;
	/** 刷新完成标志处理提示1 */
	public static final int REFRESH_COMPLETE1 = 0x1213;
	/** 刷新完成标志处理提示2 */
	public static final int REFRESH_COMPLETE2 = 0x1214;
	/** 刷新完成标志处理提示3 http请求响应完成 */
	public static final int REFRESH_COMPLETE3 = 0x1215;
	/** 正在播放 */
	public static final int AUDIO_PLAYING = 0x1216;
	/** 播放停止 */
	public static final int AUDIO_STOP = 0x1217;
	/** 操作5 */
	public static final int REFRESH_HANDLER5 = 0x1218;
	/** 操作6 */
	public static final int REFRESH_HANDLER6 = 0x1219;
	/** 操作7 */
	public static final int REFRESH_HANDLER7 = 0x1220;

	/******************************* 刷新操作end*******预留100个 *********************************************/

	/** 从本地加载 */
	public static final int LOAD_FROM_SQL = 0x1300;
	/** 从本地加载2 */
	public static final int LOAD_FROM_SQL1 = 0x1301;
	/** 从本地加载3 */
	public static final int LOAD_FROM_SQL2 = 0x1302;
	/** 从本地加载4 */
	public static final int LOAD_FROM_SQL3 = 0x1303;
	/** 从数据库加载完成 */
	public static final int LOAD_FROM_SQL_COMPLETE = 0x1304;
	/** 从本地加载第二组数据1 */
	public static final int LOAD_FROM_SQL_COMPLETE_1 = 0x1305;
	/** 从本地加载第二组数据2 */
	public static final int LOAD_FROM_SQL_COMPLETE_2 = 0x1306;
	/** 从本地加载第二组数据3 */
	public static final int LOAD_FROM_SQL_COMPLETE_3 = 0x1307;
	/** 转化数据1 */
	public static final int TRANSLATE_COMPLETE = 0x1308;
	/** 转化数据2 */
	public static final int TRANSLATE_COMPLETE1 = 0x1309;
	/** 转化数据3 */
	public static final int TRANSLATE_COMPLETE2 = 0x1310;
	/** 转化数据4 */
	public static final int TRANSLATE_COMPLETE3 = 0x1311;
	/** 搜索 */
	public static final int LOAD_FROM_SQL_LIKE = 0x1312;
	/** 搜索1 */
	public static final int LOAD_FROM_SQL_LIKE1 = 0x1313;
	/** 搜索2 */
	public static final int LOAD_FROM_SQL_LIKE2 = 0x1314;
	/** 搜索3 */
	public static final int LOAD_FROM_SQL_LIKE3 = 0x1315;
	/** 搜索 */
	public static final int LOAD_FROM_SQL_LIKE_COMPLETE = 0x1316;
	/** 搜索1 */
	public static final int LOAD_FROM_SQL_LIKE_COMPLETE1 = 0x1317;
	/** 搜索2 */
	public static final int LOAD_FROM_SQL_LIKE_COMPLETE2 = 0x1318;
	/** 搜索3 */
	public static final int LOAD_FROM_SQL_LIKE_COMPLETE3 = 0x1319;
	/** 操作UI */
	public static final int HANDLER_CHANGE_UI = 0x1320;
	/** 操作UI1 */
	public static final int HANDLER_CHANGE_UI1 = 0x1321;
	/** 操作UI2 */
	public static final int HANDLER_CHANGE_UI2 = 0x1322;
	/** 操作UI3 */
	public static final int HANDLER_CHANGE_UI3 = 0x1323;
	/** 操作UI4 */
	public static final int HANDLER_CHANGE_UI4 = 0x1324;

	/******************************* 操作本地数据库end*******预留100个 *********************************************/

	/******************************* 以下操作网络消息枚举***start *******************************************************/
	/** 检查用户状态 **/
	public static final int EInterface_Check_User_Type = 0x2000;
	/** 登陆 **/
	public static final int EInterface_Login = 0x2001;
	/** 验证找回密码的第一步 **/
	public static final int EInterface_Check_User_Name = 0x2002;
	/** 检验邮箱 **/
	public static final int EInterface_Check_Mail = 0x2003;
	/** 注册时检查用户名 **/
	public static final int EInterface_Check_Regist_User_Name = 0x2004;
	/** 注册用户 **/
	public static final int EInterface_Regist = 0x2005;
	/** 获取手机短信认证码 **/
	public static final int EInterface_Get_Mobile_Code = 0x2006;
	/** 激活手机 **/
	public static final int EInterface_Submit_Mobile = 0x2007;
	/** 获取认证状态 **/
	public static final int EInterface_Mcheck_Mobile = 0x2008;
	/** 获取个股 **/
	public static final int EInterface_Get_Stock_List = 0x2009;
	/** 从新浪获取个股 **/
	public static final int EInterface_Get_Stock_List_From_Sina = 0x2010;
	/** 获取酷炫自选股组 **/
	public static final int EInterface_Get_Cool_Stock_Group = 0x3001;
	/** 获取行情 **/
	public static final int EInterface_Get_Market = 0x3002;
	/** 移动个股位置 **/
	public static final int EInterface_Move_Stock_Position = 0x3003;
	/** 删除个股 **/
	public static final int EInterface_Detel_Stock = 0x3004;
	/** 获取酷炫中讨论 **/
	public static final int EInterface_Get_Talk = 0x3005;
	/** 获取酷炫中新闻 **/
	public static final int EInterface_Get_News_Data = 0x3006;
	/** 获取酷炫公告 **/
	public static final int EInterface_Get_Bulletin = 0x3007;
	/** 酷炫中研报数据 **/
	public static final int EInterface_Get_Report_Data = 0x3008;
	/** 获取动态组 **/
	public static final int EInterface_Get_Dynamic_Group = 0x3009;
	/** 获取动态列表 **/
	public static final int EInterface_Get_Dynamic_List = 0x3010;
	/** 查看一位用户资料以及与该用户的关系 **/
	public static final int EInterface_Api_Get_User_Info = 0x3011;
	/** 退出登陆 **/
	public static final int EInterface_Login_Out = 0x3012;
	/** 搜索热度 **/
	public static final int EInterface_Get_Search_Hot_List = 0x3013;
	/** 搜索股票 **/
	public static final int EInterface_Search_Stock = 0x3014;
	/** 向分组里面添加自个股 **/
	public static final int EInterface_Add_Stock_To_Group = 0x3015;
	/** 修改分组 **/
	public static final int EInterface_Update_Group = 0x3016;
	/** 删除组 **/
	public static final int EInterface_Detel_Group = 0x3017;
	/** 添加组 **/
	public static final int EInterface_Add_Group = 0x3018;
	/** 是否关注个股 **/
	public static final int EInterface_Is_Care_Stock = 0x3019;
	/** 获取个股讨论数据 **/
	public static final int EInterface_Get_Stock_Talk_List = 0x3020;
	/** 获取个股股吧数据 **/
	public static final int EInterface_Get_Stock_Forum_List = 0x3021;
	/** 获取个股新闻(type=0)、公告(type=1)，研报(type=2) **/
	public static final int EInterface_Get_Stock_NBR = 0x3032;
	/** 获取好友列表 **/
	public static final int EInterface_Get_Friends_List = 0x3033;
	/** 获取粉丝列表 **/
	public static final int EInterface_Get_Fans_List = 0x3034;
	/** 获取今日赞 **/
	public static final int EInterface_Get_Wonder_Topic_List = 0x3035;
	/** 获取我的主贴 **/
	public static final int EInterface_Get_My_Quote_List = 0x3036;
	/** 获取主贴 **/
	public static final int EInterface_Get_Main_Topic = 0x3037;
	/** 获取说说详细页 **/
	public static final int EInterface_Get_Topic_List = 0x3038;
	/** 获取说说回复 **/
	public static final int EInterface_Get_Reply_Shuo_List = 0x3039;
	/** 只看楼主 **/
	public static final int EInterface_Look_User_Quote = 0x3040;
	/** 我的评论 **/
	public static final int EInterface_Get_Reply_List = 0x3041;
	/** 获取我的收藏 **/
	public static final int EInterface_Get_My_Collection = 0x3042;
	/** 获取我的订阅 **/
	public static final int EInterface_Get_My_Order = 0x3043;
	/** 将帖子添加到收藏 **/
	public static final int EInterface_Add_Quote_To_Collection = 0x3044;
	/** 将帖子赞 **/
	public static final int EInterface_Add_To_Userful = 0x3045;
	/** 将帖子加油 **/
	public static final int EInterface_Add_To_Best = 0x3046;
	/** 发表说说 **/
	public static final int EInterface_Write_Shuo_Shuo = 0x3047;
	/** 获取用户的说说（个人中心切换里） **/
	public static final int EInterface_Get_User_Topic = 0x3048;
	/** 获取自己的信息 **/
	public static final int EInterface_Get_Self_Ingfo = 0x3049;
	/** 获取淘股论坛数据 **/
	public static final int EInterface_Get_Forum_List = 0x3050;
	/** 获取我的说说 **/
	public static final int EInterface_Get_My_Topic_List = 0x3051;
	/** 更改自己信息 **/
	public static final int EInterface_Update_Info = 0x3052;
	/** 获取交友信息 **/
	public static final int EInterface_Get_Conform_Friends_List = 0x3053;
	/** 贴子引用 **/
	public static final int EInterface_Get_Quote_Tans_List = 0x3054;
	/** 好友确认信息，同意，同意并加对方为好友，拒绝 **/
	public static final int EInterface_Do_Conform_Friends_Status = 0x3055;
	/** 稿费信息 **/
	public static final int EInterface_Get_Money_Pay_List = 0x3056;
	/** 赞提醒 **/
	public static final int EInterface_Get_Lighten_List = 0x3057;
	/** 说说回复 **/
	public static final int EInterface_Get_Weo_Bo_Re_List = 0x3058;
	/** 删除黑名单 **/
	public static final int EInterface_Del_Bad_Friend = 0x3059;
	/** 获取信息新闻 **/
	public static final int EInterface_Get_New_News_List = 0x3060;
	/** 获取站内信 **/
	public static final int EInterface_Get_Msg_Text = 0x3061;
	/** 信息@我 **/
	public static final int EInterface_Get_At_Me_List = 0x3062;
	/** 关注用户 **/
	public static final int EInterface_Add_Focus = 0x3064;
	/** 用户中心添加订阅 **/
	public static final int EInterface_Add_Book = 0x3065;
	/** 拉黑用户 **/
	public static final int EInterface_Add_Bad_Friend = 0x3066;
	/** 解除黑名单 **/
	public static final int EInterface_Cancle_Band_Friends = 0x3067;
	/** 将说说收藏 **/
	public static final int EInterface_Add_Col_Shuo = 0x3068;
	/** 将说说加油 **/
	public static final int EInterface_Add_Weibo_Gold_Useful = 0x3069;
	/** 将说说赞 **/
	public static final int EInterface_Add_Weibo_Useful = 0x3070;
	/** 用户中心加好友 **/
	public static final int EInterface_Add_Good_Friends = 0x3071;
	/** 用户中心删除好友 **/
	public static final int EInterface_Detel_Good_Friend = 0x3072;
	/** 用户中心添加用户（带验证消息） **/
	public static final int EInterface_Add_User_Info = 0x3073;
	/** 获取站内信聊天记录 **/
	public static final int EInterface_Get_Msg_Text_Chat_List = 0x3074;
	/** 发送站内信（聊天） **/
	public static final int EInterface_Send_Msg_Text = 0x3075;
	/** 绑定推送 **/
	public static final int EInterface_Bing_Push = 0x3076;
	/** 将贴子加油 **/
	public static final int EInterface_Do_Light_Quite = 0x3077;
	/** 发起站内信 **/
	public static final int EInterface_Send_Msg = 0x3078;
	/** 评论贴子 **/
	public static final int EInterface_Reply_Post = 0x3079;
	/** 回复说说 **/
	public static final int EInterface_Reply_Shuo_Shuo = 0x3080;
	/** 轮循获取信息数量 **/
	public static final int EInterface_Get_New_Info_Count = 0x3081;
	/** 获取某组下成员 **/
	public static final int EInterface_Get_Group_Friend = 0x3082;
	/** 获取用户所有好友 **/
	public static final int EInterface_Get_All_Friend_List = 0x3083;
	/** 添加成员 **/
	public static final int EInterface_Add_Friend_To_Group = 0x3084;
	/** 从组中删除好友 **/
	public static final int EInterface_Dete_From_Group = 0x3085;
	/** 个股排序 **/
	public static final int EInterface_Stock_Sort = 0x3086;
	/** 动态分组排序 **/
	public static final int EInterface_Sort_Action_Group = 0x3087;
	/** 搜索标题 **/
	public static final int EInterface_Search_Title = 0x3088;
	/** 搜索用户 **/
	public static final int EInterface_Search_User = 0x3089;
	/** 搜索内容 **/
	public static final int EInterface_Search_Content = 0x3090;
	/** 添加到特别关注 **/
	public static final int EInterface_Add_To_Special_Friends = 0x3091;
	/** 删除特别关注 **/
	public static final int EInterface_Delete_Special_Friends = 0x3092;
	/** 发主贴 **/
	public static final int EInterface_Add_Topic = 0x3093;
	/** 推送总开关 **/
	public static final int EInterface_App_Push_Switch = 0x3094;
	/** 推送类型 **/
	public static final int EInterface_App_Push_Type = 0x3095;
	/** 推送时间段 **/
	public static final int EInterface_App_Push_Time = 0x3096;
	// /** 第三方登陆 **/
	// public static final int EInterface_Login_Third = 0x3097;
	// /** 注册第三方 **/
	// public static final int EInterface_Register_Third = 0x3098;
	/** 检验新浪登陆 **/
	// public static final int EInterface_Check_Sina_Login = 0x3099;
	/** 修改密码 **/
	public static final int EInterface_Update_Psw = 0x3100;
	/** 获取用户可用积分 **/
	public static final int EInterface_Get_User_Score = 0x3101;
	/** 提交打赏积分 **/
	public static final int EInterface_Summit_PCScore = 0x3102;
	/** 提交打赏积分说说 **/
	public static final int EInterface_Summit_PCScore_Shuo = 0x3013;
	/** 删除说说 **/
	public static final int EInterface_Delete_Shuoshuo = 0x3103;
	/** 获取积分记录 **/
	public static final int EInterface_Get_Points = 0x3104;
	/** 引道时添加个股 **/
	public static final int EInterface_Add_To_Cool_Stock = 0x3105;
	/** 引道时赞用户列表 **/
	public static final int EInterface_Get_Guide_User_List = 0x3106;
	/** 开通好友动态 **/
	public static final int EInterface_Open_Action = 0x3107;
	/** 是否绑定 **/
	public static final int EInterface_Is_Bind = 0x3108;
	/** 获取新浪绑定用户信息 **/
	public static final int EInterface_Get_Sina_User_Info = 0x3109;
	/** 获取QQ绑定用户信息 **/
	public static final int EInterface_Get_QQUser_Info = 0x3110;
	/** 解除绑定 **/
	public static final int EInterface_Delete_Bind = 0x3111;
	/** 只做绑定不登陆 **/
	public static final int EInterface_Single_Bind = 0x3112;
	/** 检查用户名是否存在 **/
	public static final int EInterface_Check_ISUser_Name = 0x3113;
	/** 获取收藏数 **/
	public static final int EInterface_Get_Col_Num = 0x3114;
	/** 删除收藏 **/
	public static final int EInterface_Delete_Col = 0x3115;
	/** 获取省市城市 **/
	public static final int EInterface_Get_City_List = 0x3116;
	/** 取消关注 **/
	public static final int EInterface_Delete_Focus = 0x3117;
	/** 获取用户信息2 **/
	public static final int EInterface_Get_User_Info2 = 0x3118;
	/** 删除主说说 **/
	public static final int EInterface_Del_Main_Shuoshuo = 0x3119;
	/** 支付宝先下单到我们自己服务器上 **/
	public static final int EInterface_Pay_To_Server = 0x3120;
	/** 引用帖子 **/
	public static final int EInterface_Quote_Topic = 0x3121;
	/** 解除绑定 **/
	public static final int EInterface_Bind_Out_Login = 0x3121;
	/** 最近@过的人 **/
	public static final int EInterface_Last_At_User = 0x3122;
	/** 购买加油券 **/
	public static final int EInterface_Get_Buy_Gold = 0x3123;
	/** 购买加油券提交 **/
	public static final int EInterface_Buy_Gold_Action = 0x3124;
	/** 得到加油券记录 **/
	public static final int EInterface_Get_Gold_Num_List = 0x3125;
	/** 得到加油券使用记录 **/
	public static final int EInterface_Get_User_Gold_Use = 0x3126;
	/** 获取标签吧 **/
	public static final int EInterface_Get_Key_Bar = 0x3127;
	/** 获取收益 **/
	public static final int EInterface_Get_Theme_Income = 0x3128;
	/** 获取主题关注列表 **/
	public static final int EInterface_Get_Theme_Care_List = 0x3130;
	/** 获取标签关注 **/
	public static final int EInterface_Get_Tab_Atten = 0x3131;
	/** 添加主题关注 **/
	public static final int EInterface_Add_Theme_Atten = 0x3132;
	/** 获取当关主题信息 **/
	public static final int EInterface_Get_Current_Subject_Info = 0x3133;
	/** 取消主题关注 **/
	public static final int EInterface_Delete_Theme_Atten = 0x3134;
	/** 获取主题讨论 **/
	public static final int EInterface_Get_Theme_Talk_List = 0x3135;
	/** 获取主题讨论 **/
	public static final int EInterface_Get_Theme_NBR_List = 0x3136;
	/** 获得APP网友活跃 **/
	public static final int EInterface_Get_User_Active = 0x3137;
	/** 获取网友活跃详情 **/
	public static final int EInterface_Get_Tl_UADetailed = 0x3138;
	/** 根据标签 获取网友 **/
	public static final int EInterface_Get_Tags_Attr = 0x3139;
	/** 获取主题个股 **/
	public static final int EInterface_Get_Historical_Stocks_Delta = 0x3040;
	/** 获取经期 **/
	public static final int EInterface_Get_Subject_Base_Date = 0x3041;
	/** 获取沪深涨幅数据（start=获取经期返回的日期值） **/
	public static final int EInterface_Get_Hushen_Daily_Return = 0x3042;
	/** 获取对应主题的涨幅数据 （start=获取经期返回的日期值 subjectId=通联主题ID） **/
	public static final int EInterface_Get_Subject_Daily_Return = 0x3043;
	/** 搜索主题 **/
	public static final int EInterface_Seach_Theme = 0x3042;
	/** 批量获取通联信息 **/
	public static final int EInterface_Batch_Themes_Info = 0x3043;
	/** 搜索单个主题 **/
	public static final int EInterface_Search_Single_theme = 0x3044;
	/** 获取广告图 **/
	public static final int EInterface_Get_App_Ad_Img = 0x3045;

	/**
	 * 新闻 公告 研报 type 值区分 酷炫中新闻（11）酷炫中公告（12）酷炫中研报（13）信息中新闻（21）信息中公告（22）信息中研报（23）
	 * 个股吧中新闻（31）个股吧中公告（32）个股吧中研报（33） 主题吧中新闻（41）主题吧中公告（42）主题吧中研报（43）
	 */
	public static final int EInterface_Get_NBRList = 0x3046;
	/** 全部牛帖 **/
	public static final int EInterface_Get_All_NTopic = 0x3047;
	/** 全部牛帖从通联获取股票信息 **/
	public static final int EInterface_Get_TLStock_Info = 0x3048;
	/** 取得牛人等级 **/
	public static final int EInterface_Get_Niu_Ren_Rank = 0x3049;
	/** 取得长胜数据 **/
	public static final int EInterface_Get_Chang_Sheng = 0x3050;
	/** 得到牛人牛帖广告 **/
	public static final int EInterface_Get_NTopic_Ad = 0x3051;
	/** 淘股宝交易计划列表（加密） **/
	public static final int EInterface_Get_Api_Deal_Plan = 0x3152;
	/** 获取交易计划详情信息(包含交易计划 及 评论信息)： **/
	public static final int EInterface_Get_View_New_Bo = 0x3153;
	/** 添加订阅交易计划 **/
	public static final int EInterface_Add_Plan = 0x3154;
	/** Ta的战绩 **/
	public static final int EInterface_Get_Api_Niu_Bo = 0x3155;
	/** 关注我的关注达人 **/
	public static final int EInterface_Atte_Sub_Api_User = 0x3156;
	/** 取消我的关注达人 **/
	public static final int EInterface_Del_Attent_User = 0x3157;
	/** 淘股宝计划 交流信息获取更多评论 **/
	public static final int EInterface_Get_More_Niu_Bo_Msg = 0x3158;
	/** 搜索淘股宝交易计划 **/
	public static final int EInterface_Search_Api_Plan = 0x3159;
	/** 交流区 评论 **/
	public static final int EInterface_Sub_Api_Dp_Msg = 0x3160;
	/** 交流区 评论回复 **/
	public static final int EInterface_Sub_Api_Dp_Re_Msg = 0x3161;
	/** 淘股宝交流数据删除 (UrlType:0删除评论）(UrlType:1删除评论中的自已的回复） **/
	public static final int EInterface_Del_Deal_Plan_Relpy = 0x3162;
	/** 设置交易计划状态开关 **/
	public static final int EInterface_Push_Deal_Plan_Status = 0x3163;

	/** 个人中心改版请求接口 **/
	public static final int EInterface_Get_Api_Dynamic = 0x3164;
	/** 牛人牛帖列表比赛 **/
	public static final int EInterface_Get_Api_Nrntmatch_List = 0x3165;
	/** 获取周赛月赛信息 **/
	public static final int EInterface_Get_Api_Nrntmatch = 0x3166;
	/** 牛人牛帖当前( 周赛 月赛) **/
	public static final int EInterface_Get_Api_Nrntposts = 0x3167;

	/** 获取他人自选 **/
	public static final int EInterface_Api_Get_Ta_Stock = 0x4168;
	/** 获取他人牛帖排行榜 **/
	public static final int EInterface_Get_App_My_Niutie = 0x4169;
	/** 主题是否关注 **/
	public static final int EInterface_Api_Is_Atte_Tags = 0x4170;
	/** 牛人牛帖搜索 **/
	public static final int EInterface_Search_Nr = 0x4171;
	/** 取微信token **/
	public static final int EInterface_Get_Wei_Xin_Token = 0x4173;
	/** 获取微信用户信息 **/
	public static final int EInterface_Get_Weixin_User_Info = 0x4174;
	/** 浏览用户登陆**/
	public static final int EInterface_Visitor_Login = 0x4178;
	/** 开通引导 **/
	public static final int EInterface_Open_Yin_Dao = 0x4179;
	/** 获取游客关注数 **/
	public static final int EInterface_Get_Visitor_Flllows_Count = 0x4175;
	/** 获取引导数据 **/
	public static final int EInterface_Get_Yin_Dao_Data = 0x4176;
	/** 安装来在哪一个渠道 **/
	public static final int EInterface_App_Install_From_Channel = 0x4177;

	// /** 获取他的牛帖 **/
	// public static final int EInterface_Get_His_Niu_Tie = 0x5101;
	/** 跟买 **/
	public static final int EInterface_Get_Nunr_Comp_User_Id = 0x4172;
	/** 交易开关 **/
	public static final int EInterface_Get_Swich_Status = 0x3514;
	/** 实盘交易登陆成功绑定接口 **/
	public static final int EInterface_Add_Bond = 0x3515;

	/*******************************以下太平洋**start*******************************************************/
	/**********handler*****/
	/** 3.1登录**/
	public static final int EInterface_Handler_Login = 0x3301;
	/** 3.2 修改密码 **/
	public static final int EInterface_Handler_Modifypwd = 0x3302;
	/** 3.3 买卖 **/
	public static final int EInterface_Handler_Order = 0x3303;
	/** 3.4 撤单 **/
	public static final int EInterface_Handler_Withdraw = 0x3304;
	/** 3.5 查股东信息 **/
	public static final int EInterface_Handler_Query_Share_Holder = 0x3305;
	/** 3.6 查资金 **/
	public static final int EInterface_Handler_Query_Money = 0x3306;
	/** 3.7 查股份 **/
	public static final int EInterface_Handler_Query_Shares = 0x3307;
	/** 3.8 查当日委托 **/
	public static final int EInterface_Handler_Query_Order = 0x3308;
	/** 3.9 查当日成交 **/
	public static final int EInterface_Handler_Query_Knock = 0x3309;
	/** 3.10 查当最大可买量 **/
	public static final int EInterface_Handler_Query_Max_Buy = 0x3310;
	/** 3.11 查当日可撤单委托 **/
	public static final int EInterface_Handler_Query_With_Draw_Order = 0x3311;
	/** 3.12 资金流水 **/
	public static final int EInterface_Handler_Query_Money_Serial = 0x3312;
	/** 3.13 查历史委托 **/
	public static final int EInterface_Handler_Query_His_Order = 0x3313;
	/** 3.14 查历史成交 **/
	public static final int EInterface_Handler_Query_His_Knock = 0x3314;

	/**3.18 查银行**/
	public static final int EInterface_Handler_Query_Bank = 0x3318;
	/** 3.19 查客户存管银行 **/
	public static final int EInterface_Handler_Query_Cust_Bank = 0x3319;
	/** 3.20 银行转证券 **/
	public static final int EInterface_Handler_Bank_To_Secu = 0x3320;
	/** 3.21 证券转银行 **/
	public static final int EInterface_Handler_Secu_To_Bank = 0x3321;
	/** 3.22 查银行余额 **/
	public static final int EInterface_Handler_Query_Bank_Money = 0x3322;
	/** 3.23 查转账状态 **/
	public static final int EInterface_Handler_Query_Trans_Serial = 0x3323;
	/** 3.24 查历史转账流水 **/
	public static final int EInterface_Handler_Query_His_Trans_Serial = 0x3324;
	/** 3.25 查客户资料 **/
	public static final int EInterface_Handler_Query_Cust_Info = 0x3325;

	/** 获取行情**/
	public static final int EInterface_Handler_Get_Secu_Detail = 0x3326;
	/** 获取多只股票行情 **/
	public static final int EInterface_Handler_Get_Multi_Secu_Detail = 0x3327;
	/** 获取市场码表 **/
	public static final int EInterface_Handler_Get_Code_Table = 0x3328;
	/** 获取码表扩展信息 **/
	public static final int EInterface_Handler_Get_Ext_Ct = 0x3329;
	/** 获取市场码表扩展信息 **/
	public static final int EInterface_Handler_Get_Market_Ext_Ct = 0x3330;
	/**获取验证码 **/
	public static final int EInterface_Handler_Get_Login_Image_Code = 0x3331;

	/**********Callback回调*****/
	/** 3.1登录**/
	public static final int EInterface_Callback_Login = 0x3401;
	/** 3.2 修改密码 **/
	public static final int EInterface_Callback_Modifypwd = 0x3402;
	/** 3.3 买卖 **/
	public static final int EInterface_Callback_Order = 0x3403;
	/** 3.4 撤单 **/
	public static final int EInterface_Callback_Withdraw = 0x3404;
	/** 3.5 查股东信息 **/
	public static final int EInterface_Callback_Query_Share_Holder = 0x3405;
	/** 3.6 查资金 **/
	public static final int EInterface_Callback_Query_Money = 0x3406;
	/** 3.7 查股份 **/
	public static final int EInterface_Callback_Query_Shares = 0x3407;
	/** 3.8 查当日委托 **/
	public static final int EInterface_Callback_Query_Order = 0x3408;
	/** 3.9 查当日成交 **/
	public static final int EInterface_Callback_Query_Knock = 0x3409;
	/** 3.10 查当最大可买量 **/
	public static final int EInterface_Callback_Query_Max_Buy = 0x3410;
	/** 3.11 查当日可撤单委托 **/
	public static final int EInterface_Callback_Query_With_Draw_Order = 0x3411;
	/** 3.12 资金流水 **/
	public static final int EInterface_Callback_Query_Money_Serial = 0x3412;
	/** 3.13 查历史委托 **/
	public static final int EInterface_Callback_Query_His_Order = 0x3413;
	/** 3.14 查历史成交 **/
	public static final int EInterface_Callback_Query_His_Knock = 0x3414;

	/**3.18 查银行**/
	public static final int EInterface_Callback_Query_Bank = 0x3418;
	/** 3.19 查客户存管银行 **/
	public static final int EInterface_Callback_Query_Cust_Bank = 0x3419;
	/** 3.20 银行转证券 **/
	public static final int EInterface_Callback_Bank_To_Secu = 0x3420;
	/** 3.21 证券转银行 **/
	public static final int EInterface_Callback_Secu_To_Bank = 0x3421;
	/** 3.22 查银行余额 **/
	public static final int EInterface_Callback_Query_Bank_Money = 0x3422;
	/** 3.23 查转账状态 **/
	public static final int EInterface_Callback_Query_Trans_Serial = 0x3423;
	/** 3.24 查历史转账流水 **/
	public static final int EInterface_Callback_Query_His_Trans_Serial = 0x3424;
	/** 3.25 查客户资料 **/
	public static final int EInterface_Callback_Query_Cust_Info = 0x3425;

	/** 获取行情**/
	public static final int EInterface_Callback_Get_Secu_Detail = 0x3426;
	/** 获取多只股票行情 **/
	public static final int EInterface_Callback_Get_Multi_Secu_Detail = 0x3427;
	/** 获取市场码表 **/
	public static final int EInterface_Callback_Get_Code_Table = 0x3428;
	/** 获取码表扩展信息 **/
	public static final int EInterface_Callback_Get_Ext_Ct = 0x3429;
	/** 获取市场码表扩展信息 **/
	public static final int EInterface_Callback_Get_Market_Ext_Ct = 0x3430;
	/** 获取验证码 **/
	public static final int EInterface_Callback_Get_Login_Image_Code = 0x3431;

	/********页面跳转中参数传递***************/
	/** 实盘买入 单股股票参数回传js**/
	public static final int EInterface_Handler_TpyBOneOrder_Query_StockCode = 0x3500;
	/** 实盘买入 单股股票参数回传js **/
	public static final int EInterface_Handler_TpyBManyOrder_Query_StockCodes = 0x3501;
	/** 多股买入 循环调用单股买入 **/
	public static final int EInterface_Handler_TpyBManyOrder_Commit_Buy_StockList = 0x3502;
	/** 多股卖出 **/
	public static final int EInterface_Handler_TpySSellManyOrder_Sell_Many_Stock = 0x3503;
	/** 委托回传数据 **/
	public static final int EInterface_Handler_TpyEnTrust_Init_Entrust = 0x3504;
	/** 3.3 买卖判断交易密码过期 **/
	public static final int EInterface_Handler_Order_EXPIRE = 0x3505;
	/** 重置交易密码 **/
	public static final int EInterface_Handler_Relogin = 0x3506;
	/** 重置登录失败 **/
	public static final int EInterface_Handler_Relogin_Failure = 0x3507;
	/** 多股买入 交易密码过期 **/
	public static final int EInterface_Handler_TpyBManyOrder_Commit_Buy_StockList_EXPIRE = 0x3508;
	/** 多股卖出 交易密码过期 **/
	public static final int EInterface_Handler_TpySSellManyOrder_Sell_Many_Stock_EXPIRE = 0x3509;
	/** 委托页面 完成返回页面 **/
	public static final int EInterface_Handler_TpyEnTrust_FINISH_TO_TRADE = 0x3510;
	/** 3.20 银行转证券 交易密码过期 **/

	public static final int EInterface_Handler_Bank_To_Secu_EXPIRE = 0x3511;
	/** 3.21 证券转银行交易密码过期 **/
	public static final int EInterface_Handler_Secu_To_Bank_EXPIRE = 0x3512;
	/** 3.4 撤单 交易密码过期 **/
	public static final int EInterface_Handler_Withdraw_EXPIRE = 0x3513;

	/**委托页面进入查询**/
	public static final int EInterface_Handler_TpyQuery_Entrust = 0x3516;
	/** 3.5 查股东信息 交易密码过期 **/
	public static final int EInterface_Handler_Query_Share_Holder_EXPIRE = 0x3317;
	/** 3.6 查资金 交易密码过期 **/
	public static final int EInterface_Handler_Query_Money_EXPIRE = 0x3318;
	/** 3.7 查股份 交易密码过期 **/
	public static final int EInterface_Handler_Query_Shares_EXPIRE = 0x3319;
	/** 3.8 查当日委托 交易密码过期 **/
	public static final int EInterface_Handler_Query_Order_EXPIRE = 0x3320;
	/** 3.9 查当日成交 交易密码过期 **/
	public static final int EInterface_Handler_Query_Knock_EXPIRE = 0x3321;
	/** 3.10 查当最大可买量 交易密码过期 **/
	public static final int EInterface_Handler_Query_Max_Buy_EXPIRE = 0x3322;
	/** 3.11 查当日可撤单委托 交易密码过期 **/
	public static final int EInterface_Handler_Query_With_Draw_Order_EXPIRE = 0x3323;
	/** 3.12 资金流水 交易密码过期 **/
	public static final int EInterface_Handler_Query_Money_Serial_EXPIRE = 0x3324;
	/** 3.13 查历史委托 交易密码过期 **/
	public static final int EInterface_Handler_Query_His_Order_EXPIRE = 0x3325;
	/** 3.14 查历史成交 交易密码过期 **/
	public static final int EInterface_Handler_Query_His_Knock_EXPIRE = 0x3326;

	/**3.18 查银行 交易密码过期**/
	public static final int EInterface_Handler_Query_Bank_EXPIRE = 0x3327;
	/** 3.19 查客户存管银行 交易密码过期 **/
	public static final int EInterface_Handler_Query_Cust_Bank_EXPIRE = 0x3328;
	/** 3.22 查银行余额 交易密码过期 **/
	public static final int EInterface_Handler_Query_Bank_Money_EXPIRE = 0x3329;
	/** 3.23 查转账状态 交易密码过期 **/
	public static final int EInterface_Handler_Query_Trans_Serial_EXPIRE = 0x3330;
	/** 3.24 查历史转账流水 交易密码过期 **/
	public static final int EInterface_Handler_Query_His_Trans_Serial_EXPIRE = 0x3331;
	/** 3.25 查客户资料 交易密码过期 **/
	public static final int EInterface_Handler_Query_Cust_Info_EXPIRE = 0x3332;
	/*******************************以下太平洋**end*******************************************************/
	/** 用户同意被关注**/
	public static final int EInterface_Agree_Add_Friend = 0x4180;// 用户同意被关注
	/** 接受关注并且关注对方注**/
	public static final int EInterface_Agree_And_Req = 0x4181;// 接受关注并且关注对方
	/** 拒绝关注**/
	public static final int EInterface_Refuse_Focus = 0x4182;// 拒绝关注
	/**检查用户名是否存在*/
	public static final int EInterface_Is_Name_Ok = 0x4183;
	/** 游戏用户基础信息，外带未完成任务数，是否签到 */
	public static final int EInterface_Base_Kline = 0x5102;
	/** 游戏签到操作 */
	public static final int EInterface_Signin = 0x5103;
	/** 游戏数据上传 */
	public static final int EInterface_Kl_Opera_Record = 0x5104;
	/** 获取100条K线数据 */
	public static final int EInterface_Trad_Exercise = 0x5105;
	/** 查看游戏任务列表 */
	public static final int EInterface_Get_Kljobs = 0x5106;
	/** 分享 任务完成加金豆 */
	public static final int EInterface_Share_Add_Gold = 0x5107;
	/** 退出K线游戏 */
	public static final int EInterface_Out_Line = 0x5108;
	/**长期游戏获取100条K线数据*/
	public static final int EInterface_Get_Long_Trad_Exerise = 0x4184;
	/**长期游戏数据上传*/
	public static final int EInterface_Postlong_Kl_Opera_Record = 0x4185;
	/**重置财富*/
	public static final int EInterface_Lont_Reset = 0x4186;
	/**领取金豆*/
	public static final int EInterface_Receive_Gold = 0x5109;
	/**获取 财富收益，财富增长排名信息*/
	public static final int EInterface_Get_Money_Rank = 0x5110;
	/**获取 K线用户综合能力*/
	public static final int EInterface_Get_Ability = 0x5111;
	/**展示K线收藏列表*/
	public static final int EInterface_Get_Kl_Collect_List = 0x5112;
	/**获取复盘数据*/
	public static final int EInterface_Get_Replay_Kline = 0x5113;
	/**收藏游戏*/
	public static final int EInterface_Kl_Collect = 0x5114;
	/**取消收藏游戏*/
	public static final int EInterface_Cal_Kl_Collect = 0X5115;
	/**盘中奖励*/
	public static final int EInterface_Jiangli = 0x5116;
	/**获取 同期资讯*/
	public static final int EInterface_Get_Same_Info = 0x5117;
	/**修改 用户的PK开关状态*/
	public static final int EInterface_Change_Pk_Switch = 0x5118;
	/**获取好贴分享*/
	public static final int EInterface_Get_Haotietopic = 0x5119;
	/**PK游戏数据上传*/
	public static final int EInterface_Pk_Kl_Opera_Record = 0x4187;
	/* ************************************************直呼吧 start************************************************************* */
	/**获取我的直呼列表*/
	public static final int EInterface_Get_My_Call = 0x5120;
	/**回答问题*/
	public static final int EInterface_Answer_Question = 0x5121;
	/**问题榜*/
	public static final int EInterface_Query_All_Zhi_Hu = 0x4188;
	/**直呼谁*/
	public static final int EInterface_Zhihu_By_Name = 0x4189;
	/**个人直呼信息*/
	public static final int EInterface_Getzhihu_Main = 0x5122;
	/**获取问题信息*/
	public static final int EInterface_To_Answer_Question = 0x5123;
	/**添加直呼*/
	public static final int EInterface_Add_Zhihu = 0x5124;
	/**直呼问题*/
	public static final int EInterface_Zhihu_Question = 0x5125;
	/**偷偷听*/
	public static final int EInterface_Zhihu_Ting = 0x5126;
	/**添加直呼赞*/
	public static final int EInterface_Add_Hehe = 0x5127;
	/**判断实盘用户是否开户过*/
	public static final int EInterface_Api_Open_User_Flag = 0x5128;
	/**添加直呼赞*/
	public static final int EInterface_Add_Zh_Useful = 0x5129;
	/* ************************************************直呼吧 end************************************************************** */
	/**统计按钮点击率*/
	public static final int EInterface_Button_Count = 0x5130;
	/**获取主页面数据*/
	public static final int EInterface_Get_Home_Data = 0x4190;
	/**APP首页统计广告点击次数*/
	public static final int EInterface_Record_Ad_Click = 0x4191;
	/**验证用户是否要进行邮箱验证*/
	public static final int EInterface_Check_City_Code = 0x4192;
	/**防止用户发公告，发送邮件*/
	public static final int EInterface_Send_Code_By_Email = 0x4193;
	/**防止用户发公告，提交验证*/
	public static final int EInterface_Send_Yanzheng = 0x4194;
	/**防止用户发公告，获取用户Email*/
	public static final int EInterface_Api_Get_Email = 0x4195;
	/**用户点击充值时，服务端返回金豆数和对应的价格，这个get请求不能缓存**/
	public static final int EInterface_Api_Get_Jindou_Price = 0x4196;
	/**游戏购买金豆之创建订单**/
	public static final int EInterface_Api_Pay_Jindou = 0x4197;
	/**太平洋or福米开户获取OpenID**/
	public static final int EInterface_Get_Open_ID = 0x4198;
	/**获取社区达人数据(communityPeople)**/
	public static final int EInterface_Api_Community_People = 0x4199;
	/**帖子举报*/
	public static final int EInterface_Api_Inform_Topic = 0x4200;
	/**注册:获取手机校验码(AppCheckMoblie)(找回密码，注册)*/
	public static final int EInterface_Api_Get_Phone_Code_New = 0x4201;
	/**验证码验证*/
	public static final int EInterface_Api_Check_Code_New = 0x4202;
	// /**新密码修改*/
	// public static final int EInterface_Api_Update_Pwd_New = 0x4203;
	/**获取行情，几个类型的热门前6个股票*/
	public static final int EInterface_Api_Get_Hangqing = 0x5131;
	/**重置所有的推送设置*/
	public static final int EInterface_Api_Reset_All_Push_Set = 0x5132;
	/**我参与的所有比赛*/
	public static final int EInterface_My_Join_Match = 0x4204;
	/**查看比赛页*/
	public static final int EInterface_View_Sp_Match = 0x4205;
	/**查看比赛场用户数据*/
	public static final int EInterface_User_Rate_List = 0x4206;
	/**查看用户的个人战绩*/
	public static final int EInterface_Watch_User = 0x4207;
	/**查看用户每日持仓*/
	public static final int EInterface_User_Day_Position = 0x4208;
	/**实盘比赛*/
	public static final int EInterface_SHI_PAN_MATCH_STATE = 0x5133;
	/**查看用户每日收益率*/
	public static final int EInterface_User_Day_Rate = 0x5134;
	/**我的比赛*/
	public static final int EInterface_My_Match = 0x4209;
	/**付费查看战绩*/
	public static final int EInterface_Buy_Look_User = 0x4210;
	/**申请满仓停牌提交开关状态*/
	public static final int EInterface_Askting = 0x5135;
	/**比赛验证邀请码*/
	public static final int EInterface_Check_Sp_Code = 0x5136;
	/**参加比赛即报名*/
	public static final int EInterface_Enter_Sp_Match = 0x5137;
	/**批量报单*/
	public static final int EInterface_Ever_Day_Baodan = 0x5138;
	/**返回用户所有比赛的可请假信息*/
	public static final int EInterface_Get_Leave_Msg = 0x5139;
	/**进入报单页面获取所有我的正在参加的比赛*/
	public static final int EInterface_Get_My_Matching = 0x5140;
	/**提交请假信息*/
	public static final int EInterface_Insert_User_Leave = 0x5141;
	/**退赛*/
	public static final int EInterface_Back_Match = 0x4211;
	/**实盘比赛获取规则*/
	public static final int EInterface_Get_Rule_ID = 0x5142;
	/**判断能否报单状态接口*/
	public static final int EInterface_Get_My_Day_Baodan = 0x4212;
	/**比赛高手*/
	public static final int EInterface_Master_User_List = 0x4212;
	/**实盘订阅列表*/
	public static final int EInterface_Spmatch_Subscribe = 0x4213;
	/**深度分析列表*/
	public static final int EInterface_Deep_Analysis_Sp_List = 0x4214;
	/**选手名单*/
	public static final int EInterface_User_List = 0x4215;
	/**总赛场龙虎榜*/
	public static final int EInterface_Total_Spmatch_Longhu = 0x4216;
	/**赛况分析_买入卖出*/
	public static final int EInterface_Analyze_Match_Info = 0x4217;
	/**关注网友在本赛场的走势*/
	public static final int EInterface_Follow_User_Rate_List = 0x4218;
	/**实盘订阅类型选择*/
	public static final int EInterface_Choose_Sp_Subscribe = 0x4219;
	/**实盘股票luncne搜索*/
	public static final int EInterface_Search_Stock_Spmatch = 0x4210;
	/**获取qqqun*/
	public static final int EInterface_Get_Sp_QQqun = 0x4221;
	/**获取最先开始的比赛到今天所有的交易日*/
	public static final int EInterface_Get_Deal_Plan_Day = 0x4222;
	/** 微信先下单到我们自己服务器上 **/
	public static final int EInterface_Pay_To_Server_wx = 0x4223;
	/**app端直接通过微信或者支付宝来捧场*/
	public static final int EInterface_Api_Peng_Chang_By_Money = 0x4224;
	/**获取捧场用户名单*/
	public static final int EInterface_Api_Get_Peng_Chang_User = 0x4225;
	/**获取股市直播数字*/
	public static final int EInterface_Get_Index_Red_Point = 0x4226;
	/**通过积分来购买大V观点*/
	public static final int EInterface_Buy_By_Point = 0x4227;
	/**app端直接通过微信或者支付宝来购买大V观点*/
	public static final int EInterface_Apidavbymoney = 0x4228;
	/**大V观点留言数目*/
	public static final int EInterface_Get_Dav_Message_Num = 0x4229;
	/**设置用户名*/
	public static final int EInterface_Api_Add_User_Name = 0x4230;
	/**个股吧股票信息内容*/
	public static final int EInterface_Get_Stock_Info = 0x4231;

	/**找回密码获取验证码*/
	public static final int EInterface_Phonecodepwd = 0x4232;
	/**找回密码*/
	public static final int EInterface_Submit_Password = 0x4233;
	/**激活获取短信效验码,注册获取验证码*/
	public static final int EInterface_Phone_Code = 0x4234;
	/**注册第一步*/
	public static final int EInterface_Register_StepOne = 0x4235;
	/**注册第二步*/
	public static final int EInterface_Register_StepTwo = 0x4236;
	/**登陆 密码错误5次获取验证码*/
	public static final int EInterface_Login_CheckCode = 0x4237;
	/**提交登录数据*/
	public static final int EInterface_Login_Submit = 0x4238;
	/**浏览用户登录*/
	public static final int EInterface_SSO_Visitor_Login = 0x4239;
	/**激活提交数据*/
	public static final int EInterface_Register_Activeuser = 0x4240;
	/**授权后提交accessToken*/
	public static final int EInterface_Aauth_Submit = 0x4241;
	/**新注册用户第一步*/
	public static final int EInterface_Oauth_Register = 0x4242;
	// /**注册第二步*/
	// public static final int EInterface_Oauth_Register_StepTwo = 0x4243;
	/**绑定已有账号*/
	public static final int EInterface_Oauth_BindLogin = 0x4244;
}
