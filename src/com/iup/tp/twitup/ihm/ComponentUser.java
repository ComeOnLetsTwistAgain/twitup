package com.iup.tp.twitup.ihm;

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
		//this.
		
		
	}
	
	

}
