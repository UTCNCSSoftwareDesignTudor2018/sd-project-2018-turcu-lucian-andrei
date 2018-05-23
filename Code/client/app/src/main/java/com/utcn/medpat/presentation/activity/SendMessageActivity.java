package com.utcn.medpat.presentation.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.utcn.medpat.R;
import com.utcn.medpat.dataAccess.dto.MessageDTO;
import com.utcn.medpat.dataAccess.service.MessagingService;
import com.utcn.medpat.dataAccess.service.PersonService;
import com.utcn.medpat.dataAccess.service.UserService;
import com.utcn.medpat.model.Medic;
import com.utcn.medpat.model.Message;
import com.utcn.medpat.model.User;
import com.utcn.medpat.presentation.adapter.MessageAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SendMessageActivity extends AppCompatActivity {

    Spinner messageSpinner;
    User selectedUser;
    User currentUser;
    List<User> users;
    int iCurrentSelection;
    private UserService userService;
    private MessagingService messagingService;
    EditText messageText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        currentUser = (User) getIntent().getSerializableExtra("User");

        messageSpinner = (Spinner) findViewById(R.id.messageSpinner);
        iCurrentSelection = messageSpinner.getSelectedItemPosition();
        messageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (iCurrentSelection != i){
                    selectedUser = users.get(i);
                    Log.i("USER", selectedUser.getUsername());
                }
                iCurrentSelection = i;
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });


        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(MainActivity.SERVER_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        userService = retrofit.create(UserService.class);
        messagingService = retrofit.create(MessagingService.class);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog mProgressDialog = new ProgressDialog(SendMessageActivity.this);
                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.show();

                messageText = findViewById(R.id.messageText);
                MessageDTO messageDTO = new MessageDTO.Builder()
                        .setMessage(messageText.getText().toString())
                        .setFrom(currentUser.getUsername())
                        .setTo(selectedUser.getUsername())
                        .create();

                Call<Void> messageCall = messagingService.sendMessage(messageDTO);

                messageCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                        Toast.makeText(SendMessageActivity.this, "Message sent!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        if (mProgressDialog.isShowing())
                            mProgressDialog.dismiss();
                        Toast.makeText(SendMessageActivity.this, "Message not sent!", Toast.LENGTH_SHORT).show();
                        Log.e("MESSAGES", t.getMessage());
                    }
                });
            }
        });

        populateUserList(retrofit);
    }

    private void populateUserList(Retrofit retrofit){
        Call<List<User>> userCall = userService.getUsers();

        userCall.enqueue(new Callback<List<User>>() {

            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                users = response.body();
                ArrayAdapter<User> adapter =
                        new ArrayAdapter<User>(getApplicationContext(), R.layout.single_medic_item, users);
                adapter.setDropDownViewResource(R.layout.single_medic_item);
                messageSpinner.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("SPINNER ERROR", t.getMessage());
            }
        });
    }

}
