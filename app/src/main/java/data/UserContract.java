package data;

import android.provider.BaseColumns;

public class UserContract {

    public static class UserEntry implements BaseColumns{

        public static final String TABLE_QUEST = "User";
        public static final String KEY_ID = "id";
        public static final String KEY_PSEUDO = "pseudo";
        public static final String KEY_SCORE = "score";

    }
}
