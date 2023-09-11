package com.security.apps.service;

import com.security.apps.model.Role;
import com.security.apps.model.User;

public interface AccountService {
	public User saveUser(String username,String password,String confirmedPassword);
    public Role save(Role role);
    public User loadUserByUsername(String username);
    public void addRoleToUser(String username,String rolename);
}
