package com.example.dialogue.ui;

import android.content.Context;
/**
 * @author Jacob Nett
 */
public interface IView {
    public void toastText(String s);
    public void changeScreen(Class c);
    public void setUser(String u);
    public Context getContext();
}
