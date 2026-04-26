package com.ding.feature.service;

import com.ding.feature.beans.FeatureFlagBean;

public interface FeatureAPIService {
	
	/*
	 * first to check the cache service
	 * if cache not exists then invoke the remote data service to query and then save to cache
	 * note:this part should use local lock to lock the flag key to avoid  of db penetrating issue：
	 * 1.use try lock with expire time
	 * 2.for each flag key maintain a try lock count（semaphore) , if number is greater than 10, then directly throw exception
	 * 3.for db operation , use a global semaphore to control the concurrency 
	 * as the cache is loaded fully before this, so most case the cache should be exists
	 * when doing evaluation, this method should optimize the memory cache and memory evaluation part to speed up 
	 */
	boolean isEnabled(FeatureFlagBean bean);
}
