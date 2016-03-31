package com.iup.tp.twitup.ihm;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.iup.tp.twitup.datamodel.Twit;

public class TwitComponentView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel nameLabel;
	private JLabel userLabel;
	private JLabel twitContentLabel;
	private JLabel dateLabel;
	
	// private ImageIcon userIcon;
	// private ImageIcon imageTwitIcon;
	
	private Twit t;
	
	public TwitComponentView (Twit t)
	{
		this.t = t;
		this.initGUI();
	}
	
	private void initGUI()
	{
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weightx = 0;
       // c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
		
		nameLabel = new JLabel();
		userLabel = new JLabel();
		twitContentLabel = new JLabel();
		dateLabel = new JLabel();

		nameLabel.setText(t.getTwiter().getName());
		userLabel.setText(t.getTwiter().getUserTag());
		twitContentLabel.setText(t.getText());
		dateLabel.setText(""+t.getEmissionDate());

		// Image du twit
		// userIcon = new ImageIcon();
		// imageTwitIcon = new ImageIcon();

		this.add(nameLabel, c);
		this.add(userLabel, c);
		this.add(twitContentLabel, c);
		this.add(dateLabel, c);
		
		this.setVisible(true);
	}	


}
