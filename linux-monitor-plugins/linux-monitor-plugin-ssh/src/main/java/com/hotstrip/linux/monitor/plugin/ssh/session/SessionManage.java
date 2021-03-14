package com.hotstrip.linux.monitor.plugin.ssh.session;

import com.google.common.collect.Maps;
import com.jcraft.jsch.Session;

import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

public class SessionManage {

    private SessionManage() {
    }

    private static class SessionManageHolder {
        private static final SessionManage INSTANCE = new SessionManage();
    }

    public static final SessionManage getInstance() {
        return SessionManageHolder.INSTANCE;
    }

    private static final ConcurrentMap<String, Session> SESSION_MAP = Maps.newConcurrentMap();

    public void cacheSessionData(final Session sessionData) {
        Optional.ofNullable(sessionData).ifPresent(data -> SESSION_MAP.put(data.toString(), data));
    }
}
