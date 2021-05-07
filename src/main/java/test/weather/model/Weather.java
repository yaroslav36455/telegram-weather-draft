package test.weather.model;

import java.util.Optional;

public class Weather {
	private Units units;
	private Optional<String> city = Optional.empty();
	private Optional<String> description = Optional.empty();
	private Optional<Temperature> temperature = Optional.empty();
	private Optional<Pressure> pressure = Optional.empty();
	private Optional<Integer> humidity = Optional.empty();
	private Optional<Wind> wind = Optional.empty();
	private Optional<Integer> visibility = Optional.empty();
	private Optional<Integer> clouds = Optional.empty();
	private Optional<PrecipitationVolume> snow = Optional.empty();
	private Optional<PrecipitationVolume> rain = Optional.empty();
	
	public Weather(Units units) {
		this.units = units;
	}
	
	public Units getUnits() {
		return units;
	}
	public Optional<String> getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = Optional.ofNullable(city);
	}
	public Optional<String> getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = Optional.ofNullable(description);
	}
	public Optional<Temperature> getTemperature() {
		return temperature;
	}
	public void setTemperature(Temperature temperature) {
		this.temperature = Optional.ofNullable(temperature);
	}
	public Optional<Pressure> getPressure() {
		return pressure;
	}
	public void setPressure(Pressure pressure) {
		this.pressure = Optional.ofNullable(pressure);
	}
	public Optional<Integer> getHumidity() {
		return humidity;
	}
	public void setHumidity(Integer humidity) {
		this.humidity = Optional.ofNullable(humidity);
	}
	public Optional<Wind> getWind() {
		return wind;
	}
	public void setWind(Wind wind) {
		this.wind = Optional.ofNullable(wind);
	}
	public Optional<Integer> getVisibility() {
		return visibility;
	}
	public void setVisibility(Integer visibility) {
		this.visibility = Optional.ofNullable(visibility);
	}
	public Optional<Integer> getClouds() {
		return clouds;
	}
	public void setClouds(Integer clouds) {
		this.clouds = Optional.ofNullable(clouds);
	}
	public Optional<PrecipitationVolume> getSnow() {
		return snow;
	}
	public void setSnow(PrecipitationVolume snow) {
		this.snow = Optional.ofNullable(snow);
	}
	public Optional<PrecipitationVolume> getRain() {
		return rain;
	}
	public void setRain(PrecipitationVolume rain) {
		this.rain = Optional.ofNullable(rain);
	}
}
