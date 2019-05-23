package com.jedzer.learnero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.jedzer.adapters.LessonListAdapter;
import com.jedzer.model.Course;
import com.jedzer.model.Lesson;

import java.util.List;

public class CourseDetailActivity extends AppCompatActivity {

    TextView courseTitleLVTextView;
    TextView courseDescriptionLVTextView;
    ListView courseDetailLessonListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        courseTitleLVTextView = findViewById(R.id.courseTitleLVTextView);
        courseDescriptionLVTextView = findViewById(R.id.courseDescriptionLVTextView);
        courseDetailLessonListView = findViewById(R.id.courseDetailLessonListView);

        long courseId = getIntent().getLongExtra(Home.COURSE_EXTRA, -1);
        List<Lesson> lessons = Lesson.find(Lesson.class, "course = ?", Long.toString(courseId));
        LessonListAdapter adapter = new LessonListAdapter(this, lessons);
        courseDetailLessonListView.setAdapter(adapter);
    }
}
