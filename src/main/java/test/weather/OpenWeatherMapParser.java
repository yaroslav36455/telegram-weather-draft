package test.weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import test.weather.exception.WeatherException;
import test.weather.model.PrecipitationVolume;
import test.weather.model.Pressure;
import test.weather.model.Temperature;
import test.weather.model.Units;
import test.weather.model.Weather;
import test.weather.model.Wind;

public class OpenWeatherMapParser implements WeatherParser {

	@Override
	public Weather parse(String body, Units units) throws WeatherException {
		try {
			Weather weather = new Weather(units);
			JSONObject object = new JSONObject(body);
			
			weather.setCity(parseCity(object));
			weather.setClouds(parseClouds(object));
			weather.setDescription(parseDescription(object));
			weather.setHumidity(parseHumidity(object));
			weather.setPressure(parsePressure(object));
			weather.setTemperature(parseTemperature(object));
			weather.setRain(parseRainVolume(object));
			weather.setSnow(parseSnowVolume(object));
			weather.setVisibility(parseVisibility(object));
			weather.setWind(parseWind(object));
			
			return weather;
		} catch (JSONException e) {
			throw new WeatherException("invalid json");
		}
	}


	private String parseCity(JSONObject object) {
		String city = null;
		
		if(!object.isNull("name")) {
			city = object.getString("name");
		}
		
		return city;
	}
	
	private Integer parseClouds(JSONObject object) {
		Integer result = null;
		
		if(!object.isNull("clouds")) {
			JSONObject clouds = object.getJSONObject("clouds");
			
			if(!clouds.isNull("all")) {
				result = clouds.getInt("all");
			}
		}
		
		return result;
	}
	
	private String parseDescription(JSONObject object) {
		String result = null;
		
		if(!object.isNull("weather")) {
			JSONArray arr = object.getJSONArray("weather");
			
			if(!arr.isEmpty()) {
				JSONObject elem = arr.getJSONObject(0);
				
				if(!elem.isNull("description")) {
					result = elem.getString("description");
				}
			}
		}
		
		return result;
	}
	
	private Integer parseHumidity(JSONObject object) {
		Integer humidity = null;
		
		if(!object.isNull("main")) {
			JSONObject main = object.getJSONObject("main");
			
			if(!main.isNull("humidity")) {
				humidity = main.getInt("humidity");
			}
		}
		
		if(!object.isNull("humidity")) {
			humidity = object.getInt("humidity");
		}
		
		return humidity;
	}
	
	private Pressure parsePressure(JSONObject object) {
		Pressure pressure = null;
		
		if(!object.isNull("main")) {
			JSONObject main = object.getJSONObject("main");
			Pressure tmpPressure = new Pressure();
			
			tmpPressure.setPressure(main.isNull("pressure") ? null : main.getInt("pressure"));
			tmpPressure.setSeaLevel(main.isNull("sea_level") ? null : main.getInt("sea_level"));
			tmpPressure.setGroundLevel(main.isNull("grnd_level") ? null : main.getInt("grnd_level"));
			
			if(tmpPressure.getPressure().isPresent()
					|| tmpPressure.getSeaLevel().isPresent()
					|| tmpPressure.getGroundLevel().isPresent()) {
				pressure = tmpPressure;
			}
		}
		
		return pressure;
	}
	
	private Temperature parseTemperature(JSONObject object) {
		Temperature temperature = null;
		
		if(!object.isNull("main")) {
			JSONObject main = object.getJSONObject("main");
			Temperature tmpTemperature = new Temperature();
			
			tmpTemperature.setTemperature(main.isNull("temp") ? null : main.getFloat("temp"));
			tmpTemperature.setFeelsLike(main.isNull("feels_like") ? null : main.getFloat("feels_like"));
			tmpTemperature.setMin(main.isNull("min") ? null : main.getFloat("min"));
			tmpTemperature.setMax(main.isNull("max") ? null : main.getFloat("max"));
			
			if(tmpTemperature.getTemperature().isPresent()
					|| tmpTemperature.getFeelsLike().isPresent()
					|| tmpTemperature.getMin().isPresent()
					|| tmpTemperature.getMax().isPresent()) {
				temperature = tmpTemperature;
			}
		}
		
		return temperature;
	}
	
	private PrecipitationVolume parseRainVolume(JSONObject object) {
		return parsePrecipitation("rain", object);
	}
	
	private PrecipitationVolume parseSnowVolume(JSONObject object) {
		return parsePrecipitation("snow", object);
	}
	
	private PrecipitationVolume parsePrecipitation(String fieldName, JSONObject object) {
		PrecipitationVolume result = null;
		
		if(!object.isNull(fieldName)) {
			JSONObject field = object.getJSONObject(fieldName);
			
			if(!field.isEmpty()) {
				PrecipitationVolume volume = new PrecipitationVolume();
				volume.setOneHour(field.isNull("1h") ? null : field.getFloat("1h"));
				volume.setThreeHours(field.isNull("3h") ? null : field.getFloat("3h"));
				result = volume;
			}
		}
		
		return result;
	}
	
	private Integer parseVisibility(JSONObject object) {
		Integer visibility = null;
		
		if(!object.isNull("visibility")) {
			visibility = object.getInt("visibility");
		}
		
		return visibility;
	}
	
	private Wind parseWind(JSONObject object) {
		Wind result = null;
		
		if(!object.isNull("wind")) {
			JSONObject wind = object.getJSONObject("wind");
			
			if(!wind.isEmpty()) {
				Wind tmpWind = new Wind();
				
				tmpWind.setSpeed(wind.isNull("speed") ? null : wind.getFloat("speed"));
				tmpWind.setDegree(wind.isNull("deg") ? null : wind.getFloat("deg"));
				tmpWind.setGust(wind.isNull("gust") ? null : wind.getFloat("gust"));
				
				result = tmpWind;
			}
		}
		return result;
	}
}
