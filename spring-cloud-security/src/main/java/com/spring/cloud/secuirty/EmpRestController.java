package com.spring.cloud.secuirty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableOAuth2Sso
public class EmpRestController extends WebSecurityConfigurerAdapter{
	
	Map<Integer, Employee> empMap = new HashMap<>();
	
	@Autowired
	private OAuth2ClientContext clientContext;
	
	@RequestMapping(value="/emp", method=RequestMethod.GET)
	public Collection<Employee> getEmployess(){
		if (ObjectUtils.isEmpty(empMap)){
			empMap.put(123, new Employee(123, "ram", 25, "ap"));
			empMap.put(122, new Employee(122, "sita", 27, "tn"));
			empMap.put(124, new Employee(124, "raju", 38, "kl"));
		}
		return empMap.values();
	}
	
	@RequestMapping(value="/emp/{empid}", method=RequestMethod.GET)
	public Collection<Employee> saveEmployee(@PathVariable Integer empid){
		empMap.put(empid, new Employee(empid, "bala", 45, "tn"));
		return empMap.values();
	}
	
	@RequestMapping("/acc_token")
	public String getToken(){
		String token = clientContext.getAccessToken().getValue();
		System.out.println ("access_token : "+token);
		return token;
	}
	
	public void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
		.antMatchers("/","/emp")
		.permitAll()
		.anyRequest()
		.authenticated();
	}
}
