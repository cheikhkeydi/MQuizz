package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;

import Table.Filiere;
import Table.Matiere;
import Table.Niveau;
import Table.Question;
import classe.Users;

import java.util.ArrayList;
import java.util.List;

import static data.FiliereContract.FiliereEntry.KEY_FID;
import static data.FiliereContract.FiliereEntry.KEY_FNOM;
import static data.FiliereContract.FiliereEntry.TABLE_FQUEST;
import static data.MatiereContract.MatiereEntry.KEY_MID;
import static data.MatiereContract.MatiereEntry.KEY_MNOM;
import static data.MatiereContract.MatiereEntry.KEY_MNomFiliere;
import static data.MatiereContract.MatiereEntry.KEY_MNomNiveau;
import static data.MatiereContract.MatiereEntry.TABLE_MQUEST;
import static data.NiveauContract.NiveauEntry.KEY_NANNEE;
import static data.NiveauContract.NiveauEntry.KEY_NID;
import static data.NiveauContract.NiveauEntry.TABLE_NQUEST;
import static data.QuizContract.MovieEntry.KEY_ANSWER;
import static data.QuizContract.MovieEntry.KEY_ID;
//import static data.QuizContract.MovieEntry.KEY_NomMatiere;
import static data.QuizContract.MovieEntry.KEY_NomMatiere;
import static data.QuizContract.MovieEntry.KEY_OPTA;
import static data.QuizContract.MovieEntry.KEY_OPTB;
import static data.QuizContract.MovieEntry.KEY_OPTC;
//import static data.QuizContract.MovieEntry.KEY_OPTD;
import static data.QuizContract.MovieEntry.KEY_QUES;
import static data.QuizContract.MovieEntry.TABLE_QUEST;
import static data.UserContract.UserEntry.KEY_UID;
import static data.UserContract.UserEntry.KEY_UPSEUDO;
import static data.UserContract.UserEntry.KEY_USCORE;
import static data.UserContract.UserEntry.TABLE_UQUEST;


