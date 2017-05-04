package application.core.processor;

import java.io.InputStreamReader;
import java.util.List;

import application.model.ResultModel;

/**
 * 
 * In case of having other strategies to process stream for data
 * 
 * @author Horseman
 *
 */
public interface IStreamParser {

	public List<ResultModel> getResults(InputStreamReader inputStream, int limit);

}
