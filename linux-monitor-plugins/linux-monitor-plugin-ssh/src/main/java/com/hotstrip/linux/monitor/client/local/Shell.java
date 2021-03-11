package com.hotstrip.linux.monitor.client.local;

import com.hotstrip.linux.monitor.client.local.ssh.HostDO;
import com.hotstrip.linux.monitor.client.local.ssh.JSchClient;
import com.hotstrip.linux.monitor.client.local.ssh.session.SessionManage;
import com.hotstrip.linux.monitor.client.local.ssh.session.SessionManageImpl;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;


public class Shell {

    public static void main(String[] args) {
        JSch jSch = JSchClient.getInstance();
        HostDO hostDO = HostDO.builder()
                .host("127.0.0.1")
                .user("root")
                .password("password")
                .build();

        SessionManage sessionManage = new SessionManageImpl();
        Session session = sessionManage.openSession(hostDO);

        try {
            Channel channel = session.openChannel("shell");
            channel.setInputStream(System.in);
            channel.setOutputStream(System.out);

            channel.connect(3000);


        } catch (JSchException e) {
            e.printStackTrace();
            session.disconnect();
        }
    }
}
