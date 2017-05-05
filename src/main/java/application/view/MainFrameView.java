package application.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import application.core.Constants;
import application.view.components.ActionBarView;
import application.view.components.TableView;

public class MainFrameView extends JFrame {

	private static final Logger LOG = LoggerFactory.getLogger(MainFrameView.class);
	private static final long serialVersionUID = 4805280124968896690L;

	private final TableView tableView;
	private final ActionBarView actionBarView;

	public MainFrameView() {
		LOG.info("Init main frame...");
		tableView = new TableView();
		actionBarView = new ActionBarView();

		setUpFrame();
		initItems();
		LOG.info("Initialization of main frame finished");
	}

	public TableView getTableBlock() {
		return tableView;
	}

	public ActionBarView getActionBarBlock() {
		return actionBarView;
	}

	private void setUpFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setTitle(Constants.APP_HEADER);
		this.setLocationRelativeTo(null);

		Dimension bounds = new Dimension(500, 600);
		this.setMinimumSize(bounds);
	}

	private void initItems() {
		LayoutManager layout = new GridBagLayout();
		this.setLayout(layout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 5, 5, 5);

		c.gridx = 0;
		c.weightx = 0.8;
		this.add(actionBarView, c);

		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0;
		this.add(tableView, c);

		pack();
	}

}
