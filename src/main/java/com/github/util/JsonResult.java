package com.github.util;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 通用JSON格式接口返回数据
 * @Author Leo
 * @Create 2023/9/18 23:52 
 */
@Data
//@ApiModel
@Accessors(chain = true)
public class JsonResult<T> {
	
	public static final int STATUS_CODE_OK = 200;
	public static final int STATUS_CODE_ERROR = 500;
	public static final int STATUS_UNAUTHORIZED = 10001;

	//@ApiModelProperty("状态码")
	private Integer code;

	//@ApiModelProperty("返回信息")
	private String msg;
	
	//@ApiModelProperty("返回数据")
	private T data;
	
	public static <T> JsonResult<T> ok() {
		return ok(null);
	}
	
	public static <T> JsonResult<T> ok(T data) {
		JsonResult<T> result = new JsonResult<>();
		result.setCode(STATUS_CODE_OK);
		result.setData(data);
		return result;
	}
	
	public static <T> JsonResult<T> error(String msg) {
		return error(STATUS_CODE_ERROR, msg);
	}
	
	public static <T> JsonResult<T> error(Integer code, String msg) {
		JsonResult<T> result = new JsonResult<>();
		result.setCode(code == null ? STATUS_CODE_ERROR : code);
		result.setMsg(msg);
		return result;
	}

}
