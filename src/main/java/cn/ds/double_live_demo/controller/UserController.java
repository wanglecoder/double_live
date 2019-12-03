package cn.ds.double_live_demo.controller;

import cn.ds.double_live_demo.entity.User;
import cn.ds.double_live_demo.service.UserService;
import cn.ds.double_live_demo.util.*;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("register")
    public BaseResponse<String> register(@RequestParam String username,@RequestParam String password,  HttpServletRequest request, HttpServletResponse response){
        Preconditions.checkNotNull(username,"用户名不能为空");
        Preconditions.checkNotNull(password,"密码不能为空");
        User res = userService.selectByUserName(username);
        if(res != null) return new BaseResponse<>(Constant.PARAM_ERROR,"用户名已存在,注册失败");
        res = new User();
        String salt = RandomUtil.generateRandom(8);
        String newPassWord = PasswordUtil.encrypt(password, salt);
        res.setUsername(username);
        res.setPassword(newPassWord);
        res.setSalt(salt);
        String userId = userService.addUser(res);
        String jwt = JwtUtil.generateJWT(userId, username, request.getHeader("User-Agent"));
        response.setHeader("User-Token", jwt);
        return new BaseResponse<>(Constant.SUCCESS,"注册成功");
    }

    @PostMapping("check/name")
    public BaseResponse<Boolean> CheckName(@RequestParam String username, HttpServletRequest request, HttpServletResponse response){
        Preconditions.checkNotNull(username,"用户名不能为空");
        User res = userService.selectByUserName(username);
        if(res == null) return new BaseResponse<>(Constant.SUCCESS,"用户名可用",true);
        return new BaseResponse<>(Constant.SUCCESS,"用户名已存在",false);
    }

    @PostMapping("login")
    public BaseResponse<String> login(@RequestParam String username,@RequestParam String password, HttpServletResponse response,HttpServletRequest request){
        Preconditions.checkNotNull(username,"用户名不能为空");
        Preconditions.checkNotNull(password,"密码不能为空");
        User auth = userService.selectByUserName(username);
        Preconditions.checkNotNull(auth,"用户不存在");
        Preconditions.checkNotNull(auth.getPassword(),"用户不存在");
        Preconditions.checkNotNull(auth.getSalt(),"用户不存在");
        String encrypt = PasswordUtil.encrypt(password, auth.getSalt());
        if(!auth.getPassword().equals(encrypt)) return new BaseResponse<>(Constant.AUTH_FAIL,"用户名或密码错误");
        String jwt = JwtUtil.generateJWT(auth.getGlobalId(), username, request.getHeader("User-Agent"));
        response.setHeader("User-Token", jwt);
        return new BaseResponse<>(Constant.SUCCESS,"登录成功");
    }

    @GetMapping("check")
    public BaseResponse<String> checkJWT(){
        return new BaseResponse<>(Constant.SUCCESS,"JWT检验通过");
    }
}
