package com.journaldev.youplusmegaevent;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.journaldev.youplusmegaevent.newprovider.LoginProvider;
import com.journaldev.youplusmegaevent.newprovider.UserProvider;
import com.journaldev.youplusmegaevent.newprovider.bean.Login;
import com.journaldev.youplusmegaevent.newprovider.bean.Page;
import com.journaldev.youplusmegaevent.newprovider.bean.User;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import eu.inmite.android.lib.validations.form.annotations.NotEmpty;

/**
 * Created by lenovo on 9/23/2016.
 */
public class PageTwoActivity extends ActionBarActivity {

Context context;
    private Uri userUri;

    // uicontrols
    // Spinner spCountries;

    Spinner ages;

    Spinner inCome;
    Spinner gendersp;
    Spinner statesp;

    Spinner citysp;

    Spinner areasp;
    Spinner vehicalsp;
    Spinner shoppingsp;

    Spinner lovitsp;
    Spinner dovesp;

    Spinner attatsp;

    Spinner failnlovelysp;
    Spinner cheirosp;
    Spinner hairsp;


    TextView timetxt;
    // Button btnsubmit,btnExport;
    private final static String TAG = MainActivity.class.getSimpleName();


    String shoppigString[] = {"Select Shopping Information","Super Markets", "Online","Grocery Shops"};


    String doveString[] = {"Have you tried Dove Shampoo","Yes","No"};
    String hairString[] = {"Which Hair conditioner do you use", "LOreal","Dove","None"};
    String attatString[] = {"Which Atta do you use","Aashirvad", "Pilsbury","None"};
    String cheroString[] = {"Have you tried Cherio","Yes","No"};
    String lovitString[] = {"Have you tried Luvit","Yes","No"};
    String lfairnlovlyString[] = {"Have you tried Fair & Lovely BB","Yes","No"};



    ArrayAdapter<String> adapterShoppingType;


    ArrayAdapter<String> adapterLovitType;
    ArrayAdapter<String> adapterHairType;
    ArrayAdapter<String> adapterDoveType;
    ArrayAdapter<String> adapterAttatType;
    ArrayAdapter<String> adapterCheiroType;
    ArrayAdapter<String> adapterFairnLovelyType;

    AutoCompleteTextView autoCompleteTextView;

    ArrayList<User> healthlList;


    YouPlusButton btnsubmit;
    YouPlusButton btnExport;
    private static final int REQUEST_WRITE_STORAGE = 112;
    private static final int   MY_PERMISSIONS_REQUEST_READ_CONTACTS=12;

    private static final int   MY_PERMISSIONS_REQUEST_SMS_SEND=13;

    File file=null;
    public EditText filenametoexport,fileNameEditTxt;
    String formattedDate=null;
    // local members
    String sbusinesstype, sphonenumber, sincome,sage,sgender,sstate,scity,sarea,svehical,sshopping;
    String slovit,shair,sdove,sfairnlovly,sattat,scheior;
    private AppCompatDelegate delegate;
    String ageselected="Select Age";


    public UserProvider.UserDBHelper dbHealper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.print("---start------------");
        Log.d("mmmdddddddddddddd", "ddddddddddddddddddddddddd");
        setContentView(R.layout.pagetwo);
        System.out.print("---start------------");
        Log.d("mmmdddddddddddddd", "ddddddddddddddddddddddddd");

btnsubmit=(YouPlusButton)findViewById(R.id.submit);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);
        System.out.print("---------------------------------------------------");

        Bundle b=   getIntent().getBundleExtra("nowblude");
        Log.d("after budle------", "-------------bundle data ------------------" + b);

        sphonenumber= "+91"+  b.getString("ph");

        sage=   b.getString("age");
        sgender=   b.getString("gender");
        sarea=   b.getString("area");
        sincome=    b.getString("income");
        svehical=   b.getString("vehical");
        ActivityCompat.requestPermissions(PageTwoActivity.this,
                new String[]{Manifest.permission.READ_PHONE_STATE},
                MY_PERMISSIONS_REQUEST_READ_CONTACTS);

        ActivityCompat.requestPermissions(PageTwoActivity.this,
                new String[]{Manifest.permission.SEND_SMS},
                MY_PERMISSIONS_REQUEST_SMS_SEND);
        Log.d("afterstring------", "-------------bundle string------------------"+sphonenumber);
        Log.d("afterstring------", "-------------bundle string------------------"+sage);
        Log.d("afterstring------", "-------------bundle string------------------"+sgender);
        Log.d("afterstring------", "-------------bundle string------------------"+sarea);
        Log.d("afterstring------", "-------------bundle string------------------"+sincome);
        Log.d("afterstring------", "-------------bundle string------------------"+svehical);



        TelephonyManager mTelephonyMgr;
        mTelephonyMgr = (TelephonyManager)
                getSystemService(Context.TELEPHONY_SERVICE);
        String mPhoneNumber = mTelephonyMgr.getLine1Number();

        Log.d("Phonenumber---", "-------------Phonenumber------------------"+mPhoneNumber);


     /*   Bundle args = new Bundle();
  Object  phonenumber=      args.get("ph");
        Object  age=    args.get("age");*/


        ActivityCompat.requestPermissions(PageTwoActivity.this,
                new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                },
                100);

        boolean hasPermission = (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE);
        }
