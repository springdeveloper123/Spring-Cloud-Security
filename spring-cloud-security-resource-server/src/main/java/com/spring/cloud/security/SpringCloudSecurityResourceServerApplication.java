package com.spring.cloud.security;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableResourceServer
@RestController
public class SpringCloudSecurityResourceServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudSecurityResourceServerApplication.class, args);
	}
	
	Map<Integer, Account> accMap = new HashMap<>();
	
	@RequestMapping("/account")
	private Collection<Account> getAccountDetails(){
		if(ObjectUtils.isEmpty(accMap)){
			accMap.put(123456, new Account(123456, "saving", "ramu", "SBI"));
			accMap.put(123457, new Account(123457, "saving", "sita", "ICICI"));
			accMap.put(123458, new Account(123458, "current", "ganesh", "HDFC"));
		}
		
		return accMap.values();
	}
}
