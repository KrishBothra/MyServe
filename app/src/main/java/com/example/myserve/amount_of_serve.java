package com.example.myserve;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link amount_of_serve#newInstance} factory method to
 * create an instance of this fragment.
 */
public class amount_of_serve extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button enter;

    EditText editText;

    public amount_of_serve() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment amount_of_serve.
     */
    // TODO: Rename and change types and number of parameters
    public static amount_of_serve newInstance(String param1, String param2) {
        amount_of_serve fragment = new amount_of_serve();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_amount_of_serve, container, false);
        enter = view.findViewById(R.id.enter);
        editText = view.findViewById(R.id.editTextText);
        getData();
        return view;
    }

    public void getData(){
        enter.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendDataToActivity(String.valueOf(editText.getText()));

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

    // Usage example
//    private void someMethod(SdatatoSend) {
//        // Pass data to the activity
//        sendDataToActivity(dataToSend);
//    }
}