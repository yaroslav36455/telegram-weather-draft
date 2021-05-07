package test.weather.model;

import java.util.Optional;

public class Wind {
	private Optional<Float> speed;
	private Optional<Float> degree;
	private Optional<Float> gust;
	
	public Wind() {
		speed = Optional.empty();
		degree = Optional.empty();
		gust = Optional.empty();
	}

	public Optional<Float> getSpeed() {
		return speed;
	}

	public void setSpeed(Float speed) {
		this.speed = Optional.ofNullable(speed);
	}

	public Optional<Float> getDegree() {
		return degree;
	}

	public void setDegree(Float degree) {
		this.degree = Optional.ofNullable(degree);
	}

	public Optional<Float> getGust() {
		return gust;
	}

	public void setGust(Float gust) {
		this.gust = Optional.ofNullable(gust);
	}
}
