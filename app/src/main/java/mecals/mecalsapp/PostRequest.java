package mecals.mecalsapp;

import android.app.Activity;
import android.util.Log;

import java.io.OutputStream;
import java.net.HttpURLConnection;

/**
 * Created by Théo on 07/02/2017.
 */

public class PostRequest extends HttpAsync {

    public PostRequest(Activity activity) {
       super(activity, "POST");
    }

    @Override
    protected HttpResponse doInBackground(HttpRequest... requests) {
        HttpRequest request = requests[0];

        try {
            HttpURLConnection httpConnection = this.createConnection(request.getUrl());
            httpConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            OutputStream output = httpConnection.getOutputStream();
            output.write(request.encodedParameters().getBytes());
            return (this.createResponse(httpConnection));
        }
        catch (Exception e){
            Log.e("PostRequest", e.getMessage(), e.getCause());
            return (null);
        }
    }
}
