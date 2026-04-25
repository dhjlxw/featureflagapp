package com.ding.feature.beans;

public class FeatureMessageBean {
	public final static int UPDATE = 0;
	public final static int DELETE = 1;
	public final static int ADD = 2;
	private int operation;
	private FeatureBean bean;
	private long updatedTime;//ms
}
