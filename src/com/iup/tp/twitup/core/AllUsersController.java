package com.iup.tp.twitup.core;

import java.util.Set;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;

public class AllUsersController {
	
	IDatabase db;
	EntityManager em;
	Twitup t;

	public AllUsersController(IDatabase db, EntityManager em, Twitup twitup){
		this.db = db;
		this.em = em;
		this.t = twitup;
	}
	
	public Set<User> getUsers(){
		return db.getUsers();
	}

}
