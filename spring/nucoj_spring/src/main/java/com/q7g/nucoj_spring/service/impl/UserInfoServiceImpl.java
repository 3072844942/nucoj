package com.q7g.nucoj_spring.service.impl;

import com.q7g.nucoj_spring.dto.*;
import com.q7g.nucoj_spring.exceotion.BizException;
import com.q7g.nucoj_spring.po.Contest;
import com.q7g.nucoj_spring.po.Menu;
import com.q7g.nucoj_spring.po.Role;
import com.q7g.nucoj_spring.po.User;
import com.q7g.nucoj_spring.repository.ContestRepository;
import com.q7g.nucoj_spring.repository.RoleRepository;
import com.q7g.nucoj_spring.repository.UserRepository;
import com.q7g.nucoj_spring.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ContestRepository contestRepository;

    /**
     * 转换用户基础对象
     * @param user
     * @return
     */
    private UserBaseDTO getUserBaseDTO(User user) {
        return UserBaseDTO.builder()
                .id(user.getUsername())
                .nickname(user.getNickName())
                .role(user.getRole().getCode())
                .grade(user.getGrade())
                .avatar(user.getAvatar())
                .build();
    }

    /**
     * 转换用户菜单
     * @param menu
     * @return
     */
    private UserMenuDTO getUserMenuDTO(Menu menu) {
        // 如果菜单为空， 直接返回
        if (menu == null) return null;
        // 子菜单默认为null
        List<UserMenuDTO> list = null;
        // 如果子菜单不为空， 生成子菜单
        if (menu.getChildren() != null) {
            list = new LinkedList<>();
            for (Menu i : menu.getChildren())
                // 递归创建子菜单
                list.add(getUserMenuDTO(i));
        }

        // 转换对象
        return UserMenuDTO.builder()
                .icon(menu.getIcon())
                .key(menu.getKey())
                .label(menu.getLabel())
                .type(menu.getType())
                .children(list)
                .build();
    }

    @Override
    public List<UserBaseDTO> getUserRank(int size) {
        // 以Grade降序排序
        List<User> list = userRepository.findAll(Sort.by(Sort.Direction.DESC, "grade"));
        // 转换
        List<UserBaseDTO> res = new LinkedList<>();
        for (User i : list) {
            if (res.size() >= size) break;
            res.add(getUserBaseDTO(i));
        }
        return res;
    }

    @Override
    public List<UserMenuDTO> getUserMenu(String userId) {
        Optional<User> byId = userRepository.findById(userId);
        if (!byId.isPresent()) throw new BizException("用户不存在");

        Optional<Role> role = roleRepository.findById(byId.get().getRole());
        if (!role.isPresent()) throw new BizException("用户身份错误");

        List<UserMenuDTO> res = new LinkedList<>();
        for (Menu i : role.get().getMenus())
            res.add(getUserMenuDTO(i));
        return res;
    }

    @Override
    public PageDTO<UserDetailDTO> getUsers(int current, int size) {
        // 计数
        long total = userRepository.count();
        // 分页查询
        Pageable pageable = PageRequest.of(current - 1, size);
        List<User> list = userRepository.findAll(pageable).toList();
        List<UserDetailDTO> res = new LinkedList<>();
        for (User i : list)
            res.add(new UserDetailDTO(i));
        return new PageDTO<>(total, res);
    }

    @Override
    public List<String> getUserContest(String userId) {
        List<Contest> contests = contestRepository.findAll(Sort.by(Sort.Direction.DESC, "startTime"));
        // 过滤出包含当前用户报名的比赛 -》 遍历出比赛ID
        return contests.stream().filter(item -> item.getEntryUsers().contains(userId)).map(Contest::getId).collect(Collectors.toList());
    }

    @Override
    public void entryContest(String userId, String contestId) {
        Contest contest = contestRepository.findById(contestId).get();
        if (contest.getEntryUsers().contains(userId)) throw new BizException("您已报名");
        contest.getEntryUsers().add(userId);
        contestRepository.save(contest);
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public void updateUserAvatar(String userId, String url) {
        User user = getUser(userId);
        user.setAvatar(url);
        userRepository.save(user);
    }
}
