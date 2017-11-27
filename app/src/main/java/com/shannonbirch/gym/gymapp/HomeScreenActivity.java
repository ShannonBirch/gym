package com.shannonbirch.gym.gymapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        // Below method sets the toolbar as the app bar for the activity
        Toolbar myToolbar = findViewById(R.id.my_toolbar);

        // Sets the toolbar as the action bar (the settings button didn't show without this line)
        setSupportActionBar(myToolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_log_out:
                // Brings you to "App Start Activity" when "Log Out" buttons is pressed, it also should Log Out the user
                startActivity(new Intent(HomeScreenActivity.this, AppStartScreenActivity.class));

                return true;
        }

        return (super.onOptionsItemSelected(item));
    }
}
