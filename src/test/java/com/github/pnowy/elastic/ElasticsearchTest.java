package com.github.pnowy.elastic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApp.class)
public class ElasticsearchTest {

    @Test
    public void shouldRunExampleTest() throws Exception {
        System.out.println("test");

    }
}
