package com.q7g.judgehost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 这是OnlineJudge项目--YuJudge的JudgeHost部分（专门判题的服务器）
 * https://github.com/yuzhanglong/YuJudge-JudgeHost
 * 基于Java的springboot框架
 * <p>
 * 本判题服务器（JudgeHost）负责接收用户的提交 并将代码编译、运行、比较，并返回判断情况
 * 其中，代码运行的核心被单独分离在这个仓库 https://github.com/yuzhanglong/YuJudge-Core
 * <p>
 * <p>
 * 考虑到判题的速度、短时间内可能需要大量判题，JudgeHost可能需要考虑多线程、集群相关
 *
 * @author yuzhanglong [email: yuzl1123@163.com]
 * @version V0.1
 */

@SpringBootApplication
@EnableEurekaClient
public class JudgeHostApplication {
    public static void main(String[] args) {
        SpringApplication.run(JudgeHostApplication.class, args);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return  new RestTemplate();
    }
}
