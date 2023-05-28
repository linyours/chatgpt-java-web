package com.blue.cat.handler;

import com.blue.cat.bean.entity.PopupInfo;
import com.blue.cat.service.impl.PopupInfoServiceImpl;
import com.blue.cat.utils.ResultVO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

@Component
public class PopupHandler {

    @Resource
    private PopupInfoServiceImpl popupInfoService;

    /**
     * 获取公告内容
     * @return
     */
    public ResultVO queryPopupInfo(String source){
        Optional<PopupInfo> popupInfo = popupInfoService.queryPopupInfo(source);
        return popupInfo.<ResultVO>map(ResultVO::success).orElseGet(() -> ResultVO.success(new PopupInfo()));
    }

}
