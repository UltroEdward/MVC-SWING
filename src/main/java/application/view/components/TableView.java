package application.view.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import application.model.ResultTable;

public class TableView extends JPanel {

	private static final long serialVersionUID = 8828350317981591585L;
	private ResultTable model = null;
	private JTable table = null;

	public TableView() {
		initItems();
	}

	private void initItems() {
		LayoutManager layout = new GridBagLayout();
		this.setLayout(layout);

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.weightx = 1;

		model = new ResultTable();
		table = new JTable(model);

		JScrollPane scrollPane = new JScrollPane(table);
		this.add(scrollPane);

	}
}
