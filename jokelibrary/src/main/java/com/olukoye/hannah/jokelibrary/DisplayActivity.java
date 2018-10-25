package com.olukoye.hannah.jokelibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        TextView textview = (TextView) findViewById(R.id.joke_text);

        //Retrieve the joke from the Intent Extras
        String JokeResult = null;
        Intent intent = getIntent();
        JokeResult = intent.getStringExtra(getString(R.string.jokeText));

        if (JokeResult != null) {
            textview.setText(JokeResult);
        } else {
            textview.setText("Searching for the joke!");
        }
    }

}
