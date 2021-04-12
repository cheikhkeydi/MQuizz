package data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;

import Table.Question;
import classe.Users;

import java.util.ArrayList;
import java.util.List;

import static data.QuizContract.MovieEntry.KEY_ANSWER;
import static data.QuizContract.MovieEntry.KEY_ID;
import static data.QuizContract.MovieEntry.KEY_OPTA;
import static data.QuizContract.MovieEntry.KEY_OPTB;
import static data.QuizContract.MovieEntry.KEY_OPTC;
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
		String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
				+ " TEXT, " + KEY_ANSWER+ " TEXT, "+KEY_OPTA +" TEXT, "
				+KEY_OPTB +" TEXT, "+KEY_OPTC+" TEXT)";

		//Creation Table User
		String sql1 = "CREATE TABLE IF NOT EXISTS " + TABLE_UQUEST + " ( "
				+ KEY_UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_UPSEUDO
				+ " TEXT, " + KEY_USCORE+ " INTEGER DEFAULT 0" +")";



		db.execSQL(sql);
		db.execSQL(sql1);
		addQuestions();
		addUsers();
		//db.close();
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
		Question q1=new Question("If permissions are missing the application will get this at runtime","Parser", "SQLiteOpenHelper ", "Security Exception", "Security Exception");
		this.addQuestion(q1);
		Question q2=new Question("An open source standalone database", "SQLite", "BackupHelper", "NetworkInfo", "SQLite");
		this.addQuestion(q2);
		Question q3=new Question("Sharing of data in Android is done via?","Wi-Fi radio", "Service Content Provider","Ducking", "Service Content Provider" );
		this.addQuestion(q3);
		Question q4=new Question("Main class through which your application can access location services on Android", "LocationManager", "AttributeSet", "SQLiteOpenHelper","LocationManager");
		this.addQuestion(q4);
		Question q5=new Question("Android is?","NetworkInfo","GooglePlay","Linux Based","Linux Based");
		this.addQuestion(q5);
	}

	// Adding new question
	public void addQuestion(Question quest) {
		//SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_QUES, quest.getQUESTION());
		values.put(KEY_ANSWER, quest.getANSWER());
		values.put(KEY_OPTA, quest.getOPTA());
		values.put(KEY_OPTB, quest.getOPTB());
		values.put(KEY_OPTC, quest.getOPTC());
		// Inserting Row
		dbase.insert(TABLE_QUEST, null, values);
	}

	public List<Question> getAllQuestions() {
		List<Question> quesList = new ArrayList<Question>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
		dbase=this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery, null);
		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Question quest = new Question();
				quest.setID(cursor.getInt(0));
				quest.setQUESTION(cursor.getString(1));
				quest.setANSWER(cursor.getString(2));
				quest.setOPTA(cursor.getString(3));
				quest.setOPTB(cursor.getString(4));
				quest.setOPTC(cursor.getString(5));
				quesList.add(quest);
			} while (cursor.moveToNext());
		}
		// return quest list
		return quesList;
	}

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
