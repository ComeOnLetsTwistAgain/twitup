package com.iup.tp.twitup.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.UUID;

import javax.swing.*;

import com.iup.tp.twitup.core.Twitup;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;

public class ConnexionCompteView extends JPanel{
	
	IDatabase db;
	Twitup t;
	
	JLabel header;
	JLabel username;
	JLabel password;
	JTextField fieldUsername;
	JTextField fieldPassword;
	
	JButton buttonSubmit; 
	
	public ConnexionCompteView(IDatabase db, Twitup t){
		this.db = db;
		this.t=t;
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
				
				boolean inputNotValid = fieldUsername.getText().equals("") || fieldPassword.getText().equals("");
				boolean isUserNotValid = isUserNotValid(fieldUsername.getText());
				
				if(inputNotValid){
					System.err.println("[AUTH ERR] - Les champs doivent être renseignés");
				} else if(isUserNotValid){
					System.err.println("[AUTH ERR] - Le mot de passe ou l'utilisateur est invalide");
				} 
				else {
					User user = getUserByTag(fieldUsername.getText());
					if(user != null){
						System.out.println("Connecté au compte : " + user.getUserTag());
						t.setCurrentUser(user);
					}
						
						
				}
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
	
	private boolean isUserNotValid(String tag){
		for(User u : db.getUsers())
			if(u.getUserTag().equals(tag))
				return false;
		return true;
	}
	
	private User getUserByTag(String tag){
		for(User u : db.getUsers())
			if(u.getUserTag().equals(tag))
				return u;
		return null;
	}

}
