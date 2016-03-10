package com.iup.tp.twitup.ihm;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
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
	
	int i = 1;
	
	JPanel panel;
	JScrollPane scrollPane ;
	JLabel rechercherUserLabel;
	JTextField rechercheUserTextField;
	JButton rechercherUserButton;
	
	public AllUsersView(AllUsersController controller, Twitup t){
		this.controller = controller;
		this.t = t;
		this.setLayout(new GridBagLayout());
		this.initGUI();
	}
	
	public void initGUI(){
		
		this.setLayout(new GridBagLayout());

		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		
		rechercherUserLabel = new JLabel("Rechercher");
		rechercheUserTextField = new JTextField("");
		rechercherUserButton = new JButton(new AbstractAction("Rechercher"){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Recherche de "+ rechercheUserTextField);
				
			}
			
		});
		
		
		scrollPane = new JScrollPane(panel);
		scrollPane.setPreferredSize(new Dimension(100, 400));
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);

		this.add(rechercherUserLabel, new GridBagConstraints(0, 0, 2, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(
						5, 5, 0, 5), 0, 0));
		
		this.add(scrollPane, new GridBagConstraints(0, 0, 2, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(
						5, 5, 0, 5), 0, 0));	
		
	}
	
	
	public void addUserToView(User u)
	{
		GridBagConstraints c = new GridBagConstraints(0, i, 1, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.HORIZONTAL, new Insets(
						5, 5, 0, 5), 0, 0);
	
		System.out.println("user " + u.toString());
		ComponentUser compu = new ComponentUser(controller, u);
		
		
		panel.add(compu, c);
		i++;
	}

}
