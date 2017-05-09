package application.core.processor;

import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import application.controller.IEventListenerController;
import application.core.processor.impl.RegExpTableWorker;
import application.model.ResultModel;

/**
 * 
 * In case of having other strategies to process stream for data.
 * 
 * @author Horseman
 *
 */
public abstract class AbstarctTableWorker extends SwingWorker<List<ResultModel>, Object> {

	private static final Logger LOG = LoggerFactory.getLogger(RegExpTableWorker.class);
	protected InputStreamReader stream;
	protected int limitRecords;
	protected IEventListenerController controller;

	public AbstarctTableWorker(InputStreamReader stream, int limitRecords, IEventListenerController controller) {
		this.stream = stream;
		this.limitRecords = limitRecords;
		this.controller = controller;
	}

	protected abstract List<ResultModel> getResults(InputStreamReader inputStream);

	@Override
	protected List<ResultModel> doInBackground() throws Exception {
		return getResults(this.stream);
	}

	@Override
	protected void done() {
		try {
			controller.populateData(this.get());
		} catch (InterruptedException | ExecutionException ex) {
			LOG.error("Data process failed " + ex.getMessage());
		}
	}

}
