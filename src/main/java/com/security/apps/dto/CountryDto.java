package com.security.apps.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class CountryDto extends BaseDto{
	private Long id;
	private String name;
	private String code;
}
