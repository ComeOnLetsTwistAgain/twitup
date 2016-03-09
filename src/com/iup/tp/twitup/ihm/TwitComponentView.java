package com.iup.tp.twitup.ihm;

import java.awt.GridBagConstraints;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TwitComponentView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel nameLabel;
	private JLabel userLabel;
	private JLabel twitContentLabel;
	private JLabel dateLabel;
	
	private ImageIcon userIcon;
	private ImageIcon imageTwitIcon;
	
	public TwitComponentView (GridBagConstraints c)
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
	
	

}
