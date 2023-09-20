package com.github.config;

import com.github.filter.JwtAuthenticationTokenFilter;
import com.github.hander.AccessDeniedHandlerImpl;
import com.github.hander.AuthenticationEntryPointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity //开启WebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    private AuthenticationEntryPointImpl authenticationEntryPoint;
    @Autowired
    private AccessDeniedHandlerImpl accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()// 开启自动配置的表单登录功能
                .and()
                //禁用csrf (CSRF：跨站请求伪造是一种常见的攻击方式)
                //前后端分离的应用中，前端和后端可能不在同一域上，这种情况下受跨域限制，启用CSRF保护可能会导致正常的API请求失败
                .csrf().disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests() // 认证配置
                .antMatchers("/sso/login","/hello").anonymous() //允许匿名访问
                .anyRequest() // 其他任何请求
                .authenticated(); // 都需要身份验证

        // 配置过滤器：将自定义的 JwtAuthenticationTokenFilter 配置到 SpringSecurity 过滤器链中
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // 配置异常处理器
        http.exceptionHandling()
                //认证失败处理器
                .authenticationEntryPoint(authenticationEntryPoint)
                //授权失败处理器
                .accessDeniedHandler(accessDeniedHandler);

        // 配置 SpringSecurity 允许跨域 CORS(Cross-Origin Resource Sharing
        // tips: 在分布式项目中一般会使用代理服务器,大多数情况下不会触发跨域问题
        http.cors();
    }

    // 重新方法将 AuthenticationManager交给容器管理
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * 替换默认的密码解析器
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
