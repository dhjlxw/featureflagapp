package com.ding.feature.beans;

public class FeatureFlagBean {
	private String appId;
	private String appType;
	private String region;
	private String user;
	private String release;
	private String flag;
	private long updatedTime;//only used by sdk to pull the increment cache
	private long checkDuration;//only used by sdk to report duration

}
