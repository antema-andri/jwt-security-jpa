/**
 * 
 */
package com.security.apps.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.security.apps.model.User;

/**
 * 
 */
@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
}
