package com.jedzer.learnero;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jedzer.adapters.CourseListAdapter;
import com.jedzer.model.Course;
import com.jedzer.model.Unit;

import java.util.List;


public class Home extends AppCompatActivity {

    ListView availableCoursesListView;
    BottomNavigationView bottomNavigationView;
    public static final String COURSE_EXTRA = "COURSE_EXTRA";
    public static final String COURSE_DETAIL_EXTRA = "COURSE_DETAIL_EXTRA";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Intent intent;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    intent = new Intent(Home.this, Home.class);
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.navigation_dashboard:
                    intent = new Intent(Home.this, MainActivity.class);
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.navigation_profile:
                    intent = new Intent(Home.this, SettingsActivity.class);
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    return true;
            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        availableCoursesListView = findViewById(R.id.availableCoursesListView);
        bottomNavigationView = findViewById(R.id.nav_view);

        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        List<Course> courses = Course.listAll(Course.class);

        CourseListAdapter adapter = new CourseListAdapter(this, courses);
        availableCoursesListView.setAdapter(adapter);

        availableCoursesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Course course = (Course) availableCoursesListView.getItemAtPosition(position);
                Intent intent = new Intent(Home.this, CourseDetailActivity.class);
                intent.putExtra(COURSE_DETAIL_EXTRA, course.getId());
                startActivity(intent);
            }
        });

    }
}
