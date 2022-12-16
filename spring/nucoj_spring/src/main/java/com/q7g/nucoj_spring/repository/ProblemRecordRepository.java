package com.q7g.nucoj_spring.repository;

import com.q7g.nucoj_spring.po.ProblemRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProblemRecordRepository extends MongoRepository<ProblemRecord, String> {
}
