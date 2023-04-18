package com.example.cal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class FirstScreen extends Activity {

    private TextView amountTv;
    private EditText amountInput;
    private TextView timeTv;
    private EditText timeInput;
    private Button submit;
    List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_screen);
        amountTv = findViewById(R.id.amount);
        amountInput = findViewById(R.id.amountInput);
        timeTv = findViewById(R.id.time);
        timeInput = findViewById(R.id.timeInput);
        submit = findViewById(R.id.submit);

        initListener();
    }

    private void initListener() {
        submit.setOnClickListener(view -> {
            String amount = amountInput.getText().toString();
            String time = timeInput.getText().toString();
            if (amount.isEmpty() || time.isEmpty()) {
                return;
            }
            double amountDouble = getAmountDouble(amountInput.getText());
            long timeLong = getTimeLong(timeInput.getText());
            double result = amountDouble * timeLong;
            list.add(result + "");
            startSecondScreen();
        });

        amountInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                amountTv.setText("Amount: " + getFormattedNumber(getAmountDouble(editable)));
            }
        });

        timeInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                timeTv.setText("Time: " + getFormattedTime(getTimeLong(editable)));
            }
        });
    }

    private void startSecondScreen() {
        Intent intent = new Intent(this, SecondScreen.class);
        intent.putExtra("list", (ArrayList<String>) list);
        startActivity(intent);
    }

    private double getAmountDouble(Editable editable) {
        String amount = editable.toString();
        double l = 0;
        try {
            l = Double.parseDouble(amount);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return l;
    }

    private long getTimeLong(Editable editable) {
        String time = editable.toString();
        long t = 0;
        try {
            t = Long.parseLong(time);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return t;
    }

    // 把123456.33转换为123,456.33这种格式
    private String getFormattedNumber(double number) {
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        return formatter.format(number);
    }

    // 把123456.33转换为123,456.33这种格式
    private String getFormattedTime(long seconds) {
        long minutes = seconds / 60;
        long remainingSeconds = seconds % 60;
        return String.format("%dm%ds", minutes, remainingSeconds);
    }
}
