package com.iup.tp.twitup.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.iup.tp.twitup.core.CreationTwitController;
import com.iup.tp.twitup.core.Twitup;
import com.iup.tp.twitup.document.MaxLengthTextDocument;

public class CreationTwitView extends JPanel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	CreationTwitController controller;

	Twitup t;

	JLabel header;
	JLabel twitLabel;
	JTextField twitContent;

	JButton buttonSubmit; 


	public CreationTwitView(CreationTwitController controller, Twitup t){
		controller = controller;
		this.t = t;
		this.initGUI();
	}

	public CreationTwitController getController() {
		return controller;
	}

	public void setController(CreationTwitController controller) {
		this.controller = controller;
	}

	public Twitup getT() {
		return t;
	}

	public void setT(Twitup t) {
		this.t = t;
	}

	public JLabel getHeader() {
		return header;
	}

	public void setHeader(JLabel header) {
		this.header = header;
	}

	public JLabel getTwitLabel() {
		return twitLabel;
	}

	public void setTwitLabel(JLabel twitLabel) {
		this.twitLabel = twitLabel;
	}

	public JTextField getTwitContent() {
		return twitContent;
	}

	public void setTwitContent(JTextField twitContent) {
		this.twitContent = twitContent;
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
				createTwit();
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
	
	private void createTwit() {
		controller.creationTwit(this);
	}

}
