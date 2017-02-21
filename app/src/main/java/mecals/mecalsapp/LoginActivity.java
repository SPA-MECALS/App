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


public class LoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ArrayAdapter<CharSequence> adapter;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        //TODO fix spinner bug which doesn't show default or choice. It shows the options you have when you click the arrow , though
        spinner = (Spinner)findViewById(R.id.spinner1);

        //Alternative versions for the adapter, to fix the spinner. They did not work
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(LoginActivity.this,
                android.R.layout.simple_spinner_item,R.id.spinner1); */

        //adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, R.array.atco_array);

        adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.atco_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        spinner.setSelection(0,true);

        Button nameBtn = (Button) findViewById(R.id.loginButton);
        nameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText nameEntry = (EditText) findViewById(R.id.insertName);
                nameEntry.setTextColor(Color.BLACK);
                String name = nameEntry.getText().toString();


                //TODO username checking , need devC Team's work
                if (name.length() > 0) {
                    Toast toastYes = Toast.makeText(getApplicationContext(), "Hi there, " + name + "!", Toast.LENGTH_SHORT);
                    toastYes.show();

                    Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(intent);

                } else {

                    Toast toastNo = Toast.makeText(getApplicationContext(), "Please insert your name !", Toast.LENGTH_SHORT);
                    toastNo.show();
                }


                EditText passwordEntry = (EditText) findViewById(R.id.insertPass);
                passwordEntry.setTextColor(Color.BLACK);
                String pass = passwordEntry.getText().toString();
                //TODO password checking , need devC Team's work


            }
        });

    }

    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)

        //here however, we just set the spinner value to the one selected
        String role = spinner.getSelectedItem().toString();
        int spinnerPosition = adapter.getPosition(role);
        spinner.setSelection(spinnerPosition);
        Toast toastNo = Toast.makeText(getApplicationContext(), "Your role is " + role, Toast.LENGTH_SHORT);
        toastNo.show();
        }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
        spinner.setSelection(0,true); //set the default value
    }


}
