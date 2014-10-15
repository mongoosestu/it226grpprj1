import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GroceryListFrame extends JFrame implements ActionListener {

	private JPanel mainPanel;
	private JPanel optionsPanel;
	private JButton newListButton;
	private JButton importListButton;
	private JButton addItemButton;
	private JButton saveListButton;
	//private GroceryList list;
	
	protected static String itemName;
	protected static int quantity;
	protected static String units;
	
	public GroceryListFrame() {
		
		//initial set up of the frame
		super();
		setSize(600,700);
		setTitle("Grocery List");
		
		//creates main panel
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(mainPanel);
	
		//creates the options panel
		optionsPanel = new JPanel();
		optionsPanel.setLayout(new FlowLayout());
		optionsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		//creates the new list button
		newListButton = new JButton("New List");
		newListButton.setActionCommand("newlist");
		newListButton.addActionListener(this);
		optionsPanel.add(newListButton);
		
		//creates the import list button
		importListButton = new JButton("Import List");
		importListButton.setActionCommand("importlist");
		importListButton.addActionListener(this);
		optionsPanel.add(importListButton);
		
		//creates the add item button
		addItemButton = new JButton("Add Item");
		addItemButton.setActionCommand("additem");
		addItemButton.addActionListener(this);
		optionsPanel.add(addItemButton);
		
		//creates the save list button
		saveListButton = new JButton("Save List");
		saveListButton.setActionCommand("savelist");
		saveListButton.addActionListener(this);
		optionsPanel.add(saveListButton);
		
		//adds optionsPanel to south
		mainPanel.add(optionsPanel, BorderLayout.SOUTH);
	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String who = arg0.getActionCommand();
		switch (who) {
			
			case "newlist" :
			break;
		
			case "importlist" :
			break;
			
			case "additem" :
				WordFrame test = new WordFrame();
				test.setVisible(true);
			case "savelist" :
			break;
		}
		
	}
}
