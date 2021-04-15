package adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mquizz.MainActivity;
import com.example.mquizz.R;
import com.example.mquizz.acceuil;

import java.util.List;

import Table.Niveau;

public class FiliereAdapter extends BaseAdapter {

    private List<Niveau> niveaus;
    private Context context;
    private LayoutInflater layoutInflater;
    public final static String SELECTNIVEAU = "SELECTNIVEAU";

    public FiliereAdapter(Context context, List<Niveau> filiereList)
    {
        this.context = context;
        this.niveaus = filiereList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return niveaus.size();
    }

    @Override
    public Niveau getItem(int position) {
        return niveaus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.filiere_adapter,null);

        //lister les elements
        Niveau nom_filiere = niveaus.get(position);
        final String nom = nom_filiere.getANNEE();

        Button btn_filiere = (Button) convertView.findViewById(R.id.btn_adpaterFiliere);
        btn_filiere.setText(nom);

       // LinearLayout filiere_layout = (LinearLayout) convertView.findViewById(R.id.filiere_layout);


        btn_filiere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Vous avez choisi "+nom,Toast.LENGTH_LONG).show();
                Intent hello = new Intent(context, acceuil.class);
                hello.putExtra(SELECTNIVEAU,nom);
                context.startActivity(hello);
            }
        });


        return convertView;
    }
}
