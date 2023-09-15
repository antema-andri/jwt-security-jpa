package com.security.apps.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.security.apps.model.Country;

@RepositoryRestResource
public interface CountryRepository extends JpaRepository<Country, Long>{

}
