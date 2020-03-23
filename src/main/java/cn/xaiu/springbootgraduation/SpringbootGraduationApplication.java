package cn.xaiu.springbootgraduation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringbootGraduationApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootGraduationApplication.class, args);
    }

}
