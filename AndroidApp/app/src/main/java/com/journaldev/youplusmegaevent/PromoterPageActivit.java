package com.journaldev.youplusmegaevent;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.journaldev.youplusmegaevent.newprovider.LoginProvider;
import com.journaldev.youplusmegaevent.newprovider.UserProvider;
import com.journaldev.youplusmegaevent.newprovider.bean.*;

/**
 * Created by lenovo on 9/27/2016.
 */
public class PromoterPageActivit extends Activity {

    SessionManager session;
    private Uri userUri;
    SharedPreferences sharedpreferences;
    YouPlusButton btnsubmit;
    EditText phoneeditText;
    EditText areaeditText;
    EditText locationeditText;

    private final static String TAG = PromoterPageActivit.class.getSimpleName();

    LoginDataBaseAdapter loginDataBaseAdapter;
    String phonenumber,location,area;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firtpage_promoter);
        loginDataBaseAdapter = new LoginDataBaseAdapter(this);
        loginDataBaseAdapter = loginDataBaseAdapter.open();
        session = new SessionManager(getApplicationContext());
        btnsubmit=(YouPlusButton)findViewById(R.id.btnSave);

        phoneeditText=(EditText)findViewById(R.id.protometnumberEditText);
        locationeditText=(EditText)findViewById(R.id.locationEditText);

        areaeditText=(EditText)findViewById(R.id.areaEditText);


       // session.createLoginSession(phoneeditText.t, locationeditText,areaeditText);

        btnsubmit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
// get The User name and Password
                saveUser();
                Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
                System.out.println("lllll" + phonenumber + "pooo" + location);
                Intent intentmain = new Intent(PromoterPageActivit.this, MainActivity.class);
                finish();
                startActivity(intentmain);

            }
        });

    }
    private void saveUser() {
       /* phonenumber = phoneeditText.getText().toString();
        location = locationeditText.getText().toString();
        area = areaeditText.getText().toString();*/
        ContentValues values = new ContentValues();
        values.put(Login.PHONENUMBER,phoneeditText.getText().toString());
        values.put(Login.AREA,areaeditText.getText().toString());
        values.put(Login.LOCATION,locationeditText.getText().toString());




        userUri = getContentResolver().insert(LoginProvider.CONTENT_URI, values);
        Log.d(TAG, userUri.toString());
        System.out.println("inside LoginProvfder----" + userUri.toString());

        final   Toast toast= Toast.makeText(getApplicationContext(), "Data Successfully Saved", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, 300);

    }

}
