package mecals.mecalsapp;

import android.content.Intent;
import android.os.SystemClock;
import android.view.View;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class HomeActivity extends AppCompatActivity implements IRequestHandler {

    Chronometer m_chronometer;
    long        m_lastPause = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        m_chronometer = (Chronometer) findViewById(R.id.chronometer1);
        ToggleButton toggleAlarm = (ToggleButton) findViewById(R.id.toggleButton1);

        m_chronometer.start();
        toggleAlarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    m_chronometer.start();
                    if (m_lastPause != 0)
                        m_chronometer.setBase(SystemClock.elapsedRealtime() + m_lastPause);
                }
                else {
                    m_lastPause = m_chronometer.getBase() - SystemClock.elapsedRealtime();
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
