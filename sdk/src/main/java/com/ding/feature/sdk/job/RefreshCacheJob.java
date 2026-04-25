package com.ding.feature.sdk.job;

public interface RefreshCacheJob {
	void refresh();
	long lastLocalCacheTime();
}
