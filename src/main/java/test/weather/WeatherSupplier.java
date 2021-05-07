package test.weather;

import test.weather.model.Units;
import test.weather.model.Weather;

public interface WeatherSupplier {
	public Weather getWeather(String city, Units units) throws Exception;
}
