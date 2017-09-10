package com.spring.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

@SpringBootApplication
public class SpringCloudSecurityBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudSecurityBatchApplication.class, args);
	}
	
	@Bean
	public SecurityBatch batch(){
		return new SecurityBatch();
	}
	
	class SecurityBatch implements CommandLineRunner{

		@Override
		public void run(String... arg0) throws Exception {
			ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
			resource.setAccessTokenUri("http://localhost:9000/service/oauth/token");
			resource.setClientId("springdeveloper123");
			resource.setClientSecret("test");
			resource.setGrantType("password");
			resource.setUsername("allen");
			resource.setPassword("pass");
			resource.setClientAuthenticationScheme(AuthenticationScheme.header);
			OAuth2RestTemplate tempalte = new OAuth2RestTemplate(resource);
			String results = tempalte.getForObject("http://localhost:8000/resource/account", String.class);
			System.out.println("results : "+ results);
		}
		
	}
}
