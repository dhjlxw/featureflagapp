package com.ding.feature.service;

import com.ding.feature.beans.FeatureFlagBean;

public interface BloomFilterValidateService {
	/*
	 * use redis bloom filter to do the first validation
	 * to check if the flag key is in the latest 2 dateShardKeys
	 * */
	boolean exists(FeatureFlagBean bean);
}
