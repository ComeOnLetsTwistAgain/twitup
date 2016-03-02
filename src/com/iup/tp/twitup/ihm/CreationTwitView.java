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

import com.iup.tp.twitup.core.Twitup;
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
	Twitup t;

	JLabel header;
	JLabel twitLabel;
	JTextField twitContent;

	JButton buttonSubmit; 


	public CreationTwitView(IDatabase db, Twitup t){
		this.db = db;
		this.t = t;
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



				User user = t.getCurrentUser();

				if (user != null)
				{

					long millis = System.currentTimeMillis();					
					Twit twit = new Twit(UUID.randomUUID(), user, millis, twitContent.getText() );
					db.addTwit(twit);
					System.out.println("Création du twit : ");

					for (Twit toto:db.getTwitsWithUserTag(user.getUserTag()))
					{
						System.out.println("Twit : "+toto.getText());
					}

				}else{
					System.out.println("Pas de current user");
				}

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
