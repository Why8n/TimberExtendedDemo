package com.yn.timberextendeddemo;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_WRITE_EXTERNAL_STORAGE_REQUEST_CODE
                && permissions != null && permissions.length > 0) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "since you deny write_external_storage permission," +
                        "that log file will not be able to perform normally", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onConstTag(View view) {
        if(!checkPermission())
            return;
        Timber.v("watch the tag");
        Timber.v("watch the tag haha");
        Timber.v("watch the tag hehe");
    }

    public void onMultiTag(View view) {
        if(!checkPermission())
            return;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Timber.i("watch the tag in thread");
            }
        }).start();
    }

    public void onRelease(View view) {
        if(!checkPermission())
            return;
        Timber.v("release log test: Timber.v");
        Timber.i("release log test: Timber.i");
        Timber.d("release log test: Timber.d");
        Timber.w("release log test: Timber.w");
        Timber.e("release log test: Timber.e");
        Timber.wtf("release log test: Timber.wtf");
    }


    private boolean checkPermission(){
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                //show a dialog to explain why we need this permission
                new AlertDialog.Builder(this)
                        .setTitle("permission")
                        .setMessage("Log file needs this permission ^-^")
                        .setNegativeButton("deny", null)
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(
                                        MainActivity.this,
                                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        PERMISSION_WRITE_EXTERNAL_STORAGE_REQUEST_CODE
                                );
                            }
                        }).show();
            } else {
                ActivityCompat.requestPermissions(
                        MainActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSION_WRITE_EXTERNAL_STORAGE_REQUEST_CODE
                );
            }
        }else{
            return true;
        }
        return false;
    }
}
