package com.frank.mmp.common.bean;

import java.io.Serializable;

import org.apache.poi.ss.formula.functions.T;

@SuppressWarnings("hiding")
public class PaginationDao<T> extends PaginationView<T> implements Serializable{
	private static final long serialVersionUID = -3218143217974938752L;
	private T param;
	private Integer startNum;
	private Integer endNumMysql;
	private Integer endNumOracle;
	
	public Integer getStartNum() {
		Integer pageNum = super.getPageNumber();
		if(null == pageNum || pageNum<=0){
			pageNum = 1;
		}
		Integer pageSize = super.getPageSize();
		if(null == pageSize || pageSize <= 0){
			pageSize = 10;
		}
		startNum = (pageNum-1)*pageSize;
		return startNum;
	}
	public void setStartNum(Integer startNum) {
		this.startNum = startNum;
	}
	public Integer getEndNumMysql() {
		Integer pageSize = super.getPageSize();
		if(null == pageSize || pageSize <= 0){
			pageSize = 10;
		}
		endNumMysql = pageSize;
		return endNumMysql;
	}
	public void setEndNumMysql(Integer endNumMysql) {
		this.endNumMysql = endNumMysql;
	}
	public Integer getEndNumOracle() {
		Integer pageNum = super.getPageNumber();
		if(null == pageNum || pageNum<=0){
			pageNum = 1;
		}
		Integer pageSize = super.getPageSize();
		if(null == pageSize || pageSize <= 0){
			pageSize = 10;
		}
		this.endNumOracle = (pageNum-1)*pageSize + pageSize;
		return endNumOracle;
	}
	public void setEndNumOracle(Integer endNumOracle) {
		this.endNumOracle = endNumOracle;
	}
	public T getParam() {
		return param;
	}
	public void setParam(T param) {
		this.param = param;
	}
	
}
