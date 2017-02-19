package mecals.mecalsapp;

import java.util.HashMap;

/**
 * Created by Théo on 07/02/2017.
 */

public class API {
    private static API m_instance = null;

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

    public void login(IRequestHandler handler, String login, String password) {
        HashMap<String, String> parameters = new HashMap<>();

        if (m_url == null)
            throw new NullPointerException();
        parameters.put("login", login);
        parameters.put("password", password); //TODO Encrypt password
        new GetRequest(handler).execute(new HttpRequest(m_url + "/login", parameters));
    }

    public void logout(IRequestHandler handler) {
        if (m_url == null)
            throw new NullPointerException();
        //TODO
    }

    public void changeRole(IRequestHandler handler) {
        if (m_url == null)
            throw new NullPointerException();
        //TODO
    }
}