package com.iup.tp.twitup.core;

import com.iup.tp.twitup.datamodel.IDatabase;

public class ConsulterProfilController {
	IDatabase db;
	EntityManager em;
	
	public ConsulterProfilController(IDatabase db, EntityManager em){
		this.db = db;
		this.em = em;
	}
	
	
}
