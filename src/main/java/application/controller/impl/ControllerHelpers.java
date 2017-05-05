package application.controller.impl;

import java.util.Arrays;

public class ControllerHelpers {

	private ControllerHelpers() {
	}

	public static String generateSearchUrl(String baseUrl, String... searchParams) {
		StringBuilder builder = new StringBuilder();
		builder.append(baseUrl);
		Arrays.asList(searchParams).stream().forEach(e -> builder.append(e + "+"));

		if (searchParams.length > 0) {
			return builder.substring(0, builder.length() - 1).toString();
		}

		return builder.toString();
	}

}
