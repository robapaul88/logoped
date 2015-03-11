package com.android.logoped.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.w3c.dom.Text;

/**
 * Created by paul on 16/02/15.
 */
public class WordsDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "logoped.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + WordItem.WordEntry.WORDS_TABLE_NAME + " (" +
                    WordItem.WordEntry._ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                    WordItem.WordEntry.COLUMN_NAME_WORD_NAME + TEXT_TYPE + COMMA_SEP +
                    WordItem.WordEntry.COLUMN_NAME_WORD_IMAGE_NAME + TEXT_TYPE + COMMA_SEP + WordItem.WordEntry.COLUMN_NAME_WORD_FONEM + TEXT_TYPE + COMMA_SEP + WordItem.WordEntry.COLUMN_NAME_ANSWER_TYPE + INTEGER_TYPE + " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + WordItem.WordEntry.WORDS_TABLE_NAME;

    public WordsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
