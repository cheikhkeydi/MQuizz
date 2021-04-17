package data;

import android.provider.BaseColumns;

import Table.Matiere;

public class MatiereContract {

    public static class MatiereEntry implements BaseColumns{

        public static final String TABLE_MQUEST = "Matiere";
        public static final String KEY_MID = "id";
        public static final String KEY_MNOM = "nom";
        public static final String KEY_MNomFiliere = "NomFiliere";
        public static final String KEY_MNomNiveau = "NomNiveau";

    }
}
