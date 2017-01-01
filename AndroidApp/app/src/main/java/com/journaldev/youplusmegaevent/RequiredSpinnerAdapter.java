package com.journaldev.youplusmegaevent;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by lenovo on 9/8/2016.
 */
public class RequiredSpinnerAdapter<T> extends ArrayAdapter<String> {

    public RequiredSpinnerAdapter(Context context, int textViewResourceId,
                                  String agesString[]) {
        super(context, textViewResourceId, agesString);
    }

    int textViewId = 0;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        if (view instanceof TextView) {
            textViewId = view.getId();

        }

      //  return  txt;
        return view;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View row = super.getView(position, convertView, parent);



        return (row);
    }

    public void setError(View v, CharSequence s) {
        if(textViewId != 0){
            TextView name = (TextView) v.findViewById(textViewId);
           // name.setTextSize(50);
            name.setHint("Select Country");
            name.setError(s);
        }
    }
}
