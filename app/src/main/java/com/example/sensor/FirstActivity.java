package com.example.sensor;
import android.util.Log;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.sensor.databinding.ActivityFirstBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.result.DataReadResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import java.util.concurrent.TimeUnit;

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
                Intent intent = new Intent(FirstActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Google Fit API 코드 추가
        readCumulativeStepCount();
    }

    private void readCumulativeStepCount()  {
        // Create a FitnessOptions with the desired data type and read access
        FitnessOptions fitnessOptions = FitnessOptions.builder()
                .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
                .build();

        // Get the Google Fit account
        GoogleSignInAccount account = GoogleSignIn.getAccountForExtension(this, fitnessOptions);

        // Define the time range for data reading (past 7 days)
        long endTimeMillis = System.currentTimeMillis();  // Current time
        long startTimeMillis = endTimeMillis - TimeUnit.DAYS.toMillis(7);  // 7 days ago

        Fitness.getHistoryClient(this, account)
                .readData(new DataReadRequest.Builder()
                        .read(DataType.TYPE_STEP_COUNT_DELTA)
                        .setTimeRange(startTimeMillis, endTimeMillis, TimeUnit.MILLISECONDS)
                        .build())
                .addOnCompleteListener(new OnCompleteListener<DataReadResponse>() {
                    @Override
                    public void onComplete(@NonNull Task<DataReadResponse> task) {
                        if (task.isSuccessful()) {
                            DataReadResponse readDataResponse = task.getResult();
                            DataSet dataSet = readDataResponse.getDataSet(DataType.TYPE_STEP_COUNT_DELTA);
                            if (dataSet != null && !dataSet.isEmpty()) {
                                // 데이터가 있는 경우 처리
                            } else {
                                // 데이터가 없는 경우 처리
                            }
                        } else {
                            // 작업이 실패한 경우 처리
                            Exception exception = task.getException();
                            if (exception != null) {
                                Log.e("TAG", "Error: " + exception.getMessage());
                            }
                        }
                    }
                });

    }
}