package com.ding.feature.cache.config;
/*
 * to define all the configured items
 */
public interface ConfigService {
	//the running duration gap of the cache sync job
	int getSyncGapSeconds();
	//each time the cache sync job will query some extra seconds to avoid the data missing issue
	int getExtraDataSeconds();
	

}
