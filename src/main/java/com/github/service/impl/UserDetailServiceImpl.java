package com.github.service.impl;

import com.github.domain.User;
import com.github.entity.LoginUser;
import com.github.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private IUserService userService;
    /*@Autowired
    private IPermissionService permissionService;*/


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.selectUserByUserName(username);

        if(Objects.isNull(user)){
            throw new RuntimeException("用户名或密码错误!");
        }
        return createLoginUser(user);
    }

    private UserDetails createLoginUser(User user) {
        // TODO::权限查询
        return new LoginUser(user, null);
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("admin123");
        System.out.println("encode = " + encode);
    }
}
