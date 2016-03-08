package com.iup.tp.twitup.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.iup.tp.twitup.core.ConnexionController;
import com.iup.tp.twitup.core.ConsulterProfilController;
import com.iup.tp.twitup.core.Twitup;

public class ConsulterProfilView extends JPanel {
	
	ConsulterProfilController controller;
	Twitup t;
	
	JLabel header;
	JLabel username;
	JLabel avatar;
	JLabel password;
	JTextField fieldUsername;
	JTextField fieldPassword;
	JTextField fieldAvatar;
	
	JButton buttonSubmit; 
	
	public ConsulterProfilView(	ConsulterProfilController controller, Twitup t){
		this.controller = controller;
		this.t = t;
		initGUI();
	}
	
	private void initGUI(){
this.setLayout(new GridBagLayout());
		
		header = new JLabel("Mon profil");
		username = new JLabel("Nom d'utilisateur");
		password = new JLabel("Mot de passe");
		avatar = new JLabel("Chemin de l'avatar");
		header.setHorizontalAlignment(JTextField.CENTER);
		
		fieldUsername = new JTextField();
		fieldPassword = new JPasswordField();
		
		buttonSubmit = new JButton( new AbstractAction("Modifier"){
			@Override
			public void actionPerformed(ActionEvent e) {
				//connexion();
				
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
