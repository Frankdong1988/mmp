package com.frank.mmp.common.bean;

import java.io.Serializable;

import org.apache.poi.ss.formula.functions.T;

@SuppressWarnings("hiding")
public class PaginationDao<T> extends PaginationView<T> implements Serializable{
	private static final long serialVersionUID = -3218143217974938752L;
	private T param;

	public T getParam() {
		return param;
	}
	public void setParam(T param) {
		this.param = param;
	}
	
}
