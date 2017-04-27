package volley;

import android.content.Context;
import android.provider.Settings;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import reddit.Reddit;
import reddit.RedditDbWrapper;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;
import static com.android.volley.toolbox.Volley.newRequestQueue;

/**
 * Created by charl on 24/04/2017.
 * <p>
 * Need to get reddit list out of here but different shit..
 */

public class Volley {
    public static String url = "http://httpbin.org/get?site=code&network=tutsplus";
    private static String frontPageURL = "https://www.reddit.com/.json";

    public static void reQuest(final Context context) {

        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, frontPageURL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // the response is already constructed as a JSONObject!
                        String title = null;
                        String score = null;
                        String sub = null;
                        try {
                            response = response.getJSONObject("data");
                            JSONArray children = response.getJSONArray("children");
                            for (int i = 0; i < children.length(); i++) {
                                JSONObject data = children.getJSONObject(i).getJSONObject("data");
                                title = data.getString("title");
                                score = data.getString("score");
                                sub = data.getString("subreddit");
                                System.out.println(title + " : " + sub + " : " + score);
                                RedditDbWrapper.addRedditPost(context, new Reddit(title, Integer.parseInt(score), sub, sub));
                            }

                            System.out.println(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        Request<JSONObject> bob = newRequestQueue(context).add(jsonRequest);
    }
}
