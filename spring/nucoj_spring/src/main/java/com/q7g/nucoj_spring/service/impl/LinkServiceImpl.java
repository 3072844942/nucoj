package com.q7g.nucoj_spring.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.q7g.nucoj_spring.dto.LinkDTO;
import com.q7g.nucoj_spring.dto.PageDTO;
import com.q7g.nucoj_spring.po.Link;
import com.q7g.nucoj_spring.repository.LinkRepository;
import com.q7g.nucoj_spring.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LinkServiceImpl implements LinkService {
    @Autowired
    private LinkRepository linkRepository;

    @Override
    public PageDTO<LinkDTO> getAll() {
        long total = linkRepository.count();
        List<Link> list = linkRepository.findAll();
        return new PageDTO<>(total, list.stream().map(LinkDTO::of).collect(Collectors.toList()));
    }

    @Override
    public void updateLinks(List<Object> list) {
        linkRepository.deleteAll();
        for (Object i : list) {
            Link link = JSONObject.parseObject(JSON.toJSONString(i), Link.class);
            linkRepository.insert(link);
        }
    }

    @Override
    public void deleteLink(String link) {
        linkRepository.deleteById(link);
    }
}
