package com.iup.tp.twitup.ihm;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
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
	IDatabase db;
	Twitup t;
	
	// texte des twits
	JLabel nameLabel;
	JLabel userLabel;
	JLabel twitContentLabel;
	JLabel dateLabel;
	
	// Image du twit
	ImageIcon userIcon;
	ImageIcon imageTwitIcon;
		

	public ConsultationTwitView(ConsultationTwitController consultationTwitController, Twitup t){
		this.db=db;
		this.controller = consultationTwitController;
		this.t = t;
		this.initGUI();
	}

	private void initGUI(){
	
			
		this.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weightx = 0;
        c.gridwidth = GridBagConstraints.REMAINDER;
        
    	nameLabel = new JLabel();
    	userLabel = new JLabel();
    	twitContentLabel = new JLabel();
    	dateLabel = new JLabel();
    	
    	// Image du twit
    	userIcon = new ImageIcon();
    	imageTwitIcon = new ImageIcon();
        
        this.add(nameLabel, c);
        this.add(userLabel, c);
        this.add(twitContentLabel, c);
        this.add(dateLabel, c);
        
        this.setBorder(BorderFactory.createLineBorder(Color.black));	

		this.setVisible(true);
		
	}
	
	
	
	
}
