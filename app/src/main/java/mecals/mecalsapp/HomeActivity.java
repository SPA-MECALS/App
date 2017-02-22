package mecals.mecalsapp;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class HomeActivity extends AppCompatActivity implements IRequestHandler {

    private Chronometer m_chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        m_chronometer = (Chronometer) findViewById(R.id.chronometer1);
        ToggleButton toggleAlarm = (ToggleButton) findViewById(R.id.toggleButton1);

        toggleAlarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //TODO fix the chronometer. It crashes the app if we hit the Break Toggle Button for now
                if (isChecked) {
                    m_chronometer.start();
                }
                else {
                    m_chronometer.stop();
                }
            }
        });
    }

    public void onLogout(View view) {
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
