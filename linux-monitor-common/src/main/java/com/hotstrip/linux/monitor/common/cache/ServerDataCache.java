package com.hotstrip.linux.monitor.common.cache;

import com.google.common.collect.Maps;
import com.hotstrip.linux.monitor.common.pojo.ServerPropertyData;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

public class ServerDataCache {
    private ServerDataCache() {
    }

    private static class ServerDataCacheHolder {
        private static final ServerDataCache INSTANCE = new ServerDataCache();
    }

    public static final ServerDataCache getInstance() {
        return ServerDataCacheHolder.INSTANCE;
    }

    private static final ConcurrentMap<String, List<ServerPropertyData>> SERVER_PROPERTY_MAP = Maps.newConcurrentMap();
}
