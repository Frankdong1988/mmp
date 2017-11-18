package com.frank.mmp.common.bean;

import java.io.Serializable;
import java.util.List;

public class PaginationView<T> implements Serializable{
	private static final long serialVersionUID = 4372142211474397293L;
	/** 当前页码 **/
	private Integer pageNumber;
	/** 每页长度 **/
	private Integer pageSize;
	/** 总页数 **/
	private Integer pageTotal;
	/** 数据长度 **/
	private Integer total;
	/** 数据列表 **/
	private List<T> list;
	
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getPageTotal() {
		return pageTotal;
	}
	public void setPageTotal(Integer pageTotal) {
		this.pageTotal = pageTotal;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
}
