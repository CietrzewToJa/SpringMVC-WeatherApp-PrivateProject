package com.cietrzew.wp.api;

public class WeatherWind {
    float speed;
    int deg;
    float gust;

    public float getGust() {
		return gust;
	}

	public void setGust(float gust) {
		this.gust = gust;
	}

	public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    @Override
    public String toString() {
        return "Wind{" +
                "speed=" + speed +
                ", deg=" + deg +
                '}';
    }

}
