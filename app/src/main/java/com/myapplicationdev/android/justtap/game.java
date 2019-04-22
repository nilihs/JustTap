package com.myapplicationdev.android.justtap;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class game extends AppCompatActivity {

    TextView tvScore;
    ImageView ivTap;
    EditText etName;

    CountDownTimer countDown;
    int taps = 0;
    boolean gameStart = false;

    DatabaseReference databaseAccountInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tvScore = findViewById(R.id.textViewScore);
        ivTap = findViewById(R.id.imageViewAim);
        etName = findViewById(R.id.editTextName);

        databaseAccountInfo = FirebaseDatabase.getInstance().getReference("accountinfo");

        ivTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gameStart == true) {
                    taps++;
                }
                else {
                    gameStart = true;
                    countDown.start();
                }
            }
        });


        countDown = new CountDownTimer(15000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                tvScore.setText("Time Remaining: " + (millisUntilFinished/1000) +
                        "\n Score: " + taps);

            }

            @Override
            public void onFinish() {

                tvScore.setText("Game Over! " + etName.getText().toString().trim() + " highest score is " + taps);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ivTap.setEnabled(true);
                        tvScore.setText("Start Tapping !");
                        taps = 0;
                    }
                }, 5000);

            }
        };

    }

    private void addInfo() {
        String name = etName.getText().toString().trim();
        int score = taps;

        if (!TextUtils.isEmpty(name)) {
            String id = databaseAccountInfo.push().getKey();
            accountinfo account = new accountinfo(id, name, score);
            databaseAccountInfo.child(id).setValue(account);

        }else {
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }
}
