package com.ding.feature.cache.job;

public interface InitialLoadJob {
	/*
	 * to initially load all the data into redis
	 * this should first get lock
	 */
	void initialLoad(String dateShardKey);

}
