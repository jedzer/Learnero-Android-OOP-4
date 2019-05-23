package com.jedzer.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jedzer.file.FileImage;
import com.jedzer.learnero.R;
import com.jedzer.misc.UpdateData;
import com.jedzer.model.Course;

import java.util.List;

public class CourseListAdapter extends BaseAdapter {

    List<Course> courses;
    LayoutInflater mInflater;

    public CourseListAdapter(Context context, List<Course> courses) {
        this.courses = courses;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return courses.size();
    }

    @Override
    public Object getItem(int position) {
        return courses.get(position);
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
        ConstraintLayout layout = v.findViewById(R.id.courseLVItemRelativeLayout);

        String t = courses.get(position).getTitle();
        String d = courses.get(position).getDescription();
        String imageName = courses.get(position).getImageName();
        String color = courses.get(position).getBackGroundColor();


        Bitmap bitmap = FileImage.loadImageFromSD(imageName);

        layout.setBackgroundColor(Color.parseColor(color));
        title.setText(t);
        description.setText(d);
        image.setImageBitmap(bitmap);

        return v;
    }
}
