package test.weather.model;

import java.util.Optional;

public class PrecipitationVolume {
	private Optional<Float> oneHour;
	private Optional<Float> threeHours;
	
	public PrecipitationVolume() {
		oneHour = Optional.empty();
		threeHours = Optional.empty();
	}
	
	public Optional<Float> getOneHour() {
		return oneHour;
	}
	
	public void setOneHour(Float oneHour) {
		this.oneHour = Optional.ofNullable(oneHour);
	}
	
	public Optional<Float> getThreeHours() {
		return threeHours;
	}
	
	public void setThreeHours(Float threeHours) {
		this.threeHours = Optional.ofNullable(threeHours);
	}
}
