package com.example.morga.myteam;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FragmentPartido extends Fragment implements View.OnClickListener {

    Button btnEmpezarPartido;

    public FragmentPartido() {
        // Required empty public constructor
    }

    public static FragmentPartido newInstance() {
        FragmentPartido fragment = new FragmentPartido();
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
        return inflater.inflate(R.layout.fragment_partido, container, false);
    }


    //*****
    // Metodo para Cambiar de Activity
    //*****
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnEmpezarPartido = (Button)view.findViewById(R.id.buttonEmpezarPartido);

        btnEmpezarPartido.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EstadisticasActivity2.class);

                startActivity(intent);
            }
        });


    }


    @Override
    public void onClick(View v) {

    }
}