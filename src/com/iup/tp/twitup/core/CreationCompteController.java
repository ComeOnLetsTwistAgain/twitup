package com.iup.tp.twitup.core;

import java.util.HashSet;
import java.util.UUID;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.CreationCompteView;

public class CreationCompteController {
	IDatabase db;
	
	public CreationCompteController(IDatabase db){
		this.db = db;
	}
	
	public void createCompte(CreationCompteView v){
		
		boolean inputNotValid = v.getFieldUsername().getText().equals("") || v.getFieldPassword().getText().equals("");
		boolean isTagUnique = isTagUnique(v.getUsername().getText());
		
		if(inputNotValid){
			System.err.println("[AUTH ERR] - Les champs doivent être renseignés");
		} else if(isTagUnique) {
			System.err.println("[AUTH ERR] - Le tag existe déjà");
		}
		else {
			
			User user = new User(
									UUID.randomUUID(), //uuid
									v.getFieldUsername().getText(), //tag
									v.getFieldPassword().getText(), //pass
									v.getFieldUsername().getText(), //name
									new HashSet<String>(), //follows
									"" //avatar path TODO
								); 
			db.addUser(user);
		}
		
	}
	
	private boolean isTagUnique(String tag){
		for(User u : db.getUsers())
			if(u.getUserTag().equals(tag))
				return true;
		return false;
	}
}
