package com.ding.feature.sdk.cache;

public interface PullingCache {
	void pull(long lastCacheTime);

	void save();

	long getLastCacheTime();

}
