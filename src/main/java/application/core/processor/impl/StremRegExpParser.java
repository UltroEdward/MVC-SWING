package application.core.processor.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import application.core.processor.IStreamParser;
import application.model.ResultModel;
import application.utils.Constants;

public class StremRegExpParser implements IStreamParser {

	private static final Logger LOG = LoggerFactory.getLogger(StremRegExpParser.class);

	public static final String REG_EXP_URL = "<cite class=\"_Rm\">([^<]+)";
	public static final String REG_EXP_TITLE = "<h3 class=\"r\"><a href[^<]+\">([^<]+)";

	private Pattern pUrl = Pattern.compile(REG_EXP_URL);
	private Pattern pTitle = Pattern.compile(REG_EXP_TITLE);
	private Matcher mUrl = null;
	private Matcher mTitle = null;

	@Override
	public List<ResultModel> getResults(InputStreamReader inputStream, int limit) {

		List<String> urls = new ArrayList<>();
		List<String> titles = new ArrayList<>();

		if (limit < 0) {
			limit = Constants.MAX_RESULTS_COUNT_TO_SHOW;
		}

		BufferedReader br = new BufferedReader(inputStream);
		String l;

		try (InputStreamReader stream = inputStream;) {

			while ((l = br.readLine()) != null) {

				mUrl = pUrl.matcher(l);
				mTitle = pTitle.matcher(l);

				while (mUrl.find()) {
					if (mUrl.group().length() != 0) {
						urls.add(mUrl.group(1).trim());
					}
				}

				while (mTitle.find()) {
					if (mTitle.group().length() != 0) {
						titles.add(mTitle.group(1).trim());
					}
				}

			}
		} catch (IOException e) {
			LOG.error("Uh-oh, getiing IO exception!" + e.getMessage());
			LOG.debug("IOException: " + e.getStackTrace());
		}
		LOG.info("Data processed");
		LOG.info(String.format("Items found: %sURL [%s], %sTITLE [%s]", Constants.SEPARATOR, urls, Constants.SEPARATOR, titles));
		return buildResultSet(urls, titles);
	}

	private List<ResultModel> buildResultSet(List<String> urls, List<String> titles) {
		List<ResultModel> results = new ArrayList<>();

		if (urls.isEmpty() || titles.isEmpty()) {
			LOG.error("No results founded, resulty set is empty");
		} else if (urls.size() != titles.size()) {
			LOG.error("Some data is missing, check data-processor for correct parsing");
		}

		for (int i = 0; i < urls.size(); i++) {
			ResultModel result = new ResultModel();
			result.setNumber(String.valueOf(i + 1));
			result.setUrl(urls.get(i));
			result.setTitle(titles.get(i));

			results.add(result);
		}

		return results;
	}

}
