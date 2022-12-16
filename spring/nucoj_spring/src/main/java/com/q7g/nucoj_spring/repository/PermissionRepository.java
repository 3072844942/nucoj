package com.q7g.nucoj_spring.repository;

import com.q7g.nucoj_spring.po.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PermissionRepository extends MongoRepository<Permission, String> {
}
