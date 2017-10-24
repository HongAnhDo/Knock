package com.example.hongdo.knock.models;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hongdo.knock.R;
import com.example.hongdo.knock.adapters.CustomPagerAdapter;
import com.hado.indicatorlibrary.IndicatorView;

/**
 * Created by Win 8.1 Version 2 on 13/10/2017.
 */

public class DFragment extends DialogFragment {
    ViewPager viewPager;
    FragmentManager manager;
    Button btnClose;
    IndicatorView indicatorView;
    int mCurrentPosition = 0;


    @Override
    public void onStart()
    {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_show_picture, container);


        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        int[] mResources = {
                R.drawable.bg1,
                R.drawable.bg2,
                R.drawable.bg3,
        };
        CustomPagerAdapter customPagerAdapter = new CustomPagerAdapter(this.getContext(), mResources);
        viewPager.setCurrentItem(0);

        viewPager.setAdapter(customPagerAdapter);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(viewPager, true);
//        Tạo pager cicler
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                mCurrentPosition = position;
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//                if (mCurrentPosition == 0)
//                    viewPager.setCurrentItem(4, false);
//                if (mCurrentPosition == 4)
//                    viewPager.setCurrentItem(1, false);
//            }
//        });

//          Dùng indicator thay tavlayout
//        indicatorView = view.findViewById(R.id.indicator);
//        try {
//            indicatorView.setViewPager(viewPager);
//        } catch (PagesLessException e) {
//            e.printStackTrace();
//        }
        btnClose = (Button)view .findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        return view;
    }


    @Override
    public void show(FragmentManager manager, String tag) {
        this.manager = manager;
        super.show(manager, tag);
    }
}