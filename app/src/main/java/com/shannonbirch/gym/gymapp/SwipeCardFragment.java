package com.shannonbirch.gym.gymapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by shannon on 1/15/18.
 */

public class SwipeCardFragment extends Fragment{




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.activity_swipe_card_fragment, container, false);
        return v;
    }




    //ToDo: Online stuff, connect to online, create a database

    private void swipeIn(){



    }

    private void swipeOut(){

    }





    private String swipe(Boolean inOrOut){

        return null;

    }

}
