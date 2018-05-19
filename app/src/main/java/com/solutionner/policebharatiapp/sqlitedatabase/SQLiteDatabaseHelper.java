package com.solutionner.policebharatiapp.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by DEV on 31-Oct-17.
 */

public class SQLiteDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "HospitalDatabase";

    //Table names
    private static final String TABLENAME_ADDHOSPITAL = "addhospital";
    private static final String TABLENAME_ADDRATINGS = "addrating";


    //Column for add hospital
    private static final String COL_HOSPITAL_ID = "hospitalid";
    private static final String COL_HOSPITALNAME = "hospitalname";
    private static final String COL_HOSPITALUSERNAME = "hospitalusername";
    private static final String COL_HOSPITALMOBILE = "hospitalmobile";

    //Column for add Ratings
    private static final String COL_SUBMIT_RATING = "rating";
    private static final String COL_SUBMIT_COMMENTS = "comments";
    private static final String COL_SUBMIT_FEEDBACK_BY = "feedbackby";


    public SQLiteDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table addhospital" + "(Id INTEGER PRIMARY KEY AUTOINCREMENT,hospitalid text,hospitalname text,hospitalusername text,hospitalmobile text)");
        db.execSQL("create table addrating" + "(Id INTEGER PRIMARY KEY AUTOINCREMENT,rating text,comments text,feedbackby text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME_ADDHOSPITAL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME_ADDRATINGS);
        onCreate(db);
    }

    //Insert data for hospital
    public long insertHospitalData(String hospitalId, String hospitalName, String hospitalUsername, String hospitalMobile) {

        SQLiteDatabase lDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_HOSPITAL_ID, hospitalId);
        contentValues.put(COL_HOSPITALNAME, hospitalName);
        contentValues.put(COL_HOSPITALUSERNAME, hospitalUsername);
        contentValues.put(COL_HOSPITALMOBILE, hospitalMobile);
        long results = lDB.insert(TABLENAME_ADDHOSPITAL, null, contentValues);
        return results;
    }

    //Insert data for Rating Record
    public long insertAllRatingData(String ratingJsonArray, String commentJsonArray, String feedbackBy) {

        SQLiteDatabase lDB2 = this.getWritableDatabase();
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(COL_SUBMIT_RATING, ratingJsonArray);
        contentValues2.put(COL_SUBMIT_COMMENTS, commentJsonArray);
        contentValues2.put(COL_SUBMIT_FEEDBACK_BY, feedbackBy);
        long resultActivity = lDB2.insert(TABLENAME_ADDRATINGS, null, contentValues2);
        return resultActivity;
    }

//    //getAll User
//    public ArrayList<HospitalModel.Data> getAllHospital() {
//        SQLiteDatabase lDB = this.getReadableDatabase();
//        String lQuery = "Select * From " + TABLENAME_ADDHOSPITAL;
//        Cursor lCursor = lDB.rawQuery(lQuery, null);
//        ArrayList<HospitalModel.Data> lList = new ArrayList<HospitalModel.Data>();
//        if (lCursor.moveToFirst()) {
//            do {
//                HospitalModel.Data lFixture = new HospitalModel.Data();
//                int id = lCursor.getInt(0);
//                lFixture.setId(lCursor.getString(1));
//                lFixture.setName(lCursor.getString(2));
//                lFixture.setUser_name(lCursor.getString(3));
//                lFixture.setMobile_number(lCursor.getString(4));
//
//                lList.add(lFixture);
//            } while (lCursor.moveToNext());
//        }
//        lCursor.close();
//        return lList;
//    }
//
//    //getAll Rating Data
//    public ArrayList<SubmitRecordModel.Data> getAllRating() {
//        SQLiteDatabase lDB = this.getReadableDatabase();
//        String lQuery = "Select * From " + TABLENAME_ADDRATINGS;
//        Cursor lCursor = lDB.rawQuery(lQuery, null);
//        ArrayList<SubmitRecordModel.Data> lList = new ArrayList<SubmitRecordModel.Data>();
//        if (lCursor.moveToFirst()) {
//            do {
//                SubmitRecordModel.Data lFixture = new SubmitRecordModel.Data();
//                int id = lCursor.getInt(0);
//                lFixture.setId(lCursor.getString(1));
//                lFixture.setName(lCursor.getString(2));
//                lFixture.setUser_name(lCursor.getString(3));
//
//                lList.add(lFixture);
//            } while (lCursor.moveToNext());
//        }
//        lCursor.close();
//        return lList;
//    }

    public void onDeleteHospitalData() {
        SQLiteDatabase lDB = this.getReadableDatabase();
        lDB.execSQL("delete from " + TABLENAME_ADDHOSPITAL);
    }

    public void onDeleteRatingData() {
        SQLiteDatabase lDB = this.getReadableDatabase();
        lDB.execSQL("delete from " + TABLENAME_ADDRATINGS);
    }

}