package com.jedzer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jedzer.learnero.R;
import com.jedzer.model.Quiz;

import java.util.List;

public class QuizListAdapter extends BaseAdapter {

    List<Quiz> quizzes;
    LayoutInflater mInflater;

    public QuizListAdapter(Context context, List<Quiz> quizzes) {
        this.quizzes = quizzes;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return quizzes.size();
    }

    @Override
    public Object getItem(int position) {
        return quizzes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.quiz_listview_item_layout, null);
        TextView title = v.findViewById(R.id.quizTitleLVTextView);

        String t = quizzes.get(position).getTitle();

        title.setText(t);

        return v;
    }
}
