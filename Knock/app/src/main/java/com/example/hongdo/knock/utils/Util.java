package com.example.hongdo.knock.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.hongdo.knock.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Win 8.1 Version 2 on 23/10/2017.
 */

public class Util {
    public static void replaceFragment(Fragment fragment, FragmentManager fm){
        String backStateName = fragment.getClass().getName();
        String fragmentTag = backStateName;

        boolean fragmentPopped = fm.popBackStackImmediate(backStateName, 0);
        if (!fragmentPopped && fm.findFragmentByTag(fragmentTag) == null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.setCustomAnimations(R.anim.anim_slide_in_from_left, R.anim.anim_slide_out_from_left);
            ft.replace(R.id.frame_fragment, fragment, fragmentTag);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }

    public static Date ConverStringToDate(String str) {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  date;
    }
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
