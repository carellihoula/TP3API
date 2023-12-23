package com.example.ntsoumoucarel.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Forecast {

    @JsonProperty("tmin")
    private double tmin;
    @JsonProperty("tmax")
    private double tmax;
    @JsonProperty("weather")
    private double weather;
    private double wind10m;

    //Methode pour calculer la température ressentie
    public static double calculateTemperature(double tmin,double tmax, double wind10m) {
        double temperature = (tmin+tmax) / 2;
        double feelsLikeTemperature = temperature - 0.1 * wind10m;
        return (int)(Math.round(feelsLikeTemperature * 10.0) / 10.0);
    }
}

