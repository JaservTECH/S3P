package com.jafar.jaservtech.system3pjagung.dummy;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Jafar on 4/5/2016.
 */
public class DBHandler extends  SQLiteOpenHelper{

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "System_third_p";

    // Contacts table name
    private static final String TABLE_ProgressS = "sc_prog";
    private static final String TABLE_ProgressS2 = "sc_detail_prog";

    // Progresss Table Columns names
    private static final String KEY_ID = "p_id";
    private static final String KEY_NAME = "p_name";
    private static final String KEY_SH_ADDR = "p_address";
    private static final String KEY_SH_SDATE = "p_start_date";
    private static final String KEY_SH_EDATE = "p_end_date";
    private static final String KEY_SH_AUT = "p_auth";
    private static final String KEY_SH_FIN = "p_finish";
    private static final String KEY_SH_CAT = "p_cat";
    private static final String KEY_SH_PERC = "p_percent";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_ProgressS + "("
                + KEY_ID + " TEXT PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_SH_ADDR + " TEXT,"
                + KEY_SH_SDATE + " TEXT,"
                + KEY_SH_EDATE + " TEXT,"
                + KEY_SH_AUT + " TEXT,"
                + KEY_SH_FIN + " INTEGER,"
                + KEY_SH_CAT + " INTEGER,"
                + KEY_SH_PERC + " INTEGER"
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ProgressS);
        // Creating tables again
        onCreate(db);
    }
    //SESSION PROGRESS
    // Adding new Progress okay
    public boolean addProgress(Progress Progress) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, Progress.getId()); // Progress Phone Number
        values.put(KEY_NAME, Progress.getName()); // Progress Name
        values.put(KEY_SH_ADDR, Progress.getAddress()); // Progress Address of field
        values.put(KEY_SH_SDATE, Progress.getStartDate()); // Progress Start farming
        values.put(KEY_SH_EDATE, Progress.getEndDate()); // Progress End Farming
        values.put(KEY_SH_AUT, Progress.getAuth()); // Progress Stake holder
        values.put(KEY_SH_FIN, Progress.getFinish()); // Progress finish
        values.put(KEY_SH_CAT, Progress.getCat()); // Category
        values.put(KEY_SH_PERC, Progress.getPercentage()); // Progress percentage
        // Inserting Row
        db.insert(TABLE_ProgressS, null, values);
        db.close(); // Closing database connection
        return true;
    }

    // Getting one Progress
    public Progress getProgress(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ProgressS, new String[]{KEY_ID,
                        KEY_NAME, KEY_SH_ADDR}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor == null)
            return null;
        cursor.moveToFirst();

        Progress contact = new Progress();
        contact.setId(cursor.getString(0));
        contact.setName(cursor.getString(1));
        contact.setAddress(cursor.getString(2));
        contact.setStartDate(cursor.getString(3));
        contact.setEndDate(cursor.getString(4));
        contact.setAuth(cursor.getString(5));
        contact.setFinish(Integer.parseInt(cursor.getString(6)));
        contact.setCat(Integer.parseInt(cursor.getString(7)));
        contact.setPercentage(Integer.parseInt(cursor.getString(8)));
        // return Progress
        return contact;
    }

    // Getting All Progresss
    public List<Progress> getAllProgresss() {
        List<Progress> ProgressList = new ArrayList<Progress>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ProgressS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Progress contact = new Progress();
                contact.setId(cursor.getString(0));
                contact.setName(cursor.getString(1));
                contact.setAddress(cursor.getString(2));
                contact.setStartDate(cursor.getString(3));
                contact.setEndDate(cursor.getString(4));
                contact.setAuth(cursor.getString(5));
                contact.setFinish(Integer.parseInt(cursor.getString(6)));
                contact.setCat(Integer.parseInt(cursor.getString(7)));
                contact.setPercentage(Integer.parseInt(cursor.getString(8)));
                // Adding contact to list
                ProgressList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return ProgressList;
    }

    public List<Progress> getAllProgHistory() {
        List<Progress> ProgressList = new ArrayList<Progress>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ProgressS+"WHERE "+KEY_SH_FIN+"=1";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Progress contact = new Progress();
                contact.setId(cursor.getString(0));
                contact.setName(cursor.getString(1));
                contact.setAddress(cursor.getString(2));
                contact.setStartDate(cursor.getString(3));
                contact.setEndDate(cursor.getString(4));
                contact.setAuth(cursor.getString(5));
                contact.setFinish(Integer.parseInt(cursor.getString(6)));
                contact.setCat(Integer.parseInt(cursor.getString(7)));
                contact.setPercentage(Integer.parseInt(cursor.getString(8)));
                // Adding contact to list
                ProgressList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return ProgressList;
    }
    // Getting ProgresssHistory Count
    public int getProgresssHisCount(int t) {
        String countQuery = "SELECT  * FROM " + TABLE_ProgressS + " WHERE " + KEY_SH_FIN + "=" + t;
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery(countQuery, null).getCount();
    }

    // Updating a Progress
    public int updateProgress(Progress Progress) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, Progress.getName()); // Progress Name
        values.put(KEY_SH_ADDR, Progress.getAddress()); // Progress Address of field
        values.put(KEY_SH_SDATE, Progress.getStartDate()); // Progress Start farming
        values.put(KEY_SH_EDATE, Progress.getEndDate()); // Progress End Farming
        values.put(KEY_SH_AUT, Progress.getAuth()); // Progress Stake holder
        values.put(KEY_SH_FIN, Progress.getFinish()); // Progress finish
        values.put(KEY_SH_CAT, Progress.getCat()); // kategori
        values.put(KEY_SH_PERC, Progress.getPercentage()); // Progress percentage

        // updating row
        return db.update(TABLE_ProgressS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(Progress.getId())});
    }

    // Deleting a Progress
    public void deleteProgress(Progress Progress) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ProgressS, KEY_ID + " = ?",
                new String[] { String.valueOf(Progress.getId()) });
        db.close();
    }
}


