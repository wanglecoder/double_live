package cn.ds.double_live_demo.entity;

import lombok.Data;

@Data
public class User {
    private String globalId;
    private String username;
    private String password;
    private String salt;
    private String other;
}
