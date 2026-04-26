package com.ding.feature.service;

import java.util.Map;

/*
 * to maintain the hot flag keys so that the local cache can keep those hot key's cache into memory
 */
public interface HotFlagKeyService {
	Map<String, Integer> getTopFlagKeyQPS();

	Map<String, Integer> getTopFlagKeyHits();

	//to record the flag key hits in order to maintain the top flag key metrics data
	void hitFlagKey(String flagKey);

}
