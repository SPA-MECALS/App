package mecals.mecalsapp;

import android.content.Intent;
import android.os.SystemClock;
import android.view.View;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements IRequestHandler {

    Chronometer m_chronometer;
    Spinner spinner;
    long m_lastPause = 0;


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

        spinner = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.atco_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void onLogout(View view) {
        API.getInstance().logout(this, "be7cf567591", "dd4850b04e3", "d53524a0330");
    }

    public void onBreak(View view){
        //TODO: edit it when server route is created!
    }

    public void onRoleChange(View view) {
        String role = spinner.getSelectedItem().toString();
        API.getInstance().changeRole(HomeActivity.this, role);
    }

    public void onRequest(HttpResponse response, int identifier) {
        if (response == null) {
            Toast.makeText(this.getApplicationContext(), Constants.ERR_SERVER_UNREACHABLE, Toast.LENGTH_SHORT).show();
            return;
        }
        if (identifier == Route.LOGOUT && response.getStatus() == 200) {
            Intent intent = new Intent(this.getApplicationContext(), ScannerActivity.class);
            this.startActivity(intent);
        } else if (identifier == Route.CHANGE_ROLE && response.getStatus() == 200) {
            Toast.makeText(this.getApplicationContext(), "Role has been changed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this.getApplicationContext(), Constants.ERR_UNEXPECTED_RESPONSE, Toast.LENGTH_SHORT).show();
        }
    }
}
