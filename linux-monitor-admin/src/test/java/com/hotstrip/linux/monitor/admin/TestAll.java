package com.hotstrip.linux.monitor.admin;

import com.hotstrip.linux.monitor.admin.mapper.TestMapperAll;
import com.hotstrip.linux.monitor.admin.service.TestServiceAll;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses(value = {
        TestServiceAll.class,
        TestMapperAll.class
})
public class TestAll {
}
