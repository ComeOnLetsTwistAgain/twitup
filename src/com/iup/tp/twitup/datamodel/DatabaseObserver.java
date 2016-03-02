package com.iup.tp.twitup.datamodel;

import java.util.Set;

import com.iup.tp.twitup.ihm.TwitupMainView;

public class DatabaseObserver implements IDatabaseObserver {
	
	private IDatabase d;
	private TwitupMainView view;
	
	public DatabaseObserver(IDatabase d, TwitupMainView v){
		this.d = d;
		this.view = v;
	}

	@Override
	public void notifyTwitAdded(Twit addedTwit) {
		System.out.println("twit added : " + addedTwit);
	}

	@Override
	public void notifyTwitDeleted(Twit deletedTwit) {
		System.out.println("twit deleted : " + deletedTwit);
		
	}

	@Override
	public void notifyTwitModified(Twit modifiedTwit) {
		System.out.println("twit modified : " + modifiedTwit);
		
	}

	@Override
	public void notifyUserAdded(User addedUser) {
		System.out.println("user added : " + addedUser);
		
	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		System.out.println("user modified : " + deletedUser);
		
	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		System.out.println("user modified : " + modifiedUser);
		
	}

}
