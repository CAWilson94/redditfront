package reddit;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

import static android.R.attr.data;
import static com.android.volley.VolleyLog.TAG;

/**
 * Created by charl on 24/04/2017.
 * <p>
 * <p>
 * result
 * -- data
 * ---- children
 * ------ data
 * -------- author
 * -------- thumbnail
 * -------- created_utc
 * -------- score
 * -------- title
 */

public class RedditFront {
    private static String frontPageURL = "https://www.reddit.com/.json";
    private static String jsonResponse = "";


    public static void redditFrontAll(final Context context) throws JSONException {
        Log.d("REDDIT", "got here");
        
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                frontPageURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject data = response.getJSONObject("data");
                    JSONArray children = data.getJSONArray("children");
                    for (int i = 0; i < children.length(); i++) {
                        JSONObject topic = children.getJSONObject(i);

                        String author = topic.getString("author");
                        String imageUrl = topic.getString("thumbnail");
                        String postTime = topic.getString("created_utc");
                        String rScore = topic.getString("score");
                        String title = topic.getString("title");
                        Log.d("REDDIT_TITLE:", title);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context,
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }

            }
        },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                        Toast.makeText(context,
                                error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
        Log.d("JSON:", jsonResponse);
    }

    public static void redditFront() {
    }
}
