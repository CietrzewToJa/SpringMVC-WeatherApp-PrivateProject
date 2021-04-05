package com.cietrzew.wp.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cietrzew.wp.api.Weather;
import com.cietrzew.wp.api.WeatherMain;

public class WeatherRowMapper implements RowMapper<Weather> {

	@Override
	public Weather mapRow(ResultSet rs, int rowNum) throws SQLException {

		Weather weather = new Weather();
		WeatherMain weatherMain = new WeatherMain();
		weatherMain.setTemp(rs.getFloat("temp"));
		
		weather.setMain(weatherMain);
		
		
		System.out.println(weatherMain.getTemp());
				
		return weather;
	}

	
}
