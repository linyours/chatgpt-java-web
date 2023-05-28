package com.blue.cat.bean.gpt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author liuzilin
 * @date 2023/5/5 11:22 PM
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImageReq {

    private String authToken;

    private String modelId;

    private String negativePrompt;

    private String prompt;

    private Integer modelIsPublic;

    private Integer width;

    private Integer height;

    private Integer imageNum;

}
