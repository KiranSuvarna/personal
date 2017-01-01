package com.journaldev.youplusmegaevent;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
/*
import android.support.v7.app.AppCompatActivity;
*/
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
/*
import android.support.v7.widget.Toolbar;
*/
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.journaldev.youplusmegaevent.newprovider.DatabaseHelper;
import com.journaldev.youplusmegaevent.newprovider.LoginProvider;
import com.journaldev.youplusmegaevent.newprovider.UserProvider;
import com.journaldev.youplusmegaevent.newprovider.bean.Login;
import com.journaldev.youplusmegaevent.newprovider.bean.Page;
import com.journaldev.youplusmegaevent.newprovider.bean.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import au.com.bytecode.opencsv.CSVWriter;
import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import eu.inmite.android.lib.validations.form.callback.SimpleErrorPopupCallback;

import java.util.ArrayList;

/**
 * Created by anupamchugh on 10/12/15.
 */
public class HomeFragment extends Fragment {

    SessionManager session;
    public static boolean isMainFragment = true;

    YouPlusButton btnNext;
    public HomeFragment() {
        try {

        List getphonenumberlist=  readphonenumber();
            /*if (getphonenumberlist.equals(null))
            {
                AlertDialog alert =  new  AlertDialog.Builder(getActivity()).setPositiveButton("Ok",null).create();
                alert.setTitle("No Internet/wifi");
                alert.setMessage("Please Connect internet/wifi");
                alert.show();
            }*/
        System.out.println("-------Inside-Constroutor----" + getphonenumberlist);
        String truephonenumber=null;
            String concatephonenumber = "91" + phoneeditText.getText().toString();

            //List getphonenumberlist=  readphonenumber();
            System.out.println("--------Strarttimgggggg-------" + getphonenumberlist);

            for (int i = 0; i < getphonenumberlist.size(); i++) {

                String dbphonenumber = getphonenumberlist.get(i).toString();
                if (concatephonenumber.equals(dbphonenumber) == true) {
                    System.out.println("--------If phone number is true--------" + dbphonenumber);
                                /*phoneeditText.setError("Phone Number which is Already Registered!");
                                Toast toast = Toast.makeText(getContext(), "Phone Number which is Already Registered!,Please Try other Phone Number ", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();*/
                    truephonenumber = dbphonenumber;
                    System.out.println("--------If Revese--------" + truephonenumber);


                }


            }

            if (concatephonenumber.equals(truephonenumber)) {
                System.out.println("Phone Number must be in the form XXX-XXXXXXX");
                phoneeditText.setError("Phone Number already exists!");
                Toast toast = Toast.makeText(getContext(), "Please enter different phone number ", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }catch (Exception e)
        {

            e.printStackTrace();
        }

    }

PageTwoActivity pageTwoActivity;


    private Context ctx;

    public UserProvider.UserDBHelper dbHealper;


    private Uri userUri;

    // uicontrols
    // Spinner spCountries;
   // @NotEmpty(messageId = R.string.validation_type,order = 2)
   // @Nullable
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
    String mPhoneNumber;
    Spinner attatsp;

    Spinner failnlovelysp;
    Spinner cheirosp;
    Spinner hairsp;
    YouPlusButton btnsubmit;
    EditText phoneeditText;

    TextView timetxt;
    // Button btnsubmit,btnExport;
    private final static String TAG = MainActivity.class.getSimpleName();

    //class members
    String agesString[] = {"Select Age","below 18", "18-22", "23-30", "31-40",
            "above 40"};
    String income[] = {"Select Income", "below 30K", "31K-50K", "51K-70K", "71K-90K",
            "91K-1Lakh", "above 1Lakh","No Salary"};

    String genderString[] = {"Select Gender","Male", "Female"};
    String stateString[] = {"Karnataka"};
    String cityString[] = {"Bengaluru"};


    String areaString[] = {"Abbigere","Adugodi  ","AECS Layout","Agara  ","Airport Road","Akshaya Nagar","Amruthahalli  ","Anandnagar","Anekal","Anjanapura","Ashoknagar   ","Attibele  ","Austin Town  ",
            "B Sk II Stage  ","Bagalur   ","Banashankari","Banashankari III Stage  ","Banaswadi","Bangalore Corporation   ","Bannerghatta Road  ","Basavanagar","Basavanagudi","Basaveshwaranagar","Begur Road","Belandur","Bellary Road","Benson Town","Bettahalsur  ","Bilekahalli","Bommanahalli","Bommasandra","Brooke Field","BTM Layout","Byatarayanapura  ","C V Raman Nagar","Central Silk Board","Chamarajpet","Chambal River","Chandapur","Chandra Lay Out  ","Chickpet  ","Chikkaballapur","Chikkajala  ","Chikkalasandra  ","Cookes Town","Cox Town","Dasarahalli(Srinagar)  ",
            "Deepanjalinagar  ","Defence Colony","Devanahalli","Devasandra  ","Dodballapur Road","Doddaballapur","Doddajala  ","Doddakallasandra  ","Dollars Colony","Domlur  ","Dommasandra  ","Doorvaninagar  ","Dr Ambedkar Veedhi  ","Dr Shivarama Karanth Nagar  ","Electronic City","Fraser Town","G M Palya","GKVK","Ganganagar","Gaviopuram Extension  ","Gayathrinagar  ","Girinagar   ","Gottigere  ","HAL II Stage HO","Hampinagar  ","Hanumanth Nagar","HBR Layout","Hebbal","Hebbal Kempapura  ","Hegde Nagar","Hennur","Hesaraghatta Main Road","HighCourt  ","HMT Layout","Hoodi  ","Horamavu  ","HRBR Layout","Hulimavu  ","Hulsur Bazaar  ","Hunasamaranahalli  ","Indira Nagar","ISRO Layout","ITPL","J P Nagar  ","JC Nagar  ","Jakkur  ","Jeevan Bima Nagar","Jigani  ","K H B Colony  ","K G Road  ","Kadabagere  ","Kaggadaspura","Kallubalu  ","Kamakshipalya  ","Kanakapura Road","Kathriguppe  ","Konanakunte  ","Koramangala","Kothanur  ","KR Puram","Krishnarajapuram ","Kudlu Gate","Kumaraswamy Layout","Kumbalagodu  ","Kundalahalli  ","Laggere  ","Lavelle Road","Lingarajapuram  ",
            "Madhavan Park  ","Madiwala","Mahadevapura","Mahalakshmipuram Layout  ","Majestic","Mallathahalli  ","Mallesh Palaya","Malleshwaram","Marathahalli","Mathikere  ","Mayasandra  ","Mico Layout  ","Millers Road","Msrit  ","Museum Road  ","Mysore Road","Naganathapura","Nagawara","NAL  ","Nandi Hills","Nandini Layout  ","Nayandahalli  ","NelaMangala","New Thippasandra  ","OMBR Layout","P&T Col Kavalbyrasandra  ","Pai Layout","Palace Guttahalli  ","Palace Road","Peenya Dasarahalli  ","Prashanth Nagar","R T Nagar ","RMV Extension ","Ragihalli  ","Raj Bhavan","Rajajinagar","Rajanakunte  ","Rajarajeshwari Nagar","Ramamurthy Nagar","Ramohalli  ","RBI Layout","Rest House Road","Richards Town","Richmond Road","Richmond Town  ","Sadaramangala","Sadashivanagar  ","Sahakar Nagar","Sampangiramnagar  ","Sanjay Nagar","Sarjapur Road","Seshadripuram  ","Shanti Nagar","Silkboard","Singanayakanahalli  ","Sri Chowdeshwari  ","St Johns Medical College  ","State Bank Of Mysore Colony  ","Subramanyapura  ","Swimming Pool Extn  ","Tarabanahalli  ","Tavarekere  ","Thanisandra",
            "Thyagaraj Nagar","Ullalu Upanagar  ","Uttarahalli","Vartur  ","Vasanth Nagar","Vidhana Soudha  ",
            "Vidyanagar","Vijaya Bank Layout","Vimanapura  ","Viveknagar   ","Vyalikaval Extn  ","West of Chord Road ","Wilson Garden","Yelachenahalli  ","Yelahanka","yesvantpur"};
    String vehicalString[] = {"Select vehicle Information","Two Wheeler", "Four Wheeler","None","Both"};


    ArrayAdapter<String> adapterBusinessType;
    ArrayAdapter<String> adapterInComeType;
    ArrayAdapter<String> adapterGenderType;
    ArrayAdapter<String> adapterStateType;
    ArrayAdapter<String> adapterCityType;
    ArrayAdapter<String> adapterAreaType;
    ArrayAdapter<String> adapterVehicalType;


    AutoCompleteTextView autoCompleteTextView;

    ArrayList<User> healthlList;



    YouPlusButton btnExport;
    private static final int REQUEST_WRITE_STORAGE = 112;

    File file=null;
    public EditText filenametoexport,fileNameEditTxt;
    String formattedDate=null;
    // local members
    String sbusinesstype, scountry, sincome,sage,sgender,sstate,scity,sarea,svehical,sshopping;
    String slovit,shair,sdove,sfairnlovly,sattat,scheior;
    private AppCompatDelegate delegate;
    String ageselected="Select Age";
    View rootView;
    TelephonyManager mTelephonyMgr;
    SharedPreferences sharedpreferences;
    public static final String mypreference = "mypref";
    public static final String Pphonenumber = "promoterphonember";
    public static final String loction = "location";
    public static final String area = "area";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.pageone, container, false);

        phoneeditText = (EditText) rootView.findViewById(R.id.phonenumberEditText);
        LoginProvider getobj = LoginProvider.getInstanceforUserContet(getContext());


        LoginProvider.UserDBHelper gg = getobj.getInstance(getActivity());
        List<Login> listofpromoters=gg.getlistofUser();
        System.out.println("List OF user-------"+listofpromoters.size());

        if (listofpromoters.size()==0)
        {
            AlertDialog alert =  new  AlertDialog.Builder(getActivity()).setPositiveButton("Ok",null).create();
            alert.setTitle("No promoter Information ");
            alert.setIcon(R.drawable.warning);
            alert.setMessage("Please Enter Promoter Information before entering customer Information ");
            alert.show();



        }
        List getphonenumberlist=  readphonenumber();
        System.out.println("-------Inside-Constroutor----" + getphonenumberlist);
        String truephonenumber=null;
        try {
            String concatephonenumber = "91" + phoneeditText.getText().toString();

            //List getphonenumberlist=  readphonenumber();
            System.out.println("--------Strarttimgggggg-------" + getphonenumberlist);

            for (int i = 0; i < getphonenumberlist.size(); i++) {

                String dbphonenumber = getphonenumberlist.get(i).toString();
                if (concatephonenumber.equals(dbphonenumber) == true) {
                    System.out.println("--------If phone number is true--------" + dbphonenumber);
                                /*phoneeditText.setError("Phone Number which is Already Registered!");
                                Toast toast = Toast.makeText(getContext(), "Phone Number which is Already Registered!,Please Try other Phone Number ", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();*/
                    truephonenumber = dbphonenumber;
                    System.out.println("--------If Revese--------" + truephonenumber);


                }


            }

            if (concatephonenumber.equals(truephonenumber)) {
                System.out.println("Phone Number must be in the form XXX-XXXXXXX");
                phoneeditText.setError("Phone Number already exists!");
                Toast toast = Toast.makeText(getContext(), "Please enter different phone number ", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }catch (Exception e)
        {

            e.printStackTrace();
        }

        Cursor cu =  getActivity().getContentResolver().query(LoginProvider.CONTENT_URI, null, null, null, null);
        Page page = new Page(cu, Login.class);
        //   userDetail.setText(page.getContent().toString());
        //ormlite core method
        List<Login> listdata=page.getContent();
        System.out.println("-----------------DDDDDDDDDDDDDDDDDDDdr--------" + listdata);

        //mPhoneNumber = mTelephonyMgr.get
     /*   session = new SessionManager(getActivity());
        HashMap<String, String> userlis= session.getUserDetails();

        Set<Map.Entry<String, String>> mapSet = userlis.entrySet();
        Iterator<Map.Entry<String, String>> mapiter=mapSet.iterator();
        while (mapiter.hasNext())
        {
            Map.Entry mapEntry = (Map.Entry) mapiter.next();
            String keyValue = (String) mapEntry.getKey();
            String value = (String) mapEntry.getValue();
            //iterate over the array and print each value
            //for (int i=0; i<value.length; i++) {
                System.out.print(value + " session data----------------");
           // }
            System.out.println();
        }
        System.out.println("------------------mPhoneNumber--------" + mPhoneNumber);
*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        UserProvider provider=new UserProvider();
        dbHealper=  provider.getInstance(getContext());
        // dbHealper=new UserProvider.UserDBHelper(getActivity());
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.SEND_SMS}, 1);
        autoCompleteTextView = (AutoCompleteTextView) rootView.findViewById(R.id.autoCompleteTextView1);
        adapterAreaType = new ArrayAdapter<String>(getContext(), R.layout.data_items, areaString);
        autoCompleteTextView.setAdapter(adapterAreaType);


        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sarea = parent.getItemAtPosition(position).toString();
               /* Toast toast = Toast.makeText(getApplicationContext(), sarea + " is clicked", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();*/
            }
        });




