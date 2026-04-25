package com.ding.feature.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ding.feature.beans.FeatureFlagBean;
import com.ding.feature.beans.ResultBean;
import com.ding.feature.beans.ConfigurationBean;

@Controller
public class FeatureAPIController {
	@PostMapping
	public ResultBean<Boolean> isEnabled(@RequestBody FeatureFlagBean bean) {
		// this should first check the bloom filter service
		// then invoke the FeatureAPIService.isEnable
		return null;
	}

	@PostMapping
	public ResultBean<ConfigurationBean> getConfiguration(@RequestBody FeatureFlagBean bean) {
		return null;
	}

}
