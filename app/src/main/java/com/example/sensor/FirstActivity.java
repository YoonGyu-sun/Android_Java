package com.example.sensor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.sensor.databinding.ActivityFirstBinding;

public class FirstActivity extends Activity {

    private ActivityFirstBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFirstBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Intent에서 값 받아오기
        int valueFromIntent = getIntent().getIntExtra("value", 0); // 기본값은 0
        TextView valueTextView = binding.valueTextView; // XML의 TextView에 대한 바인딩
        valueTextView.setText("Value: " + valueFromIntent);


        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(FirstActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
