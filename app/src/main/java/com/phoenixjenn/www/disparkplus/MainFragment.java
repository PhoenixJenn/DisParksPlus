package com.phoenixjenn.www.disparkplus;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        //// Set the text on a TextView object using a resource ID
        //TextView msgTextView = (TextView) findViewById(R.id.msg);
        //msgTextView.setText(R.string.hello_message);
        return inflater.inflate(R.layout.main, container, false);
    }
}
