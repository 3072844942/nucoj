package com.q7g.nucoj_spring;

import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.*;
import com.q7g.nucoj_spring.dto.JudgeStatusDTO;
import com.q7g.nucoj_spring.enums.JudgeModel;
import com.q7g.nucoj_spring.po.*;
import com.q7g.nucoj_spring.repository.*;
import com.q7g.nucoj_spring.service.LogService;
import com.q7g.nucoj_spring.service.ProblemService;
import com.q7g.nucoj_spring.service.RedisService;
import com.q7g.nucoj_spring.service.UserProblemService;
import com.q7g.nucoj_spring.util.HttpUtil;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.core.parameters.P;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.*;

import static com.q7g.nucoj_spring.enums.FilePathEnum.JUDGE;

@SpringBootTest
class NucojSpringApplicationTests {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserProblemService userProblemService;

    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private LogService logService;

    @Autowired
    private LogRepository logRepository;

    @Value("${upload.local.path}")
    private String localPath;

    @Value("${oj.judgeAddress}")
    private String judgeAddress;

    @Autowired
    private ProblemService problemService;
    @Test
    void contextLoads() throws Exception {
    }
}
