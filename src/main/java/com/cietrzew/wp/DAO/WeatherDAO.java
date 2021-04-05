package com.cietrzew.wp.DAO;

import java.util.List;

import com.cietrzew.wp.api.Weather;

public interface WeatherDAO {

	Weather loadWeatherFromAPI(String location);
	List<Weather> loadWeather();
}