public class DbHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "Mquizz";
	// tasks table name

	private SQLiteDatabase dbase;
	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		dbase=db;
		//creation Table questionnaire
		String sql = "CREATE TABLE " +
				TABLE_QUEST + " ( " +
				KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
				KEY_QUES + " TEXT, " +
				KEY_OPTA+ " TEXT, " +
				KEY_OPTB + " TEXT, " +
				KEY_OPTC + " TEXT, " +
				KEY_ANSWER + " INTEGER, " +
	 			KEY_NomMatiere + " TEXT, " +
				"FOREIGN KEY (" + KEY_NomMatiere + ") REFERENCES " +
                    TABLE_MQUEST + " (" +KEY_MNOM +"));";

		//Creation Table User
		String sql1 = "CREATE TABLE IF NOT EXISTS " + TABLE_UQUEST + " ( "
				+ KEY_UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_UPSEUDO
				+ " TEXT, " + KEY_USCORE+ " INTEGER DEFAULT 0" +")";

		//Creation de la Table Niveau
		String sql2 = "CREATE TABLE IF NOT EXISTS " + TABLE_NQUEST + " ( "
				+ KEY_NID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NANNEE
				+ " TEXT " +" )";
		//Creation Table FIliere
		String sql3 = "CREATE TABLE IF NOT EXISTS " + TABLE_FQUEST + " ( "
				+ KEY_FID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_FNOM
				+ " TEXT " +" )";

		//Creation de la table Matiere
		String sql4 = "CREATE TABLE IF NOT EXISTS " + TABLE_MQUEST + " ( "

				//+ KEY_MID + " INTEGER DEFAULT 0 AUTOINCREMENT, "
				+ KEY_MNOM + " TEXT PRIMARY KEY, "
				+ KEY_MNomFiliere + " TEXT, "
				+ KEY_MNomNiveau + " TEXT,"
				+ " FOREIGN KEY (" + KEY_MNomNiveau + " ) REFERENCES "+ TABLE_NQUEST + "( "+KEY_NANNEE+" ),"
				+ "FOREIGN KEY ("+ KEY_MNomFiliere +" ) REFERENCES "+ TABLE_FQUEST + "( "+KEY_FNOM+"));";


		db.execSQL(sql1);
		db.execSQL(sql2);
		db.execSQL(sql3);
		db.execSQL(sql4);
		db.execSQL(sql);


		addQuestions();
		addUsers();
		addNiveaux();
		addFilieres();
		addMatieres();
		//db.close();
	}

	//////////////////////////////////////////////////////////////////////////////////////
	////////////////////// MANIPULATION DE LA  TABLE MATIERE ///////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void addMatieres() {
		Matiere matiere1 = new Matiere("Java","Informatique","Licence 1");
		this.addMatiere(matiere1);
		Matiere matiere2 = new Matiere("Algo1","Informatique","Licence 1");
		this.addMatiere(matiere2);
		Matiere matiere3 = new Matiere("Algo2","Informatique","Licence 2");
		this.addMatiere(matiere3);
		Matiere matiere4 = new Matiere("dev-web","Informatique","Licence 2");
		this.addMatiere(matiere4);
		Matiere matiere5 = new Matiere("Compilation","Informatique","Licence 3");
		this.addMatiere(matiere5);
		Matiere matiere6 = new Matiere("Dev-Mobile","Informatique","Licence 3");
		this.addMatiere(matiere6);
		Matiere matiere7 = new Matiere("Complexité 2","Informatique","Master 1");
		this.addMatiere(matiere7);
	}

	public void addMatiere(Matiere matiere){
		ContentValues values = new ContentValues();
		values.put(KEY_MNOM, matiere.getNOM());
		values.put(KEY_MNomFiliere, matiere.getNomFiliere());
		values.put(KEY_MNomNiveau, matiere.getNomNiveau());
		dbase.insert(TABLE_MQUEST, null, values);

	}

	public List<Matiere> getAllMatiere(String annee,String filiere) {
		List<Matiere> matiereList = new ArrayList<Matiere>();
		// Select All Query
		//String selectQuery = "SELECT  "+KEY_MNOM+" FROM " + TABLE_MQUEST;
		String selectQuery = "SELECT "+KEY_MNOM+" FROM " + TABLE_MQUEST+" WHERE "+KEY_MNomNiveau+" ='"+annee+"' AND "+KEY_MNomFiliere+" ='"+filiere+"'";
		dbase=this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Matiere matiere = new Matiere();
				//niveau.setID(cursor.getInt(0));
				matiere.setNOM(cursor.getString(0));
				matiereList.add(matiere);
			} while (cursor.moveToNext());
		}
		// return quest list
		return matiereList;
	}




	//////////////////////////////////////////////////////////////////////////////////////
	////////////////////// MANIPULATION DE LA  TABLE NIVEAU ///////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	public void addNiveaux() {
		Niveau niveau1 = new Niveau("Licence 1");
		this.addNiveau(niveau1);
		Niveau niveau2 = new Niveau("Licence 2");
		this.addNiveau(niveau2);
		Niveau niveau3 = new Niveau("Licence 3");
		this.addNiveau(niveau3);
		Niveau niveau4 = new Niveau("Master 1");
		this.addNiveau(niveau4);
	}

	//Sauvegarder ses données déclarés dans la bade de donnnees
	public void addNiveau(Niveau niveau) {
		ContentValues values = new ContentValues();
		values.put(KEY_NANNEE, niveau.getANNEE());
		dbase.insert(TABLE_NQUEST, null, values);
	}

	public Cursor getListNiveau(){
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor data = db.rawQuery("SELECT * FROM "+TABLE_NQUEST,null);
		return data;
	}

    public List<Niveau> getAllNiveau() {
        List<Niveau> niveauList = new ArrayList<Niveau>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NQUEST;
        dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Niveau niveau = new Niveau();
                niveau.setID(cursor.getInt(0));
                niveau.setANNEE(cursor.getString(1));
                niveauList.add(niveau);
            } while (cursor.moveToNext());
        }
        // return quest list
        return niveauList;
    }




	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////////////////////
	////////////////////// MANIPULATION DE LA  TABLE FILIERE  ///////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Ajouter des données dans la table Filiere
	public void addFilieres() {
		Filiere filiere = new Filiere("Informatique");
		this.addFiliere(filiere);
		Filiere filiere1 = new Filiere("Mathematique");
		this.addFiliere(filiere1);
		Filiere filiere2 = new Filiere("Physique");
		this.addFiliere(filiere2);
		Filiere filiere3 = new Filiere("Chimie");
		this.addFiliere(filiere3);
		Filiere filiere4 = new Filiere("Science de la Vie");
		this.addFiliere(filiere4);
		Filiere filiere5 = new Filiere("Science de la Terre");
		this.addFiliere(filiere5);

	}

	//Sauvegarder ses données déclarés dans la bade de donnnees
	public void addFiliere(Filiere filiere) {
		ContentValues values = new ContentValues();
		values.put(KEY_FNOM, filiere.getNOM());

		dbase.insert(TABLE_FQUEST, null, values);
	}

	public List<Filiere> getAllFilieres() {
		List<Filiere> filiereList = new ArrayList<Filiere>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_FQUEST;
		dbase=this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Filiere filiere = new Filiere();
				filiere.setID(cursor.getInt(0));
				filiere.setNOM(cursor.getString(1));
				filiereList.add(filiere);
			} while (cursor.moveToNext());
		}
		// return quest list
		return filiereList;
	}










	//////////////////////////////////////////////////////////////////////////////////////
	////////////////////// MANIPULATION DE LA  TABLE USER ///////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Ajouter des données dans la table User
	public void addUsers() {
		Users user1 = new Users("Stephanie");
		this.addUser(user1);
		Users user2 = new Users("Fallou");
		this.addUser(user2);
		Users user3 = new Users("Cheikh");
		this.addUser(user3);
	}

	//Sauvegarder ses données déclarés dans la bade de donnnees
	public void addUser(Users user1) {
		ContentValues values = new ContentValues();
		values.put(KEY_UPSEUDO, user1.getPseudo());
		values.put(KEY_USCORE, user1.getScore());

		dbase.insert(TABLE_UQUEST, null, values);
	}

	public List<Users> getAllUsers() {
		List<Users> usersList = new ArrayList<Users>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_UQUEST;
		dbase=this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Users user = new Users();
				user.setId(cursor.getInt(0));
				user.setPseudo(cursor.getString(1));
				user.setScore(cursor.getInt(2));
				usersList.add(user);
			} while (cursor.moveToNext());
		}
		// return quest list
		return usersList;
	}

	//Envoyer le Nom du dernier utilisateur
	public String getLastPseudo() {
		String lastPseudo;
		// Select All Query
		String selectQuery = "SELECT "+ KEY_UPSEUDO + " FROM " + TABLE_UQUEST + " WHERE "+ KEY_UID + " = (SELECT max ( " +KEY_UID+ ") FROM " +TABLE_UQUEST+ " )";
		dbase=this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery,null);
		cursor.moveToFirst();

		return cursor.getString(0);
	}

	//Envoyer le Nom du dernier utilisateur
	public String getLastScore() {
		String lastPseudo;
		// Select All Query
		String selectQuery = "SELECT "+ KEY_USCORE + " FROM " + TABLE_UQUEST + " WHERE "+ KEY_UID + " = (SELECT max ( " +KEY_UID+ ") FROM " +TABLE_UQUEST+ " )";
		dbase=this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery,null);
		cursor.moveToFirst();

		return cursor.getString(0).toString() + "/20";
	}



	//le Nombre d'utilisateur
	public int rowcountUser()
	{
		int row=0;
		String selectQuery = "SELECT  * FROM " + TABLE_UQUEST;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		row=cursor.getCount();
		return row;
	}


	public Cursor getListUsers(){
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor data = db.rawQuery("SELECT * FROM "+TABLE_UQUEST,null);
		return data;
	}


	public void AjouterUSer(String item1){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(KEY_UPSEUDO,item1);
		contentValues.put(KEY_USCORE,0);

		db.insert(TABLE_UQUEST,null,contentValues);
	}

	public boolean updateData(Integer score) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("UPDATE "+ TABLE_UQUEST + " set " + KEY_USCORE +" = "+score+ " WHERE "+ KEY_UID + " = (SELECT max ( " +KEY_UID+ ") FROM " +TABLE_UQUEST+ " )");
		return true;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////// MANIPULATION DE LA TABLE QUESTIONS ///////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////////////////////



	 private void addQuestions()
	{
		//Licence 1 Java
		Question q1=new Question("Java est un langage","Compilé", "Interprété ", "Compilé et interprété", 3,"Java");
		this.addQuestion(q1);
		Question q2=new Question("Java est un langage développé par","Hewlett-Packard", "Sun Microsystems ", "Microsoft", 2,"Java");
		this.addQuestion(q2);
		Question q3=new Question("La liaison tardive est essentielle pour assurer","L'encapsulation", "Le polymorphisme ", "L'héritage", 2,"Java");
		this.addQuestion(q3);
		Question q4=new Question("L'interprétation des programme Java est effectuée par : ","API", "JDK ", "JVM", 3,"Java");
		this.addQuestion(q4);
		Question q5=new Question("Quelle classe n'a pas de classe mère?","Une classe abstraite", "Object ", "String", 2,"Java");
		this.addQuestion(q5);


		//Licence 1
		Question q6=new Question("Qu'est ce q'un algorithme?", "Une décision", "Instructions pas à pas utilisées pour resoudre un problème", "Un organigramme", 2,"Algo2");
		this.addQuestion(q6);
		Question q7=new Question("Pour répéter une tâche, nous utilisons une .....?", "Boucle", "Entrée", "Condition", 1,"Algo2");
		this.addQuestion(q7);
		Question q8=new Question("Si.....Alors.....Sinon.....Fin Si, vérifier", "Une seul condition", "Deux conditions", "Trois conditions", 2,"Algo2");
		this.addQuestion(q8);
		Question q9=new Question("Qu'est ce qu'un organigramme?", "Un diagramme qui représente un ensemble d'instructions", "Un schéma d'instruction", "Un langage de programmation spécifique", 1,"Algo2");
		this.addQuestion(q9);
		Question q10=new Question("Quelles sont les trois constructions d'algorithme?", "Entrée, Sortie, Processus", "Séquence, Sélection, Répétition", "Boucle, Entrée/Sortie, Processus", 2,"Algo2");
		this.addQuestion(q10);



		//Licence 3
		Question q11=new Question("L'instance d'une classe est :","Un objet", "Une session","Une classe", 1,"dev-web");
		this.addQuestion(q11);
		Question q12=new Question("Le mot clé glob","Ne peut prendre qu'un seul paramètre", "Prend deux paramètres, dont un optionnel","Affiche une liste de fichiers", 2,"dev-web");
		this.addQuestion(q12);
		Question q13=new Question("Pour ce qui concerne notre UE, PHP signifie : ","Personal Home Page", "Personnel HTTP Preprocessor","Powerful HTML Prepocessor", 1,"dev-web");
		this.addQuestion(q13);
		Question q14=new Question("Le quel de ces mots-clés sont des noms de primitives PHP","Is_b", "Classof","Is_object", 3,"dev-web");
		this.addQuestion(q14);
		Question q15=new Question("Parmi les formats suivants, lequel s'appuie sur un langage à balises?","Le format HMTL", "Le format textes brut (TXT)","Le nouveau format Microsoft Word(DOCX)", 1,"dev-web");
		this.addQuestion(q15);



		//Licence 3
		Question q16=new Question("Quelle est le tâche effectuée durant l'analyse syntaxique?", "Produire une suite de tokens", "Optimiser le code intermédiaire","Lire la suite de tokens par le scanner",3,"Compilation");
		this.addQuestion(q16);
		Question q17=new Question("Un analyseur prédictif non-recursif : ","S'appuie sur une file","Utilise pour son fonctionnement le symbole spéciale £","Utilise pour son fonctionnement le symbole spéciale $",2,"Compilation");
		this.addQuestion(q17);
		Question q18=new Question("Un compilateur utilise des automates finis","Pour l'analyse lexicale","Pour l'analyse logique","Pour l'analyse syntaxique",1,"Compilation");
		this.addQuestion(q18);
		Question q19=new Question("Deux grammaires sont équivalentes:  ","Seulement si elles ont les mêmes terminaux","Seulement si elles ont les mêmes non-terminaux","Si et seulement si elles engendrent le même langage",3,"Compilation");
		this.addQuestion(q19);
		Question q20=new Question("La phrase d'analyse qui s'appuie sur la théorie des grammaires est : ","La phase d'analyse lexicale","La phase d'analyse syntaxique","La phase d'analyse sémantique",2,"Compilation");
		this.addQuestion(q20);



		Question q21=new Question("Android est : ","NetworkInfo","GooglePlay","Linux Based",3,"Dev-Mobile");
		this.addQuestion(q21);
		Question q22=new Question("Le méta-langage XML : ","est utilisé pour définir le langage XHTML","Est un langage de programmation","Est un format difficile à lire pour une machine",1,"Dev-Mobile");
		this.addQuestion(q22);
		Question q23=new Question("Lesquells des importations suivantes sont nécessaires pour stoquer les informations d'état dans une application android?","Import android.os.Bundle","Using System.Text","Import android.PutInteger",1,"Dev-Mobile");
		this.addQuestion(q23);
		Question q24=new Question("Laquelle des classes suivantes dans android permet de créer une étiquette simple?","android.app.Activity","Android.os.Bundle","Android.view.Window",1,"Dev-Mobile");
		this.addQuestion(q24);
		Question q25=new Question("Quel langage permet de gerer l'interface front-end sous android","CSS","XML","Bootstrap",2,"Dev-Mobile");
		this.addQuestion(q25);
	}

