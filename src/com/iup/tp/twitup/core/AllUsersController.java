package com.iup.tp.twitup.core;

import com.iup.tp.twitup.datamodel.IDatabase;

public class AllUsersController {
	
	IDatabase db;
	EntityManager em;
	Twitup t;

	public AllUsersController(IDatabase db, EntityManager em, Twitup twitup){
		this.db = db;
		this.em = em;
		this.t = twitup;
	}

}
