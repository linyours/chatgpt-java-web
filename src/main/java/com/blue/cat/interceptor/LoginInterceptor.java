package com.blue.cat.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.blue.cat.bean.common.BaseCodeEnum;
import com.blue.cat.bean.constants.CommonConstant;
import com.blue.cat.bean.vo.CacheUserInfoVo;
import com.blue.cat.utils.CacheUtil;
import com.blue.cat.utils.SessionUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            //放行OPTIONS请求
            String method = request.getMethod();
            if("OPTIONS".equals(method)){
                return true;
            }
            CacheUserInfoVo userInfo = null;
            String sessionId= request.getHeader(CommonConstant.TOKEN);
            log.info("LoginInterceptor sessionId ={}", sessionId);
            if (sessionId != null) {
                String sessionUser = CacheUtil.getIfPresent(sessionId);
                log.info("LoginInterceptor sessionUser ={}", sessionUser);
                if (sessionUser != null) {
                    CacheUtil.put(sessionId, sessionUser);
                    userInfo = JSONObject.parseObject(sessionUser, CacheUserInfoVo.class);
                    log.info("LoginInterceptor userDTO={}", JSONObject.toJSONString(userInfo));
                }
            }
            // 重定向到登录
//            if (userInfo == null) {
//                responseResult(response, BaseCodeEnum.LOGIN_EXPIRE.getMsg());
//                return false;
//            }
            if(userInfo!=null){
                SessionUser.setSessionUserInfo(userInfo);
            }
            return true;
        }catch (Exception e){
            log.error("LoginInterceptor error",e);
            responseResult(response, BaseCodeEnum.SERVER_BUSY.getMsg());
            return false;
        }
    }

    private void responseResult(HttpServletResponse response, String msg) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.getWriter().write(msg);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //一次请求后需要删除线程变量，否则会造成内存泄漏
        SessionUser.remove();
    }
}
