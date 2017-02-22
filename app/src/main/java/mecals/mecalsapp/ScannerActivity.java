package mecals.mecalsapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.google.zxing.Result;
import java.net.URI;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler, IRequestHandler {

    private ZXingScannerView m_scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_scanner);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        }
    }

    public void onClick(View v) {
        m_scannerView = new ZXingScannerView(this);
        this.setContentView(m_scannerView);
        m_scannerView.setResultHandler(this);
        m_scannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        m_scannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        String url = result.getText();

        try {
            URI.create(url);
        }
        catch (IllegalArgumentException e) {
            Toast.makeText(this.getApplicationContext(), Constants.ERR_NO_URL, Toast.LENGTH_SHORT).show();
            m_scannerView.resumeCameraPreview(this);
            return;
        }
        API.getInstance().setUrl(url);
        if (API.getInstance().hasToken(this.getApplicationContext())) {
            API.getInstance().login(this);
        }
        else {
            Intent intent = new Intent(this.getApplicationContext(), LoginActivity.class);
            this.startActivity(intent);
        }
        m_scannerView.resumeCameraPreview(this);
    }

    public void onRequest(HttpResponse response, int identifier) {
        if (response == null) {
            Toast.makeText(this.getApplicationContext(), Constants.ERR_SERVER_UNREACHABLE, Toast.LENGTH_SHORT).show();
            return;
        }
        if (response.getStatus() == 200) {
            Intent intent = new Intent(this.getApplicationContext(), HomeActivity.class);
            this.startActivity(intent);
        }
        else {
            Toast.makeText(this.getApplicationContext(), Constants.ERR_UNEXPECTED_RESPONSE, Toast.LENGTH_SHORT).show();
        }
    }
}

