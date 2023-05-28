package com.blue.cat.handler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.blue.cat.bean.entity.PromptModel;
import com.blue.cat.bean.vo.PromptModelVo;
import com.blue.cat.mapper.PromptModelMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class PromptModelHandler {

    @Autowired
    private PromptModelMapper promptMapper;

    public List<PromptModelVo> getPromptGroup() {
        QueryWrapper<PromptModel> wrapper = new QueryWrapper<>();
        wrapper.eq("state", 1);
        wrapper.orderByDesc("sort");
        List<PromptModel> promptModels = promptMapper.selectList(wrapper);
        if (CollectionUtils.isEmpty(promptModels)) {
            return Collections.EMPTY_LIST;
        }
        // 使用Stream API进行分组
        Map<String, List<PromptModel>> groupedPromptModels = promptModels.stream()
                .collect(Collectors.groupingBy(PromptModel::getType));

// 将分组后的结果转换为List<PromptModelVo>
        List<PromptModelVo> promptModelVos = groupedPromptModels.entrySet().stream()
                .map(entry -> {
                    PromptModelVo vo = new PromptModelVo();
                    vo.setType(entry.getKey());
                    vo.setPromptModels(entry.getValue());
                    return vo;
                })
                .collect(Collectors.toList());

//        List<PromptModelVo> voList = promptModels.stream().collect(Collectors.groupingBy(PromptModel::getType)).entrySet().stream()
//                .map(entry -> {
//                    PromptModelVo vo = new PromptModelVo();
//                    vo.setType(entry.getKey());
//                    vo.setPromptModels(entry.getValue());
//                    return vo;
//                }).collect(Collectors.toList());
        return promptModelVos;
    }

    public PromptModel getPromptById(Long modelId) {
       return promptMapper.selectById(modelId);
    }
}
