package com.iup.tp.twitup.ihm;

import java.awt.Color;
import java.awt.Event;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.UUID;

import javax.swing.*;

import com.iup.tp.twitup.core.ConnexionController;
import com.iup.tp.twitup.core.Twitup;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;

public class ConnexionCompteView extends JPanel{
	
	ConnexionController controller;
	Twitup t;
	
	JLabel header;
	JLabel username;
	JLabel password;
	JTextField fieldUsername;
	JTextField fieldPassword;
	
	JLabel error;
	
	JButton buttonSubmit; 
	
	public ConnexionCompteView(ConnexionController controller, Twitup t){
		this.controller = controller;
		this.t = t;
		initGUI();
	}
	
	private void initGUI(){
		this.setLayout(new GridBagLayout());
		
		header = new JLabel("Connexion");
		username = new JLabel("Nom d'utilisateur");
		password = new JLabel("Mot de passe");
		error = new JLabel();
		
		error.setVisible(false);
		error.setForeground(Color.red);
		
		header.setHorizontalAlignment(JTextField.CENTER);
		
		fieldUsername = new JTextField();
		fieldPassword = new JPasswordField();
		
		fieldPassword.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == Event.ENTER){
					connexion();	
				}
			}
		});
		
		buttonSubmit = new JButton( new AbstractAction("Connexion"){
			@Override
			public void actionPerformed(ActionEvent e) {
				connexion();
				
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
		
		this.add(error, c);
		
		
		this.setVisible(true);
	}
	
	private void connexion(){
		controller.connexion(this);
	}

	public ConnexionController getController() {
		return controller;
	}

	public void setController(ConnexionController controller) {
		this.controller = controller;
	}

	public Twitup getT() {
		return t;
	}

	public void setT(Twitup t) {
		this.t = t;
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

	public JLabel getError() {
		return error;
	}

	public void setError(JLabel error) {
		this.error = error;
	}
	
	
	
	
	

}
