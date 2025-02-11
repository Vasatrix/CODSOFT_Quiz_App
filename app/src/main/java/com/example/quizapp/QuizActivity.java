package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private TextView questionTextView;
    private Button option1Button, option2Button, option3Button, option4Button, restartButton, homeButton;
    private List<Question> questionList;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private String quizCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        quizCategory = getIntent().getStringExtra("CATEGORY");

        questionTextView = findViewById(R.id.questionTextView);
        option1Button = findViewById(R.id.option1Button);
        option2Button = findViewById(R.id.option2Button);
        option3Button = findViewById(R.id.option3Button);
        option4Button = findViewById(R.id.option4Button);
        restartButton = findViewById(R.id.restartButton);
        homeButton = findViewById(R.id.homeButton);

        loadQuestions(quizCategory);
        displayQuestion();

        setAnswerListeners();

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartQuiz();
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToHome();
            }
        });
    }

    private void loadQuestions(String category) {
        questionList = new ArrayList<>();
        if (category.equals("Science")) {
            questionList.add(new Question("What is the chemical symbol for water?", "O2", "H2O", "CO2", "HO2", 2));
            questionList.add(new Question("Which planet is closest to the sun?", "Mars", "Earth", "Mercury", "Venus", 3));
            questionList.add(new Question("What gas do plants produce?", "Oxygen", "Carbon", "Nitrogen", "Helium", 1));
            questionList.add(new Question("What is the boiling point of water?", "90°C", "110°C", "100°C", "120°C", 3));
            questionList.add(new Question("What part of the plant conducts photosynthesis?", "Leaf", "Root", "Stem", "Flower", 1));
        } else if (category.equals("Geography")) {
            questionList.add(new Question("Which is the largest continent?", "Africa", "Asia", "Europe", "Australia", 2));
            questionList.add(new Question("What is the capital of Australia?", "Melbourne", "Sydney", "Canberra", "Brisbane", 3));
            questionList.add(new Question("Which ocean is the deepest?", "Indian", "Atlantic", "Pacific", "Arctic", 3));
            questionList.add(new Question("What is the smallest country?", "Vatican City", "Monaco", "Maldives", "Liechtenstein", 1));
            questionList.add(new Question("Which country is known as the Land of the Rising Sun?", "China", "South Korea", "Japan", "Thailand", 3));
        }
    }

    private void displayQuestion() {
        Question currentQuestion = questionList.get(currentQuestionIndex);
        questionTextView.setText(currentQuestion.getQuestion());
        option1Button.setText(currentQuestion.getOption1());
        option2Button.setText(currentQuestion.getOption2());
        option3Button.setText(currentQuestion.getOption3());
        option4Button.setText(currentQuestion.getOption4());
        restartButton.setVisibility(View.GONE);
        homeButton.setVisibility(View.GONE);
    }

    private void setAnswerListeners() {
        View.OnClickListener answerListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedOption = Integer.parseInt(v.getTag().toString());
                checkAnswer(selectedOption);
                if (currentQuestionIndex < questionList.size() - 1) {
                    currentQuestionIndex++;
                    displayQuestion();
                } else {
                    finishQuiz();
                }
            }
        };

        option1Button.setOnClickListener(answerListener);
        option2Button.setOnClickListener(answerListener);
        option3Button.setOnClickListener(answerListener);
        option4Button.setOnClickListener(answerListener);
    }

    private void checkAnswer(int selectedOption) {
        if (selectedOption == questionList.get(currentQuestionIndex).getCorrectAnswer()) {
            score++;
        }
    }

    private void finishQuiz() {
        questionTextView.setText("Quiz finished! Your score: " + score);
        option1Button.setVisibility(View.GONE);
        option2Button.setVisibility(View.GONE);
        option3Button.setVisibility(View.GONE);
        option4Button.setVisibility(View.GONE);
        restartButton.setVisibility(View.VISIBLE);
        homeButton.setVisibility(View.VISIBLE);
    }

    private void restartQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        option1Button.setVisibility(View.VISIBLE);
        option2Button.setVisibility(View.VISIBLE);
        option3Button.setVisibility(View.VISIBLE);
        option4Button.setVisibility(View.VISIBLE);
        displayQuestion();
    }

    private void goToHome() {
        Intent intent = new Intent(QuizActivity.this, QuizSelectionActivity.class);
        startActivity(intent);
        finish();
    }
}
