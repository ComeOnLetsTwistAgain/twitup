package com.iup.tp.twitup.ihm;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
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
	
	ImageIcon img;
	JLabel labelimg;
	
	JButton buttonSubmit; 
	JButton buttonChangeAvatar; 
	
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
			
			img = new ImageIcon(t.getCurrentUser().getAvatarPath());
			
			Image i = img.getImage();
			BufferedImage bi = new BufferedImage(i.getWidth(null), i.getHeight(null), BufferedImage.TYPE_INT_ARGB);
			Graphics g = bi.createGraphics();
			g.drawImage(i, 0, 0, img.getIconWidth()/2, img.getIconHeight()/2, null, null);
			img = new ImageIcon(bi);
			
			labelimg = new JLabel(fieldAvatar.getText(), img, JLabel.CENTER);
			
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
			
			buttonChangeAvatar = new JButton(new AbstractAction("changer l'image"){

				@Override
				public void actionPerformed(ActionEvent e) {
					modifierAvatar();
					
				}
				
			});
			
	        
			this.add(header, c);
			
			this.add(username, c);
			this.add(fieldUsername, c);
			
			this.add(password, c);
			this.add(fieldPassword, c);
			
			this.add(avatar, c);
			this.add(fieldAvatar, c);
			this.add(labelimg, c);
			this.add(buttonChangeAvatar, c);
			
			this.add(buttonSubmit, c);
		} else {
			this.add(errorNotConnected, c);
		}
		
		this.setVisible(true);
	}
	
	private void modifierProfil(){
		controller.modifierProfil(this);
	}
	
	private void modifierAvatar(){
		controller.modifierAvatar(this);
	}


	public ConsulterProfilController getController() {
		return controller;
	}


	public void setController(ConsulterProfilController controller) {
		this.controller = controller;
	}


	public Twitup getT() {
		return t;
	}


	public void setT(Twitup t) {
		this.t = t;
	}


	public JLabel getErrorNotConnected() {
		return errorNotConnected;
	}


	public void setErrorNotConnected(JLabel errorNotConnected) {
		this.errorNotConnected = errorNotConnected;
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


	public JLabel getAvatar() {
		return avatar;
	}


	public void setAvatar(JLabel avatar) {
		this.avatar = avatar;
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


	public JTextField getFieldAvatar() {
		return fieldAvatar;
	}


	public void setFieldAvatar(JTextField fieldAvatar) {
		this.fieldAvatar = fieldAvatar;
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
