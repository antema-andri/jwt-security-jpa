package com.security.apps.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.security.apps.dao.CountryRepository;
import com.security.apps.dto.CountryDto;
import com.security.apps.mapper.CountryMapper;
import com.security.apps.model.Country;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CountryService {
	private final CountryRepository countryRepository;
	private final CountryMapper countryMapper;
	
	public Country getById(Long countryId){
		return countryRepository.findById(countryId).orElse(null);
	}
	
	public List<CountryDto> getCountryList(){
		List<Country> countries=countryRepository.findAll();
		return countries.stream().map(co->countryMapper.fromEntity(co)).collect(Collectors.toList());
	}
}
