package com.security.apps;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.security.apps.config.RsaKeysConfig;
import com.security.apps.service.InitDataService;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeysConfig.class)
public class JwtSecurJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtSecurJpaApplication.class, args);
	}
	
	@Bean
    CommandLineRunner loadDatas(InitDataService initDataService){
        return args->{
        	initDataService.loadDatas();
        };
    }
	
	@Bean
    BCryptPasswordEncoder getBCPE(){
        return new BCryptPasswordEncoder();
    }
	
}
