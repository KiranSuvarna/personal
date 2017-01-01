package com.journaldev.youplusmegaevent;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.journaldev.youplusmegaevent.filechooser.FileChooser;
import com.journaldev.youplusmegaevent.newprovider.LoginProvider;
import com.journaldev.youplusmegaevent.newprovider.UserProvider;
import com.journaldev.youplusmegaevent.newprovider.bean.Login;
import com.journaldev.youplusmegaevent.newprovider.bean.Page;
import com.journaldev.youplusmegaevent.newprovider.bean.User;

import org.json.JSONObject;
/*import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;*/
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.ParseException;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.util.EntityUtils;

/**
 * Created by anupamchugh on 10/12/15.
 */
public class ServerFragment extends Fragment {
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    public ServerFragment() {
    }

    YouPlusButton btnDatePicker, btnTimePicker;
    YouPlusButton btnseleted;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;

    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_server, container, false);


        btnDatePicker=(YouPlusButton)rootView.findViewById(R.id.btn_date);

        txtDate=(EditText)rootView.findViewById(R.id.in_date);
        btnseleted=(YouPlusButton) rootView.findViewById(R.id.button_select);




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

                                txtDate.setText((monthOfYear + 1) + "/" +dayOfMonth  + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        textView=(TextView)rootView.findViewById(R.id.label);
btnseleted.setOnClickListener(new View.OnClickListener() {



    @Override
    public void onClick(View v) {


        ProgressDialog dialog_network;

        Map<String, String> networkDetails = getConnectionDetails();
        if (txtDate.getText().toString().equals("")) {
          /*  txtDate.setError("Please Select Date");
            Toast toast = Toast.makeText(getContext(), "Please Select Date ", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();*/
            AlertDialog alert =  new  AlertDialog.Builder(getActivity()).setPositiveButton("Ok",null).create();
            alert.setTitle("Please Select Date");
            alert.setIcon(R.drawable.warning);

            alert.setMessage("Please Select Date");
            alert.show();
        }


      else if (networkDetails.isEmpty()) {
       /* if (serializable instanceof Error) {
            Error error = (Error) serializable;

            if(error.getMessage().contains("refuse")){*/
            //findViewById(R.id.tableRow1).setVisibility(View.VISIBLE);
                    /*TextView value = (TextView) findViewById(R.id.status);
                    value.setTextColor(0xff0000ff);
                    value.setBackgroundColor( 0xffffffff);
                    value.setText("Internet Connection/wifi unavailable");
                    value.setTextColor(0xff0000ff);*/
          /*  dialog_network = new ProgressDialog(getActivity());
            dialog_network.setMessage(" Sorry,Please Connect internet ");
            dialog_network.setTitle("No Internet Connection/wifi");
            dialog_network.show();
            dialog_network.setCancelable(true);*/

            AlertDialog alert =  new  AlertDialog.Builder(getActivity()).setPositiveButton("Ok",null).create();
            alert.setTitle("No Internet/wifi");
            alert.setIcon(R.drawable.warning);

            alert.setMessage("Please Connect internet/wifi");
            alert.show();
           // error.setMessage("No Internet");
            // dialog_network.
        }
        else {

           // txtDate.setError(null);
            new AttemptLogin().execute();



        }
      /*  Intent fileintent = new Intent(getActivity(), FileChooser.class);
        //  finish();
        startActivity(fileintent);*/


    }
});
        return rootView;
    }





    class AttemptLogin extends AsyncTask<String, String, String> {

        boolean failure = false;
        ProgressDialog dialog;
        JSONObject json=null;

       String id,
               PHONENUMBER,
               AGE,
               GENDER,
               STATE,
               CITY,
               AREA,
               INCOME
        ,VECHICALINFORMATION,
               SHOPPINGINFORMATION,
               Luvit,
               FairLovely
               ,Cherio,
               DoveShampoo,
               Atta
               ,Hairconditioner,
               CREATEDDATE,
               promoternumber;

        String urldata=null;

        String formattedDate=null;
        String serdate = txtDate.getText().toString();


        @Override
        protected void onPreExecute() {
            // layout.setVisibility(View.VISIBLE);
            super.onPreExecute();

            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("Uploading please wait");
            dialog.setTitle("Uploading progress");
            dialog.setMax(2000);
            dialog.show();

            dialog.setCancelable(false);



        }

        @Override
        protected String doInBackground(String... urls) {
            HttpPost httpPostreq;
            int code=0;

            Calendar c1 = Calendar.getInstance();
            System.out.println("Current time => " + c1.getTime());
            // hh:mm a
            SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            formattedDate = df.format(c1.getTime());
            // Check for success tag
            // int success;


            UserProvider getobj=          UserProvider.getInstanceforUserContet(getContext());


            UserProvider.UserDBHelper gg=    getobj.getInstance(getActivity());

            System.out.println("----------------Get Date for------" + serdate);
//the below code is get count of all records based on date


            String responseText = null;
            /*String username = user.getText().toString();

            String password = pass.getText().toString();*/

            try {


                String promoternumberfromdb=null;
                String  location_of_event_db=null;
                String    areaofevet_db=null;
                Cursor cu = getActivity().getContentResolver().query(LoginProvider.CONTENT_URI, null, null, null, null);
                Page page1 = new Page(cu, Login.class);
                //   userDetail.setText(page.getContent().toString());
                //ormlite core method
                List<Login> listdatalogin = page1.getContent();
                Login login=null;
                System.out.println("-----------------DDDDDDDDDDDDDDDDDDDdr--------" + listdatalogin);
                for(int index=0; index < listdatalogin.size(); index++) {
                    login=listdatalogin.get(index);


                    System.out.println("---------------Forloop-------" + login);

                    promoternumberfromdb=login.getPhonenumber();
                    location_of_event_db=login.getLocation();
                    areaofevet_db=login.getArea();


                }

                //////////////////////////////////b/wlogin and

                Cursor c = getActivity().getContentResolver().query(UserProvider.CONTENT_URI, null, null, null, null);
                Page page = new Page(c, User.class);
                //   userDetail.setText(page.getContent().toString());
                //ormlite core method
                List<User> listdata = page.getContent();
                User bduser = new User();
                ArrayList<com.journaldev.youplusmegaevent.User> crunchifyResult = new ArrayList<com.journaldev.youplusmegaevent.User>();
                com.journaldev.youplusmegaevent.User user = new com.journaldev.youplusmegaevent.User();
                Date date=null;
                try{
                    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

                    date = (Date)formatter.parse(serdate);
                }catch (Exception e)
                {

                    e.printStackTrace();;
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat(
                        "MM/dd/yyyy");

                System.out.println("----------------Get IN server Date for------" + dateFormat.format(date));


                List<User> listofuser=    gg.getlistofUser(dateFormat.format(date));
               // if (listdata != null) {


                if (listofuser.equals(null)==true)
                {
                    AlertDialog alert =  new  AlertDialog.Builder(getActivity()).setPositiveButton("Ok",null).create();
                    alert.setTitle("No Information");
                    alert.setIcon(R.drawable.warning);
                    alert.setMessage("No Information for Selected Date");
                    alert.show();

                }

                    for (int i = 0; i < listofuser.size(); i++) {


                        bduser = listofuser.get(i);
                        System.out.println("--------data-------from----------dab-------" + bduser.toString());

                        crunchifyResult.add(user);
                        user.setId(bduser.getId());
                        user.setPHONENUMBER(bduser.getPhonenumber());
                        user.setAGE(bduser.getAge().replaceAll("\\s+", ""));

                        user.setGENDER(bduser.getGender());

                        user.setSTATE(bduser.getState());

                        user.setCITY(bduser.getCity());

                        user.setAREA(bduser.getArea().replaceAll("\\s+", ""));

                        user.setINCOME(bduser.getIncome().replaceAll("\\s+", ""));

                        user.setVECHICALINFORMATION(bduser.getVehicalInformation().replaceAll("\\s+", ""));

                        user.setSHOPPINGINFORMATION(bduser.getShoppingInformation().replaceAll("\\s+", ""));

                        user.setLuvit(bduser.getLovit().replaceAll("\\s+", ""));


                        user.setFairLovely(bduser.getFairnlovly().replaceAll("\\s+", ""));

                        user.setCherio(bduser.getCheior().replaceAll("\\s+", ""));

                        user.setDoveShampoo(bduser.getDove().replaceAll("\\s+", ""));

                        user.setAtta(bduser.getAttat().replaceAll("\\s+", ""));

                        user.setHairconditioner(bduser.getHair().replaceAll("\\s+", ""));

                        user.setCREATEDDATE(bduser.getCreateddate());
                        user.setPromoternumber(promoternumberfromdb);
                        user.setLocationofEvent(location_of_event_db);
                        user.setAreaNameofEvent(areaofevet_db);
                        System.out.println("--------data-------from----------dab-------" +bduser.getCreateddate());
                    urldata="http://ind-youplus-campain.yupl.us/youplus_offline_campain/user/saveuser?" +
                            "userid="+user.getId()+
                            "&PHONENUMBER="+user.getPHONENUMBER().trim()+
                            "&AGE="+user.getAGE().trim()+
                            "&GENDER="+user.getGENDER()+
                            "&STATE="+user.getSTATE()+
                            "&CITY="+user.getCITY()+
                            "&AREA="+user.getAREA().trim()+
                            "&INCOME="+user.getINCOME().trim()+
                            "&VECHICALINFORMATION="+user.getVECHICALINFORMATION().trim()+
                            "&SHOPPINGINFORMATION="+user.getSHOPPINGINFORMATION().trim()+
                            "&Luvit="+user.getLuvit().trim()+
                            "&FairLovely="+user.getFairLovely().trim()+
                            "&Cherio="+user.getCherio().trim()+
                            "&DoveShampoo="+user.getDoveShampoo().trim()+
                            "&Atta="+user.getAtta().trim()+
                            "&Hairconditioner="+user.getHairconditioner().trim()+
                            "&CREATEDDATE="+user.getCREATEDDATE()+
                            "&promoternumber="+user.getPromoternumber()+
                            "&uploadDate="+formattedDate+
                            "&areaNameofEvent="+user.getAreaNameofEvent().replaceAll("\\s+", "")+
                            "&locationofEvent=" +user.getAreaNameofEvent().replaceAll("\\s+", "");

                        //   urldata = "http://youplusadam-appv3.rhcloud.com/adamyouplus/user/saveuser?;" +
                      /*  urldata = "http://ind-youplus-campain.yupl.us/youplus_offline_campain/user/saveuser?" +

                                "userid=" + user.getId() + "&" +
                                "PHONENUMBER=" + user.getPHONENUMBER().trim()+
                                "&AGE=" + user.getAGE().trim()+
                                "&GENDER=" + user.getGENDER()+
                                "&STATE=" + user.getSTATE()+
                                "&CITY=" + user.getCITY()+
                                "&AREA=" +user.getAREA().trim()+
                                "&INCOME=" + user.getINCOME().trim()+
                                "&VECHICALINFORMATION=" + user.getVECHICALINFORMATION().trim()+ "&" +
                                "SHOPPINGINFORMATION=" + user.getSHOPPINGINFORMATION().trim()+ "&" +
                                "Luvit=" + user.getLuvit().trim()+ "&" +
                                "FairLovely=" + user.getFairLovely().trim()+ "&" +
                                "Cherio=" + user.getCherio().trim()+ "&" +
                                "DoveShampoo=" + user.getDoveShampoo().trim()+ "&" +
                                "Atta=" + user.getAtta().trim()+ "&" +
                                "Hairconditioner=" + user.getHairconditioner().trim()+ "&" +
                                "CREATEDDATE=" + user.getCREATEDDATE()+ "&" +
                                "promoternumber=" + user.getPromoternumber()+ "&" +
                        "uploadDate=" +formattedDate+ "&" +
                        "locationofEvent=" +user.getLocationofEvent().replaceAll("\\s+", "")+ "&" +

                        "areaNameofEvent=" +user.getAreaNameofEvent().replaceAll("\\s+", "");
*/

                        System.out.println("-------promoternumber------" + user.getLocationofEvent().replaceAll("\\s+", ""));
                        System.out.println("-------getCREATEDDATE------" + user.getPromoternumber());

                        crunchifyResult.add(user);

                  //  }
                //}

                System.out.println("--------data from dab-------" + crunchifyResult);

                Log.d("request!", "starting");
                // getting product details by making HTTP request
                DefaultHttpClient httpClient = new DefaultHttpClient();
                httpPostreq = new HttpPost(urldata);

                // check your log for json response
                System.out.println("--------Inside---url-------" + urldata);
                StringEntity se = new StringEntity(urldata);
                se.setContentType("application/json;charset=UTF-8");


                httpPostreq.setEntity(se);


                @SuppressWarnings("deprecation")
                HttpResponse httpResponse = httpClient.execute(httpPostreq);
                 code = httpResponse.getStatusLine().getStatusCode();
                System.out.println("------code------"+code);
                System.out.println("near httprespone" + httpResponse);
                try {
                    responseText = EntityUtils.toString(httpResponse
                            .getEntity());
                    System.out.println("---------value of responetext--------" + responseText);

                    try {
                        Thread.sleep(3000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    Log.i("Parse Exception", e + "response got");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
				/*
				 * JSONObject json = jsonParser.makeHttpRequest ( LOGIN_URL,
				 * "POST", params);
				 */
                    // check your log for json response
              /*  try {


                    if (json==null)
                    {

                        AlertDialog alert =  new  AlertDialog.Builder(getActivity()).setPositiveButton("Ok",null).create();
                        alert.setTitle("No Information");
                        alert.setIcon(R.drawable.warning);
                        alert.setMessage("No Information for Selected Date");
                        alert.show();

                    }
                    json = new JSONObject(responseText);

                    System.out.println(responseText
                            + "response from json object...............");

                    System.out.println(json
                            + "response from json object...............");


                }catch (NullPointerException e)
                {

                    e.printStackTrace();
System.out.println("--------------NULL POINTER----------"+e.getMessage());


                }*/

                   /* if (code == 200) {
                        System.out.println("inside if loop");
                        Log.d("Login Successful!", json.toString());

                    //   Toast.makeText(getActivity(), "Uploaded successfully.", Toast.LENGTH_LONG).show();

                        return json.getString(TAG_MESSAGE);
                    } else if (code !=200) {



                        ProgressDialog dialog_loginerror;
                        dialog_loginerror = new ProgressDialog(getActivity());
                        dialog_loginerror.setMessage(" Server not found ");
                        dialog_loginerror.setTitle("server");
                        dialog_loginerror.show();
                        dialog_loginerror.setCancelable(true);
                        Log.d("Login Falure!", json.getString(TAG_MESSAGE));
                        Toast.makeText(getActivity(), "Connection failure!.", Toast.LENGTH_SHORT).show();

                        return json.getString(TAG_MESSAGE);

                    }*/
               // }//End OF FOR LOOP
                }/*catch(JSONException e){
                    e.printStackTrace();
                }*/catch(ClientProtocolException e1){
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }catch(Exception e1){
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                return responseText;
            }



        // After completing background task Dismiss the progress dialog

        protected void onPostExecute(String result) {

            System.out.println("--------------NULL POINTER----------"+result);
            System.out.println("-----inside dopost method-----" + result);
            super.onPostExecute(result);

            if (result==null)
            {
                AlertDialog alert =  new  AlertDialog.Builder(getActivity()).setPositiveButton("Ok",null).create();
                alert.setTitle("No Information");
                alert.setIcon(R.drawable.warning);
                alert.setMessage("No Information for Selected Date");
                alert.show();

            }else
            {


                Toast.makeText(getActivity(), "Uploaded successfully.", Toast.LENGTH_LONG).show();


            }

            dialog.cancel();

           /*if (result != null) {
                Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
            }*/
        }
    }


    private Map<String, String> getConnectionDetails() {
        Map<String, String> networkDetails = new HashMap<>();
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifiNetwork = connectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (wifiNetwork != null && wifiNetwork.isConnected()) {

                networkDetails.put("Type", wifiNetwork.getTypeName());
                networkDetails.put("Sub type", wifiNetwork.getSubtypeName());
                networkDetails.put("State", wifiNetwork.getState().name());
            }

            NetworkInfo mobileNetwork = connectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mobileNetwork != null && mobileNetwork.isConnected()) {
                networkDetails.put("Type", mobileNetwork.getTypeName());
                networkDetails.put("Sub type", mobileNetwork.getSubtypeName());
                networkDetails.put("State", mobileNetwork.getState().name());
                if (mobileNetwork.isRoaming()) {
                    networkDetails.put("Roming", "YES");
                } else {
                    networkDetails.put("Roming", "NO");
                }
            }
        } catch (Exception e) {
            networkDetails.put("Status", e.getMessage());
        }
        return networkDetails;
    }


}
