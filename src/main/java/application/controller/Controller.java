package application.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import application.model.Result;
import application.model.ResultTable;
import application.view.MainFrameView;

public class Controller implements EventListener {

	private ResultTable model = null;
	private MainFrameView view = null;

	public Controller(ResultTable model, MainFrameView view) {
		this.model = model;
		this.view = view;
		initController();
	}

	public void initController() {
		view.bar.getSearchBtn().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				List<Result> data = getData();
				populateData(data);
			}
		});
		
		//view.table.setL
	}

	@Override
	public List<Result> getData() {
		List<Result> results = new ArrayList<>();
		Result result = new Result();
		result.setNumber("1");
		result.setTitle("1");
		result.setUrl("url");
		System.out.println("make request");
		results.add(result);
		return results;

	}

	@Override
	public void populateData(List<Result> data) {
		System.out.println("populate request");
		model.setValueAt(data.get(0), 0, 0);

	}

}
