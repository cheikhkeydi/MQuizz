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

import classe.User;

public class ListeScoreAdapter extends BaseAdapter {

    private List<User> users = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;

    public ListeScoreAdapter(List<User> users,Context context){
        this.users = users;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }




    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public User getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = layoutInflater.inflate(R.layout.adapter_listescore,null);
        //Lister les composants de notre classe USers
        User userList = users.get(position);
        String userName =userList.getPseudo().toString();
        Integer  userScore = userList.getScore();
        //Instanciere les elements de AdapterListeScore

        TextView textPseudo = (TextView) convertView.findViewById(R.id.text_pseudo);
        TextView scorePseudo = (TextView) convertView.findViewById(R.id.score_adapter);

      //  textPseudo.setText(userName);

        textPseudo.setText(userName);
        textPseudo.setText(userScore.toString());

      //  scorePseudo.setText(Integer.toString(userScore)+"/20");


        return convertView;
    }
}
