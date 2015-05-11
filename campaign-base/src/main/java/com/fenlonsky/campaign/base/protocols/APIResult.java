package com.fenlonsky.campaign.base.protocols;

/**
 * 
 * @ClassName: APIResult
 * @Description: 接口返回类（为了接口一致性）
 * @author <a href="mailto:fenlonxiong@gmail.com">Fenlon</a>
 * @date 2015年5月11日 下午4:39:50
 * 
 * @param <T>
 */
public class APIResult<T> {
	
	/** 是否成功 **/
	private Boolean success;
	/** 执行结果编号 **/
	private APIResultCode code;
	/** 信息 **/
	private String message;
	/** 数据 **/
	private T result;
	
	public APIResult() {
	}
	
	public APIResult(Boolean success, APIResultCode code, String message, T result) {
		this.setSuccess(success);
		this.setCode(code);
		this.setMessage(message);
		this.setResult(result);
	}
	
	public APIResultCode getCode() {
		return code;
	}
	
	public void setCode(APIResultCode code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public T getResult() {
		return result;
	}
	
	public void setResult(T result) {
		this.result = result;
	}
	
	public String simpleJson() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append('{');
		stringBuilder.append("\"code\": \"").append(code).append("\",");
		stringBuilder.append("\"message\": \"").append(message).append("\",");
		stringBuilder.append("\"result\": \"").append(result).append("\"}");
		
		return stringBuilder.toString();
	}
	
	public Boolean getSuccess() {
		return success;
	}
	
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
}
