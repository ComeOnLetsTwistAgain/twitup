package com.iup.tp.twitup.core;

import com.iup.tp.twitup.datamodel.Database;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.ConnexionCompteView;

public class ConnexionController {

	IDatabase db;

	public ConnexionController(IDatabase db){
		this.db = db;
	}


	public void connexion(ConnexionCompteView v){
		boolean inputNotValid = v.getFieldUsername().getText().equals("") || v.getFieldPassword().getText().equals("");
		boolean isUserNotValid = isUserNotValid(v.getFieldUsername().getText());

		if(inputNotValid){
			System.err.println("[AUTH ERR] - Les champs doivent être renseignés");
		} else if(isUserNotValid){
			System.err.println("[AUTH ERR] - Le mot de passe ou l'utilisateur est invalide");
		} 
		else {
			User user = getUserByTag(v.getFieldUsername().getText());
			if(user != null){
				System.out.println("Connecté au compte : " + user.getUserTag());
				v.getT().setCurrentUser(user);
			}


		}
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
