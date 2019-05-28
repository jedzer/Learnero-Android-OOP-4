package com.jedzer.learnero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.jedzer.model.Quiz;

public class QuizDetail extends AppCompatActivity {

    TextView quizDetailTitleTextView;
    TextView quizDetailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_detail);

        quizDetailTitleTextView = findViewById(R.id.quizDetailTitleTextView);
        quizDetailTextView = findViewById(R.id.quizDetailTextView);

        long id = getIntent().getLongExtra(LessonDetailActivity.LESSON_DETAIL_EXTRA, -1);
        Quiz quiz = Quiz.find(Quiz.class, "id = ?", Long.toString(id)).get(0);

        quizDetailTitleTextView.setText(quiz.getTitle());
        quizDetailTextView.setText(quiz.getText());
    }
}
