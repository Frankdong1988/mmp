package com.frank.mmp.system.bean;

public class MenuBean {
	/**主键**/
	private Long menuId; 
	/**菜单名称**/
	private String menuName; 
	/**菜单连接**/
	private String menuUrl; 
	/**菜单样式**/
	private String menuStyle; 
	/**菜单图标**/
	private String menuIco; 
	/**菜单父id**/
	private Long menuParentId; 
	/**菜单类型(page、button)**/
	private String menuType;
	
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
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
	public Long getMenuParentId() {
		return menuParentId;
	}
	public void setMenuParentId(Long menuParentId) {
		this.menuParentId = menuParentId;
	} 
	
}
