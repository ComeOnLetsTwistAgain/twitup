package com.iup.tp.twitup.core;

import java.io.File;

import javax.swing.JPanel;

import java.awt.CardLayout;

import com.iup.tp.twitup.common.PropertiesManager;
import com.iup.tp.twitup.datamodel.Database;
import com.iup.tp.twitup.datamodel.DatabaseObserver;
import com.iup.tp.twitup.datamodel.IDatabase;
import com.iup.tp.twitup.datamodel.User;
import com.iup.tp.twitup.events.file.IWatchableDirectory;
import com.iup.tp.twitup.events.file.WatchableDirectory;
import com.iup.tp.twitup.ihm.*;

/**
 * Classe principale l'application.
 * 
 * @author S.Lucas
 */
public class Twitup
{
	ConnexionController connexionController;
	CreationCompteController creationCompteController;
	ConsulterProfilController consulterProfilController;
	
	JPanel parametersView;
	JPanel connexionView;
	JPanel creationCompteView;
	JPanel consulterProfilView;
	
	final static String PARAMETERS = "parametres";
	final static String CREATETWIT = "vue de création de twit";
	final static String CREATEACCOUNT = "vue de création de compte";
	final static String CONNEXION = "vue de connexion";
	final static String CONSULTERPROFIL = "vue de profil";
	
	/**
   * Après connexion, contient les infos du user.
   */
  protected User currentUser;
	
  /**
   * Base de données.
   */
  protected IDatabase mDatabase;

  /**
   * Gestionnaire des entités contenu de la base de données.
   */
  protected EntityManager mEntityManager;

  /**
   * Vue principale de l'application.
   */
  protected TwitupMainView mMainView;

  /**
   * Classe de surveillance de répertoire
   */
  protected IWatchableDirectory mWatchableDirectory;

  /**
   * Répertoire d'échange de l'application.
   */
  protected String mExchangeDirectoryPath;

  /**
   * Idnique si le mode bouchoné est activé.
   */
  protected boolean mIsMockEnabled = true;

  /**
   * Nom de la classe de l'UI.
   */
  protected String mUiClassName;
  
  private DatabaseObserver observer; 

  /**
   * Constructeur.
   */
  public Twitup()
  {
	  
	// Initialisation de la base de données
	this.initDatabase();
	
    // Init du look and feel de l'application
    this.initLookAndFeel();

    

    if (this.mIsMockEnabled)
    {
      // Initialisation du bouchon de travail
      this.initMock();
    }

   

    // Initialisation du répertoire d'échange
    this.initDirectory(PropertiesManager.loadProperties("src/resources/configuration.properties").getProperty("EXCHANGE_DIRECTORY"));
    
    // Initialisation de l'IHM
    this.initGui();
    
    observer = new DatabaseObserver(mDatabase, mMainView);
    mDatabase.addObserver(observer);
    
    
    //init des vues et des controllers
    this.connexionController = new ConnexionController(this.mDatabase);
    this.creationCompteController = new CreationCompteController(this.mDatabase, this.mEntityManager);
    this.consulterProfilController = new ConsulterProfilController(this.mDatabase, this.mEntityManager);
    
    this.parametersView = new ParametersView();
    this.connexionView = new ConnexionCompteView(connexionController, this);
    this.creationCompteView = new CreationCompteView(creationCompteController, this);
    this.consulterProfilView = new ConsulterProfilView(consulterProfilController, this);
    
    this.mMainView.getCards().add(connexionView, CONNEXION);
    this.mMainView.getCards().add(parametersView, PARAMETERS);
    this.mMainView.getCards().add(creationCompteView, CREATEACCOUNT);
    this.mMainView.getCards().add(consulterProfilView, CONSULTERPROFIL);
    
  
  }
  
  /*
   * Fonctions de switch de vue
   */
  
  public void switchToParameters(){
	  ((CardLayout) this.mMainView.getCards().getLayout()).show(this.mMainView.getCards(), PARAMETERS);
	  this.mMainView.getFrame().pack();
	  
  }
  
  public void switchToConnexion(){
	  ((CardLayout) this.mMainView.getCards().getLayout()).show(this.mMainView.getCards(), CONNEXION);
	  this.mMainView.getFrame().pack();
  }
  
