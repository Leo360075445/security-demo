package com.github.expression;

import com.github.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 自定义校验表达式
 */
@Component("exp")
public class CustomExpressionRoot {

    public boolean hasAuthority(String authority){
        //获取当前用户的权限集
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Set<String> permissions = loginUser.getPermissions();

        //判断用户权限集是否包含当前 authority
        return permissions.contains(authority);
    }
}
