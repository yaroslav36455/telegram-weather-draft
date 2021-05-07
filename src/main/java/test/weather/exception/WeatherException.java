package test.weather.exception;

public class WeatherException extends Exception {
	private static final long serialVersionUID = -522024946033059683L;
	
	public WeatherException(String message) {
		super(message);
	}
}
