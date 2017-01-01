package com.journaldev.youplusmegaevent.compoent.Itemname_onselect;/*
package com.youplus.youplusapp.compoent.Itemname_onselect;

*/
/**
 * Created by Admin on 11/4/2015.
 *//*


import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.wireless_weighing.wireless_weighing.activity.ItemActivity;
import com.wireless_weighing.wireless_weighing.activity.NewItemActivity;
import com.wireless_weighing.wireless_weighing.activity.ShowitemActivity;
import com.wireless_weighing.wireless_weighing.activity.importdatabase.DBController;
import com.wireless_weighing.wireless_weighing.itemdescDB.ItemDescName;

public class CustomAutoCompleteTextChangedListener implements TextWatcher{

    public static final String TAG = "CustomAutoComplet";
    Context context;
    DBController dbController;


    public CustomAutoCompleteTextChangedListener(Context context){
        this.context = context;
        dbController=new DBController(context);
    }

    @Override
    public void afterTextChanged(Editable s) {
        // TODO Auto-generated method stub

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count,
                                  int after) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTextChanged(CharSequence userInput, int start, int before, int count) {

        // if you want to see in the logcat what the user types
        Log.e(TAG, "input:" + userInput);

        NewItemActivity mainActivity = ((NewItemActivity
                ) context);
        mainActivity.myAdapter.notifyDataSetChanged();

        // query the database based on the user input
        mainActivity.item = mainActivity.getItemsFromDb(userInput.toString());
       */
/* ItemDescName listolitrs=dbController.readByltrszero(userInput.toString());
        //giving data to getdata method for
        //getdata(listofrecords.getKgltrs());
        try {
            if (Integer.parseInt(listolitrs.getKgltrs().toString())!=0) {
                System.out.println("inside customtexte" + listolitrs.getKgltrs());

            } else {
                System.out.println("nooooooooooooooooooooocustomtexte" + listolitrs.getKgltrs());

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }*//*

        // update the adapater
        mainActivity.myAdapter.notifyDataSetChanged();
        mainActivity.myAdapter = new ArrayAdapter<String>(mainActivity, android.R.layout.simple_dropdown_item_1line, mainActivity.item);
        mainActivity.myAutoComplete.setAdapter(mainActivity.myAdapter);





        // get suggestions from the database
      */
/*  ItemDescName[] myObjs = mainActivity.databaseH.read(userInput.toString());

        // update the adapter
        mainActivity.myAdapter = new AutocompleteCustomArrayAdapter(mainActivity, R.layout.list_view_row_item, myObjs);

        mainActivity.myAutoComplete.setAdapter(mainActivity.myAdapter);*//*

    }

}*/
