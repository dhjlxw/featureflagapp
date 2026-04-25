package com.ding.feature.sdk.spec;

public interface FeatureSDKSpec {
	void init(String appId, String appType, String region);

	boolean isEnabled(String user, String release, String flag);

}
