package com.qezhhnjy.login.dao;

import com.qezhhnjy.login.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author fuzy
 * create time 19-3-26-下午9:53
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsername(String username);

}
