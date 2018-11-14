package com.pps.ppls.database;

import android.content.Context;

import com.pps.ppls.PPLSApplication;
import com.pps.ppls.model.Word;
import com.pps.ppls.model.WordDao;

import java.util.List;

/**
 * Created by roba on 3/12/2015.
 */
class WordsRepo {
    static void insertOrUpdate(Context context, Word word) {
        getWordsDao(context).insertOrReplace(word);
    }

    static List<Word> getAllWords(Context context) {
        return getWordsDao(context).loadAll();
    }

    static List<Word> getAnsweredWords(Context context) {
        return getWordsDao(context).queryBuilder()
                .where(WordDao.Properties.AnswerType.notEq(String.valueOf(Word.UNANSWERED_ID)))
                .list();
    }

    static boolean resetSavedWords(Context context) {
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
