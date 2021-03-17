package com.hotstrip.linux.monitor.admin.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(value = {
        TestAdminUserService.class,
        TestServerService.class
})
public class TestServiceAll {
}
