package com.jedzer.misc;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.jedzer.file.FileImage;
import com.jedzer.model.Course;
import com.jedzer.model.Lesson;
import com.jedzer.model.Quiz;
import com.jedzer.model.Unit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UpdateData {

    private static void formQuiz(final Context context, String link, final Lesson lesson)
    {
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jsonObject = new JSONObject(response);
                            String selfUrl = jsonObject.getString("url");
                            String title = jsonObject.getString("title");
                            String text = jsonObject.getString("xml");

                            Quiz quiz = new Quiz(title, text, selfUrl, lesson);

                            quiz.save();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) { }
                });
        queue.add(stringRequest);
    }


    private static void formLesson(final Context context, String link, final Course course)
    {
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            final String imageLink = jsonObject.getString("image");
                            final String selfUrl = jsonObject.getString("url");
                            String title = jsonObject.getString("title");
                            String description = jsonObject.getString("description");

                            final Bitmap[] bitmap = new Bitmap[1];
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        bitmap[0] = (Bitmap) FileImage.getBitmapFromURL(imageLink);
                                        FileImage.writeBitmapToSD(selfUrl, bitmap[0]);

                                    } catch (Exception e) {

                                        e.printStackTrace();
                                        e.getMessage();
                                    }

                                }
                            }).start();

                            Lesson lesson = new Lesson(title, description, selfUrl, course);

                            JSONArray jsonArray = jsonObject.getJSONArray("quiz_set");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                String quizLink = jsonArray.getString(i);
                                formQuiz(context, quizLink, lesson);
                            }

                            lesson.save();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) { }
                });
        queue.add(stringRequest);
    }

    private static void formCourse(final Context context, String link, final Unit unit)
    {
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            final String imageLink = jsonObject.getString("image");
                            final String selfUrl = jsonObject.getString("url");
                            String title = jsonObject.getString("title");
                            String description = jsonObject.getString("description");
                            String background = jsonObject.getString("background_color");

                            final Bitmap[] bitmap = new Bitmap[1];
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        bitmap[0] = (Bitmap) FileImage.getBitmapFromURL(imageLink);
                                        FileImage.writeBitmapToSD(selfUrl, bitmap[0]);
                                    } catch (Exception e) {

                                        e.printStackTrace();
                                        e.getMessage();
                                    }

                                }
                            }).start();

                            Course course = new Course(title, description, selfUrl, background, unit);

                            JSONArray jsonArray = jsonObject.getJSONArray("lesson_set");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                String lessonLink = jsonArray.getString(i);
                                formLesson(context, lessonLink, course);
                            }

                            course.save();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) { }
                });
        queue.add(stringRequest);
    }

    public static void startUpdate(final Context context)
    {
        Unit.deleteAll(Unit.class);
        Course.deleteAll(Course.class);
        Lesson.deleteAll(Lesson.class);
        Quiz.deleteAll(Quiz.class);


        RequestQueue queue = Volley.newRequestQueue(context);
        String url ="http://192.168.1.49:8000/api/units/";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray jsonArrayUnits = new JSONArray(response);
                            for (int j = 0; j < jsonArrayUnits.length(); j++)
                            {
                                JSONObject jsonObject = jsonArrayUnits.getJSONObject(j);
                                Unit unit = new Unit(jsonObject.getString("title"));
                                JSONArray jsonArray = jsonObject.getJSONArray("course_set");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    String link = jsonArray.getString(i);
                                    formCourse(context, link, unit);
                                }
                                unit.save();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) { }
                });
        queue.add(stringRequest);
    }

}
