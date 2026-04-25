package com.ding.feature.cache.service;

import java.util.List;

import com.ding.feature.beans.FeatureFlagBean;

public interface BloomFilterCacheKeyService {
	void addBloomFilter(List<FeatureFlagBean> beans);
}
