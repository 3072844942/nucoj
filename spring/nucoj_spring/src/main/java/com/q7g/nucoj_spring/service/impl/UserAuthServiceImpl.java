package com.q7g.nucoj_spring.service.impl;

import com.alibaba.fastjson.JSON;
import com.q7g.nucoj_spring.po.LoginUser;
import com.q7g.nucoj_spring.dto.EmailDTO;
import com.q7g.nucoj_spring.dto.UserBaseDTO;
import com.q7g.nucoj_spring.enums.UserRoleEnum;
import com.q7g.nucoj_spring.exceotion.BizException;
import com.q7g.nucoj_spring.po.User;
import com.q7g.nucoj_spring.po.UserContactInfo;
import com.q7g.nucoj_spring.repository.UserRepository;
import com.q7g.nucoj_spring.service.RedisService;
import com.q7g.nucoj_spring.service.UserAuthService;
import com.q7g.nucoj_spring.util.JwtUtil;
import com.q7g.nucoj_spring.util.Utils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;

import static com.q7g.nucoj_spring.constant.MQPrefixConst.EMAIL_EXCHANGE;
import static com.q7g.nucoj_spring.constant.RedisPrefixConst.*;

@Service
public class UserAuthServiceImpl implements UserAuthService{

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RedisService redisService;
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 用户登录
     * @return 用户基础信息
     */
    @Override
    public UserBaseDTO login(String username, String password) {

        // 利用security机制进行用户认证
        // UserDetailsServiceImpl
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 如果验证不通过
        if (Objects.isNull(authenticate)) {
            throw new BizException("登录失败");
        }
        // 使用username生成jwt， 存入redis 作为用户验证
        LoginUser loginUser = (LoginUser)authenticate.getPrincipal();
//        if (!Objects.isNull(redisService.get(USER_TOKEN_KEY + loginUser.getUsername()))) {
//            throw new BizException("当前用户已经在其他地方登录");
//        }
        redisService.set(USER_TOKEN_KEY + loginUser.getUsername(), loginUser);

        User user1 = userRepository.findByUsername(username).get();
        UserBaseDTO res = UserBaseDTO.of(user1);
        res.setToken(JwtUtil.createJWT(user1.getUsername()));
        return res;
    }

    @Override
    public void logout() {
        // 获取SecurityContextHolder中的用户ID
        System.out.println( SecurityContextHolder.getContext().getAuthentication());
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getUsername();
        // 从redis中删除token
        redisService.del(USER_TOKEN_KEY + userId);
    }

    /**
     * 发送验证码
     * @param email 邮箱地址
     */
    @Override
    public void sendCode(String email) {
        if (!Utils.checkEmail(email)) {
            throw new BizException("邮箱输入错误");
        }
        // 生成六位随机验证码发送
        String code = Utils.getRandCode(6);
        // 发送验证码
        EmailDTO emailDTO = EmailDTO.builder()
                .email(email)
                .subject("验证码")
                .content("<h1>您的验证码为 " + code + " 有效期15分钟，请不要告诉他人哦！</h1>")
                .build();
        rabbitTemplate.convertAndSend(EMAIL_EXCHANGE, "*", new Message(JSON.toJSONBytes(emailDTO), new MessageProperties()));

        // 存入redis
        redisService.set(USER_CODE_KEY + email, code, CODE_EXPIRE_TIME);
    }

    private boolean checkUser(String username, String code) {
        if (!code.equals(redisService.get(USER_CODE_KEY + username))) {
            throw new BizException("验证码错误");
        }

        return userRepository.existsById(username);
    }

    /**
     * 用户注册
     */
    @Override
    public void register(String username, String password, String code) {
        if (checkUser(username, code)) throw new BizException("用户名已存在");
        // 密码加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user1 = User.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .grade(100)
                .role(UserRoleEnum.USER)
                .nickName("一只特立独行的猪")
                .avatar("https://www.static.snak.space/avatar/d0961e340ccffdfff77488a307271a95.jpg")
                .name("")
                .number("")
                .college("")
                .contact(UserContactInfo.builder()
                        .qq("")
                        .github("")
                        .blog("")
                        .build())
                .accept_problem(new LinkedList<>())
                .try_problem(new LinkedList<>())
                .build();
        userRepository.insert(user1);
    }

    @Override
    public void modify(String username, String password, String code) {
        if (!checkUser(username, code)) throw new BizException("不存在该用户");
        // 如果不存在该验证码
        if (!code.equals(redisService.get(USER_CODE_KEY + username))) {
            throw new BizException("验证码错误");
        }

        // 修改用户密码, 删除后插入
        User user1 = userRepository.findByUsername(username).get();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user1.setPassword(passwordEncoder.encode(password));
        userRepository.deleteById(user1.getUsername());
        userRepository.insert(user1);
    }

    @Override
    public void updateUserRole(String userId, int role) {
        Optional<User> byId = userRepository.findById(userId);
        if (!byId.isPresent()) {
            throw new BizException("用户不存在");
        }
        User user = byId.get();
        user.setRole(role == 1 ? UserRoleEnum.MANAGER : role == 2 ? UserRoleEnum.MEMBER : UserRoleEnum.USER);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(String userId, String nickName, String name, String avatar, String number, String college, String qq, String blog, String github) {
        User user = userRepository.findById(userId).get();
        user.setName(name);
        user.setNickName(nickName);
        user.setAvatar(avatar);
        user.setNumber(number);
        user.setCollege(college);
        user.setContact(UserContactInfo.builder()
                        .qq(qq)
                        .blog(blog)
                        .github(github)
                .build());
        userRepository.save(user);
    }
}
