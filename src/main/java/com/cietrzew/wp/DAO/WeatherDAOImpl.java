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
import com.fasterxml.jackson.databind.DeserializationFeature;
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
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		try {
			weather = objectMapper.readValue(url, Weather.class);
		} catch (IOException e) {
			weather = null;
			return weather;
			// e.printStackTrace();
		}
				
		return weather;
	}

	@Override
	public List<Weather> loadWeather(String location) {
		
		String sql = "SELECT * FROM weather WHERE city='" + location + "'";
		
		List<Weather> theListOfWeather = jdbcTemplate.query(sql, new WeatherRowMapper());
		
		return theListOfWeather;
	}

	@Override
	public void saveWeather(Weather weather) {

		System.out.println(weather.getDate());
		
		Object[] sqlParameters = { weather.getDate(), 
				weather.getWeather()[0].getDescription(), 
				weather.getMain().getTemp(), 
				weather.getMain().getFeels_like(), 
				weather.getMain().getPressure(), 
				weather.getMain().getHumidity(), 
				weather.getWind().getSpeed(), 
				weather.getRain().getOneHour(), 
				weather.getSnow().getOneHour(), 
				weather.getCity() };
		
		String sql = "INSERT INTO weather (date, sky, temp, feels_like, pressure, humidity, wind, rain, snow, city) VALUES(?,?,?,?,?,?,?,?,?,?)";
		
		int n = jdbcTemplate.update(sql, sqlParameters);
		
		System.out.print(n + " ");
		
		System.out.println("Data Saved.");
	}

}
