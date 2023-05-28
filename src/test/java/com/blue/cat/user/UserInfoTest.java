package com.blue.cat.user;


import com.blue.cat.RunnerTest;
import com.blue.cat.bean.req.UserInfoLoginReq;
import com.blue.cat.bean.req.UserInfoRegisterReq;
import com.blue.cat.controller.UserInfoController;
import com.blue.cat.mapper.UserInfoMapper;
import com.blue.cat.utils.ResultVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserInfoTest extends RunnerTest {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    UserInfoController userInfoController;

    @Test
    public void login() {
        UserInfoLoginReq loginReq = new UserInfoLoginReq();
        loginReq.setAccount("admin4");
        loginReq.setPassword("admin");
        ResultVO login = userInfoController.login(loginReq,null);
        assert login !=null;
    }

    @Test
    public void register(){
        UserInfoRegisterReq registerReq = new UserInfoRegisterReq();
        registerReq.setAccount("admin");
        registerReq.setPassword("admin");
        registerReq.setUsername("ai助手-test");
        ResultVO register = userInfoController.register(registerReq);
        assert register!=null;
    }
}
