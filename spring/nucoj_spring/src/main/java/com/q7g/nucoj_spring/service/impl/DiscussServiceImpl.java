package com.q7g.nucoj_spring.service.impl;

import com.q7g.nucoj_spring.dto.DiscussDTO;
import com.q7g.nucoj_spring.dto.PageDTO;
import com.q7g.nucoj_spring.exceotion.BizException;
import com.q7g.nucoj_spring.po.Discuss;
import com.q7g.nucoj_spring.repository.DiscussRepository;
import com.q7g.nucoj_spring.repository.UserRepository;
import com.q7g.nucoj_spring.service.DiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DiscussServiceImpl implements DiscussService {
    @Autowired
    private DiscussRepository discussRepository;


    @Override
    public PageDTO<DiscussDTO> getDiscusses(int current, int size, String keywords, String userId) {
        // 分页器
        Pageable pageable = PageRequest.of(current - 1, size);
        // 创建样例
        Page<Discuss> all = discussRepository.findAllByContextOrAuthorOrTitle(keywords, pageable);
        // 转换为数据传输对象
        return (PageDTO<DiscussDTO>) PageDTO.of(
                all.getTotalElements(),
                all.stream().map(item -> DiscussDTO.of(item, false)).collect(Collectors.toList())
        );
    }

    @Override
    public DiscussDTO getDiscuss(String discussId) {
        Optional<Discuss> byId = discussRepository.findById(discussId);
        // 如果不存在
        if (!byId.isPresent()){
            throw new BizException("到达知识的边界");
        }
        // 转换
        return DiscussDTO.of(byId.get(), true);
    }

    private void insertDiscuss(String title, Long time, String context, String author) {
        // 找到第一个没有使用的Id
        long count = discussRepository.count() + 1;
        while (discussRepository.findById(String.valueOf(count)).isPresent())
            count++;
        String id = String.valueOf(count);
        Discuss discuss = Discuss.builder()
                .id(id)
                .title(title)
                .time(time)
                .context(context)
                .author(author)
                .build();
        discussRepository.insert(discuss);
    }

    @Override
    public void updateDiscuss(String id, String title, Long time, String context, String author) {
        // 如果存在当前分享
        if (Objects.isNull(id)) insertDiscuss(title, time, context, author);
        else {
            Discuss discuss = Discuss.builder()
                    .id(id)
                    .title(title)
                    .time(time)
                    .context(context)
                    .author(author)
                    .build();
            discussRepository.save(discuss);
        }
    }

    @Override
    public void deleteDiscuss(String discussId) {
        discussRepository.deleteById(discussId);
    }
}
