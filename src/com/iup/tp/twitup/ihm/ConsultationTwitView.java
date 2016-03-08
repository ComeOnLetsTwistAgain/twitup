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

	// scroll pane
	ScrollPane sp;

	// texte des twits
	JLabel nameLabel;
	JLabel userLabel;
	JLabel twitContentLabel;
	JLabel dateLabel;

	// Image du twit
	ImageIcon userIcon;
	ImageIcon imageTwitIcon;

	// Liste Twits
	Set<Twit> liste;

	public ConsultationTwitView(ConsultationTwitController consultationTwitController, Twitup t){
		this.controller = consultationTwitController;
		this.t = t;
		this.initGUI();
	}

	private void initGUI(){

		sp = new ScrollPane();		
		this.setLayout(new GridBagLayout());
		this.add(sp);

		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weightx = 0;
		c.gridwidth = GridBagConstraints.REMAINDER;
		
		liste = controller.getTwits();

		if (liste != null)
		{
			for (Twit l:liste)
			{
				nameLabel = new JLabel();
				userLabel = new JLabel();
				twitContentLabel = new JLabel();
				dateLabel = new JLabel();

				nameLabel.setName("nomView");
				userLabel.setName("userView");
				twitContentLabel.setName("contenuView");
				dateLabel.setName("dateView");

				// Image du twit
				userIcon = new ImageIcon();
				imageTwitIcon = new ImageIcon();

				this.add(nameLabel, c);
				this.add(userLabel, c);
				this.add(twitContentLabel, c);
				this.add(dateLabel, c);
			} 

			this.setVisible(true);
		}
		
	}

}
