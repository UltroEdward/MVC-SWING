package application.controller;

import java.util.List;

import application.model.Result;

public interface EventListener {

	public List<Result> getData();

	public void populateData(List<Result> data);
}
