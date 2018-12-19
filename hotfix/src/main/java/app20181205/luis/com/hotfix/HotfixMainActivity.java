package app20181205.luis.com.hotfix;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.PermissionRequest;
import android.widget.Toast;

import com.example.liuzhi20181026.R;

public class HotfixMainActivity extends AppCompatActivity {
    private static final String TAG = "HotfixMainActivity";
    private static final int REQUEST_CODE_PERMISSION = 1001;
    private AndFixManager andFixManager;
    private String fixDexFilePath = "/sdcard/fix.dex";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        andFixManager = new AndFixManager(this.getApplicationContext());

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);
    }

    public void fixBug(View view) {
        Log.e(TAG, "fixBug");

        andFixManager = new AndFixManager(this.getApplicationContext());
        andFixManager.fix(fixDexFilePath);
    }


    public void runTest(View view) {

        Toast.makeText(this, new TestBug().getRetString(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean isGranted = false;
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.length > 0) {
                for (int result : grantResults) {
                    isGranted = true;
                    if (result == PackageManager.PERMISSION_GRANTED) {

                    } else {
                        isGranted = false;
                    }
                }
            }
        }

        if (!isGranted) {
            Toast.makeText(this, "没有申请到权限", Toast.LENGTH_SHORT).show();
        }
    }
}
