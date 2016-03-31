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

public class ConsultationTwitController implements IDatabaseObserver {

	IDatabase db;
	EntityManager em;
	Twitup t;

	// pattern pour la recherche
	public static String hashtagRegex = "^#\\w+|\\s#\\w+";
	public static Pattern hashtagPattern = Pattern.compile(hashtagRegex);

	public static String mentionRegex = "^@\\w+|\\s@\\w+";
	public static Pattern mentionPattern = Pattern.compile(mentionRegex);

	//	public static String urlRegex = "http+://[\\S]+|https+://[\\S]+";
	//	public static Pattern urlPattern = Pattern.compile(urlRegex);

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
		System.out.println("Suppresion d'un twit");
	}


	@Override
	public void notifyTwitModified(Twit modifiedTwit) {
		// TODO Auto-generated method stub
		System.out.println("Twit modifié");
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

	public void rechercheTwit(String texte)
	{
		Set<Twit> allTwits = this.db.getTwits();
		Set<Twit> res = new  HashSet<Twit>();
		
		// la recherche a été initiée on vide le panel actuel
		viderView();

		/*
		 *  si la recherche est vide on affiche tout les twits
		 *  sinon on fait la recherche et on sort la liste de twits qui nous interessent
		 */
		
		if (texte == null)
		{
			afficherTwitsToView(allTwits);
		}else{
			Iterator<Twit> iterator = allTwits.iterator();
			while (iterator.hasNext()) {
				Twit element = iterator.next();
				
				/* faire la recherche ici */
				
				if (element.getText().equals(texte)){
					System.out.println("element ajouté = " + element);	
					res.add(element);				
				} 		
			}	
			
			afficherTwitsToView(res);
		}
	}


	/*
	 *  rechercher un hashtag
	 */
	public Set<Twit> getHashtag(Set<Twit> res, String hashtag) {

		Matcher matcher = hashtagPattern.matcher(hashtag);

		if ( matcher.find() ) {
			System.out.println("hashtag detecté, retour de la liste avec "+hashtag);
			res = this.db.getTwitsWithTag(hashtag);		
		}

		return res;
	}

	// rechercher une mention
	public void getMention(Set<Twit> res, String mention) {

		Matcher matcher = mentionPattern.matcher(mention);

		if ( matcher.find() ) {
			System.out.println("mention detectée, retour de la liste des twits de "+mention);
		}
	}
	
	/*
	 * affiche les twits de la recherche
	 */
	public void afficherTwitsToView(Set<Twit> listeTwits){
		
		Iterator<Twit> iterator = listeTwits.iterator();
		System.out.println("Résultat de la recherche : ");
		while (iterator.hasNext()) {
			Twit element = iterator.next();
			t.getConsultationTwitView().addTwitToView(element);
		}
	}
	
	/*
	 * vide le panel de la vue twit 
	 */
	public void viderView()
	{
		t.getConsultationTwitView().viderPanel();
	}



}
