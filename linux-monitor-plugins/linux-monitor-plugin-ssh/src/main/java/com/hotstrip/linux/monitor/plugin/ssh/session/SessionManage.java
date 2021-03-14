package com.hotstrip.linux.monitor.plugin.ssh.session;

import com.google.common.collect.Maps;
import com.jcraft.jsch.Session;

import java.util.List;
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

    public void cacheSessionData(final Session session) {
        final String key = session.getHost() + ConstPool.COLON + session.getPort();
        Optional.ofNullable(session).ifPresent(data -> SESSION_MAP.put(key, data));
    }

    public void removeSessionData(Session session) {
        final String key = session.getHost() + ConstPool.COLON + session.getPort();
        Optional.of(SESSION_MAP.get(key)).ifPresent(data -> SESSION_MAP.remove(key));
    }
}
