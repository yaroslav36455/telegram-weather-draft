package test.weather.model;

import java.util.Optional;

public class Temperature {
	private Optional<Float> temperature = Optional.empty();
	private Optional<Float> feelsLike = Optional.empty();
	private Optional<Float> min = Optional.empty();
	private Optional<Float> max = Optional.empty();
	
	public Optional<Float> getTemperature() {
		return temperature;
	}
	public void setTemperature(Float temperature) {
		this.temperature = Optional.ofNullable(temperature);
	}
	public Optional<Float> getMin() {
		return min;
	}
	public void setMin(Float min) {
		this.min = Optional.ofNullable(min);
	}
	public Optional<Float> getMax() {
		return max;
	}
	public void setMax(Float max) {
		this.max = Optional.ofNullable(max);
	}
	public Optional<Float> getFeelsLike() {
		return feelsLike;
	}
	public void setFeelsLike(Float feelsLike) {
		this.feelsLike = Optional.ofNullable(feelsLike);
	}
}
