package com.security.apps.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.security.apps.dao.CountryRepository;
import com.security.apps.model.Country;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CountryService {
	private final CountryRepository countryRepository;
	
	public List<Country> getAllCoutries(){
		return countryRepository.findAll();
	}
}
