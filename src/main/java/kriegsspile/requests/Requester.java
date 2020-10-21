package kriegsspile.requests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kriegsspile.dto.GameInformation;
import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class Requester {
    public static GameInformation addPlayer(String name) throws IOException {
        GameInformation gameInformation = new GameInformation();
        Content getResultForm = Request.Get("http://localhost:8081/addPlayer?name="+name)
                .execute().returnContent();
        if (getResultForm == null){
            gameInformation.messageResponse = "Нед подключения к серверу";
            return gameInformation;
        }
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        gameInformation = gson.fromJson(getResultForm.asString(), GameInformation.class);
        return gameInformation;
    }

    public static GameInformation startGame() throws IOException {
        Content getResultForm = Request.Get("http://localhost:8081/startGame")
                .execute().returnContent();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(getResultForm.asString(), GameInformation.class);
    }

    public static GameInformation getVision(String name) throws IOException {
        Content getResultForm = Request.Get("http://localhost:8081/getVision?name="+name)
                .execute().returnContent();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(getResultForm.asString(), GameInformation.class);
    }

    public static GameInformation move(String name, int x1, int y1, int x2, int y2) throws IOException {
        final Collection<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("x1", ""+x1));
        params.add(new BasicNameValuePair("y1", ""+y1));
        params.add(new BasicNameValuePair("x2", ""+x2));
        params.add(new BasicNameValuePair("y2", ""+y2));
        Content getResultForm = Request.Get("http://localhost:8081/move")
                .bodyForm(params, Charset.defaultCharset())
                .execute().returnContent();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(getResultForm.asString(), GameInformation.class);
    }

    public static Set getPlayers() throws IOException {
        Content getResultForm = Request.Get("http://localhost:8081/getPlayers")
                .execute().returnContent();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(getResultForm.asString(), Set.class);
    }
}
