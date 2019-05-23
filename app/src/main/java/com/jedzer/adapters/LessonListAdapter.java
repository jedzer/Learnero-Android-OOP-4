package com.jedzer.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jedzer.file.FileImage;
import com.jedzer.learnero.R;
import com.jedzer.model.Lesson;

import java.util.List;

public class LessonListAdapter extends BaseAdapter {

    List<Lesson> lessons;
    LayoutInflater mInflater;

    public LessonListAdapter(Context context, List<Lesson> lessons) {
        this.lessons = lessons;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return lessons.size();
    }

    @Override
    public Object getItem(int position) {
        return lessons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.lesson_listview_item_layout, null);
        TextView title = v.findViewById(R.id.lessonTitleLVTextView);
        TextView description = v.findViewById(R.id.lessonDescriptionLVTextView);
        ImageView image = v.findViewById(R.id.lessonImageLVImageView);

        String t = lessons.get(position).getTitle();
        String d = lessons.get(position).getDescription();
        String imageName = lessons.get(position).getImageName();

        Bitmap bitmap = FileImage.loadImageFromSD(imageName);

        title.setText(t);
        description.setText(d);
        image.setImageBitmap(bitmap);

        return v;
    }
}
