package mecals.mecalsapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

                EditText nameEntry = (EditText) findViewById(R.id.insertName);
                nameEntry.setTextColor(Color.BLACK);
                String name = nameEntry.getText().toString();


                //TODO username checking, username not showing , need devC Team's work
                if (name.length() > 0) {
                    Toast toastYes = Toast.makeText(getApplicationContext(), "Hi there, " + name + "!", Toast.LENGTH_SHORT);
                    toastYes.show();

                    Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(intent);

                } else {

                    Toast toastNo = Toast.makeText(getApplicationContext(), "Please insert name !", Toast.LENGTH_SHORT);
                    toastNo.show();
                }


                EditText passwordEntry = (EditText) findViewById(R.id.insertPass);
                passwordEntry.setTextColor(Color.BLACK);
                String pass = passwordEntry.getText().toString();
                //TODO password checking , need devC Team's work


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
