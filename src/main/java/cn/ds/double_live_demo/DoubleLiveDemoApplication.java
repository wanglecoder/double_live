package cn.ds.double_live_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.ds.double_live_demo.mapper")
public class DoubleLiveDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoubleLiveDemoApplication.class, args);
    }

}
