package com.blue.cat.controller;


import com.blue.cat.bean.annotation.VisitLimit;
import com.blue.cat.bean.constants.CommonConstant;
import com.blue.cat.bean.constants.LimitEnum;
import com.blue.cat.bean.constants.ModelServiceTypeConstant;
import com.blue.cat.bean.entity.PromptModel;
import com.blue.cat.bean.req.CompletionReq;
import com.blue.cat.bean.vo.PromptModelVo;
import com.blue.cat.handler.PromptModelHandler;
import com.blue.cat.utils.HttpUtil;
import com.blue.cat.utils.ResultVO;
import com.blue.cat.utils.SessionUser;
import io.github.asleepyfish.util.OpenAiUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("/prompt")
public class PromptModelController {

    @Autowired
    private PromptModelHandler promptHandler;

    @GetMapping("/getPromptGroup")
    public ResultVO<List<PromptModelVo>> getPromptGroup() {
        try {
            List<PromptModelVo> modelVos = promptHandler.getPromptGroup();
            return ResultVO.success(modelVos);
        } catch (Exception e) {
            log.error("getPromptGroup error",e);
        }
        return ResultVO.fail("服务器繁忙!请联系作者！");
    }

    @VisitLimit(value = {LimitEnum.IP})
    @PostMapping("/completion")
    public void completion(@RequestBody @Validated CompletionReq completionReq, HttpServletResponse response) throws IOException {
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");

        StringBuilder builder = new StringBuilder();
        PromptModel prompt = promptHandler.getPromptById(Long.parseLong(completionReq.getModelId()));
        if(prompt == null || StringUtils.isBlank(prompt.getContent())){
            response.getOutputStream().write("模板已过期，请联系管理员".getBytes());
            return;
        }
        Long id = SessionUser.getUserId();
        String ip = HttpUtil.getIpAddress();
        String browserName = HttpUtil.browserName();
        builder.append(prompt.getContent()).append("\n");
        builder.append(completionReq.getContent());
        OpenAiUtils.createStreamChatCompletion(builder.toString(), UUID.randomUUID().toString(), response.getOutputStream());
    }
}

