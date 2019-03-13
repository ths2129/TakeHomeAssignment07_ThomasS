package com.example.takehomeassignment07_thomass;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.Locale;

import static com.example.takehomeassignment07_thomass.Keys.REQUEST_CODE_CHEAT;

public class MainActivity extends AppCompatActivity {

    //data can be passed back and forth
    //private static final int REQUEST_CODE_CHEAT = 0; //This will increase if cheat is clicked
    private static final String KEY_INDEX = "index";
    private static final String TAG = "cycle";  // for Logcat


    private Button mTrueButton, mFalseButton, mNextButton, mCheatButton, mPreviousButton; //member variable is more private and static global variables.
    private TextView mQuestionTextView, mWarningTextView;

    private Question[] mQuestionBank = new Question[]{ // array
            new Question(R.string.question_australia, true),
            new Question(R.string.question_america, false),
            new Question(R.string.question_asia, true),
            new Question(R.string.question_mideast, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.capital, true),
    };

    private int mCurrentIndex = 0; //Where we start in array - will increase with next button
    private boolean mIsCheater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_main);

       /* mQuestionTextView = findViewById(R.id.question_text_view);
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);

        }*/

        mQuestionTextView = findViewById(R.id.question_text_view); //activity is calling
        updateQuestion(); //method used twice


        mTrueButton = findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true); //looking for boolean connecting to method
            }
        });


        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false); //Toast message from method
            }
        });


        mNextButton = findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length; //increase index to move down the array - modular division gets the remainer - goes through lentgth and resets at 0                mIsCheater = false;
                mIsCheater = false; //setting to false because they clicked it
                updateQuestion();
            }
        });


        mPreviousButton = (Button) findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mCheatButton = findViewById(R.id.cheat_button); //linked to XML
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent intent = Cheat_Activity.newIntent(MainActivity.this, answerIsTrue); //boolean is passed
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
                updateQuestion();//passing information back and forth global
            }
        });
        updateQuestion();

    }

    private void updateQuestion() { //method -
        int question = mQuestionBank[mCurrentIndex].getTextResId();//Getter from question class
        mQuestionTextView.setText(question);//takes the variables that gets and linking variable to XML

    }

    private void checkAnswer(boolean userPressedTrue) { //method - if user pressed true
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue(); //variable that holds spots

        int messageResId;

        if (mIsCheater) { // id this is true this will be the toast message
            messageResId = R.string.judgement_toast;
        } else

        if (userPressedTrue == answerIsTrue) { //variable
            messageResId = R.string.correct_toast; //answer is true - toast is true
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(MainActivity.this, messageResId, Toast.LENGTH_SHORT).show(); //Toast holds variable conditional
    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) { //everything checked out
            return; //won't do anything
        }

        if (requestCode == REQUEST_CODE_CHEAT) { //if equal to the code
            if (data == null) { // and data equal to null it will return
                return;
            }
            mIsCheater = Cheat_Activity.wasAnswerShown(data); //grabbing information

        }

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
        Toast.makeText(this, "DESTROY", Toast.LENGTH_SHORT).show(); // Will appear when app is destroyed
    }
}