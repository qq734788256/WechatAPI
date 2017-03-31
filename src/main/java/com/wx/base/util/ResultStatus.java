package com.wx.base.util;

public class ResultStatus {
	
	// 执行成功状态码
	public static final int SUCCESS_CODE = 200;
	// 服务器故障状态码
	public static final int SERVER_ERROR_CODE = 500;
	
	// 成功
	public static final String SUCCESS = "success";
	// 失败
	public static final String FAIL = "fail";
	
	// 服务器异常
	public static final String SERVER_ERROR = "操作失败（服务器未知故障）";
	// 查询成功
	public static final String QUERY_SUCCESS = "查询成功";
	// 添加成功
	public static final String ADD_SUCCESS = "添加成功";
	// 操作成功
	public static final String OPRATE_SUCCESS = "操作成功";
}
