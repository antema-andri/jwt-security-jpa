package com.security.apps.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.apps.service.AccountService;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.security.apps.model.User appUser=accountService.loadUserByUsername(username);
        if(appUser==null) throw new UsernameNotFoundException("INVALID USER");
        Collection<GrantedAuthority> authorities=new ArrayList<>();
        appUser.getRoles().forEach(r->{
            authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
        });
        UserDetails userDetails=User
        		.withUsername(appUser.getUsername())
        		.password(appUser.getPassword())
        		.authorities(authorities)
        		.build();
        return userDetails;
    }
}
