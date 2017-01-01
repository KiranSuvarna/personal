package com.journaldev.youplusmegaevent.newprovider.bean;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naveen on 6/24/2015.
 */
public class Page<T extends CuresorResource<T>> {

    private List<T> content = new ArrayList<T>();

    public Page(Cursor cursor, Class type) {
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false) {
            try {
                T bean = (T) type.newInstance();
                bean.bindBean(cursor);
                content.add(bean);
            }catch (IllegalAccessException e) {
                e.printStackTrace();
            }catch (InstantiationException e) {
                e.printStackTrace();
            }
            cursor.moveToNext();
        }
        cursor.close();
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
