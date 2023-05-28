package com.blue.cat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blue.cat.bean.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lixin
 * @since 2023-05-07
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    @Select("select id,username,account, avatar ,status ,user_level as userLevel from user_info where account=#{account} and password=#{password} and status = 0  limit 1")
    UserInfo queryAccountAndPassword(@Param("account") String account, @Param("password") String password);

    @Select("select id from user_info where account = #{account} limit 1")
    UserInfo queryByAccount(@Param("account")String account);

}
