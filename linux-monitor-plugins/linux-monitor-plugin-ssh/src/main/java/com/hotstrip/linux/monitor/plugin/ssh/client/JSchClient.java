package com.hotstrip.linux.monitor.plugin.ssh.client;

import com.jcraft.jsch.JSch;

/**
 * @author Hotstrip
 * return a JSch use singelton
 */
public class JSchClient {

    public static JSch getInstance() {
        return JSchClientHolder.instance;
    }

    private static class JSchClientHolder {
        private static final JSch instance = new JSch();
    }
}
