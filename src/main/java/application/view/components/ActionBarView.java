package application.view.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import application.view.Constants;

public class ActionBarView extends JPanel {

	private static final long serialVersionUID = 6238419338202523501L;

	private JButton searchBtn = new JButton(Constants.SEARCH_BUTTON_TEXT);
	private JTextField searchTxt = new JTextField(5);

	public ActionBarView() {
		initItems();
	}

	private void initItems() {
		LayoutManager layout = new GridBagLayout();
		this.setLayout(layout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 5, 5, 5);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.weightx = 0.8;
		this.add(searchTxt, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.weightx = 0;
		this.add(searchBtn, c);
	}

	public JButton getSearchBtn() {
		return searchBtn;
	}

}
