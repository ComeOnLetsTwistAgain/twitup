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
import com.iup.tp.twitup.mock.MockController;

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
	CreationTwitController creationTwitController; 
	ConsultationTwitController consultationTwitController;
	AllUsersController allUsersController;
	
	JPanel parametersView;
	ConnexionCompteView connexionView;
	JPanel creationCompteView;
	JPanel consulterProfilView;
	JPanel creationTwitView;
	ConsultationTwitView consultationTwitView;
	AllUsersView allUsersView;
	
	final static String PARAMETERS = "parametres";
	final static String CREATETWIT = "vue de création de twit";
	final static String CREATEACCOUNT = "vue de création de compte";
	final static String CONNEXION = "vue de connexion";
	final static String CONSULTERPROFIL = "vue de profil";
	final static String CONSULTERTWIT = "vue d'affichage d'un twit";
	final static String ALLUSERS = "vue d'affichage des users";
	

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

		//init des controllers et des vues
		this.connexionController = new ConnexionController(this.mDatabase, this.mEntityManager, this);
		this.creationCompteController = new CreationCompteController(this.mDatabase,this.mEntityManager, this);
		this.creationTwitController = new CreationTwitController(this.mDatabase, this, this.mEntityManager);
		this.consulterProfilController = new ConsulterProfilController(this.mDatabase, this.mEntityManager, this);
		this.consultationTwitController = new ConsultationTwitController(this.mDatabase, this.mEntityManager, this);
		//
		
		this.parametersView = new ParametersView();
		this.connexionView = new ConnexionCompteView(connexionController, this);
		this.creationCompteView = new CreationCompteView(creationCompteController, this);
		this.creationTwitView = new CreationTwitView(creationTwitController, this);
		this.consulterProfilView = new ConsulterProfilView(consulterProfilController, this);
		this.consultationTwitView = new ConsultationTwitView(consultationTwitController, this);
		//

		this.mMainView.getCards().add(connexionView, CONNEXION);
		this.mMainView.getCards().add(parametersView, PARAMETERS);
		this.mMainView.getCards().add(creationCompteView, CREATEACCOUNT);
		this.mMainView.getCards().add(creationTwitView,CREATETWIT);
		this.mMainView.getCards().add(consultationTwitView,CONSULTERTWIT);
		
		
		//ajout de certain controller en observateur de la base
		//
		this.mDatabase.addObserver(this.consultationTwitController);

	}
	
	public void afterConnexion(){
		//on rajoute les liens de menu
		this.getmMainView().afterConnexion();
		
		//all users
		this.allUsersController = new AllUsersController(this.mDatabase, this.mEntityManager, this);
		this.allUsersView = new AllUsersView(allUsersController, this);
		this.mMainView.getCards().add(allUsersView, ALLUSERS);
		this.mDatabase.addObserver(this.allUsersController);
		
		
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

	public void switchToCreationTwit(){
		((CardLayout) this.mMainView.getCards().getLayout()).show(this.mMainView.getCards(), CREATETWIT);
		this.mMainView.getFrame().pack();
	}
	
	public void switchToConsultationTwit(){
		((CardLayout) this.mMainView.getCards().getLayout()).show(this.mMainView.getCards(), CONSULTERTWIT);
		this.mMainView.getFrame().pack();
	}
	
	public void switchToConsulterProfil(){

		this.consulterProfilController = new ConsulterProfilController(this.mDatabase, this.mEntityManager, this);
		this.consulterProfilView = new ConsulterProfilView(consulterProfilController, this);
		this.mMainView.getCards().add(consulterProfilView, CONSULTERPROFIL);
		
		
		
		((CardLayout) this.mMainView.getCards().getLayout()).show(this.mMainView.getCards(), CONSULTERPROFIL);
		this.mMainView.getFrame().pack();
	}
	
	public void switchToAllUsers(){
		// this.allUsersView.initGUI();
		((CardLayout) this.mMainView.getCards().getLayout()).show(this.mMainView.getCards(), ALLUSERS);
		this.mMainView.getFrame().pack();
	}
	
	
	public void logout(){
		this.currentUser = null;
		this.mMainView.afterDeconnexion();
		
		((CardLayout) this.mMainView.getCards().getLayout()).show(this.mMainView.getCards(), CONNEXION);
		this.mMainView.getFrame().pack();
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

	public ConnexionController getConnexionController() {
		return connexionController;
	}

	public void setConnexionController(ConnexionController connexionController) {
		this.connexionController = connexionController;
	}

	public CreationCompteController getCreationCompteController() {
		return creationCompteController;
	}

	public void setCreationCompteController(
			CreationCompteController creationCompteController) {
		this.creationCompteController = creationCompteController;
	}

	public ConsulterProfilController getConsulterProfilController() {
		return consulterProfilController;
	}

	public void setConsulterProfilController(
			ConsulterProfilController consulterProfilController) {
		this.consulterProfilController = consulterProfilController;
	}

	public CreationTwitController getCreationTwitController() {
		return creationTwitController;
	}

	public void setCreationTwitController(
			CreationTwitController creationTwitController) {
		this.creationTwitController = creationTwitController;
	}

	public ConsultationTwitController getConsultationTwitController() {
		return consultationTwitController;
	}

	public void setConsultationTwitController(
			ConsultationTwitController consultationTwitController) {
		this.consultationTwitController = consultationTwitController;
	}

	public AllUsersController getAllUsersController() {
		return allUsersController;
	}

	public void setAllUsersController(AllUsersController allUsersController) {
		this.allUsersController = allUsersController;
	}

	public JPanel getParametersView() {
		return parametersView;
	}

	public void setParametersView(JPanel parametersView) {
		this.parametersView = parametersView;
	}

	public JPanel getConnexionView() {
		return connexionView;
	}

	public void setConnexionView(ConnexionCompteView connexionView) {
		this.connexionView = connexionView;
	}

	public JPanel getCreationCompteView() {
		return creationCompteView;
	}

	public void setCreationCompteView(JPanel creationCompteView) {
		this.creationCompteView = creationCompteView;
	}

	public JPanel getConsulterProfilView() {
		return consulterProfilView;
	}

	public void setConsulterProfilView(JPanel consulterProfilView) {
		this.consulterProfilView = consulterProfilView;
	}

	public JPanel getCreationTwitView() {
		return creationTwitView;
	}

	public void setCreationTwitView(JPanel creationTwitView) {
		this.creationTwitView = creationTwitView;
	}

	public ConsultationTwitView getConsultationTwitView() {
		return consultationTwitView;
	}

	public void setConsultationTwitView(ConsultationTwitView consultationTwitView) {
		this.consultationTwitView = consultationTwitView;
	}

	public AllUsersView getAllUsersView() {
		return allUsersView;
	}

	public void setAllUsersView(AllUsersView allUsersView) {
		this.allUsersView = allUsersView;
	}

	public static String getParameters() {
		return PARAMETERS;
	}

	public static String getCreatetwit() {
		return CREATETWIT;
	}

	public static String getCreateaccount() {
		return CREATEACCOUNT;
	}

	public static String getConnexion() {
		return CONNEXION;
	}

	public static String getConsulterprofil() {
		return CONSULTERPROFIL;
	}

	public static String getConsultertwit() {
		return CONSULTERTWIT;
	}

	public static String getAllusers() {
		return ALLUSERS;
	}
	
	

	


}
