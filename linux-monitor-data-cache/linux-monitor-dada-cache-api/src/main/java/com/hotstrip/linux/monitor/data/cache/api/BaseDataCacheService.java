package com.hotstrip.linux.monitor.data.cache.api;

import com.hotstrip.linux.monitor.common.pojo.SystemLoadAvgDO;

public interface BaseDataCacheService {

    void cacheSystemLoadAvgData(final SystemLoadAvgDO systemLoadAvgDO);
}
