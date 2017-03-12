package mecals.mecalsapp;

import android.util.Log;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.net.URLEncoder;

/**
 * Created by Théo on 07/02/2017.
 */

public class HttpRequest {
    private String m_url;
    private HashMap<String, String> m_parameters;
    private static final String ENCODING = "UTF-8";

    public HttpRequest(String url) {
        m_url = url;
        m_parameters = null;
    }

    public HttpRequest(String url, HashMap<String, String> parameters) {
        m_url = url;
        m_parameters = parameters;
    }

    public String getUrl() {
        return (m_url);
    }

    public HashMap<String, String> getParameters() {
        return (m_parameters);
    }

    public String encodedParameters() {
        String encoded = "";

        if (m_parameters == null)
            return (encoded);
        try  {
            for (String key : m_parameters.keySet()) {
                encoded += encoded.equals("") ? "" : "&";
                encoded += URLEncoder.encode(key, ENCODING) + "=" + URLEncoder.encode(m_parameters.get(key), ENCODING);
            }
        }
        catch (UnsupportedEncodingException e) {
            Log.e("HttpRequest", e.getMessage(), e.getCause());
        }
        return (encoded);
    }

    public JSONObject jsonParameters() {
        return new JSONObject(m_parameters);
    }
}
