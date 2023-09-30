package com.security.apps.mapper;

import org.mapstruct.Mapper;

import com.security.apps.dto.CountryDto;
import com.security.apps.dto.RoleDto;
import com.security.apps.dto.UserDto;
import com.security.apps.model.Country;
import com.security.apps.model.Role;
import com.security.apps.model.User;

@Mapper(componentModel = "spring")
public interface EntityMapper {
	UserDto fromEntity(User user);
	User fromDto(UserDto userDto);
	
	RoleDto fromEntity(Role role);
	Role fromDto(RoleDto roleDto);
	
	CountryDto fromEntity(Country country);
	Country fromDto(CountryDto countryDto);
}
