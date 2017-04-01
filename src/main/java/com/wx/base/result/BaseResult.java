package com.wx.base.result;

import java.io.Serializable;

import com.wx.base.util.ResultStatus;

public class BaseResult implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4265185238810913137L;

	/**
	 * 结果状态码
	 */
	private int statusCode = ResultStatus.SUCCESS_CODE;

	/**
	 * 结果
	 */
	private String result = ResultStatus.SUCCESS;

	/**
	 * 结果提示语
	 */
	private String message = ResultStatus.SUCCESS;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
