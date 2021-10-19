package com.cietrzew.wp.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.cietrzew.wp.api.WeatherRain;
import com.cietrzew.wp.api.WeatherSnow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cietrzew.wp.DAO.WeatherDAO;
import com.cietrzew.wp.api.Weather;

@Service
public class WeatherServiceImpl implements WeatherService {
	
	@Autowired
	private WeatherDAO weatherDAO;

	@Autowired
	private DateService dateService;

	@Override
	public Weather loadWeatherFromAPI(String location) {
		
		Weather weather = weatherDAO.loadWeatherFromAPI(location);

		if(weather == null) {
			return null;
		} else {
			weather.resetNullValues();
		}
		
		weather.setDate(dateService.getActualDate());
		
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

	@Override
	public void deleteWeather(String city, String date) {
		weatherDAO.deleteWeather(city, date);
	}


}
