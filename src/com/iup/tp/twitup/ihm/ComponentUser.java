package com.iup.tp.twitup.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.iup.tp.twitup.core.AllUsersController;
import com.iup.tp.twitup.datamodel.User;

public class ComponentUser extends JPanel {
	AllUsersController controller;
	
	User u;
	
	JPanel mainPanel;
	ImageIcon img;
	JButton followButton;
	
	JLabel username;
	
	public ComponentUser(AllUsersController controller, User u){
		this.u = u;
		this.controller = controller;
		initGUI();
	}
	
	private void initGUI(){
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weightx = 0;
        c.gridwidth = GridBagConstraints.REMAINDER;
		
        
		JLabel username = new JLabel(u.getName());
		
		/*Image i = img.getImage();
		BufferedImage bi = new BufferedImage(i.getWidth(null), i.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		g.drawImage(i, 0, 0, 20, 20, null, null);
		img = new ImageIcon(bi);*/
		
		//this.add(new JLabel("", img, JLabel.CENTER), c);
		followButton = new JButton(new AbstractAction("Suivre"){

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.follow(u);
				
			}
			
		});
		
		this.add(username, c);
		this.add(followButton);
		
		this.setVisible(true);
	}
	
	

}
