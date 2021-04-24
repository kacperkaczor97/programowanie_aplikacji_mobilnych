package com.example.kalkulatorbmi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Quiz extends AppCompatActivity {

    private static final int QUEST_IN_QUIZ = 6;

    ArrayList<Question> quizQuestionList = new ArrayList<Question>() {
        {
            add(new Question("Co to jest glukoza?", new ArrayList<String>(Arrays.asList(
                    "jest to cukier", "len na gardło", "klej do papieru"
            )), "jest to cukier"));
            add(new Question("Błonnik to?", new ArrayList<String>(Arrays.asList(
                    "Kwiat", "Składnik potrzebny do prawidlowego odzywiania", "marka samochodu"
            )), "Składnik potrzebny do prawidlowego odzywiania"));
            add(new Question("Gdzie jest najwiecej błonnika?", new ArrayList<String>(Arrays.asList(
                    "W pieczywie", "sałata", "fasola"
            )), "W pieczywie"));
            add(new Question("Co powinniśmy jeść codziennie?", new ArrayList<String>(Arrays.asList(
                    "owoce i warzywa", "pieczywo", "mięso"
            )), "owoce i warzywa"));
            add(new Question("Ile godzin powiniśmy spać?", new ArrayList<String>(Arrays.asList(
                    "4", "8", "10"
            )), "8"));
            add(new Question("Ile posiłkow powinniśmy jeść?", new ArrayList<String>(Arrays.asList(
                    "1", "3", "4-5"
            )), "4-5"));
        }
    };
    private Handler handler; // used to delay loading next flag
    private int guessRows = 3; // number of rows displaying guess Buttons
    private List<String> answerList;
    private String correctAnswer; // correct country for the current flag
    private int totalGuesses; // number of guesses made
    private int correctAnswers; // number of correct guesses
    private TextView questionTextView;
    private LinearLayout quizLayout; // layout that contains the quiz
    private TextView questionNumberTextView; // shows current question #
    private LinearLayout[] guessLinearLayouts; // rows of answer Buttons
    private TextView answerTextView; // displays correct answer


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        handler = new Handler();
        questionNumberTextView = findViewById(R.id.questionNumberTextView);
        questionTextView = findViewById(R.id.question);
        guessLinearLayouts = new LinearLayout[3];
        guessLinearLayouts[0] = findViewById(R.id.row1LinearLayout);
        guessLinearLayouts[1] = findViewById(R.id.row2LinearLayout);
        guessLinearLayouts[2] = findViewById(R.id.row3LinearLayout);
        answerTextView = findViewById(R.id.answerTextView);

        for (LinearLayout row : guessLinearLayouts) {
            Button button = (Button) row.getChildAt(0);
            button.setOnClickListener(guessButtonListener);
        }

// set questionNumberTextView's text
        questionNumberTextView.setText(
                getString(R.string.question, 1, QUEST_IN_QUIZ));
        resetQuiz();


    }


    public void resetQuiz() {
        if (answerList != null) {
            answerList.clear();
        }

        correctAnswers = 0;
        totalGuesses = 0;
        loadNextQuest();
    }

    private void disableButtons() {
        for (int row = 0; row < guessRows; row++) {
            LinearLayout guessRow = guessLinearLayouts[row];
            for (int i = 0; i < guessRow.getChildCount(); i++)
                guessRow.getChildAt(i).setEnabled(false);
        }
    }

    public static class MyAlertDialogFragment extends DialogFragment {

        private OnYesNoClick yesNoClick;

        public static MyAlertDialogFragment newInstance(int totalGuesses) {
            MyAlertDialogFragment frag = new MyAlertDialogFragment();
            Bundle args = new Bundle();
            args.putInt("totalGuesses", totalGuesses);
            frag.setArguments(args);
            return frag;
        }

        public void setOnYesNoClick(OnYesNoClick yesNoClick) {
            this.yesNoClick = yesNoClick;
        }

        @Override
        public Dialog onCreateDialog(Bundle bundle) {
            int totalGuesses = getArguments().getInt("totalGuesses");
            AlertDialog.Builder builder =
                    new AlertDialog.Builder(getActivity());
            builder.setMessage(
                    getString(R.string.results,
                            totalGuesses,
                            (100 * QUEST_IN_QUIZ / (double) totalGuesses)));

            // "Reset Quiz" Button
            builder.setPositiveButton(R.string.reset_quiz,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int id) {
                            yesNoClick.onYesClicked();
                        }
                    }
            );

            return builder.create(); // return the AlertDialog
        }


        public interface OnYesNoClick {
            void onYesClicked();
        }
    }

    private void showYesNoDialog() {
        MyAlertDialogFragment yesNoAlert = MyAlertDialogFragment.newInstance(
                totalGuesses);
        yesNoAlert.show(getSupportFragmentManager(), "quiz results");

        yesNoAlert.setOnYesNoClick(new MyAlertDialogFragment.OnYesNoClick() {
            @Override
            public void onYesClicked() {
                resetQuiz();
            }

        });
    }

    private void loadNextQuest() {
        // get file name of the next flag and remove it from the list
        Question nextQuestion = quizQuestionList.get(correctAnswers);
        correctAnswer = nextQuestion.correctAnswer; // update the correct answer
        answerTextView.setText(""); // clear answerTextView

        // display current question number
        questionTextView.setText(nextQuestion.question);
        questionNumberTextView.setText(getString(
                R.string.question, (correctAnswers + 1), QUEST_IN_QUIZ));

        // add 2, 4, 6 or 8 guess Buttons based on the value of guessRows
        for (int row = 0; row < guessRows; row++) {
            // place Buttons in currentTableRow
            for (int column = 0;
                 column < guessLinearLayouts[row].getChildCount();
                 column++) {
                // get reference to Button to configure
                Button newGuessButton =
                        (Button) guessLinearLayouts[row].getChildAt(column);
                newGuessButton.setEnabled(true);
                newGuessButton.setText(nextQuestion.answers.get(row));
            }
        }
    }


    private View.OnClickListener guessButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button guessButton = ((Button) v);
            String guess = guessButton.getText().toString();
            String answer = correctAnswer;
            ++totalGuesses; // increment number of guesses the user has made

            if (guess.equals(answer)) { // if the guess is correct
                ++correctAnswers; // increment the number of correct answers

                // display correct answer in green text
                answerTextView.setText("Dobrze!");
                answerTextView.setTextColor(
                        getResources().getColor(R.color.correct_answer,
                                getApplicationContext().getTheme()));

                disableButtons(); // disable all guess Buttons

                // if the user has correctly identified FLAGS_IN_QUIZ flags
                if (correctAnswers == QUEST_IN_QUIZ) {
                // DialogFragment to display quiz stats and start new quiz
                    showYesNoDialog();
                } else { // answer is correct but quiz is not over
                    // load the next flag after a 2-second delay
                    handler.postDelayed(
                            new Runnable() {
                                @Override
                                public void run() {
                                    loadNextQuest();
                                }
                            }, 2000); // 2000 milliseconds for 2-second delay
                }
            } else { // answer was incorrect

                // display "Incorrect!" in red
                answerTextView.setText(R.string.incorrect_answer);
                answerTextView.setTextColor(getResources().getColor(
                        R.color.incorrect_answer, getApplicationContext().getTheme()));
                guessButton.setEnabled(false); // disable incorrect answer
            }
        }
    };
}