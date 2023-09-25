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
 * Use the {@link first_or_second#newInstance} factory method to
 * create an instance of this fragment.
 */
public class first_or_second extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button firstServe,secondServe;
    public first_or_second() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment first_or_second.
     */
    // TODO: Rename and change types and number of parameters
    public static first_or_second newInstance(String param1, String param2) {
        first_or_second fragment = new first_or_second();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

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
        firstServe = view.findViewById(R.id.firstServe);
        secondServe = view.findViewById(R.id.secondServe);
        firstServe.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendDataToActivity("1");
                    }
                }
        );

        secondServe.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendDataToActivity("2");
                    }
                }
        );
        return view;
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