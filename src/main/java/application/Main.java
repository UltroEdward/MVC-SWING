package application;

import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import application.controller.impl.Controller;
import application.model.ResultTableModel;
import application.view.MainFrameView;

public class Main {

	private static final Logger LOG = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		try {
			SwingUtilities.invokeAndWait(() -> {
				ResultTableModel model = ResultTableModel.getInstance();
				MainFrameView view = new MainFrameView();
				new Controller(model, view);
				LOG.info("Application is started");
			});
		} catch (Exception e) {
			LOG.info(String.format("Uh-oh, smth wrong! Getting exception: %s", e.getMessage()));
			LOG.error("Error is:" + e.getStackTrace());
		}

	}

}
