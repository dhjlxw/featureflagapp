package com.ding.feature.service;

import java.util.List;

import com.ding.feature.beans.FeatureBean;
import com.ding.feature.beans.FeatureFlagBean;

/*
 * invoke the admin service to get data
 */
public interface RemoteDataService {
	/*
	 * to query the admin service to get the matched configurations
	 */
	List<FeatureBean> matched(FeatureFlagBean bean);
}
