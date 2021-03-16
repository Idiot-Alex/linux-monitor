package com.hotstrip.linux.monitor.data.cache.impl;

import com.google.common.collect.Maps;
import com.hotstrip.linux.monitor.common.pojo.SystemLoadAvgDO;
import com.hotstrip.linux.monitor.data.cache.api.BaseDataCacheService;

import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

public class BaseDataCache {

    public static final ConcurrentMap<String, SystemLoadAvgDO> SYSTEM_LOAD_AVG_MAP = Maps.newConcurrentMap();

}
