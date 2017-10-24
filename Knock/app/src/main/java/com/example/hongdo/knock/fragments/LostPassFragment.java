package com.example.hongdo.knock.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.hongdo.knock.R;
import com.example.hongdo.knock.utils.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Win 8.1 Version 2 on 13/10/2017.
 */

public class LostPassFragment extends Fragment {

    @BindView(R.id.btn_back)
    ImageView btnBack;
    Unbinder unbinder;
    FragmentManager fm;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fm = getActivity().getSupportFragmentManager();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_forgot_pass, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        Util.replaceFragment(new LoginFragment(), fm);

    }


}
