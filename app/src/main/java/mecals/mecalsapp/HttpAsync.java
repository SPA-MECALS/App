package mecals.mecalsapp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Théo on 07/02/2017.
 */

public class HttpAsync extends AsyncTask<HttpRequest, Void, HttpResponse> {

    protected IRequestHandler m_handler;
    private int m_identifier;
    private String m_type;

    public HttpAsync(IRequestHandler handler, int identifier, String type) {
        m_handler = handler;
        m_identifier = identifier;
        m_type = type;
    }

    protected HttpURLConnection createConnection(String url) throws IOException {
        HttpURLConnection httpConnection = (HttpURLConnection) new URL(url).openConnection();
        httpConnection.setRequestMethod(m_type);
        return (httpConnection);
    }

    protected HttpResponse createResponse(HttpURLConnection httpConnection) throws IOException {
        String line;
        String content = "";

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(), "UTF-8"));
            while ((line = reader.readLine()) != null)
                content += line;
        }
        catch (Exception e) {
            content = "";
        }
        return (new HttpResponse(httpConnection.getResponseCode(), content));
    }

    @Override
    protected HttpResponse doInBackground(HttpRequest... requests) {
        HttpRequest request = requests[0];

        try {
            HttpURLConnection httpConnection = this.createConnection(request.getUrl() + "?" + request.encodedParameters());
            HttpResponse r = this.createResponse(httpConnection);
            return (r);
        }
        catch (Exception e) {
            Log.e("HttpAsync", e.getMessage(), e.getCause());
            return (null);
        }
    }

    @Override
    protected void onPostExecute(HttpResponse response) {
        m_handler.onRequest(response, m_identifier);
    }
}