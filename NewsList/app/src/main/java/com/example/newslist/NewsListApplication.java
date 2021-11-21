package com.example.newslist;
import android.app.Application;
//import android.content.Context;

public class NewsListApplication extends Application{
    private String login;
    //private static Context context;
    /**public void OnCreate(){
        super.onCreate();
        this.login = null;
        //NewsListApplication.context = getApplicationContext();
    }**/

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}