//toget time










        shoppingsp= (Spinner) findViewById(R.id.shop);
        lovitsp= (Spinner) findViewById(R.id.luvit);
        dovesp= (Spinner) findViewById(R.id.dove);
        attatsp= (Spinner) findViewById(R.id.attat);

        failnlovelysp= (Spinner) findViewById(R.id.fairnlovely);
        cheirosp= (Spinner) findViewById(R.id.cherio);
        hairsp= (Spinner) findViewById(R.id.hair);

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());
        Date date=null;
        try{
            DateFormat formatter = new SimpleDateFormat("mm/dd/yyyy");

          //  date =formatter.parse();
        }catch (Exception e)
        {

            e.printStackTrace();;
        }
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        formattedDate = df.format(c.getTime());

/*
        timetxt.setText(formattedDate);
*/

        btnsubmit = (YouPlusButton) findViewById(R.id.submit);
        //  btnsubmit.setOnClickListener(this);


        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               if (shoppingsp.getSelectedItem().toString().equals("")||shoppingsp.getSelectedItem().toString().equals("Select Shopping Information")) {
                    RequiredSpinnerAdapter adapter = (RequiredSpinnerAdapter) shoppingsp.getAdapter();
                    view = shoppingsp.getSelectedView();
                    adapter.setError(view, "Please Select Shopping Information");
                    Toast toast = Toast.makeText(PageTwoActivity.this, "Please Select Shopping Information ", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();


                }
                else if (lovitsp.getSelectedItem().toString().equals("")||lovitsp.getSelectedItem().toString().equals("Have you tried Luvit")) {
                    RequiredSpinnerAdapter adapter = (RequiredSpinnerAdapter) lovitsp.getAdapter();
                    view = lovitsp.getSelectedView();
                    adapter.setError(view, "Please Select Luvit Option");
                    Toast toast=   Toast.makeText(PageTwoActivity.this, "Please Select Luvit Option ", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();


                }
                else if (failnlovelysp.getSelectedItem().toString().equals("")||failnlovelysp.getSelectedItem().toString().equals("Have you tried Fair & Lovely BB")) {
                    RequiredSpinnerAdapter adapter = (RequiredSpinnerAdapter) failnlovelysp.getAdapter();
                    view = failnlovelysp.getSelectedView();
                    adapter.setError(view, "Please Select  Fair & Lovely Option");
                    Toast toast=   Toast.makeText(PageTwoActivity.this, "Please Select Fair & Lovely Option", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();


                } else if (cheirosp.getSelectedItem().toString().equals("")||cheirosp.getSelectedItem().toString().equals("Have you tried Cherio")) {
                    RequiredSpinnerAdapter adapter = (RequiredSpinnerAdapter) cheirosp.getAdapter();
                    view = cheirosp.getSelectedView();
                    adapter.setError(view, "Please Select Cherio Option");
                    Toast toast=   Toast.makeText(PageTwoActivity.this, "Please Select Cherio Option", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();


                }
                else if (dovesp.getSelectedItem().toString().equals("")||dovesp.getSelectedItem().toString().equals("Have you tried Dove Shampoo")) {
                    RequiredSpinnerAdapter adapter = (RequiredSpinnerAdapter) dovesp.getAdapter();
                    view = dovesp.getSelectedView();
                    adapter.setError(view, "Please Select Dove Shampoo Option");
                    Toast toast=   Toast.makeText(PageTwoActivity.this, "Please Select Dove Shampoo Option", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();


                }


                else if (attatsp.getSelectedItem().toString().equals("")||attatsp.getSelectedItem().toString().equals("Which Atta do you use")) {
                    RequiredSpinnerAdapter adapter = (RequiredSpinnerAdapter) attatsp.getAdapter();
                    view = attatsp.getSelectedView();
                    adapter.setError(view, "Please Select Atta Option");
                    Toast toast=   Toast.makeText(PageTwoActivity.this, "Please Select Atta Option", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();


                }
                else if (hairsp.getSelectedItem().toString().equals("")||hairsp.getSelectedItem().toString().equals("Which Hair conditioner do you use")) {
                    RequiredSpinnerAdapter adapter = (RequiredSpinnerAdapter) hairsp.getAdapter();
                    view = hairsp.getSelectedView();
                    adapter.setError(view, "Please Select Hair conditioner Option");
                    Toast toast=   Toast.makeText(PageTwoActivity.this, "Please Select Hair conditioner Option ", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                }



                else {
                   saveUser();

                }


            }
        });


        adapterShoppingType = new RequiredSpinnerAdapter<String>(PageTwoActivity.this,R.layout.data_items, shoppigString);
        adapterShoppingType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shoppingsp.setAdapter(adapterShoppingType);
        shoppingsp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View arg1,
                                       int position, long arg3) {
               /* ((TextView) adapter.getChildAt(0)).setTextColor(Color.BLACK);
                ((TextView)adapter.getChildAt(0)).setGravity(Gravity.CENTER);
                ((TextView)adapter.getChildAt(0)).setPadding(16, 16, 16, 16);
                ((TextView)adapter.getChildAt(0)).setTextSize(16);*/
                // On selecting a spinner item
                sshopping = adapter.getItemAtPosition(position).toString();
                System.out.println("-------------------sshopping-----------" + sshopping);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // Put code here
            }
        });
























        adapterCheiroType = new RequiredSpinnerAdapter<String>(PageTwoActivity.this,R.layout.data_items, cheroString);
        adapterCheiroType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cheirosp.setAdapter(adapterCheiroType);
        cheirosp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View arg1,
                                       int position, long arg3) {
                /*((TextView) adapter.getChildAt(0)).setTextColor(Color.BLACK);
                ((TextView)adapter.getChildAt(0)).setGravity(Gravity.CENTER);
                ((TextView)adapter.getChildAt(0)).setPadding(16, 16, 16, 16);
                ((TextView)adapter.getChildAt(0)).setTextSize(16);*/
                // On selecting a spinner item
                scheior = adapter.getItemAtPosition(position).toString();
                System.out.println("-------------------scheior-----------" + scheior);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // Put code here
            }
        });



        adapterAttatType = new RequiredSpinnerAdapter<String>(PageTwoActivity.this,R.layout.data_items, attatString);
        adapterAttatType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        attatsp.setAdapter(adapterAttatType);
        attatsp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View arg1,
                                       int position, long arg3) {
                /*((TextView) adapter.getChildAt(0)).setTextColor(Color.BLACK);
                ((TextView)adapter.getChildAt(0)).setGravity(Gravity.CENTER);
                ((TextView)adapter.getChildAt(0)).setPadding(16, 16, 16, 16);
                ((TextView)adapter.getChildAt(0)).setTextSize(16);*/
                // On selecting a spinner item
                sattat = adapter.getItemAtPosition(position).toString();
                System.out.println("-------------------sattat-----------" + sattat);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // Put code here
            }
        });




        adapterDoveType = new RequiredSpinnerAdapter<String>(PageTwoActivity.this,R.layout.data_items, doveString);
        adapterDoveType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dovesp.setAdapter(adapterDoveType);
        dovesp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View arg1,
                                       int position, long arg3) {
               /* ((TextView) adapter.getChildAt(0)).setTextColor(Color.BLACK);
                ((TextView)adapter.getChildAt(0)).setGravity(Gravity.CENTER);
                ((TextView)adapter.getChildAt(0)).setPadding(16, 16, 16, 16);
                ((TextView)adapter.getChildAt(0)).setTextSize(16);*/
                // On selecting a spinner item
                sdove = adapter.getItemAtPosition(position).toString();
                System.out.println("-------------------sdove-----------" + sdove);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // Put code here
            }
        });



        adapterHairType = new RequiredSpinnerAdapter<String>(PageTwoActivity.this,R.layout.data_items, hairString);
        adapterHairType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hairsp.setAdapter(adapterHairType);
        hairsp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View arg1,
                                       int position, long arg3) {
            /*    ((TextView) adapter.getChildAt(0)).setTextColor(Color.BLACK);
                ((TextView)adapter.getChildAt(0)).setGravity(Gravity.CENTER);
                ((TextView)adapter.getChildAt(0)).setPadding(16, 16, 16, 16);
                ((TextView)adapter.getChildAt(0)).setTextSize(16);*/
                // On selecting a spinner item
                shair = adapter.getItemAtPosition(position).toString();
                System.out.println("-------------------shair-----------" + shair);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // Put code here
            }
        });



        adapterLovitType = new RequiredSpinnerAdapter<String>(PageTwoActivity.this,R.layout.data_items, lovitString);
        adapterLovitType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lovitsp.setAdapter(adapterLovitType);
        lovitsp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View arg1,
                                       int position, long arg3) {
               /* ((TextView) adapter.getChildAt(0)).setTextColor(Color.BLACK);
                ((TextView)adapter.getChildAt(0)).setGravity(Gravity.CENTER);
                ((TextView)adapter.getChildAt(0)).setPadding(16, 16, 16, 16);
                ((TextView)adapter.getChildAt(0)).setTextSize(16);*/
                // On selecting a spinner item
                slovit = adapter.getItemAtPosition(position).toString();
                System.out.println("-------------------slovit-----------" + slovit);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // Put code here
            }
        });

        adapterFairnLovelyType = new RequiredSpinnerAdapter<String>(PageTwoActivity.this, R.layout.data_items, lfairnlovlyString);
        //  adapterFairnLovelyType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        failnlovelysp.setAdapter(adapterFairnLovelyType);
        failnlovelysp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View arg1,
                                       int position, long arg3) {
                /*((TextView) adapter.getChildAt(0)).setTextColor(Color.BLACK);
                ((TextView)adapter.getChildAt(0)).setGravity(Gravity.CENTER);
                ((TextView)adapter.getChildAt(0)).setPadding(16, 16, 16, 16);
                ((TextView)adapter.getChildAt(0)).setTextSize(16);*/
                // On selecting a spinner item
                sfairnlovly = adapter.getItemAtPosition(position).toString();
                System.out.println("-------------------sfairnlovly-----------" + sfairnlovly);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // Put code here
            }
        });



        boolean mExternalStorageAvailable = false;
        boolean mExternalStorageWriteable = false;
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // We can read and write the media
            mExternalStorageAvailable = mExternalStorageWriteable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // We can only read the media
            mExternalStorageAvailable = true;
            mExternalStorageWriteable = false;
        } else {
            // Something else is wrong. It may be one of many other states, but all we need
            //  to know is we can neither read nor write
            mExternalStorageAvailable = mExternalStorageWriteable = false;
        }




    }


    private void saveUser() {
        //getdata();
String promoternumberfromdb=null;
        String  location_of_event_db=null;
        String    areaofevet_db=null;
        Cursor cu = getContentResolver().query(LoginProvider.CONTENT_URI, null, null, null, null);
        Page page = new Page(cu, Login.class);
        //   userDetail.setText(page.getContent().toString());
        //ormlite core method
        List<Login> listdata = page.getContent();
        Login login=null;
        System.out.println("-----------------DDDDDDDDDDDDDDDDDDDdr--------" + listdata);
        for(int index=0; index < listdata.size(); index++) {
            login=listdata.get(index);


            System.out.println("---------------Forloop-------" + login);

            promoternumberfromdb=login.getPhonenumber();
            location_of_event_db=login.getLocation();
            areaofevet_db=login.getArea();


        }
        String mPhoneNumber;
        TelephonyManager mTelephonyMgr;


        ActivityCompat.requestPermissions(PageTwoActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);

        mTelephonyMgr = (TelephonyManager)PageTwoActivity.this.getSystemService(Context.TELEPHONY_SERVICE);
        mPhoneNumber = mTelephonyMgr.getLine1Number();
        Log.d("Inside saved method---", "------------- saved method------------------" + mPhoneNumber);


        try {

            List getphonenumberlist=  readphonenumber(sphonenumber);
            /*if (getphonenumberlist.equals(null))
            {
                AlertDialog alert =  new  AlertDialog.Builder(getActivity()).setPositiveButton("Ok",null).create();
                alert.setTitle("No Internet/wifi");
                alert.setMessage("Please Connect internet/wifi");
                alert.show();
            }*/
            System.out.println("-------Inside-Constroutor----" + getphonenumberlist);
            String truephonenumber=null;
          //  String concatephonenumber = "91" + phoneeditText.getText().toString();

            //List getphonenumberlist=  readphonenumber();
            System.out.println("--------Strarttimgggggg-------" + getphonenumberlist);

            for (int i = 0; i < getphonenumberlist.size(); i++) {

                String dbphonenumber = getphonenumberlist.get(i).toString();
                if (sphonenumber.equals(dbphonenumber) == true) {
                    System.out.println("--------If phone number is true--------" + dbphonenumber);
                                /*phoneeditText.setError("Phone Number which is Already Registered!");
                                Toast toast = Toast.makeText(getContext(), "Phone Number which is Already Registered!,Please Try other Phone Number ", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();*/
                    truephonenumber = dbphonenumber;
                    System.out.println("--------If Revese--------" + truephonenumber);


                }


            }

            if (sphonenumber.equals(truephonenumber)) {
                System.out.println("Phone Number must be in the form XXX-XXXXXXX");
               // phoneeditText.setError("Phone Number already exists!");
                Toast toast = Toast.makeText(PageTwoActivity.this, "Please enter different phone number ", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }catch (Exception e)
        {

            e.printStackTrace();
        }





        ContentValues values = new ContentValues();
        values.put(User.PHONENUMBER,sphonenumber);
        values.put(User.AGE, sage);
        values.put(User.GENDER, sgender);

        values.put(User.STATE,"Karnataka");

        values.put(User.CITY, "Bangalore");

        values.put(User.ARE, sarea);

        values.put(User.INCOME, sincome);
        values.put(User.VECHICALINFO, svehical);




        values.put(User.SHOPPINGINFO, sshopping);

        values.put(User.LOVIT, slovit);
        values.put(User.FAIRNLOVLY, sfairnlovly);

        values.put(User.CHEIRO, scheior);

        values.put(User.DOVE, sdove);

        values.put(User.ATTAT, sattat);

        values.put(User.HAIR, shair);
        values.put(User.LOCATIONOFEVENT,location_of_event_db);
        values.put(User.AREAOFEVENT,areaofevet_db);
        values.put(User.PROMOTERNUMBER,promoternumberfromdb);

        values.put(User.CREATEDDATE, formattedDate);

        Log.d("Inside saved method---", "------------- Aftersaved method------------------" + formattedDate);

        userUri = getContentResolver().insert(UserProvider.CONTENT_URI, values);
        Log.d("Inside saved method---", "------------- Aftersaved method------------------" + userUri.toString());

        Log.d(TAG, userUri.toString());

        final   Toast toast= Toast.makeText(getApplicationContext(), "Items Successfully Saved", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.show();
        //toast.show();
        // }

        // ages.setClickable(false);
      //  ages.setSelection(0);
        //gendersp.setSelection(0);
       /* autoCompleteTextView.setText("");
        autoCompleteTextView.clearFocus();*/

       // inCome.setSelection(0);
       // vehicalsp.setSelection(0);

        shoppingsp.setSelection(0);






        lovitsp.setSelection(0);
        failnlovelysp.setSelection(0);
        hairsp.setSelection(0);
        attatsp.setSelection(0);
        dovesp.setSelection(0);
        cheirosp.setSelection(0);
        sendSmsByManager();


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, 300);
        //	Toast.makeText(getApplicationContext(),"Items Successfully Saved",Toast.LENGTH_LONG).show();
        //saveButton.setClickable(false);
        /*myAutoComplete.setText("");
        itemCodeEditTxt.setText("");
        itemWeightEditTxt.setText("");*/

        //btnsubmit.setBackgroundColor();

        btnsubmit.setBackgroundColor(Color.GREEN);
        btnsubmit.setEnabled(false);
        btnsubmit.setClickable(false);
       // btnsubmit.setClickable(true);
       // onBackPressed();

	/*clearItemButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {


				myAutoComplete.setText("");
				itemCodeEditTxt.setText("");
				itemWeightEditTxt.setText("");
					saveButton.setClickable(true);


			}
		});
*/

        onBackPressed();
    }

   /* @Override
    public void onBackPressed()
    {

        Intent intent = new Intent(PageTwoActivity.this,HomeFragment.class);

        intent.putExtra("Check",1);
        startActivity(intent);
    }*/


    public List readphonenumber(String phonenumber)
    {

        boolean checkphonenumberbooleanvalue = false;
        String dbphonenumber = null;
        List getphonenumberlist = new ArrayList();
        try {
            String truephonenumber=null;
            //String concatephonenumber="91"+phoneeditText.getText().toString();
            System.out.println("----------INside metting---------");
            Cursor c = getContentResolver().query(UserProvider.CONTENT_URI, null, null, null, null);
            Page page = new Page(c, User.class);
            //   userDetail.setText(page.getContent().toString());
            //ormlite core method
            List<User> listdata = page.getContent();
            //dbhelper.GetDataPerson();
            User person = null;
            System.out.println("----------Size or Count --------" + listdata.size());

            if (listdata.size() >=1) {
                for (int index = 0; index < listdata.size(); index++) {
                    person = listdata.get(index);
                    person.getPhonenumber();
                    getphonenumberlist.add(person.getPhonenumber());
                }
            }


            System.out.println("---------Only list of phone number--------" + getphonenumberlist);

            if (phonenumber.equals(truephonenumber)==true) {
                System.out.println("Phone Number must be in the form XXX-XXXXXXX");
               // phoneeditText.setError("Phone Number already exists!");
                Toast toast = Toast.makeText(PageTwoActivity.this, "Please enter different phone number ", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
            Log.d("Expoting data from db", "-----loading datafrom database " + listdata);


            System.out.println("----------read---------" + listdata);
        }catch (Exception e)
        {

            e.printStackTrace();
        }
        return getphonenumberlist;
    }
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd-MM-yyyy", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
    public void sendSMSMessage() {
        Log.i("Send SMS", "");
       // String phoneNo = txtphoneNo.getText().toString();
        String message = "Thank you for sharing your details,I hope you have enjoyed the gift hamper. For more gift hampers and freebies at restaurants, fitness centers, spa’s, saloon’s etc., please download the Youplus app. You are also" +
                "eligible to win a “Mac Book Air”!So happy downloading and all the best for the contest.";

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(sphonenumber, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS sent.", Toast.LENGTH_LONG).show();
        }

        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }


    public void sendSmsByManager() {
        try {

            String message = "Thank you! For a chance to win a free MacBookAir," +
                    "\ndownload the Youplus from \n" +
                    " https://play.google.com/store/apps/details?id=co.youplus";

String msg2="Hey, thanks for your time and checking out the gift hamper. " +
        " You also have a chance to win a Macbook Air for FREE https://youplus.hoko.link/giveaway";
            String msg1="Hope you liked the gift hamper! For a chance to win a FREE MacBook Air, " +
                    "install Youplus from https://youplus.hoko.link/giveaway";
            Log.d("--------mPhonption-",sphonenumber);

            // Get the default instance of the SmsManager
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(sphonenumber,
                    null,
                    msg2,
                    null,
                    null);
            Toast.makeText(getApplicationContext(), " sms sent!",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext()," sms has failed...",
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
            Log.d("--------msgexception-", ex.getMessage());
        }
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            getFragmentManager().popBackStack();
        }
    }
    public void getsta(String phonenumber)
    {

        System.out.print("-----------PAgeTwo---" + phonenumber);


    }
}
