package data;

import android.provider.BaseColumns;

public class UserContract {

    public static class UserEntry implements BaseColumns{

        public static final String TABLE_UQUEST = "User";
        public static final String KEY_UID = "id";
        public static final String KEY_UPSEUDO = "pseudo";
        public static final String KEY_USCORE = "score";

    }
}
