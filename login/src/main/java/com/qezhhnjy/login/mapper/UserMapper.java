package com.qezhhnjy.login.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qezhhnjy.login.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author fuzy
 * create time 19-3-24-上午11:21
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT * FROM user")
    List<User> getAll();

    @Select("SELECT role FROM user WHERE username = #{username} ")
    String getRole(String username);
}
