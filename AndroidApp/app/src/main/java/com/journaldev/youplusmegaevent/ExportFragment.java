package com.journaldev.youplusmegaevent;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.journaldev.youplusmegaevent.custom_view.TypefaceTextView;
import com.journaldev.youplusmegaevent.filechooser.FileChooser;
import com.journaldev.youplusmegaevent.newprovider.UserProvider;
import com.journaldev.youplusmegaevent.newprovider.bean.Page;
import com.journaldev.youplusmegaevent.newprovider.bean.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import au.com.bytecode.opencsv.CSVWriter;

/**
 * Created by anupamchugh on 10/12/15.
 */
public class ExportFragment extends Fragment {
    YouPlusButton btnExport;
    private Context ctx;
    private final static String TAG = ExportFragment.class.getSimpleName();

    TextView textView;
    private static final int REQUEST_WRITE_STORAGE = 112;
    private Uri userUri;
    File file=null;
    YouPlusButton btncount;
   // private Button btnsendmail=null;
    private EditText txtsubj;
    private EditText txttextmsg;
    YouPlusButton btnsendmail;
    public ExportFragment() {
    }

    YouPlusButton btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;
    String mPhoneNumber;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_export, container, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        TelephonyManager mTelephonyMgr;
        mTelephonyMgr = (TelephonyManager)getActivity().
                getSystemService(Context.TELEPHONY_SERVICE);
        mPhoneNumber = mTelephonyMgr.getLine1Number();
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                },
                100);

        boolean hasPermission = (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
        if (!hasPermission) {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE);
        }



        /*txtsubj = (EditText) rootView.findViewById(R.id.editText_subj);
        txttextmsg = (EditText) rootView.findViewById(R.id.editText_text);*/
      //  btnsendmail=(YouPlusButton) rootView.findViewById(R.id.button_sendmail);
        textView=(TextView) rootView.findViewById(R.id.edittxtcount);
        btnDatePicker=(YouPlusButton)rootView.findViewById(R.id.btn_date);

        txtDate=(EditText)rootView.findViewById(R.id.in_date);
        btncount=(YouPlusButton) rootView.findViewById(R.id.button_count);
String gettext=null;
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                   txtDate.setText((monthOfYear + 1)  + "/" +dayOfMonth + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
       /* btnsendmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendmail();

            }
        });*/

        btncount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                UserProvider getobj=          UserProvider.getInstanceforUserContet(getContext());


                UserProvider.UserDBHelper gg=    getobj.getInstance(getActivity());
                Date date=null;
                if (txtDate.getText().toString().equals("")) {
                    AlertDialog alert = new AlertDialog.Builder(getActivity()).setPositiveButton("Ok", null).create();
                    alert.setTitle("Please Select Date");
                    alert.setIcon(R.drawable.warning);

                    alert.setMessage("Please Select Date");
                    alert.show();
                }
                String serdate = txtDate.getText().toString();
                try{
                    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

                    date = (Date)formatter.parse(serdate);
                }catch (Exception e)
                {

                    e.printStackTrace();;
                }

                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "MM/dd/yyyy");
                String nwdate=null;
              //  System.out.println("----------------Get Date for------" + dateFormat.format(date));
