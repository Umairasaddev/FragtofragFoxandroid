package com.example.fragtofragfoxandroid.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fragtofragfoxandroid.R;

import org.w3c.dom.Text;


public class Fragment1 extends Fragment {


    Button btf1;
    EditText etf1;
    public Fragment1() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_1, container, false);

        etf1 = view.findViewById(R.id.etf1);
        btf1 = view.findViewById(R.id.btf1);


        getParentFragmentManager().setFragmentResultListener("datafrom2", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String data = result.getString("df2");
                TextView textView=view.findViewById(R.id.tvf2);
                textView.setText(data);

                //
                Fragment fragment = new Fragment(); // replace your custom fragment class
                Bundle bundle = new Bundle();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                bundle.putString("key","value"); // use as per your need
                fragment.setArguments(bundle);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(viewID,fragment);
                fragmentTransaction.commit();
            }
        });
        btf1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle result = new Bundle();
                result.putString("df1",etf1.getText().toString());
                getParentFragmentManager().setFragmentResult("datafrom1",result);
                etf1.setText("");
            }
        });



        return view;
    }

    private FragmentManager getSupportFragmentManager() {
    };
}