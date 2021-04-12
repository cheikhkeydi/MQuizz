package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mquizz.R;

import java.util.ArrayList;
import java.util.List;

import classe.Users;
import data.DbHelper;

public class ListeScoreAdapter extends BaseAdapter {

//    private DbHelper;
    private List<Users> users;
    private Context context;
    private LayoutInflater layoutInflater;

    public ListeScoreAdapter(List<Users> users, Context context){
        this.users = users;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }




    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Users getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = layoutInflater.inflate(R.layout.adapter_listescore,null);
        Users userList = users.get(position);
        //Lister les composants de notre classe USers

        final String userName =userList.getPseudo().toString();
        final Integer userScore = userList.getScore();
        //Instanciere les elements de AdapterListeScore

        TextView textPseudo = (TextView) convertView.findViewById(R.id.text_pseudo);
        TextView scorePseudo = (TextView) convertView.findViewById(R.id.score_adapter);

      //  textPseudo.setText(userName);

        textPseudo.setText(userName);
        //scorePseudo.setText(userScore.toString());

        scorePseudo.setText(Integer.toString(userScore)+"/20");


        return convertView;
    }
}
