package com.cietrzew.wp.controllers;

import com.cietrzew.wp.api.Weather;
import com.cietrzew.wp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class WAController {

    private WeatherService weatherService;

    @Autowired
    public void WAController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/")
    public ModelAndView showHomePage(Model model) {

        return new ModelAndView("home-page", "weather", new Weather());
    }

    @RequestMapping(value = "/show-weather", method = {RequestMethod.POST, RequestMethod.GET})
    public String showWeather(@ModelAttribute("weather") Weather weather, Model model, RedirectAttributes redirectAttributes) {

        String city = weather.getCity();
        weather = weatherService.loadWeatherFromAPI(city);

        if (weather == null) {

            return "redirect:/";
        }

        weather.setCity(city);

        List<Weather> weatherList = weatherService.loadWeather(weather.getCity());
        model.addAttribute("weatherList", weatherList);
        model.addAttribute("weather", weather);


        return "show-weather";
    }

    @PostMapping("/add-weather")
    public ModelAndView addStudent(
            Weather weather,
            RedirectAttributes redirectAttributes) {

        weatherService.saveWeather(weather);

        redirectAttributes.addFlashAttribute("weather", weather);

        return new ModelAndView("redirect:/show-weather#basse-nav");
    }

    @GetMapping("/delete-weather")
    public ModelAndView deleteWeather(@RequestParam("city") String city, @RequestParam("date") String date, RedirectAttributes redirectAttributes) {

        weatherService.deleteWeather(city, date);

        Weather weather = new Weather();
        weather.setCity(city);

        redirectAttributes.addFlashAttribute(weather);

        return new ModelAndView("redirect:/show-weather");
    }
}
