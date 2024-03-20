package MeteoPack.model;

import org.springframework.stereotype.Component;

@Component
public class WeatherData {

    // Имя города для погоды
    private String cityName;

    // Температура в городе
    private String temperature;

    // Влажность в городе
    private double humidity;

    // Скорость ветра в городе
    private double windSpeed;

    // Текстовое описание погоды
    private String weatherDescription;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }
}
