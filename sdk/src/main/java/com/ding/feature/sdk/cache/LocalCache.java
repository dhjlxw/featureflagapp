package com.ding.feature.sdk.cache;

import com.ding.feature.beans.FeatureFlagBean;

public interface LocalCache {
	boolean isEnabled(FeatureFlagBean bean);
}
