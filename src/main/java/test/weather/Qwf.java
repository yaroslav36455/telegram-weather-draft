package test.weather;

import java.util.Locale;
import java.util.ResourceBundle;

import test.weather.model.Units;

public class Qwf {
	private static String RESOURCE_NAME = "locale";
	private static Qwf qwf;
	private ResourceBundle bundle;
	private Units units;
	
	public static Qwf getQwf(String lang, Units units) {		
		if(qwf == null || qwf.bundle == null || !qwf.getLocale().equals(lang)) {
			qwf = new Qwf(lang, units);
		}
		return qwf;
	}
	
	public String getLocale() {
		return qwf.bundle.getLocale().getLanguage();
	}
	
	public Units getUnits() {
		return units;
	}
	
	private Qwf(String lang, Units units) {
		Locale locale = new Locale(lang);
		bundle = ResourceBundle.getBundle(RESOURCE_NAME, locale);
		this.units = units;
	}
	
	public String getCityPrefix() {
		return bundle.getString("city");
	}
	
	public String getWeatherPrefix() {
		return bundle.getString("weather");
	}
	
	public String getTemperaturePrefix() {
		return bundle.getString("temperature");
	}
	
	public String getTemperatureFeelsLlikePrefix() {
		return bundle.getString("temperature.feels_like");
	}
	
	public String getTemperatureMinPrefix() {
		return bundle.getString("temperature.min");
	}
	
	public String getTemperatureMaxPrefix() {
		return bundle.getString("temperature.max");
	}
	
	public String getPressurePrefix() {
		return bundle.getString("pressure");
	}
	
	public String getPressureSeaLevelPrefix() {
		return bundle.getString("pressure.sea_level");
	}
	
	public String getPressureGroundLevelPrefix() {
		return bundle.getString("pressure.ground_level");
	}
	
	public String getHumidityPrefix() {
		return bundle.getString("humidity");
	}
	
	public String getWind() {
		return bundle.getString("wind");
	}
	
	public String getWindSpeedPrefix() {
		return bundle.getString("wind.speed");
	}
	
	public String getWindDegreePrefix() {
		return bundle.getString("wind.degree");
	}
	
	public String getWindGustPrefix() {
		return bundle.getString("wind.gust");
	}
	
	public String getVisibilityPrefix() {
		return bundle.getString("visibility");
	}
	
	public String getCloudnessPrefix() {
		return bundle.getString("cloudness");
	}
	
	public String getPrecipitationPrefix() {
		return bundle.getString("precipitation");
	}
	
	public String getPrecipitationRainPrefix() {
		return bundle.getString("precipitation.rain");
	}
	
	public String getPrecipitationSnowPrefix() {
		return bundle.getString("precipitation.snow");
	}
	
	
	public String getTemperatureSuffix() {
		String unit;
		
		switch (units) {
		case METRIC:   unit = "°C"; break;
		case IMPERIAL: unit = "°F"; break;
		/* STANDARD */
		default: unit = "K"; break;
		}
		
		return unit;
	}
	
	public String getPressureSuffix() {
		return bundle.getString("pressure.units");
	}
	
	public String getHumiditySuffix() {
		return "%";
	}
	
	public String getWindVelocitySuffix() {
		return units == Units.IMPERIAL ? bundle.getString("wind.speed.units.imperial")
									   : bundle.getString("wind.speed.units.metric");
	}
	
	public String getWindDegreeSuffic() {
		return "°";
	}
	
	public String getVisibilitySuffix() {
		return bundle.getString("visibility.units");
	}
	
	public String getCloudnessSuffix() {
		return "%";
	}
	
	public String getPrecipitationSuffix() {
		return bundle.getString("precipitation.units");
	}
	
	public String getPrecipitationPrefix1h() {
		return bundle.getString("precipitation.1h");
	}
	
	public String getPrecipitationPrefix3h() {
		return bundle.getString("precipitation.3h");
	}
}
