package com.q7g.nucoj_spring.repository;

import com.q7g.nucoj_spring.po.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface NoticeRepository extends MongoRepository<Notice, String> {
    /**
     * 获取满足条件的公告对象
     * 标题/作者/内容 包含 条件
     * @param keywords 条件
     * @return
     */
    @Query("{$or: [{'context': {$regex: ?0}}, {'author': {$regex: ?0}}, {'title': {$regex: ?0}}]}")
    Page<Notice> findAllByContextOrAuthorOrTitle(String keywords, Pageable pageable);
}
