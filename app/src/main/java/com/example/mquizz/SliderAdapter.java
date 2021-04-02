package com.example.mquizz;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;
    }

    //Le tableau des Images
    public int[] slide_images ={
            R.drawable.acceui,
            R.drawable.slider2,
            R.drawable.slider4,
            R.drawable.slider3,
            R.drawable.acceui,
            R.drawable.acceui
    };

    //Le tableau des titres
    public  String[] slide_title={
        "Bienvenue dans MQuizz",
        "Choix Niveau d'etude",
        "Choix filiere d'etude",
        "choix matiere",
        "Super, on y vas pour le quizz",
        "Affichage du score"
    };
    //Le tableau des Description
    public  String[] slide_description = {
            "L'application faites pour vous!",
            "Ici vous avez le choix de selectionner votre niveau d'etude qui corresponde",
            "Choisisszez votre filiere selon la liste des filiere Proposées.",
            "Selectionner la matiere dont vous vouliez qu'on vous évalue",
            "Puis amuser vous a repondre aux questions",
            "Ici vous verrez votre resultat aprés évaluation de vos réponses"
    };




    @Override
    public int getCount() {
        return slide_title.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object ;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view =layoutInflater.inflate(R.layout.slide_layout,container,false);

        ImageView slideImageView= (ImageView) view.findViewById(R.id.slideImage);
        TextView slideHeading = (TextView) view.findViewById(R.id.slideTitre);
        TextView slideText = (TextView) view.findViewById(R.id.slideText);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_title[position]);
        slideText.setText(slide_description[position]);

        container.addView(view);

        return view;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }
}
