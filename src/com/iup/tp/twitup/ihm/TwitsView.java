package com.iup.tp.twitup.ihm;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.UUID;

import javax.swing.*;

import com.iup.tp.twitup.core.Twitup;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.document.MaxLengthTextDocument;

public class TwitsView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		
	// Cadre du twit
	


	public TwitsView(IDatabase db, Twitup t){
		this.db = db;
		this.t = t;
		this.initGUI();
	}

	private void initGUI(){
	
		this.setBorder(BorderFactory.createLineBorder(Color.black));		
		
	}
	
	
	
	
}
