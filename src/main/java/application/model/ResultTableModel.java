package application.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.table.AbstractTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static application.core.Constants.*;

/**
 * @author Horseman
 * 
 *         JTable view can't work without predefined implementation of
 *         AbstractTableModel, so this Class is singleton to safe share for
 *         Controller and View
 */
public final class ResultTableModel extends AbstractTableModel {

	private static volatile ResultTableModel instance = null;

	private static final Logger LOG = LoggerFactory.getLogger(ResultTableModel.class);
	private static final long serialVersionUID = -1630622466946080960L;

	private static List<ResultModel> results = new ArrayList<>();

	private enum Column {
		NUMBER(0, TABLE_HEADER_NUMBER), URL(1, TABLE_HEADER_URL), TITLE(2, TABLE_HEADER_TITLE);

		private int order;
		private String name;

		Column(int order, String name) {
			this.order = order;
			this.name = name;
		}

		public int getOrder() {
			return this.order;
		}

		public String getName() {
			return this.name;
		}

	}

	private ResultTableModel() {
	}

	public static ResultTableModel getInstance() {
		ResultTableModel localInstance = instance;
		if (localInstance == null) {
			synchronized (ResultTableModel.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new ResultTableModel();
				}
			}
		}
		return instance;
	}

	/**
	 * Method call for each element method 'addData(ResultModel result)' instead
	 * of using 'results.addAll(results);' as some more logic can be implemented
	 * inside addData(ResultModel result
	 * 
	 * @param results:
	 *            adding new data to table
	 */
	public void addData(List<ResultModel> resultsToAdd) {
		for (int i = 0; i < resultsToAdd.size() & results.size() < MAX_RESULTS_COUNT_TO_SHOW; i++) {
			addData(resultsToAdd.get(i));
		}
	}

	public void clearData() {
		results.clear();
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		int count = results.size();
		LOG.debug("Rows count:" + count);
		return count;
	}

	@Override
	public int getColumnCount() {
		int count = Column.values().length;
		LOG.debug("Columns count:" + count);
		return count;
	}

	@Override
	public String getColumnName(int column) {
		int colCount = getColumnCount();
		if (colCount < column) {
			LOG.error(String.format("Trying to get column [%d] that out of scope, colum size is: [%d]", column, colCount));
			return "";
		}
		return getColumn(column).getName();
	}

	@Override
	public Class<?> getColumnClass(int arg0) {
		// TODO: think about using proper data types
		return String.class;
	}

	public boolean isCellEditable(int arg0, int arg1) {
		return IS_COLUMNS_EDITABLE;
	}

	@Override
	public Object getValueAt(int row, int column) {
		String value = "";
		Optional<ResultModel> resultRow = getRow(row);
		Column col = getColumn(column);

		if (!resultRow.isPresent()) {
			LOG.error(String.format("Result is not presented, row not exists, pushing 'empty' row"));
			return value;
		}

		switch (col) {
		case NUMBER:
			value = resultRow.get().getNumber();
			break;
		case TITLE:
			value = resultRow.get().getTitle();
			break;
		case URL:
			value = resultRow.get().getUrl();
			break;
		}
		LOG.debug(String.format("Value of cell {%d ; %d} is %s", row, column, value));
		return value;
	}

	@Override
	public void setValueAt(Object data, int row, int column) {

		if (!IS_COLUMNS_EDITABLE) {
			LOG.info("Setting data - disabled");
			return;
		}

		Optional<ResultModel> result = getRow(row);
		if (!result.isPresent()) {
			LOG.error(String.format("Can't save result, cell {%d ; %d} is not presented", row, column));
			return;
		}

		Column col = getColumn(column);

		switch (col) {
		case NUMBER:
			result.get().setNumber((String) data);
			break;
		case TITLE:
			result.get().setTitle((String) data);
			break;
		case URL:
			result.get().setUrl((String) data);
			break;
		}

		LOG.debug(String.format("Set value cell {%d ; %d} is %s", row, column, (String) data));
		fireTableCellUpdated(row, column);

	}

	private void addData(ResultModel result) {
		results.add(result);
		fireTableRowsInserted(results.size() - 1, results.size());
	}

	private Column getColumn(int columnNumber) {
		for (Column column : Column.values()) {
			if (column.getOrder() == columnNumber) {
				return column;
			}
		}
		throw new IllegalArgumentException("Desired column is not exists: " + columnNumber);
	}

	private Optional<ResultModel> getRow(int row) {
		int rowCount = results.size();

		if (row < 0) {
			LOG.error(String.format("Trying to get illigal row [%d] that out of scope", row));
			return Optional.empty();
		}

		// if 'row == results.size() + 1' - new item should be added to table
		if (row > results.size() + 1) {
			LOG.error(String.format("Trying to get row [%d] that out of scope as actual size is [%d]", row, rowCount));
			return Optional.empty();
		}

		if (row == results.size() + 1) {
			LOG.info(String.format("Increasing table size, as new result is added"));
			results.add(new ResultModel());
			fireTableRowsInserted(results.size() - 1, results.size());
			return Optional.of(results.get(row));
		}

		// value of already existing row
		return Optional.of(results.get(row));
	}

}
