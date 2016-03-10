package com.iup.tp.twitup.ihm;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Set;
import javax.swing.*;

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
	
	// Liste Twits
	Set<Twit> liste;
	
	int i = 0 ;

	public ConsultationTwitView(ConsultationTwitController consultationTwitController, Twitup t){
		this.controller = consultationTwitController;
		this.t = t;
		this.initGUI();
	}

	private void initGUI(){

		this.setLayout(new GridBagLayout());

		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		scrollPane = new JScrollPane(panel);
		scrollPane.setPreferredSize(new Dimension(100, 400));
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);

		this.add(scrollPane, new GridBagConstraints(0, 0, 2, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(
						5, 5, 0, 5), 0, 0));

		/*int i = 1;
		liste = controller.getTwits();
		if (liste != null )
		{
			System.out.println("la liste de twits n'est pas nulle");
			for(Twit l : liste){

				GridBagConstraints c = new GridBagConstraints(0, i, 1, 1, 1, 1,
						GridBagConstraints.LINE_START, GridBagConstraints.LINE_START, new Insets(
								5, 5, 0, 5), 0, 0);

				System.out.println(l.getText());
				TwitComponentView t = new TwitComponentView(l);

				panel.add(t, c);
				i++;
			}
		} else {
			System.out.println("la liste de twits est nulle");
		}*/


	}
	
	public void addTwitToView(Twit t)
	{
		
		GridBagConstraints c = new GridBagConstraints(0, i, 1, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.LINE_START, new Insets(
						5, 5, 0, 5), 0, 0);

		System.out.println(t.getText());
		TwitComponentView tcv = new TwitComponentView(t);

		panel.add(tcv, c);
		
		i++;
	}

}
