package com.hotstrip.linux.monitor.plugin.ssh.session;

import com.hotstrip.linux.monitor.common.pojo.ServerData;
import com.hotstrip.linux.monitor.plugin.ssh.executor.ChannelExecutorImpl;
import com.hotstrip.linux.monitor.plugin.ssh.executor.Executor;
import com.hotstrip.linux.monitor.plugin.ssh.executor.handler.LoadAvgHandler;
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
    private ServerData hostDO;

    private SSHSessionService sessionService;

    @Before
    public void setup() {
        log.info("TestSession before...");
        // replace your host, user and password, if you want run this test pass
        this.hostDO = ServerData.builder()
                .host("127.0.0.1")
                .user("test")
                .password("123456")
                .build();
        this.sessionService = new DefaultSSHSessionServiceImpl();
        testOpenSession();
    }

    public void testOpenSession() {
        Session session = sessionService.   openSession(hostDO);
        log.info("testOpenSession...session is connected: [{}]", session.isConnected());
        Assert.assertTrue(session.isConnected());
    }

    @Test
    public void testExecChannel() {
        final String key = SSHSessionManage.getInstance().getSessionKey(hostDO.getHost(), hostDO.getUser());
        final Session session = SSHSessionManage.getInstance().getSessionData(key);
        try {
            Channel channel = session.openChannel(ConstPool.EXEC_CHANNEL);
            // 获取 Mac OS 系统的平均负载命令 uptime | cut -d":" -f4- | sed s/,//g
            ((ChannelExec) channel).setCommand("uptime | cut -d\":\" -f4- | sed s/,//g");

            Executor executor = new ChannelExecutorImpl((ChannelExec) channel);
            executor.execute(new LoadAvgHandler());

            sessionService.closeSession(session);
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }
}
