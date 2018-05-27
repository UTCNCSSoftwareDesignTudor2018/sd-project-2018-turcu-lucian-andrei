package com.utcn.medpat.presentation.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.utcn.medpat.R;
import com.utcn.medpat.dataAccess.dto.AppointmentDTO;
import com.utcn.medpat.dataAccess.service.AppointmentService;
import com.utcn.medpat.dataAccess.service.PersonService;
import com.utcn.medpat.model.Medic;
import com.utcn.medpat.model.Patient;
import com.utcn.medpat.model.User;
import com.utcn.medpat.presentation.fragment.DatePickerFragment;
import com.utcn.medpat.presentation.fragment.TimePickerFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddAppointmentActivity extends AppCompatActivity implements
DatePickerInterface, TimePickerInterface{
    private User currentUser;
    private Patient currentPatient;
    private Medic selectedMedic;
    private PersonService personService;

    private Spinner medicSpinner;
    private TextView hourView;
    private TextView dayView;

    private List<Medic> medics;
    int iCurrentSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        currentPatient = (Patient) getIntent().getSerializableExtra("Type");
        currentUser = (User) getIntent().getSerializableExtra("User");

        medicSpinner = (Spinner) findViewById(R.id.medicSpinner);
        iCurrentSelection = medicSpinner.getSelectedItemPosition();
        medicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (iCurrentSelection != i){
                    selectedMedic = medics.get(i);
                    Log.i("MEDIC", selectedMedic.toString());
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
        personService = retrofit.create(PersonService.class);
        populateMedicList();

        hourView = findViewById(R.id.hour);
        dayView = findViewById(R.id.day);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = hourView.getText()+" "+dayView.getText();
                if(!date.equals("00:00 1/1/1")) {
                    String location = selectedMedic.getWorkAddress();
                    AppointmentDTO appointmentDTO = new AppointmentDTO(selectedMedic.getId(), currentPatient.getId(), location, date);
                    AppointmentService appointmentService = retrofit.create(AppointmentService.class);
                    Call<Boolean> appointmentCall = appointmentService.makeAppointment(appointmentDTO);

                    appointmentCall.enqueue(new Callback<Boolean>() {

                        @Override
                        public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                            Boolean ok = response.body();
                            if (ok) {
                                Toast.makeText(AddAppointmentActivity.this, "Date successfully booked!", Toast.LENGTH_SHORT).show();
                                returnToMain();
                            } else {
                                Toast.makeText(AddAppointmentActivity.this, "Date already booked!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Boolean> call, Throwable t) {
                            Toast.makeText(AddAppointmentActivity.this, "Bad Request", Toast.LENGTH_SHORT).show();
                            Log.e("LOGIN ERROR", t.getMessage());
                        }
                    });
                } else {
                    Toast.makeText(AddAppointmentActivity.this, "Please set the hour and date!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void returnToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("User", currentUser);
        startActivity(intent);
    }

    public void showTimePickerDialog(View v) {
        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.delegate = AddAppointmentActivity.this;
        newFragment.setCancelable(false);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.delegate = AddAppointmentActivity.this;
        newFragment.setCancelable(false);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private void populateMedicList() {
        Call<List<Medic>> userCall = personService.getMedics();

        userCall.enqueue(new Callback<List<Medic>>() {

            @Override
            public void onResponse(Call<List<Medic>> call, Response<List<Medic>> response) {
                medics = response.body();
                ArrayAdapter<Medic> adapter =
                        new ArrayAdapter<Medic>(getApplicationContext(), R.layout.single_medic_item, medics);
                adapter.setDropDownViewResource(R.layout.single_medic_item);
                medicSpinner.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Medic>> call, Throwable t) {
                Log.e("SPINNER ERROR", t.getMessage());
            }
        });
    }

    @Override
    public void onDateSelected(int day, int month, int year) {
        dayView.setText(day+"-"+month+"-"+year);
    }

    @Override
    public void onTimeSelected(int hours, int minute) {
        hourView.setText(hours+":"+minute+":00");
    }
}
