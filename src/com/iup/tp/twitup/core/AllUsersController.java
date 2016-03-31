package com.iup.tp.twitup.core;

import java.util.Set;

import javax.swing.JPanel;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.ihm.ComponentUser;

public class AllUsersController implements IDatabaseObserver {
	
	IDatabase db;
	EntityManager em;
	Twitup t;

	public AllUsersController(IDatabase db, EntityManager em, Twitup twitup){
		this.db = db;
		this.em = em;
		this.t = twitup;
		
		
		//System.out.println("following : " + t.getCurrentUser().getFollows());
	}
	
	public Set<User> getUsers(){
		return db.getUsers();
	}
	
	
	
	public void follow(User u){
		if(t.getCurrentUser() != null){
			t.getCurrentUser().addFollowing(u.getUserTag());
			em.sendUser(t.getCurrentUser());
			
			
			System.out.println("following : " + t.getCurrentUser().getFollows());
		} else {
			System.out.println("vous n'êtes pas connecté");
		}
	}
	
	public void unfollow(User u){
		if(t.getCurrentUser() != null){
			t.getCurrentUser().removeFollowing(u.getUserTag());
			em.sendUser(t.getCurrentUser());
			t.getmMainView().getFrame().repaint();
			
			System.out.println("following : " + t.getCurrentUser().getFollows());
		} else {
			System.out.println("Vous n'êtes pas connecté");
		}
	}

	@Override
	public void notifyTwitAdded(Twit addedTwit) {
		// TODO Auto-generated method stub
		
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
		
		t.getAllUsersView().addUserToView(addedUser);
		
		t.getAllUsersView().getParent().revalidate();
		t.getAllUsersView().getParent().repaint();
	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		// TODO Auto-generated method stub
		
	}

	public IDatabase getDb() {
		return db;
	}

	public void setDb(IDatabase db) {
		this.db = db;
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public Twitup getT() {
		return t;
	}

	public void setT(Twitup t) {
		this.t = t;
	}
	
	

}
