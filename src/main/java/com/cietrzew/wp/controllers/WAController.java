package com.cietrzew.wp.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cietrzew.wp.api.Weather;
import com.cietrzew.wp.service.WeatherService;

@Controller
public class WAController {

	@Autowired
	private WeatherService weatherService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView showHomePage(Model model) {

		return new ModelAndView("home-page", "weather", new Weather());
	}

	@RequestMapping(value = "/show-weather", method = { RequestMethod.POST, RequestMethod.GET })
	public String showWeather(@ModelAttribute("weather") Weather weather, Model model) {

		String city = weather.getCity();
		weather = weatherService.loadWeatherFromAPI(weather.getCity());

		if (weather == null) {
			System.out.println("NO!");

			return "redirect:/";
		}

		weather.setCity(city);

		List<Weather> weatherList = weatherService.loadWeather(weather.getCity());
		model.addAttribute("weatherList", weatherList);
		model.addAttribute("weather", weather);

		for(var we : weatherList) {
			System.out.println(we.getMain().getPressure());
		}

		return "show-weather";
	}

	@RequestMapping(value = "/add-weather", method = { RequestMethod.POST })
	public ModelAndView addStudent(
			Weather weather, 
			RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		
		request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
		
		weatherService.saveWeather(weather);

		redirectAttributes.addFlashAttribute("weather", weather);

		return new ModelAndView("redirect:/show-weather");
	}

}
