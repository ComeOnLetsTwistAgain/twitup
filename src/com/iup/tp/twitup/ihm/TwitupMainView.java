package com.iup.tp.twitup.ihm;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.*;
import java.io.File;
import java.util.*;

import javax.swing.*;

import com.iup.tp.twitup.common.PropertiesManager;
import com.iup.tp.twitup.core.EntityManager;
import com.iup.tp.twitup.core.Twitup;
import com.iup.tp.twitup.datamodel.Database;
import com.iup.tp.twitup.datamodel.DatabaseObserver;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.IDatabaseObserver;
import com.iup.tp.twitup.datamodel.Twit;
import com.iup.tp.twitup.datamodel.User;

/**
 * Classe de la vue principale de l'application.
 */
public class TwitupMainView extends JFrame implements IDatabaseObserver
{
	//element parent
	private Twitup t;
	
	private JFrame frame;
	
	private PropertiesManager propertiesManager = new PropertiesManager();
	
	private IDatabase db;
	
	private String exchangeDir;
	
	//Element de swing
	JList<String> twitList;
	ArrayList<String> data = new ArrayList<String>();
	
	GridBagConstraints constraintForPanels = new GridBagConstraints(0, 0, 2, 1, 1, 1,
			   							  		 GridBagConstraints.CENTER, 
			   							  		 GridBagConstraints.BOTH, 
			   							  		 new Insets(0, 0, 0, 0), 0, 0);
	
	
	public TwitupMainView(Twitup t, IDatabase db){
		this.frame = new JFrame();
		this.t = t;
		this.db = db;
		
		//exchange dir
		this.exchangeDir = this.getExchangeDirectory();
		
	}
	
	public void initGUI(){
		
		//selection du répertoire d'échange si il n'est pas renseigné dans le fichier de conf
		Properties p = propertiesManager.loadProperties("src/resources/configuration.properties");
		
		if(this.exchangeDir.equals("")){
			try {
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Choisir le répertoire d'échange");
				fileChooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY);
				int result = fileChooser.showOpenDialog(frame);
				if (result == JFileChooser.APPROVE_OPTION) {
				    File selectedFile = fileChooser.getSelectedFile();
				    
				    p.setProperty("EXCHANGE_DIRECTORY", selectedFile.getAbsolutePath());
				    propertiesManager.writeProperties(p, "src/resources/configuration.properties");
				    
				    this.exchangeDir = p.getProperty("EXCHANGE_DIRECTORY");
				    t.setEchangeDirectory(this.exchangeDir);
				    t.initDirectory(this.exchangeDir);
				    System.out.println("Changed directory to : " + this.exchangeDir);
				    
				} else if(result == JFileChooser.CANCEL_OPTION)
					System.out.println("Pas de repertoire sélectionné");
			} catch (Exception e) {
				e.printStackTrace();
			} 
		} else {
			
			this.exchangeDir = p.getProperty("EXCHANGE_DIRECTORY");
		    t.setEchangeDirectory(this.exchangeDir);
		    t.initDirectory(this.exchangeDir);
		    System.out.println("Directory already set : " + this.exchangeDir);
		}
		
		
		
		
		
		
	    this.frame.setTitle("TwitUp");
	    this.frame.setSize(500, 400);
	    this.frame.setLocationRelativeTo(null);
	    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.frame.setIconImage( new ImageIcon("src/resources/images/logoIUP_20.png").getImage());
	    
	    this.frame.setLayout(new GridBagLayout());
	    
	    
	    //list de page d'accueil
	    twitList = new JList<String>();
	    JScrollPane scrollPane = new JScrollPane(twitList);
		
	    
	    
	    this.frame.setVisible(true);
	}
	
	public void initMenu(){
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFichier = new JMenu("Fichier");
		JMenu menuCompte = new JMenu("Compte");
		JMenu menuTwits = new JMenu("Twits");
		JMenu menuAPropos = new JMenu("?");
		
		
		//Fichier		
		JMenuItem itemQuitter = new JMenuItem(new AbstractAction("Quitter", new ImageIcon("src/resources/images/exitIcon_20.png")){
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		itemQuitter.getAccessibleContext().setAccessibleDescription("Quitter l'application");
		
		JMenuItem itemParametres = new JMenuItem(new AbstractAction("Paramêtres"){
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().invalidate();
				
				frame.getContentPane().add(new ParametersView(), constraintForPanels);
				frame.getContentPane().revalidate();
			}
		});
		itemParametres.getAccessibleContext().setAccessibleDescription("Paramêtres");
		
		//Compte
		JMenuItem itemConnexionCompte = new JMenuItem(new AbstractAction("Se connecter"){
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().invalidate();
				
				frame.getContentPane().add(new ConnexionCompteView(db, t), constraintForPanels);
				frame.getContentPane().revalidate();
			}
		});
		
		JMenuItem itemCreationCompte = new JMenuItem(new AbstractAction("Créer un compte"){
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().invalidate();
				
				frame.getContentPane().add(new CreationCompteView(db), constraintForPanels);
				frame.getContentPane().revalidate();
			}
		});
		
		// Twits
		JMenuItem itemCreationTwit = new JMenuItem(new AbstractAction("Créer un twit"){
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().invalidate();
				
				frame.getContentPane().add(new CreationTwitView(db), constraintForPanels);
				frame.getContentPane().revalidate();
			}
		});
		
		
		//?
		JMenuItem itemAPropos = new JMenuItem(new AbstractAction("A Propos"){
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "à propos de TwitUp ...", "A Propos", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		itemAPropos.getAccessibleContext().setAccessibleDescription("A Propos");
		
		
		
		
		menuBar.add(menuFichier);
		menuBar.add(menuCompte);
		menuBar.add(menuTwits);
		menuBar.add(menuAPropos);
		
		menuFichier.add(itemParametres);
		menuFichier.add(itemQuitter);
		menuCompte.add(itemConnexionCompte);
		menuCompte.add(itemCreationCompte);
		menuTwits.add(itemCreationTwit);
		menuAPropos.add(itemAPropos);
		
		frame.setJMenuBar(menuBar);
	}
	
	public void initLookFeel(){
		
		try {
			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			//System.setProperty("apple.laf.useScreenMenuBar", "true");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getExchangeDirectory(){
		return propertiesManager.loadProperties("src/resources/configuration.properties").getProperty("EXCHANGE_DIRECTORY");
	}
	
	
	
	
	
	
	
	
	

	@Override
	public void notifyTwitAdded(Twit addedTwit) {
		System.out.println("from view > twit added :");
		
		//this.twitList.add("lala");
	}

	@Override
	public void notifyTwitDeleted(Twit deletedTwit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyTwitModified(Twit modifiedTwit) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyUserAdded(User addedUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyUserDeleted(User deletedUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyUserModified(User modifiedUser) {
		// TODO Auto-generated method stub
		
	}
	
}
