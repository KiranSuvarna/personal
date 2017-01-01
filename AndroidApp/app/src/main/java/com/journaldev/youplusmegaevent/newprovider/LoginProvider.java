package com.journaldev.youplusmegaevent.newprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import com.journaldev.youplusmegaevent.newprovider.bean.Login;
import com.journaldev.youplusmegaevent.newprovider.bean.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Naveen on 6/24/2015.
 */
public class LoginProvider extends ContentProvider {


    public static final String PROVIDER_NAME="com.journaldev.youplusmegaevent.newprovider.Login";
    public static final String URL="content://"+PROVIDER_NAME+"/loginitem";
    public static final Uri CONTENT_URI = Uri.parse(URL);
    public static final String DB_NAME = "youplus_logindatabase";

    public static final int USER = 11;
    public static final int USER_ID = 12;
    public static final UriMatcher uriMatcher;
    public static Map<String,String> STUDENTS_PROJECTION_MAP;
    private SQLiteDatabase db;


    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME,"loginitem",USER);
        uriMatcher.addURI(PROVIDER_NAME,"loginitem/#",USER_ID);
    }

    private UserDBHelper dbHealper;
    private static UserDBHelper dbHealper2;

    public  UserDBHelper instance;
    public static LoginProvider instancej;

    public  static LoginProvider getInstanceforUserContet(Context context) {
        if (instancej == null) {
            instancej = new LoginProvider();
        }
        return instancej;
    }
    public    UserDBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new UserDBHelper(context);
        }
        return instance;
    }
    public class UserDBHelper extends SQLiteOpenHelper {

        public UserDBHelper(Context context) {
            super(context, DB_NAME, null, 1);




        }



/*
        + User.PHONENUMBER + " text, "
*//**/


        /*primary key*

          + User._ID + " integer  autoincrement, "

        /
         */

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table " + Login.TABLE_NAME + " ("
                    + Login._ID + " integer primary key autoincrement, "
                    + Login.PHONENUMBER + " integer , "
                    + Login.AREA + " text, "
                    + Login.LOCATION + " text);");
                   /*
                    , primary key ("+User._ID+")

                    + User.TRANSACTION_TYPE + " text,"
                    + User.ITEM_DATE + " text );");*/
           // + User.DATE + "DATETIME DEFAULT CURRENT_TIMESTAMP );");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists " + Login.TABLE_NAME);
            this.onCreate(db);
        }


        public void delete()


        {


            SQLiteDatabase dbin = getReadableDatabase();
            dbin.execSQL("drop table if exists " + Login.TABLE_NAME);
            this.onCreate(dbin);
            System.out.println("----------------Get IN LOgin Provider Date for------");

            /*String sql = "DELETE FROM "+Login.TABLE_NAME+"persons WHERE id=" + id + ";";
            db.execSQL(sql);*/


        }

        public List<Login> getlistofUser()
        {

            SQLiteDatabase dbin = getReadableDatabase();
            String selectQuery = "SELECT  * FROM " + Login.TABLE_NAME;
                    //+" where " + Login.PHONENUMBER + "=" + "'" + date + "'"+" ORDER BY " + Login.PHONENUMBER+ " ASC";

            List<Login> categories = new ArrayList<Login>();
            Cursor cursor = dbin.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {


                    Login contact = new Login();
                    contact.setId(String.valueOf(cursor.getInt(0)));
                    contact.setPhonenumber(cursor.getString(1));

                    contact.setArea(cursor.getString(2));
                    contact.setLocation(cursor.getString(3));









                    // Adding contact to list
                    categories.add(contact);
                } while (cursor.moveToNext());
            }
