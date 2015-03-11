package com.android.logoped.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.logoped.model.Word;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paul on 16/02/15.
 */
public class DbManager {
    private static final String TAG = DbManager.class.getSimpleName();

    private static DbManager _instance;

    private static WordsDbHelper mDbHelper;

    private DbManager(Context context) {
        _instance = this;
        mDbHelper = new WordsDbHelper(context);
    }

    public static DbManager getInstance(Context context) {
        return _instance != null ? _instance : new DbManager(context);
    }

    public boolean insertWords(List<Word> words) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        boolean success = true;
        if (words != null && words.size() > 0) {
            for (Word word : words) {
                ContentValues values = new ContentValues();
                values.put(WordItem.WordEntry.COLUMN_NAME_WORD_NAME, word.getName());
                values.put(WordItem.WordEntry.COLUMN_NAME_WORD_IMAGE_NAME, word.getImageName());
                values.put(WordItem.WordEntry.COLUMN_NAME_WORD_FONEM, word.getFonem());
                values.put(WordItem.WordEntry.COLUMN_NAME_ANSWER_TYPE, word.getAnswerType());

                long result = db.insert(
                        WordItem.WordEntry.WORDS_TABLE_NAME,
                        null,
                        values);
                if (result <= 0) {
                    success = false;
                }
            }
        }
        db.close();
        return success;
    }

//    public boolean deleteWord(int id) {
//        SQLiteDatabase db = mDbHelper.getWritableDatabase();
//        int result = db.delete(WordItem.WordEntry.WORDS_TABLE_NAME, WordItem.WordEntry._ID + "=?", new String[]{String.valueOf(id)});
//        return result > 0;
//    }

    public List<Word> getAllWords() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        List<Word> result = new ArrayList<>();
        Cursor c = db.query(WordItem.WordEntry.WORDS_TABLE_NAME, WordItem.WordEntry.COLUMNS, null, null, null, null, null);
        if (c != null) {
            Log.d(TAG, "C has:" + c.getCount() + " items");
            if (c.moveToFirst()) {
                while (!c.isAfterLast()) {
                    result.add(cursorToWord(c));
                    c.moveToNext();
                }
            }
            c.close();
        }
        return result;
    }

    private Word cursorToWord(Cursor cursor) {
        Word word = new Word();
        word.set_id(cursor.getInt(0));
        word.setName(cursor.getString(1));
        word.setImageName(cursor.getString(2));
        word.setFonem(cursor.getString(3));
        word.setAnswerType(cursor.getInt(4));
        return word;
    }

    public boolean updateWordAnswer(Word word) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        boolean success = true;

        ContentValues values = new ContentValues();
        values.put(WordItem.WordEntry.COLUMN_NAME_WORD_NAME, word.getName());
        values.put(WordItem.WordEntry.COLUMN_NAME_WORD_IMAGE_NAME, word.getImageName());
        values.put(WordItem.WordEntry.COLUMN_NAME_WORD_FONEM, word.getFonem());
        values.put(WordItem.WordEntry.COLUMN_NAME_ANSWER_TYPE, word.getAnswerType());

        int result = db.update(
                WordItem.WordEntry.WORDS_TABLE_NAME,
                values, WordItem.WordEntry._ID + "=?", new String[]{String.valueOf(word.getId())});
        if (result <= 0) {
            success = false;
        }
        db.close();
        return success;
    }

    public boolean resetSavedAnswers() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        boolean success = true;
        ContentValues values = new ContentValues();
        values.put(WordItem.WordEntry.COLUMN_NAME_ANSWER_TYPE, Word.AnswerType.UNANSWERED.mCode);
        int result = db.update(
                WordItem.WordEntry.WORDS_TABLE_NAME,
                values, null, null);
        if (result <= 0) {
            success = false;
        }
        db.close();
        return success;
    }

    public List<Word> getAllAnsweredWords() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        List<Word> result = new ArrayList<>();
        Cursor c = db.query(WordItem.WordEntry.WORDS_TABLE_NAME, WordItem.WordEntry.COLUMNS, WordItem.WordEntry.COLUMN_NAME_ANSWER_TYPE + " != ?", new String[]{String.valueOf(Word.UNANSWERED_ID)}, null, null, null);
        if (c != null) {
            Log.d(TAG, "Answered:" + c.getCount() + " items");
            if (c.moveToFirst()) {
                while (!c.isAfterLast()) {
                    result.add(cursorToWord(c));
                    c.moveToNext();
                }
            }
            c.close();
        }
        return result;
    }
}
