package com.example.hongdo.knock.databases;

import com.example.hongdo.knock.models.EventDay;
import com.example.hongdo.knock.utils.Util;

import java.util.ArrayList;

/**
 * Created by Win 8.1 Version 2 on 24/10/2017.
 */

public class Test {
    public  static ArrayList<EventDay> getListEventDay(){
        ArrayList<EventDay> list = new ArrayList<EventDay>();
        EventDay ev1 = new EventDay("assignment week 2", "View", "08:00", "09:00GMT", Util.ConverStringToDate("24/10/2017"));
        EventDay ev2 = new EventDay("Test English", "Part 5-6 Reading, part 2 Test 3 ", "16:00", "17:00GMT", Util.ConverStringToDate("25/10/2017"));
        EventDay ev3 = new EventDay("Meeting", "With Ngoc Anh ", "10:00", "11:00GMT", Util.ConverStringToDate("25/10/2017"));
        EventDay ev4 = new EventDay("Test03", "BT tuan 2", "16:00", "17:00GMT", Util.ConverStringToDate("26/10/2017"));
        list.add(ev1);
        list.add(ev2);
        list.add(ev3);
        list.add(ev4);


        return list;
    }
}
