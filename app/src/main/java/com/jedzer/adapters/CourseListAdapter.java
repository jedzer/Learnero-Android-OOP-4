package com.jedzer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jedzer.learnero.R;
import com.jedzer.model.Course;

public class CourseListAdapter extends BaseAdapter {

    Course[] courses;
    LayoutInflater mInflater;

    public CourseListAdapter(Context context, Course[] courses) {
        this.courses = courses;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return courses.length;
    }

    @Override
    public Object getItem(int position) {
        return courses[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.course_listview_item_layout, null);
        TextView title = v.findViewById(R.id.courseTitleLVTextView);
        TextView description = v.findViewById(R.id.courseDescriptionLVTextView);
        ImageView image = v.findViewById(R.id.courseImageLVImageView);

        String t = courses[position].getTitle();
        String d = courses[position].getDescription();
        int i = courses[position].getImageId();

        title.setText(t);
        description.setText(d);
        if (i != -1)
            image.setImageResource(i);

        return v;
    }
}
