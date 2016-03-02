package com.iup.tp.twitup.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class ConnexionCompteView extends JPanel{
	
	JLabel header;
	JLabel username;
	JLabel password;
	JTextField fieldUsername;
	JTextField fieldPassword;
	
	JButton buttonSubmit; 
	
	public ConnexionCompteView(){
		initGUI();
	}
	
	private void initGUI(){
this.setLayout(new GridBagLayout());
		
		header = new JLabel("Connexion");
		username = new JLabel("Nom d'utilisateur");
		password = new JLabel("Mot de passe");
		header.setHorizontalAlignment(JTextField.CENTER);
		
		fieldUsername = new JTextField();
		fieldPassword = new JPasswordField();
		
		buttonSubmit = new JButton( new AbstractAction("Connexion"){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Connexion au compte : " + fieldUsername.getText() + " | " + fieldPassword.getText());
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
