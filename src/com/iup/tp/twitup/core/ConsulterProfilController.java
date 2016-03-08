package com.iup.tp.twitup.core;

import java.io.File;

import javax.swing.JFileChooser;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.ConsulterProfilView;

public class ConsulterProfilController {
	IDatabase db;
	EntityManager em;
	Twitup t;
	
	public ConsulterProfilController(IDatabase db, EntityManager em, Twitup twitup){
		this.db = db;
		this.em = em;
		this.t = twitup;
	}
	
	public User getCurrentUser(){
		return t.getCurrentUser();
	}
	
	public void modifierProfil(ConsulterProfilView v){
		
		User userToModify = new User(
				t.getCurrentUser().getUuid(),
				v.getFieldUsername().getText(), //tag
				v.getFieldPassword().getText(), //pass
				v.getFieldUsername().getText(), //name
				t.getCurrentUser().getFollows(),
				v.getFieldAvatar().getText()
				);
		
		em.sendUser(userToModify);
		
		t.setCurrentUser(userToModify);
		
	}
	
	public void modifierAvatar(ConsulterProfilView v){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Choisir l'image");
		fileChooser.setFileSelectionMode( JFileChooser.FILES_ONLY);
		int result = fileChooser.showOpenDialog(t.getmMainView().getFrame());
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    
		    v.getFieldAvatar().setText(selectedFile.getAbsolutePath());
		    
		    v.initGUI();
		    
		}
	}
	
	
}
