package com.hotstrip.linux.monitor.plugin.ssh;

import com.hotstrip.linux.monitor.plugin.ssh.session.TestSession;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(value = {TestSession.class})
public class TestAll {

}
