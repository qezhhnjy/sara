package com.qezhhnjy.login.service;

import com.qezhhnjy.login.entity.User;

import java.util.List;

/**
 * @author fuzy
 * create time 19-3-26-下午9:59
 */
public interface UserService {

    User findByUsername(String username);

    List<User> findAll();

    User save(User user);

    void delete(User user);

}
