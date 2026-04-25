package com.ding.feature.admin.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.ding.feature.beans.FeatureBean;
import com.ding.feature.beans.FeatureFlagBean;
import com.ding.feature.beans.PaginatedBean;
import com.ding.feature.beans.ResultBean;

/*
 * The token and user name will be handled in the filter
 */
public class FeatureDataController {
	@GetMapping("/get/{id}")
	public ResultBean<FeatureBean> get(@PathVariable("id") long id) {
		return null;
	}

	@PostMapping("/insert/{id}")
	public ResultBean<Integer> insert(@PathVariable("id") long id, @RequestBody FeatureBean bean) {
		return null;

	}

	@PostMapping("/update/{id}")
	public ResultBean<Integer> update(@PathVariable("id") long id, @RequestBody FeatureBean bean) {
		return null;

	}
	
	@DeleteMapping("/delete/{id}")
	public ResultBean<Integer> delete(@PathVariable("id") long id) {
		return null;

	}

	@PostMapping("/query/{page}")
	public PaginatedBean<FeatureBean> query(@PathVariable("page") int page,
			@RequestParam("includeDeleted") boolean includeDeleted, @RequestBody FeatureBean bean) {
		return null;
	}
	
	
	// TODO batch methods, ignored now
}
