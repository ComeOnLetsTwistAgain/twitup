package com.iup.tp.twitup.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.iup.tp.twitup.datamodel.User;

public class ComponentUser extends JPanel {
	
	User u;
	
	JPanel mainPanel;
	
	JLabel username;
	
	public ComponentUser(User u){
		this.u = u;
		initGUI();
	}
	
	private void initGUI(){
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weightx = 0;
        c.gridwidth = GridBagConstraints.REMAINDER;
		
        
		JLabel username = new JLabel(u.getName());
		
		this.add(username, c);
		
		this.setVisible(true);
	}
	
	

}
