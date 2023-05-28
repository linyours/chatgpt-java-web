package com.blue.cat.bean.constants;


/**
 * 目前系统支持的模型type
 */
public enum ModelEnum {

    CHAT_GPT3(ModelServiceTypeConstant.CHAT_GPT_MODEL3,"chat-gpt3.5模型"),
    CHAT_GPT4(ModelServiceTypeConstant.CHAT_GPT_MODEL4,"chat-gpt4模型");

    private String modelType;
    private String desc;

    ModelEnum(String modelType, String desc) {
        this.modelType = modelType;
        this.desc = desc;
    }

    public String getModelType() {
        return modelType;
    }

    public String getDesc() {
        return desc;
    }
}
