package test.weather.config;

import test.weather.model.Units;

public class Config {
	private String telegramBotToken;
	private String telegramGroupId;
	private String appId;
	private String lang;
	private Units units;
	
	public String getTelegramBotToken() {
		return telegramBotToken;
	}
	public void setTelegramBotToken(String telegramBotToken) {
		this.telegramBotToken = telegramBotToken;
	}
	public String getTelegramGroupId() {
		return telegramGroupId;
	}
	public void setTelegramGroupId(String telegramGroupId) {
		this.telegramGroupId = telegramGroupId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public Units getUnits() {
		return units;
	}
	public void setUnits(Units units) {
		this.units = units;
	}
}
