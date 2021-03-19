package com.hotstrip.linux.monitor.plugin.ssh.session;

import com.google.common.collect.Maps;
import com.jcraft.jsch.Session;

import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

public class SSHSessionManage {

    private SSHSessionManage() {
    }

    private static class SessionManageHolder {
        private static final SSHSessionManage INSTANCE = new SSHSessionManage();
    }

    public static final SSHSessionManage getInstance() {
        return SessionManageHolder.INSTANCE;
    }

    private static final ConcurrentMap<String, Session> SESSION_MAP = Maps.newConcurrentMap();

    /**
     * get key from session
     * @param host
     * @param userName
     * @return
     */
    public String getSessionKey(final String host, final String userName) {
        return host + ConstPool.COLON + userName;
    }

    private String getSessionKey(final Session session) {
        return getSessionKey(session.getHost(), session.getUserName());
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

    /**
     * get session from map
     * @param key
     * @return
     */
    public Session getSessionData(final String key) {
        return SESSION_MAP.get(key);
    }
}
