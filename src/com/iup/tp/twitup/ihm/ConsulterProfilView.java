package com.iup.tp.twitup.ihm;

import javax.swing.JPanel;

import com.iup.tp.twitup.core.ConnexionController;
import com.iup.tp.twitup.core.ConsulterProfilController;
import com.iup.tp.twitup.core.Twitup;

public class ConsulterProfilView extends JPanel {
	
	ConsulterProfilController controller;
	Twitup t;
	
	public ConsulterProfilView(	ConsulterProfilController controller, Twitup t){
		this.controller = controller;
		this.t = t;
		initGUI();
	}
	
	private void initGUI(){
		
	}
	

}
