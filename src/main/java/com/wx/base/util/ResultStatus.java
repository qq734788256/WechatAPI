package com.wx.base.util;

public class ResultStatus {
	
	// 执行成功状态码
	public static final int SUCCESS_CODE = 200;
	// 执行失败状态码
	public static final int FAIL_CODE = 400;
	// 服务器故障状态码
	public static final int SERVER_ERROR_CODE = 500;
	// token异常状态码
	public static final int TOKEN_ERROR_CODE = 502;
	
	// 成功
	public static final String SUCCESS = "success";
	// 失败
	public static final String FAIL = "fail";
	// 异常
	public static final String ERROR = "error";
	
	// 服务器异常
	public static final String SERVER_ERROR = "服务器未知故障";
	// 查询成功
	public static final String QUERY_SUCCESS = "查询成功";
	// 添加成功
	public static final String ADD_SUCCESS = "添加成功";
	// 操作成功
	public static final String OPRATE_SUCCESS = "操作成功";
	// 操作失败
	public static final String OPRATE_FAIL = "操作失败";
	// 用户失效
	public static final String USER_INVILIDE = "用户登录失效";
	// 用户名或密码不能为空
	public static final String LOGIN_PARAM_NULL = "用户名或密码不能为空";
	// 用户被禁用
	public static final String USER_NO_USED = "用户不可用";
	// 用户名或密码错误
	public static final String LOGIN_PARAM_ERROR = "用户名或密码错误";
	// 旧密码错误
	public static final String OLD_PASSWORD_ERROR = "旧密码错误";
	// 参数错误
	public static final String PARAM_ERROR = "参数错误";

}
