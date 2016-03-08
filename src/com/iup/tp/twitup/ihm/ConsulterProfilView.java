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

import com.iup.tp.twitup.core.ConsulterProfilController;
import com.iup.tp.twitup.core.Twitup;

public class ConsulterProfilView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ConsulterProfilController controller;
	Twitup t;
	
	JLabel errorNotConnected;
	
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
	
	
	public void initGUI(){
		this.setLayout(new GridBagLayout());
		
		errorNotConnected = new JLabel("Vous n'êtes pas connecté");
	
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weightx = 0;
        c.gridwidth = GridBagConstraints.REMAINDER;
		
		if(controller.getCurrentUser() != null){
			header = new JLabel("Mon profil");
			username = new JLabel("Nom d'utilisateur");
			password = new JLabel("Mot de passe");
			avatar = new JLabel("Chemin de l'avatar");
			header.setHorizontalAlignment(JTextField.CENTER);
			
			fieldUsername = new JTextField(t.getCurrentUser().getName());
			fieldPassword = new JPasswordField(t.getCurrentUser().getUserPassword());
			fieldAvatar = new JTextField(t.getCurrentUser().getAvatarPath());
			
			buttonSubmit = new JButton( new AbstractAction("Modifier"){
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void actionPerformed(ActionEvent e) {
					modifierProfil();
					
				}
			});
			
			
	        
	        
			this.add(header, c);
			
			this.add(username, c);
			this.add(fieldUsername, c);
			
			this.add(password, c);
			this.add(fieldPassword, c);
			
			this.add(avatar, c);
			this.add(fieldAvatar, c);
			
			this.add(buttonSubmit, c);
		} else {
			this.add(errorNotConnected, c);
		}
		
		
		
		this.setVisible(true);
	}
	
	private void modifierProfil(){
		controller.modifierProfil(this);
	}
	

}
