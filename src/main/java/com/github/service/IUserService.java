package com.github.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.domain.User;

import java.util.List;

public interface IUserService extends IService<User> {

    List<User> selectAllUsers();

    User selectUserByUserName(String username);
}
