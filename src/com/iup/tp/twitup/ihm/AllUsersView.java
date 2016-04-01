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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.iup.tp.twitup.core.AllUsersController;
import com.iup.tp.twitup.core.Twitup;
import com.iup.tp.twitup.datamodel.User;

public class AllUsersView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	AllUsersController controller;
	Twitup t;	
	
	JLabel rechercherUserLabel;
	JTextField rechercheUserTextField;
	JButton rechercherUserButton;
	
	JPanel listPane;
	
	int i = 1;
	
	public AllUsersView(AllUsersController allUsersController, Twitup t){
		this.controller = allUsersController;
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
		listPane.add(Box.createVerticalGlue());
		listPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		rechercherUserLabel = new JLabel("Rechercher");
		rechercheUserTextField = new JTextField("");
		
		rechercheUserTextField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == Event.ENTER){
					System.out.println("Recherche de " + rechercheUserTextField.getText());
					controller.rechercheUser(rechercheUserTextField.getText());				
				}
			}
		});
		
		rechercherUserButton = new JButton(new AbstractAction("Rechercher"){
		
			public void actionPerformed(ActionEvent e) {

				System.out.println("Recherche de " + rechercheUserTextField.getText());
				controller.rechercheUser(rechercheUserTextField.getText());

			}
			
		});

		JPanel panelNorth = new JPanel();
		panelNorth.setLayout(new GridBagLayout());
		
		panelNorth.add(rechercherUserLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(
						5, 5, 0, 5), 0, 0));
	
		panelNorth.add(rechercheUserTextField, new GridBagConstraints(1, 0, 1, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(
						5, 5, 0, 5), 0, 0));
		panelNorth.add(rechercherUserButton, new GridBagConstraints(2, 0, 1, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(
						5, 5, 0, 5), 0, 0));

		
		this.add(panelNorth, BorderLayout.NORTH);
		this.add(listScroller, BorderLayout.CENTER);
		
		
	}
	
	
	public void addUserToView(User u)
	{
		ComponentUser compu = new ComponentUser(controller, u);
		// System.out.println("ajout d'un user dans la liste " + compu);
		listPane.add(compu);
	}

	public void viderPanel() {
		listPane.removeAll();	
		listPane.revalidate();
		listPane.repaint();		
	}
	
}
