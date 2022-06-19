//Benjamin Duncan
//StartWindow.java

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
// import mazeGenerator.castleFrames;

public class StartWindow extends JFrame {
	
	private int diff = 1;
	private int size = 10;
	private boolean close; 
	
	private JComboBox diffOption;
	private JTextComponent sizeOption;
	
	private JButton begin;
	
	private JPanel mainPanel;
	private JPanel optionPanel;
	private JPanel picPanel;
	private JPanel buttonPanel;

	private JGif mainGif;
	
	public StartWindow() {	
		close = false;
		
		// set gif
		mainGif = new JGif(6);
		for(int i = 0; i < 40; i++) {
			String picName = "assets/Castle/castle_";
			if(i < 10)
				picName += "0";
			picName += i + ".png";
			mainGif.addFrame(picName);
		}
		
		//difficulty selector
		diffOption = new JComboBox();
		diffOption.addItem("Easy");
		diffOption.addItem("Medium");
		diffOption.addItem("Hard");
			
		//set up myGL
		GridLayout myGL = new GridLayout(1,4);
		myGL.setHgap(10);
				
		//set up buttonPanel
		buttonPanel = new JPanel(myGL);
		begin = new JButton("Begin");
		begin.setPreferredSize(new Dimension(30, 30));
		buttonPanel.add(new JLabel(""));
		buttonPanel.add(begin);
		buttonPanel.add(new JLabel(""));
		
		//set up optionPanel
		sizeOption = new JTextField(Integer.toString(size));
		
		optionPanel = new JPanel(myGL);
		optionPanel.add(new JLabel(""));
		optionPanel.add(sizeOption);
		optionPanel.add(diffOption);
		optionPanel.add(new JLabel(""));
		
		//set up mainPanel
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(mainGif, BorderLayout.NORTH);
		mainPanel.add(optionPanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		this.add(mainPanel);
		
		setTitle("The Maze Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(250, 250);
		setVisible(true);
		setDefaultLookAndFeelDecorated(false);
		setBackground(new Color(10, 89, 160));
				
		diffOption.addActionListener(new beginListener());
				
		this.setSize(400,500);
	}
	
	public JButton getBeginBtn() {
		return begin;
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

	private class beginListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String op;
			try {
				size = Integer.parseInt(sizeOption.getText());
				if(diffOption.getSelectedItem() instanceof String) {
					op = (String) diffOption.getSelectedItem();
				
					if (op.equals("Easy"))
						diff = 1;
					else if (op.equals("Medium"))
						diff = 2;
					else if (op.equals("Hard"))
						diff = 3;
				}
			} catch(Exception ea) {
				
			}
		}
	}
}
		