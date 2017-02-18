package mecals.mecalsapp;

import android.app.Activity;
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

    protected Activity m_activity;
    private String m_type;

    public HttpAsync(Activity activity, String type) {
        m_activity = activity;
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
            content = null;
        }
        return (new HttpResponse(httpConnection.getResponseCode(), content));
    }

    @Override
    protected HttpResponse doInBackground(HttpRequest... requests) {
        HttpRequest request = requests[0];

        try {
            HttpURLConnection httpConnection = this.createConnection(request.getUrl() + "?" + request.encodedParameters());
            return (this.createResponse(httpConnection));
        }
        catch (Exception e) {
            Log.e("HttpAsync", e.getMessage(), e.getCause());
            return (null);
        }
    }
}