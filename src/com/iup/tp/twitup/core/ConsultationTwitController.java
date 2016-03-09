package com.iup.tp.twitup.core;

import java.util.Set;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.Twit;

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
	
		return this.db.getTwits();
	}

}
