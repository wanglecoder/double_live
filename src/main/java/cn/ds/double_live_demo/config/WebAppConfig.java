package cn.ds.double_live_demo.config;

import cn.ds.double_live_demo.interceptor.ValidateLoginInterceptor;
import cn.ds.double_live_demo.util.SnowFlake;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebAppConfig extends WebMvcConfigurationSupport {

    @Value("${snowFlak.datacenter_id}")
    private String datacenterId;
    @Value("${snowFlak.machine_id}")
    private String machineId;

    //实现拦截器 要拦截的路径以及不拦截的路径
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(new ValidateLoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/register", "/user/login");
    }

    @Bean
    public SnowFlake getSnowFlake() {
        return new SnowFlake(Long.valueOf(datacenterId), Long.valueOf(machineId));
    }
}
