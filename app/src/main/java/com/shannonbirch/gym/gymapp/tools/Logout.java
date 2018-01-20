package com.shannonbirch.gym.gymapp.tools;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.util.Log;

import com.shannonbirch.gym.gymapp.LoginActivity;
import com.shannonbirch.gym.gymapp.MainActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

import static com.shannonbirch.gym.gymapp.tools.CheckToken.checkToken;
import static com.shannonbirch.gym.gymapp.tools.DeleteUserInfo.deleteUserInfo;
import static com.shannonbirch.gym.gymapp.tools.PostToServer.postToServer;
import static com.shannonbirch.gym.gymapp.tools.StoreDetails.storeDetails;

/**
 * Created by shannon on 1/10/18.
 */

public class Logout {

    public static Boolean logout(Context context){

        Log.d("CheckToken", "Checking Token begins");
        final String SHARED_PREF_KEY            = "UserInfo";
        final String SHARED_PREF_USER_ID_KEY    = "uID";
        final String SHARED_PREF_USER_TOKEN_KEY = "uToken";

        SharedPreferences sharedPreferences;
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE);

        if(checkToken(context)){

            String data = "";
            try {// To create a data variable to send to the server
                data = URLEncoder.encode("uID", "UTF-8")
                        + "=" + URLEncoder.encode(sharedPreferences.getString(SHARED_PREF_USER_ID_KEY, null), "UTF-8");


                data += "&" + URLEncoder.encode("uToken", "UTF-8")
                        + "=" + URLEncoder.encode(sharedPreferences.getString(SHARED_PREF_USER_TOKEN_KEY, null), "UTF-8");

            }catch (Exception e) {
                //ToDo: Exception Handling
            }


            try{//To Send the Data

                URL url = new URL("http://gym.shannonbirch.com/phpScripts/Auth/uLogout.php");

                ArrayList<String> response = postToServer(data, url);

                if(response.get(1).equals("Success")){

                    deleteUserInfo(context);
                    return true;

                }else{//There was an error of sorts

                    //ToDo: clean this and handle common responses

                         /**/
                    // Read Server Response
                    for(int i=0; i<response.size();i++) {
                        Log.e("In read line", response.get(i));
                    }

                }






            }catch(Exception e){
                //ToDo: More Exception Handling
                e.printStackTrace();
            }


            deleteUserInfo(context);//Deletes user info that is saved on the phone



            return true;
        }


        return false;

    }
}
