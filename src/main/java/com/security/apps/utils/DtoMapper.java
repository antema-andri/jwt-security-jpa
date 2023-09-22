package com.security.apps.utils;

import org.springframework.beans.BeanUtils;

import com.security.apps.dto.CountryDto;
import com.security.apps.model.Country;

public class DtoMapper {
	public static CountryDto fromEntity(Country country) {
		CountryDto countryDto=new CountryDto();
		BeanUtils.copyProperties(country, countryDto);
		return countryDto;
	}
	
	public static Country fromDto(CountryDto countryDto) {
		Country country=new Country();
		BeanUtils.copyProperties(countryDto, country);
		return country;
	}
}
