package com.shannonbirch.gym.gymapp.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by shannon on 1/10/18.
 */

public class StoreDetails {



    public static void storeDetails(String userID, String token, Context context){

        Log.d("Store Details", "Started");
        final String SHARED_PREF_KEY = "UserInfo";

        SharedPreferences sharedPreferences;

        sharedPreferences = context.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("uID", userID);
        editor.putString("uToken", token);
        editor.commit();


    }
}
