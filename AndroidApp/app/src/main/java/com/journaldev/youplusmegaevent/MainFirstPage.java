package com.journaldev.youplusmegaevent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by lenovo on 9/27/2016.
 */
public class MainFirstPage  extends Activity {
    SharedPreferences sharedpreferences;
    TextView promoterphonembere;
    TextView locatione;
    TextView areae;
    EditText editTextUserName,editTextPassword,editTextConfirmPassword;

    public static final String mypreference = "mypref";
    public static final String Pphonenumber = "promoterphonember";
    public static final String loction = "location";
    public static final String area = "area";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firtpage_promoter);
        promoterphonembere = (TextView) findViewById(R.id.protometnumberEditText);
        locatione = (TextView) findViewById(R.id.locationEditText);
        areae = (TextView) findViewById(R.id.areaEditText);

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(Pphonenumber)) {
            promoterphonembere.setText(sharedpreferences.getString(Pphonenumber, ""));
        }
        if (sharedpreferences.contains(loction)) {
            locatione.setText(sharedpreferences.getString(loction, ""));

        }

        if (sharedpreferences.contains(area)) {
            areae.setText(sharedpreferences.getString(area, ""));

        }

    }

    public void Save(View view) {


        String n = promoterphonembere.getText().toString();
        String e = locatione.getText().toString();
        String a = areae.getText().toString();
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(Pphonenumber, n);
        editor.putString(loction, e);
        editor.putString(area, a);
        editor.commit();
if (n.equals(promoterphonembere.getText().toString())==true) {
    Intent intentmain = new Intent(MainFirstPage.this, MainActivity.class);
    finish();
    startActivity(intentmain);
}else
{


}
    }

    public void clear(View view) {
        promoterphonembere = (TextView) findViewById(R.id.protometnumberEditText);
        locatione = (TextView) findViewById(R.id.locationEditText);
        areae = (TextView) findViewById(R.id.areaEditText);
        promoterphonembere.setText("");
        locatione.setText("");
        areae.setText("");

    }

    public void Get(View view) {
        promoterphonembere = (TextView) findViewById(R.id.protometnumberEditText);
        locatione = (TextView) findViewById(R.id.locationEditText);
        areae = (TextView) findViewById(R.id.areaEditText);
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);

        if (sharedpreferences.contains(Pphonenumber)) {
            promoterphonembere.setText(sharedpreferences.getString(Pphonenumber, ""));
        }
        if (sharedpreferences.contains(loction)) {
            locatione.setText(sharedpreferences.getString(loction, ""));

        }

        if (sharedpreferences.contains(area)) {
            areae.setText(sharedpreferences.getString(area, ""));

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}