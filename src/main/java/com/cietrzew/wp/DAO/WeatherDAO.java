package com.cietrzew.wp.DAO;

import java.util.List;

import com.cietrzew.wp.api.Weather;

public interface WeatherDAO {

	Weather loadWeatherFromAPI(String location);
	List<Weather> loadWeather(String location);
	void saveWeather(Weather weather);
	void deleteWeather(String city, String date);
}
