package com.example.dialogue.objects;
        import java.text.DateFormat;
        import java.text.SimpleDateFormat;
        import java.util.Date;

/**
 * @author Jacob Nett
 */
public class Messages {
    private String data;
    private String date;
    private String sender;

    /**
     * contructor set date auto
     * @param data
     * @param sender
     */
    public Messages(String data, String sender){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        this.date = dateFormat.format(date);
        this.data = data;
        this.sender = sender;
    }

    /**
     * constuctor uses provided date
     * @param data
     * @param date
     * @param sender
     */
    public Messages(String data,String date ,String sender){
        this.date = date;
        this.data = data;
        this.sender = sender;
    }

    /**
     * getter method
     * @return the message stored by the object
     */
    public String getData(){
        return data;
    }

    /**
     * getter method
     * @return returns who sent the message
     */
    public String getSender(){
        return sender;
    }

    /**
     * getter method
     * @return the date attached to the message
     */
    public String getDate(){
        return date;
    }
}
