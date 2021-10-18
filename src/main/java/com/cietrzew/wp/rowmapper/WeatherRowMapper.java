package com.cietrzew.wp.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.cietrzew.wp.api.Weather;
import com.cietrzew.wp.api.WeatherMain;
import com.cietrzew.wp.api.WeatherRain;
import com.cietrzew.wp.api.WeatherSnow;
import com.cietrzew.wp.api.WeatherWeather;
import com.cietrzew.wp.api.WeatherWind;

public class WeatherRowMapper implements RowMapper<Weather> {

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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date d = null;
		try {
			d = sdf.parse(rs.getString("date"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		sdf.applyPattern("dd-MM-yyyy HH:mm:ss");

		weather.setDate(sdf.format(d));
		weather.setCity(rs.getString("city"));

		return weather;
	}

	
}
