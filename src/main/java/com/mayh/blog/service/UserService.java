package com.mayh.blog.service;

import com.mayh.blog.po.User;

public interface UserService {
    User checkUser(String username, String password);
}
