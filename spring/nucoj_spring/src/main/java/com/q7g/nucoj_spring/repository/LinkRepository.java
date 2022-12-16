package com.q7g.nucoj_spring.repository;

import com.q7g.nucoj_spring.po.Link;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LinkRepository extends MongoRepository<Link, String> {
}
