package com.q7g.nucoj_spring.repository;

import com.q7g.nucoj_spring.po.Train;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrainRepository extends MongoRepository<Train, String> {
}
