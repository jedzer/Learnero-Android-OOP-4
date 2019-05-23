package com.jedzer.dao.Quiz;

import android.util.JsonReader;

import com.jedzer.dao.exception.QuizAlreadyCreatedException;
import com.jedzer.dao.exception.QuizNotFoundException;
import com.jedzer.model.Quiz;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

public class QuizDaoServer implements IQuizDao {
    //TODO change to right IP
    private static final String URLish = "http://localhost:8000";
    private static final QuizDaoServer Instance = new QuizDaoServer();

    private QuizDaoServer() { };

    private HttpsURLConnection getConnection(String type, String field) throws IOException {
        URL serverEndpoint = new URL(URLish + '/' + type + '/' + field);
        HttpsURLConnection connection = (HttpsURLConnection) serverEndpoint.openConnection();
        connection.setRequestProperty("User-Agent", "Learnero-Android-App-v1.0");
        return connection;

    }


    public static QuizDaoServer getInstance()
    {
        return Instance;
    }

    @Override
    public void save(Quiz user) throws QuizAlreadyCreatedException, IOException {

    }

    @Override
    public Quiz getById(long id) throws QuizNotFoundException, IOException {
        String type = "users";
        Quiz quiz = new Quiz(1, "1", "1", "1", "2", false);
        HttpsURLConnection connection = getConnection(type, nickname);
        int responseCode = connection.getResponseCode();
        if (responseCode == 200)
        {
            InputStream responseBody = connection.getInputStream();
            InputStreamReader responseBodyReader = new InputStreamReader(responseBody, StandardCharsets.UTF_8);
            JsonReader jsonReader = new JsonReader(responseBodyReader);

            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String key = jsonReader.nextName();
                if (key.equals("organization_url")) {
                    String value = jsonReader.nextString();
//                    user = new User(id, nickname, email, password, registrationDate);
                    break;
                } else {
                    jsonReader.skipValue();
                }
            }
            jsonReader.close();
        }
        else if (responseCode == 404)
        {
            connection.disconnect();
            throw new QuizNotFoundException();
        }
        connection.disconnect();
        return quiz;
    }
}
