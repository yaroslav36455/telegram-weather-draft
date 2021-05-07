package test.weather;

import test.weather.exception.WeatherException;
import test.weather.model.Units;
import test.weather.model.Weather;

public interface WeatherParser {
	public Weather parse(String body, Units units) throws WeatherException;
}
