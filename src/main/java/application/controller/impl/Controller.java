package application.controller.impl;

import static application.core.Constants.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import application.controller.IEventListenerController;
import application.core.processor.AbstarctTableWorker;
import application.core.processor.impl.RegExpTableWorker;
import application.core.utils.HttpUtils;
import application.model.ResultModel;
import application.model.ResultTableModel;
import application.view.MainFrameView;

public class Controller implements IEventListenerController {

	private static final Logger LOG = LoggerFactory.getLogger(Controller.class);
	private ResultTableModel model = null;
	private MainFrameView view = null;

	public Controller(ResultTableModel model, MainFrameView view) {
		this.model = model;
		this.view = view;
		initController();
	}

	public final void initController() {
		view.getActionBarBlock().getSearchBtn().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				List<ResultModel> data = getData();
				populateData(data);
			}
		});
	};

	@Override
	public List<ResultModel> getData() {
		final List<ResultModel> results = new ArrayList<>();

		String[] inputData = view.getActionBarBlock().getSearchTxtValue().split(" ");
		String url = ControllerHelpers.generateSearchUrl(SEARCH_BASE_URL, inputData);
		HttpResponse resp = HttpUtils.doGet(url);
		
		InputStreamReader inputStream = null;
		AbstarctTableWorker parser = null;

		try {
			inputStream = new InputStreamReader(resp.getEntity().getContent(), StandardCharsets.UTF_8);
			parser = new RegExpTableWorker(inputStream, MAX_RESULTS_COUNT_TO_SHOW, this);
			parser.execute();
		} catch (Exception e) {
			LOG.error("Some error happens while updating table: " + e.getMessage());
		}

		return results;
	}

	@Override
	public void populateData(List<ResultModel> data) {
		model.clearData();
		model.addData(data);
	}

}
