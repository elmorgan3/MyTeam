package com.example.morga.myteam;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class FragmentEntreno extends Fragment {

    public FragmentEntreno() {
        // Required empty public constructor
    }

    public static FragmentEntreno newInstance() {
        FragmentEntreno fragment = new FragmentEntreno();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entreno, container, false);
    }


}
