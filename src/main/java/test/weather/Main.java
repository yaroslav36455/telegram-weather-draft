package test.weather;

import java.io.IOException;
import java.util.Scanner;

import test.weather.config.Config;
import test.weather.config.ConfigLoader;
import test.weather.model.Weather;
import test.weather.telegram.Bot;

public class Main {
    public static void main(String[] args) {
    	ConfigLoader loader = new ConfigLoader();
    	
    	try {
    		Config config = loader.load();
			String city = cityRequest();
			
			method(city, config);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
    }
    
    private static String cityRequest() {
    	Scanner scanner = new Scanner(System.in);
    	String name = null;
    	
    	System.out.print("City:");
    	if(scanner.hasNext()) {
    		name = scanner.next();
    	}
    	System.out.println("Wait...");
    	
    	scanner.close();
    	return name;
    }
    
    private static void method(String city, Config config) {
		try {
	    	OpenWeatherMapParser parser = new OpenWeatherMapParser();
	    	WeatherSupplier supplier = new OpenWeatherMapSupplier(config.getAppId(),
	    														  config.getLang(),
	    														  parser);
			Weather w = supplier.getWeather(city, config.getUnits());
		
			OpenWeatherMapMessageBuilder builder = new OpenWeatherMapMessageBuilder();
			builder.setWeather(w);
			builder.setQwf(Qwf.getQwf(config.getLang(), config.getUnits()));
			
	    	Bot bot = new Bot(config.getTelegramBotToken());
	    	bot.sendMessage(config.getTelegramGroupId(), builder.getMessage());
	    	
	    	System.out.println("Success!");
	    	
	    	bot.getMessages().stream().forEach(m->{
	    		System.out.println("@" + m.getUserName() + ":" + m.getMessage());
	    	});
		} catch (Exception e) {
			System.err.println("Exception:" + e.getMessage());
		}
    }
}
