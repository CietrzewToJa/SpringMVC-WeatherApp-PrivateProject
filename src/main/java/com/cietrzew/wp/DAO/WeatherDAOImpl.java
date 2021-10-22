package com.cietrzew.wp.DAO;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.cietrzew.wp.api.WeatherRain;
import com.cietrzew.wp.api.WeatherSnow;
import com.cietrzew.wp.service.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.cietrzew.wp.api.Weather;
import com.cietrzew.wp.rowmapper.WeatherRowMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class WeatherDAOImpl implements WeatherDAO {
	
	private JdbcTemplate jdbcTemplate;
	private WeatherRowMapper weatherRowMapper;

	@Autowired
	public void weatherDAOimpl(JdbcTemplate jdbcTemplate, WeatherRowMapper weatherRowMapper) {
		this.jdbcTemplate = jdbcTemplate;
		this.weatherRowMapper = weatherRowMapper;
	}

	@Value("${weather-api.key}")
	String apiKey;
	
	@Override
	public Weather loadWeatherFromAPI(String location) {

		URL url = null;
		Weather weather = null;
		try {
			url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + location + "&units=metric&appid=" + apiKey);
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
		}

		return weather;
	}

	@Override
	public List<Weather> loadWeather(String location) {
		
		String sql = "SELECT * FROM weather WHERE city='" + location + "'";
		
		List<Weather> theListOfWeather = jdbcTemplate.query(sql, weatherRowMapper);
		
		return theListOfWeather;
	}

	@Override
	public void saveWeather(Weather weather) {

		Object[] sqlParameters = { weather.getDate(),
				weather.getWeather()[0].getDescription(), 
				weather.getMain().getTemp(), 
				weather.getMain().getFeels_like(), 
				weather.getMain().getPressure(), 
				weather.getMain().getHumidity(), 
				weather.getWind().getSpeed(), 
				weather.getRain().getOneHour(), 
				weather.getSnow().getOneHour(), 
				weather.getCity()
		};

		String sql = "SELECT date FROM weather WHERE date='" + weather.getDate() + "'";

		List<String> dateList = jdbcTemplate.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet resultSet, int i) throws SQLException {
				return resultSet.getString(1);
			}
		});

		if(dateList.isEmpty()) {
			sql = "INSERT INTO weather (date, sky, temp, feels_like, pressure, humidity, wind, rain, snow, city) VALUES(?,?,?,?,?,?,?,?,?,?)";

			int n = jdbcTemplate.update(sql, sqlParameters);
		}
		


	}

	@Override
	public void deleteWeather(String city, String date) {

		String sql = "DELETE FROM weather WHERE city=? AND date=?";

		int n = jdbcTemplate.update(sql, city, date);
	}

}
