package com.ding.feature.service;

import java.util.List;

import com.ding.feature.beans.FeatureBean;
/*
 * maintain a memory cache and specify expire time
 * this should ensure each flagKey will only be deserialize once in a small duration(30s)
 * this service should work with hot flag key service to keep the hot accessed flag key objects
 * the feature bean should only contain the minimum fields
 */
public interface LocalCacheService {
	List<FeatureBean> getByFlagKey(String flagKey);
}
