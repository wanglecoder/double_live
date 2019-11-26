package cn.ds.double_live_demo.service.impl;

import cn.ds.double_live_demo.entity.User;
import cn.ds.double_live_demo.mapper.UserMapper;
import cn.ds.double_live_demo.service.UserService;
import cn.ds.double_live_demo.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private SnowFlake snowFlake;

    @Override
    public String addUser(User user) {
        user.setGlobalId(snowFlake.nextId());
        int add = userMapper.add(user);
        if (add > 0) return user.getGlobalId();
        return null;
    }

    @Override
    public User selectById(String id) {
        return userMapper.selectById(id);
    }

    @Override
    public User selectByUserName(String username) {
        return userMapper.selectByUserName(username);
    }
}
