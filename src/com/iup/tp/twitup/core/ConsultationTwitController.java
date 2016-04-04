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


		// parcours les followers du current user
		Set<String> following;

		if (this.t.getCurrentUser() != null ){
			following = this.t.getCurrentUser().getFollows();

			for(String f : following)
			{
				if(f.equals(addedTwit.getTwiter().getName()))
				{
					t.getConsultationTwitView().creerPopupNouveauTwit(addedTwit.getTwiter().getName());
				}
			}	
		}


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
		if (texte.equals(""))
		{
			afficherTwitsToView(allTwits);
		}else{

			// matcher sur les hashtags
			Matcher matchHashtag = hashtagPattern.matcher(texte);
			// matcher sur les mentions
			Matcher matchMention = mentionPattern.matcher(texte);

			// si la recherche est un hashtag
			if ( matchHashtag.find() ) {
				System.out.println("hashtag detecté : "+texte);
				texte = texte.replace("#", "");
				System.out.println("Suppression du # et recherche sur "+texte);

				// on ajoute tout les twits avec le hashtag
				res.addAll(this.db.getTwitsWithTag(texte));				
			}

			// si la recherche est une mention
			else if (matchMention.find())
			{
				System.out.println("mention detectée : "+texte);
				texte = texte.replace("@", "");
				System.out.println("Suppression du @ et recherche sur "+texte);

				// ajout de tout les twits avec la mention de la personne
				res.addAll(this.db.getTwitsWithUserTag(texte));

				// ajout de tout les twits de la personne 
				for(Twit t : allTwits){
					if(t.getTwiter().getUserTag().equals(texte))
						res.add(t);
				}

			} else if

			// si il n'y a aucun des deux symboles ci-dessus on recherche sur tout
			( !matchHashtag.find() && !matchMention.find()) {
				System.out.println("pas de hashtag et pas de mention détectée recherche sur tout");

				for(Twit t : allTwits){
					if(t.getTwiter().getUserTag().equals(texte))
						res.add(t);
					if(t.getText().contains(texte))
						res.add(t);
				}

			} else {
				System.out.println("rien trouvé");
			}

			afficherTwitsToView(res);
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
