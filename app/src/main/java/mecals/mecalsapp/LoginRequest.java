package mecals.mecalsapp;

import android.app.Activity;
import android.widget.TextView;

/**
 * Created by Théo on 07/02/2017.
 */

public class LoginRequest extends GetRequest {

    public LoginRequest(Activity context) {
        super(context);
    }

    @Override
    protected void onPostExecute(HttpResponse response) {
        if (response == null) {
            //TODO Handle connection error !
            return;
        }
        TextView txt = (TextView) m_context.findViewById(R.id.label_test);
        txt.setText("Response " + response.getStatus() + "\n" + response.getContent());
    }
}
