package com.journaldev.youplusmegaevent;

/**
 * Created by Admin on 10/15/2015.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;


public class LoginDataBaseAdapter
{
    static final String DATABASE_NAME = "login.db";
    static final int DATABASE_VERSION = 1;
    public static final int NAME_COLUMN = 1;
    // TODO: Create public field for each column in your table.
    // SQL Statement to create a new database.
   public static final String DATABASE_CREATE = "create table "+"LOGIN"+
            "( " +"ID"+" integer primary key autoincrement,"+ "protomernumber  text,area text, location text); ";
    // Variable to hold the database instance
    public  SQLiteDatabase db;
    // Context of the application using the database.
    private final Context context;
    // Database open/upgrade helper
    private DataBaseHelper dbHelper;
    public LoginDataBaseAdapter(Context _context)
    {
        context = _context;
        dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public  LoginDataBaseAdapter open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        db.close();
    }

    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    public void insertEntry(String protomernumber,String area,String location)
    {


        ContentValues newValues = new ContentValues();
        // Assign values for each row.
        newValues.put("protomernumber", protomernumber);
        newValues.put("area",area);
        newValues.put("location",location);
        Log.d("LogonDatabase", "password---------" + protomernumber);
        Log.d("LogonDatabase", "userName---------" + area);
        Log.d("LogonDatabase", "email------------" + location);

        // Insert the row into your table
        db.insert("LOGIN", null, newValues);
        ///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
    }
    public int deleteEntry(String protomernumber)
    {
        //String id=String.valueOf(ID);
        String where="USERNAME=?";
        int numberOFEntriesDeleted= db.delete("LOGIN", where, new String[]{protomernumber}) ;
         Toast.makeText(context, "Number fo Entry Deleted Successfully : " + numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
        return numberOFEntriesDeleted;
    }
    public String getSinlgeEntry(String protomernumber)
    {
        Cursor cursor=db.query("LOGIN", null, "protomernumber=?", new String[]{protomernumber}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();

       // String protomernumber= cursor.getString(cursor.getColumnIndex("protomernumber"));
        String getprotomernumber= cursor.getString(cursor.getColumnIndex("protomernumber"));
       // String location= cursor.getString(cursor.getColumnIndex("location"));

        Log.d("LogonDatabase","eeeee"+getprotomernumber);
        cursor.close();
        return getprotomernumber;
    }
    public void  updateEntry(String protomernumber,String area,String location)
    {


        // Define the updated row content.
        ContentValues updatedValues = new ContentValues();
        // Assign values for each row.
        updatedValues.put("protomernumber", protomernumber);
        updatedValues.put("area",area);
        updatedValues.put("location",location);


        String where="protomernumber = ?";
        db.update("LOGIN",updatedValues, where, new String[]{protomernumber});
    }
}