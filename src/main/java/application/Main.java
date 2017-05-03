package application;

import application.controller.Controller;
import application.model.ResultTable;
import application.view.MainFrameView;

public class Main {

	private ResultTable model = null;
	private MainFrameView view = null;
	private Controller controller = null;

	public Main() {
		view = new MainFrameView();
		model = new ResultTable();
		controller = new Controller(model, view);
	}

	public static void main(String[] args) {
		new Main();
		System.out.println("Start");
	}

}
