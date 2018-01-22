package com.shannonbirch.gym.gymapp.tools;

import android.os.StrictMode;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by shannon on 1/20/18.
 */

public class PostToServer {

    public static ArrayList<String> postToServer(String data, URL url){
        ArrayList<String> response = new ArrayList<String>();

        //ToDo: Figure out how to revert this when no longer needed
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);


        }

        // Send POST data request
        try {
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();

            // Get the server response

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();

            String line;
            while((line = reader.readLine()) != null) {

                // Append server response in string
                response.add(line);
            }

        }catch(Exception e){
            //ToDo: Exceoption handling again
        }







        return response;
    }
}
