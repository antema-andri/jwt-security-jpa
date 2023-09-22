package com.security.apps.service;

import java.io.IOException;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.security.apps.dao.CountryRepository;
import com.security.apps.dao.RoleRepository;
import com.security.apps.dao.UserRepository;
import com.security.apps.dto.CountryDto;
import com.security.apps.dto.RoleDto;
import com.security.apps.dto.UserDto;
import com.security.apps.enums.UserRole;
import com.security.apps.model.Country;
import com.security.apps.model.Role;
import com.security.apps.model.User;
import com.security.apps.utils.UtilFileReader;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class InitDataService {
	private final RoleRepository roleRepository;
	private final UserRepository userRepository;
	private final CountryRepository countryRepository;
	
	public void loadDatas() {
		this.loadRoles();
		this.loadUsers();
		this.loadCountries();
	}
	
	public void loadRoles() {
		try {
			List<RoleDto> roles=UtilFileReader.readJsonArray("static/jsondata/roles.json", RoleDto.class);
			roles.forEach(r->{
				Role role=new Role(null, r.getName());
				roleRepository.save(role);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadUsers() {
		try {
			List<UserDto> users=UtilFileReader.readJsonArray("static/jsondata/users.json", UserDto.class);
			users.forEach(u->{
				User user=new User(null, u.getUsername(), new BCryptPasswordEncoder().encode(u.getPassword()), true);
				if(user.getUsername().startsWith("user")) {
					user.getRoles().add(roleRepository.findByRoleName(UserRole.USER.toString()));
				}else {
					user.getRoles().add(roleRepository.findByRoleName(UserRole.ADMIN.toString()));
				}
				userRepository.save(user);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
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
