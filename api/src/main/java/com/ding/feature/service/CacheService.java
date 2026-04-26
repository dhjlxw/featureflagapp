package com.ding.feature.service;

import java.util.List;

import com.ding.feature.beans.FeatureBean;
import com.ding.feature.beans.FeatureFlagBean;

/*
 * to operate redis
 * the bean should only contain the minimum fields
 */
public interface CacheService {

	List<String> getFlagKeys(FeatureFlagBean bean);

	List<FeatureBean> getByFlagKey(String flagKey);

	void save(FeatureBean bean);

}
