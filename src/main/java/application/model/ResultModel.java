package application.model;

import application.utils.Constants;

public class ResultModel {

	private String number;
	private String url;
	private String title;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		String cleanUrl = url.trim();
		if (cleanUrl.length() > Constants.MAX_URL_LENGTH) {
			cleanUrl = cleanUrl.substring(0, Constants.MAX_URL_LENGTH);
		}

		this.url = cleanUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		String cleanTitle = url.trim();
		if (cleanTitle.length() > Constants.MAX_TITLE_LENGTH) {
			cleanTitle = cleanTitle.substring(0, Constants.MAX_TITLE_LENGTH);
		}

		this.title = cleanTitle;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		String NEW_LINE = System.getProperty("line.separator");

		result.append(this.getClass().getName() + " Object {" + NEW_LINE);
		result.append(" Number: " + getNumber() + NEW_LINE);
		result.append(" URL: " + getUrl() + NEW_LINE);
		result.append(" Title: " + getTitle() + NEW_LINE);
		result.append("}");

		return result.toString();
	}

}
