package com.android.logoped.database;

import android.provider.BaseColumns;

/**
 * Created by paul on 16/02/15.
 */
public final class WordItem {
    public WordItem() {
    }

    public static abstract class WordEntry implements BaseColumns {
        public static final String WORDS_TABLE_NAME = "words";
        public static final String COLUMN_NAME_WORD_NAME = "wordName";
        public static final String COLUMN_NAME_WORD_IMAGE_NAME = "wordImageName";
        public static final String COLUMN_NAME_WORD_FONEM = "wordFonem";
        public static final String COLUMN_NAME_ANSWER_TYPE = "wordAnswerType";
        public static final String[] COLUMNS = {_ID, COLUMN_NAME_WORD_NAME, COLUMN_NAME_WORD_IMAGE_NAME, COLUMN_NAME_WORD_FONEM, COLUMN_NAME_ANSWER_TYPE};
    }
}
