package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText txtUser;
    private EditText txtPass;
    private Button btnSubmit;
    private Button button2;
    private TextView label;

    private int backPressCount = 0;
    private final Handler handler = new Handler(Looper.getMainLooper());

    private final Runnable resetBackPressCount = () -> backPressCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUser = findViewById(R.id.editTextText);
        txtPass = findViewById(R.id.editTextTextPassword);
        btnSubmit = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        label = findViewById(R.id.label);

        btnSubmit.setOnClickListener(v -> {
            String username = txtUser.getText().toString();
            String password = txtPass.getText().toString();

            if (username.equals("Admin") && password.equals("123")) {
                label.setText("خوش آمدید");
            } else {
                label.setText("نام کاربری یا رمز عبور اشتباه است");
            }
        });

        button2.setOnClickListener(v -> {
            if (backPressCount == 0) {
                backPressCount = 1;
                label.setText("برای خروج، دوباره دکمه را بزنید");

                handler.removeCallbacks(resetBackPressCount);
                handler.postDelayed(resetBackPressCount, 2000);
            } else {
                handler.removeCallbacks(resetBackPressCount);
                label.setText("خداحافظ");

                // کمی فرصت می‌دهیم تا پیام دیده شود، سپس Activity بسته می‌شود.
                handler.postDelayed(this::finish, 1000);
            }
        });
    }
}
