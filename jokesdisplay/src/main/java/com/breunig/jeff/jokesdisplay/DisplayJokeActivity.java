package com.breunig.jeff.jokesdisplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DisplayJokeActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_joke);
        textView = (TextView) findViewById(R.id.joke_text_view);

        String joke = getIntent().getStringExtra(getString(R.string.joke_extra));
        textView.setText(joke);
    }
}
