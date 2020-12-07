package com.ramhluns.lltf.admin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class DetailActivity extends AppCompatActivity {

    TextView tvHming, tvPhone, tvChhuahduhchhan, tvChhuahduhna, tvChhuahni, tvChhuahhnuDarkar, tvMotorHming, tvMotorNumber;
    Button btnApprove, btnReject;
    ImageButton btnBack;
    private FormModel formData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        formData = (FormModel) getIntent().getSerializableExtra("form");

        tvHming = findViewById(R.id.tvHming);
        tvPhone = findViewById(R.id.tvPhone);
        tvChhuahduhchhan = findViewById(R.id.tvChhuahduhchhan);
        tvChhuahduhna = findViewById(R.id.tvChhuahduhna);
        tvChhuahni = findViewById(R.id.tvDate);
        tvChhuahhnuDarkar = findViewById(R.id.tvChhuahhnuDarkar);
        tvMotorHming = findViewById(R.id.tvMotorHming);
        tvMotorNumber = findViewById(R.id.tvMotorNo);

        if (formData != null) {
            tvHming.setText(formData.hming);
            tvPhone.setText(formData.phone);
            tvChhuahduhchhan.setText(formData.chhuahduhchhan);
            tvChhuahduhna.setText(formData.chhuahduhna);
            tvChhuahni.setText(formData.chhuahni);
            tvChhuahhnuDarkar.setText(formData.chhuahhnuDarkar);
            tvMotorHming.setText(formData.motorHming);
            tvMotorNumber.setText(formData.motorNumber);
        }

        btnApprove = findViewById(R.id.btnApprove);
        btnApprove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFormStatus("approved");
            }
        });

        btnReject = findViewById(R.id.btnReject);
        btnReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFormStatus("reject");
            }
        });

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void changeFormStatus(String status) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseFirestore.setLoggingEnabled(true);
        db.collection("forms")
                .document(formData.id)
                .update("status", status)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        finish();
                    }
                });
    }
}
