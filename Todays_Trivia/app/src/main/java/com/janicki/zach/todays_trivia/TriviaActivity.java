package com.janicki.zach.todays_trivia;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class TriviaActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);
        Button history = (Button) findViewById(R.id.buttonHistory);
        history.setOnClickListener(this);
        Button entertainment = (Button) findViewById(R.id.buttonEntertainment);
        entertainment.setOnClickListener(this);
        Button sports = (Button) findViewById(R.id.buttonSports);
        sports.setOnClickListener(this);
        Button menu = (Button) findViewById(R.id.buttonTriviaMenu);
        menu.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stats, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        Button buttonHistory = (Button) findViewById(R.id.buttonHistory);
        Button buttonEntertainment = (Button) findViewById(R.id.buttonEntertainment);
        Button buttonSports = (Button) findViewById(R.id.buttonSports);
        Button buttonMenu = (Button) findViewById(R.id.buttonTriviaMenu);
        switch (v.getId()) {
            case R.id.buttonHistory:
                buttonHistory.setBackgroundDrawable(getResources().getDrawable(R.drawable.white_button100));
                buttonHistory.setTextColor(Color.WHITE);
                Intent history = new Intent(TriviaActivity.this, GameActivity.class);
                history.putExtra("com.zachjanicki.CATEGORY", 1);
                startActivity(history);
                break;
            case R.id.buttonEntertainment:
                buttonEntertainment.setBackgroundDrawable(getResources().getDrawable(R.drawable.white_button100));
                buttonEntertainment.setTextColor(Color.WHITE);
                Intent entertainment = new Intent(TriviaActivity.this, GameActivity.class);
                entertainment.putExtra("com.zachjanicki.CATEGORY", 2);
                startActivity(entertainment);
                break;
            case R.id.buttonSports:
                buttonSports.setBackgroundDrawable(getResources().getDrawable(R.drawable.white_button100));
                buttonSports.setTextColor(Color.WHITE);
                Intent sports = new Intent(TriviaActivity.this, GameActivity.class);
                sports.putExtra("com.zachjanicki.CATEGORY", 3);
                startActivity(sports);
                break;
            case R.id.buttonTriviaMenu:
                buttonMenu.setBackgroundDrawable(getResources().getDrawable(R.drawable.white_button100));
                buttonMenu.setTextColor(Color.WHITE);
                Intent back = new Intent(TriviaActivity.this, MainActivity.class);
                startActivity(back);
                break;

        }
    }
}