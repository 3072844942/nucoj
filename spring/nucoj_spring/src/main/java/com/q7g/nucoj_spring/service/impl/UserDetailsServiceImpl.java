package com.q7g.nucoj_spring.service.impl;

import com.q7g.nucoj_spring.po.LoginUser;
import com.q7g.nucoj_spring.exceotion.BizException;
import com.q7g.nucoj_spring.po.User;
import com.q7g.nucoj_spring.repository.RoleRepository;
import com.q7g.nucoj_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        // 查询用户信息
        Optional<User> byId = userRepository.findByUsername(s);
        if (!byId.isPresent()) {
            throw new BizException("用户名不存在");
        }
        User user = byId.get();
//        List<String> list = new LinkedList<>(Arrays.asList("test", "admin"));
        List<String> permissions = roleRepository.findById(user.getRole()).get().getPermissions();
        // 封装数据
        return new LoginUser(user, permissions);
    }
}
