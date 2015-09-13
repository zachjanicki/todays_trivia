package com.janicki.zach.todays_trivia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;



/**
 * Created by zachjanicki on 6/11/15.
 */
public class GameActivity extends Activity implements View.OnClickListener {
    Random rand = new Random();
    // a button is given a value of true or false regarding whether or not it contains the correct answer
    public static boolean a = false;
    public static boolean b = false;
    public static boolean c = false;
    public static boolean d = false;
    static Calendar currentDay = GregorianCalendar.getInstance();
    static int year = currentDay.get(GregorianCalendar.YEAR);
    int month = currentDay.get(GregorianCalendar.MONTH);
    int day = currentDay.get(GregorianCalendar.DAY_OF_MONTH);
    public static int answerLetter;
    public static int category;
    public static int dateCode, realDateCode;
    public static int hCorrect, hAttempted, eCorrect, eAttempted, sCorrect, sAttempted, mostDayStreak, currentDayStreak, playedToday, totalDaysPlayed, lastDate;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Bundle categoryBundle = getIntent().getExtras();
        category = categoryBundle.getInt("com.zachjanicki.CATEGORY");
        System.out.println(category);
        Button buttonA = (Button) findViewById(R.id.buttonA);
        buttonA.setOnClickListener(this);
        Button buttonB = (Button) findViewById(R.id.buttonB);
        buttonB.setOnClickListener(this);
        Button buttonC = (Button) findViewById(R.id.buttonC);
        buttonC.setOnClickListener(this);
        Button buttonD = (Button) findViewById(R.id.buttonD);
        buttonD.setOnClickListener(this);
        Button buttonE = (Button) findViewById(R.id.buttonE);
        buttonE.setOnClickListener(this);
        createQuestion();
        readAndPopulateStats();

    }

    private void readAndPopulateStats() {
        ArrayList<String> stats = new ArrayList<String>();
        String n;
        int k = 0;
        File file = new File("stats.txt");
        String filePath = file.getAbsolutePath();
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.statistics);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((n = bufferedReader.readLine()) != null) {
                    stringBuilder.append(n);
                    stats.add(n);
                    //System.out.println(stats.txt.get(i));
                    k++;
                }

                inputStream.close();
                //ret = stringBuilder.toString();
                //System.out.println(ret);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Declaration and assignment of all statistic variable as well as creation of textViews and their corresponding text changes


        hCorrect = Integer.parseInt(stats.get(0));
        hAttempted = Integer.parseInt(stats.get(1));
        eCorrect = Integer.parseInt(stats.get(2));
        eAttempted = Integer.parseInt(stats.get(3));
        sCorrect = Integer.parseInt(stats.get(4));
        sAttempted = Integer.parseInt(stats.get(5));
        mostDayStreak = Integer.parseInt(stats.get(6));
        currentDayStreak = Integer.parseInt(stats.get(7));
        playedToday = Integer.parseInt(stats.get(8));
        totalDaysPlayed = Integer.parseInt(stats.get(9));
        lastDate = Integer.parseInt(stats.get(10));
        if (category == 1) {
            hAttempted++;
        } else if (category == 2) {
            eAttempted++;
        } else if (category == 3) {
            sAttempted++;
        }
    }

    private void createQuestion() {
        Button buttonA = (Button) findViewById(R.id.buttonA);
        Button buttonB = (Button) findViewById(R.id.buttonB);
        Button buttonC = (Button) findViewById(R.id.buttonC);
        Button buttonD = (Button) findViewById(R.id.buttonD);
        dateCode = 0;
        System.out.println(dateCode);
        System.out.println(month);
        switch (month) {
            case 1:
                dateCode = 0;
                break;
            case 2:
                dateCode = 31;
                break;
            case 3:
                dateCode = 59;
                break;
            case 4:
                dateCode = 90;
                break;
            case 5:
                dateCode = 120;
                break;
            case 6:
                dateCode = 151;
                break;
            case 7:
                dateCode = 181;
                break;
            case 8:
                dateCode = 212;
                break;
            case 9:
                dateCode = 243;
                break;
            case 10:
                dateCode = 273;
                break;
            case 11:
                dateCode = 304;
                break;
            case 12:
                dateCode = 334;
                break;

        }
        dateCode *= 15;
        realDateCode = dateCode + (day * 15) - 15;
        if (category == 1) {
            dateCode += day * 15 - 15;
        } else if (category == 2) {
            dateCode += day * 15 - 10;
        } else if (category == 3) {
            dateCode += day * 15 - 5;
        }
        System.out.println(dateCode);
        String question, A, B, C, D;

        ArrayList<String> buttonFields = new ArrayList<String>();
        String m;
        int i = 0;
        String ret = "";
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.questions);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((m = bufferedReader.readLine()) != null) {
                    stringBuilder.append(m);
                    if (i == dateCode || i == dateCode + 1 || i == dateCode + 2 || i == dateCode + 3 || i == dateCode + 4) {
                        buttonFields.add(m);
                        //System.out.println(buttonFields.get(i - dateCode));
                    }
                    i++;
                }

                inputStream.close();
                ret = stringBuilder.toString();
                //System.out.println(ret);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TextView textViewQuestion = (TextView) findViewById(R.id.textViewQuestion);
        question = buttonFields.get(0);
        A = buttonFields.get(1);
        B = buttonFields.get(2);
        C = buttonFields.get(3);
        D = buttonFields.get(4);
        textViewQuestion.setText(question);
        //put buttons in random order
        answerLetter = rand.nextInt(4) + 1;
        if (answerLetter == 1) {
            //the correct answer should be in A
            buttonA.setText(A);
            buttonB.setText(B);
            buttonC.setText(C);
            buttonD.setText(D);
        } else if (answerLetter == 2) {
            //the correct answer is in B
            buttonA.setText(B);
            buttonB.setText(A);
            buttonC.setText(C);
            buttonD.setText(D);
        } else if (answerLetter == 3) {
            //the correct answer is in C
            buttonA.setText(C);
            buttonB.setText(B);
            buttonC.setText(A);
            buttonD.setText(D);
        } else if (answerLetter == 4) {
            //the correct answer is in D
            buttonA.setText(D);
            buttonB.setText(B);
            buttonC.setText(C);
            buttonD.setText(A);
        }

        System.out.println(buttonFields.get(0));
        System.out.println(day);
        System.out.println(month);
        System.out.println(year);
        System.out.println(dateCode);
        System.out.println(buttonFields.toString());
    }

    @Override
    public void onClick(View v) {

        Button buttonA = (Button) findViewById(R.id.buttonA);
        Button buttonB = (Button) findViewById(R.id.buttonB);
        Button buttonC = (Button) findViewById(R.id.buttonC);
        Button buttonD = (Button) findViewById(R.id.buttonD);
        Button buttonE = (Button) findViewById(R.id.buttonE); //menu button
        buttonA.setClickable(false);
        buttonB.setClickable(false);
        buttonC.setClickable(false);
        buttonD.setClickable(false);
        buttonE.setClickable(true);
        buttonE.setVisibility(View.VISIBLE);

        // a button is given a value of true or false regarding whether or not it contains the correct answer
        a = false;
        b = false;
        c = false;
        d = false;
        if (answerLetter == 1) {
            a = true;
        } else if (answerLetter == 2) {
            b = true;
        } else if (answerLetter == 3) {
            c = true;
        } else if (answerLetter == 4) {
            d = true;
        }
        boolean correctAnswer = false;

        switch (v.getId()) {
            //switch through the 4 answer buttons. The answers will be assigned randomly so each
            //case will have to deal with he randomization of the answers


            case R.id.buttonA:
                if (a) {
                    buttonA.setTextColor(Color.parseColor("#FFFFFF"));
                    //make A green
                    buttonA.setBackgroundDrawable(getResources().getDrawable(R.drawable.green_button));
                    correctAnswer = true;
                    buttonB.setVisibility(View.INVISIBLE);
                    buttonC.setVisibility(View.INVISIBLE);
                    buttonD.setVisibility(View.INVISIBLE);
                } else {
                    correctAnswer = false;
                    if (b) {
                        //make A red, B green and delete other two buttons
                        buttonA.setBackgroundDrawable(getResources().getDrawable(R.drawable.red_button_tiny));
                        buttonB.setBackgroundDrawable(getResources().getDrawable(R.drawable.green_button));
                        buttonC.setVisibility(View.INVISIBLE);
                        buttonD.setVisibility(View.INVISIBLE);
                    } else if (c) {
                        // A red, C green delete B and D
                        buttonA.setBackgroundDrawable(getResources().getDrawable(R.drawable.red_button_tiny));
                        buttonB.setVisibility(View.INVISIBLE);
                        buttonC.setBackgroundDrawable(getResources().getDrawable(R.drawable.green_button));
                        buttonD.setVisibility(View.INVISIBLE);
                    } else if (d) {
                        // A red, D green ...
                        buttonA.setBackgroundDrawable(getResources().getDrawable(R.drawable.red_button_tiny));
                        buttonB.setVisibility(View.INVISIBLE);
                        buttonC.setVisibility(View.INVISIBLE);
                        buttonD.setBackgroundDrawable(getResources().getDrawable(R.drawable.green_button));
                    }
                    buttonA.setTextColor(Color.parseColor("#FFFFFF"));
                }
                break;
            case R.id.buttonB:
                if (b) {
                    buttonB.setTextColor(Color.parseColor("#FFFFFF"));
                    //make Green
                    correctAnswer = true;
                    buttonA.setVisibility(View.INVISIBLE);
                    buttonB.setBackgroundDrawable(getResources().getDrawable(R.drawable.green_button));
                    buttonC.setVisibility(View.INVISIBLE);
                    buttonD.setVisibility(View.INVISIBLE);
                } else {
                    correctAnswer = false;
                    if (a) {
                        //B red, A green
                        buttonA.setBackgroundDrawable(getResources().getDrawable(R.drawable.green_button));
                        buttonB.setBackgroundDrawable(getResources().getDrawable(R.drawable.red_button_tiny));
                        buttonC.setVisibility(View.INVISIBLE);
                        buttonD.setVisibility(View.INVISIBLE);
                    } else if (c) {
                        //B red. C green
                        buttonA.setVisibility(View.INVISIBLE);
                        buttonC.setBackgroundDrawable(getResources().getDrawable(R.drawable.green_button));
                        buttonD.setVisibility(View.INVISIBLE);
                    } else if (d) {
                        buttonA.setVisibility(View.INVISIBLE);
                        buttonB.setBackgroundDrawable(getResources().getDrawable(R.drawable.red_button_tiny));
                        buttonC.setVisibility(View.INVISIBLE);
                        buttonD.setBackgroundDrawable(getResources().getDrawable(R.drawable.green_button));
                    }
                }
                break;
            case R.id.buttonC:
                if (c) {
                    buttonC.setTextColor(Color.parseColor("#FFFFFF"));
                    correctAnswer = true;
                    buttonA.setVisibility(View.INVISIBLE);
                    buttonB.setVisibility(View.INVISIBLE);
                    buttonC.setBackgroundDrawable(getResources().getDrawable(R.drawable.green_button));
                    buttonD.setVisibility(View.INVISIBLE);
                } else {
                    correctAnswer = false;
                    if (a) {
                        //C red, A green
                        buttonA.setBackgroundDrawable(getResources().getDrawable(R.drawable.green_button));
                        buttonB.setVisibility(View.INVISIBLE);
                        buttonC.setBackgroundDrawable(getResources().getDrawable(R.drawable.red_button_tiny));
                        buttonD.setVisibility(View.INVISIBLE);
                    } else if (b) {
                        buttonA.setVisibility(View.INVISIBLE);
                        buttonB.setBackgroundDrawable(getResources().getDrawable(R.drawable.green_button));
                        buttonC.setBackgroundDrawable(getResources().getDrawable(R.drawable.red_button_tiny));
                        buttonD.setVisibility(View.INVISIBLE);
                    } else if (d) {
                        buttonA.setVisibility(View.INVISIBLE);
                        buttonB.setVisibility(View.INVISIBLE);
                        buttonC.setBackgroundDrawable(getResources().getDrawable(R.drawable.red_button_tiny));
                        buttonD.setBackgroundDrawable(getResources().getDrawable(R.drawable.green_button));
                    }
                }
                break;
            case R.id.buttonD:
                if (d) {
                    buttonD.setTextColor(Color.parseColor("#FFFFFF"));
                    //make green
                    correctAnswer = true;
                    buttonB.setVisibility(View.INVISIBLE);
                    buttonC.setVisibility(View.INVISIBLE);
                    buttonA.setVisibility(View.INVISIBLE);
                    buttonD.setBackgroundDrawable(getResources().getDrawable(R.drawable.green_button));
                } else {
                    correctAnswer = false;
                    if (a) {
                        buttonA.setBackgroundDrawable(getResources().getDrawable(R.drawable.green_button));
                        buttonB.setVisibility(View.INVISIBLE);
                        buttonC.setVisibility(View.INVISIBLE);
                        buttonD.setBackgroundDrawable(getResources().getDrawable(R.drawable.red_button_tiny));
                    } else if (b) {
                        buttonA.setVisibility(View.INVISIBLE);
                        buttonB.setBackgroundDrawable(getResources().getDrawable(R.drawable.green_button));
                        buttonC.setVisibility(View.INVISIBLE);
                        buttonD.setBackgroundDrawable(getResources().getDrawable(R.drawable.red_button_tiny));
                    } else if (c) {
                        buttonA.setVisibility(View.INVISIBLE);
                        buttonB.setVisibility(View.INVISIBLE);
                        buttonC.setBackgroundDrawable(getResources().getDrawable(R.drawable.green_button));
                        buttonD.setBackgroundDrawable(getResources().getDrawable(R.drawable.red_button_tiny));
                    }
                }
                break;
            case R.id.buttonE:
                //save all the data and go back to the main menu
                if ((correctAnswer) && category == 1) {
                    hCorrect++;
                } else if ((correctAnswer) && category == 2) {
                    eCorrect++;
                } else if ((correctAnswer) && category == 3) {
                    sCorrect++;
                }
                if (lastDate != realDateCode) {
                    currentDayStreak++;
                }
                lastDate = realDateCode;
                if (currentDayStreak > mostDayStreak) currentDayStreak = mostDayStreak;
                playedToday = 1;
                totalDaysPlayed++;
                String[] statsFile = {Integer.toString(hCorrect), Integer.toString(hAttempted), Integer.toString(eCorrect), Integer.toString(eAttempted),
                        Integer.toString(sCorrect), Integer.toString(sAttempted), Integer.toString(mostDayStreak), Integer.toString(currentDayStreak),
                        Integer.toString(playedToday), Integer.toString(totalDaysPlayed), Integer.toString(lastDate)};
                String filename = "stats.txt";
                FileOutputStream outputStream;

                try {
                    outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                    for (int i = 0; i <statsFile.length; i ++ ) {
                        outputStream.write((statsFile[i] + "\n").getBytes());
                        outputStream.close();
                    }
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                buttonE.setTextColor(Color.WHITE);
                buttonE.setBackgroundDrawable(getResources().getDrawable(R.drawable.white_button100));
                Intent back = new Intent(GameActivity.this, TriviaActivity.class);
                startActivity(back);
                break;
        }
    }
}

