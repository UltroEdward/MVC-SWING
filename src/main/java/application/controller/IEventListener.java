package application.controller;

import java.util.List;

import application.model.ResultModel;

public interface IEventListener {

	public List<ResultModel> getData();

	public void populateData(List<ResultModel> data);
}
