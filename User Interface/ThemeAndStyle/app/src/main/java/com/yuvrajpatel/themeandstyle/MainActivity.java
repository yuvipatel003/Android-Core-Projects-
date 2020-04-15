package com.yuvrajpatel.themeandstyle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Member variables for holding the score
    private int mTeamOneScore;
    private int mTeamTwoScore;
    private TextView mTextViewScoreTeamOne, mTextViewScoreTeamTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewScoreTeamOne = findViewById(R.id.txt_score_one);
        mTextViewScoreTeamTwo = findViewById(R.id.txt_score_two);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        // Change the label of the menu based on the state of the app.
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){
            menu.findItem(R.id.night_mode).setTitle(R.string.str_day_mode);
        } else {
            menu.findItem(R.id.night_mode).setTitle(R.string.str_night_mode);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Check if the correct item was clicked
        if(item.getItemId()==R.id.night_mode){}
            // Get the night mode state of the app.
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            //Set the theme mode for the restarted activity
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_YES);
            }
            // Recreate the activity for the theme change to take effect.
            recreate();
        return true;
    }

    /**
     * Method that handles the onClick of both the decrement buttons
     * @param view The button view that was clicked
     */
    public void decreaseScore(View view) {
        // Get the ID of the button that was clicked
        int viewID = view.getId();
        switch (viewID){
            //If it was on Team 1
            case R.id.image_decrease_team_one:
                //Decrement the score and update the TextView
                mTeamOneScore--;
                mTextViewScoreTeamOne.setText(String.valueOf(mTeamOneScore));
                break;
            //If it was Team 2
            case R.id.image_decrease_team_two:
                //Decrement the score and update the TextView
                mTeamTwoScore--;
                mTextViewScoreTeamTwo.setText(String.valueOf(mTeamTwoScore));
        }
    }

    /**
     * Method that handles the onClick of both the increment buttons
     * @param view The button view that was clicked
     */
    public void increaseScore(View view) {
        //Get the ID of the button that was clicked
        int viewID = view.getId();
        switch (viewID){
            //If it was on Team 1
            case R.id.image_increase_team_one:
                //Increment the score and update the TextView
                mTeamOneScore++;
                mTextViewScoreTeamOne.setText(String.valueOf(mTeamOneScore));
                break;
            //If it was Team 2
            case R.id.image_increase_team_two:
                //Increment the score and update the TextView
                mTeamTwoScore++;
                mTextViewScoreTeamTwo.setText(String.valueOf(mTeamTwoScore));
        }
    }
}
