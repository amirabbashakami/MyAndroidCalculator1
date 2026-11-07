package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText num1;
    private EditText num2;

    private Button btnAdd;
    private Button btnSub;
    private Button btnMul;
    private Button btnDiv;

    private Button button2;

    private TextView label;

    private int backPressCount = 0;

    private final Handler handler =
            new Handler(Looper.getMainLooper());

    private final Runnable resetBackPressCount =
            () -> backPressCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // اتصال اجزای صفحه
        num1 = findViewById(R.id.textview);
        num2 = findViewById(R.id.textview2);

        btnAdd = findViewById(R.id.button6);
        btnSub = findViewById(R.id.button);
        btnMul = findViewById(R.id.button5);
        btnDiv = findViewById(R.id.button3);

        button2 = findViewById(R.id.button2);

        label = findViewById(R.id.label);

        // دکمه جمع
        btnAdd.setOnClickListener(v -> calculate('+'));

        // دکمه تفریق
        btnSub.setOnClickListener(v -> calculate('-'));

        // دکمه ضرب
        btnMul.setOnClickListener(v -> calculate('*'));

        // دکمه تقسیم
        btnDiv.setOnClickListener(v -> calculate('/'));

        // دکمه خروج (حفظ کد قبلی)
        button2.setOnClickListener(v -> {

            if (backPressCount == 0) {

                backPressCount = 1;

                label.setText("برای خروج، دوباره دکمه را بزنید");

                handler.removeCallbacks(resetBackPressCount);

                handler.postDelayed(resetBackPressCount, 2000);

            } else {

                handler.removeCallbacks(resetBackPressCount);

                label.setText("خداحافظ");

                handler.postDelayed(this::finish, 1000);
            }
        });
    }

    // تابع انجام چهار عمل اصلی
    private void calculate(char operation) {

        String a = num1.getText().toString();
        String b = num2.getText().toString();

        // بررسی خالی نبودن تکست باکس ها
        if (a.isEmpty() || b.isEmpty()) {

            label.setText("لطفاً هر دو عدد را وارد کنید");

            return;
        }

        double n1 = Double.parseDouble(a);
        double n2 = Double.parseDouble(b);

        double result = 0;

        switch (operation) {

            case '+':

                result = n1 + n2;

                break;

            case '-':

                result = n1 - n2;

                break;

            case '*':

                result = n1 * n2;

                break;

            case '/':

                if (n2 == 0) {

                    label.setText("تقسیم بر صفر امکان پذیر نیست");

                    return;
                }

                result = n1 / n2;

                break;
        }

        label.setText("نتیجه: " + result);
    }
}