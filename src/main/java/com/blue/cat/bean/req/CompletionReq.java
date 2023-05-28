package com.blue.cat.bean.req;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author huyd
 * @date 2023/5/14 12:16 AM
 */
@Data
public class CompletionReq {

    /**
     * 提示模板id
     */
    @NotBlank(message = "模板id不能为空")
    private String modelId;

    /**
     * 提交的内容
     */
    @NotBlank(message = "内容不能为空")
    private String content;

}
