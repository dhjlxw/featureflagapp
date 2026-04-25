package com.ding.feature.cache.service;

import java.util.List;

import com.ding.feature.beans.FeatureBean;

/*
 * this service will directly operate redis
 * this service should handle the shard key logic
 * this should combine all the possible regions/users etc. into flat data and save
 */
public interface CacheProxy {

	long delete(FeatureBean bean);

	long update(FeatureBean bean);

	long insert(FeatureBean bean);

	long[] delete(List<FeatureBean> beans);

	long[] update(List<FeatureBean> beans);

	long[] insert(List<FeatureBean> beans);
	
	

}
