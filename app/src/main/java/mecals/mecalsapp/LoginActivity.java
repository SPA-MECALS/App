package mecals.mecalsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, IRequestHandler {

    private Spinner m_spinner;
    private ArrayAdapter<CharSequence> m_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        Button nameBtn = (Button) findViewById(R.id.loginButton);
        m_spinner = (Spinner) findViewById(R.id.spinner1);
        m_adapter = ArrayAdapter.createFromResource(getApplicationContext(), R.array.atco_array, android.R.layout.simple_spinner_item);

        m_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        m_spinner.setAdapter(m_adapter);
        m_spinner.setOnItemSelectedListener(this);
        m_spinner.setSelection(0, true);
        nameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = ((EditText) findViewById(R.id.insertName)).getText().toString();
                String password = ((EditText) findViewById(R.id.insertPass)).getText().toString();

                if (login.length() > 0 && password.length() > 0) {
                    API.getInstance().login(LoginActivity.this, login, password);
                }
                else {
                    Toast.makeText(getApplicationContext(), Constants.ERR_NO_CREDENTIALS, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String role = m_spinner.getSelectedItem().toString();

        m_spinner.setSelection(m_adapter.getPosition(role));
        Toast.makeText(this.getApplicationContext(), "Your role is " + role, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        m_spinner.setSelection(0, true);
    }

    public void onRequest(HttpResponse response, int identifier) {
        if (response == null) {
            Toast.makeText(this.getApplicationContext(), Constants.ERR_SERVER_UNREACHABLE, Toast.LENGTH_SHORT).show();
            return;
        }
        switch (response.getStatus()) {
            case 200:
                Intent intent = new Intent(this.getApplicationContext(), HomeActivity.class);
                this.startActivity(intent);
                break;
            case 401:
                Toast.makeText(this.getApplicationContext(), Constants.ERR_INVALID_CREDENTIALS, Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this.getApplicationContext(), Constants.ERR_UNEXPECTED_RESPONSE, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
