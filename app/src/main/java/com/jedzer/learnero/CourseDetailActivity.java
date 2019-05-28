package com.jedzer.learnero;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.jedzer.adapters.LessonListAdapter;
import com.jedzer.model.Course;
import com.jedzer.model.Lesson;

import java.util.List;

public class CourseDetailActivity extends AppCompatActivity {

    public static final String LESSON_EXTRA = "LESSON_EXTRA";

    TextView courseDetailTitleTextView;
    TextView courseDetailDescriptionTextView;
    ListView courseDetailLessonListView;


    private void setData(Course course)
    {
        courseDetailTitleTextView.setText(course.getTitle());
        courseDetailDescriptionTextView.setText(course.getDescription());
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        courseDetailTitleTextView = findViewById(R.id.courseDetailTitleTextView);
        courseDetailDescriptionTextView = findViewById(R.id.courseDetailDescriptionTextView);
        courseDetailLessonListView = findViewById(R.id.lessonDetailQuizListView);

        long courseId = getIntent().getLongExtra(Home.COURSE_DETAIL_EXTRA, -1);
        List<Lesson> lessons = Lesson.find(Lesson.class, "course = ?", Long.toString(courseId));

        Course course = Course.find(Course.class, "id = ?", Long.toString(courseId)).get(0);

        setData(course);

        LessonListAdapter adapter = new LessonListAdapter(this, lessons);
        courseDetailLessonListView.setAdapter(adapter);

        courseDetailLessonListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Lesson quiz = (Lesson) courseDetailLessonListView.getItemAtPosition(position);
                Intent intent = new Intent(CourseDetailActivity.this, LessonDetailActivity.class);
                intent.putExtra(LESSON_EXTRA, quiz.getId());
                startActivity(intent);
            }
        });
    }
}
