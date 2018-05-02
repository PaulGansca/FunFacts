package com.ganscapaul.funfacts;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class FunFactsActivity extends AppCompatActivity {
    public static final String TAG = FunFactsActivity.class.getSimpleName();
    private static final String KEY_FACT = "KEY_FACT";
    private static final String KEY_COLOR = "KEY_COLOR";
    private FactBook factBook = new FactBook();
    private ColorWheel colorWheel = new ColorWheel();
    //Declare view variables
    private TextView factTextView;
    private Button showFactButton;
    private RelativeLayout backgroundColour;
    private String fact = factBook.facts[0];
    private int color = Color.parseColor(colorWheel.colors[8]);

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        
        outState.putString(KEY_FACT, fact);
        factTextView.setText(fact);
        outState.putInt(KEY_COLOR, color);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        fact = savedInstanceState.getString(KEY_FACT);
        color = savedInstanceState.getInt(KEY_COLOR);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fun_facts);
        factTextView = findViewById(R.id.factTextView);
        showFactButton = findViewById(R.id.showFactButton);
        backgroundColour = findViewById(R.id.backgroundColour);
        backgroundColour.setBackgroundColor(color);
        showFactButton.setTextColor(color);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fact = factBook.getFact();

                //update the screen with our new fact
                factTextView.setText(fact);
                color = colorWheel.getColor();
                backgroundColour.setBackgroundColor(color);
                showFactButton.setTextColor(color);
            }
        };
        showFactButton.setOnClickListener(listener);

        Toast.makeText(this, "Yay! Finished my first toast!", Toast.LENGTH_SHORT).show();

        Log.d(TAG, "We're logging from the onCreate() method!");

    }
}
