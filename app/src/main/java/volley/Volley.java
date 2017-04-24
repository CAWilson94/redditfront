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

import static com.android.volley.toolbox.Volley.newRequestQueue;

/**
 * Created by charl on 24/04/2017.
 * <p>
 * Need to get reddit list out of here but different shit..
 */

public class Volley {
    public static String url = "http://httpbin.org/get?site=code&network=tutsplus";
    private static String frontPageURL = "https://www.reddit.com/.json";
    public List<String> redditTitles = new ArrayList<String>();

    public List<String> reQuest(Context context) {

        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, frontPageURL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        List<String> redditInner = new ArrayList<>();
                        // the response is already constructed as a JSONObject!
                        try {
                            response = response.getJSONObject("data");
                            JSONArray children = response.getJSONArray("children");
                            for (int i = 0; i < children.length(); i++) {
                                JSONObject data = children.getJSONObject(i).getJSONObject("data");
                                System.out.print(data.getString("title"));
                                redditTitles.add(data.getString("title"));
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
        System.out.print("BOB");
        System.out.print(bob);
        return redditTitles;
    }
}
