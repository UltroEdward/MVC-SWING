package application.view;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JFrame;

import application.view.components.ActionBarView;
import application.view.components.TableView;

public class MainFrameView extends JFrame {

	private static final long serialVersionUID = 4805280124968896690L;

	public TableView table = null;
	public ActionBarView bar = null;

	public MainFrameView() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		LayoutManager layout = new GridBagLayout();
		this.setLayout(layout);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		table = new TableView();
		bar = new ActionBarView();

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 5, 5, 5);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.weightx = 0.8;
		bar = new ActionBarView();
		this.add(bar, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0;
		table = new TableView();
		this.add(table, c);

		this.getContentPane().setLayout(layout);

		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gd.getDisplayMode().getWidth() / 2;
		int height = gd.getDisplayMode().getHeight() / 2;
		this.setSize(width, height);
	}
}
