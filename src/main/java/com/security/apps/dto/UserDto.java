package com.security.apps.dto;

import lombok.Data;

@Data
public class UserDto extends BaseDto{
	private String username;
	private String password;
}
