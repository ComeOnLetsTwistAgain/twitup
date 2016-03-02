package com.iup.tp.twitup.ihm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.UUID;

import javax.swing.*;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;

public class CreationCompteView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	IDatabase db;

	JLabel header;
	JLabel username;
	JLabel password;
	JTextField fieldUsername;
	JTextField fieldPassword;

	JButton buttonSubmit; 


	public CreationCompteView(IDatabase db){
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
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {

				boolean inputNotValid = fieldUsername.getText().equals("") || fieldPassword.getText().equals("");
				boolean isTagUnique = isTagUnique(fieldUsername.getText());
				
				if(inputNotValid){
					System.err.println("[AUTH ERR] - Les champs doivent être renseignés");
				} else if(isTagUnique) {
					System.err.println("[AUTH ERR] - Le tag existe déjà");
				}
				else {
					
					User user = new User(
											UUID.randomUUID(), //uuid
											fieldUsername.getText(), //tag
											fieldPassword.getText(), //pass
											fieldUsername.getText(), //name
											new HashSet<String>(), //follows
											"" //avatar path TODO
										); 
					db.addUser(user);
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
	
	
	private boolean isTagUnique(String tag){
		for(User u : db.getUsers())
			if(u.getUserTag().equals(tag))
				return true;
		return false;
	}

}
