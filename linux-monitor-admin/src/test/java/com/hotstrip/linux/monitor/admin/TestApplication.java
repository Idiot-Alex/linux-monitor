package com.hotstrip.linux.monitor.admin;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@ActiveProfiles("h2")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AdminApplication.class)
public class TestApplication {

    @Test
    public void test() {
        log.info("test Application...");
    }
}
