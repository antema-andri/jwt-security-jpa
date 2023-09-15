package com.security.apps.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.security.apps.dao.CountryRepository;
import com.security.apps.dto.CountryDto;
import com.security.apps.model.Country;
import com.security.apps.utils.UtilFileReader;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InitDataService {
	private final CountryRepository countryRepository;
	
	public void loadCountries(){
		try {
			List<CountryDto> countries=UtilFileReader.readJsonArray("static/jsondata/countries.json", CountryDto.class);
			countries.forEach(c->{
				Country country=new Country(null, c.getName(), c.getCode());
				countryRepository.save(country);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
