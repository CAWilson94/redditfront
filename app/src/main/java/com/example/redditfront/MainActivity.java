package com.example.redditfront;

import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import reddit.GetRedditPostsTask;

public class MainActivity extends AppCompatActivity {
    Context contextMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contextMain = this;
        String image = "https://ih0.redbubble.net/image.120730129.7037/flat,800x800,075,t.u1.jpg";
        setContentView(R.layout.activity_main);
        Button redditFrontButton = (Button) findViewById(R.id.reddit_front_button);
        ListView listReddit = (ListView) findViewById(R.id.list_reddit);
        ImageView imageView = (ImageView) findViewById(R.id.image_view);

        String url = String.format("https://www.reddit.com/.json");

        new GetRedditPostsTask(listReddit,imageView, contextMain).execute(url);


    }
}
