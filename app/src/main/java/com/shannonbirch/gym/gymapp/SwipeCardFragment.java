package com.shannonbirch.gym.gymapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import static com.shannonbirch.gym.gymapp.tools.CheckToken.checkToken;
import static com.shannonbirch.gym.gymapp.tools.PostToServer.postToServer;

/**
 * Created by shannon on 1/15/18.
 */

public class SwipeCardFragment extends Fragment{




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.activity_swipe_card_fragment, container, false);


        Button swipeInButton = v.findViewById(R.id.swipeInButton);
        Button swipeOutButton = v.findViewById(R.id.swipeOutButton);
        // Sets onClick for the loginButton
        swipeInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swipeIn();
            }
        });
        swipeOutButton.setOnClickListener((new View.OnClickListener(){
            @Override
            public void onClick(View view){
                swipeOut();
            }

        }));

        return v;
    }




    //ToDo: Online stuff, connect to online, create a database

    private void swipeIn(){
        swipe(true);

    }

    private void swipeOut(){
        swipe(false);
    }





    private Boolean swipe(Boolean inOrOut){//True for In False for Out
        if(checkToken(getContext())) {

            final String SHARED_PREF_KEY = "UserInfo";
            final String SHARED_PREF_USER_ID_KEY = "uID";
            final String SHARED_PREF_USER_TOKEN_KEY = "uToken";

            SharedPreferences sharedPreferences;
            sharedPreferences = getContext().getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE);

            String uID      = sharedPreferences.getString(SHARED_PREF_USER_ID_KEY, null);
            String token    = sharedPreferences.getString(SHARED_PREF_USER_TOKEN_KEY, null);
            String data;
            URL url;


            try {// To create a data variable to send to the server

                if (inOrOut == true) {//Swipe In
                    url = new URL("http://gym.shannonbirch.com/phpScripts/swipein.php");
                } else {//Swipe Out
                    url = new URL("http://gym.shannonbirch.com/phpScripts/swipeout.php");
                }


                data = URLEncoder.encode("uid", "UTF-8")
                        + "=" + URLEncoder.encode(uID, "UTF-8");


                data += "&" + URLEncoder.encode("token", "UTF-8")
                        + "=" + URLEncoder.encode(token, "UTF-8");

                ArrayList<String> response;
                response = postToServer(data, url);

                    if(response.get(0).equals("Success")){
                        Log.d("Swipe Card Fragment", "Swipe Succeeded");

                        return true;
                    }else{//Something went Wrong
                        Log.e("Swipe Card Fragment","Error failed to swipe in");
                        for(int i=0; i<response.size();i++) {

                            Log.e("Swipe: In read line"+String.valueOf(i), response.get(i));

                        }
                        return false;
                    }


            } catch (Exception e) {
                e.printStackTrace();
                //ToDo: Exception Handling
            }

        }else{//Not Logged In, return user to splash screen
            startActivity(new Intent(getContext(), AppStartScreenActivity.class));
            return null;
        }
        return null;
    }

}
