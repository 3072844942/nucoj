package com.q7g.nucoj_spring.repository;

import com.q7g.nucoj_spring.enums.UserRoleEnum;
import com.q7g.nucoj_spring.po.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, UserRoleEnum> {
}
