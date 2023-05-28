package com.blue.cat;

import io.github.asleepyfish.util.OpenAiUtils;
import org.junit.jupiter.api.Test;


class BlueCatApplicationTests extends RunnerTest {

    @Test
    public void testGenerateImg() {
        OpenAiUtils.createImage("英短").forEach(System.out::println);
    }

    @Test
    public void createStreamChatCompletion() {
        System.out.println("tets");
    }


}
