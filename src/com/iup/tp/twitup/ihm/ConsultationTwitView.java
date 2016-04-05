package com.iup.tp.twitup.ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

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

	JScrollPane scrollPane ;
	JLabel rechercherTwitLabel;
	JTextField rechercheTwitTextField;
	JButton rechercherTwitButton;

	JPanel listPane;

	public ConsultationTwitView(ConsultationTwitController consultationTwitController, Twitup t){
		this.controller = consultationTwitController;
		this.t = t;
		this.initGUI();
	}

	private void initGUI(){

		this.setLayout(new BorderLayout());

		listPane = new JPanel();

		JPanel panel = new JPanel(new GridBagLayout());
		panel.add(listPane, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(
						5, 5, 0, 5), 0, 0));

		JScrollPane listScroller = new JScrollPane(panel, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		listScroller.setPreferredSize(new Dimension(800, 600));
		listScroller.setEnabled(true);
		listScroller.getVerticalScrollBar().setUnitIncrement(20);

		listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));	
		listPane.add(Box.createVerticalGlue());
		listPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		rechercherTwitLabel = new JLabel("Rechercher");
		rechercheTwitTextField = new JTextField("");
		rechercheTwitTextField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println("Recherche de " + rechercheTwitTextField.getText());
				controller.rechercheTwit(rechercheTwitTextField.getText());		

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == Event.ENTER){
					System.out.println("Recherche de " + rechercheTwitTextField.getText());
					controller.rechercheTwit(rechercheTwitTextField.getText());				
				}
			}
		});

		rechercherTwitButton = new JButton(new AbstractAction("Rechercher"){

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("Recherche de " + rechercheTwitTextField.getText());
				controller.rechercheTwit(rechercheTwitTextField.getText());

			} 
		});

		JPanel panelNorth = new JPanel();
		panelNorth.setLayout(new GridBagLayout());

		panelNorth.add(rechercherTwitLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(
						5, 5, 0, 5), 0, 0));

		panelNorth.add(rechercheTwitTextField, new GridBagConstraints(1, 0, 1, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(
						5, 5, 0, 5), 0, 0));

		panelNorth.add(rechercherTwitButton, new GridBagConstraints(2, 0, 1, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(
						5, 5, 0, 5), 0, 0));


		this.add(panelNorth, BorderLayout.NORTH);
		this.add(listScroller, BorderLayout.CENTER);


	}

	public void addTwitToView(Twit t)
	{

		TwitComponentView tcv = new TwitComponentView(t);
		listPane.add(tcv);
		// i++;
	}

	public void viderPanel()
	{
		listPane.removeAll();	
		listPane.revalidate();
		listPane.repaint();

	}

	public void creerPopupNouveauTwit(String name) {
		// créer une popup pour préviendre
		JOptionPane.showMessageDialog(null, "Un twit a été ajouté par "+ name + " - CONSULTEZ LE !!! ", "Un twit a été ajouté!!!! ", JOptionPane.INFORMATION_MESSAGE);		
	}



}
