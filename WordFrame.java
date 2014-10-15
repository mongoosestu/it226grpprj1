import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;



public class WordFrame extends JFrame 
{
	JList words;
	AutoCompleteTextField itemname;
	JTextField quantity;
	JTextField units;
	
	String string1;
	String string2;
	String string3;
	
	public WordFrame()
	{
		super();
		this.setSize(200,150); //set to whatever you want
		this.setBackground(Color.WHITE);
		this.setTitle("Add Item");
		this.setLayout(new BorderLayout());
		JPanel donePanel = new JPanel();
		donePanel.setLayout(new FlowLayout());
		this.add(donePanel,BorderLayout.SOUTH);

		//add the done button
		JButton doneButton = new JButton("Done");
		doneButton.addActionListener(new ButtonListener());
		doneButton.setActionCommand("Done button");
		donePanel.add(doneButton);
		
		//the text fields
		quantity = new JTextField("Quantity",15);
		units = new JTextField("Units",15);
		itemname = new AutoCompleteTextField("Item Name",15);
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BoxLayout(textPanel,BoxLayout.Y_AXIS));
		
		//Heres where you add the array of words to use for autocomplete
		String[] words = {"abcdefghijklmnop","two","three","four","fork","five","apple","orange","potato"};
		itemname.addWords(words);
		
		textPanel.add(itemname);
		textPanel.add(quantity);
		textPanel.add(units);
		this.add(textPanel,BorderLayout.NORTH);
	
	}
	
	
	private class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (e.getActionCommand().equals("Done button"))
			{
				GroceryListFrame.itemName = itemname.getText();
				GroceryListFrame.quantity = Integer.parseInt(quantity.getText());
				GroceryListFrame.units = units.getText();	
				dispose();
			}
		}
	}
		public static void main(String []args)
	{
		WordFrame frame = new WordFrame();
		frame.setVisible(true);
	}
}
