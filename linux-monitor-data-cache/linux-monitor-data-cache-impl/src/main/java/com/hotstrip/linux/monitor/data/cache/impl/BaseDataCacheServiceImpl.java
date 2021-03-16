package com.hotstrip.linux.monitor.data.cache.impl;

import com.hotstrip.linux.monitor.common.pojo.SystemLoadAvgDO;
import com.hotstrip.linux.monitor.data.cache.api.BaseDataCacheService;
import java.util.Optional;

public class BaseDataCacheServiceImpl implements BaseDataCacheService {

    @Override
    public void cacheSystemLoadAvgData(final SystemLoadAvgDO systemLoadAvgDO) {
        Optional.ofNullable(systemLoadAvgDO).ifPresent(data -> BaseDataCache.SYSTEM_LOAD_AVG_MAP.put(data.getHost(), data));
    }
}
