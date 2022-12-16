package com.q7g.nucoj_spring.repository;

import com.q7g.nucoj_spring.po.Contest;
import com.q7g.nucoj_spring.po.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface LogRepository extends MongoRepository<Log, String> {

    @Query(value = "{}", sort = "{'createTime': -1}")
    Page<Log> findAll(Pageable pageable);
}
