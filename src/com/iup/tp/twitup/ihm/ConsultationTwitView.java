package com.iup.tp.twitup.ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
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

	JPanel panel;
	JScrollPane scrollPane ;
	JLabel rechercherTwitLabel;
	JTextField rechercheTwitTextField;
	JButton rechercherTwitButton;

	JButton button;
	
	JPanel listPane;

	int i = 1 ;

	public ConsultationTwitView(ConsultationTwitController consultationTwitController, Twitup t){
		this.controller = consultationTwitController;
		this.t = t;
		this.initGUI();
	}

	private void initGUI(){

		this.setLayout(new BorderLayout());
		
		
		
		listPane = new JPanel();
		JScrollPane listScroller = new JScrollPane(listPane, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
		        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		listScroller.setPreferredSize(new Dimension(800, 600));
		listScroller.setEnabled(true);
		listScroller.getVerticalScrollBar().setUnitIncrement(20);
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));
		
		
		
		listPane.add(Box.createVerticalGlue());
		listPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		

		/*panel = new JPanel();
		panel.setPreferredSize(new Dimension(800,600));
		panel.setLayout(new GridBagLayout());

		scrollPane = new JScrollPane(panel);
		scrollPane.setPreferredSize(new Dimension(800, 600));
		scrollPane.setEnabled(true);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);*/

		rechercherTwitLabel = new JLabel("Rechercher");
		rechercheTwitTextField = new JTextField("");
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


		/*this.add(rechercheTwitTextField, new GridBagConstraints(1, 0, 1, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(
						5, 5, 0, 5), 0, 0));*/
		panelNorth.add(rechercherTwitButton, new GridBagConstraints(2, 0, 1, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(
						5, 5, 0, 5), 0, 0));
		
		
		this.add(panelNorth, BorderLayout.NORTH);
		this.add(listScroller, BorderLayout.CENTER);

		/*this.add(scrollPane, new GridBagConstraints(0, 1, 3, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(
						5, 5, 0, 5), 0, 0));*/


	}

	public void addTwitToView(Twit t)
	{


//		GridBagConstraints c = new GridBagConstraints(0, i, 1, 1, 1, 1,
//				GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(
//						5, 5, 0, 5), 0, 0);

		//System.out.println(t.getText());
		TwitComponentView tcv = new TwitComponentView(t);

		listPane.add(tcv);
		System.out.println();

		i++;
	}

	public void viderPanel()
	{
		panel.removeAll();	
		panel.repaint();
	}



}
