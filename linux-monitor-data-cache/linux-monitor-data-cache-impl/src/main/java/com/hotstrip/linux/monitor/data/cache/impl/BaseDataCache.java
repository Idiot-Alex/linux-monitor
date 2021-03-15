package com.hotstrip.linux.monitor.data.cache.impl;

import com.google.common.collect.Maps;
import com.hotstrip.linux.monitor.data.cache.api.BaseDataCacheService;

import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

public class BaseDataCache implements BaseDataCacheService {

    private BaseDataCache() {
    }

    private static class BaseDataCacheHolder {
        private static final BaseDataCache INSTANCE = new BaseDataCache();
    }

    public static final BaseDataCache getInstance() {
        return BaseDataCacheHolder.INSTANCE;
    }

    private static final ConcurrentMap<String, Object> PLUGIN_MAP = Maps.newConcurrentMap();

    public void cachePluginData(final Object pluginData) {
        Optional.ofNullable(pluginData).ifPresent(data -> PLUGIN_MAP.put(data.toString(), data));
    }
}
