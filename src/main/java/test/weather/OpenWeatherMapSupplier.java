package test.weather;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;

import org.json.JSONException;
import org.json.JSONObject;

import test.weather.exception.WeatherException;
import test.weather.model.Units;
import test.weather.model.Weather;

public class OpenWeatherMapSupplier implements WeatherSupplier {
	private String appid;
	private OpenWeatherMapParser parser;
	private String lang;

	public OpenWeatherMapSupplier(String appid, String lang, OpenWeatherMapParser parser) {
		this.appid = appid;
		this.parser = parser;
		this.lang = lang;
	}

	@Override
	public Weather getWeather(String city, Units units) throws Exception {		
		String json = getBody(city, units);
		Weather weather = parseModel(json, units);
		
		return weather;
	}

	private String getBody(String city, Units units) throws Exception {
		final String URI = "https://api.openweathermap.org/data/2.5/weather?"
							+ "q=" + city
							+ "&appid=" + appid
							+ "&units=" + units.toString().toLowerCase()
							+ "&lang=" + lang;
		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
												.GET()
												.uri(new URI(URI))
												.header("Accept", "application/json")
											 .build();

			BodyHandler<String> handler = BodyHandlers.ofString(StandardCharsets.UTF_8);
			HttpResponse<String> response = client.send(request, handler);
			
			return checkResponseAndGetBody(response);
			
		} catch(URISyntaxException e) {
			throw new WeatherException("invalid URI");
		} catch(InterruptedException e ) {
			throw new WeatherException("connection lost");
		}
	}
	
	private String checkResponseAndGetBody(HttpResponse<String> response)
														   throws JSONException,
																  WeatherException {
		String body = response.body();
		
		if(response.statusCode() >= 400) {
			JSONObject json = new JSONObject(body);
			throw new WeatherException(json.getString("message"));
		}
		
		return response.body();
	}

	private Weather parseModel(String body, Units units) throws WeatherException {
		return this.parser.parse(body, units);
	}
}
