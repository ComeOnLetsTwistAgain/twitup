package com.iup.tp.twitup.ihm;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.iup.tp.twitup.core.AllUsersController;
import com.iup.tp.twitup.core.Twitup;
import com.iup.tp.twitup.datamodel.User;

public class AllUsersView extends JPanel {
	
	AllUsersController controller;
	Twitup t;
	
	JPanel listPanel;
	
	public AllUsersView(AllUsersController controller, Twitup t){
		this.controller = controller;
		this.t = t;
	}
	
	public void initGUI(){
		
		this.setLayout(new GridBagLayout());
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setPreferredSize(new Dimension(100, 400));
		
		this.add(scrollPane, new GridBagConstraints(0, 0, 2, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(
						5, 5, 0, 5), 0, 0));
		
		
		
		
		int i = 1;
		for(User u : controller.getUsers()){
			
			GridBagConstraints c = new GridBagConstraints(0, i, 1, 1, 1, 1,
						GridBagConstraints.LINE_START, GridBagConstraints.LINE_START, new Insets(
								5, 5, 0, 5), 0, 0);
			
			System.out.println(u.toString());
			ComponentUser compu = new ComponentUser(u);
			
			
			panel.add(compu, c);
			i++;
		}
		
		
		
		
	}

}
