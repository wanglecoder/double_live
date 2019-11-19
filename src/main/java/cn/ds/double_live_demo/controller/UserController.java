package cn.ds.double_live_demo.controller;

import cn.ds.double_live_demo.entity.User;
import cn.ds.double_live_demo.service.UserService;
import cn.ds.double_live_demo.util.BaseResponse;
import cn.ds.double_live_demo.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("register")
    public BaseResponse<String> register(@RequestBody User user){
        userService.addUser(user);
        return new BaseResponse<>(Constant.SUCCESS,"注册成功");
    }
}
