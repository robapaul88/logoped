package com.pps.ppls.model;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Entity mapped to table WORD.
 */
public class Word implements Parcelable {
    public static final int UNANSWERED_ID = -2;
    public static final int ANSWERED_CORRECTLY_ID = -1;
    public static final int ANSWERED_OMIS_ID = 0;
    public static final int ANSWERED_DISTORSIONAT_ID = 1;
    public static final int ANSWERED_INLOCUIT_ID = 2;
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
        id = in.readLong();
        name = in.readString();
        imageName = in.readString();
        audioName = in.readString();
        fonem = in.readString();
        answerType = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(imageName);
        dest.writeString(audioName);
        dest.writeString(fonem);
        dest.writeInt(answerType);
    }

    public enum AnswerType {
        UNANSWERED(UNANSWERED_ID),
        ANSWERED_CORRECTLY(ANSWERED_CORRECTLY_ID),
        ANSWERED_WRONG_OMIS(ANSWERED_OMIS_ID),
        ANSWERED_WRONG_DISTORSIONAT(ANSWERED_DISTORSIONAT_ID),
        ANSWERED_WRONG_INLOCUIT(ANSWERED_INLOCUIT_ID);

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

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageName='" + imageName + '\'' +
                ", audioName='" + audioName + '\'' +
                ", fonem='" + fonem + '\'' +
                ", answerType=" + answerType +
                '}';
    }

    public Word(String name, String imageName, String audioName, String fonem, Integer answerType) {
        this.name = name;
        this.imageName = imageName;
        this.audioName = audioName;
        this.fonem = fonem;
        this.answerType = answerType;
    }

    private Long id;
    private String name;
    private String imageName;
    private String audioName;
    private String fonem;
    private Integer answerType;

    public Word() {
    }

    public Word(Long id) {
        this.id = id;
    }

    public Word(Long id, String name, String imageName, String audioName, String fonem, Integer answerType) {
        this.id = id;
        this.name = name;
        this.imageName = imageName;
        this.audioName = audioName;
        this.fonem = fonem;
        this.answerType = answerType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getAudioName() {
        return audioName;
    }

    public void setAudioName(String audioName) {
        this.audioName = audioName;
    }

    public String getFonem() {
        return fonem;
    }

    public void setFonem(String fonem) {
        this.fonem = fonem;
    }

    public Integer getAnswerType() {
        return answerType;
    }

    public void setAnswerType(Integer answerType) {
        this.answerType = answerType;
    }

}
