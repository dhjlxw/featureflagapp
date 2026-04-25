package com.ding.feature.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ding.feature.beans.FeatureExplainBean;
import com.ding.feature.beans.FeatureFlagBean;
import com.ding.feature.beans.ResultBean;

/*
 */
@Controller
public class FeatureReportController {
	@PostMapping
	public ResultBean<Integer> report(@RequestBody FeatureFlagBean bean){
		//this should first check the bloom filter service, if not exists return true
		//then invoke the FeatureExplainService 
		return null;}

}
