package com.adilkhan.githubrepositorieslist.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


import com.adilkhan.githubrepositorieslist.Model.SingleRow;

import java.util.ArrayList;

public class DatabaseOperation
{
    public static final String LOGTAG = "ADIL";

    public static SQLiteOpenHelper dbhandler;
    private final Context context;
    SQLiteDatabase database;
    DatabaseHelper databaseHelper;

    private static final String[] allColumns =
            {
                    DatabaseHelper.COLUMN_ID,
                    DatabaseHelper.COLUMN_TITLE,
                    DatabaseHelper.COLUMN_DESCRIPTION,
                    DatabaseHelper.COLUMN_LANGUAGE,
                    DatabaseHelper.COLUMN_WATCHERS,
                    DatabaseHelper.COLUMN_OPEN_ISSUE,
                    DatabaseHelper.COLUMN_AVATAR
            };

    public DatabaseOperation(Context context)
    {
        this.context=context;
        databaseHelper =new DatabaseHelper(context);
    }

    public void open(){

        Log.i(LOGTAG,"Database Opened");

        database = databaseHelper.getWritableDatabase();


    }
    public void close()
    {
        Log.i(LOGTAG, "Database Closed");
        databaseHelper.close();

    }
    public SingleRow addSingleRow(SingleRow singleRow){

        ContentValues values  = new ContentValues();
        values.put(DatabaseHelper.COLUMN_TITLE,singleRow.getName());
        values.put(DatabaseHelper.COLUMN_DESCRIPTION,singleRow.getDescription());
        values.put(DatabaseHelper.COLUMN_LANGUAGE,singleRow.getLanguage());
        values.put(DatabaseHelper.COLUMN_WATCHERS, singleRow.getWatchers());
        values.put(DatabaseHelper.COLUMN_OPEN_ISSUE, singleRow.getOpen_issues());
        values.put(DatabaseHelper.COLUMN_AVATAR, singleRow.getAvatar_url());



        long insertid = database.insert(DatabaseHelper.TABLE_JAKES,null,values);

        if(insertid==-1)
        {
            Toast.makeText(context, "Not inserted ", Toast.LENGTH_SHORT).show();


        }

        else
        {



            singleRow.setDataId(insertid);

            return singleRow;

        }

        return null;

    }



    public ArrayList<SingleRow> getAllEmployees()
    {
        Cursor cursor = database.query(DatabaseHelper.TABLE_JAKES,allColumns,null,null,null, null, null);

        ArrayList<SingleRow> singleRows = new ArrayList<>();

        if(cursor!=null &&  cursor.getCount() > 0)
        {
            while(cursor.moveToNext()){

                SingleRow singleRow = new SingleRow();
                singleRow.setDataId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)));
                singleRow.setName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_TITLE)));
                singleRow.setDescription(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DESCRIPTION)));
                singleRow.setLanguage(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_LANGUAGE)));
                singleRow.setWatchers(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_WATCHERS)));
                singleRow.setOpen_issues(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_OPEN_ISSUE)));
                singleRow.setAvatar_url(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_AVATAR)));


                singleRows.add(singleRow);
            }
        }

        // return All Employees
        return singleRows;
    }
}
