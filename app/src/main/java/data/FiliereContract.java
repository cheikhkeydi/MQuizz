package data;

import android.provider.BaseColumns;

public class FiliereContract {

    public static class FiliereEntry implements BaseColumns{

        public static final String TABLE_FQUEST = "Filiere";
        public static final String KEY_FID = "id";
        public static final String KEY_FNOM = "nom";
    }
}
