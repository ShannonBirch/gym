package com.shannonbirch.gym.gymapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SubMenu;

public class MainActivity extends AppCompatActivity {


    //Defining Variables
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Below method sets the toolbar as the app bar for the activity
        myToolbar = findViewById(R.id.tool_bar);
        // Sets the toolbar as the action bar (the settings button didn't show without this line)
        setSupportActionBar(myToolbar);

        // Setting the title of the activity in the Toolbar
        TextView toolBarTitle = myToolbar.findViewById(R.id.toolbar_title) ;
        toolBarTitle.setText(R.string.home_menu);

        //Initializing NavigationView
        navigationView = findViewById(R.id.navigation_view);
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Checking if the item is in checked state or not, if not make it in checked state
                if(menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()){

                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.home:
                        Toast.makeText(getApplicationContext(),"Home Selected",Toast.LENGTH_SHORT).show();
                        HomeFragment homeFragment = new HomeFragment();
                        android.support.v4.app.FragmentTransaction homeFragmentTransaction = getSupportFragmentManager().beginTransaction();
                        homeFragmentTransaction.replace(R.id.frame, homeFragment).commit();
                        return true;

                    // For rest of the options we just show a toast on click

                    case R.id.reminder:
                        Toast.makeText(getApplicationContext(),"Reminder Selected",Toast.LENGTH_SHORT).show();
                        RemindersFragment remindersFragment = new RemindersFragment();
                        android.support.v4.app.FragmentTransaction reminderFragmentTransaction = getSupportFragmentManager().beginTransaction();
                        reminderFragmentTransaction.replace(R.id.frame, remindersFragment).commit();
                        return true;

                    default:
                        Toast.makeText(getApplicationContext(),"Somethings Wrong",Toast.LENGTH_SHORT).show();
                        return true;

                }
            }
        });

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,myToolbar,R.string.openDrawer, R.string.closeDrawer){

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };


        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

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
                startActivity(new Intent(MainActivity.this, AppStartScreenActivity.class));

                return true;
        }

        return (super.onOptionsItemSelected(item));
    }
}