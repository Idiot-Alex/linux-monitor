package com.hotstrip.linux.monitor.plugin.ssh.session;

import com.hotstrip.linux.monitor.common.pojo.ServerData;
import com.jcraft.jsch.Session;

/**
 * @author Hotstrip
 * this is a SSH session manage, provied some method to manage ssh session
 * such as open, close...
 */
public interface SSHSessionService {

    // open session
    Session openSession(ServerData serverData);


    // close session
    void closeSession(Session session);
}
