package com.github.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.entity.SysUser;

import java.util.List;

public interface IUserService extends IService<SysUser> {

    List<SysUser> selectAllUsers();
}
