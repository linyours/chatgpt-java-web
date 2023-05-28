package com.blue.cat.bean.vo;

import com.blue.cat.bean.entity.PromptModel;
import lombok.Data;

import java.util.List;

@Data
public class PromptModelVo {

    private String type;

    private List<PromptModel> promptModels;
}
