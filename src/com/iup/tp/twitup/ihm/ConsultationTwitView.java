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

	// Liste Twits
	Set<Twit> liste;

	public ConsultationTwitView(ConsultationTwitController consultationTwitController, Twitup t){
		this.controller = consultationTwitController;
		this.t = t;
		this.initGUI();
	}

	private void initGUI(){

		/*GridBagConstraints c = new GridBagConstraints();
		this.setLayout(new GridBagLayout());

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weightx = 0;
		c.gridwidth = GridBagConstraints.REMAINDER;

		liste = controller.getTwits();

		if (liste != null)
		{
			for (Twit l:liste)
			{

				c.fill = GridBagConstraints.BOTH;
				c.weightx = 1;
				c.weightx = 0;
				c.gridwidth = GridBagConstraints.REMAINDER;

				// instancier un nouveau composant twitView
				System.out.println("twit :"+l.getText());
				TwitComponentView t = new TwitComponentView(l,c);
				this.add(t);

			} 
		} */
		 

		this.setLayout(new GridBagLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setPreferredSize(new Dimension(100, 400));

		this.add(scrollPane, new GridBagConstraints(0, 0, 2, 1, 1, 1,
				GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(
						5, 5, 0, 5), 0, 0));

		int i = 1;
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
		}


	}

}
