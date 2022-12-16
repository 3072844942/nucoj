package com.q7g.nucoj_spring.service.impl;

import com.q7g.nucoj_spring.po.Problem;
import com.q7g.nucoj_spring.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<String> getTags() {
        Query query = new Query();
        return mongoTemplate.findDistinct(query, "tags", Problem.class, String.class);
    }

    @Override
    public List<String> getGrades() {
        Query query = new Query();
        return mongoTemplate.findDistinct(query, "grade", Problem.class, String.class);
    }
}
