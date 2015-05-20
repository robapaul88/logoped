package com.pps.ppls.database;

import android.content.Context;

import com.pps.ppls.PPLSApplication;
import com.pps.ppls.model.Word;
import com.pps.ppls.model.WordDao;

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

    public static boolean resetSavedWords(Context context) {
        List<Word> words = getAllWords(context);
        if (words != null && words.size() > 0) {
            for (Word word : words) {
                word.setAnswerType(Word.UNANSWERED_ID);
                insertOrUpdate(context, word);
            }
        }
        return true;
    }

    private static WordDao getWordsDao(Context c) {
        return ((PPLSApplication) c.getApplicationContext()).getDaoSession().getWordDao();
    }
}
