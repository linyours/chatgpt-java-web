package com.blue.cat.mapper;

import com.blue.cat.bean.entity.LoginUserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 用户登录的id Mapper 接口
 * </p>
 *
 * @author lixin
 * @since 2023-05-11
 */
@Mapper
public interface LoginUserInfoMapper extends BaseMapper<LoginUserInfo> {

    @Delete("delete from login_user_info ")
    int deleteAll();

    @Select("select * from login_user_info ")
    List<LoginUserInfo> getAll();
}
