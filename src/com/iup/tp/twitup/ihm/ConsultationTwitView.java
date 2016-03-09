package com.iup.tp.twitup.ihm;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.util.Set;
import java.util.UUID;

import javax.swing.*;

import com.iup.tp.twitup.core.ConsultationTwitController;
import com.iup.tp.twitup.core.ConsulterProfilController;
import com.iup.tp.twitup.core.Twitup;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.document.MaxLengthTextDocument;

public class ConsultationTwitView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ConsultationTwitController controller;
	Twitup t;

	// Liste Twits
	Set<Twit> liste;

	public ConsultationTwitView(ConsultationTwitController consultationTwitController, Twitup t){
		this.controller = consultationTwitController;
		this.t = t;
		this.initGUI();
	}

	private void initGUI(){

		GridBagConstraints c = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
	
		/*c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weightx = 0;
		c.gridwidth = GridBagConstraints.REMAINDER;*/
		
		liste = controller.getTwits();

		if (liste != null)
		{
			afficherTwits(liste, c);
		} 
		
	}
	
	private void afficherTwits(Set<Twit> liste, GridBagConstraints c)
	{
		for (Twit l:liste)
		{
			
			c.fill = GridBagConstraints.BOTH;
			c.weightx = 1;
			c.weightx = 0;
			c.gridwidth = GridBagConstraints.REMAINDER;
			
			// instancier un nouveau composant twitView
			System.out.println("twit :"+l.getText());
			TwitComponentView t = new TwitComponentView(l,c);
			this.add(t);
			
		} 
	}

}
