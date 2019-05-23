package com.jedzer.dao.User;

import android.util.JsonReader;

import com.jedzer.dao.exception.UserAlreadyCreatedException;
import com.jedzer.dao.exception.UserNotFoundException;
import com.jedzer.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class UserDaoServer  implements IUserDao {
    //TODO change to right IP
    private static final String URLish = "http://localhost:8000";
    private static final UserDaoServer Instance = new UserDaoServer();

    private UserDaoServer() { };

    private HttpsURLConnection getConnection(String type, String field) throws IOException {
        URL serverEndpoint = new URL(URLish + '/' + type + '/' + field);
        HttpsURLConnection connection = (HttpsURLConnection) serverEndpoint.openConnection();
        connection.setRequestProperty("User-Agent", "Learnero-Android-App-v1.0");
        return connection;

    }


    public static UserDaoServer getInstance()
    {
        return Instance;
    }

    @Override
    public void save(User user) throws UserAlreadyCreatedException, IOException {

    }

    @Override
    public User getByNickname(String nickname) throws UserNotFoundException, IOException {
        String type = "users";
        User user = new User(1, "1", "1", "1", "2", false);
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
            throw new UserNotFoundException();
        }
        connection.disconnect();
        return user;
    }
}
