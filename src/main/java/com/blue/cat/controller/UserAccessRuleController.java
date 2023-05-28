package com.blue.cat.controller;


import com.blue.cat.bean.req.UserAccessRuleUpdateReq;
import com.blue.cat.handler.access.UserAccessHandler;
import com.blue.cat.utils.ResultVO;
import com.blue.cat.utils.SessionUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 用户访问规则 前端控制器
 * </p>
 *
 * @author lixin
 * @since 2023-05-10
 */
@Slf4j
@RestController
@RequestMapping("/userAccessRule")
public class UserAccessRuleController {

    @Autowired
    private UserAccessHandler userAccessHandler;

    @PostMapping("/update")
    public ResultVO updateUserAccessRule(@RequestBody @Valid UserAccessRuleUpdateReq updateReq){
        Boolean result = null;
        try {

            boolean admin = SessionUser.isAdmin();
            if(!admin){
                return ResultVO.fail("操作失败！当前登录账号不是管理账号！");
            }
            result = userAccessHandler.updateUserAccess(updateReq);
            if(result){
                return ResultVO.success("更新成功！");
            }
        }catch (Exception e){
            log.error("updateUserAccessRule updateReq={}",updateReq,e);
        }finally {
            log.info("updateUserAccessRule updateReq={},result={}",updateReq,result);
        }
        return ResultVO.fail("更新失败！出现未知错误！");
    }

}

