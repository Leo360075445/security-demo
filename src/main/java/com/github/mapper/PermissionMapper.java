package com.github.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.domain.Permission;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PermissionMapper extends BaseMapper<Permission> {

    Set<String> selectPermsByUserId(Long userId);
}
