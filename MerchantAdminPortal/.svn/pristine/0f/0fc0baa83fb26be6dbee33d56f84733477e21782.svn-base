package com.npst.upi.portal.merchant;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@PropertySource(value = { "file:config/application.properties", "file:config/config.properties"})
public class MerchantAdminPortalApplication extends SpringBootServletInitializer  {
	
	public static void main(String[] args) {
		SpringApplication.run(MerchantAdminPortalApplication.class, args);
	}	
	   @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	        return builder.sources(MerchantAdminPortalApplication.class);
	    }
}

