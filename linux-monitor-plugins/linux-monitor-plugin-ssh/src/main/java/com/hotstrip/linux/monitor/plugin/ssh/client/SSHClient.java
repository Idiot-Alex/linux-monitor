package com.hotstrip.linux.monitor.plugin.ssh.client;

import com.hotstrip.linux.monitor.common.listener.ShellResultListener;
import com.hotstrip.linux.monitor.plugin.ssh.enums.LinuxCommandEnum;
import com.hotstrip.linux.monitor.plugin.ssh.executor.ChannelExecutor;
import com.hotstrip.linux.monitor.plugin.ssh.executor.Executor;
import com.hotstrip.linux.monitor.plugin.ssh.executor.handler.LoadAvgHandler;
import com.hotstrip.linux.monitor.plugin.ssh.executor.handler.OSNameHandler;
import com.hotstrip.linux.monitor.plugin.ssh.session.ConstPool;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class SSHClient {
    private Session session;
    private ShellResultListener shellResultListener;
    private List<Executor> executorList = new LinkedList<>();

    public SSHClient(final Session session, final ShellResultListener shellResultListener) {
        this.session = session;
        this.shellResultListener = shellResultListener;
        // add executors
        addExecutors();
    }

    /**
     * do execute
     */
    public void doExecute() {
        for (Executor executor : executorList) {
            executor.execute();
        }
    }

    /**
     * add Executors
     */
    private void addExecutors() {
        Optional.ofNullable(getOSNameExecutor()).ifPresent(executor -> this.executorList.add(executor));
        Optional.ofNullable(getLoadAvgExecutor()).ifPresent(executor -> this.executorList.add(executor));
    }

    /**
     * get OS name Executor
     * @return
     */
    private Executor getOSNameExecutor() {
        try {
            Channel channel = session.openChannel(ConstPool.EXEC_CHANNEL);
            ((ChannelExec) channel).setCommand(LinuxCommandEnum.OS_NAME.getCommand());
            Executor executor = new ChannelExecutor((ChannelExec) channel, this.shellResultListener, new OSNameHandler());
            return executor;
        } catch (JSchException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * get load avg Executor
     * @return
     */
    private Executor getLoadAvgExecutor() {
        try {
            Channel channel = session.openChannel(ConstPool.EXEC_CHANNEL);
            ((ChannelExec) channel).setCommand(LinuxCommandEnum.LOAD_AVG.getCommand());
            Executor executor = new ChannelExecutor((ChannelExec) channel, this.shellResultListener, new LoadAvgHandler());
            return executor;
        } catch (JSchException e) {
            e.printStackTrace();
            return null;
        }
    }

}
