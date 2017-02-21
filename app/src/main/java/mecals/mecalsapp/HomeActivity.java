package mecals.mecalsapp;

import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class HomeActivity extends AppCompatActivity {

    Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        chronometer = (Chronometer) findViewById(R.id.chronometer1);

        ToggleButton toggleAlarm = (ToggleButton) findViewById(R.id.toggleButton1);

        toggleAlarm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                //TODO fix the chronometer. Itcrashes the app if we hit the Break Toggle Button for now
                if(isChecked)
                {
                    chronometer.start();
                }
                else
                {
                    chronometer.stop();
                }
            }
        });
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }*/
    //TODO get timer working, as well as the signout and logout buttons. Need devC's part for this

}
