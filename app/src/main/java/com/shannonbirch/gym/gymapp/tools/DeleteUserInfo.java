package com.shannonbirch.gym.gymapp.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by shannon on 1/12/18.
 */

public class DeleteUserInfo {

    public static boolean deleteUserInfo(Context context){
        final String SHARED_PREF_KEY            = "UserInfo";
        final String SHARED_PREF_USER_ID_KEY    = "uID";
        final String SHARED_PREF_USER_TOKEN_KEY = "uToken";

    try {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if(sharedPreferences.contains(SHARED_PREF_USER_ID_KEY)||sharedPreferences.contains(SHARED_PREF_USER_TOKEN_KEY)) {

            editor.clear();
            editor.commit();
            Log.d("DeleteUSerInfo", "Deleted info");
            return true;
        }else{//There wasn't anything to delete???
            editor.commit();
            Log.d("DeleteUSerInfo", "Nothing to Delete");
            return false;
        }


    }catch(Exception e){
        //ToDo: Seriously handle some exceptions
    }


        return false;
    }
}
