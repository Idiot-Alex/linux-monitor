package com.hotstrip.linux.monitor.plugin.ssh.session;

import com.hotstrip.linux.monitor.client.local.ssh.HostDO;
import com.hotstrip.linux.monitor.client.local.ssh.JSchClient;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SessionServiceImpl implements SessionService {

    public Session openSession(HostDO hostDO) {
        JSch jSch = JSchClient.getInstance();
        try {
            Session session = jSch.getSession(hostDO.getUser(), hostDO.getHost(), hostDO.getPort());
            session.setPassword(hostDO.getPassword());
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect(3000);
            // add SESSION_MAP
            if (session.isConnected()) {
                SessionManage.getInstance().cacheSessionData(session);
            }
            return session;
        } catch (JSchException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void closeSession(Session session) {
        if (null != session) {
            session.disconnect();
            // remove SESSION_MAP
            SessionManage.getInstance().removeSessionData(session);
        }
    }
}
