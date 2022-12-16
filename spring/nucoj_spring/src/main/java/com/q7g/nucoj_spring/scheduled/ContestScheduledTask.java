package com.q7g.nucoj_spring.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ContestScheduledTask {

    //todo 定时将比赛题目加入到题库中, 这样子需要给比赛的样例也动态开表
//    @Scheduled(cron = "0 0 12 * * ?")
//    public void addProblem() {
//
//    }
}
