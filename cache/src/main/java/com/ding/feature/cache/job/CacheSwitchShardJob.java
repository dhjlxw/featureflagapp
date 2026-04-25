package com.ding.feature.cache.job;

public interface CacheSwitchShardJob {
	/*
	 * this should load the bloom filter shard with a new key for all data
	 * 
	 */
	
	void fullLoad(String dateShardKey);
	
	/*
	 *  set new date shard key globally
	 *  
	 */
	void setNewDateShardKey(String dateShardKey);
	
	/*
	 * this is to delete the old shard key
	 */
	void deleteDateShardKey(String dateShardKey);
	
	/*
	 * Global date shard key
	 */
	String getDateShardKey();
	
	
}
