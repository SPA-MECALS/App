package mecals.mecalsapp;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Théo on 07/02/2017.
 */

public class LoginRequest extends GetRequest {

    public LoginRequest(Activity activity) {
        super(activity);
    }

    @Override
    protected void onPostExecute(HttpResponse response) {
        if (response == null) {
            Toast.makeText(m_activity.getApplicationContext(), Constants.ERR_SERVER_UNREACHABLE, Toast.LENGTH_SHORT).show();
            return;
        }
        switch (response.getStatus()) {
            case 200:
                Intent intent = new Intent(m_activity.getApplicationContext(), HomeActivity.class);
                m_activity.startActivity(intent);
                break;
            case 401:
                Toast.makeText(m_activity.getApplicationContext(), Constants.ERR_INVALID_CREDENTIALS, Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(m_activity.getApplicationContext(), Constants.ERR_UNEXPECTED_RESPONSE, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
