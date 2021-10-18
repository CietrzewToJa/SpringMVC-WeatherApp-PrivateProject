package com.cietrzew.wp.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cietrzew.wp.DAO.WeatherDAO;
import com.cietrzew.wp.api.Weather;

@Service
public class WeatherServiceImpl implements WeatherService {
	
	@Autowired
	private WeatherDAO weatherDAO;

	@Override
	public Weather loadWeatherFromAPI(String location) {
		
		Weather weather = weatherDAO.loadWeatherFromAPI(location);

		if(weather == null) {
			return null;
		}
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		weather.setDate(formatter.format(date));
		
		return weather;
	}

	@Override
	public List<Weather> loadWeather(String location) {
		
		return weatherDAO.loadWeather(location);
	}

	@Override
	public void saveWeather(Weather weather) {
		
		weatherDAO.saveWeather(weather);
	}

}
