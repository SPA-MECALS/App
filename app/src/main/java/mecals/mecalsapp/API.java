package mecals.mecalsapp;

/**
 * Created by Théo on 07/02/2017.
 */

public class API {
    private String m_url;

    public API(String url) {
        m_url = url;
    }

    public void login() {
        //new LoginRequest().execute(new HttpRequest(m_url + "/login"));
    }
}