package mecals.mecalsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        Button nameBtn = (Button) findViewById(R.id.loginButton);
        nameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = ((EditText) findViewById(R.id.insertName)).getText().toString();
                String password = ((EditText) findViewById(R.id.insertPass)).getText().toString();

                if (login.length() > 0 && password.length() > 0) {
                    API.getInstance().login(LoginActivity.this, login, password);
                    //TODO Start spinner here
                }
                else {
                    Toast.makeText(getApplicationContext(), Constants.ERR_NO_CREDENTIALS, Toast.LENGTH_SHORT).show();
                }

                /*
                TODO fix spinner
                Spinner spinner = (Spinner) findViewById(R.id.spinner);
                // Create an ArrayAdapter using the string array and a default spinner layout
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                        R.array.atco_array, android.R.layout.simple_spinner_item);
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // Apply the adapter to the spinner
                spinner.setAdapter(adapter);

                public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {


                    public void onItemSelected(AdapterView<?> parent, View view,
                                               int pos, long id) {
                        // An item was selected. You can retrieve the selected item using
                        // parent.getItemAtPosition(pos)
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        // Another interface callback
                    }
                }
                */

            }
        });
    }


}
