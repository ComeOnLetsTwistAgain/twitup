package com.iup.tp.twitup.core;

import java.util.Set;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;

public class ConsultationTwitController implements IDatabaseObserver {

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

	@Override
	public void notifyTwitAdded(Twit addedTwit) {
		// TODO Auto-generated method stub
		System.out.println("Ajouter un twit dans la liste");
		t.getConsultationTwitView().addTwitToView(addedTwit);
		
		t.getConsultationTwitView().getParent().revalidate();
		t.getConsultationTwitView().getParent().repaint();
	}


	@Override
	public void notifyTwitDeleted(Twit deletedTwit) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void notifyTwitModified(Twit modifiedTwit) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void notifyUserAdded(User addedUser) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void notifyUserDeleted(User deletedUser) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void notifyUserModified(User modifiedUser) {
		// TODO Auto-generated method stub
		
	}

}
