package com.cietrzew.wp.service;

import com.cietrzew.wp.DAO.WeatherDAO;
import com.cietrzew.wp.api.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {
	
	private WeatherDAO weatherDAO;
	private DateService dateService;

	@Autowired
	public void WeatherServiceImpl(DateService dateService, WeatherDAO weatherDAO) {
		this.weatherDAO = weatherDAO;
		this.dateService = dateService;
	}

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