/*	private void addQuestions()
	{
		Question q1=new Question("If permissions are missing the application will get this at runtime","Parser", "SQLiteOpenHelper ", "Security Exception", 2);
		this.addQuestion(q1);
		Question q2=new Question("An open source standalone database", "SQLite", "BackupHelper", "NetworkInfo", 1);
		this.addQuestion(q2);
		Question q3=new Question("Sharing of data in Android is done via?","Wi-Fi radio", "Service Content Provider","Ducking", 2);
		this.addQuestion(q3);
		Question q4=new Question("Main class through which your application can access location services on Android", "LocationManager", "SQLiteOpenHelper","SQLiteOpenHelper",1);
		this.addQuestion(q4);
		Question q5=new Question("Android is?","NetworkInfo","GooglePlay","Linux Based",3);
		this.addQuestion(q5);
	} */

	// Adding new question
	public void addQuestion(Question quest) {
		//SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_QUES, quest.getQUESTION());
		values.put(KEY_OPTA, quest.getOPTA());
		values.put(KEY_OPTB, quest.getOPTB());
		values.put(KEY_OPTC, quest.getOPTC());
		values.put(KEY_ANSWER, quest.getANSWER());
		values.put(KEY_NomMatiere, quest.getNomMatiere());
		//values.put(KEY_OPTD, quest.getOPTC());
		// Inserting Row
		dbase.insert(TABLE_QUEST, null, values);
	}

	  public List<Question> getAllQuestions(String matiere) {
		List<Question> quesList = new ArrayList<Question>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_QUEST+ " WHERE "+KEY_NomMatiere+" ='"+matiere+"'";
		dbase=this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Question quest = new Question();
				quest.setID(cursor.getInt(0));
				quest.setQUESTION(cursor.getString(1));
				quest.setOPTA(cursor.getString(2));
				quest.setOPTB(cursor.getString(3));
				quest.setOPTC(cursor.getString(4));
				quest.setANSWER(cursor.getInt(5));
//				quest.setOPTD(cursor.getString(5));
				quesList.add(quest);
			} while (cursor.moveToNext());
		}
		// return quest list
		return quesList;
	}

	/*public List<Question> getAllQuestions() {
		List<Question> questionList = new ArrayList<>();
		dbase = getReadableDatabase();
		Cursor c = dbase.rawQuery("SELECT * FROM " + TABLE_QUEST, null);
		if (c.moveToFirst()) {
			do {
				Question question = new Question();
				question.setQUESTION(c.getString(c.getColumnIndex(TABLE_QUEST)));
				question.setOPTA(c.getString(c.getColumnIndex(KEY_OPTA)));
				question.setOPTB(c.getString(c.getColumnIndex(KEY_OPTB)));
				question.setOPTC(c.getString(c.getColumnIndex(KEY_OPTC)));
				question.setOPTD(c.getString(c.getColumnIndex(KEY_OPTD)));
				question.setANSWER(c.getInt(c.getColumnIndex(KEY_ANSWER)));
				questionList.add(question);
			} while (c.moveToNext());
		}
		c.close();
		return questionList;
	} */



		//recuperattion dernier element du tableau


	public int rowcount()
	{
		int row=0;
		String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		row=cursor.getCount();
		return row;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////










	@Override
	public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
		// Create tables again
		onCreate(db);
	}





}
