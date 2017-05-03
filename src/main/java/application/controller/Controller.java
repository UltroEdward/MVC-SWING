package application.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import application.model.ResultModel;
import application.model.ResultTableModel;
import application.view.MainFrameView;

public class Controller implements EventListener {

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
		List<ResultModel> results = new ArrayList<>();

		return results;

	}

	@Override
	public void populateData(List<ResultModel> data) {
		System.out.println("populate request");
		model.clearData();
		model.addData(data);

	}

}
