package com.janicki.zach.todays_trivia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.*;
import java.util.Date;

public class MainActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //create listeners for buttons
        Button buttonTrivia = (Button) findViewById(R.id.buttonTrivia);
        buttonTrivia.setOnClickListener(this);
        Button buttonAbout = (Button) findViewById(R.id.buttonAbout);
        buttonAbout.setOnClickListener(this);
        /*Button buttonStats = (Button) findViewById((R.id.buttonStats));
        buttonStats.setOnClickListener(this);
        useless();
        Context context = getApplicationContext();
        CharSequence text = "Hello toast!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();*/

    }

    private void useless() {
        /*File file = new File("stats.txt");
        String filePath = file.getAbsolutePath();
        if (!file.exists()) {
            String filename = "stats.txt";
            String string[] = {"0" + "\n", "0" + "\n", "0" + "\n", "0" + "\n", "0" + "\n",
                    "0" + "\n", "0" + "\n", "0" + "\n", "0" + "\n", "0" + "\n",};
            FileOutputStream outputStream;

            try {

                outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                for (int i = 0; i < string.length; i++)
                    outputStream.write(string[i].getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        Button buttonTrivia = (Button) findViewById(R.id.buttonTrivia);
        switch (v.getId()) {

            /*case R.id.buttonStats:
                Button button = (Button) findViewById(R.id.buttonStats);
                button.setTextColor(Color.BLACK);
                button.setPadding(10, 10, 10, 10);
                // go to stats.txt page
                Intent intentStats = new Intent(MainActivity.this, StatsActivity.class);
                startActivity(intentStats);
                break;*/
            case R.id.buttonTrivia:
                // go to trivia page
                buttonTrivia.setBackgroundDrawable(getResources().getDrawable(R.drawable.white_button100));
                buttonTrivia.setTextColor(Color.WHITE);
                Intent intentTrivia = new Intent(MainActivity.this, TriviaActivity.class);
                startActivity(intentTrivia);
        }
    }
}
