package com.wizzitdigital.emv.sdk.debicheckapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.wizzitdigital.emv.sdk.debicheck.AccountType;
import com.wizzitdigital.emv.sdk.debicheck.EMVDebicheckJava;
import com.wizzitdigital.emv.sdk.debicheck.EMVDebicheckJavaListener;
import com.wizzitdigital.emv.sdk.debicheck.IdType;

public class MainActivity extends AppCompatActivity implements EMVDebicheckJavaListener {

    EMVDebicheckJava emvDebicheckJava;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        emvDebicheckJava = new EMVDebicheckJava(this, this);
        intro();
    }

    private void intro() {
        setContentView(R.layout.activity_input);
        Button btnCall = (Button) findViewById(R.id.btnCall);
        btnCall.setOnClickListener((View view) -> {
            init();
        });
    }

    private void init() {
        emvDebicheckJava.initialize(
                "COL24152",
                "071 352 3879",
                "1234 1234 123",
                AccountType.Cheque,
                IdType.IDDocument,
                "8729230875321",
                45000,
                55000,
                120000
        );
    }

    @Override
    public void onDebicheckComplete(Boolean success, String code, String description) {
        runOnUiThread(() -> {
            setContentView(R.layout.activity_outcome);
            TextView txtSuccess = (TextView) findViewById(R.id.txtOutcomeSuccess);
            TextView txtCode = (TextView) findViewById(R.id.txtOutcomeCode);
            TextView txtDescription = (TextView) findViewById(R.id.txtOutcomeDescription);
            Button btnRestart = (Button) findViewById  (R.id.btnRestart);
            txtSuccess.setText(success?"true":"false");
            txtCode.setText("\""+code+"\"");
            txtDescription.setText("\""+description+"\"");
            btnRestart.setOnClickListener((View view) -> {
                intro();
            });
        });
    }


}
