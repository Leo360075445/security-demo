package com.github.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.entity.SysUser;
import com.github.mapper.UserMapper;
import com.github.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, SysUser> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<SysUser> selectAllUsers() {
        return userMapper.selectList(null);
    }
}
