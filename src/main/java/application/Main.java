package application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import application.controller.Controller;
import application.model.ResultTableModel;
import application.view.MainFrameView;

public class Main {

	private static final Logger LOG = LoggerFactory.getLogger(Main.class);

	private ResultTableModel model = null;
	private MainFrameView view = null;
	@SuppressWarnings("unused")
	private Controller controller = null;

	public Main() {
		view = new MainFrameView();
		model = ResultTableModel.getInstance();
		controller = new Controller(model, view);
	}

	public static void main(String[] args) {
		new Main();
		LOG.info("Application is started");
	}

}
