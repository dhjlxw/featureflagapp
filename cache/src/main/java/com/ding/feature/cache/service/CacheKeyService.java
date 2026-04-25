package com.ding.feature.cache.service;

import com.ding.feature.beans.FeatureBean;

/*
 * to generate the cache key
 */
public interface CacheKeyService {
	String getCachedKey(FeatureBean bean, String shardingKey);

	String getFlagKey(FeatureBean bean, String shardingKey);

	String getBloomFilterKey(FeatureBean bean, String shardingKey);

}
