package com.blue.cat.bean.gpt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author huyd
 * @date 2023/5/5 11:22 PM
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenReq {

    private String method;

    private String url;

    private Map<String, String> headers;

    private String body;

}
