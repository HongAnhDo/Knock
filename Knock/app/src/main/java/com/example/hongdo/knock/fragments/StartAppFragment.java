package com.example.hongdo.knock.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hongdo.knock.models.DFragment;
import com.example.hongdo.knock.R;
import com.example.hongdo.knock.utils.Util;

/**
 * Created by Win 8.1 Version 2 on 13/10/2017.
 */

public class StartAppFragment extends Fragment {
    DFragment dialog;

    ImageButton ibtnShowDiaglog;
    Button btnCreateAccount;
    TextView tvLoginApp;
    LoginFragment loginFragment;
    CreateAccountFragment fragmentCreateAccount;
    FragmentManager fm;

    public static StartAppFragment newInstance() {
        StartAppFragment startAppFragment = new StartAppFragment();
        return startAppFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fm = getActivity().getSupportFragmentManager();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_start_app, container, false);

        tvLoginApp = (TextView)view.findViewById(R.id.tv_login_app);
        tvLoginApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginFragment = new LoginFragment();
                Util.replaceFragment(loginFragment, fm);
            }
        });

        ibtnShowDiaglog = (ImageButton) view.findViewById(R.id.btn_show_dialog);
        ibtnShowDiaglog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });



        btnCreateAccount = (Button) view.findViewById(R.id.btn_creat_account);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentCreateAccount = new CreateAccountFragment();
                Util.replaceFragment(fragmentCreateAccount, fm);
            }

            
        });

        return  view;
    }


    public void showDialog() {

        DFragment dFragment = new DFragment();
        dFragment.show(fm, "Dialog Fragment");

    }
    
    



}
