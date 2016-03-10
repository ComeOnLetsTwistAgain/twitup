package com.iup.tp.twitup.ihm;

import java.awt.CardLayout;
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
	//controller
	private Twitup controller;
	
	private JFrame frame;
	
	private PropertiesManager propertiesManager = new PropertiesManager();
	
	private IDatabase db;
	
	private String exchangeDir;
	
	private JPanel cards;
	private JPanel panel = new JPanel();
	
	
	JMenuBar menuBar;
	JMenu menuFichier;
	JMenu menuCompte;
	JMenu menuUtilisateurs;
	JMenu menuTwits;
	JMenu menuAPropos;
	
	JMenuItem itemQuitter;
	JMenuItem itemParametres;
	JMenuItem itemConnexionCompte;
	JMenuItem itemCreationCompte;
	JMenuItem itemCreationTwit;
	JMenuItem itemConsultationTwit;
	JMenuItem itemAPropos;
	JMenuItem itemMonProfil;
	
	GridBagConstraints constraintForPanels = new GridBagConstraints(0, 0, 2, 1, 1, 1,
			   							  		 GridBagConstraints.CENTER, 
			   							  		 GridBagConstraints.BOTH, 
			   							  		 new Insets(0, 0, 0, 0), 0, 0);
	
	
	public TwitupMainView(Twitup controller, IDatabase db){
		this.frame = new JFrame();
		this.controller = controller;
		this.db = db;
		
		//exchange dir
		this.exchangeDir = this.getExchangeDirectory();
		
	}
	
	public void initGUI(){
		
		//selection du répertoire d'échange si il n'est pas renseigné dans le fichier de conf
		Properties p = propertiesManager.loadProperties("src/resources/configuration.properties");
		if(controller.getmExchangeDirectoryPath().equals("")){
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
				    controller.setEchangeDirectory(this.exchangeDir);
				    controller.initDirectory(this.exchangeDir);
				    System.out.println("Changed directory to : " + this.exchangeDir);
				    
				} else if(result == JFileChooser.CANCEL_OPTION)
					System.out.println("Pas de repertoire sélectionné");
			} catch (Exception e) {
				e.printStackTrace();
			} 
		} else {
		    System.out.println("Directory already set : " + controller.getmExchangeDirectoryPath());
		}
		
		
		
		
		
		
	    this.frame.setTitle("TwitUp");
	    this.frame.setSize(500, 400);
	    this.frame.setLocationRelativeTo(null);
	    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.frame.setIconImage( new ImageIcon("src/resources/images/logoIUP_20.png").getImage());
	    
	    
	    
	    this.panel.setLayout(new GridBagLayout());
	    
	    this.cards = new JPanel(new CardLayout());
	    
	    
	    //adding layout
	    /*JPanel parametersView = new ParametersView();
	    JPanel creationTwitView = new CreationTwitView(db, controller);
	    JPanel creationCompteView = new CreationCompteView(db);
	    JPanel connexionView = new ConnexionCompteView(db, controller);*/
	    
	    /*this.cards.add(connexionView, CONNEXION);
	    this.cards.add(parametersView, PARAMETERS);
	    this.cards.add(creationTwitView, CREATETWIT);
	    this.cards.add(creationCompteView, CREATEACCOUNT);*/
	    //
		
	    
	    this.panel.add(this.cards);
	    
	    this.frame.setContentPane(panel);
	    
	    this.frame.setVisible(true);
	}
	
	public void initMenu(){
		menuBar = new JMenuBar();
		menuFichier = new JMenu("Fichier");
		menuCompte = new JMenu("Compte");
		menuUtilisateurs = new JMenu("Utilisateurs");
		menuTwits = new JMenu("Twits");
		menuAPropos = new JMenu("?");
		
		
		//Fichier		
		itemQuitter = new JMenuItem(new AbstractAction("Quitter", new ImageIcon("src/resources/images/exitIcon_20.png")){
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		itemQuitter.getAccessibleContext().setAccessibleDescription("Quitter l'application");
		
		itemParametres = new JMenuItem(new AbstractAction("Paramètres"){
			public void actionPerformed(ActionEvent e) {
				controller.switchToParameters();
			}
		});
		itemParametres.getAccessibleContext().setAccessibleDescription("Paramètres");
		
		//Compte
		itemConnexionCompte = new JMenuItem(new AbstractAction("Se connecter"){
			public void actionPerformed(ActionEvent e) {
				controller.switchToConnexion();
			}
		});
		
		itemCreationCompte = new JMenuItem(new AbstractAction("Créer un compte"){
			public void actionPerformed(ActionEvent e) {
				controller.switchToCreationCompte();
			}
		});
		
		// Twits
		itemCreationTwit = new JMenuItem(new AbstractAction("Créer un twit"){
			public void actionPerformed(ActionEvent e) {
				controller.switchToCreationTwit();
			}
		});
		
		itemConsultationTwit = new JMenuItem(new AbstractAction("Consulter un twit"){
			public void actionPerformed(ActionEvent e) {
				controller.switchToConsultationTwit();
			}
		});
		
		
		//?
		itemAPropos = new JMenuItem(new AbstractAction("A Propos"){
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "à propos de TwitUp ...", "A Propos", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		itemAPropos.getAccessibleContext().setAccessibleDescription("A Propos");
		
		//Profil
		itemMonProfil = new JMenuItem(new AbstractAction("Mon profil"){

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.switchToConsulterProfil();
				
			}
			
		});
		itemMonProfil.getAccessibleContext().setAccessibleDescription("Consulter mon profil");
		
		
		
		//utilisateurs
		JMenuItem itemTousLesUtilisateurs = new JMenuItem(new AbstractAction("Tous les utilisateurs"){

			@Override
			public void actionPerformed(ActionEvent e) {
				controller.switchToAllUsers();
				
			}
			
		});
		itemTousLesUtilisateurs.getAccessibleContext().setAccessibleDescription("Consulter la liste de tous les utilisateurs");
		
		
		
		
		
		menuBar.add(menuFichier);
		menuBar.add(menuCompte);
		menuBar.add(menuUtilisateurs);
		menuBar.add(menuTwits);
		menuBar.add(menuAPropos);
		
		menuFichier.add(itemParametres);
		menuFichier.add(itemQuitter);
		
		menuCompte.add(itemConnexionCompte);
		menuCompte.add(itemCreationCompte);
		menuCompte.add(itemMonProfil);
		
		menuUtilisateurs.add(itemTousLesUtilisateurs);
		
		menuTwits.add(itemCreationTwit);
		menuTwits.add(itemConsultationTwit);
		
		menuAPropos.add(itemAPropos);
		
		//de base certain menu sont cachés
		menuUtilisateurs.setVisible(false);
		
		frame.setJMenuBar(menuBar);
	}
	
	public void afterConnexion(){
		//rendre visible certain menu
		menuUtilisateurs.setVisible(true);
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
	
	
	
	
	
	
	
	
	

	public Twitup getController() {
		return controller;
	}

	public void setController(Twitup controller) {
		this.controller = controller;
	}

	public PropertiesManager getPropertiesManager() {
		return propertiesManager;
	}

	public void setPropertiesManager(PropertiesManager propertiesManager) {
		this.propertiesManager = propertiesManager;
	}

	public IDatabase getDb() {
		return db;
	}

	public void setDb(IDatabase db) {
		this.db = db;
	}

	public String getExchangeDir() {
		return exchangeDir;
	}

	public void setExchangeDir(String exchangeDir) {
		this.exchangeDir = exchangeDir;
	}

	public JMenuBar _getMenuBar() {
		return menuBar;
	}

	public void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}

	public JMenu getMenuFichier() {
		return menuFichier;
	}

	public void setMenuFichier(JMenu menuFichier) {
		this.menuFichier = menuFichier;
	}

	public JMenu getMenuCompte() {
		return menuCompte;
	}

	public void setMenuCompte(JMenu menuCompte) {
		this.menuCompte = menuCompte;
	}

	public JMenu getMenuTwits() {
		return menuTwits;
	}

	public void setMenuTwits(JMenu menuTwits) {
		this.menuTwits = menuTwits;
	}

	public JMenu getMenuAPropos() {
		return menuAPropos;
	}

	public void setMenuAPropos(JMenu menuAPropos) {
		this.menuAPropos = menuAPropos;
	}

	public JMenuItem getItemQuitter() {
		return itemQuitter;
	}

	public void setItemQuitter(JMenuItem itemQuitter) {
		this.itemQuitter = itemQuitter;
	}

	public JMenuItem getItemParametres() {
		return itemParametres;
	}

	public void setItemParametres(JMenuItem itemParametres) {
		this.itemParametres = itemParametres;
	}

	public JMenuItem getItemConnexionCompte() {
		return itemConnexionCompte;
	}

	public void setItemConnexionCompte(JMenuItem itemConnexionCompte) {
		this.itemConnexionCompte = itemConnexionCompte;
	}

	public JMenuItem getItemCreationCompte() {
		return itemCreationCompte;
	}

	public void setItemCreationCompte(JMenuItem itemCreationCompte) {
		this.itemCreationCompte = itemCreationCompte;
	}

	public JMenuItem getItemCreationTwit() {
		return itemCreationTwit;
	}

	public void setItemCreationTwit(JMenuItem itemCreationTwit) {
		this.itemCreationTwit = itemCreationTwit;
	}

	public JMenuItem getItemConsultationTwit() {
		return itemConsultationTwit;
	}

	public void setItemConsultationTwit(JMenuItem itemConsultationTwit) {
		this.itemConsultationTwit = itemConsultationTwit;
	}

	public JMenuItem getItemAPropos() {
		return itemAPropos;
	}

	public void setItemAPropos(JMenuItem itemAPropos) {
		this.itemAPropos = itemAPropos;
	}

	public JMenuItem getItemMonProfil() {
		return itemMonProfil;
	}

	public void setItemMonProfil(JMenuItem itemMonProfil) {
		this.itemMonProfil = itemMonProfil;
	}

	public GridBagConstraints getConstraintForPanels() {
		return constraintForPanels;
	}

	public void setConstraintForPanels(GridBagConstraints constraintForPanels) {
		this.constraintForPanels = constraintForPanels;
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JPanel getCards() {
		return cards;
	}

	public void setCards(JPanel cards) {
		this.cards = cards;
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
