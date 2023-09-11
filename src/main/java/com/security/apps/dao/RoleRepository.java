package com.security.apps.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.security.apps.model.Role;

@RepositoryRestResource
public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByRoleName(String rolename);
}
