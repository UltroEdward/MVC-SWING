package application.view;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import application.utils.Constants;
import application.view.components.ActionBarView;
import application.view.components.TableView;

public class MainFrameView extends JFrame {

	private static final Logger LOG = LoggerFactory.getLogger(MainFrameView.class);
	private static final long serialVersionUID = 4805280124968896690L;

	private final TableView tableView;
	private final ActionBarView actionBarView;

	public MainFrameView() {
		LOG.debug("init main frame...");
		tableView = new TableView();
		actionBarView = new ActionBarView();

		setUpFrame();
		initItems();
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
		Dimension bounds = new Dimension(500, 600);
		this.setMinimumSize(bounds);
		this.setLocationRelativeTo(null);

		// set frame size as half of the screen
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth() / 3;
		int height = gd.getDisplayMode().getHeight() / 3;
		this.setSize(width, height);
		LOG.debug(String.format("Init frame size: %d x %d", width, height));
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
	}

}
