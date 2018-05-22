package com.utcn.medpat.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.utcn.medpat.R;
import com.utcn.medpat.dataAccess.dto.LoginCredentials;
import com.utcn.medpat.dataAccess.service.LoginService;
import com.utcn.medpat.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;

    private User currentUser;
    LoginService loginService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Retrofit initialization
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://192.168.100.5:8080")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        //Service creation
        loginService = retrofit.create(LoginService.class);

    }

    void doLogin(View view) {

        LoginCredentials loginCredentials = new LoginCredentials.Builder()
                .setUsername(((EditText) findViewById(R.id.username_input)).getText().toString())
                .setPassword(((EditText) findViewById(R.id.password_input)).getText().toString())
                .create();
        Log.i("CREDENTIALS", loginCredentials.getUsername()+" "+loginCredentials.getPassword());

        Call<User> userCall = loginService.login(loginCredentials);

        userCall.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                currentUser = response.body();
                Toast.makeText(LoginActivity.this, currentUser.getPersonId().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error occured on login.", Toast.LENGTH_SHORT).show();
                Log.e("LOGIN ERROR", t.getMessage());
            }
        });

    }
}
