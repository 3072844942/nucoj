package com.q7g.nucoj_spring.repository;

import com.q7g.nucoj_spring.po.Contest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ContestRepository extends MongoRepository<Contest, String> {
    /**
     * 获取满足条件的比赛对象
     * 标题/作者/内容 包含 条件
     * @param keywords 条件
     * @return
     */
    @Query("{$or: [{'context': {$regex: ?0}}, {'author': {$regex: ?0}}, {'title': {$regex: ?0}}]}")
    Page<Contest> findAllByContextOrAuthorOrTitle(String keywords, Pageable pageable);
}
