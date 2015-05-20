package com.pps.ppls.database;

import android.content.Context;

import com.pps.ppls.model.Word;

import java.util.List;

/**
 * Created by paul on 16/02/15.
 */
public class DbManager {
    private static final String TAG = DbManager.class.getSimpleName();

    private static DbManager _instance;
    private Context mContext;

    private DbManager(Context context) {
        _instance = this;
        mContext = context;
    }

    public static DbManager getInstance(Context context) {
        return _instance != null ? _instance : new DbManager(context);
    }

    public boolean insertWords(List<Word> words) {
        if (words != null && words.size() > 0) {
            for (Word word : words) {
                WordsRepo.insertOrUpdate(mContext, word);
            }
            return true;
        }

        return false;
    }

    public List<Word> getAllWords() {
        return WordsRepo.getAllWords(mContext);
    }

    public boolean updateWordAnswer(Word word) {
        WordsRepo.insertOrUpdate(mContext, word);
        return true;
    }

    public boolean resetSavedAnswers() {
        return WordsRepo.resetSavedWords(mContext);
    }

    public List<Word> getAllAnsweredWords() {
        return WordsRepo.getAnsweredWords(mContext);
    }
}
