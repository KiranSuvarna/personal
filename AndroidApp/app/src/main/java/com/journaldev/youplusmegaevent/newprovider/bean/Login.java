package com.journaldev.youplusmegaevent.newprovider.bean;

import android.database.Cursor;

/**
 * Created by lenovo on 9/27/2016.
 */
public class Login extends CuresorResource<Login> {
    public static final String TABLE_NAME = "Logintable";
   /* public static final String ID = "id";
    public static final String ITEMNAME = "itemname";
    public static final String ITEMCODE = "itemcode";
    public static final String WEIGHT = "weight";
    public static final String MAPRICE = "mapprice";
    public static final String PRICE = "price";*/

    public static final  String _ID = "_id";
    /*  String _STATUS = "_status";*/
    public static final  String PHONENUMBER = "phonenumber";
    public static final  String AREA = "area";
    public static final  String  LOCATION= "location";


  /*  public static final String TRANSACTION_TYPE = "transaction_type";
    public static final String ITEM_DATE = "itemdate";*/


    public Cursor cursor;

    private String id;



    private String phonenumber;
    private String area;
    private String location;


    public Login() { super(Login.class); }
    public Login(Cursor cursor) { super(Login.class);
        cursor.moveToFirst();
        this.bindBean(cursor);
        cursor.close();
    }

    @Override
    public void bindBean(Cursor cursor) {
        this.cursor = cursor;
        this.id = cursor.getString(cursor.getColumnIndex(_ID));
        this.phonenumber = cursor.getString(cursor.getColumnIndex(PHONENUMBER));
        this.location=cursor.getString(cursor.getColumnIndex(LOCATION));

        this.area = cursor.getString(cursor.getColumnIndex(AREA));


      /*  this.transaction_type = cursor.getString(cursor.getColumnIndex(TRANSACTION_TYPE));
        this.itemdate = cursor.getString(cursor.getColumnIndex(ITEM_DATE));*/
        //   this.itemdate=cursor.get(cursor.getColumnIndex(NewItemActivity.getDateTime()));
    }

    public Cursor getCursor() {
        return cursor;
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Login{" +
                "cursor=" + cursor +
                ", id='" + id + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", area='" + area + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}