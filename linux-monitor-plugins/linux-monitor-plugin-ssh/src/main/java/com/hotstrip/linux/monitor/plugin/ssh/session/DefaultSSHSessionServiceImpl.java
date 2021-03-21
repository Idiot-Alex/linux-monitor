package com.hotstrip.linux.monitor.plugin.ssh.session;

import com.hotstrip.linux.monitor.common.pojo.ServerData;
import com.hotstrip.linux.monitor.plugin.ssh.JSchClient;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class DefaultSSHSessionServiceImpl implements SSHSessionService {

    public Session openSession(ServerData serverData) {
        JSch jSch = JSchClient.getInstance();
        try {
            Session session = jSch.getSession(serverData.getUser(), serverData.getHost(), serverData.getPort());
            session.setPassword(serverData.getPassword());
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect(3000);
            // add SESSION_MAP
            if (session.isConnected()) {
                SSHSessionManage.getInstance().cacheSessionData(session);
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
            SSHSessionManage.getInstance().removeSessionData(session);
        }
    }
}
