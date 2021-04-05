package com.cietrzew.wp.api;

import java.util.Arrays;

public class Weather {
    WeatherCoord coord;
    WeatherWeather[] weather;
    String base;
    WeatherMain main;
    long visibility;
    WeatherWind wind;
    WeatherClouds clouds;
    WeatherSys sys;
    long timezone;
    long dt;
    int cod;
    long id;
    String name;
    WeatherRain rain;
    WeatherSnow snow;
    
	public WeatherCoord getCoord() {
		return coord;
	}
	public void setCoord(WeatherCoord coord) {
		this.coord = coord;
	}
	public WeatherWeather[] getWeather() {
		return weather;
	}
	public void setWeather(WeatherWeather[] weather) {
		this.weather = weather;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public WeatherMain getMain() {
		return main;
	}
	public void setMain(WeatherMain main) {
		this.main = main;
	}
	public long getVisibility() {
		return visibility;
	}
	public void setVisibility(long visibility) {
		this.visibility = visibility;
	}
	public WeatherWind getWind() {
		return wind;
	}
	public void setWind(WeatherWind wind) {
		this.wind = wind;
	}
	public WeatherClouds getClouds() {
		return clouds;
	}
	public void setClouds(WeatherClouds clouds) {
		this.clouds = clouds;
	}
	public WeatherSys getSys() {
		return sys;
	}
	public void setSys(WeatherSys sys) {
		this.sys = sys;
	}
	public long getTimezone() {
		return timezone;
	}
	public void setTimezone(long timezone) {
		this.timezone = timezone;
	}
	public long getDt() {
		return dt;
	}
	public void setDt(long dt) {
		this.dt = dt;
	}
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public WeatherRain getRain() {
		return rain;
	}
	public void setRain(WeatherRain rain) {
		this.rain = rain;
	}
	public WeatherSnow getSnow() {
		return snow;
	}
	public void setSnow(WeatherSnow snow) {
		this.snow = snow;
	}
	
	@Override
	public String toString() {
		return "Weather [coord=" + coord + ", weather=" + Arrays.toString(weather) + ", base=" + base + ", main=" + main
				+ ", visibility=" + visibility + ", wind=" + wind + ", clouds=" + clouds + ", sys=" + sys
				+ ", timezone=" + timezone + ", dt=" + dt + ", cod=" + cod + ", id=" + id + ", name=" + name + ", rain="
				+ rain + ", snow=" + snow + "]";
	}

}
