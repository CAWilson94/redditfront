package reddit;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.redditfront.R;

import java.util.List;

/**
 * Created by charl on 24/04/2017.
 */

public class RedditArrayAdapter extends ArrayAdapter<Reddit> {

    private Context context;
    private List<Reddit> redditPosts;

    public RedditArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Reddit> objects) {
        super(context, resource, objects);
        this.context = context;
        this.redditPosts = objects;
    }

    // Called when rendering the list
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the property we are displaying
        Reddit redditPost = redditPosts.get(position);
        //get the inflater and inflate the XML layout for each item
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.reddit_list, null);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(redditPost.getTitle());
        TextView score = (TextView) view.findViewById(R.id.score);
        score.setText(Integer.toString(redditPost.getScore()));
        TextView subreddit = (TextView) view.findViewById(R.id.subreddit);
        subreddit.setText(redditPost.getSubreddit());
        return view;
    }

}
