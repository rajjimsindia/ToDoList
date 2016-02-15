package com.demo.raj.todolist.pojo;

/**
 * Created by raj on 2/16/2016.
 */
public class Item {

    // description of the item
    private String mDescription;

    // name of the location
    private String mLocationName;

    // timestamp of the item
    private String mTimestamp;

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
     * Time at which To-Do needs to be performed
     * @param timestamp
     */
    public void setTimestamp(String timestamp){

        this.mTimestamp = timestamp;
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
     * @return
     */
    public String getTimestamp(){

        return this.mTimestamp;
    }
}
