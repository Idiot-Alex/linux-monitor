package com.hotstrip.linux.monitor.plugin.ssh;

import com.hotstrip.linux.monitor.client.local.ssh.HostDO;
import com.hotstrip.linux.monitor.client.local.ssh.JSchClient;
import com.hotstrip.linux.monitor.plugin.ssh.session.SessionService;
import com.hotstrip.linux.monitor.plugin.ssh.session.SessionServiceImpl;
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

        SessionService sessionManage = new SessionServiceImpl();
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
