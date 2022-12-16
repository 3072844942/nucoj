package com.q7g.nucoj_spring.service.impl;

import com.q7g.nucoj_spring.dto.PageDTO;
import com.q7g.nucoj_spring.dto.NoticeDTO;
import com.q7g.nucoj_spring.exceotion.BizException;
import com.q7g.nucoj_spring.po.Notice;
import com.q7g.nucoj_spring.repository.NoticeRepository;
import com.q7g.nucoj_spring.repository.UserRepository;
import com.q7g.nucoj_spring.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;

    @Override
    public List<NoticeDTO> getRecentNotice(int size) {
        List<Notice> list = noticeRepository.findAll(Sort.by(Sort.Direction.DESC, "time"));
        List<NoticeDTO> res = new LinkedList<>();
        for (Notice i : list) {
            if (res.size() >= size) break;
            res.add(NoticeDTO.of(i, false));
        }
        return res;
    }

    @Override
    public NoticeDTO getNotice(String noticeId) {
        Optional<Notice> byId = noticeRepository.findById(noticeId);
        if (!byId.isPresent()){
            throw new BizException("到达知识的边界");
        }
        return NoticeDTO.of(byId.get(), true);
    }

    private void insertNotice(String title, Long time, String context, String author) {
        // 找出没有使用过的ID
        long count = noticeRepository.count() + 1;
        while (noticeRepository.findById(String.valueOf(count)).isPresent())
            count++;
        // 转换对象
        String id = String.valueOf(count);
        Notice notice = Notice.builder()
                .id(id)
                .title(title)
                .time(time)
                .context(context)
                .author(author)
                .build();
        noticeRepository.insert(notice);
    }

    @Override
    public void updateNotice(String id, String title, Long time, String context, String author) {
        // 如果已经存在当前公告
        if (Objects.isNull(id)) insertNotice(title, time, context, author);
        else {
            Notice notice = Notice.builder()
                    .id(id)
                    .title(title)
                    .time(time)
                    .context(context)
                    .author(author)
                    .build();
            noticeRepository.save(notice);
        }
    }

    @Override
    public PageDTO<NoticeDTO> getNotices(int current, int size, String keywords) {
        // 分页器
        Pageable pageable = PageRequest.of(current - 1, size);
        // 查询
        Page<Notice> all = noticeRepository.findAllByContextOrAuthorOrTitle(keywords, pageable);
        return new PageDTO<>(
                all.getTotalElements(),
                all.stream().map(item -> NoticeDTO.of(item, false)).collect(Collectors.toList()));
    }

    @Override
    public void deleteNotice(String noticeId) {
        noticeRepository.deleteById(noticeId);
    }
}
