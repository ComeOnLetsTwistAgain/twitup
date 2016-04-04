package com.iup.tp.twitup.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.CreationTwitView;

public class CreationTwitController {

	IDatabase db;
	Twitup t;
	EntityManager em;

	public CreationTwitController(IDatabase db, Twitup twitup, EntityManager em)
	{
		this.db = db;
		this.t = twitup;
		this.em = em;
	}

	public void creationTwit(CreationTwitView v)
	{
		User user = t.getCurrentUser();

		if (user != null)
		{

			long millis = System.currentTimeMillis();		
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy HH:mm");
			Date date = new Date();
			
			Twit twit = new Twit(
					UUID.randomUUID(), 
					user, 
					millis, 
					v.getTwitContent().getText() 
					);
			this.em.sendTwit(twit);
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

}