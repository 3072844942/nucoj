package com.q7g.nucoj_spring.repository;

import com.q7g.nucoj_spring.po.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
}
