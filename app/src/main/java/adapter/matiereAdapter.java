package adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.mquizz.R;
import com.example.mquizz.acceuil;
import com.example.mquizz.scripte1;

import java.util.List;

import Table.Matiere;

public class matiereAdapter extends BaseAdapter {

    private Context context;
    private List<Matiere> matiere_itemList;
    private LayoutInflater layoutInflater;
    public final static String NomMatiere = "Matiere";
    private static final int LONG_DELAY = 4;

    public matiereAdapter(Context context, List<Matiere> matiere_itemList){

        this.context = context;
        this.matiere_itemList = matiere_itemList;
        this.layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return matiere_itemList.size();
    }

    @Override
    public Matiere getItem(int position) {
        return matiere_itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("WrongConstant")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.adapter_matiere, null);

        //mettre le nom des items
        Matiere matiere_item = getItem(position);
        String current_namme = matiere_item.getNOM();
        //mettre le item dans le view
        Button item_name = convertView.findViewById(R.id.item_matiere);
        item_name.setText(current_namme);
        //Toast
        LayoutInflater inflaterr = LayoutInflater.from(context);
        View layout = inflaterr.inflate(R.layout.toast_quizz,(ViewGroup) convertView.findViewById(R.id.toast_layout));
        final Toast toast = new Toast(context);
        toast.setGravity(Gravity.FILL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);



        item_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast.show();
                //Toast.makeText(context, "Vous avez choisi "+current_namme, Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        Intent hello = new Intent(context,com.example.mquizz.q1.class);
                        context.startActivity(hello);
                        //Sauvegarde de la matiere selectionnée
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString(NomMatiere, current_namme);
                        editor.commit();
                        //hello.putExtra(NomMatiere,current_namme);
                    } }, 3600);

            }
        });

        return convertView;
    }
}
