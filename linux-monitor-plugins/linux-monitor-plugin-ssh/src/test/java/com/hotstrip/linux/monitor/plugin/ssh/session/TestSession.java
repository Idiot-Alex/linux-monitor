package com.hotstrip.linux.monitor.plugin.ssh.session;

import com.hotstrip.linux.monitor.plugin.ssh.HostDO;
import com.hotstrip.linux.monitor.plugin.ssh.utils.InputStreamUtil;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.io.IOException;
import java.io.InputStream;

public class TestSession {
    private HostDO hostDO;

    @Before
    public void setup() {
        System.out.println("TestSession before...");
        // replace your host, user and password, if you want run this test pass
        hostDO = HostDO.builder()
                .host("127.0.0.1")
                .user("test")
                .password("123456")
                .build();
        testOpenSession();
    }

    public void testOpenSession() {
        SessionService sessionManage = new SessionServiceImpl();
        Session session = sessionManage.openSession(hostDO);
        System.out.println(String.format("testOpenSession...session is connected: [%s]", session.isConnected()));
        Assert.assertEquals(true, session.isConnected());
    }

    @Test
    public void testExecChannel() {
        final String key = SessionManage.getInstance().getSessionKey(hostDO.getHost(), hostDO.getUser());
        final Session session = SessionManage.getInstance().getSessionData(key);
        try {
            final Channel channel = session.openChannel(ConstPool.EXEC_CHANNEL);
            ((ChannelExec)channel).setCommand("ps -ef | grep jar");

            InputStream in = channel.getInputStream();

            channel.connect(3000);

            while(true){
                final String result = InputStreamUtil.streamToString(in);
                System.out.println(result);

                if(channel.isClosed()){
                    if(in.available()>0) continue;
                    System.out.println("exit-status: "+channel.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                }catch(Exception ee){

                }
            }
            channel.disconnect();
            session.disconnect();
        } catch (JSchException | IOException e) {
            e.printStackTrace();
        }
    }
}
