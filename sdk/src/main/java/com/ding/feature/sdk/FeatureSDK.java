package com.ding.feature.sdk;

import com.ding.feature.sdk.spec.FeatureSDKSpec;

public class FeatureSDK {
	public static FeatureSDKSpec getInstance() {
		return null;

	}

	public static void main(String[] args) {
		String appId = "10001";
		String appType = "android";
		String region = "china";
		String user = "ding";
		String flag = "MEMBERSHIP_ENABLED";
		String release = "2.3.1";
		FeatureSDK.getInstance().init(appId, appType, region);
		FeatureSDK.getInstance().isEnabled(user, release, flag);
	}
}
