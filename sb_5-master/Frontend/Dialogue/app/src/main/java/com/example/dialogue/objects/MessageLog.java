package com.example.dialogue.objects;

import com.example.dialogue.objects.Messages;

import java.util.ArrayList;
/**
 * @author Jacob Nett
 */
public class MessageLog {
    private ArrayList messages;
    private String recipient;
    public MessageLog(String user){
        messages = new ArrayList<Messages>();
        this.recipient = user;
    }

    /**
     * adds a message to the log
     * @param message
     */
    public void addMessage(Messages message){
        messages.add(message);
    }

    /**
     * gets the recipient associated with the log
     * @return
     */
    public String getRecipient(){
        return recipient;
    }

    /**
     * gets the most recent message in the log
     * @return
     */
    public  String getMostRecentMessage(){
        String retVal;
        if((messages.size()-1) == -1){
            return "";
        }
        Messages message = (Messages) messages.get(messages.size()-1);
        if(message.getData().length() > 20){
            retVal = message.getData().substring(0, 19);
            retVal += "...";
        }
        else{
            retVal = message.getData();
            }
        return retVal;
    }

    /**
     * returns the logs array list
     * @return
     */
    public void clearlog(){
        messages = new ArrayList<MessageLog>();
    }
    public ArrayList<Messages> getLog(){
        return messages;
    }

    /**
     * set the objects arraylist to the provide
     * @param messages
     */
    public void AddAllMessages(ArrayList<Messages> messages){
        this.messages = messages;
    }

    /**
     * removes message at index at returns the removed object
     * @param index
     * @return
     */
    public Object removeMessage(int index){
        return messages.remove(index);
    }

    /**
     * returns number of messages in the mess
     * @return
     */
    public int logSize(){
        return messages.size();
    }
}
