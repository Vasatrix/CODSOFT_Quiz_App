package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class QuizSelectionActivity extends AppCompatActivity {

    private Button scienceQuizButton;
    private Button geographyQuizButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_selection);

        scienceQuizButton = findViewById(R.id.scienceQuizButton);
        geographyQuizButton = findViewById(R.id.geographyQuizButton);

        scienceQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz("Science");
            }
        });

        geographyQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz("Geography");
            }
        });
    }

    private void startQuiz(String category) {
        Intent intent = new Intent(QuizSelectionActivity.this, QuizActivity.class);
        intent.putExtra("CATEGORY", category);
        startActivity(intent);
    }
}
