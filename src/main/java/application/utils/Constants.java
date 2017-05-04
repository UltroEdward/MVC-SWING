package application.utils;

public final class Constants {

	/**
	 * Application labels
	 */
	public static final String APP_HEADER = "Awesome app";
	public static final String SEARCH_BUTTON_TEXT = "Search";
	public static final String TABLE_HEADER_NUMBER = "No";
	public static final String TABLE_HEADER_URL = "URL";
	public static final String TABLE_HEADER_TITLE = "Title";

	/**
	 * Application configuration
	 */
	public static final int MAX_REQUEST_LENGTH = 100;
	public static final int MAX_URL_LENGTH = 100;
	public static final int MAX_TITLE_LENGTH = 100;
	public static final int MAX_RESULTS_COUNT_TO_SHOW = 10;
	public static final boolean IS_COLUMNS_EDITABLE = true;
	public static final String SEPARATOR = System.getProperty("line.separator");

	/**
	 * HTTP tools configuration
	 */
	public static final int CONNECTION_REQUEST_TIMEOUT_SEC = 60;
	public static final int CONNECTION_TIMEOUT_SEC = 60;
	public static final String SEARCH_BASE_URL = "https://www.google.by/search?q=";

}
