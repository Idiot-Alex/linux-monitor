package com.hotstrip.linux.monitor.plugin.ssh.session;

import com.hotstrip.linux.monitor.plugin.ssh.HostDO;
import com.hotstrip.linux.monitor.plugin.ssh.executor.ChannelExecutorImpl;
import com.hotstrip.linux.monitor.plugin.ssh.executor.ExecuteResult;
import com.hotstrip.linux.monitor.plugin.ssh.executor.Executor;
import com.hotstrip.linux.monitor.plugin.ssh.utils.InputStreamUtil;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@Slf4j
public class TestSession {
    private HostDO hostDO;

    private SessionService sessionService;

    @Before
    public void setup() {
        log.info("TestSession before...");
        // replace your host, user and password, if you want run this test pass
        this.hostDO = HostDO.builder()
                .host("127.0.0.1")
                .user("test")
                .password("123456")
                .build();
        this.sessionService = new SessionServiceImpl();
        testOpenSession();
    }

    public void testOpenSession() {
        Session session = sessionService.openSession(hostDO);
        log.info("testOpenSession...session is connected: [{}]", session.isConnected());
        Assert.assertEquals(true, session.isConnected());
    }

    @Test
    public void testExecChannel() {
        final String key = SessionManage.getInstance().getSessionKey(hostDO.getHost(), hostDO.getUser());
        final Session session = SessionManage.getInstance().getSessionData(key);
        try {
            Channel channel = session.openChannel(ConstPool.EXEC_CHANNEL);
            ((ChannelExec) channel).setCommand("ps -ef | grep jar");

            Executor executor = new ChannelExecutorImpl((ChannelExec) channel);

            executor.execute(data -> {
                log.info(data.toString());
                sessionService.closeSession(session);
            });
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }
}
