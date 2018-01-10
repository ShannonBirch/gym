package com.shannonbirch.gym.gymapp.tools;

import android.widget.EditText;

/**
 * Created by shannon on 1/9/18.
 *
 * This class allows other classes to use the method without rewriting it each time
 *
 */

public class IsEditTextEmpty {
    public static boolean isEditTextEmpty(EditText inEditText){
        return inEditText.getText().toString().trim().length()==0;
    }

}
