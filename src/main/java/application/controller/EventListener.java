package application.controller;

import java.util.List;

import application.model.ResultModel;

public interface EventListener {

	public List<ResultModel> getData();

	public void populateData(List<ResultModel> data);
}
