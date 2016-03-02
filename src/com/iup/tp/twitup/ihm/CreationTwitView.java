package com.iup.tp.twitup.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.UUID;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.iup.tp.twitup.datamodel.Database;
import com.iup.tp.twitup.datamodel.User;

public class CreationTwitView extends JPanel {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Database db;
	
	JLabel header;
	JLabel twitLabel;
	JTextField twitContent;
	
	JButton buttonSubmit; 
	
	
	public CreationTwitView(Database db){
		this.db = db;
		this.initGUI();
	}
	
	private void initGUI(){
		this.setLayout(new GridBagLayout());
		
		header = new JLabel("Ecrire un nouveau twit ");
		twitLabel = new JLabel("");
		header.setHorizontalAlignment(JTextField.CENTER);
		
		twitContent = new JTextField();
		
		buttonSubmit = new JButton( new AbstractAction("Twiter !"){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Twit créé ! ");
			}
		});
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weightx = 0;
        c.gridwidth = GridBagConstraints.REMAINDER;
		
		
		this.add(header, c);
		
		this.add(twitLabel, c);
		c.ipady = 40;
		c.ipady = 0;
		this.add(twitContent, c);
		
		this.add(buttonSubmit, c);
		
		
		this.setVisible(true);
	}

}
