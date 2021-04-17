package adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.mquizz.MainActivity;
import com.example.mquizz.R;
import com.example.mquizz.acceuil;
import com.example.mquizz.matiere;

import java.net.Inet4Address;
import java.util.List;

import Table.Filiere;

import static android.content.Intent.getIntent;
import static android.content.Intent.getIntentOld;

public class NiveauAdapter extends BaseAdapter {
    public List<Filiere> filiereList;
    public Context context;
    public LayoutInflater layoutInflater;
    public final static String SELECTFILIERE = "SELECTFILIERE";
    public final static String SELECTNIVEAU1 = "SELECTNIVEAU1";

    public NiveauAdapter(Context context,List<Filiere> filiereList){
        this.filiereList = filiereList;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return filiereList.size();
    }

    @Override
    public Filiere getItem(int position) {
        return filiereList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.adapter_niveau,null);

        //lister les elements
        Filiere nom_filiere = filiereList.get(position);
        final String nom = nom_filiere.getNOM().toString();

        Button btn_filiere = (Button) convertView.findViewById(R.id.item_niveau);
        btn_filiere.setText(nom);

        // LinearLayout filiere_layout = (LinearLayout) convertView.findViewById(R.id.filiere_layout);
        btn_filiere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Vous avez choisi "+nom,Toast.LENGTH_LONG).show();
                Intent hello = new Intent(context, com.example.mquizz.matiere.class);
                //Renvoyer Filiere selectionner dans matiere
                hello.putExtra(SELECTFILIERE,nom);
                context.startActivity(hello);
            }
        });





        return convertView;
    }
}
