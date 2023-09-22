package com.security.apps.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.security.apps.dao.CountryRepository;
import com.security.apps.dto.CountryDto;
import com.security.apps.model.Country;
import com.security.apps.utils.DtoMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CountryService {
	private final CountryRepository countryRepository;
	
	public List<Country> getAllCoutries(){
		return countryRepository.findAll();
	}
	
	public List<CountryDto> getCountryList(){
		List<Country> countries=countryRepository.findAll();
		List<CountryDto> countryDtos=countries.stream().map(co->DtoMapper.fromEntity(co)).collect(Collectors.toList());
		return countryDtos;
	}
}
