package com.q7g.nucoj_spring.repository;

import com.q7g.nucoj_spring.po.NUCOJ;
import com.q7g.nucoj_spring.po.Notice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface NUCOJRepository extends MongoRepository<NUCOJ, String> {
    NUCOJ findFirstByOrderByTimeDesc();
}
