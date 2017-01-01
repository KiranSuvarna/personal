package com.journaldev.youplusmegaevent;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;


import java.util.HashMap;


public class SessionManager {
    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    Editor editor;
 //   LoginBean loginBean=new LoginBean();
    // Context
    Context _context;
   // private static SessionManager SessionManager;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "sba";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
   // public static final String KEY_ACCESS_TOKEN = "access_token";

    // Email address (make variable public to access from outside)
   // public static final String KEY_USERID = "userId";
  //  public static final String KEY_first = "firstName";
   // public static final String KEY_lastname = "lastName";
    public static final String KEY_protomernumber= "protomernumber";
    public static final String KEY_location="location";
    public static final String KEY_area="area";

    boolean isFirstTime = true;
    SessionManager sessionManager;
    public SessionManager() {
        System.out.println("inside SessionManager class constrator");
    }

    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
pref.getBoolean("time",true);
        System.out.println("inside constroutor of session manager");


    }


   public void forfirsttime()
    {
         pref = _context.getSharedPreferences("isFirstTime", 0);
        isFirstTime = pref.getBoolean("isFirstTime", true);
        Log.d("first time logined", "===" + isFirstTime);
    }


    /**
     * Create login session
     * */

    /*public void createLoginSession(String protomernumber,
                                   String location,String area){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        //loginBean.oauth.setAccess_token(access_token);
        // Storing name in pref
        //   editor.putString(KEY_ACCESS_TOKEN, access_token);
        System.out.println(protomernumber+"inside session manager class");
        // Storing email in pref
      *//*  editor.putString(KEY_USERID, userid);
        editor.putString(KEY_first, firstname);
        editor.putString(KEY_lastname, lastname);*//*
        editor.putString(KEY_protomernumber, protomernumber);
        editor.putString(KEY_location, location);
        editor.putString(KEY_area, area);


        // commit changes
        editor.commit();
    }*/
    public void createLoginSession(String protomernumber
                                   ){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
        //loginBean.oauth.setAccess_token(access_token);
                // Storing name in pref
             //   editor.putString(KEY_ACCESS_TOKEN, access_token);
        System.out.println(protomernumber+"inside session manager class");
        // Storing email in pref
      /*  editor.putString(KEY_USERID, userid);
        editor.putString(KEY_first, firstname);
        editor.putString(KEY_lastname, lastname);*/
        editor.putString(KEY_protomernumber, protomernumber);
       /* editor.putString(KEY_location, location);
        editor.putString(KEY_area, area);
*/

        // commit changes
        editor.commit();
    }


    public void createLoginSessionUpdate(String protomernumber){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);
       // loginBean.oauth.setAccess_token(updateaccess_token);
        // Storing name in pref
        editor.putString(KEY_protomernumber, protomernumber);
        System.out.println(protomernumber + "inside update manager class");
        // Storing email in pref
        /*editor.putString(KEY_USERID, userid);
        editor.putString(KEY_first, firstname);
        editor.putString(KEY_lastname, lastname);
        editor.putString(KEY_username, userName);*/
        //editor.putString(KEY_REFRESH_ACCESS_TOKEN, refresh_access_token);

        // commit changes
        editor.commit();
    }
    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public Boolean checkLogin(){
        // Check login status
      //  !this.isLoggedIn()
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, PromoterPageActivit.class);


            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
//

            return  false;
        }
        return  true;

    }



    /**
     * Get stored session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_protomernumber, pref.getString(KEY_protomernumber, null));

        // user email id
        user.put(KEY_location, pref.getString(KEY_location, null));
        user.put(KEY_area, pref.getString(KEY_area, null));


     /*   user.put(KEY_first,pref.getString(KEY_first,null));
        user.put(KEY_lastname,pref.getString(KEY_lastname,null));
        user.put(KEY_username,pref.getString(KEY_username,null));
        user.put(KEY_REFRESH_ACCESS_TOKEN,pref.getString(KEY_REFRESH_ACCESS_TOKEN,null));*/
        // return user
        return user;
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences

        editor.clear();
        editor.commit();


        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, PromoterPageActivit.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);

    }

    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        Log.d("aaa"
                +getClass().getSimpleName(),"ssss");
        return pref.getBoolean(IS_LOGIN, false);

    }
}
