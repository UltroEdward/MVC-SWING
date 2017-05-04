package application;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

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
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
			    @Override
			    public void run() {
			    	new Main(); 
			    	LOG.info("Application is started");
			    }
			});
		} catch (Exception e) {
			LOG.info("Uh-oh, smth wrong!");
		}

		
	}

}
