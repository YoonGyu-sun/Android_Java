package com.example.sensor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.sensor.databinding.ActivityMainBinding;

import java.security.Permission;

public class MainActivity extends Activity {

    private ActivityMainBinding binding; // 바인딩 객체 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater()); // 바인딩 초기화
        setContentView(binding.getRoot()); // 바인딩한 뷰를 액티비티 콘텐츠 뷰로 설정

        // 바인딩 객체를 통해 XML에 정의된 뷰 요소에 접근
        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(MainActivity.this, FirstActivity.class);
                startActivity(intent);

                int valueFromJava = 12345; // 예시로 설정한 값
                intent.putExtra("value", valueFromJava); // 값을 인텐트에 추가
                startActivity(intent);

            }
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(MainActivity.this, FirstActivity.class);
                startActivity(intent);
            }
        });

        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "버튼을 눌렀어요", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
