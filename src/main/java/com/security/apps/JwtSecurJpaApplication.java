package com.security.apps;

import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.security.apps.config.RsaKeysConfig;
import com.security.apps.model.Role;
import com.security.apps.service.AccountService;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeysConfig.class)
public class JwtSecurJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtSecurJpaApplication.class, args);
	}
	
	@Bean
    CommandLineRunner createUsers(AccountService accountService){
        return args->{
            accountService.save(new Role(null,"USER"));
            accountService.save(new Role(null,"ADMIN"));
            Stream.of("user1","user2","user3","admin").forEach(un->{
                accountService.saveUser(un,"1234","1234");
            });
        };
    }
	
	@Bean
    BCryptPasswordEncoder getBCPE(){
        return new BCryptPasswordEncoder();
    }
	
}
