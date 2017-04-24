package db;

import android.provider.BaseColumns;

/**
 * Created by charl on 24/04/2017.
 */

public class DbContract {

    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "reddit.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String DOUBLE_TYPE = " DOUBLE";
    private static final String REAL_TYPE = " REAL";
    private static final String DATE_TYPE = " DATE";
    private static final String STRING_TYPE = " STRING";
    private static final String INTEGER_TYPE = " INTEGER ";
    private static final String COMMA_SEP = " , ";

    private DbContract() {
    }

    public static class RedditEntry implements BaseColumns {
        public static final String TABLE_NAME = "reddit";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBREDDIT = "subreddit";
        public static final String COLUMN_NAME_SCORE = "score";
        public static final String COLUMN_NAME_DATE = "date";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_SUBREDDIT + TEXT_TYPE + COMMA_SEP +
                COLUMN_NAME_SCORE + INTEGER_TYPE + COMMA_SEP +
                COLUMN_NAME_DATE + DATE_TYPE + " ) ";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

}
