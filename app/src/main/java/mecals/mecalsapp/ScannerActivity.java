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

//for the QR Code scanner
import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

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
        setContentView(m_scannerView);
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
        Log.v("handleResult", result.getText());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setMessage(result.getText());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);

        //resume scanning
        m_scannerView.resumeCameraPreview(this);
    }
}

