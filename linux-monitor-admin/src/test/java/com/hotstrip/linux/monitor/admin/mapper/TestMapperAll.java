package com.hotstrip.linux.monitor.admin.mapper;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(value = {
        TestAdminUserMapper.class,
        TestServerMapper.class
})
public class TestMapperAll {
}
