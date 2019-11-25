package cn.ds.double_live_demo.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class User {
    private String globalId;
    @NotNull(message = "用户名不能为空")
    private String username;
    @NotNull(message = "密码不能为空")
    private String password;
    private String salt;
    private String other;
}
