package com.example.hongdo.knock.models;

import java.util.Date;

/**
 * Created by Win 8.1 Version 2 on 18/10/2017.
 */

public class EventDay {

    private String strPickUp;
    private String strDes;
    private String timeDepart;
    private String timeArriver;
    private Date dateEvent;

    public EventDay(String strPickUp, String strDes, String timeDepart, String timeArriver, Date dateEvent) {
        this.strPickUp = strPickUp;
        this.strDes = strDes;
        this.timeDepart = timeDepart;
        this.timeArriver = timeArriver;
        this.dateEvent = dateEvent;
    }

    public EventDay() {
    }

    public String getStrPickUp() {
        return strPickUp;
    }

    public void setStrPickUp(String strPickUp) {
        this.strPickUp = strPickUp;
    }

    public String getStrDes() {
        return strDes;
    }

    public void setStrDes(String strDes) {
        this.strDes = strDes;
    }

    public String getTimeDepart() {
        return timeDepart;
    }

    public void setTimeDepart(String timeDepart) {
        this.timeDepart = timeDepart;
    }

    public String getTimeArriver() {
        return timeArriver;
    }

    public void setTimeArriver(String timeArriver) {
        this.timeArriver = timeArriver;
    }

    public Date getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(Date dateEvent) {
        this.dateEvent = dateEvent;
    }


}
