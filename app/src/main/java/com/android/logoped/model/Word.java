package com.android.logoped.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by paul on 16/02/15.
 */
public class Word implements Parcelable {
    public static final int UNANSWERED_ID = -2;
    public static final int ANSWERED_CORRECTLY_ID = -1;
    public static final int ANSWERED_WRONG_FIRST_PART_ID = 0;
    public static final int ANSWERED_WRONG_MIDDLE_ID = 1;
    public static final int ANSWERED_WRONG_LAST_PART_ID = 2;
    public static final Parcelable.Creator<Word> CREATOR
            = new Parcelable.Creator<Word>() {
        public Word createFromParcel(Parcel in) {
            return new Word(in);
        }

        public Word[] newArray(int size) {
            return new Word[size];
        }
    };

    private Word(Parcel in) {
        _id = in.readInt();
        mName = in.readString();
        mImageName = in.readString();
        mFonem = in.readString();
        mAnswerType = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeString(mName);
        dest.writeString(mImageName);
        dest.writeString(mFonem);
        dest.writeInt(mAnswerType);
    }

    public enum AnswerType {
        UNANSWERED(UNANSWERED_ID),
        ANSWERED_CORRECTLY(ANSWERED_CORRECTLY_ID),
        ANSWERED_WRONG_FIRST_PART(ANSWERED_WRONG_FIRST_PART_ID),
        ANSWERED_WRONG_MIDDLE(ANSWERED_WRONG_MIDDLE_ID),
        ANSWERED_WRONG_LAST_PART(ANSWERED_WRONG_LAST_PART_ID);

        public int mCode;

        AnswerType(int code) {
            this.mCode = code;
        }

        public static AnswerType getByCode(int code) {
            for (AnswerType type : values()) {
                if (type.mCode == code) {
                    return type;
                }
            }
            return UNANSWERED;
        }
    }


    private int _id;
    private String mName;
    private String mImageName;
    private String mFonem;
    private int mAnswerType = AnswerType.UNANSWERED.mCode;

    public Word() {
    }

    public Word(String name, String imageName, String fonem) {
        this.mName = name;
        this.mImageName = imageName;
        this.mFonem = fonem;
    }

    public Word(String name, String imageName, String fonem, int answer) {
        this.mName = name;
        this.mImageName = imageName;
        this.mFonem = fonem;
        this.mAnswerType = answer;
    }

    public int getId() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getImageName() {
        return mImageName;
    }

    public void setImageName(String imageName) {
        this.mImageName = imageName;
    }

    public String getFonem() {
        return mFonem;
    }

    public void setFonem(String fonem) {
        this.mFonem = fonem;
    }

    public int getAnswerType() {
        return mAnswerType;
    }

    public void setAnswerType(int answerType) {
        this.mAnswerType = answerType;
    }

    @Override
    public String toString() {
        return mName + ", " + mImageName + "," + mFonem + "," + mAnswerType + "\n";
    }
}
