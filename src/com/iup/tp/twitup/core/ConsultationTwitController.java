package com.iup.tp.twitup.core;

import java.util.Set;
import java.util.UUID;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;

public class ConsultationTwitController {

	IDatabase db;
	EntityManager em;
	Twitup t;

	public ConsultationTwitController(IDatabase db, EntityManager em, Twitup twitup){
		this.db = db;
		this.em = em;
		this.t = twitup;
	}


	public Set<Twit> getTwits()
	{
		User user = t.getCurrentUser();
		Set<Twit> liste = null;
		if (user != null)
		{
			liste = this.db.getTwits();
			if (liste != null)
			{
				System.out.println("La liste contient des twits ");
			} else {
				System.out.println("La liste ne contient pas de twits ");
			}
			

		}else{
			System.out.println("Vous n'êtes pas connectés ");
		}		
		return liste;
	}

}
