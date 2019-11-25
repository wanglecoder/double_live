package cn.ds.double_live_demo.interceptor;

import cn.ds.double_live_demo.util.Constant;
import cn.ds.double_live_demo.util.GlobalConstant;
import cn.ds.double_live_demo.util.JwtUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ValidateLoginInterceptor extends HandlerInterceptorAdapter {
    private Logger log = LoggerFactory.getLogger(ValidateLoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //首先从请求头中获取jwt串，与页面约定好存放jwt值的请求头属性名为User-Token
        String jwt = request.getHeader("User-Token");
        log.info("[登录校验拦截器]-从header中获取的jwt为:{}", jwt);
        //判断jwt是否有效
        if (StringUtils.isNotBlank(jwt)) {
            //校验jwt是否有效,有效则返回json信息，无效则返回空
            String retJson = JwtUtil.validateLogin(jwt);
            log.info("[登录校验拦截器]-校验JWT有效性返回结果:{}", retJson);
            //retJSON为空则说明jwt超时或非法
            if (StringUtils.isNotBlank(retJson)) {
                JSONObject jsonObject = JSONObject.parseObject(retJson);
                //校验客户端信息
                String userAgent = request.getHeader("User-Agent");
                if (userAgent.equals(jsonObject.getString("userAgent"))) {
                    //获取刷新后的jwt值，设置到响应头中
                    response.setHeader("User-Token", jsonObject.getString("freshToken"));
                    //将客户编号设置到session中
                    request.getSession().setAttribute(GlobalConstant.SESSION_CUSTOMER_NO_KEY, jsonObject.getString("userId"));
                    return true;
                } else {
                    log.warn("[登录校验拦截器]-客户端浏览器信息与JWT中存的浏览器信息不一致，重新登录。当前浏览器信息:{}", userAgent);
                }
            } else {
                log.warn("[登录校验拦截器]-JWT非法或已超时，重新登录");
            }
        }
        //输出响应流
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", Constant.TOKEN_FAIL);
        jsonObject.put("msg", "未登录");
        jsonObject.put("data", "");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.getOutputStream().write(jsonObject.toJSONString().getBytes("UTF-8"));
        return false;
    }
}
