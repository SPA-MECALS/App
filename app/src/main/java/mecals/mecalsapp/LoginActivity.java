package mecals.mecalsapp;

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
                String name = nameEntry.getText().toString();

                int counter = 0;
                //in toate butoane ce au legatura cu tutorialul, la progressStatus am un counter.
                //indiferent de cate ori e incrementat prin folosirea widgetului, cand ia valoarea 1 e incrementat o data si progressStatus
                //asta pentru a evita sa incrementam progressStatus prin folosirea repetitiva a aceluiasi widget

                if (name.length() > 0) {
                    //tutorial explanation, not pop-up. need String.xml !
                    counter++;
                    if (counter == 1) {
                        progressStatus++;
                    }


                    Toast toast = Toast.makeText(getApplicationContext(), "Hi there, " + name + "! " + progressStatus, Toast.LENGTH_SHORT);
                    toast.show();

                } else {

                    Toast toast1 = Toast.makeText(getApplicationContext(), "Please insert name", Toast.LENGTH_SHORT);
                    toast1.show();
                }
            }
        });
    }


    public void login(View view) {


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
