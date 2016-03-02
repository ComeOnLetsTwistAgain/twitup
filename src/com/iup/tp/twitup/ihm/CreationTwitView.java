package com.iup.tp.twitup.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.HashSet;
import java.util.UUID;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.document.MaxLengthTextDocument;

public class CreationTwitView extends JPanel {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	IDatabase db;
	
	JLabel header;
	JLabel twitLabel;
	JTextField twitContent;
	
	JButton buttonSubmit; 
	
	
	public CreationTwitView(IDatabase db){
		this.db = db;
		this.initGUI();
	}
	
	private void initGUI(){
		this.setLayout(new GridBagLayout());
		
		header = new JLabel("Ecrire un nouveau twit ");
		twitLabel = new JLabel("");
		header.setHorizontalAlignment(JTextField.CENTER);
		
		twitContent = new JTextField();
		
		MaxLengthTextDocument maxLength = new MaxLengthTextDocument();
		maxLength.setMaxChars(150); // number max of characters (150)

		twitContent.setDocument(maxLength);
		
		buttonSubmit = new JButton( new AbstractAction("Twiter !"){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				long millis = System.currentTimeMillis();
				System.out.println("Création du compte : ");
				User user = new User(UUID.randomUUID(), "tag", "pwd", "sylvain", null, null);
				Twit twit = new Twit(UUID.randomUUID(), user, millis, twitContent.getText() );
				db.addTwit(twit);
			}
		});
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weightx = 0;
        c.gridwidth = GridBagConstraints.REMAINDER;
		
		
		this.add(header, c);

		this.add(twitLabel, c);
		c.ipady = 40;
		this.add(twitContent, c);
		c.ipady = 1;
		this.add(buttonSubmit, c);
		
		
		this.setVisible(true);
	}

}