        phoneeditText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String truephonenumber=null;
                String concatephonenumber="91"+phoneeditText.getText().toString();
                List getphonenumberlist=  readphonenumber();
                System.out.println("--------Strarttimgggggg-------" + getphonenumberlist);

                for (int i=0;i<getphonenumberlist.size();i++) {

                    String dbphonenumber = getphonenumberlist.get(i).toString();
                    if (concatephonenumber.equals(dbphonenumber) == true) {
                        System.out.println("--------If phone number is true--------" + dbphonenumber);
                                /*phoneeditText.setError("Phone Number which is Already Registered!");
                                Toast toast = Toast.makeText(getContext(), "Phone Number which is Already Registered!,Please Try other Phone Number ", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();*/
                        truephonenumber=dbphonenumber;
                        System.out.println("--------If Revese--------" + truephonenumber);


                    }


                }

                if (concatephonenumber.equals(truephonenumber)==true) {
                    System.out.println("Phone Number must be in the form XXX-XXXXXXX");
                    phoneeditText.setError("Phone Number already exists!");
                    Toast toast = Toast.makeText(getContext(), "Please enter different phone number ", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }

            }
        });
//phone number validation
        phoneeditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.isPhoneNumber(phoneeditText, false);

                String truephonenumber=null;
                String concatephonenumber="91"+phoneeditText.getText().toString();

                List getphonenumberlist=  readphonenumber();
                System.out.println("--------Strarttimgggggg-------" + getphonenumberlist);

                for (int i=0;i<getphonenumberlist.size();i++) {

                    String dbphonenumber = getphonenumberlist.get(i).toString();
                    if (concatephonenumber.equals(dbphonenumber) == true) {
                        System.out.println("--------If phone number is true--------" + dbphonenumber);
                                /*phoneeditText.setError("Phone Number which is Already Registered!");
                                Toast toast = Toast.makeText(getContext(), "Phone Number which is Already Registered!,Please Try other Phone Number ", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();*/
                        truephonenumber=dbphonenumber;
                        System.out.println("--------If Revese--------" + truephonenumber);


                    }


                }

                if (concatephonenumber.equals(truephonenumber)) {
                    System.out.println("Phone Number must be in the form XXX-XXXXXXX");
                    phoneeditText.setError("Phone Number already exists!");
                    Toast toast = Toast.makeText(getContext(), "Please enter different phone number ", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }





            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });


        ages = (Spinner) rootView.findViewById(R.id.ageId);
        inCome = (Spinner) rootView.findViewById(R.id.incomeId);
        gendersp = (Spinner) rootView.findViewById(R.id.genderId);


