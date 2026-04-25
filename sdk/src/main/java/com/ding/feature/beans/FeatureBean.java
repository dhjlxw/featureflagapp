package com.ding.feature.beans;

import java.util.List;
import java.util.Map;

public class FeatureBean {//TODO minimize for sdk
	private long id;
	private String flag;
	private String description;
	private String appId;
	private List<String> appType;//android,web etc.
	private List<String> regions;
	private List<String> userGroups;
	private List<String> userIdWhiteList;
	private List<String> userIdBlackList;
	private String releaseVersion;
	private int grayPercent;//0-100
	private boolean sdkEvaluatable;
	boolean enabled;
	private String createdBy;
	private String updatedBy;
	private long createdTime;// ms
	private long updatedTime;// ms
}
