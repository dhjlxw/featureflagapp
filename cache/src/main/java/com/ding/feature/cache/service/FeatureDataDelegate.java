package com.ding.feature.cache.service;

/*
 * this service should invoke the manager api to operate or query data from db
 */

import com.ding.feature.beans.FeatureBean;

public interface FeatureDataDelegate {
	/*
	 * to query the data within the given time span
	 * 
	 * @startTime the start time, can be null
	 * 
	 * @endTime the end time, can be null
	 * 
	 * @start the start index, it should be greater than or equal to 0
	 * 
	 * @limit the record returned limitation, if -1 is passed then no limitation
	 * 
	 * @includeDeleted to ensure the deleted data will be considered for redis cache
	 */
	Iterable<FeatureBean> query(Long startTime, Long endTime, int start, int limit, boolean includeDeleted);

	// get the last sync date from db 
	Long getLastSyncDate();

	// save the last sync date into db
	void setLastSyncDate(Long date);

}