/*
        areasp= (Spinner) rootView.findViewById(R.id.a);
*/
        vehicalsp = (Spinner) rootView.findViewById(R.id.vehical);
        btnsubmit = (YouPlusButton) rootView.findViewById(R.id.btnNext);


        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());
// hh:mm a
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        formattedDate = df.format(c.getTime());






        btnsubmit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {






        //  String phoneNumberDb=    getPhoneNumber();
        Pattern pattern = Pattern.compile("\\d{10}");
        Matcher matcher = pattern.matcher(phoneeditText.getText().toString());

                /*if (matcher.matches()) {
                    System.out.println("Phone Number Valid");
                    Toast toast=  Toast.makeText(getContext(), "Phone Number Valid", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }*/
                        boolean phonenumberboolean=false;
                        String truephonenumber=null;
String concatephonenumber="91"+phoneeditText.getText().toString();

                        List getphonenumberlist=  readphonenumber();
                        System.out.println("--------Strarttimgggggg-------" + getphonenumberlist);

                        for (int i=0;i<getphonenumberlist.size();i++) {

                            String dbphonenumber = getphonenumberlist.get(i).toString();
                            if (concatephonenumber.equals(dbphonenumber) == true) {
                                System.out.println("--------If phone number is true--------" + dbphonenumber);
                                /*phoneeditText.setError("Phone Number which is Already Registered!");
                                Toast toast = Toast.makeText(getContext(), "Phone Number which is Already Registered!,Please Try other Phone Number ", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();*/
                                truephonenumber=dbphonenumber;
                                System.out.println("--------If Revese--------" + truephonenumber);


                            }


                            }

                            if (phoneeditText.getText().toString().equals("")) {
                                phoneeditText.setError("Please Enter Phone Number");
                                Toast toast = Toast.makeText(getContext(), "Please Enter Phone Number ", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                            }

                           /* else if (concatephonenumber.equals(truephonenumber)) {
                                System.out.println("Phone Number must be in the form XXX-XXXXXXX");
                                phoneeditText.setError("Phone Number already exists!");
                                Toast toast = Toast.makeText(getContext(), "Please enter different phone number ", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                            } */else if (!matcher.matches()) {
                            System.out.println("Phone Number must be in the form XXX-XXXXXXX");
                                Toast toast = Toast.makeText(getContext(), "Please Enter  Valid Phone Number", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();
                            } else if (ages.getSelectedItem().toString().equals("") || ages.getSelectedItem().toString().equals("Select Age")) {
                                RequiredSpinnerAdapter adapter = (RequiredSpinnerAdapter) ages.getAdapter();
                                rootView = ages.getSelectedView();
                                adapter.setError(rootView, "Please Select a value");
                                Toast toast = Toast.makeText(getContext(), "Please Select Age ", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();

                            } else if (gendersp.getSelectedItem().toString().equals("") || gendersp.getSelectedItem().toString().equals("Select Gender")) {
                                RequiredSpinnerAdapter adapter = (RequiredSpinnerAdapter) gendersp.getAdapter();
                                rootView = gendersp.getSelectedView();
                                adapter.setError(rootView, "Please Select Gender value");
                                Toast toast = Toast.makeText(getContext(), "Please Select Gender ", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();

                            } else if (autoCompleteTextView.getText().toString().equals("") || autoCompleteTextView.getText().toString().equals("Select Area")) {
                                autoCompleteTextView.setError("Please Enter Area");
                                Toast toast = Toast.makeText(getContext(), "Please Enter Area ", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();


                            } else if (inCome.getSelectedItem().toString().equals("") || inCome.getSelectedItem().toString().equals("Select Income")) {
                                RequiredSpinnerAdapter adapter = (RequiredSpinnerAdapter) inCome.getAdapter();
                                rootView = inCome.getSelectedView();
                                adapter.setError(rootView, "Please Select Income value");
                                Toast toast = Toast.makeText(getContext(), "Please Select Income ", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();

                            } else if (vehicalsp.getSelectedItem().toString().equals("") || vehicalsp.getSelectedItem().toString().equals("Select vehicle Information")) {
                                RequiredSpinnerAdapter adapter = (RequiredSpinnerAdapter) vehicalsp.getAdapter();
                                rootView = vehicalsp.getSelectedView();
                                adapter.setError(rootView, "Please Select vehicle Information");
                                Toast toast = Toast.makeText(getContext(), "Please Select VehicalInformation ", Toast.LENGTH_SHORT);
                                toast.setGravity(Gravity.CENTER, 0, 0);
                                toast.show();

                            }
       /* else if (concatephonenumber.equals(phonenumbervalue)==true) {

                            phoneeditText.setError("Phone Number which is Already Registered!");
                            Toast toast=  Toast.makeText(getContext(), "Phone Number which is Already Registered!,Please Try other Phone Number ", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
        }*/


                            else if (isValidPhoneNumber(phoneeditText.getText().toString())) {

                                //String phonenumbervalue1=  readphonenumber();
                              System.out.println("-------------------first boolean value----------");



                     UserProvider getobj=          UserProvider.getInstanceforUserContet(getContext());


                                UserProvider.UserDBHelper gg=    getobj.getInstance(getActivity());
                            //    System.out.println("-------------------Object iii----------"+getobj);

                                    String serdate = getDateTime();
                                System.out.println("----------------Get Date for------" + serdate);
//the below code is get count of all records based on date
                                int count = gg.getcountfromdb(serdate);

                                //the below code will be for serverto upload based on date
List<User> listofuser=gg.getlistofUser(serdate);
                                    System.out.println("------------------get count--------" + count);



                                Log.d("Inside saved method---", "------------- saved method------------------");



                                /*DatabaseHelper databaseHelper=new DatabaseHelper(getContext());
                                String serdate = getDateTime();
                                int count =  databaseHelper.getData(serdate);
*/
                                System.out.println("------------------listofuser--------" + listofuser);

                                PageTwoActivity pageTwoActivity = new PageTwoActivity();
                                Intent pageTwo = new Intent(getActivity(), PageTwoActivity.class);
                                pageTwo.putExtra("ph", phoneeditText.getText().toString());
                                pageTwo.putExtra("age", sage);
                                pageTwo.putExtra("gender", sgender);
                                pageTwo.putExtra("area", autoCompleteTextView.getText().toString());
                                pageTwo.putExtra("income", sincome);
                                pageTwo.putExtra("vehical", svehical);

                                Bundle args = new Bundle();
                                args.putString("ph", phoneeditText.getText().toString());
                                args.putString("age", sage);
                                args.putString("gender", sgender);
                                args.putString("area", autoCompleteTextView.getText().toString());
                                args.putString("income", sincome);
                                args.putString("vehical", svehical);


                                pageTwoActivity.getsta(phoneeditText.getText().toString());


                                pageTwo.putExtra("nowblude", args);

                                //getActivity().finish();

                                startActivity(pageTwo);
                                phoneeditText.setText("");
                                phoneeditText.clearFocus();
                                ages.setSelection(0);
                                gendersp.setSelection(0);
                                autoCompleteTextView.setText("");
                                autoCompleteTextView.clearFocus();

                                inCome.setSelection(0);
                                vehicalsp.setSelection(0);
                            }

                   /* if (isValidPhoneNumber(phoneeditText.getText().toString()))
                    saveUser();*/
                            else {
                                Toast.makeText(getContext(), "Form contains error", Toast.LENGTH_SHORT).show();
                            }

    }
});
///// end of button next





        adapterVehicalType = new RequiredSpinnerAdapter<String>(this.getActivity(),R.layout.data_items, vehicalString);
        // adapterVehicalType.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        vehicalsp.setAdapter(adapterVehicalType);
        vehicalsp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View arg1,
                                       int position, long arg3) {
              /*  ((TextView) adapter.getChildAt(0)).setTextColor(Color.BLACK);
                ((TextView)adapter.getChildAt(0)).setGravity(Gravity.CENTER);
                ((TextView)adapter.getChildAt(0)).setPadding(16, 16, 16, 16);
                ((TextView)adapter.getChildAt(0)).setTextSize(16);*/
                // On selecting a spinner item
                svehical = adapter.getItemAtPosition(position).toString();
                System.out.println("-------------------svehical-----------" + svehical);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // Put code here
            }
        });

//shopping
      /*  adapterShoppingType = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,shoppigString);
        adapterShoppingType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shoppingsp.setAdapter(adapterShoppingType);
*/






        //gender


        adapterGenderType = new RequiredSpinnerAdapter<String>(this.getActivity(),R.layout.data_items, genderString);
        adapterGenderType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gendersp.setAdapter(adapterGenderType);
        gendersp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View arg1,
                                       int position, long arg3) {
              /*  ((TextView) adapter.getChildAt(0)).setTextColor(Color.BLACK);
                ((TextView)adapter.getChildAt(0)).setGravity(Gravity.CENTER);
                ((TextView)adapter.getChildAt(0)).setPadding(16, 16, 16, 16);
                ((TextView)adapter.getChildAt(0)).setTextSize(16);*/
                // On selecting a spinner item
                sgender = adapter.getItemAtPosition(position).toString();
                System.out.println("-------------------sage-----------" + sgender);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // Put code here
            }
        });





        //age





