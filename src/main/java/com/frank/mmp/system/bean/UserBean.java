package com.frank.mmp.system.bean;

public class UserBean {
	/**主键**/
	private Long uId;
	/**号码**/
	private String uName;
	/**帐号**/
	private String uAccount;
	/**密码**/
	private String uPsw;
	
	public Long getuId() {
		return uId;
	}
	public void setuId(Long uId) {
		this.uId = uId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuAccount() {
		return uAccount;
	}
	public void setuAccount(String uAccount) {
		this.uAccount = uAccount;
	}
	public String getuPsw() {
		return uPsw;
	}
	public void setuPsw(String uPsw) {
		this.uPsw = uPsw;
	}
	
}
