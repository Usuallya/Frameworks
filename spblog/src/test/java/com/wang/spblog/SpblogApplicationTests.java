package com.wang.spblog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpblogApplicationTests {

    @Test
    public void contextLoads() {
        Logger logger = LoggerFactory.getLogger(getClass());
        logger.debug("debug");
        logger.error("error");
    }

}
