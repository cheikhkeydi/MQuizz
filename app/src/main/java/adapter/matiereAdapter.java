package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.mquizz.R;

import java.util.List;

import Table.Matiere;

public class matiereAdapter extends BaseAdapter {

    private Context context;
    private List<Matiere> matiere_itemList;
    private LayoutInflater layoutInflater;

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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.adapter_matiere, null);

        //mettre le nom des items
        Matiere matiere_item = getItem(position);
        String current_namme = matiere_item.getNOM();


        //mettre le item dans le view
        Button item_name = convertView.findViewById(R.id.item_matiere);
        item_name.setText(current_namme);
        
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Vous avez choisi "+current_namme, Toast.LENGTH_SHORT).show();

            }
        });

        return convertView;
    }
}
