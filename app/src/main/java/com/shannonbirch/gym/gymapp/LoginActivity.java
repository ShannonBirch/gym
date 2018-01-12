package com.shannonbirch.gym.gymapp;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import static com.shannonbirch.gym.gymapp.tools.IsEditTextEmpty.isEditTextEmpty;
import static com.shannonbirch.gym.gymapp.tools.StoreDetails.storeDetails;

public class LoginActivity extends AppCompatActivity {

    static final String SHARED_PREF_KEY = "UserInfo";

    private EditText emailField, passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        // Below method sets the toolbar as the app bar for the activity
        Toolbar myToolbar = findViewById(R.id.tool_bar);

        emailField = findViewById(R.id.login_email);
        passwordField = findViewById(R.id.login_password);

        // Setting the title of the activity in the Toolbar
        TextView toolBarTitle = myToolbar.findViewById(R.id.toolbar_title) ;
        toolBarTitle.setText(R.string.login);

        Button loginButton = findViewById(R.id.loginButton);

        // Sets onClick for the loginButton
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               login();

            }
        });
    }


    private void login(){
        Toast invalidDetailsToast;
        String invalidDetailsString="";
        if(isEditTextEmpty(emailField)){
            invalidDetailsString="Email is required";

        }else if(isEditTextEmpty(passwordField)){
            invalidDetailsString="Password is required";

        }else {


            String email  = emailField.getText().toString();
            String pass   = passwordField.getText().toString();
            String data   = "";


            try {// To create a data variable to send to the server
                data = URLEncoder.encode("email", "UTF-8")
                        + "=" + URLEncoder.encode(email, "UTF-8");


                data += "&" + URLEncoder.encode("pass", "UTF-8")
                        + "=" + URLEncoder.encode(pass, "UTF-8");

            }catch (Exception e) {
                //ToDo: Exception Handling
            }

            String responseCode = "";
            BufferedReader reader=null;


            try{// To send the data

                //ToDo: write uLogin.php when I'm not on Amazon books wifi
                URL url = new URL("http://gym.shannonbirch.com/phpScripts/Auth/uLogin.php");



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

                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder sb = new StringBuilder();

                String line = reader.readLine();//Frst line is blank
                line = reader.readLine();//Second line contains the first line of the response


                if(line.toString().equals("Success")){
                    //ToDo: Store userID and token

                    String userID = reader.readLine().toString();
                    String token = reader.readLine().toString();
                    Context context = getApplicationContext();

                    storeDetails(userID, token, LoginActivity.this);
                    
                    //Open home screen
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));

                    return;

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



                responseCode = sb.toString();
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            finally
            {
                try{

                    reader.close();
                }
                //ToDo: Handle exceptions
                catch(Exception ex) {}
            }


            //ToDo:Figure out where that first \n came from
            //ToDo: Clean
            if(responseCode.equals("\nSuccess\n")) {//User has been registered
                //Open home screen
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                return;
            }else if(responseCode.equals("Invalid")) {
                invalidDetailsString = "Invalid Details. Please confirm that the email address and password correct\n" +
                        "Remember they are case sensitive.";

            }else {//PHP script returns something other than "Success"
                Log.e("ResponseCode:", responseCode);
                invalidDetailsString = "Incorrect system response " + responseCode;
            }





        }

        invalidDetailsToast= Toast.makeText(getApplicationContext(), invalidDetailsString, Toast.LENGTH_LONG);
        invalidDetailsToast.show();
    }





}
