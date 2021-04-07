package com.hotstrip.linux.monitor.common.cache;

import com.google.common.collect.Maps;
import com.hotstrip.linux.monitor.common.pojo.ServerPropertyData;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class ServerDataCache {
    private ServerDataCache() {
    }

    private static class ServerDataCacheHolder {
        private static final ServerDataCache INSTANCE = new ServerDataCache();
    }

    public static final ServerDataCache getInstance() {
        return ServerDataCacheHolder.INSTANCE;
    }

    private static final ConcurrentMap<String, ServerPropertyData> SERVER_PROPERTY_MAP = Maps.newConcurrentMap();

    /**
     * get cache data list
     * @return {@linkplain List<ServerPropertyData>}
     */
    public List<ServerPropertyData> getCacheDataList() {
        return SERVER_PROPERTY_MAP.values().stream().collect(Collectors.toList());
    }

    /**
     * set cache data
     * @param serverPropertyData {@linkplain ServerPropertyData}
     */
    public void setCacheData(final ServerPropertyData serverPropertyData) {
        Optional.ofNullable(serverPropertyData).ifPresent(this::addOrUpdate);
    }

    /**
     * update cache data
     * @param serverPropertyData
     */
    private void addOrUpdate(final ServerPropertyData serverPropertyData) {
        final String key = serverPropertyData.getHost();
        if (SERVER_PROPERTY_MAP.containsKey(key)) {
            ServerPropertyData tempData = SERVER_PROPERTY_MAP.get(key);
            final ServerPropertyData resultData = Optional.ofNullable(tempData).map(data -> {
                if (StringUtils.isNotBlank(serverPropertyData.getOsName()))
                    tempData.setOsName(serverPropertyData.getOsName());
                if (null != serverPropertyData.getOne())
                    tempData.setOne(serverPropertyData.getOne());
                if (null != serverPropertyData.getFive())
                    tempData.setFive(serverPropertyData.getFive());
                if (null != serverPropertyData.getFifteen())
                    tempData.setFifteen(serverPropertyData.getFifteen());
                if (null != serverPropertyData.getCores())
                    tempData.setCores(serverPropertyData.getCores());
                return tempData;
            }).orElse(null);
            SERVER_PROPERTY_MAP.put(key, resultData);
        } else {
            SERVER_PROPERTY_MAP.put(key, serverPropertyData);
        }
    }
}
