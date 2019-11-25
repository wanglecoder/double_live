package cn.ds.double_live_demo.controller;

import cn.ds.double_live_demo.entity.User;
import cn.ds.double_live_demo.service.UserService;
import cn.ds.double_live_demo.util.BaseResponse;
import cn.ds.double_live_demo.util.Constant;
import cn.ds.double_live_demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("register")
    public BaseResponse<String> register(@RequestBody User user, HttpServletRequest request, HttpServletResponse response){
        String userId = userService.addUser(user);
        String jwt = JwtUtil.generateJWT(userId, user.getUsername(), request.getHeader("User-Agent"));
        response.setHeader("User-Token", jwt);
        return new BaseResponse<>(Constant.SUCCESS,"注册成功");
    }

    @PostMapping("login")
    public BaseResponse<String> login(@RequestBody User user, HttpServletResponse response,HttpServletRequest request){
        User auth = userService.selectById(user.getGlobalId());
        if(auth == null || !auth.getPassword().equals(user.getPassword())) return new BaseResponse<>(Constant.AUTH_FAIL,"用户名或密码错误");
        String jwt = JwtUtil.generateJWT(auth.getGlobalId(), user.getUsername(), request.getHeader("User-Agent"));
        response.setHeader("User-Token", jwt);
        return new BaseResponse<>(Constant.SUCCESS,"登录成功");
    }

    @GetMapping("check")
    public BaseResponse<String> checkJWT(){
        return new BaseResponse<>(Constant.SUCCESS,"JWT检验通过");
    }
}