  public void switchToCreationCompte(){
	  ((CardLayout) this.mMainView.getCards().getLayout()).show(this.mMainView.getCards(), CREATEACCOUNT);
	  this.mMainView.getFrame().pack();
  }
  
  public void switchToProfil(){
	  ((CardLayout) this.mMainView.getCards().getLayout()).show(this.mMainView.getCards(), CONSULTERPROFIL);
	  this.mMainView.getFrame().pack();
  }
  
  public boolean isLoggedIn(){
	  if(this.currentUser == null || this.currentUser.equals(""))
		  return false;
	  return true;
  }

  /**
   * Initialisation du look and feel de l'application.
   */
  protected void initLookAndFeel()
  {
	  this.mMainView = new TwitupMainView(this, mDatabase);
  }

  /**
   * Initialisation de l'interface graphique.
   */
  protected void initGui()
  {
	  this.mMainView.initLookFeel();
	  this.mMainView.initMenu();
	  this.mMainView.initGUI();
  }

  /**
   * Initialisation du répertoire d'échange (depuis la conf ou depuis un file chooser). <br/>
   * <b>Le chemin doit obligatoirement avoir été saisi et être valide avant de pouvoir utiliser l'application</b>
   */
  protected void initDirectory()
  {}

  /**
   * Indique si le fichier donné est valide pour servire de répertoire d'échange
   * 
   * @param directory
   *          , Répertoire à tester.
   */
  protected boolean isValideExchangeDirectory(File directory)
  {
    // Valide si répertoire disponible en lecture et écriture
    return directory != null && directory.exists() && directory.isDirectory() && directory.canRead()
        && directory.canWrite();
  }

  /**
   * Initialisation du mode bouchoné de l'application
   */
  protected void initMock()
  {
    TwitupMock mock = new TwitupMock(this.mDatabase, this.mEntityManager);
    mock.showGUI();
  }

  /**
   * Initialisation de la base de données
   */
  protected void initDatabase()
  {
    mDatabase = new Database();
    mEntityManager = new EntityManager(mDatabase);
  }

  /**
   * Initialisation du répertoire d'échange.
   * 
   * @param directoryPath
   */
  public void initDirectory(String directoryPath)
  {
    mExchangeDirectoryPath = directoryPath;
    mWatchableDirectory = new WatchableDirectory(directoryPath);
    mEntityManager.setExchangeDirectory(directoryPath);

    mWatchableDirectory.initWatching();
    mWatchableDirectory.addObserver(mEntityManager);
  }
  
  public String getExchangeDirectory(){
	  return this.mExchangeDirectoryPath;
  }
  
  public void setEchangeDirectory(String e){
	  this.mExchangeDirectoryPath = e;
  }

public IDatabase getmDatabase() {
	return mDatabase;
}

public void setmDatabase(IDatabase mDatabase) {
	this.mDatabase = mDatabase;
}

public EntityManager getmEntityManager() {
	return mEntityManager;
}

public void setmEntityManager(EntityManager mEntityManager) {
	this.mEntityManager = mEntityManager;
}

public TwitupMainView getmMainView() {
	return mMainView;
}

public void setmMainView(TwitupMainView mMainView) {
	this.mMainView = mMainView;
}

public IWatchableDirectory getmWatchableDirectory() {
	return mWatchableDirectory;
}

public void setmWatchableDirectory(IWatchableDirectory mWatchableDirectory) {
	this.mWatchableDirectory = mWatchableDirectory;
}

public String getmExchangeDirectoryPath() {
	return mExchangeDirectoryPath;
}

public void setmExchangeDirectoryPath(String mExchangeDirectoryPath) {
	this.mExchangeDirectoryPath = mExchangeDirectoryPath;
}

public boolean ismIsMockEnabled() {
	return mIsMockEnabled;
}

public void setmIsMockEnabled(boolean mIsMockEnabled) {
	this.mIsMockEnabled = mIsMockEnabled;
}

public String getmUiClassName() {
	return mUiClassName;
}

public void setmUiClassName(String mUiClassName) {
	this.mUiClassName = mUiClassName;
}

public DatabaseObserver getObserver() {
	return observer;
}

public void setObserver(DatabaseObserver observer) {
	this.observer = observer;
}

public User getCurrentUser() {
	return currentUser;
}

public void setCurrentUser(User currentUser) {
	this.currentUser = currentUser;
}


  
  
}
