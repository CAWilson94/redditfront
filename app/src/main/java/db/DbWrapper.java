package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by charl on 24/04/2017.
 */

public class DbWrapper {

    protected static SQLiteDatabase getReadableDatabase(Context context) {
        DbHelper mDbHelper = new DbHelper(context);
        return mDbHelper.getReadableDatabase();
    }

    protected static SQLiteDatabase getWritableDatabase(Context context) {
        DbHelper mDbHelper = new DbHelper(context);
        return mDbHelper.getWritableDatabase();
    }
}
