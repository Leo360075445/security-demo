package com.github.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<SysUser> {

}
