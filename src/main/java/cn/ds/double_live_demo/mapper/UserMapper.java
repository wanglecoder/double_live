package cn.ds.double_live_demo.mapper;

import cn.ds.double_live_demo.entity.User;

public interface UserMapper {

    void add(User user) ;

    User selectById(String id);
}
