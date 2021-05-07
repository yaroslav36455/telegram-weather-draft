package test.weather.telegram;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import test.weather.exception.WeatherException;

public class Bot {
	private String baseURI;
	
	public Bot(String token) {
		this.baseURI = "https://api.telegram.org/bot" + token + "/";
	}
	
	public void sendMessage(String channelUserName, String message) throws Exception {
		final String URI = baseURI + "sendMessage";
		
		try {
			JSONObject json = new JSONObject(Map.of("chat_id", channelUserName,
													"text",    message));
			
			HttpClient client = HttpClient.newHttpClient();
			BodyPublisher bodyPublisher = BodyPublishers.ofString(json.toString(),
																  StandardCharsets.UTF_8);
			HttpRequest request = HttpRequest.newBuilder()
												.POST(bodyPublisher)
												.uri(new URI(URI))
												.header("Content-Type", "application/json")
												.header("Accept", "application/json")
												.build();

			BodyHandler<String> handler = BodyHandlers.ofString(StandardCharsets.UTF_8);
			HttpResponse<String> response = client.send(request, handler);
			
			if (response.statusCode() >= 400) {
				String body = response.body();
				String errorMessage = getErrorMessage(body);
				
				throw new WeatherException(errorMessage);
			}
			
		} catch(URISyntaxException e) {
			throw new WeatherException("invalid URI");
		} catch(InterruptedException e ) {
			throw new WeatherException("connection lost");
		}
	}
	
	public List<Message> getMessages() throws Exception  {
		final String URI = baseURI + "getUpdates";
		try {			
			HttpClient client = HttpClient.newHttpClient();
			JSONObject requestBody = new JSONObject(Map.of("allowed_updates", new String[] {"message"}));
			BodyPublisher bodyPublisher = BodyPublishers.ofString(requestBody.toString(), StandardCharsets.UTF_8);
			HttpRequest request = HttpRequest.newBuilder()
												.POST(bodyPublisher)
												.uri(new URI(URI))
												.header("Accept", "application/json")
												.build();

			BodyHandler<String> handler = BodyHandlers.ofString(StandardCharsets.UTF_8);
			HttpResponse<String> response = client.send(request, handler);
			String body = response.body();
			
			if (response.statusCode() >= 400) {
				String errorMessage = getErrorMessage(body);
				
				throw new WeatherException(errorMessage);
			}
			
			return parseMessages(response.body());
			
		} catch(URISyntaxException e) {
			throw new WeatherException("invalid URI");
		} catch(InterruptedException e ) {
			throw new WeatherException("connection lost");
		}
	}
	
	public String getErrorMessage(String errorBody) throws WeatherException {
		try {
			JSONObject json = new JSONObject(errorBody);
			
			return json.getString("description");
		} catch(JSONException e) {
			throw new WeatherException("invalid json body");
		}
	}
	
	private List<Message> parseMessages(String body) throws WeatherException {
		List<Message> list = new ArrayList<>();
		
		try {
			JSONObject object = new JSONObject(body);
			JSONArray arr = object.getJSONArray("result");
			
			for(int i = 0; i < arr.length(); i++) {
				JSONObject jMessage = arr.getJSONObject(i)
									 	.getJSONObject("message");
				String userName = jMessage.getJSONObject("from")
			 			   			  	  .getString("username");
				String text = jMessage.getString("text");
				
				Message message = new Message();
				message.setUserName(userName);
				message.setMessage(text);
				list.add(message);
			}
			
			return list;
		} catch (JSONException e) {
			throw new WeatherException("invalid json body");
		}
	}
}
