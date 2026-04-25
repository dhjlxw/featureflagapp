package com.ding.feature.cache.job;

public interface InitialBloomFilterLoadJob {
	/*
	 * to initially load all the possible combinations into redis bloom filter
	 * the dateShardKey can be null, then use the global key
	 */
	void initialLoad(String dateShardKey);

}
