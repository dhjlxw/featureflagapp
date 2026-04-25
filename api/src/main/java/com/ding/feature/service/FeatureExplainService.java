package com.ding.feature.service;

import com.ding.feature.beans.FeatureExplainBean;
import com.ding.feature.beans.FeatureFlagBean;

public interface FeatureExplainService {
	/*
	 * this should first get the db data by invoking remote data service
	 * then do evaluation those bean to get the explain bean
	 */
	FeatureExplainBean explain(FeatureFlagBean bean);
}
