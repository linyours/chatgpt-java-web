package com.blue.cat.controller;


import com.alibaba.fastjson.JSONObject;
import com.blue.cat.bean.annotation.VisitLimit;
import com.blue.cat.bean.constants.LimitEnum;
import com.blue.cat.bean.constants.UserLevelEnum;
import com.blue.cat.bean.req.UserInfoLoginReq;
import com.blue.cat.bean.req.UserInfoRegisterReq;
import com.blue.cat.bean.req.UserUpdateReq;
import com.blue.cat.bean.vo.CacheUserInfoVo;
import com.blue.cat.handler.UserHandler;
import com.blue.cat.utils.CacheUtil;
import com.blue.cat.utils.RegexUtil;
import com.blue.cat.utils.ResultVO;
import com.blue.cat.utils.SessionUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lixin
 * @since 2023-05-07
 */
@Slf4j
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserHandler userHandler;

    /**
     * 获取已登录的用户信息
     * @return
     */
    @GetMapping("/info")
    public ResultVO getUserInfo(){
        Optional<CacheUserInfoVo> userInfoVO = SessionUser.getUserInfoVO();
        if(userInfoVO.isPresent()){
            CacheUserInfoVo vo = new CacheUserInfoVo();
            BeanUtils.copyProperties(userInfoVO.get(),vo);
            vo.setUserLevel(UserLevelEnum.getDescByUserLevel(vo.getUserLevel()));
            return ResultVO.success(vo);
        }
        return ResultVO.success();
    }


    /**
     * 更新用户信息
     * @param updateReq
     * @return
     */
    @PostMapping("/update")
    public ResultVO update(UserUpdateReq updateReq){
        Boolean result=null;
        try {
            boolean admin = SessionUser.isAdmin();
            if(!admin){
                return ResultVO.fail("操作失败！当前登录账号不是管理账号！");
            }

            CacheUserInfoVo cacheUser = userHandler.updateUserInfo(updateReq);
            if(cacheUser==null){
                return ResultVO.fail("更新用户信息失败！");
            }
            String loginUser = CacheUtil.getIfPresent(String.valueOf(cacheUser.getId()));
            if(loginUser!=null){
                //此用户是登录中 更新登录缓存
                CacheUtil.put(String.valueOf(cacheUser.getId()), JSONObject.toJSONString(cacheUser));
            }
            return ResultVO.success("更新成功");
        }catch (Exception e){
            log.error("update updateReq={}",updateReq,e);
        }finally {
            log.info("update updateReq={},result={}",updateReq,result);
        }
        return ResultVO.fail("服务器繁忙!请联系作者！");
    }

    /**
     * 退出登录
     * @return
     */
    @GetMapping("/loginOut")
    public ResultVO loginOut(){
        try {
            Optional<CacheUserInfoVo> userInfoVO = SessionUser.getUserInfoVO();
            if(userInfoVO.isPresent()){
                CacheUtil.removeCache(String.valueOf(SessionUser.getUserId()));
            }
        }finally {
            //不管如何直接放回退出成功信息给前端
            return ResultVO.success();
        }
    }


    /**
     * 登录
     * @param loginReq
     * @return
     */
    @PostMapping("/login")
    public ResultVO login(@RequestBody @Valid UserInfoLoginReq loginReq, HttpServletResponse response){

        try {
            String account = loginReq.getAccount();
            if( RegexUtil.validateEmail(account) || RegexUtil.validatePhone(account)){
                CacheUserInfoVo infoVo = userHandler.login(loginReq);
                if(infoVo!=null){
                    return ResultVO.success(infoVo);
                }
                return ResultVO.fail("账号或者密码错误!");
            }
            return ResultVO.fail("账号格式不正确！");
        }catch (Exception e){
            log.error("login error loginReq={}",loginReq,e);
        }finally {
            log.info("login loginReq={}",loginReq);
        }
        return ResultVO.fail("服务器繁忙!请联系作者！");
    }

    /**
     * 用户注册
     * @param registerReq
     * @return
     */
    @VisitLimit(value = {LimitEnum.IP})
    @PostMapping("/register")
    public ResultVO register(@RequestBody @Valid UserInfoRegisterReq registerReq){
        log.info("register registerReq = {}",registerReq);
        try {
            String account = registerReq.getAccount();
            if(RegexUtil.validateEmail(account)||RegexUtil.validatePhone(account)){
                return userHandler.register(registerReq);
            }
            return ResultVO.fail("账号格式不正确！");
        }catch (Exception e){
            log.error("register error registerReq = {}",registerReq,e);
        }finally {
            log.info("register registerReq = {}",registerReq);
        }
        return ResultVO.fail("服务器繁忙!请联系作者！");
    }
}

