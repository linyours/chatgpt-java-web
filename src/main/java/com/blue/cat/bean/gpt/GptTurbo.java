package com.blue.cat.bean.gpt;

import lombok.Data;

import java.util.List;

/**
 * @author huyd
 * @date 2023/3/6 9:42 PM
 */
@Data
public class GptTurbo {
    private String model;
    private List<ChoiceMessage> messages;
    private String user;

}
