package com.ding.feature.cache.lock;

/*
 * use redisson lock to automatically renew the expire time
 * this lock will use threadId to ensure the lock can't be operated by other threads
 */
public interface DistributedLock {
	boolean tryLock(String key, long expire);

	boolean release(String key);

}
