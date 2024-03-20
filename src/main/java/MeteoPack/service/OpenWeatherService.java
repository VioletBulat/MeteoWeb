package MeteoPack.service;

import MeteoPack.model.WeatherData;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenWeatherService {

    @Value("${api.key}")
    private String apiKey;
    private final String API_URL = "https://api.openweathermap.org/data/2.5/weather?q=";
    // Внедрение Rest
    private final RestTemplate restTemplate;
    // Создание конструктора для внедрения Rest
    @Autowired
    public OpenWeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Метод создает URL запрос и получает данные от OpenWeatherAPI
    public WeatherData getWeatherData(String city) {
        String url = API_URL + city + "&appid=" + apiKey + "&units=metric";
        // Выполняет HTTP GET запрос к указанному URL с использованием RestTemplate
        // и ожидает получения ответа в виде строки JSON.
        String jsonResponse = restTemplate.getForObject(url, String.class);

        return parseWeatherData(jsonResponse);
    }
    // Преобразует данные из json в String для вывода пользователю
    // код выполняет разбор JSON-строки jsonResponse,
    // полученной из удаленного сервера, с использованием библиотеки org.json.JSONObject.
    // Он извлекает необходимые данные о погоде из этой JSON-строки и сохраняет их в объект WeatherData.
    private WeatherData parseWeatherData(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        WeatherData weatherData = new WeatherData();
        JSONObject main = jsonObject.getJSONObject("main");
        JSONObject wind = jsonObject.getJSONObject("wind");
        JSONArray weatherArray = jsonObject.getJSONArray("weather");
        // Получение названия города
        weatherData.setCityName(jsonObject.getString("name"));
        // Получение температуры города
        weatherData.setTemperature(String.format("%.1f", main.getDouble("temp" ))+ "°C");
        // Получение влажности воздуха
        weatherData.setHumidity(main.getDouble("humidity"));
        // Получение скорости ветра
        weatherData.setWindSpeed(wind.getDouble("speed"));
        // Получение описания погоды
        if (weatherArray.length() > 0) {
            JSONObject weatherObj = weatherArray.getJSONObject(0);
            weatherData.setWeatherDescription(weatherObj.getString("main"));
        }
        return weatherData;
    }
}
