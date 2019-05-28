package com.jedzer.learnero;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.jedzer.adapters.QuizListAdapter;
import com.jedzer.model.Lesson;
import com.jedzer.model.Quiz;

import java.util.List;


public class LessonDetailActivity extends AppCompatActivity {
    public static final String LESSON_DETAIL_EXTRA = "LESSON_DETAIL_EXTRA";


    TextView lessonDetailTitleTextView;
    TextView lessonDetailDescriptionTextView;
    ListView lessonDetailQuizListView;


    private void setItems(Lesson lesson)
    {
        lessonDetailDescriptionTextView.setText(lesson.getDescription());
        lessonDetailTitleTextView.setText(lesson.getTitle());
        List<Quiz> quizzes = Quiz.find(Quiz.class, "lesson = ?", Long.toString(lesson.getId()));
        QuizListAdapter adapter = new QuizListAdapter(this, quizzes);
        lessonDetailQuizListView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_detail);

        lessonDetailTitleTextView = findViewById(R.id.lessonDetailTitleTextView);
        lessonDetailDescriptionTextView = findViewById(R.id.lessonDetailDescriptionTextView);
        lessonDetailQuizListView = findViewById(R.id.lessonDetailQuizListView);

        long id = getIntent().getLongExtra(CourseDetailActivity.LESSON_EXTRA, -1);
        Lesson lesson = Lesson.find(Lesson.class, "id = ?", Long.toString(id)).get(0);

        setItems(lesson);

        lessonDetailQuizListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Quiz quiz = (Quiz) lessonDetailQuizListView.getItemAtPosition(position);
                Intent intent = new Intent(LessonDetailActivity.this, QuizDetail.class);
                intent.putExtra(LESSON_DETAIL_EXTRA, quiz.getId());
                startActivity(intent);
            }
        });
    }
}