return  categories;

        }

        /*public int getData(String date){
            SQLiteDatabase db = getWritableDatabase();
            Cursor res =  db.rawQuery( "select * from youplus_offinedatabase."+ User.TABLE_NAME+" where createddate=+'"+date+"'", null );
            return res.getCount();
        }
*/

    }


    /*public int getContactsCount(String date) {
        this.dbHealper = new UserDBHelper(getContext());

      int count=  dbHealper.getData(date);
        // return count
        return count;
    }*/
    @Override
    public boolean onCreate() {
        this.dbHealper = new UserDBHelper(getContext());
        db = this.dbHealper.getWritableDatabase();
        return (db==null)?false:true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables(Login.TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            case USER:
                qb.setProjectionMap(STUDENTS_PROJECTION_MAP);
                break;
            case USER_ID:
                //previuusly userid
                qb.appendWhere(Login._ID+"="+uri.getPathSegments().get(1));
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
        if(sortOrder == null || sortOrder.equals("")) {
            //previuusly userid
            sortOrder = User._ID;
        }
        Cursor cursor = qb.query(this.dbHealper.getReadableDatabase(), projection, selection, selectionArgs,null,null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case USER:
                return "vnd.android.cursor.dir/vnd.youplus.login";
            case USER_ID:
                return "vnd.android.cursor.item/vnd.youplus.login";
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = this.db.insert(Login.TABLE_NAME, null, values);
        if(id>0){
            Uri userUri = ContentUris.withAppendedId(CONTENT_URI, id);
            getContext().getContentResolver().notifyChange(userUri,null);
            return userUri;
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case USER:
                count = this.db.delete(Login.TABLE_NAME, selection, selectionArgs);
                break;
            case USER_ID:
                String id = uri.getPathSegments().get(1);
                //previuusly userid
                count = db.delete(Login.TABLE_NAME, Login._ID + "=" + id +
                        (!TextUtils.isEmpty(selection) ? " and (" + selection + ") " : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri "+ uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = 0;
        switch (uriMatcher.match(uri)) {
            case USER:
                this.db.update(Login.TABLE_NAME, values, selection, selectionArgs);
                break;
            case USER_ID:
                String id = uri.getPathSegments().get(1);
                //previuusly userid
                this.db.update(Login.TABLE_NAME, values, Login._ID +"=" + id +
                        (!TextUtils.isEmpty(selection) ? " and ("+selection+")":""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri "+uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }



   /* public Cursor getData(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }*/


 /* public List<User> read() {

        List<User> recordsList = new ArrayList<User>();

        // select query
       *//* String sql = "";
        sql += "SELECT * FROM " + User.TABLE_NAME;
        sql += " WHERE " + User._ID + " LIKE '%" + searchTerm + "%'";
        sql += " ORDER BY " + User.CREATEDDATE + " DESC";
        sql += " LIMIT 0,5";*//*
      String sql = "";
      sql += "SELECT * FROM " + User.TABLE_NAME;


        SQLiteDatabase db = dbHealper.getWritableDatabase();

        // execute the query
        Cursor cursor = db.rawQuery(sql, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                // int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(fieldProductId)));
                String objectName = cursor.getString(cursor.getColumnIndex(User.ITEMNAME));
                System.out.println("inside UserProvider----"+objectName);
                User myObject = new User();
                myObject.setItemname(objectName);
                // add to list
                recordsList.add(myObject);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // return the list of records
        return recordsList;
    }
*/
    // Read records related to the search term


  /*  public List<User> read(String  phoneNumber) {

        List<User> recordsList = new ArrayList<User>();

        // select query
         String sql = "";
        sql += "SELECT phonenumber FROM " + User.TABLE_NAME;
        sql += " WHERE " + User.PHONENUMBER + "= '" + phoneNumber + "'";
        sql += " ORDER BY " + User.CREATEDDATE + " DESC";
        sql += " LIMIT 0,5";*//**//*
        String sql = "";
        sql += "SELECT * FROM " + User.TABLE_NAME;


        SQLiteDatabase db = dbHealper.getWritableDatabase();

        // execute the query
        Cursor cursor = db.rawQuery(sql, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                // int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(fieldProductId)));
                String objectName = cursor.getString(cursor.getColumnIndex(User.ITEMNAME));
                System.out.println("inside UserProvider----"+objectName);
                User myObject = new User();
                myObject.setItemname(objectName);
                // add to list
                recordsList.add(myObject);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // return the list of records
        return recordsList;
    }*/
}
