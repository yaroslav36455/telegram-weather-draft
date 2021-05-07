package test.weather.config;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import test.weather.model.Units;

public class ConfigLoader {
	private static final String FILE_NAME = "application.properties";
	private Config config;
	
	public ConfigLoader() {
		config = new Config();
	}

	public Config load() throws IOException {
		try (Reader reader = new FileReader(FILE_NAME);) {
			Properties prop = new Properties();

			prop.load(reader);

			config.setTelegramBotToken(prop.getProperty("telegram.bot.token"));
			config.setTelegramGroupId(prop.getProperty("telegram.group.id"));
			config.setAppId(prop.getProperty("openweathermap.appid"));
			config.setLang(prop.getProperty("message.lang", "en"));
			config.setUnits(Enum.valueOf(Units.class, prop.getProperty("units", Units.METRIC.name())
					  									  .toUpperCase()));
			return config;
		}
	}

	public Config getConfig() {
		return config;
	}
}
