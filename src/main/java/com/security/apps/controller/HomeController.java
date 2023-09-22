package com.security.apps.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.apps.dto.CountryDto;
import com.security.apps.service.CountryService;
import lombok.AllArgsConstructor;

import java.util.List;

@RestController
@AllArgsConstructor
public class HomeController {
	private final CountryService countryService;

	@GetMapping("/countrylist")
	public List<CountryDto> countryList() {
		return countryService.getCountryList();
	}
	
}
