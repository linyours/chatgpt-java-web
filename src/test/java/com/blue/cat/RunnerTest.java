package com.blue.cat;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


/**
 * 所有测试类都需要继承此类
 */
@ActiveProfiles(value = "dev")
@SpringBootTest
public class RunnerTest {



}
