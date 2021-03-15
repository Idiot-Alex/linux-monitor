package com.hotstrip.linux.monitor.plugin.ssh.session;

import com.hotstrip.linux.monitor.plugin.ssh.HostDO;
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
        hostDO = HostDO.builder()
                .host("121.42.160.109")
                .user("root")
                .password("272377@zx")
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

            InputStream in=channel.getInputStream();

            channel.connect(3000);

            byte[] tmp=new byte[1024];
            while(true){
                while(in.available()>0){
                    int i=in.read(tmp, 0, 1024);
                    if(i<0)break;
                    System.out.print(new String(tmp, 0, i));
                }
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
