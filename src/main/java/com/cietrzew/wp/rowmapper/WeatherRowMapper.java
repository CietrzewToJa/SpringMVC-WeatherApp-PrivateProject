package com.cietrzew.wp.rowmapper;

import com.cietrzew.wp.api.*;
import com.cietrzew.wp.service.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class WeatherRowMapper implements RowMapper<Weather> {

    private DateService dateService;

    @Autowired
    public void WeatherRowMapper(DateService dateService) {
        this.dateService = dateService;
    }

    @Override
    public Weather mapRow(ResultSet rs, int rowNum) throws SQLException {

        Weather weather = new Weather();

        WeatherWeather weatherWeather = new WeatherWeather();
        weatherWeather.setDescription(rs.getString("sky"));
        WeatherWeather[] weatherArray = {weatherWeather};
        weather.setWeather(weatherArray);

        WeatherMain weatherMain = new WeatherMain();
        weatherMain.setTemp(rs.getFloat("temp"));
        weatherMain.setFeels_like(rs.getFloat("feels_like"));
        weatherMain.setPressure(rs.getFloat("pressure"));
        weatherMain.setHumidity(rs.getFloat("humidity"));
        weather.setMain(weatherMain);

        WeatherWind weatherWind = new WeatherWind();
        weatherWind.setSpeed(rs.getFloat("wind"));
        weather.setWind(weatherWind);

        WeatherRain weatherRain = new WeatherRain();
        weatherRain.setOneHour(rs.getFloat("rain"));
        weather.setRain(weatherRain);

        WeatherSnow weatherSnow = new WeatherSnow();
        weatherSnow.setOneHour(rs.getFloat("snow"));
        weather.setSnow(weatherSnow);

        weather.setDate(dateService.parseDate(rs.getString("date")));
        weather.setCity(rs.getString("city"));

        return weather;
    }


}
