// Benjamin Duncan
// MainMenuScene.java

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenuScene extends Scene {

	private int diff = 1;
	private int size = 10;

	private JComboBox<String> diffOption;
	private JTextComponent sizeOption;

	private JButton begin;

	private JPanel mainPanel;
	private JPanel optionPanel;
	private JPanel buttonPanel;

	private JGif mainGif;

	public MainMenuScene(SceneManager sceneManager) {
		super(sceneManager);

		// set gif
		mainGif = new JGif();
		for (int i = 0; i < 40; i++) {
			String picName = "assets/Castle/castle_";
			if (i < 10)
				picName += "0";
			picName += i + ".png";
			mainGif.addFrame(picName);
		}

		// difficulty selector
		diffOption = new JComboBox<String>();
		diffOption.addItem("Easy");
		diffOption.addItem("Medium");
		diffOption.addItem("Hard");

		// set up myGL
		GridLayout myGL = new GridLayout(1, 4);
		myGL.setHgap(10);

		// set up buttonPanel
		buttonPanel = new JPanel(myGL);
		begin = new JButton("Begin");
		begin.setPreferredSize(new Dimension(30, 30));
		buttonPanel.add(new JLabel(""));
		buttonPanel.add(begin);
		buttonPanel.add(new JLabel(""));
		begin.addActionListener(new beginListener());

		// set up optionPanel
		sizeOption = new JTextField(Integer.toString(size));

		optionPanel = new JPanel(myGL);
		optionPanel.add(new JLabel(""));
		optionPanel.add(sizeOption);
		optionPanel.add(diffOption);
		optionPanel.add(new JLabel(""));

		// set up mainPanel
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setSize(500, 500);
		mainPanel.add(mainGif, BorderLayout.NORTH);
		mainPanel.add(optionPanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		super.add(mainPanel);
	}

	public boolean close() {
		return true;
	}

	public int getDiff() {
		return diff;
	}

	public int getMazeSize() {
		return size;
	}

	public void tick(int msTick, InputManager input) {
		super.repaint();
		if (mainGif != null)
			mainGif.tick(msTick, input);
	}

	private class beginListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String op;
			try {
				size = Integer.parseInt(sizeOption.getText());
				if (diffOption.getSelectedItem() instanceof String) {
					op = (String) diffOption.getSelectedItem();

					if (op.equals("Easy"))
						diff = 1;
					else if (op.equals("Medium"))
						diff = 2;
					else if (op.equals("Hard"))
						diff = 3;
				}

				sceneManager.LoadScene(new MazeScene(sceneManager));
			} catch (Exception ea) {
				System.out.println("ERROR 23: " + ea);
			}
		}
	}
}
