package com.iup.tp.twitup.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.iup.tp.twitup.common.PropertiesManager;
import com.iup.tp.twitup.core.Twitup;

public class ParametersView extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Twitup t;

	JLabel choixLookAndFeelLabel;
	JLabel choixDossier;

	JTextField dossierChoisiTextField;

	JButton dossierButton;

	JComboBox liste;
	
	Properties prop;


	public ParametersView(Twitup t){
		this.t = t;
		initGUI();
	}

	private void initGUI(){

		this.setLayout(new GridBagLayout());

		UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
		HashMap<String,String> hm = new HashMap<String,String>();

		for (UIManager.LookAndFeelInfo look : looks) {
			hm.put(look.getName(), look.getClassName());
		}

		choixLookAndFeelLabel = new JLabel("Choix Look & feel :");
		liste = new JComboBox(hm.keySet().toArray());
		liste.setEditable(false);

		liste.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel(hm.get(liste.getSelectedItem()));
//					t.getmMainView().getFrame().revalidate();
//					t.getmMainView().getFrame().repaint();
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		
		/*liste.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent arg0) {
	            //Do Something
	        }
	    });*/
		

		choixDossier = new JLabel("Choix dossier :");

		dossierButton = new JButton("Parcourir ");
		dossierChoisiTextField = new JTextField("");
		
		prop = PropertiesManager.loadProperties("src/resources/configuration.properties");
		
		dossierChoisiTextField.setEnabled(false);
		dossierChoisiTextField.setText(prop.getProperty("EXCHANGE_DIRECTORY"));

		dossierButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				choisirDossier();
			}
		});

		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weightx = 0;
		c.gridwidth = GridBagConstraints.REMAINDER;

		this.add(choixLookAndFeelLabel, c);
		this.add(liste, c);

		this.add(choixDossier, c);
		this.add(dossierChoisiTextField, c);
		this.add(dossierButton, c);

	}        
	
	private void choisirDossier() {
		JFileChooser choix = new JFileChooser();
		choix.setCurrentDirectory(new File("."));
		choix.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // repertoire uniquement

		int retour= choix.showOpenDialog(this);

		if(retour==JFileChooser.APPROVE_OPTION){
			choix.getSelectedFile().getName();
			choix.getSelectedFile().getAbsolutePath();
			changeProperties(choix.getSelectedFile().getAbsolutePath());
			dossierChoisiTextField.setText(choix.getSelectedFile().getAbsolutePath());
		}else{
			dossierChoisiTextField.setText("");
		}
	}

	public void changeProperties(String absolutePath){
		Properties prop = PropertiesManager.loadProperties("src/resources/configuration.properties");
		prop.setProperty("EXCHANGE_DIRECTORY",absolutePath);
		PropertiesManager.writeProperties(prop, "src/resources/configuration.properties");
	}
	
}
