package com.example.sensor;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;



import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.sensor.databinding.ActivityMainBinding;

import java.security.Permission;
public class MainActivity extends Activity {

    private static final int MY_PERMISSIONS_REQUEST_ACTIVITY_RECOGNITION = 1;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAndRequestPermission(); // 버튼을 누를 때 권한 확인 및 요청
            }
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAndRequestPermission(); // 버튼을 누를 때 권한 확인 및 요청
            }
        });

        // ... button2와 button3의 클릭 리스너 등을 설정
    }

    private void checkAndRequestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION)
                != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.ACTIVITY_RECOGNITION,
                            Manifest.permission.ACCESS_FINE_LOCATION
                    },
                    MY_PERMISSIONS_REQUEST_ACTIVITY_RECOGNITION);
        } else {
            // 권한이 이미 부여된 경우 MainActivity를 보여줌
            Intent intent = new Intent(MainActivity.this, FirstActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_ACTIVITY_RECOGNITION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults.length > 1 && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                // 권한이 부여되었을 때 MainActivity를 보여줌
                Intent intent = new Intent(MainActivity.this, FirstActivity.class);
                startActivity(intent);
            } else {
                // 권한이 거부되었을 때 처리
                Toast.makeText(this, "권한이 거부되었습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}