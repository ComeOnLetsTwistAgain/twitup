package com.iup.tp.twitup.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.iup.tp.twitup.datamodel.Twit;


/*
//import javafx.application.Platform;
//import javafx.embed.swing.JFXPanel;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
*/
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
		
		 /*try {
	            Parent root = FXMLLoader.load(getClass().getResource("FXComponents.fxml"));
	            Scene scene = new Scene(root, 250, 150);
	            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
	            jfxPanel.setScene(scene);
	        } catch (IOException exc) {
	            exc.printStackTrace();
	            System.exit(1);
	        }*/
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
		userLabel.setText("@"+t.getTwiter().getUserTag());
		twitContentLabel.setText(t.getText());
		dateLabel.setText(""+t.getEmissionDate());

		// Image du twit

		// userIcon = new ImageIcon();
		// imageTwitIcon = new ImageIcon();



		this.add(nameLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(userLabel, new GridBagConstraints(1, 0, 5, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(twitContentLabel, new GridBagConstraints(0, 1, 6, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(dateLabel, new GridBagConstraints(0, 2, 6, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(
						5, 5, 0, 5), 0, 0));

		
		this.setVisible(true);
	}	


}
