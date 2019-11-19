package cn.ds.double_live_demo.service.impl;

import cn.ds.double_live_demo.entity.User;
import cn.ds.double_live_demo.mapper.UserMapper;
import cn.ds.double_live_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void addUser(User user) {
        userMapper.add(user);
    }

    @Override
    public User selectById(String id) {
        return userMapper.selectById(id);
    }
}
