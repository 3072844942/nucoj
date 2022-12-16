package com.q7g.nucoj_spring.repository;

import com.q7g.nucoj_spring.po.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProblemRepository extends MongoRepository<Problem, String> {
    /**
     * 获取满足条件的题目对象
     * 标题/作者/内容 包含 条件
     * @param keywords 条件
     * @param tags 标签集
     * @return
     */
    @Query("{$or: [{'context': {$regex: ?0}}, {'author': {$regex: ?0}}, {'title': {$regex: ?0}}], 'grade':  {$in:  ?1},  'tags': {$in: ?1}}")
    Page<Problem> findAllByContextOrAuthorOrTitle(String keywords, List<String> tags, Pageable pageable);
}
