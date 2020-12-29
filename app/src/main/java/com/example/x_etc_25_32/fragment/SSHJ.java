package com.example.x_etc_25_32.fragment;

import com.github.mikephil.charting.data.Entry;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2020/12/5 9:24
 */
public class SSHJ {

    /**
     *     "temperature": 31,
     *     "humidity": 0,
     *     "illumination": 1496,
     *     "co2": 582,
     *     "pm25": 80,
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
