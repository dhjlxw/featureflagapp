package com.ding.feature.admin.service;

import java.util.List;

import com.ding.feature.beans.FeatureBean;
import com.ding.feature.beans.FeatureFlagBean;

public interface FeatureDataService {
	FeatureBean get(long id);

	int update(FeatureBean bean);

	int delete(FeatureBean bean);

	int insert(FeatureBean bean);

	List<FeatureBean> getBatch(List<Long> ids);
	int insertBatch(List<FeatureBean> beans);
	int updateBatch(List<FeatureBean> beans);
	int deleteBatch(List<FeatureBean> beans);
	
	Iterable<FeatureBean> query(FeatureBean bean, int start, int limit, boolean includeDeleted);
	
	FeatureBean query(FeatureFlagBean bean);

}
