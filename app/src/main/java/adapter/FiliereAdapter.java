package adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.mquizz.R;

import java.util.List;

import classe.Filiere;

public class FiliereAdapter extends BaseAdapter {

    private List<Filiere> filiereList;
    private Context context;
    private LayoutInflater layoutInflater;

    public FiliereAdapter(Context context, List<Filiere> filiereList)
    {
        this.context = context;
        this.filiereList = filiereList;
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
        convertView = layoutInflater.inflate(R.layout.filiere_adapter,null);

        //lister les elements
        Filiere nom_filiere = filiereList.get(position);
        final String nom = nom_filiere.getNom();

        Button btn_filiere = (Button) convertView.findViewById(R.id.btn_adpaterFiliere);
        btn_filiere.setText(nom);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Vous avez selectionner "+nom, Toast.LENGTH_SHORT).show();
            }
        });


        return convertView;
    }
}
