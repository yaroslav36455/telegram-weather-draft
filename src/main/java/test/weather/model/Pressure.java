package test.weather.model;

import java.util.Optional;

public class Pressure {
	private Optional<Integer> pressure;
	private Optional<Integer> seaLevel;
	private Optional<Integer> groundLevel;
	
	public Pressure() {
		pressure = Optional.empty();
		seaLevel = Optional.empty();
		groundLevel = Optional.empty();
	}
	
	public Optional<Integer> getPressure() {
		return pressure;
	}
	public void setPressure(Integer pressure) {
		this.pressure = Optional.ofNullable(pressure);
	}
	public Optional<Integer> getSeaLevel() {
		return seaLevel;
	}
	public void setSeaLevel(Integer seaLevel) {
		this.seaLevel = Optional.ofNullable(seaLevel);
	}
	public Optional<Integer> getGroundLevel() {
		return groundLevel;
	}
	public void setGroundLevel(Integer groundLevel) {
		this.groundLevel = Optional.ofNullable(groundLevel);
	}
}
