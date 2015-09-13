package com.janicki.zach.todays_trivia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class StatsActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        Button buttonMenu = (Button) findViewById(R.id.buttonMenu);
        buttonMenu.setOnClickListener(this);
        createFile();
        populateStatsView();
    }

    private void createFile() {
        File statsFile = new File("statsFile.txt");

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
        //Go back to menu, this will be the only option
        Intent intent = new Intent(StatsActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void populateStatsView() {
        //Declaration and assignment of all statistic variable as well as creation of textViews and their corresponding text changes
        //populate statistics fields
        ArrayList<String> stats = new ArrayList<String>();
        String m;
        int i = 0;
        String ret = "";
        try {
            InputStream inputStream =  getResources().openRawResource(R.raw.statistics);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (m = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(m);
                    stats.add(m);
                    System.out.println(stats.get(i));
                    i++;
                }

                inputStream.close();
                ret = stringBuilder.toString();
                //System.out.println(ret);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int hCorrect, hAttempted, eCorrect, eAttempted, sCorrect, sAttempted, mostDayStreak, currentDayStreak, playedToday, threeCorrect, lastDate;
        hCorrect = Integer.parseInt(stats.get(0));
        hAttempted = Integer.parseInt(stats.get(1));
        eCorrect = Integer.parseInt(stats.get(2));
        eAttempted = Integer.parseInt(stats.get(3));
        sCorrect = Integer.parseInt(stats.get(4));
        sAttempted = Integer.parseInt(stats.get(5));
        mostDayStreak = Integer.parseInt(stats.get(6));
        currentDayStreak = Integer.parseInt(stats.get(7));
        playedToday = Integer.parseInt(stats.get(8));
        threeCorrect = Integer.parseInt(stats.get(9));
        lastDate = Integer.parseInt(stats.get(10));
        System.out.println(lastDate);
        System.out.println(hCorrect + "" + hAttempted);
        TextView textViewH = (TextView) findViewById(R.id.textViewHistory);
        TextView textViewE = (TextView) findViewById(R.id.textViewEntertainmnet);
        TextView textViewS = (TextView) findViewById(R.id.textViewSports);
        TextView textViewMostDayStreak = (TextView) findViewById(R.id.textViewMostDays);
        TextView textViewCurrentDayStreak = (TextView) findViewById(R.id.textViewCurrentDayStreak);
        TextView textViewFavorite = (TextView) findViewById(R.id.textViewFavorite);
        TextView textViewBest = (TextView) findViewById(R.id.textViewBest);
        double hPercentage =  ((double)hCorrect / (double)hAttempted) * 100;
        double ePercentage =  ((double)eCorrect / (double)eAttempted) * 100;
        double sPercentage =  ((double)sCorrect / (double)sAttempted) * 100;
        System.out.println(hPercentage);
        textViewH.setText(new DecimalFormat("##.##").format(hPercentage) + "%");
        textViewE.setText(new DecimalFormat("##.##").format(ePercentage) + "%");
        textViewS.setText(new DecimalFormat("##.##").format(sPercentage) + "%");
        textViewMostDayStreak.setText("" + mostDayStreak);
        textViewCurrentDayStreak.setText("" + currentDayStreak);
        if (hAttempted > eAttempted && hAttempted > sAttempted) {
            textViewFavorite.setText("History");
        } else if (eAttempted > hAttempted && eAttempted > sAttempted) {
            textViewFavorite.setText("Entertainment");
        } else if (sAttempted > hAttempted && sAttempted > eAttempted) {
            textViewFavorite.setText("Sports");
        } else {
            textViewFavorite.setText("Not Established");
        }
        if (hPercentage > sPercentage && hPercentage > ePercentage) {
            textViewBest.setText("History");
        } else if (ePercentage > sPercentage && ePercentage > hPercentage) {
            textViewBest.setText("Entertainment");
        } else if (sPercentage > ePercentage && sPercentage > hPercentage) {
            textViewBest.setText("Sports");
        } else {
            textViewBest.setText("Not Established");
        }
        String a = Integer.toString(hCorrect);
        String b = Integer.toString(hAttempted);
        Context context = getApplicationContext();
        CharSequence text = a+b;
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
