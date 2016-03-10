package com.iup.tp.twitup.ihm;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.Set;

import javax.swing.*;

import com.iup.tp.twitup.core.ConsultationTwitController;
import com.iup.tp.twitup.core.Twitup;
import com.iup.tp.twitup.datamodel.Twit;

public class ConsultationTwitView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	ConsultationTwitController controller;
	Twitup t;

	JPanel panel;
	JScrollPane scrollPane ;
	JLabel rechercherTwitLabel;
	JTextField rechercheTwitTextField;
	JButton rechercherTwitButton;
	
	JButton button;

	int i = 1 ;

	public ConsultationTwitView(ConsultationTwitController consultationTwitController, Twitup t){
		this.controller = consultationTwitController;
		this.t = t;
		this.initGUI();
	}

	private void initGUI(){

		this.setLayout(new GridBagLayout());

		panel = new JPanel();
		panel.setPreferredSize(new Dimension(800,600));
		panel.setLayout(new GridBagLayout());

		scrollPane = new JScrollPane(panel);
		scrollPane.setPreferredSize(new Dimension(800, 600));
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);

		rechercherTwitLabel = new JLabel("Rechercher");
		rechercheTwitTextField = new JTextField("");
		rechercherTwitButton = new JButton(new AbstractAction("Rechercher"){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Recherche de " + rechercheTwitTextField);

			}

		});


		this.add(rechercherTwitLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.NONE, new Insets(
						5, 5, 0, 5), 0, 0));
	
		this.add(rechercheTwitTextField, new GridBagConstraints(1, 0, 1, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(
						5, 5, 0, 5), 0, 0));
		this.add(rechercherTwitButton, new GridBagConstraints(2, 0, 1, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(
						5, 5, 0, 5), 0, 0));

		this.add(scrollPane, new GridBagConstraints(0, 1, 2, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(
						5, 5, 0, 5), 0, 0));


	}

	public void addTwitToView(Twit t)
	{

		GridBagConstraints c = new GridBagConstraints(0, i, 1, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.CENTER, new Insets(
						5, 5, 0, 5), 0, 0);

		System.out.println(t.getText());
		TwitComponentView tcv = new TwitComponentView(t);

		panel.add(tcv, c);

		i++;
	}

}
