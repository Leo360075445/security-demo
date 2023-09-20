package com.github.hander;

import com.alibaba.fastjson.JSON;
import com.github.util.JsonResult;
import com.github.util.WebUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 授权失败处理类
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        JsonResult<Object> result = JsonResult.error(HttpStatus.FORBIDDEN.value(), "您的权限不足!");
        String json = JSON.toJSONString(result);
        WebUtil.renderString(response,json);

    }
}

