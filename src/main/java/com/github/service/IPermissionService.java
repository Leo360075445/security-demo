package com.github.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.domain.Permission;

import java.util.Set;

public interface IPermissionService extends IService<Permission> {

    Set<String> selectPermsByUserId(Long userId);
}
