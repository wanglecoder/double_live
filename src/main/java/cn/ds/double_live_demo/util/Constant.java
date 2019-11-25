package cn.ds.double_live_demo.util;

import java.util.UUID;

public class Constant {
    public static final String SUCCESS = "0000";
    public static final String SERVICE_ERROR = "0001";
    public static final String PARAM_ERROR = "0002";
    public static final String AUTH_FAIL = "0003";
    public static final String TOKEN_FAIL = "0004";

    public static final String JWT_ID = UUID.randomUUID().toString();

    /**
     * 加密密文
     */
    public static final String JWT_SECRET = "woyebuzhidaoxiediansha";
    public static final int JWT_TTL = 60*60*1000;  //millisecond
}
