package com.github.service.impl;

import com.github.domain.User;
import com.github.entity.LoginUser;
import com.github.service.ILoginService;
import com.github.util.JsonResult;
import com.github.util.JwtUtil;
import com.github.util.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    /**
     * 登录方法: 认证用户信息成功后生成令牌, 后续用户发起其他请求都会在header中携带令牌
     * @param user
     * @return
     */
    @Override
    public JsonResult login(User user) {
        Object principal = user.getUsername();
        Object credentials = user.getPassword();
        // UsernamePasswordAuthenticationToken 是 Authentication 的实现类, 将用户信息封装进该对象即可
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(principal, credentials);

        // 调用 authenticate()方法进行用户认证, 参数为 Authentication类型
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 认证通过后,使用 userId生成 JWT令牌
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);

        //存入redis
        redisCache.setCacheObject("login:" + userId, loginUser, 1, TimeUnit.HOURS);

        Map<String, Object> map = new HashMap<>();
        map.put("token",jwt);
        return JsonResult.ok(map).setMsg("登录成功");
    }

    /**
     * 登出方法: 从 SecurityContextHolder 中获取用户id, 并删除 redis 数据
     *          登出后, 原先的令牌依旧可以解析出数据, 但 redis中已没有对应的 userId
     *
     * @return
     */
    @Override
    public JsonResult logout() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        redisCache.deleteObject("login:" + userId);
        return JsonResult.ok().setMsg("退出登录");
    }


}
