package mecals.mecalsapp;

import android.content.SharedPreferences;
import android.content.Context;
import java.util.HashMap;

/**
 * Created by Théo on 07/02/2017.
 */

public class API {
    private static API m_instance = null;
    private static final String TOKEN = "token";

    private String m_url = null;

    protected API() {
        //Avoid default instantiation
    }

    public static API getInstance() {
        if (m_instance == null) {
            m_instance = new API();
        }
        return (m_instance);
    }

    public void setUrl(String url) {
        m_url = url;
    }

    public String getUrl() {
        return (m_url);
    }

    public void addToken(Context context, String token) {
        SharedPreferences.Editor edit = context.getSharedPreferences(API.TOKEN, Context.MODE_PRIVATE).edit();
        edit.putString(API.TOKEN, token);
        edit.commit();
    }

    public boolean hasToken(Context context) {
        return (context.getSharedPreferences(API.TOKEN, Context.MODE_PRIVATE).contains(API.TOKEN));
    }

    public String getToken(Context context) {
        return (context.getSharedPreferences(API.TOKEN, Context.MODE_PRIVATE).getString(API.TOKEN, null));
    }

    public void signIn(IRequestHandler handler, String login, String password) {
        HashMap<String, String> parameters = new HashMap<>();

        if (m_url == null)
            throw new NullPointerException();
        parameters.put("login", login);
        parameters.put("password", password); //TODO Encrypt password
        new PostRequest(handler, Route.SIGN_IN).execute(new HttpRequest(m_url + "/signIn", parameters));
    }

    public void login(IRequestHandler handler) {
        if (m_url == null)
            throw new NullPointerException();
        new GetRequest(handler, Route.LOGIN).execute(new HttpRequest(m_url + "/login"));
    }

    public void logout(IRequestHandler handler) {
        if (m_url == null)
            throw new NullPointerException();
        new GetRequest(handler, Route.LOGOUT).execute(new HttpRequest(m_url + "/logout"));
    }
}