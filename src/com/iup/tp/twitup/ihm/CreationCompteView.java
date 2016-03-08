package com.iup.tp.twitup.ihm;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.UUID;

import javax.swing.*;

import com.iup.tp.twitup.core.ConnexionController;
import com.iup.tp.twitup.core.CreationCompteController;
import com.iup.tp.twitup.core.Twitup;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;

public class CreationCompteView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	CreationCompteController controller;
	Twitup t;

	JLabel header;
	JLabel username;
	JLabel password;
	JTextField fieldUsername;
	JTextField fieldPassword;

	JButton buttonSubmit; 


	public CreationCompteView(CreationCompteController controller, Twitup t){
		this.controller = controller;
		this.t = t;
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

				createCompte();
				
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
	
	private void createCompte(){
		controller.createCompte(this);
	}

	public JLabel getHeader() {
		return header;
	}

	public void setHeader(JLabel header) {
		this.header = header;
	}

	public JLabel getUsername() {
		return username;
	}

	public void setUsername(JLabel username) {
		this.username = username;
	}

	public JLabel getPassword() {
		return password;
	}

	public void setPassword(JLabel password) {
		this.password = password;
	}

	public JTextField getFieldUsername() {
		return fieldUsername;
	}

	public void setFieldUsername(JTextField fieldUsername) {
		this.fieldUsername = fieldUsername;
	}

	public JTextField getFieldPassword() {
		return fieldPassword;
	}

	public void setFieldPassword(JTextField fieldPassword) {
		this.fieldPassword = fieldPassword;
	}

	public JButton getButtonSubmit() {
		return buttonSubmit;
	}

	public void setButtonSubmit(JButton buttonSubmit) {
		this.buttonSubmit = buttonSubmit;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
