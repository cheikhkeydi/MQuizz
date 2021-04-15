 package com.example.mquizz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class slider extends AppCompatActivity {
    private ViewPager sliderViewPager;
    private LinearLayout dotslayout;
    private SliderAdapter sliderAdapter;
    private ImageButton back,forward;
    private Button compris;

    private TextView[] mDots;
    private int mCurrentPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);
        sliderViewPager =(ViewPager) findViewById(R.id.sliderviewPager);
        dotslayout =(LinearLayout) findViewById(R.id.dotslayout);
        back= (ImageButton) findViewById(R.id.back);
        forward = (ImageButton) findViewById(R.id.fordward);
        compris = (Button) findViewById(R.id.compris);


        sliderAdapter = new SliderAdapter(this);

        sliderViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        sliderViewPager.addOnPageChangeListener(viewListener);
        back.setVisibility(View.INVISIBLE);


        compris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hello = new Intent(getApplicationContext(), acceuil.class);
                startActivity(hello);
                finish();
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sliderViewPager.setCurrentItem(mCurrentPage + 1);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sliderViewPager.setCurrentItem(mCurrentPage -1);
            }
        });

    }

//------------------------------------------------------------------------------Pour les Fonctions
    public void addDotsIndicator(int position){

         mDots = new TextView[6];
         dotslayout.removeAllViews();
         for(int i =0; i<mDots.length; i++){
             mDots[i] = new TextView(this);
             mDots[i].setText(Html.fromHtml("&#8226;"));
             mDots[i].setTextSize(35);
             mDots[i].setTextColor(getResources().getColor(R.color.purple_200));

             dotslayout.addView(mDots[i]);
         }

        if (mDots.length > 0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorprincipale));
        }

    }



    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            mCurrentPage = position;

            if (position == 0){
                forward.setEnabled(true);
                back.setEnabled(false);
                back.setVisibility(View.INVISIBLE);

            }else if( position == mDots.length-1) {
                forward.setEnabled(false);
                back.setEnabled(true);
                forward.setVisibility(View.INVISIBLE);


            } else{
                forward.setEnabled(true);
                back.setEnabled(true);
                back.setVisibility(View.VISIBLE);
                forward.setVisibility(View.VISIBLE);

            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}