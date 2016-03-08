package com.iup.tp.twitup.core;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;

public class ConsulterProfilController {
	IDatabase db;
	EntityManager em;
	Twitup t;
	
	public ConsulterProfilController(IDatabase db, EntityManager em, Twitup twitup){
		this.db = db;
		this.em = em;
		this.t = twitup;
	}
	
	public User getCurrentUser(){
		return t.getCurrentUser();
	}
	
	
}
