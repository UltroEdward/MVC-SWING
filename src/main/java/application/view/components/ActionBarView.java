package application.view.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import application.core.Constants;

public class ActionBarView extends JPanel {

	private static final long serialVersionUID = 3962286261970694411L;
	private JButton searchBtn = new JButton(Constants.SEARCH_BUTTON_TEXT);
	private JTextField searchTxt = new JTextField();

	public ActionBarView() {
		addItems();
	}

	public JButton getSearchBtn() {
		return searchBtn;
	}
	
	public String getSearchTxtValue() {
		return searchTxt.getText();
	}

	private void addItems() {
		LayoutManager layout = new GridBagLayout();
		this.setLayout(layout);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;

		constraints.gridx = 0;
		constraints.weightx = 0.8;
		this.add(searchTxt, constraints);

		constraints.gridx = 1;
		constraints.weightx = 0; //make as default
		this.add(searchBtn, constraints);
	}

}
