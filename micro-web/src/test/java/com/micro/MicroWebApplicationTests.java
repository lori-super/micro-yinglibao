package com.micro;

import com.micro.properties.AliSmsProperties;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes = MicroWebApplication.class)
class MicroWebApplicationTests {

    @Resource
    private AliSmsProperties aliSmsProperties;
    @Test
    void contextLoads() {
        System.out.println(aliSmsProperties.getAccessKeyId());
    }

}
