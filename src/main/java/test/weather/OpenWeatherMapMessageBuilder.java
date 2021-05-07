package test.weather;

import java.util.Optional;
import java.util.function.Supplier;

import test.weather.model.PrecipitationVolume;
import test.weather.model.Pressure;
import test.weather.model.Temperature;
import test.weather.model.Weather;
import test.weather.model.Wind;

public class OpenWeatherMapMessageBuilder {
	private static final int INDENTATION = 2; 
	private Optional<Weather> weather;
	private Optional<String> message;
	private Qwf qwf;
	
	public void setWeather(Weather weather) {
		this.weather = Optional.ofNullable(weather);
		this.message = Optional.empty();
	}
	
	public void setQwf(Qwf qwf) {
		this.qwf = qwf;
		this.message = Optional.empty();
	}
	
	public String getMessage() {
		if(message.isEmpty()) {
			buildMessage();
		}
		return message.get();
	}
	
	private void buildMessage() {
		final String ind = " ".repeat(INDENTATION);
		Weather weather = this.weather.get();
		StringBuilder builder = new StringBuilder();
		
		appendLine(builder, qwf.getCityPrefix(), weather::getCity, "");
		appendLine(builder, qwf.getWeatherPrefix(), weather::getDescription, "");
		
		if(weather.getTemperature().isPresent()) {
			Temperature temperature = weather.getTemperature().get();
			
			appendLine(builder, ind + qwf.getTemperaturePrefix(), temperature::getTemperature, qwf.getTemperatureSuffix());
			appendLine(builder, ind + qwf.getTemperatureFeelsLlikePrefix(), temperature::getFeelsLike, qwf.getTemperatureSuffix());
			appendLine(builder, ind + qwf.getTemperatureMinPrefix(), temperature::getMin, qwf.getTemperatureSuffix());
			appendLine(builder, ind + qwf.getTemperatureMaxPrefix(), temperature::getMax, qwf.getTemperatureSuffix());
		}
		
		if(weather.getPressure().isPresent()) {
			Pressure pressure = weather.getPressure().get();
			
			appendLine(builder, ind + qwf.getPressurePrefix(), pressure::getPressure, qwf.getPressureSuffix());
			appendLine(builder, ind + qwf.getPressureSeaLevelPrefix(), pressure::getSeaLevel, qwf.getPressureSuffix());
			appendLine(builder, ind + qwf.getPressureGroundLevelPrefix(), pressure::getGroundLevel, qwf.getPressureSuffix());
		}
		
		appendLine(builder, qwf.getHumidityPrefix(), weather::getHumidity, qwf.getHumiditySuffix());
		
		if(weather.getWind().isPresent()) {
			Wind wind = weather.getWind().get();
			
			builder.append(qwf.getWind()).append(":\n");
			
			appendLine(builder, ind + qwf.getWindSpeedPrefix(), wind::getSpeed, qwf.getWindVelocitySuffix());
			appendLine(builder, ind + qwf.getWindDegreePrefix(), wind::getDegree, qwf.getWindDegreeSuffic());
			appendLine(builder, ind + qwf.getWindGustPrefix(), wind::getGust, qwf.getWindVelocitySuffix());
		}
		
		appendLine(builder, qwf.getVisibilityPrefix(), weather::getVisibility, qwf.getVisibilitySuffix());
		appendLine(builder, qwf.getCloudnessPrefix(), weather::getClouds, qwf.getCloudnessSuffix());
		
		if(weather.getRain().isPresent() || weather.getSnow().isPresent()) {
			builder.append(qwf.getPrecipitationPrefix()).append(":\n");
		}
		
		if(weather.getRain().isPresent()) {
			PrecipitationVolume volume = weather.getRain().get();
			
			builder.append(ind + qwf.getPrecipitationRainPrefix()).append(":\n");
			appendLine(builder, ind+ind+qwf.getPrecipitationPrefix1h(), volume::getOneHour, qwf.getPrecipitationSuffix());
			appendLine(builder, ind+ind+qwf.getPrecipitationPrefix3h(), volume::getThreeHours, qwf.getPrecipitationSuffix());
		}
		
		if(weather.getSnow().isPresent()) {
			PrecipitationVolume volume = weather.getSnow().get();
			
			builder.append(ind + qwf.getPrecipitationSnowPrefix()).append(":\n");
			appendLine(builder, ind+ind+qwf.getPrecipitationPrefix1h(), volume::getOneHour, qwf.getPrecipitationSuffix());
			appendLine(builder, ind+ind+qwf.getPrecipitationPrefix3h(), volume::getThreeHours, qwf.getPrecipitationSuffix());
		}
		
		message = Optional.of(builder.toString());
	}
	
	private <T> void appendLine(StringBuilder builder, String prefix,
								Supplier<Optional<T>> s, String suffix) {
		if(s.get().isPresent()) {
			T v = s.get().get();
			builder.append(prefix).append(": ").append(v).append(" "+suffix).append('\n');
		}
	}
}
