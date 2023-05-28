package com.blue.cat.bean.gpt;

import lombok.Data;

/**
 * @author huyd
 * @date 2023/3/6 9:47 PM
 */
@Data
public class ChoiceMessage {

    private String role;
    private String content;

    public ChoiceMessage() {
    }

    public ChoiceMessage(String role, String content) {
        this.role = role;
        this.content = content;
    }

}
