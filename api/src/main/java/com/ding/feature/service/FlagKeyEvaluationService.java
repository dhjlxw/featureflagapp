package com.ding.feature.service;

import java.util.List;

import com.ding.feature.beans.FeatureBean;
import com.ding.feature.beans.FeatureExplainBean;
/*
 * this class should evaluate the given beans asap, the strategy to evaluate is important
 * this class should consider some optimization methods to improve performance
 */
public interface FlagKeyEvaluationService {
	boolean evaluate(List<FeatureBean> beans);
	FeatureExplainBean explain(List<FeatureBean> beans);

}
