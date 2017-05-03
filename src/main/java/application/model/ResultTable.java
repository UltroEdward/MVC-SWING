package application.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import application.view.Constants;

public class ResultTable extends AbstractTableModel implements TableModel {

	private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
	private List<Result> results = new ArrayList<>();

	private enum Column {
		NUMBER(0, Constants.TABLE_HEADER_NUMBER), URL(1, Constants.TABLE_HEADER_URL), TITLE(2,
				Constants.TABLE_HEADER_TITLE);

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

	public ResultTable() {

	}

	public int getRowCount() {
		return results.size();
	}

	public int getColumnCount() {
		return Column.values().length;
	}

	public String getColumnName(int column) {
		return getColumn(column).getName();
	}

	public Class<?> getColumnClass(int arg0) {
		// TODO: think about using proper data types
		return String.class;
	}

	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	public Object getValueAt(int row, int column) {
		Result result = results.get(row);
		Column col = getColumn(column);

		switch (col) {
		case NUMBER:
			return result.getNumber();
		case TITLE:
			return result.getTitle();
		case URL:
			return result.getUrl();
		}
		return "";
	}

	public void setValueAt(Object data, int row, int column) {
		//Result result = results.get(row);
		Column col = getColumn(column);
		Result result = (Result) data;
		result.setNumber("1");
		results.add(result);
		//switch (col) {
		//case NUMBER:
		//	result.setNumber((String) data);
		//	break;
		//case TITLE:
		//	result.setTitle((String) data);
		//	break;
		//case URL:
		//	result.setUrl((String) data);
		//	break;
		//}
		System.out.println("fire");
		fireTableDataChanged();
		fireTableCellUpdated(1, 1);
		fireTableCellUpdated(0, 1);
	
	}

	public void addTableModelListener(TableModelListener listener) {
		listeners.add(listener);

	}

	public void removeTableModelListener(TableModelListener listener) {
		listeners.remove(listener);

	}

	private Column getColumn(int columnNumber) {
		for (Column column : Column.values()) {
			if (column.getOrder() == columnNumber) {
				return column;
			}
		}
		throw new IllegalArgumentException("Desired column is not exists: " + columnNumber);
	}

}
