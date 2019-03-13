package com.example.takehomeassignment07_thomass;



import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.takehomeassignment07_thomass.R;

public class Cheat_Activity extends AppCompatActivity {

        private static final String EXTRA_ANSWER_IS_TRUE = "com.example.quiz_activity;";
        private static final String EXTRA_ANSWER_SHOWN = "com.example.quiz_activity;";

        private boolean mAnswerIsTrue;

        private TextView mAnswerTextView, mWarningTextView;
        private Button mShowAnswerButton;

        public static Intent newIntent(Context packageContext, boolean answerIsTrue) { //gathering information
                Intent intent = new Intent(packageContext, Cheat_Activity.class);
                intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
                return intent;
        }

        public static boolean wasAnswerShown(Intent result) {
                return result.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        }

        @Override
        public void onBackPressed() {
                startActivity(new Intent(Cheat_Activity.this, MainActivity.class));
        }


        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_cheat);

                mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

                mWarningTextView = findViewById(R.id.warning_text_view);


                mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);

                mShowAnswerButton = (Button) findViewById(R.id.show_answer_button);
                mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                                if (mAnswerIsTrue) { //conditional will be linked to XML - boolean logic
                                        mAnswerTextView.setText(R.string.yes);
                                } else {
                                        mAnswerTextView.setText(R.string.no);
                                }
                                setAnswerShownResult(true); //connects to method below
                        }
                });

        }

        private void setAnswerShownResult(boolean isAnswerShown) {
                Intent data = new Intent();
                data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
                setResult(RESULT_OK, data);
                Toast.makeText(Cheat_Activity.this, "Cheating is wrong", Toast.LENGTH_SHORT);
                finish();
        }

}
