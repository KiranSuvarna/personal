package com.journaldev.youplusmegaevent.newprovider.bean;

import android.database.Cursor;

/**
 * Created by Naveen on 6/24/2015.
 */
public abstract class CuresorResource<T> {

    private Class type;

     public CuresorResource(Class type) {
         this.type = type;
     }
    public void bind(Cursor cursor) {
        cursor.moveToFirst();
        this.bindBean(cursor);
        cursor.close();
    }
    public Class getType(){return this.type;}
    public abstract void bindBean(Cursor cursor);
}
