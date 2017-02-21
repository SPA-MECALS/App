package mecals.mecalsapp;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements IRequestHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
    }

    public void onSignOut(View view) {
        API.getInstance().logout(this);
    }

    public void onBreak(View view) {
        //TODO handle break/resume here
    }

    public void onRequest(HttpResponse response, int identifier) {
        if (response == null) {
            Toast.makeText(this.getApplicationContext(), Constants.ERR_SERVER_UNREACHABLE, Toast.LENGTH_SHORT).show();
            return;
        }
        if (identifier == Route.LOGOUT && response.getStatus() == 200) {
            Intent intent = new Intent(this.getApplicationContext(), ScannerActivity.class);
            this.startActivity(intent);
        }
    }
}
