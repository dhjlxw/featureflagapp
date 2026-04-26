package com.ding.feature.service;

import com.ding.feature.beans.FeatureFlagBean;

public interface FeatureAPIService {
	
	/*
	 * first to check the cache service
	 * if cache not exists then invoke the remote data service to query and then save to cache
	 * as the cache is loaded fully before this, so most case the cache should be exists
	 * when doing evaluation, this method should optimize the memory cache and memory evaluation part to speed up 
	 */
	boolean isEnabled(FeatureFlagBean bean);
}
