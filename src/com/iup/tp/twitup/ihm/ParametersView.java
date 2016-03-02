package com.iup.tp.twitup.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

public class ParametersView extends JPanel{
	
	JComboBox liste;
	
	public ParametersView(){
		initGUI();
	}
	
	private void initGUI(){
		
		this.setLayout(new GridBagLayout());
		
		UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
	    HashMap<String,String> hm = new HashMap<String,String>();
	    
	    for (UIManager.LookAndFeelInfo look : looks) {
	    	hm.put(look.getName(), look.getClassName());
	    }
	    
	    for(String s : hm.values()){
	    	System.out.println(s);
	    }
	    
	    liste = new JComboBox(hm.keySet().toArray());
	    liste.setEditable(true);
	    
	    liste.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel(hm.get(liste.getSelectedItem()));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
	    });
	    
	    GridBagConstraints c = new GridBagConstraints();
		
		c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weightx = 0;
        c.gridwidth = GridBagConstraints.REMAINDER;
	    
	    this.add(liste, c);
		
	}
	
	
	
	
    
        
        
        //JOptionPane.showMessageDialog(mFrame, liste1,prop.getProperty("UI_CLASS_NAME"), 0);

}
