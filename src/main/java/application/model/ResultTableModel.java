package application.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.table.AbstractTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import application.utils.Constants;

/**
 * 
 * @author Horseman
 * 
 *         JTable view can't work without predefined implementation of
 *         AbstractTableModel, so this Class is singleton to safe share in for
 *         Controller and View
 *
 */
public class ResultTableModel extends AbstractTableModel {

	private static volatile ResultTableModel instance = null;

	private static final Logger LOG = LoggerFactory.getLogger(ResultTableModel.class);

	private static final long serialVersionUID = -1630622466946080960L;
	private static final List<ResultModel> results = new ArrayList<>(Constants.MAX_RESULTS_COUNT_TO_SHOW);

	private enum Column {
		NUMBER(0, Constants.TABLE_HEADER_NUMBER), URL(1, Constants.TABLE_HEADER_URL), TITLE(2, Constants.TABLE_HEADER_TITLE);

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

	private ResultTableModel() {
	}

	public void addData(List<ResultModel> results) {
		results.stream().forEach(e -> addData(e));
	}

	public void clearData() {
		results.clear();
	}

	public void addData(ResultModel result) {
		results.add(result);
		fireTableDataChanged();
	}

	public int getRowCount() {
		int count = results.size();
		LOG.debug("Rows count:" + count);
		return count;
	}

	public int getColumnCount() {
		int count = Column.values().length;
		LOG.debug("Columns count:" + count);
		return count;
	}

	public String getColumnName(int column) {
		int colCount = getColumnCount();
		if (colCount < column) {
			LOG.error(String.format("Trying to get column [%d] that out of scope, colum size is: [%d]", column, colCount));
			return "";
		}
		return getColumn(column).getName();
	}

	public Class<?> getColumnClass(int arg0) {
		// TODO: think about using proper data types
		return String.class;
	}

	public boolean isCellEditable(int arg0, int arg1) {
		return true;
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

	public void setValueAt(Object data, int row, int column) {

		if (!Constants.IS_COLUMNS_EDITABLE) {
			LOG.info("Setting data - disabled");
			return;
		}

		Optional<ResultModel> result = getRow(row);
		if (!result.isPresent()) {
			LOG.error(String.format("Can't save result, cell {%d ; %d} is not presented:", row, column));
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
		if (row > rowCount || results.isEmpty()) {
			if (results.size() < Constants.MAX_RESULTS_COUNT_TO_SHOW) {
				results.add(new ResultModel());
				LOG.info(String.format("Trying to get row [%d] that out of scope, new one is added: [%d]", row, rowCount));
			}
		}
		return Optional.of(results.get(row));
	}

}
