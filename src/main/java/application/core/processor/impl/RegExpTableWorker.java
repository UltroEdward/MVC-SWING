package application.core.processor.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import application.controller.IEventListenerController;
import application.core.processor.AbstarctTableWorker;
import application.model.ResultModel;

import static application.core.Constants.*;

public class RegExpTableWorker extends AbstarctTableWorker {

	private static final Logger LOG = LoggerFactory.getLogger(RegExpTableWorker.class);

	public static final String REG_EXP_URL = "<cite class=\"_Rm\">([^<]+)";
	public static final String REG_EXP_TITLE = "<h3 class=\"r\"><a href[^<]+\">([^<]+)";

	private Pattern patternUrl = Pattern.compile(REG_EXP_URL);
	private Pattern patternTitle = Pattern.compile(REG_EXP_TITLE);
	private Matcher matcherUrl = null;
	private Matcher matcherTitle = null;

	public RegExpTableWorker(InputStreamReader stream, int limitRecords, IEventListenerController controller) {
		super(stream, limitRecords, controller);
		LOG.info("Creating worker in another (not Event Dispatcher) thread: " + SwingUtilities.isEventDispatchThread());
	}

	@Override
	protected List<ResultModel> getResults(InputStreamReader inputStream) {

		List<String> urls = new ArrayList<>();
		List<String> titles = new ArrayList<>();

		if (limitRecords < 0) {
			limitRecords = MAX_RESULTS_COUNT_TO_SHOW;
		}

		BufferedReader br = new BufferedReader(inputStream);
		String l;

		try (InputStreamReader stream = this.stream;) {

			// limitation added for time saving
			while ((l = br.readLine()) != null && !(urls.size() > limitRecords && titles.size() > limitRecords)) {

				matcherUrl = patternUrl.matcher(l);
				matcherTitle = patternTitle.matcher(l);

				while (matcherUrl.find()) {
					if (matcherUrl.group().length() != 0) {
						urls.add(matcherUrl.group(1).trim());
					}
				}

				while (matcherTitle.find()) {
					if (matcherTitle.group().length() != 0) {
						titles.add(matcherTitle.group(1).trim());
					}
				}
			}
		} catch (IOException e) {
			LOG.error("Uh-oh, getting IOException!: " + e.getMessage());
			LOG.debug("IOException: " + e.getStackTrace());
		}

		LOG.info("Data processed is done");
		LOG.info(String.format("Items found: %sURL [%s], %sTITLE [%s]", SEPARATOR, urls, SEPARATOR, titles));

		// cut results according to @limit value
		if (urls.size() > limitRecords) {
			urls = urls.subList(0, limitRecords);
		}

		if (titles.size() > limitRecords) {
			titles = titles.subList(0, limitRecords);
		}

		return buildResultSet(urls, titles);
	}

	private List<ResultModel> buildResultSet(List<String> urls, List<String> titles) {
		List<ResultModel> results = new ArrayList<>();

		if (urls.isEmpty() || titles.isEmpty()) {
			LOG.error("No results founded, result set is empty");
			return results;
		} else if (urls.size() != titles.size()) {
			LOG.error(String.format("Some data is missing, check data-processor for correct parsing, results is: urls:%d, titles:%d",
					urls.size(), titles.size()));
		}

		for (int i = 0; i < urls.size(); i++) {
			ResultModel result = new ResultModel();
			result.setNumber(String.valueOf(i + 1));
			result.setUrl(urls.get(i));

			if (i > titles.size() - 1) {
				result.setTitle(MISSING_DATA_PLACEHOLDER);
			} else {
				result.setTitle(titles.get(i));
			}

			results.add(result);
		}
		return results;
	}

}
