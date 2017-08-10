package com.code_breaker.tesandroidbootcamp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_btnLogin)
    Button btnLogin;
    @BindView(R.id.login_etPass)
    EditText etPass;
    @BindView(R.id.login_etUsername)
    EditText etUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_btnLogin)
    public void login(){
        String username = etUsername.getText().toString().trim();
        String pass = etPass.getText().toString().trim();

        if(username.isEmpty() || pass.isEmpty()){
            Toast.makeText(this, "Kolom masih kosong", Toast.LENGTH_SHORT).show();
        }else{
            if(username.equals("admin") && pass.equals("admin")) startActivity(new Intent(this,SplashActivity.class));

            else  Toast.makeText(this, "Cek username dan password", Toast.LENGTH_SHORT).show();

        }

    }

}
