package com.iup.tp.twitup.ihm;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.iup.tp.twitup.core.AllUsersController;
import com.iup.tp.twitup.core.Twitup;

public class AllUsersView extends JPanel {
	
	AllUsersController controller;
	Twitup t;
	
	JPanel mainPanel;
	
	public AllUsersView(AllUsersController controller, Twitup t){
		this.controller = controller;
		this.t = t;
		initGUI();
	}
	
	private void initGUI(){
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
	}

}
