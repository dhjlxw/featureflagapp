package com.ding.feature.service;

import com.ding.feature.beans.FeatureFlagBean;

public interface BloomFilterCacheKeyService {
	/*
	 * bloom filter to check the flag key is valid or not
	 */
	String getKey(FeatureFlagBean bean);

}
