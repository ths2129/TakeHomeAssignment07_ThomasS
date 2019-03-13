package com.example.takehomeassignment07_thomass;


import android.widget.Button;
import android.widget.TextView;




public class Question { //creates an object to hold fields

    private int mTextResId;   //m variables
    private Boolean mAnswerTrue;


    public Question(int isTextResId, boolean isAnswerTrue){ //constructor
        mTextResId = isTextResId;
        mAnswerTrue = isAnswerTrue;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int mTextResId) {
        this.mTextResId = mTextResId;
    }

    public Boolean isAnswerTrue() { //is it true or false?
        return mAnswerTrue;
    }

    public void setAnswerTrue(Boolean mAnswerTrue) {
        this.mAnswerTrue = mAnswerTrue;
    }}
