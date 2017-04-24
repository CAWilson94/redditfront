package reddit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import db.DbContract;
import db.DbHelper;
import db.DbWrapper;

/**
 * Created by charl on 24/04/2017.
 */

public class RedditDbWrapper extends DbWrapper {
    public static void addRedditPost(Context context, Reddit redditPost) {
        // Reference to writable DB
        SQLiteDatabase db = getWritableDatabase(context);
        // Create content values to add key "column/value"
        ContentValues values = new ContentValues();
        values.put(DbContract.RedditEntry.COLUMN_NAME_TITLE, redditPost.getTitle());
        values.put(DbContract.RedditEntry.COLUMN_NAME_SUBREDDIT, redditPost.getSubreddit());
        //values.put(DbContract.RedditEntry.COLUMN_NAME_DATE, String.valueOf(redditPost.getDate()));
        values.put(DbContract.RedditEntry.COLUMN_NAME_SCORE, redditPost.getScore());
        // Insert
        db.insert(DbContract.RedditEntry.TABLE_NAME, null, values);
        // Close
        db.close();
    }

    public static List<Reddit> getAllRedditPosts(Context context) {
        List<Reddit> foodWheels = new LinkedList<Reddit>();
        // Reference to readable db
        SQLiteDatabase db = getReadableDatabase(context);
        // Build the query
        String query = "SELECT  * FROM " + DbContract.RedditEntry.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        // Go over each row, build foodWheel and add it to the list
        Reddit redditPost = null;
        if (cursor.moveToFirst()) {
            do {
                redditPost = new Reddit();
                redditPost.setTitle(cursor.getString(1)); // May change
                redditPost.setScore(Integer.parseInt(cursor.getString(3))); // May change
                // add foodWheel to foodWheels
                foodWheels.add(redditPost);
            } while (cursor.moveToNext());
        }
        db.close();
        // return all foodWheels
        return foodWheels;
    }
}
