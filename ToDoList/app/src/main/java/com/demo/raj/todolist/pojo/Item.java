package com.demo.raj.todolist.pojo;

/**
 * Created by raj on 2/16/2016.
 */
public class Item {

    // description of the item
    private String mDescription;

    // name of the location
    private String mLocationName;

    // date of the item
    private String mDate;

    // time of the item
    private String mTime;

    public Item(){}

    /**
     * Description of the To-Do
     * @param desc
     */
    public void setDescription(String desc){

        this.mDescription = desc;
    }

    /**
     * Location at which To-Do needs to be performed
     * @param location
     */
    public void setLocationName(String location){

        this.mLocationName = location;
    }

    /**
     * Date at which To-Do needs to be performed
     * @param date
     */
    public void setDate(String date){

        this.mDate = date;
    }

    /**
     * Time at which To-Do needs to be performed
     * @param time
     */
    public void setTime(String time){

        this.mTime = time;
    }

    /**
     * Description of item
     * @return
     */
    public String getDescription(){

        return this.mDescription;
    }

    /**
     * Location of item
     * @return
     */
    public String getLocationName(){

        return this.mLocationName;
    }

    /**
     * Time of item
     * @return time
     */
    public String getTime(){

        return this.mTime;
    }

    /**
     * Date of item
     * @return date
     */
    public String getDate(){

        return this.mDate;
    }
}
