package com.github.service;

import com.github.domain.User;
import com.github.util.JsonResult;

public interface ILoginService {

    JsonResult login(User user);

    JsonResult logout();
}
