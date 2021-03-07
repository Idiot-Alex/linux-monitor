package com.hotstrip.linux.monitor.client.local.ssh.session;

import com.hotstrip.linux.monitor.client.local.ssh.HostDO;
import com.jcraft.jsch.Session;

/**
 * @author Hotstrip
 * this is a SSH session manage, provied some method to manage ssh session
 * such as open, close...
 */
public interface SessionManage {

    // open session
    Session openSession(HostDO hostDO);


    // close session
    void closeSession(Session session);
}
