package com.example.x_etc_25_32.bean;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/4 10:18
 */
public class SHZS {

    /**
     *     "temperature": 5,
     *     "humidity": 20,
     *     "illumination": 552,
     *     "co2": 1723,
     *     "pm25": 3,
     */

    private int temperature,humidity,illumination,co2,pm25;

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getIllumination() {
        return illumination;
    }

    public void setIllumination(int illumination) {
        this.illumination = illumination;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public int getPm25() {
        return pm25;
    }

    public void setPm25(int pm25) {
        this.pm25 = pm25;
    }
}
