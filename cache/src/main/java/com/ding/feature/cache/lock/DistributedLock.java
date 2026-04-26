package com.ding.feature.cache.lock;

/*
 * use redisson lock to automatically renew the expire time
 */
public interface DistributedLock {
	boolean tryLock(String key, long expire);

	boolean release(String key);

}
