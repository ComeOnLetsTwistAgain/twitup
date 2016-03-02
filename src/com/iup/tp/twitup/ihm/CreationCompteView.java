package com.iup.tp.twitup.ihm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.UUID;

import javax.swing.*;

import com.iup.tp.twitup.datamodel.Database;
import com.iup.tp.twitup.datamodel.User;

public class CreationCompteView extends JPanel {
	
	Database db;
	
	JLabel header;
	JLabel username;
	JLabel password;
	JTextField fieldUsername;
	JTextField fieldPassword;
	
	JButton buttonSubmit; 
	
	
	public CreationCompteView(Database db){
		this.db = db;
		this.initGUI();
	}
	
	private void initGUI(){
		this.setLayout(new GridBagLayout());
		
		header = new JLabel("Création d'un compte");
		username = new JLabel("Nom d'utilisateur");
		password = new JLabel("Mot de passe");
		header.setHorizontalAlignment(JTextField.CENTER);
		
		fieldUsername = new JTextField();
		fieldPassword = new JPasswordField();
		
		buttonSubmit = new JButton( new AbstractAction("Créer le compte"){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Création du compte : " + fieldUsername.getText() + " | " + fieldPassword.getText());
				
				User newUser = new User(UUID.randomUUID(), fieldUsername.getText(), "--", fieldUsername.getText(), new HashSet<String>(), "");
				db.addUser(user);
			}
		});
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weightx = 0;
        c.gridwidth = GridBagConstraints.REMAINDER;
		
		
		this.add(header, c);
		
		this.add(username, c);
		this.add(fieldUsername, c);
		
		this.add(password, c);
		this.add(fieldPassword, c);
		
		this.add(buttonSubmit, c);
		
		
		this.setVisible(true);
	}

}
