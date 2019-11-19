package cn.ds.double_live_demo.service;

import cn.ds.double_live_demo.entity.User;

public interface UserService {
    void addUser(User user);

    User selectById(String id);
}
