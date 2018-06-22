package com.adilkhan.githubrepositorieslist.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="MyDatabase.db";

    public static final String TABLE_JAKES = "jakes";

    public static final String COLUMN_ID = "repoId";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_LANGUAGE= "language";
    public static final String COLUMN_WATCHERS = "watchers";
    public static final String COLUMN_OPEN_ISSUE= "issue";
    public static final String COLUMN_AVATAR= "avatar";

    public static final String KEY_IMAGE_DATA = "image_data";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_JAKES + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TITLE + " TEXT, " +
                    COLUMN_DESCRIPTION + " TEXT, " +
                    COLUMN_LANGUAGE + " TEXT, " +
                    COLUMN_WATCHERS + " TEXT, " +
                    COLUMN_OPEN_ISSUE + " NUMERIC, " +
                    COLUMN_AVATAR + " TEXT " +
                    ")";


    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_JAKES);
        db.execSQL(TABLE_CREATE);
    }
}
