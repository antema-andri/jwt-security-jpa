package com.security.apps.dto;

import lombok.Data;

@Data
public class CountryDto extends BaseDto{
	private Long id;
	private String name;
	private String code;
}
