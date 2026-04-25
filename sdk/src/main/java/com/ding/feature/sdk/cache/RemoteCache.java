package com.ding.feature.sdk.cache;

import com.ding.feature.beans.FeatureFlagBean;

public interface RemoteCache {
	boolean isEnabled(FeatureFlagBean bean);
}
