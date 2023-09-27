package com.security.apps.mapper;

import org.mapstruct.Mapper;

import com.security.apps.dto.CountryDto;
import com.security.apps.model.Country;

@Mapper(componentModel = "spring")
public interface CountryMapper {
	CountryDto fromEntity(Country country);
}
