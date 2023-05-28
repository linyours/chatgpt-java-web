package com.blue.cat.bean.gpt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatReq {
    private String prompt;

    private String conversationId;

    private String userId;
}
