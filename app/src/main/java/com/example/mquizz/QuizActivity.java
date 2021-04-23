package com.example.mquizz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import Table.Question;
import data.DbHelper;

public class QuizActivity extends AppCompatActivity {
	List<Question> quesList;
	int score=0;
	int qid=0;
	Question currentQ;
	TextView txtQuestion;
	RadioButton rda, rdb, rdc,rdd;
	Button butNext;


	@SuppressLint("ResourceAsColor")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);

		DbHelper db=new DbHelper(this);
		quesList=db.getAllQuestions();
		currentQ=quesList.get(qid);

		txtQuestion=(TextView)findViewById(R.id.textView1);
		rda=(RadioButton)findViewById(R.id.radio0);
		rdb=(RadioButton)findViewById(R.id.radio1);
		rdc=(RadioButton)findViewById(R.id.radio2);
		rdd=(RadioButton)findViewById(R.id.radio3);
		butNext=(Button)findViewById(R.id.button1);
		//RadioGroup grp=(RadioGroup)findViewById(R.id.radioGroup1);
		setQuestionView();

		butNext.setText("Passer");

		if(rda.isChecked() || rdb.isChecked() || rdc.isChecked() || rdd.isChecked()){
			butNext.setText("Valider");
		}



		butNext.setOnClickListener(new View.OnClickListener() {		

			@Override
			public void onClick(View v) {

				RadioGroup grp=(RadioGroup)findViewById(R.id.radioGroup1);
				RadioButton answer=(RadioButton)findViewById(grp.getCheckedRadioButtonId());
				grp.clearCheck();

				Log.d("yourans", currentQ.getANSWER()+" "+answer.getText());

		/*		if(currentQ.getANSWER().equals(answer.getText().toString()))
				{
					butNext.setText("Valider");
					score++;
					Log.d("score", "Your score"+score);
				}
				if(qid<5){
					butNext.setText("Valider");
					currentQ=quesList.get(qid);
					setQuestionView();
				}else{
					Intent intent = new Intent(com.example.mquizz.QuizActivity.this, com.example.mquizz.score.class);
					Bundle b = new Bundle();
					b.putInt("score", score); //Your score
					intent.putExtras(b); //Put your score to your next Intent
					startActivity(intent);
					finish();
				} */
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_quiz, menu);
		return true;
	}

	private void setQuestionView()
	{
		//butNext.setEnabled(true);
		txtQuestion.setText(currentQ.getQUESTION());
		rda.setText(currentQ.getOPTA());
		rdb.setText(currentQ.getOPTB());
		rdc.setText(currentQ.getOPTC());
	//	rdd.setText(currentQ.getOPTD());
		qid++;
	}
}
