package com.example.myserve;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.

 * create an instance of this fragment.
 */
public class type_of_serve extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button topspin,slice,flat;
    public type_of_serve() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment first_or_second.
     */
    // TODO: Rename and change types and number of parameters


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first_or_second, container, false);
        topspin = view.findViewById(R.id.topspin);
        slice = view.findViewById(R.id.slice);
        flat = view.findViewById(R.id.flat);
        getData();
        getData2();
        getData3();
        return view;
    }

    private void getData2() {
        topspin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendDataToActivity("topspin");
                    }
                }
        );
    }

    private void getData(){
        slice.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendDataToActivity("slice");
                    }
                }
        );
    }

    private void getData3(){
        flat.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendDataToActivity("flat");
                    }
                }
        );
    }
    private DataPassListener dataPassListener;

    // Attach the interface to the fragment


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dataPassListener = (DataPassListener) context;
    }

    // Method to pass data to the activity
    private void sendDataToActivity(String data) {
        dataPassListener.onDataPass(data);
    }
}