//set the default according to value

        adapterBusinessType = new RequiredSpinnerAdapter<String>(this.getActivity(), R.layout.data_items, agesString);
        adapterBusinessType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);//android.R.layout.simple_dropdown_item_1line
        ages.setAdapter(adapterBusinessType);

        ages.setSelection(0);

        ages.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View arg1,
                                       int position, long arg3) {
                /*((TextView)adapter.getChildAt(0)).setTextColor(Color.BLACK);


                ((TextView)adapter.getChildAt(0)).setGravity(Gravity.CENTER);
                ((TextView)adapter.getChildAt(0)).setPadding(16, 16, 16, 16);
                ((TextView)adapter.getChildAt(0)).setTextSize(16);*/
                // On selecting a spinner item
                sage = adapter.getItemAtPosition(position).toString();
                System.out.println("-------------------sage-----------"+sage);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {


                // Put code here
            }
        });
        //income



        adapterInComeType = new RequiredSpinnerAdapter<String>(this.getActivity(),R.layout.data_items, income);
        adapterInComeType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inCome.setAdapter(adapterInComeType);
        inCome.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View arg1,
                                       int position, long arg3) {
               /* ((TextView) adapter.getChildAt(0)).setTextColor(Color.BLACK);
                ((TextView)adapter.getChildAt(0)).setGravity(Gravity.CENTER);
                ((TextView)adapter.getChildAt(0)).setPadding(16, 16, 16, 16);
                ((TextView)adapter.getChildAt(0)).setTextSize(16);*/
                // On selecting a spinner item
                sincome = adapter.getItemAtPosition(position).toString();
                System.out.println("-------------------sincome-----------" + sincome);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // Put code here
            }
        });








        return rootView;
    }

    private String getDateTime() {
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        formattedDate = df.format(c.getTime());
        return df.format(c.getTime());
    }
    public List readphonenumber()
    {

    boolean checkphonenumberbooleanvalue = false;
    String dbphonenumber = null;
    List getphonenumberlist = new ArrayList();
        try {
            String truephonenumber=null;
            String concatephonenumber="91"+phoneeditText.getText().toString();
    System.out.println("----------INside metting---------");
    Cursor c = getActivity().getContentResolver().query(UserProvider.CONTENT_URI, null, null, null, null);
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

            if (concatephonenumber.equals(truephonenumber)==true) {
                System.out.println("Phone Number must be in the form XXX-XXXXXXX");
                phoneeditText.setError("Phone Number already exists!");
                Toast toast = Toast.makeText(getContext(), "Please enter different phone number ", Toast.LENGTH_SHORT);
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

    public static boolean isValidPhoneNumber(String phone)
    {
        if (!phone.trim().equals("") || phone.length() >= 10||phone.length() == 10)
        {
            return Patterns.PHONE.matcher(phone).matches();
        }

        return false;
    }








}
