package com.iup.tp.twitup.core;

import java.util.HashSet;
import java.util.UUID;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.CreationCompteView;

public class CreationCompteController {
	IDatabase db;
	EntityManager em;
	Twitup t;

	public CreationCompteController(IDatabase db, EntityManager em, Twitup t){
		this.db = db;
		this.em = em;
		this.t = t;
	}

	public void createCompte(CreationCompteView v){

		boolean inputNotValid = v.getFieldUsername().getText().equals("") || v.getFieldPassword().getText().equals("");
		boolean isTagUnique = isTagUnique(v.getUsername().getText());

		if(inputNotValid){
			System.err.println("[AUTH ERR] - Les champs doivent être renseignés");
			v.getError().setText("Les champs doivent être renseignés");
			v.getError().setVisible(true);
		} else if(isTagUnique) {
			System.err.println("[AUTH ERR] - Le tag existe déjà");
			v.getError().setText("Le tag existe déjà");
			v.getError().setVisible(true);
		} else {

			User user = new User(
					UUID.randomUUID(), //uuid
					v.getFieldTag().getText(), //tag
					v.getFieldPassword().getText(), //pass
					v.getFieldUsername().getText(), //name
					new HashSet<String>(), //follows
					"src/resources/images/default_user.png" //avatar path par défault
					); 
			em.sendUser(user);
			
			// redirection 
			this.t.switchToConnexion();
		}
	}

	private boolean isTagUnique(String tag){
		for(User u : db.getUsers())
			if(u.getUserTag().equals(tag))
				return true;
		return false;
	}
}
