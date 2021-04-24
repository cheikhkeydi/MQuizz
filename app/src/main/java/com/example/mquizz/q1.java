package com.example.mquizz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import Table.Question;
import Table.Users;
import data.DbHelper;

import static adapter.matiereAdapter.NomMatiere;
import static com.example.mquizz.Niveau.NAME;

public class q1 extends AppCompatActivity {
    private static final long COUNTDOWN_IN_MILLIS = 16000;

    //orientation d'ecran
    private  static  final String KEY_SCORE = "keyScore";
    private  static  final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private  static  final String KEY_MILLIS_LEFT = "keyMillisLeft";
    private  static  final String KEY_ANSWERED = "keyAnswered";
    private  static  final String KEY_QUESTION_LIST = "keyQuestionList";
    public static final String LASTSCORE= "LastScore";
    public DbHelper dbHelper;
    public Users users;

    TextView textViewQuestion;
    TextView textViewScore;
    TextView textViewQuestionCount;
    TextView textViewCountDown;
    RadioGroup rbGroup;
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    Button buttonConfirmNext;

    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCd;

    private CountDownTimer countDownTimer;
    private  long timeLeftInMillis;

    private List<Question> questionList;
    private int questionCounter;
    int questionCountTotal;
    Question currentQuestion;

    private int score;
    private boolean answered;

    private  long backPressedTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q1);
        textViewQuestion = findViewById(R.id.textView1);
        textViewScore = findViewById(R.id.score);
        textViewQuestionCount = findViewById(R.id.nb_qst);
        textViewCountDown = findViewById(R.id.chrono);
        rbGroup = findViewById(R.id.radioGroup1);
        rb1 = findViewById(R.id.radio0);
        rb2 = findViewById(R.id.radio1);
        rb3 = findViewById(R.id.radio2);
        buttonConfirmNext = findViewById(R.id.button1);

        //Recuperation de la matiere selectionnée
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String matiere = preferences.getString(NomMatiere,"Pas trouvé");

        textColorDefaultRb = rb1.getTextColors();
        textColorDefaultCd = textViewCountDown.getTextColors();
        DbHelper dbHelper= new DbHelper(this);

        if (savedInstanceState == null) {
            questionList = dbHelper.getAllQuestions(matiere);
            questionCountTotal = questionList.size();
            Collections.shuffle(questionList);

            showNextQuestion();
        }else {
            questionList = savedInstanceState.getParcelable(KEY_QUESTION_LIST);
            questionCountTotal = questionList.size();
            questionCounter  = (savedInstanceState.getInt(KEY_QUESTION_COUNT));
            currentQuestion = questionList.get(questionCounter - 1);
            score = (savedInstanceState.getInt(KEY_SCORE));
            timeLeftInMillis = savedInstanceState.getLong(KEY_MILLIS_LEFT);
            answered = savedInstanceState.getBoolean(KEY_ANSWERED);

            if(!answered){
                startCountDown();
            }else {
                updateCountDownText();
                showSolution();
            }
        }
        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!answered){
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()){
                        checkAnswer();
                    }else {
                        Toast.makeText(q1.this, "Veuillez selectionner une reponse", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    showNextQuestion();
                }
            }
        });



    }

    private  void showNextQuestion(){
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();

        //Nombre de question effectuer % nombre question total
        if (questionCounter < questionCountTotal){
            currentQuestion = questionList.get(questionCounter);
            textViewQuestion.setText(currentQuestion.getQUESTION());
            rb1.setText(currentQuestion.getOPTA());
            rb2.setText(currentQuestion.getOPTB());
            rb3.setText(currentQuestion.getOPTC());

            questionCounter++;
            textViewQuestionCount.setText( questionCounter + "/" + questionCountTotal);
            answered = false;
            buttonConfirmNext.setText("Confirmer");

            //Minuteur
            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();
        }
        else {
            finishQuiz();
        }
    }

    private void startCountDown(){
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();

            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                checkAnswer();
            }
        }.start();
    }

    private void updateCountDownText(){
       // int minutes = (int)(timeLeftInMillis /1000) / 60;
        int seconds = (int)(timeLeftInMillis / 1000) % 60;
        String timeFormatted = String.format(Locale.getDefault(), "%02d",seconds);
        textViewCountDown.setText(timeFormatted);

        if(timeLeftInMillis <10000){
            textViewCountDown.setTextColor(Color.GREEN);
        }else {
            textViewCountDown.setTextColor(textColorDefaultCd);
        }
    }
    private  void checkAnswer(){
        answered = true;

        countDownTimer.cancel();

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;

        if (answerNr == currentQuestion.getANSWER()){
            LayoutInflater inflaterr = LayoutInflater.from(this);
            View layout = inflaterr.inflate(R.layout.toast_vraie,(ViewGroup) findViewById(R.id.toast_layout));
            final Toast toast = new Toast(this);
            toast.setGravity(Gravity.FILL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();
            score = score + 4;
            textViewScore.setText(score+"/20");
        }
        else
        {
            LayoutInflater inflaterr = LayoutInflater.from(this);
            View layout = inflaterr.inflate(R.layout.toast_faux,(ViewGroup) findViewById(R.id.toast_layout));
            final Toast toast = new Toast(this);
            toast.setGravity(Gravity.FILL, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();
        }
        showSolution();
    }
    @SuppressLint("ResourceAsColor")
    private  void showSolution(){
        rb1.setTextColor(Color.GRAY);
        rb2.setTextColor(Color.GRAY);
        rb3.setTextColor(Color.GRAY);

        switch (currentQuestion.getANSWER()){
            case 1:
                rb1.setTextColor(Color.GREEN);
                textViewQuestion.setText("Reponse 1 correcte");
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                textViewQuestion.setText("Reponse 2 correcte");
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                textViewQuestion.setText("Reponse 3 correcte");
                break;
        }
        if (questionCounter < questionCountTotal){
            buttonConfirmNext.setText("suivant");

        }else{
            buttonConfirmNext.setText("Terminer");
        }
    }
    private void finishQuiz(){
        Intent resultIntent = new Intent();
        setResult(RESULT_OK, resultIntent);
        Intent hello = new Intent(getApplicationContext(),score.class);
        //Sauvegarde du score
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(LASTSCORE, Integer.toString(score));
        editor.commit();


        startActivity(hello);
        finish();

        //dbHelper.updateData(score);
        //finish();
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()){
            finishQuiz();
        }else {
            Toast.makeText(this, "appuyez à nouveau pour terminer", Toast.LENGTH_SHORT).show();
        }
        backPressedTime = System.currentTimeMillis();
    }
    protected  void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null){
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCORE, score);
        outState.putInt(KEY_QUESTION_COUNT, questionCounter);
        outState.putLong(KEY_MILLIS_LEFT, timeLeftInMillis);
        outState.putBoolean(KEY_ANSWERED, answered);
        outState.putParcelable(KEY_QUESTION_LIST, (Parcelable) questionList);

    }


}