package reddit;

import android.os.AsyncTask;
import android.widget.TextView;

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

/**
 * Created by charl on 26/04/2017.
 */

public class GetRedditPostsTask extends AsyncTask<String, Void, String> {

    TextView textView;

    public GetRedditPostsTask(TextView textView) {
        this.textView = textView;
    }

    @Override
    protected String doInBackground(String... strings) {
        String title = "UNDEFINED";
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

            JSONObject data = children.getJSONObject(0).getJSONObject("data");
            title = data.getString("title");

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return title;
    }


    @Override
    protected void onPostExecute(String temp) {
        textView.setText("Latest Post Title: " + temp);
    }
}
