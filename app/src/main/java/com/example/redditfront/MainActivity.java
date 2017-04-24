package com.example.redditfront;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONException;

import java.util.List;

import reddit.RedditFront;
import volley.Volley;


public class MainActivity extends AppCompatActivity {
    Context contextMain;
    Volley vol = new Volley();

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
                 vol.reQuest(contextMain);
                //System.out.print(reddit_list.get(0) + "LIST HERE");
               /* ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(contextMain,
                        android.R.layout.simple_list_item_1,
                        reddit_list);
                listReddit.setAdapter(arrayAdapter);*/
            }
        });
    }
}
