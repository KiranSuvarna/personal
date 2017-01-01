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


import com.journaldev.youplusmegaevent.newprovider.bean.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Naveen on 6/24/2015.
 */
public class UserProvider extends ContentProvider {


    public static final String PROVIDER_NAME="com.journaldev.youplusmegaevent.newprovider.User";
    public static final String URL="content://"+PROVIDER_NAME+"/useritem";
    public static final Uri CONTENT_URI = Uri.parse(URL);
    public static final String DB_NAME = "youplus_offinedatabase";

    public static final int USER = 1;
    public static final int USER_ID = 2;
    public static final UriMatcher uriMatcher;
    public static Map<String,String> STUDENTS_PROJECTION_MAP;
    private SQLiteDatabase db;


    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME,"useritem",USER);
        uriMatcher.addURI(PROVIDER_NAME,"useritem/#",USER_ID);
    }

    private UserDBHelper dbHealper;
    private static UserDBHelper dbHealper2;

    public  UserDBHelper instance;
    public static UserProvider instancej;

    public  static  UserProvider getInstanceforUserContet(Context context) {
        if (instancej == null) {
            instancej = new UserProvider();
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
            db.execSQL("create table " + User.TABLE_NAME + " ("
                    + User._ID + " integer primary key autoincrement, "
                    + User.PHONENUMBER + " integer , "
                    + User.AGE + " text, "
                    + User.GENDER + " text, "
                    + User.STATE + " text, "
                    + User.CITY + " text, "

                    + User.ARE + " text, "
                    + User.INCOME + " text, "
                    + User.VECHICALINFO + " text, "
                    + User.SHOPPINGINFO + " text, "
                    + User.LOVIT + " text, "
                    + User.FAIRNLOVLY + " text, "
                    + User.CHEIRO + " text, "
                    + User.DOVE + " text, "
                    + User.ATTAT + " text, "
                    + User.HAIR + " text, "
                    + User.LOCATIONOFEVENT + " text, "
                    + User.AREAOFEVENT + " text, "
                    + User.PROMOTERNUMBER + " text, "
                    + User.CREATEDDATE + " DATETIME DEFAULT CURRENT_DATE  );");
                   /*

                     private String locationofEvent;
    private String areaNameofEvent;
                    , primary key ("+User._ID+")

                    + User.TRANSACTION_TYPE + " text,"
                    + User.ITEM_DATE + " text );");*/
           // + User.DATE + "DATETIME DEFAULT CURRENT_TIMESTAMP );");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists " + User.TABLE_NAME);
            this.onCreate(db);
        }


        public List<User> getlistofUser(String date)
        {

            SQLiteDatabase dbin = getReadableDatabase();
            String selectQuery = "SELECT  * FROM " + User.TABLE_NAME +" where " + User.CREATEDDATE + "=" + "'" + date + "'"+" ORDER BY " + User.CREATEDDATE + " ASC";

            List<User> categories = new ArrayList<User>();
            Cursor cursor = dbin.rawQuery(selectQuery, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {

                    System.out.println("-------FRomDTATABASE----------" + cursor.toString());

                    User contact = new User();
                    contact.setId(String.valueOf(cursor.getInt(0)));
                    contact.setPhonenumber(cursor.getString(1));
                    contact.setAge(cursor.getString(2));
                    contact.setGender(cursor.getString(3));
                    contact.setState(cursor.getString(4));
                    contact.setCity(cursor.getString(5));
                    contact.setArea(cursor.getString(6));
                    contact.setIncome(cursor.getString(7));
                    contact.setVehicalInformation(cursor.getString(8));
                    contact.setShoppingInformation(cursor.getString(9));
                    contact.setLovit(cursor.getString(10));
                    contact.setFairnlovly(cursor.getString(11));
                    contact.setCheior(cursor.getString(12));
                    contact.setDove(cursor.getString(13));

                    contact.setAttat(cursor.getString(14));
                    contact.setHair(cursor.getString(15));
                    contact.setLocationofEvent(cursor.getString(16));
                    contact.setAreaNameofEvent(cursor.getString(17));
                    contact.setPromoternumber(cursor.getString(18));
                    contact.setCreateddate(cursor.getString(19));




                    System.out.println("-------FRomDTATABASE----------" + contact.toString());




                    // Adding contact to list
                    categories.add(contact);
                } while (cursor.moveToNext());
            }
return  categories;

        }
        public int getcountfromdb(String date)
        {        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

            int i = 0;
            // Cursor c = null;
            String[] selectionArgs = new String[]{date};

            System.out.println("------------------Inside DATASNSD-------" + db);
            SQLiteDatabase dbin = getReadableDatabase();
            System.out.println("------------------Inside inner DATASNSD-------" + dbin);

           // Cursor  c = dbin.rawQuery("select * from " + User.TABLE_NAME + " where " + User.CREATEDDATE + "=" + "'" + date + "'", null);
            Cursor  c = dbin.rawQuery("select COUNT(createddate) from " + User.TABLE_NAME + " where " + User.CREATEDDATE + "=" + "'" + date + "'", null);
            if (c != null ) {
                if (c.moveToFirst()) {
                    do {
i=c.getInt(0);
                        System.out.println("---------------Date from databse------" + c.getInt(0));

                   /*     String objectName = c.getString(c.getColumnIndex(User.CREATEDDATE));
                        System.out.println("---------------Date from databse------" + objectName);*/
                        // get the data into array, or class variable
                    } while (c.moveToNext());
                }
            }

          /*  while (!c.isLast())

            {
              //System.out.println("---------------Date from databse------" +  c.getInt(1));

                String objectName = c.getString(c.getColumnIndex(User.CREATEDDATE));
                System.out.println("---------------Date from databse------" +  objectName);

                c.moveToNext();

            }*/
            // c.moveToFirst();
            /*if (c.moveToFirst()) {
                do {
                  //  c.getInt(0);
//c.getString(16);
                  //  System.out.println("---------------Date from databse------" +  c.getInt(1));
                    String objectName = c.getString(c.getColumnIndex(User.CREATEDDATE));
                    System.out.println("---------------Date from databse------" +  objectName);

                } while (c.moveToNext());

            }*/
            // i = c.getCount();
          //  c.close();
            System.out.println("----------------Cusor count------" + i);


            return  i;
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
        qb.setTables(User.TABLE_NAME);

        switch (uriMatcher.match(uri)) {
            case USER:
                qb.setProjectionMap(STUDENTS_PROJECTION_MAP);
                break;
            case USER_ID:
                //previuusly userid
                qb.appendWhere(User._ID+"="+uri.getPathSegments().get(1));
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
                return "vnd.android.cursor.dir/vnd.youplus.user";
            case USER_ID:
                return "vnd.android.cursor.item/vnd.youplus.user";
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = this.db.insert(User.TABLE_NAME, null, values);
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
                count = this.db.delete(User.TABLE_NAME, selection, selectionArgs);
                break;
            case USER_ID:
                String id = uri.getPathSegments().get(1);
                //previuusly userid
                count = db.delete(User.TABLE_NAME, User._ID + "=" + id +
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
                this.db.update(User.TABLE_NAME, values, selection, selectionArgs);
                break;
            case USER_ID:
                String id = uri.getPathSegments().get(1);
                //previuusly userid
                this.db.update(User.TABLE_NAME, values, User._ID +"=" + id +
                        (!TextUtils.isEmpty(selection) ? " and ("+selection+")":""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri "+uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }


    public static  int read(String searchTerm) {

        List<User> recordsList = new ArrayList<User>();

        String countQuery = "SELECT  * FROM " + User.TABLE_NAME+" WHERE createddate='"+ searchTerm +"'";
        // select query
        String sql = "";
        sql += "SELECT * FROM " + User.TABLE_NAME;
        sql += " WHERE " + User.PHONENUMBER + " ='" + searchTerm + "'";
        /*sql += " ORDER BY " + User.ITEMNAME + " DESC";
        sql += " LIMIT 0,5";
*/
        SQLiteDatabase db = dbHealper2.getWritableDatabase();

        // execute the query
        Cursor cursor = db.rawQuery(countQuery, null);

        /*// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                // int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(fieldProductId)));
                String objectName = cursor.getString(cursor.getColumnIndex(User.TABLE_NAME));
                System.out.println("inside UserProvider----"+objectName);
                User myObject = new User();
                myObject.setPhonenumber(objectName);
                // add to list
                recordsList.add(myObject);

            } while (cursor.moveToNext());
        }
*/
        cursor.close();
        db.close();

        // return the list of records
        return cursor.getCount();
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
