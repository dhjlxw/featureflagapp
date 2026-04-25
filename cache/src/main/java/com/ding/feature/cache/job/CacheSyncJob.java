package com.ding.feature.cache.job;

public interface CacheSyncJob {
	// to reload the increment data into redis every 5 minutes
	// this should first get lock
	// to reload the increment data into redis every 5 minutes
	// the dateShardKey can be null, then use the global date shard key
	void incrementSync(String dateShardKey);

}
