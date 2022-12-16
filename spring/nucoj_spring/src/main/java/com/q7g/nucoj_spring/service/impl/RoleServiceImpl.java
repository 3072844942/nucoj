package com.q7g.nucoj_spring.service.impl;

import com.q7g.nucoj_spring.dto.PageDTO;
import com.q7g.nucoj_spring.dto.RoleDTO;
import com.q7g.nucoj_spring.dto.UserMenuDTO;
import com.q7g.nucoj_spring.po.Menu;
import com.q7g.nucoj_spring.po.Role;
import com.q7g.nucoj_spring.repository.RoleRepository;
import com.q7g.nucoj_spring.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public PageDTO<RoleDTO> getRoles(int current, int size) {
        Pageable pageable = PageRequest.of(current - 1, size);
        List<Role> roles = roleRepository.findAll(pageable).toList();
        long total = roleRepository.count();
        List<RoleDTO> res = new LinkedList<>();
        for (Role i : roles)
            res.add(RoleDTO.builder()
                    .role(i.getRole())
                    .build());
        return new PageDTO<>(total, res);
    }
}
