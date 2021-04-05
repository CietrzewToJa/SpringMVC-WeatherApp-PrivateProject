package com.cietrzew.wp.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherRain {
    @JsonProperty("1h")
    float oneHour;
    @JsonProperty("3h")
    float threeHours;

    public float getOneHour() {
        return oneHour;
    }

    public void setOneHour(float oneHour) {
        this.oneHour = oneHour;
    }

    public float getThreeHours() {
        return threeHours;
    }

    public void setThreeHours(float threeHours) {
        this.threeHours = threeHours;
    }

    @Override
    public String toString() {
        return "Rain{" +
                "1h=" + oneHour +
                ", 3h=" + threeHours +
                '}';
    }

}
