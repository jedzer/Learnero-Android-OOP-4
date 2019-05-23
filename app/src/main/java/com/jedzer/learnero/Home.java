package com.jedzer.learnero;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.jedzer.adapters.CourseListAdapter;
import com.jedzer.model.Course;


public class Home extends AppCompatActivity {

    ListView availableCoursesListView;
    BottomNavigationView bottomNavigationView;

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

        Course[] courses = {
                new Course(12, "lol", "descr", -1, null),
                new Course(12, "lol1", "descr", -1, null),
                new Course(12, "lol2", "descr", -1, null),
                new Course(12, "lol3", "descr", -1, null),
                new Course(12, "lol4", "descr", -1, null)
        };

        CourseListAdapter adapter = new CourseListAdapter(this, courses);
        availableCoursesListView.setAdapter(adapter);

    }
}
