package com.q7g.nucoj_spring;

import com.alibaba.fastjson.JSON;
import com.q7g.nucoj_spring.enums.UserRoleEnum;
import com.q7g.nucoj_spring.po.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static com.q7g.nucoj_spring.enums.PermissionEnum.*;

@SpringBootApplication
@EnableScheduling
//@EnableEurekaClient
public class NucojSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(NucojSpringApplication.class, args);
    }

    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return  new RestTemplate();
    }

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 启动时自动注入数据
     */
//    @PostConstruct
    public void init() {
        mongoTemplate.dropCollection(Contest.class);
        mongoTemplate.dropCollection(Discuss.class);
        mongoTemplate.dropCollection(Link.class);
        mongoTemplate.dropCollection(Log.class);
        mongoTemplate.dropCollection(Notice.class);
        mongoTemplate.dropCollection(NUCOJ.class);
        mongoTemplate.dropCollection(Permission.class);
        mongoTemplate.dropCollection(Problem.class);
        mongoTemplate.dropCollection(Result.class);
        mongoTemplate.dropCollection(Role.class);
        mongoTemplate.dropCollection(Solution.class);
        mongoTemplate.dropCollection(Submit.class);
        mongoTemplate.dropCollection(Train.class);
        mongoTemplate.dropCollection(User.class);

        addInfo();
        addRoot();
        addPermission();
        addRole();
        addNotice();
        addLink();
    }

    private void addInfo() {
        NUCOJ nucoj = NUCOJ.builder()
                .time(System.currentTimeMillis())
                .title("中北大学ACM实验室")
                .build();
        mongoTemplate.insert(nucoj);
    }

    private void addLink() {
        Link link = Link.builder()
                .link("https://ac.nowcoder.com/acm/contest/vip-index")
                .title("牛客").build();
        mongoTemplate.insert(link);
    }

    private void addNotice() {
        Notice notice = Notice.builder()
                .id("1")
                .title("入站必读")
                .time(1666785958191L)
                .author("root")
                .context("Hello")
                .build();
        mongoTemplate.insert(notice);
    }

    private void addRoot() {
        List<String> accept = new LinkedList<>();
        List<String> tryProblem = new LinkedList<>();

        User user = User.builder()
                .username("root")
                .password("$2a$10$Gasv15czMEz9CLc1i3XhzORcKh0.CyqGWMT8ZqKSAhVntr1nkKeyu")
                .grade(1020)
                .role(UserRoleEnum.ROOT)
                .nickName("超凶大魔王")
                .avatar("https://www.static.snak.space/avatar/d0961e340ccffdfff77488a307271a95.jpg")
                .name("qlp")
                .number("20221020")
                .college("上兰帝国理工大学")
                .contact(UserContactInfo.builder()
                        .qq("3072844942")
                        .github("https://github.com/3072844942")
                        .blog("https://www.snak.space")
                        .build())
                .accept_problem(accept)
                .try_problem(tryProblem)
                .build();
        mongoTemplate.insert(user);
    }

    private void addRole() {
        List<String> allPermissions =
                new LinkedList<>(Arrays.asList(PROBLEM_DEBUG.getMessage(), PROBLEM_SUBMIT.getMessage()));

        List<String> basePermissions =
                new LinkedList<>(Arrays.asList(PROBLEM_DEBUG.getMessage()));

        // 添加菜单
        List<Menu> allMenus =
                JSON.parseArray("[\n" +
                        "  {\n" +
                        "    \"label\": \"首页\",\n" +
                        "    \"key\": \"/\",\n" +
                        "    \"icon\": \"HomeIcon\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"label\": \"公告\",\n" +
                        "    \"key\": \"/notice\",\n" +
                        "    \"icon\": \"NoticeIcon\",\n" +
                        "    \"children\": [\n" +
                        "      {\n" +
                        "        \"label\": \"添加公告\",\n" +
                        "        \"key\": \"/notice/add\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"label\": \"所有公告\",\n" +
                        "        \"key\": \"/notice/list\"\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"label\": \"题目\",\n" +
                        "    \"key\": \"/problem\",\n" +
                        "    \"icon\": \"ProblemIcon\",\n" +
                        "    \"children\": [\n" +
                        "      {\n" +
                        "        \"label\": \"添加题目\",\n" +
                        "        \"key\": \"/problem/add\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"label\": \"所有题目\",\n" +
                        "        \"key\": \"/problem/list\"\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"label\": \"题单\",\n" +
                        "    \"key\": \"/train\",\n" +
                        "    \"icon\": \"TrainIcon\",\n" +
                        "    \"children\": [\n" +
                        "      {\n" +
                        "        \"type\": \"group\",\n" +
                        "        \"label\": \"个人\",\n" +
                        "        \"key\": \"/train/self\",\n" +
                        "        \"children\": [\n" +
                        "          {\n" +
                        "            \"label\": \"添加题单\",\n" +
                        "            \"key\": \"/train/add\"\n" +
                        "          },\n" +
                        "          {\n" +
                        "            \"label\": \"我的题单\",\n" +
                        "            \"key\": \"/train/list/my\"\n" +
                        "          }\n" +
                        "        ]\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"type\": \"group\",\n" +
                        "        \"label\": \"全部\",\n" +
                        "        \"key\": \"/train/whole\",\n" +
                        "        \"children\": [\n" +
                        "          {\n" +
                        "            \"label\": \"所有题单\",\n" +
                        "            \"key\": \"/train/list\"\n" +
                        "          }\n" +
                        "        ]\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"label\": \"比赛\",\n" +
                        "    \"key\": \"/contest\",\n" +
                        "    \"icon\": \"ContestIcon\",\n" +
                        "    \"children\": [\n" +
                        "      {\n" +
                        "        \"label\": \"添加比赛\",\n" +
                        "        \"key\": \"/contest/add\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"label\": \"所有比赛\",\n" +
                        "        \"key\": \"/contest/list\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"label\": \"比赛实时\",\n" +
                        "        \"key\": \"/contest/now\"\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"label\": \"题解\",\n" +
                        "    \"key\": \"/solution\",\n" +
                        "    \"icon\": \"SolutionIcon\",\n" +
                        "    \"children\": [\n" +
                        "      {\n" +
                        "        \"type\": \"group\",\n" +
                        "        \"label\": \"个人\",\n" +
                        "        \"key\": \"/solution/self\",\n" +
                        "        \"children\": [\n" +
                        "          {\n" +
                        "            \"label\": \"添加题解\",\n" +
                        "            \"key\": \"/solution/add\"\n" +
                        "          },\n" +
                        "          {\n" +
                        "            \"label\": \"我的题解\",\n" +
                        "            \"key\": \"/solution/list/my\"\n" +
                        "          }\n" +
                        "        ]\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"type\": \"group\",\n" +
                        "        \"label\": \"全部\",\n" +
                        "        \"key\": \"/solution/whole\",\n" +
                        "        \"children\": [\n" +
                        "          {\n" +
                        "            \"label\": \"所有题解\",\n" +
                        "            \"key\": \"/solution/list\"\n" +
                        "          }\n" +
                        "        ]\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"label\": \"分享\",\n" +
                        "    \"key\": \"/discuss\",\n" +
                        "    \"icon\": \"DiscussIcon\",\n" +
                        "    \"children\": [\n" +
                        "      {\n" +
                        "        \"type\": \"group\",\n" +
                        "        \"label\": \"个人\",\n" +
                        "        \"key\": \"/discuss/self\",\n" +
                        "        \"children\": [\n" +
                        "          {\n" +
                        "            \"label\": \"添加分享\",\n" +
                        "            \"key\": \"/discuss/add\"\n" +
                        "          },\n" +
                        "          {\n" +
                        "            \"label\": \"我的分享\",\n" +
                        "            \"key\": \"/discuss/list/my\"\n" +
                        "          }\n" +
                        "        ]\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"type\": \"group\",\n" +
                        "        \"label\": \"全部\",\n" +
                        "        \"key\": \"/discuss/whole\",\n" +
                        "        \"children\": [\n" +
                        "          {\n" +
                        "            \"label\": \"所有分享\",\n" +
                        "            \"key\": \"/discuss/list\"\n" +
                        "          }\n" +
                        "        ]\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"label\": \"用户\",\n" +
                        "    \"key\": \"/user\",\n" +
                        "    \"icon\": \"UserIcon\",\n" +
                        "    \"children\": [\n" +
                        "      {\n" +
                        "        \"label\": \"所有用户\",\n" +
                        "        \"key\": \"/user/list\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"label\": \"角色管理\",\n" +
                        "        \"key\": \"/user/role\"\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"label\": \"系统管理\",\n" +
                        "    \"key\": \"/info\",\n" +
                        "    \"icon\": \"SettingIcon\",\n" +
                        "    \"children\": [\n" +
                        "      {\n" +
                        "        \"label\": \"网站管理\",\n" +
                        "        \"key\": \"/info/website\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"label\": \"友链管理\",\n" +
                        "        \"key\": \"/info/link\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"label\": \"日志管理\",\n" +
                        "        \"key\": \"/info/log\"\n" +
                        "      },\n" +
                        "      {\n" +
                        "        \"label\": \"判题服务\",\n" +
                        "        \"key\": \"/info/host\"\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"label\": \"个人中心\",\n" +
                        "    \"key\": \"/setting\",\n" +
                        "    \"icon\": \"SelfIcon\"\n" +
                        "  }\n" +
                        "]", Menu.class);

        List<Menu> medMenus =
                JSON.parseArray("[\n" +
                        "  {\n" +
                        "    \"label\": \"首页\",\n" +
                        "    \"key\": \"/\",\n" +
                        "    \"icon\": \"HomeIcon\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"label\": \"题单\",\n" +
                        "    \"key\": \"/train\",\n" +
                        "    \"icon\": \"TrainIcon\",\n" +
                        "    \"children\": [\n" +
                        "      {\n" +
                        "        \"type\": \"group\",\n" +
                        "        \"label\": \"个人\",\n" +
                        "        \"key\": \"/train/self\",\n" +
                        "        \"children\": [\n" +
                        "          {\n" +
                        "            \"label\": \"添加题单\",\n" +
                        "            \"key\": \"/train/add\"\n" +
                        "          },\n" +
                        "          {\n" +
                        "            \"label\": \"我的题单\",\n" +
                        "            \"key\": \"/train/list/my\"\n" +
                        "          }\n" +
                        "        ]\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"label\": \"题解\",\n" +
                        "    \"key\": \"/solution\",\n" +
                        "    \"icon\": \"SolutionIcon\",\n" +
                        "    \"children\": [\n" +
                        "      {\n" +
                        "        \"type\": \"group\",\n" +
                        "        \"label\": \"个人\",\n" +
                        "        \"key\": \"/solution/self\",\n" +
                        "        \"children\": [\n" +
                        "          {\n" +
                        "            \"label\": \"添加题解\",\n" +
                        "            \"key\": \"/solution/add\"\n" +
                        "          },\n" +
                        "          {\n" +
                        "            \"label\": \"我的题解\",\n" +
                        "            \"key\": \"/solution/list/my\"\n" +
                        "          }\n" +
                        "        ]\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"label\": \"分享\",\n" +
                        "    \"key\": \"/discuss\",\n" +
                        "    \"icon\": \"DiscussIcon\",\n" +
                        "    \"children\": [\n" +
                        "      {\n" +
                        "        \"type\": \"group\",\n" +
                        "        \"label\": \"个人\",\n" +
                        "        \"key\": \"/discuss/self\",\n" +
                        "        \"children\": [\n" +
                        "          {\n" +
                        "            \"label\": \"添加分享\",\n" +
                        "            \"key\": \"/discuss/add\"\n" +
                        "          },\n" +
                        "          {\n" +
                        "            \"label\": \"我的分享\",\n" +
                        "            \"key\": \"/discuss/list/my\"\n" +
                        "          }\n" +
                        "        ]\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"label\": \"个人中心\",\n" +
                        "    \"key\": \"/setting\",\n" +
                        "    \"icon\": \"SelfIcon\"\n" +
                        "  }\n" +
                        "]", Menu.class);

        List<Menu> baseMenus =
                JSON.parseArray("[\n" +
                        "  {\n" +
                        "    \"label\": \"首页\",\n" +
                        "    \"key\": \"/\",\n" +
                        "    \"icon\": \"HomeIcon\"\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"label\": \"题解\",\n" +
                        "    \"key\": \"/solution\",\n" +
                        "    \"icon\": \"SolutionIcon\",\n" +
                        "    \"children\": [\n" +
                        "      {\n" +
                        "        \"type\": \"group\",\n" +
                        "        \"label\": \"个人\",\n" +
                        "        \"key\": \"/solution/self\",\n" +
                        "        \"children\": [\n" +
                        "          {\n" +
                        "            \"label\": \"添加题解\",\n" +
                        "            \"key\": \"/solution/add\"\n" +
                        "          },\n" +
                        "          {\n" +
                        "            \"label\": \"我的题解\",\n" +
                        "            \"key\": \"/solution/list/my\"\n" +
                        "          }\n" +
                        "        ]\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"label\": \"分享\",\n" +
                        "    \"key\": \"/discuss\",\n" +
                        "    \"icon\": \"DiscussIcon\",\n" +
                        "    \"children\": [\n" +
                        "      {\n" +
                        "        \"type\": \"group\",\n" +
                        "        \"label\": \"个人\",\n" +
                        "        \"key\": \"/discuss/self\",\n" +
                        "        \"children\": [\n" +
                        "          {\n" +
                        "            \"label\": \"添加分享\",\n" +
                        "            \"key\": \"/discuss/add\"\n" +
                        "          },\n" +
                        "          {\n" +
                        "            \"label\": \"我的分享\",\n" +
                        "            \"key\": \"/discuss/list/my\"\n" +
                        "          }\n" +
                        "        ]\n" +
                        "      }\n" +
                        "    ]\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"label\": \"个人中心\",\n" +
                        "    \"key\": \"/setting\",\n" +
                        "    \"icon\": \"SelfIcon\"\n" +
                        "  }\n" +
                        "]", Menu.class);

        Role root = Role.builder().role(UserRoleEnum.ROOT).permissions(allPermissions).menus(allMenus).build();
        mongoTemplate.insert(root);

        Role manager = Role.builder().role(UserRoleEnum.MANAGER).permissions(allPermissions).menus(allMenus).build();
        mongoTemplate.insert(manager);

        Role member = Role.builder().role(UserRoleEnum.MEMBER).permissions(basePermissions).menus(medMenus).build();
        mongoTemplate.insert(member);

        Role user = Role.builder().role(UserRoleEnum.USER).permissions(basePermissions).menus(baseMenus).build();
        mongoTemplate.insert(user);
    }

    private void addPermission() {
        // todo 添加总权限表
        // 应该包含全部权限， 并且显示为是否可用？
        // 包含全部权限， 并且角色权限也包含全部权限？
    }
}
