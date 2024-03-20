package MeteoPack.controller;

import MeteoPack.model.WeatherData;
import MeteoPack.service.OpenWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {

    private final OpenWeatherService openWeatherService;

    @Autowired
    public WeatherController(OpenWeatherService openWeatherService) {
        this.openWeatherService = openWeatherService;
    }

    @RequestMapping("/meteo")
    public String showWeatherForm() {
        return "homepage";
    }

    @PostMapping("/meteo")
    public String getWeather(@RequestParam String city, Model model){
        WeatherData weatherData = openWeatherService.getWeatherData(city);
        model.addAttribute("weatherData", weatherData);
        return "homepage";
    }
}
