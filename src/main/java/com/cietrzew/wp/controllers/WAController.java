package com.cietrzew.wp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cietrzew.wp.DAO.WeatherDAO;
import com.cietrzew.wp.api.RequestInfo;
import com.cietrzew.wp.api.Weather;

@Controller
public class WAController {
	
	@Autowired
	private WeatherDAO WeatherDAO;

	@RequestMapping("/")
	public String showHomePage(Model model) {
		
		model.addAttribute("requestInfo", new RequestInfo());
		
		return "home-page";
	}
	
	@RequestMapping("/show-weather")
	public String showWeather(RequestInfo requestInfo, Model model) {
		
		Weather weather = WeatherDAO.loadWeatherFromAPI(requestInfo.getCityName());
		model.addAttribute("weather", weather);			
		
		WeatherDAO.loadWeather();
		
		return "show-weather";
	}
}
