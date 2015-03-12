package com.android.logoped.database;

import android.content.Context;

import com.android.logoped.LogopedApplication;
import com.android.logoped.model.Word;
import com.android.logoped.model.WordDao;

import java.util.List;

/**
 * Created by roba on 3/12/2015.
 */
public class WordsRepo {
    public static void insertOrUpdate(Context context, Word word) {
        getWordsDao(context).insertOrReplace(word);
    }

    public static void clearWords(Context context) {
        getWordsDao(context).deleteAll();
    }

    public static List<Word> getAllWords(Context context) {
        return getWordsDao(context).loadAll();
    }

    public static List<Word> getAnsweredWords(Context context) {
        return getWordsDao(context).queryBuilder().where(WordDao.Properties.AnswerType.notEq(String.valueOf(Word.UNANSWERED_ID))).list();
    }

    private static WordDao getWordsDao(Context c) {
        return ((LogopedApplication) c.getApplicationContext()).getDaoSession().getWordDao();
    }
}
