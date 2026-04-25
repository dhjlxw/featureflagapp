package com.ding.feature.beans;

import java.util.List;

public class FeatureBean {
	private long id;
	private String flag;
	private String description;
	private String appId;
	private List<String> appType;// android,web etc.
	private List<String> region;
	private List<String> userGroups;
	private List<String> userIdWhiteList;
	private List<String> userIdBlackList;
	private String releaseVersion;
	private int priority;
	private boolean sdkEvaluatable;//allow sdk to evaluate
	private int grayPercent;//0-100
	boolean enabled;
	private String createdBy;
	private String updatedBy;
	private long createdTime;// ms
	private long updatedTime;// ms
	private boolean matched;// only used for explain purpose
}
