package mecals.mecalsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements IRequestHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        Button nameBtn = (Button) findViewById(R.id.loginButton);

        findViewById(R.id.insertName).requestFocus();
        nameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = ((EditText) findViewById(R.id.insertName)).getText().toString();
                String password = ((EditText) findViewById(R.id.insertPass)).getText().toString();

                if (login.length() > 0 && password.length() > 0) {
                    MecalsController.getInstance().login(LoginActivity.this, login, password);
                }
                else {
                    Toast.makeText(getApplicationContext(), Constants.ERR_NO_CREDENTIALS, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onRequest(HttpResponse response, int identifier) {
        if (response == null) {
            Toast.makeText(this.getApplicationContext(), Constants.ERR_SERVER_UNREACHABLE, Toast.LENGTH_SHORT).show();
            return;
        }
        switch (response.getStatus()) {
            case 200:
                //TODO Save token rather than entiere content
                MecalsController.getInstance().addToken(this.getApplicationContext(), response.getContent());
                try {
                    JSONObject resp = new JSONObject(response.getContent().replace("[", "").replace("]", ""));
                    User.getInstance().setUserData(resp);
                } catch (Exception e) {
                    Toast.makeText(this.getApplicationContext(), "Parsing faillure", Toast.LENGTH_SHORT).show();
                    break;
                }
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
