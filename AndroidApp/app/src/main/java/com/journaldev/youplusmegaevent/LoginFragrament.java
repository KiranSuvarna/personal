package com.journaldev.youplusmegaevent;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatDelegate;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.journaldev.youplusmegaevent.newprovider.LoginProvider;
import com.journaldev.youplusmegaevent.newprovider.UserProvider;
import com.journaldev.youplusmegaevent.newprovider.bean.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lenovo on 9/27/2016.
 */
public class LoginFragrament extends Fragment

    {

        SessionManager session;

        YouPlusButton btnNext;
        public LoginFragrament() {

    }


        public static final String MyPREFERENCES = "MyPrefs" ;
        public static final String AREAName = "areanamekey";
        public static final String Phone = "phoneKey";
        public static final String LOCATIONNAME = "locationKey";
       // SharedPreferences sharedpreferences;
        private Uri userUri;
        SharedPreferences sharedpreferences;
        YouPlusButton btnsubmit,btnclear;
       EditText phoneeditText;
        EditText areaeditText;
        EditText locationeditText;

        SharedPreferences.Editor editor;
        private final static String TAG = LoginFragrament.class.getSimpleName();

        LoginDataBaseAdapter loginDataBaseAdapter;
        String phonenumber,location,area;
        View rootView;

        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle
        savedInstanceState){

        rootView = inflater.inflate(R.layout.firtpage_promoter, container, false);

            sharedpreferences= getActivity().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        Cursor cu = getActivity().getContentResolver().query(LoginProvider.CONTENT_URI, null, null, null, null);
        Page page = new Page(cu, Login.class);
        //   userDetail.setText(page.getContent().toString());
        //ormlite core method
        List<Login> listdata = page.getContent();
        System.out.println("-----------------DDDDDDDDDDDDDDDDDDDdr--------" + listdata.size());


            LoginProvider getobj = LoginProvider.getInstanceforUserContet(getContext());


            LoginProvider.UserDBHelper gg = getobj.getInstance(getActivity());
            List<Login> listofpromoters=gg.getlistofUser();
            System.out.println("List OF user-------"+listofpromoters.size());



            btnsubmit=(YouPlusButton)rootView.findViewById(R.id.btnSave);
            btnclear=(YouPlusButton)rootView.findViewById(R.id.btnClear);
            phoneeditText=(EditText)rootView.findViewById(R.id.protometnumberEditText);
            locationeditText=(EditText)rootView.findViewById(R.id.locationEditText);

            areaeditText=(EditText)rootView.findViewById(R.id.areaEditText);


            if (listofpromoters.size()>=1)
            {

                String phonenumber = phoneeditText.getText().toString();
                String location = locationeditText.getText().toString();
                String areaname = areaeditText.getText().toString();


                try {        String recivedphone = sharedpreferences.getString(Phone, null);

                    if (recivedphone!=null) {
                        phoneeditText.setHint("");
                        phoneeditText.setFocusable(false);
                        phoneeditText.setClickable(false);
                        //phoneeditText.cl
                        phoneeditText.setText(recivedphone);
                    }
                    else if (recivedphone==null)
                    {
                        phoneeditText.setHint("Enter Promoter Phone Number");
                        phoneeditText.setFocusable(true);
                        phoneeditText.setClickable(true);
                    }
                }catch (Exception e)
                {

                    e.printStackTrace();
                }

                try {
                    String locationa = sharedpreferences.getString(LOCATIONNAME, null);
                    if (locationa!=null) {
                        locationeditText.setHint("");
                        locationeditText.setFocusable(false);
                        locationeditText.setClickable(false);
                        //phoneeditText.cl
                        // String locationa = sharedpreferences.getString(LOCATIONNAME, null);
                        locationeditText.setText(locationa);
                    }else if (locationa==null)
                    {
                        locationeditText.setHint("Enter Location of Event");
                        locationeditText.setFocusable(true);
                        locationeditText.setClickable(true);


                    }
                }catch (Exception e)
                {

                    e.printStackTrace();
                }


                try {
                    String recivedlocation = sharedpreferences.getString(AREAName, null);
                    if (recivedlocation!=null) {
                        areaeditText.setHint("");
                        areaeditText.setFocusable(false);
                        areaeditText.setClickable(false);

                        areaeditText.setText(recivedlocation);
                    }else
                    {

                        areaeditText.setHint("Enter Area Name of Event");
                        areaeditText.setFocusable(true);
                        areaeditText.setClickable(true);


                    }


                }catch (Exception e)
                {

                    e.printStackTrace();
                }

                btnsubmit.setClickable(false);
                btnsubmit.setFocusable(false);
                btnsubmit.setEnabled(false);
                btnsubmit.setText("promoter Info Already exists");
                btnsubmit.setBackgroundDrawable(getActivity().getResources().getDrawable(R.drawable.ok));

               // btnsubmit.val

            }else


            {

                btnsubmit.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
// get The User name and Password
                        //phoneeditText.setText("");


                        if (phoneeditText.getText().toString().equals("")) {
                            phoneeditText.setError("Please Enter Phone Number");
                            Toast toast = Toast.makeText(getContext(), "Please Enter Phone Number ", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                        } else if (locationeditText.getText().toString().equals("")) {
                            locationeditText.setError("Please Enter Location Name");
                            Toast toast = Toast.makeText(getContext(), "Please Enter Location Name", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();

                        } else if (areaeditText.getText().toString().equals("")) {
                            areaeditText.setError("Please Enter Area Name");
                            Toast toast = Toast.makeText(getContext(), "Please Enter Area Name", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();

                        } else {




                            saveUser();



                        }
                        //edt.setText("http://");


                    }
                });

            }
try {
/*
    String phonenumber = phoneeditText.getText().toString();
    String location = locationeditText.getText().toString();
    String areaname = areaeditText.getText().toString();


    try {        String recivedphone = sharedpreferences.getString(Phone, null);

        if (recivedphone!=null) {
            phoneeditText.setHint("");
            phoneeditText.setFocusable(false);
            phoneeditText.setClickable(false);
            //phoneeditText.cl
            phoneeditText.setText(recivedphone);
        }
        else if (recivedphone==null)
        {
            phoneeditText.setHint("Enter Promoter Phone Number");
            phoneeditText.setFocusable(true);
            phoneeditText.setClickable(true);
        }
    }catch (Exception e)
    {

        e.printStackTrace();
    }

    try {
        String locationa = sharedpreferences.getString(LOCATIONNAME, null);
if (locationa!=null) {
    locationeditText.setHint("");
    locationeditText.setFocusable(false);
    locationeditText.setClickable(false);
    //phoneeditText.cl
    // String locationa = sharedpreferences.getString(LOCATIONNAME, null);
    locationeditText.setText(locationa);
}else if (locationa==null)
{
    locationeditText.setHint("Enter Location of Event");
    locationeditText.setFocusable(true);
    locationeditText.setClickable(true);


}
    }catch (Exception e)
    {

        e.printStackTrace();
    }


        try {
             String recivedlocation = sharedpreferences.getString(AREAName, null);
if (recivedlocation!=null) {
    areaeditText.setHint("");
    areaeditText.setFocusable(false);
    areaeditText.setClickable(false);

    areaeditText.setText(recivedlocation);
}else
{

    areaeditText.setHint("Enter Area Name of Event");
    areaeditText.setFocusable(true);
    areaeditText.setClickable(true);


}


        }catch (Exception e)
        {

            e.printStackTrace();
        }
    */


    btnclear.setOnClickListener(new View.OnClickListener() {

        public void onClick(View v) {
// get The User name and Password

            btnsubmit.setClickable(true);
            btnsubmit.setFocusable(true);
            btnsubmit.setEnabled(true);
            btnsubmit.setText("Save");
            btnsubmit.setBackgroundDrawable(null);
            phoneeditText.getText().clear();
            phoneeditText.setFocusable(true);
            phoneeditText.setClickable(true);
            phoneeditText.setEnabled(true);
            phoneeditText.setHint("Enter Promoter Phone Number");


            // phoneeditText.set
            // locationeditText
            locationeditText.getText().clear();

            locationeditText.setFocusable(true);
            locationeditText.setClickable(true);
            locationeditText.setEnabled(true);
            //locationeditText.setText("");
            locationeditText.setHint("Enter Location of Event");

            // areaeditText
            areaeditText.getText().clear();

            areaeditText.setFocusable(true);
            areaeditText.setClickable(true);
            locationeditText.setEnabled(true);
            areaeditText.setHint("Enter Area Name of Event");

            LoginProvider getobj = LoginProvider.getInstanceforUserContet(getContext());


            LoginProvider.UserDBHelper gg = getobj.getInstance(getActivity());

            gg.delete();

           // btnsubmit.setEnabled(true);
           // btnsubmit.setClickable(true);
            getActivity().getSharedPreferences("MyPref", 0).edit().clear().commit();


            Toast toast = Toast.makeText(getContext(), "previous Promoter Information Deleted!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            onBackPressed();



        }
    });

}catch (Exception e)
{

    e.printStackTrace();
}
            return rootView;
        }


        public void onBackPressed() {
            Fragment fragment = new HomeFragment();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

           /* FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_frame, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
*/
        }
        private void saveUser() {
            try {
       /* phonenumber = phoneeditText.getText().toString();
        location = locationeditText.getText().toString();
        area = areaeditText.getText().toString();*/



                ContentValues values = new ContentValues();
                values.put(Login.PHONENUMBER, phoneeditText.getText().toString());
                values.put(Login.AREA, areaeditText.getText().toString());
                values.put(Login.LOCATION, locationeditText.getText().toString());


                userUri = getActivity().getContentResolver().insert(LoginProvider.CONTENT_URI, values);
                Log.d(TAG, userUri.toString());
                System.out.println("inside LoginProvfder----" + userUri.toString());

                final Toast toast = Toast.makeText(getActivity(), "Data Successfully Saved", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                String ph = phoneeditText.getText().toString();
                String area = areaeditText.getText().toString();
                String loc = locationeditText.getText().toString();

                editor = sharedpreferences.edit();

                editor.putString(Phone, ph);
                editor.putString(LOCATIONNAME, loc);
                editor.putString(AREAName, area);
                editor.commit();
                String recivedphone = sharedpreferences.getString(Phone, null);
                String recivedlocation = sharedpreferences.getString(LOCATIONNAME, null);
                String recivedarea = sharedpreferences.getString(AREAName, null);

                phoneeditText.setFocusable(false);
                phoneeditText.setClickable(true);
                //  phoneeditText.setText(ph);


                // phoneeditText.set
                // locationeditText

                locationeditText.setFocusable(false);
                locationeditText.setClickable(true);
                // locationeditText.setText(loc);

                // areaeditText
                areaeditText.setFocusable(false);
                areaeditText.setClickable(true);
                // areaeditText.setText(area);

                phoneeditText.setText(recivedphone);
                areaeditText.setText(recivedarea);
                locationeditText.setText(recivedlocation);

                btnsubmit.setEnabled(false);
                btnsubmit.setClickable(false);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, 300);


                onBackPressed();

            }catch (Exception e)
            {


                e.printStackTrace();
            }
        }



    }