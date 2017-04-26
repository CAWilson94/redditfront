package reddit;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.data;
import static com.example.redditfront.R.id.score;


/**
 * Created by charl on 26/04/2017.
 */

public class GetRedditPostsTask extends AsyncTask<String, Void, String> {

    ListView listView;
    Context context;

    public GetRedditPostsTask(ListView listView, Context context) {
        this.listView = listView;
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        String jsonShit = "NONE";
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();

            String inputString;
            while ((inputString = bufferedReader.readLine()) != null) {
                builder.append(inputString);
            }

            JSONObject response = new JSONObject(builder.toString());
            response = response.getJSONObject("data");
            JSONArray children = response.getJSONArray("children");
            jsonShit = children.toString();
            System.out.println(jsonShit);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return jsonShit;
    }


    @Override
    protected void onPostExecute(String temp) {
        String title = null;
        String score = null;
        String subreddit = null;
        String imageURL = null;
        List<Reddit> redditPosts = new ArrayList<Reddit>();
        try {
            JSONArray children = new JSONArray(temp);
            for (int i = 0; i < children.length(); i++) {
                JSONObject data = children.getJSONObject(i).getJSONObject("data");
                title = data.getString("title");
                score = data.getString("score");
                subreddit = data.getString("subreddit");
                imageURL = data.getString("thumbnail");// TODO: 26/04/2017 test this works default and live
                System.out.println(title + " : " + subreddit + " : " + score);
                Reddit redditPost = new Reddit(title, Integer.valueOf(score), subreddit, imageURL);
                redditPosts.add(redditPost);
            }

            ArrayAdapter<Reddit> adapter = new RedditArrayAdapter(context, 0, redditPosts);
            listView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
