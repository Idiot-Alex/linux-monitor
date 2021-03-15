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

    /**
     * get key from session
     * @param session
     * @return
     */
    private String getSessionKey(final Session session) {
        return session.getHost() + ConstPool.COLON + session.getPort();
    }

    /**
     * add session into map
     * @param session
     */
    public void cacheSessionData(final Session session) {
        final String key = getSessionKey(session);
        Optional.ofNullable(session).ifPresent(data -> SESSION_MAP.put(key, data));
    }

    /**
     * remove session from map
     * @param session
     */
    public void removeSessionData(Session session) {
        final String key = getSessionKey(session);
        Optional.of(SESSION_MAP.get(key)).ifPresent(data -> SESSION_MAP.remove(key));
    }
}
