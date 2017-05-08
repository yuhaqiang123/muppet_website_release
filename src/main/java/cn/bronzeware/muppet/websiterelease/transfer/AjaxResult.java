package cn.bronzeware.muppet.websiterelease.transfer;


public class AjaxResult {
	//成功失败标识，1成功，0失败
	private int code;
	//结果描述，存放成功信息或者异常信息
	private String msg;
	//返回的结果数据
	private Object result;
	

	public AjaxResult(){}
	

	public AjaxResult(int code,String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public AjaxResult(int code,String msg,Object result){
		this(code,msg);
		this.result = result;
	}
	
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
	
}
