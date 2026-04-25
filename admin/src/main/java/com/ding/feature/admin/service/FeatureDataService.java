package com.ding.feature.admin.service;

import com.ding.feature.beans.FeatureBean;
import com.ding.feature.beans.FeatureFlagBean;

public interface FeatureDataService {
	FeatureBean get(long id);

	int update(FeatureBean bean);

	int delete(FeatureBean bean);

	int insert(FeatureBean bean);

	// TODO batch methods, ignored now

	Iterable<FeatureBean> query(FeatureBean bean, int start, int limit, boolean includeDeleted);
	
	FeatureBean query(FeatureFlagBean bean);

}
