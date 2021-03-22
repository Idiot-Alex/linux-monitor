package com.hotstrip.linux.monitor.plugin.ssh.client;

import com.hotstrip.linux.monitor.plugin.ssh.executor.ChannelExecutor;
import com.hotstrip.linux.monitor.plugin.ssh.executor.Executor;
import com.hotstrip.linux.monitor.plugin.ssh.executor.handler.LoadAvgHandler;
import com.hotstrip.linux.monitor.plugin.ssh.session.ConstPool;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.util.LinkedList;
import java.util.List;

public class SSHClient {
    private Session session;
    private List<Executor> executorList = new LinkedList<>();

    public SSHClient(final Session session) {
        this.session = session;
        // add executors
        addExecutors();
    }

    private void addExecutors() {
        try {
            Channel channel = session.openChannel(ConstPool.EXEC_CHANNEL);
            // 获取 Mac OS 系统的平均负载命令 uptime | cut -d":" -f4- | sed s/,//g
            ((ChannelExec) channel).setCommand("uptime | cut -d\":\" -f4- | sed s/,//g");

            Executor executor = new ChannelExecutor((ChannelExec) channel, new LoadAvgHandler());
            this.executorList.add(executor);
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    public void doExecute() {
        for (Executor executor : executorList) {
            executor.execute();
        }
    }
}
