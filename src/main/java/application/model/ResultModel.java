package application.model;

import static application.core.Constants.*;

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
		if (cleanUrl.length() > MAX_URL_LENGTH) {
			cleanUrl = cleanUrl.substring(0, MAX_URL_LENGTH);
		}

		this.url = cleanUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		String cleanTitle = title.trim();
		if (cleanTitle.length() > MAX_TITLE_LENGTH) {
			cleanTitle = cleanTitle.substring(0, MAX_TITLE_LENGTH);
		}

		this.title = cleanTitle;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

		result.append(this.getClass().getName() + " Object {" + SEPARATOR);
		result.append(" Number: " + getNumber() + SEPARATOR);
		result.append(" URL: " + getUrl() + SEPARATOR);
		result.append(" Title: " + getTitle() + SEPARATOR);
		result.append("}");

		return result.toString();
	}

}
