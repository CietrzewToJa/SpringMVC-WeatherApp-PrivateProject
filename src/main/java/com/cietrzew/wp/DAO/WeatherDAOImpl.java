package com.cietrzew.wp.DAO;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cietrzew.wp.api.Weather;
import com.cietrzew.wp.rowmapper.WeatherRowMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class WeatherDAOImpl implements WeatherDAO {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Weather loadWeatherFromAPI(String location) {

		URL url = null;
		Weather weather = null;
		try {
			url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + location + "&units=metric&appid=09a5062ab95e415e71d251e55884c81f");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			weather = objectMapper.readValue(url, Weather.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Loaded");
		
		return weather;
	}

	@Override
	public List<Weather> loadWeather() {
		
		String sql = "SELECT * FROM weather";
		
		List<Weather> theListOfWeather = jdbcTemplate.query(sql, new WeatherRowMapper());
		
		return theListOfWeather;
	}

}
