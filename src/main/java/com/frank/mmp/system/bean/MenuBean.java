package com.frank.mmp.system.bean;

public class MenuBean {
	/**菜单名称**/
	private String menuName; 
	/**菜单连接**/
	private String menuUrl; 
	/**菜单样式**/
	private String menuStyle; 
	/**菜单图标**/
	private String menuIco; 
	/**菜单登录校验 1：不需要登录校验，2：需要登录校验 **/
	private Integer menuLoginCheck; 
	/**菜单类型(page、button)**/
	private String menuType;
	
	public Integer getMenuLoginCheck() {
		return menuLoginCheck;
	}
	public void setMenuLoginCheck(Integer menuLoginCheck) {
		this.menuLoginCheck = menuLoginCheck;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public String getMenuStyle() {
		return menuStyle;
	}
	public void setMenuStyle(String menuStyle) {
		this.menuStyle = menuStyle;
	}
	public String getMenuIco() {
		return menuIco;
	}
	public void setMenuIco(String menuIco) {
		this.menuIco = menuIco;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	
}
