package com.security.apps.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home(Principal principal) {
        return "Hello, " + principal.getName();
    }
    
	@GetMapping("/customerlist")
	public List<String> customerList() {
		List<String> customers = Arrays.asList(
	    	"english",
	    	"american",
	    	"french"
	    );
		return customers;
	}

}
