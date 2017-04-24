package com.example.redditfront;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import reddit.Reddit;
import reddit.RedditArrayAdapter;
import reddit.RedditDbWrapper;
import volley.Volley;

import static reddit.RedditDbWrapper.getAllRedditPosts;


public class MainActivity extends AppCompatActivity {
    Context contextMain;
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contextMain = this;
        setContentView(R.layout.activity_main);
        Button redditFrontButton = (Button) findViewById(R.id.reddit_front_button);
        final ListView listReddit = (ListView) findViewById(R.id.list_reddit);
        redditFrontButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Volley.reQuest(contextMain);
                List<Reddit> reddit_list = RedditDbWrapper.getAllRedditPosts(contextMain);
                System.out.println(reddit_list.get(0).getTitle() + "_________________________________");
                ArrayAdapter<Reddit> adapter = new RedditArrayAdapter(contextMain, 0, reddit_list);
                listReddit.setAdapter(adapter);
            }
        });
    }
}
