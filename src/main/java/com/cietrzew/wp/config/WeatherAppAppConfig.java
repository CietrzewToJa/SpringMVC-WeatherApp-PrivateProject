package com.cietrzew.wp.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.net.URI;
import java.net.URISyntaxException;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.cietrzew.wp")
public class WeatherAppAppConfig implements WebMvcConfigurer {
	
	@Bean
	InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
	
		return jdbcTemplate;
	}
	
	@Bean
	public DataSource dataSource() {

		URI dbUri = null;
		try {
			dbUri = new URI(System.getenv("DATABASE_URL"));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";

		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setUrl(dbUrl);

//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//
//		dataSource.setUsername("root");
//		dataSource.setPassword("root");
//		dataSource.setUrl("jdbc:mysql://localhost:3306/weather?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC");
//		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

		return dataSource;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry
			.addResourceHandler("URLToReachResourcesFolder/**")
			.addResourceLocations("/resources/");
		
	}
}
