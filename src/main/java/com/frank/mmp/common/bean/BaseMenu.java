package com.frank.mmp.common.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 全局基本菜单类
 * @author dgf
 * @version 创建时间：2017年12月7日 下午3:12:43 
 * @param <E>
 * @param <T>
 */
public class BaseMenu<E> {
	private Integer menuId;
	private Integer menuParentId;
	private List<E> children;
	
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public Integer getMenuParentId() {
		return menuParentId;
	}
	public void setMenuParentId(Integer menuParentId) {
		this.menuParentId = menuParentId;
	}
	public List<E> getChildren() {
		return children;
	}
	public void setChildren(List<E> children) {
		this.children = children;
	}
	
	/**
	 * 设置list为树形菜单格式数据
	 * @param list
	 * @return
	 */
	public static <T extends BaseMenu> List<T> formatTree(List<T> list) {
        List<T> nodeList = new ArrayList<T>();
        for(T node1 : list){
            boolean mark = false;
            for(T node2 : list){
                if(node1.getMenuParentId() != null && node1.getMenuParentId().equals(node2.getMenuId())){
                    mark = true;
                    if(node2.getChildren() == null) {
                        node2.setChildren(new ArrayList<T>());
                    }
                    node2.getChildren().add(node1);
                    break;
                }
            }
            if(!mark){
                nodeList.add(node1);
            }
        }
        return nodeList;
    }
	
}
