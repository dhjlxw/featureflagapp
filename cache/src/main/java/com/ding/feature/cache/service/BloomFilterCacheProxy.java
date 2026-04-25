package com.ding.feature.cache.service;

import java.util.List;

import com.ding.feature.beans.FeatureFlagBean;

/*
 * this service will directly operate redis
 */
public interface BloomFilterCacheProxy {

	void add(List<FeatureFlagBean> beans, String dateShardKey);

}
