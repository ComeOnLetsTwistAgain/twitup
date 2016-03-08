package com.iup.tp.twitup.core;

import java.util.UUID;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.CreationTwitView;

public class CreationTwitController {

	IDatabase db;
	Twitup t;
	
	public CreationTwitController(IDatabase db, Twitup twitup)
	{
		this.db = db;
		this.t = twitup;
	}
	
	public void creationTwit(CreationTwitView v)
	{
		User user = t.getCurrentUser();

		if (user != null)
		{

			long millis = System.currentTimeMillis();					
			Twit twit = new Twit(UUID.randomUUID(), user, millis, v.getTwitContent().getText() );
			db.addTwit(twit);
			System.out.println("Création du twit");

		}else{
			System.out.println("Pas de current user");
		}
	}
	
	public void afficherTwits()
	{
		User user = t.getCurrentUser();
		
		for (Twit toto:db.getTwitsWithUserTag(user.getUserTag()))
		{
			System.out.println("Twit : "+toto.getText());
		}
	}
	
	
	/*
	 * 
	
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

	 */
}