try {
   nwdate = dateFormat.format(date);

            //  System.out.println("----------------Get Date for------" + dateFormat.format(date));
//the below code is get count of all records based on date


        // List<User> listofuser=    gg.getlistofUser(serdate);
        // System.out.println("---------------LIst of user-----" + listofuser);
    int count = gg.getcountfromdb(nwdate);

    textView.setText((String.valueOf(count)));


}catch (Exception e)
{

    e.printStackTrace();
}

            }
        });


        btnExport= (YouPlusButton) rootView.findViewById(R.id.button_export);


        btnExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Validation class will check the error and display the error on respective fields
                but it won't resist the form submission, so we need to check again before submit
                -\d{7}

                 */


                ExportDatabaseCSVTask task=new ExportDatabaseCSVTask();
                task.execute();

              /*  if (filenametoexport.getText().toString().equals("")) {

                    Toast toast=  Toast.makeText(MainActivity.this, "Please Enter File Name ", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }else

                {


                    ExportDatabaseCSVTask task=new ExportDatabaseCSVTask();
                    task.execute();
                    filenametoexport.setText("");

                }*/

            }
        });
        return rootView;
    }



    private class ExportDatabaseCSVTask extends AsyncTask<String, Void, Boolean> {
        private final ProgressDialog barProgressDialog = new ProgressDialog(getActivity());
        //String filename= filenametoexport.getText().toString();

        @Override
        protected void onPreExecute() {

            barProgressDialog.setMessage("EXporting in progress ...");
            barProgressDialog.setProgressStyle(barProgressDialog.STYLE_SPINNER);
            barProgressDialog.setProgress(0);
            barProgressDialog.setMax(2000);//In this part you can set the  MAX value of data
            barProgressDialog.show();

            final int totalProgressTime = 30000;
            final Thread t = new Thread() {
                @Override
                public void run() {
                    int jumpTime = 0;

                    while(jumpTime < totalProgressTime) {
                        try {
                            sleep(20000);
                            jumpTime += 5;
                            barProgressDialog.setProgress(jumpTime);
                        }
                        catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            };
            t.start();

        }
        protected Boolean doInBackground(final String... args){

            File dbFile=getActivity().getDatabasePath("youplus_offinedatabase");
            Log.v(TAG, "Db path is: " + dbFile);  //get the path of db
         //   String storagePath = Environment.getExternalStorageDirectory().getAbsolutePath();
            String storagePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
            String rootPath = storagePath + "YouPlus/";
            File root = new File(getActivity().getFilesDir()+rootPath);
            if(!root.mkdirs()) {
                Log.i("Test", "This path is already exist: " + root.getAbsolutePath());
            }else {
                root.mkdirs();
            }


            Calendar c1 = Calendar.getInstance();
            System.out.println("Current time => " + c1.getTime());

            SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
            String createdTimeforFile = df.format(c1.getTime());
          //  Environment.getExternalStorageDirectory()
            File exportDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString(), "");
            if (!exportDir.exists()) {
                exportDir.mkdirs();
            }
            String filename="youpluscamp"+createdTimeforFile;


            String extension=filename+".csv";

            file = new File(exportDir,extension);
            try {

                file.createNewFile();

                CSVWriter csvWrite = new CSVWriter(new FileWriter(file,true));


                Cursor c =  getActivity().getContentResolver().query(UserProvider.CONTENT_URI, null, null, null, null);
                Page page = new Page(c, User.class);
                //   userDetail.setText(page.getContent().toString());
                //ormlite core method
                List<User> listdata=page.getContent();
                //dbhelper.GetDataPerson();
                User person=null;


                Log.d("Expoting data from db", "-----loading datafrom database " + listdata);

                // this is the Column of the table and same for Header of CSV file
                String arrStr1[] ={"Id", "PHONENUMBER", "AGE","GENDER","STATE","CITY","AREA",
                        "INCOME","VECHICALINFORMATION","SHOPPINGINFORMATION",
                        "Have you tried Luvit","Have you tried Fair & Lovely BB","Have you tried Cherio",
                        "Have you tried Dove Shampoo","Which Atta do you use?","Which Hair conditioner do you use?",

                        "CREATEDDATE","promoternumber","LOCATIONOFEVENT","AREANAMEOFEVENT"};
                csvWrite.writeNext(arrStr1);

                if(listdata.size() > 1)
                {
                    for(int index=0; index < listdata.size(); index++)
                    {
                        person=listdata.get(index);
                        String arrStr[] ={ person.getId(), person.getPhonenumber(),
                                person.getAge(),person.getGender(),person.getState(),person.getCity(),
                                person.getArea(),person.getIncome(),person.getVehicalInformation(),
                                person.getShoppingInformation(),person.getLovit(),person.getFairnlovly(),
                                person.getCheior(),person.getDove(),person.getAttat(),
                                person.getHair(),
                                person.getCreateddate(),person.getPromoternumber(),person.getLocationofEvent()
                        ,person.getAreaNameofEvent()};
                        csvWrite.writeNext(arrStr);
                    }
                }

                csvWrite.close();
                return true;
            }
            catch (IOException e){
                Log.e("MainActivity", e.getMessage(), e);
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success)	{

            if (this.barProgressDialog.isShowing()){
                //this.barProgressDialog.dismiss();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        barProgressDialog.dismiss();
                    }
                }, 3000);
            }
            if (success){
                final Toast toast = Toast.makeText(getActivity(), "Export successfully", Toast.LENGTH_LONG);
                toast.show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, 2000);
                //	Toast.makeText(NewItemActivity.this, "Export successful!", Toast.LENGTH_SHORT).show();
            }
            else {
                final Toast toast = Toast.makeText(getActivity(), "Export failed!", Toast.LENGTH_LONG);
                toast.show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, 2000);
                //Toast.makeText(NewItemActivity.this, "Export failed!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void sendmail(){




//String tosender={"",""};
        //String to=txtto.getText().toString().trim();
	/*	int pos = spnr.getSelectedItemPosition();
		String tosender=celebrities[+pos];*/
        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }
        Calendar c1 = Calendar.getInstance();

        System.out.println("Current time => " + c1.getTime());

        SimpleDateFormat df = new SimpleDateFormat("ddMMyyyy");
        String createdTimeforFile = df.format(c1.getTime());
        String filename="youpluscamp"+createdTimeforFile;


        String extension=filename+".csv";

      File  fileloc = new File(exportDir,extension);

       // String subj=txtsubj.getText().toString().trim();
       // String msg=txttextmsg.getText().toString().trim();
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"ritesh@youplus.co","vindhya@youplus.co","adam.hussain@youplus.co"});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "YouPlus_MegaeventCSVFILE"+createdTimeforFile);
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "YouPlus_MegaeventCSVFILE");
        //  emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(file.getAbsolutePath()));
        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(fileloc.getAbsoluteFile()));
        startActivity(Intent.createChooser(emailIntent, "Send mail..."));

        //	Toast.makeText(getApplicationContext(),"Mail send",Toast.LENGTH_LONG).show();

    }


    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 100:
                if (grantResults.length > 0
                        || grantResults[0] == PackageManager.PERMISSION_GRANTED
                        || grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    /* User checks permission. */

                } else {
                    Toast.makeText(getActivity(), "Permission is denied.", Toast.LENGTH_SHORT).show();
                    //finish();
                }
                return;
        }
    }

}
