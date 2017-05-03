package application.view.components;

import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import application.model.ResultTableModel;

public class TableView extends JPanel {

	private static final long serialVersionUID = 8828350317981591585L;
	private ResultTableModel model = null;
	private JTable table = null;
	private JScrollPane scrollPane = null;

	public TableView() {
		model =  ResultTableModel.getInstance();
		table = new JTable(model);
		scrollPane = new JScrollPane(table);

		addItems();
	}

	private void addItems() {
		LayoutManager layout = new FlowLayout();
		this.setLayout(layout);

		this.add(scrollPane);

	}
}
