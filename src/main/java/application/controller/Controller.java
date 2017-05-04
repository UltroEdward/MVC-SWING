package application.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;

import application.core.processor.IStreamParser;
import application.core.processor.impl.StremRegExpParser;
import application.model.ResultModel;
import application.model.ResultTableModel;
import application.utils.Constants;
import application.utils.HttpUtils;
import application.view.MainFrameView;

public class Controller implements IEventListener {

	private ResultTableModel model = null;
	private MainFrameView view = null;

	public Controller(ResultTableModel model, MainFrameView view) {
		this.model = model;
		this.view = view;
		initController();
	}

	public void initController() {
		view.getActionBarBlock().getSearchBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				List<ResultModel> data = getData();
				populateData(data);
			}
		});
	}

	@Override
	public List<ResultModel> getData() {
		final List<ResultModel> results = new ArrayList<>();

		String[] inputData = view.getActionBarBlock().getSearchTxtValue().split(" ");
		String url = ControllerHelpers.generateSearchUrl(Constants.SEARCH_BASE_URL, inputData);
		HttpResponse resp = HttpUtils.doGet(url);

		try (InputStreamReader inputStream = new InputStreamReader(resp.getEntity().getContent(), StandardCharsets.UTF_8);) {
			new Runnable() {
				@Override
				public void run() {
					IStreamParser parser = new StremRegExpParser();
					List<ResultModel> resultsLoc = parser.getResults(inputStream, Constants.MAX_RESULTS_COUNT_TO_SHOW);
					results.addAll(resultsLoc);
				}
			}.run();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return results;

	}

	@Override
	public void populateData(List<ResultModel> data) {
		model.clearData();
		model.addData(data);
	}

}
