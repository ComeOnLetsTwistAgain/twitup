package com.iup.tp.twitup.core;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;

public class AllUsersController implements IDatabaseObserver {

	IDatabase db;
	EntityManager em;
	Twitup t;

	public static String mentionRegex = "^@\\w+|\\s@\\w+";
	public static Pattern mentionPattern = Pattern.compile(mentionRegex);

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


	public void rechercheUser(String texte)
	{
		Set<User> allUsers = this.db.getUsers();
		Set<User> res = new  HashSet<User>();
		
		System.out.println("alluser size "+allUsers.size());

		// la recherche a été initiée on vide le panel actuel
		viderView();

		/*
		 *  si la recherche est vide on affiche tout les users
		 *  sinon on fait la recherche sur les personnes qui nous interessent
		 */		
		if (texte.equals(""))
		{
			afficherUserToView(allUsers);
			System.out.println("recherche user vide");
		}else{

			// matcher sur les mentions
			Matcher matchMention = mentionPattern.matcher(texte);

			// si la recherche est une mention
			if (matchMention.find())
			{
				System.out.println("mention detectée : "+texte);
				texte = texte.replace("@", "");
				System.out.println("Suppression du @ et recherche sur "+texte);

				// ajout de tout les users 
				for(User u : allUsers){
					if(u.getUserTag().equals(texte))
						res.add(u);
				}

			} else if (! matchMention.find()){
				System.out.println("recherche globale");
				// ajout de tout les users 
				for(User u : allUsers){
					if(u.getUserTag().equals(texte))
						res.add(u);
				}
			} else {
				System.out.println("rien trouvé");
			}


			afficherUserToView(res);
		}
	}



	/*
	 * affiche les users de la recherche
	 */
	public void afficherUserToView(Set<User> listeUsers){

		Iterator<User> iterator = listeUsers.iterator();
		System.out.println("Résultat de la recherche : ");
		while (iterator.hasNext()) {
			User u = iterator.next();
			t.getAllUsersView().addUserToView(u);
		}
	}

	/*
	 * vide le panel de la vue user
	 */
	public void viderView()
	{
		t.getAllUsersView().viderPanel();
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
