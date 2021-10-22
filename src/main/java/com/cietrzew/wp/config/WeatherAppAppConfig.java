package com.cietrzew.wp.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "com.cietrzew.wp")
@PropertySource("classpath:application-dev.properties")
public class WeatherAppAppConfig implements WebMvcConfigurer {

	@Value("${database.user}")
	String dbUser;

	@Value("${database.password}")
	String dbPassword;

	@Value("${database.url}")
	String dbUrl;

	@Value("${database.driver.class.name}")
	String dbDriverClassName;

	@Bean
	InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {

		return new JdbcTemplate(dataSource());
	}
	
	@Bean
	public DataSource dataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setUsername(dbUser);
		dataSource.setPassword(dbPassword);
		dataSource.setUrl(dbUrl);

		dataSource.setDriverClassName(dbDriverClassName);

		return dataSource;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry
			.addResourceHandler("URLToReachResourcesFolder/**")
			.addResourceLocations("/resources/");
	}
}
