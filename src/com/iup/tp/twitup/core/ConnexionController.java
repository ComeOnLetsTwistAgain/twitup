package com.iup.tp.twitup.core;

import com.iup.tp.twitup.datamodel.Database;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.ConnexionCompteView;

public class ConnexionController {

	IDatabase db;
	EntityManager em;
	Twitup t;
	
	

	public ConnexionController(IDatabase db, EntityManager em, Twitup twitup){
		this.db = db;
		this.em = em;
		this.t = twitup;
	}


	public void connexion(ConnexionCompteView v){
		boolean inputNotValid = v.getFieldUsername().getText().equals("") || v.getFieldPassword().getText().equals("");
		boolean isUserNotValid = isUserNotValid(v.getFieldUsername().getText());

		if(inputNotValid){
			System.err.println("[AUTH ERR] - Les champs doivent être renseignés");
			v.getError().setText("Les champs doivent être renseignés");
			v.getError().setVisible(true);
		} else if(isUserNotValid){
			System.err.println("[AUTH ERR] - Le mot de passe ou l'utilisateur est invalide");
			v.getError().setText("Le mot de passe ou l'utilisateur est invalide");
			v.getError().setVisible(true);
		} 
		else {
			User user = getUserByTag(v.getFieldUsername().getText());
			if(user != null){
				System.out.println("Connecté au compte : " + user.getUserTag());
				v.getT().setCurrentUser(user);
				v.getT().afterConnexion();
				t.switchToConsultationTwit();
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
