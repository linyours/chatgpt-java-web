package com.blue.cat.controller;


import com.blue.cat.bean.req.PopupReq;
import com.blue.cat.handler.PopupHandler;
import com.blue.cat.utils.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 *
 * @author linyous
 * @since 2023-05-10
 */
@Slf4j
@RestController
@RequestMapping("/popupInfo")
public class PopupController {

    @Autowired
    private PopupHandler popupHandler;

    /**
     * 获取公告内容
     * @return
     */
    @PostMapping("/getPopupInfo")
    public ResultVO getPopupInfo(@RequestBody PopupReq source){
        try {
            return popupHandler.queryPopupInfo(source.getSource());
        }catch (Exception e){
            log.error("getPopupInfo error",e);
        }
        return ResultVO.fail("服务器繁忙!请联系作者！");
    }

}

