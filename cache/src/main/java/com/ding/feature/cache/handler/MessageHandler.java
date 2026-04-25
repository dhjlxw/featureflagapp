package com.ding.feature.cache.handler;

import com.ding.feature.beans.FeatureMessageBean;

public interface MessageHandler {
	/*
	 * to consume the mq message and sync the cache
	 * if the message proceeded fail, the timely sync process will ensure the cache is correct
	 */
	void handle(FeatureMessageBean message);
}
