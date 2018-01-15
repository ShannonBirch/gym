package com.shannonbirch.gym.gymapp.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import static com.shannonbirch.gym.gymapp.tools.CheckToken.checkToken;
import static com.shannonbirch.gym.gymapp.tools.DeleteUserInfo.deleteUserInfo;

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

                //ToDo: Figure out how to revert this when no longer needed
                int SDK_INT = android.os.Build.VERSION.SDK_INT;
                if (SDK_INT > 8)
                {
                    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                            .permitAll().build();
                    StrictMode.setThreadPolicy(policy);


                }



                // Send POST data request

                URLConnection conn = url.openConnection();
                conn.setDoOutput(true);
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write( data );
                wr.flush();


                // Get the server response

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();

                String line = reader.readLine();//First line is blank
                line = reader.readLine();//Second line contains the first line of the response


                if(line.toString().equals("Success")){
                    deleteUserInfo(context);
                    return true;

                }else{//There was an error of sorts

                    //ToDo: clean this and handle common responses
                    sb.append(line);
                         /**/
                    // Read Server Response
                    while((line = reader.readLine()) != null)
                    {
                        Log.e("In read line", line.toString());
                        // Append server response in string
                        sb.append(line+"\n");
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
