package com.utcn.medpat.presentation.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.utcn.medpat.R;
import com.utcn.medpat.dataAccess.dto.PrescriptionDTO;
import com.utcn.medpat.dataAccess.service.MedicationService;
import com.utcn.medpat.dataAccess.service.PersonService;
import com.utcn.medpat.dataAccess.service.PrescriptionService;
import com.utcn.medpat.model.Medic;
import com.utcn.medpat.model.Medication;
import com.utcn.medpat.model.Patient;
import com.utcn.medpat.model.User;
import com.utcn.medpat.presentation.adapter.MedicationAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddPrescriptionActivity extends AppCompatActivity {

    PersonService personService;
    MedicationService medicationService;
    PrescriptionService prescriptionService;

    List<Patient> patients;
    List<Medication> medications;
    List<Medication> prescribed;
    Medic currentMedic;
    User currentUser;

    Spinner patientSpinner;
    Spinner medicationSpinner;
    Button addMed;
    ListView prescribedMedList;
    EditText disease;

    Patient selectedPatient;
    Medication selectedMedication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prescription);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(MainActivity.SERVER_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();
        personService = retrofit.create(PersonService.class);
        medicationService = retrofit.create(MedicationService.class);
        prescriptionService = retrofit.create(PrescriptionService.class);

        currentMedic = (Medic) getIntent().getSerializableExtra("Medic");
        currentUser = (User) getIntent().getSerializableExtra("User");
        prescribed = new ArrayList<>();

        patientSpinner = findViewById(R.id.patientSpinner);
        medicationSpinner = findViewById(R.id.medicationSpinner);
        addMed = findViewById(R.id.addMed);
        disease = findViewById(R.id.diseaseText);
        prescribedMedList = findViewById(R.id.prescribedMedList);

        populateMedicationSpinner();
        populatePatientSpinner();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPrescription();
            }
        });

        addMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMedication();
            }
        });
        patientSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedPatient = patients.get(i);
                Log.i("PATIENT", selectedPatient.getName());
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

       medicationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedMedication = medications.get(i);
                Log.i("MEDICATION", selectedMedication.getName());
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
    }

    private void populatePatientSpinner() {
        Call<List<Patient>> userCall = personService.getPatients();

        userCall.enqueue(new Callback<List<Patient>>() {

            @Override
            public void onResponse(Call<List<Patient>> call, Response<List<Patient>> response) {
                patients = response.body();
                ArrayAdapter<Patient> adapter =
                        new ArrayAdapter<Patient>(getApplicationContext(), R.layout.single_medic_item, patients);
                adapter.setDropDownViewResource(R.layout.single_medic_item);
                patientSpinner.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Patient>> call, Throwable t) {
                Log.e("SPINNER ERROR", t.getMessage());
            }
        });
    }

    private void populateMedicationSpinner() {
        Call<List<Medication>> userCall = medicationService.getMedications();

        userCall.enqueue(new Callback<List<Medication>>() {

            @Override
            public void onResponse(Call<List<Medication>> call, Response<List<Medication>> response) {
                medications = response.body();
                ArrayAdapter<Medication> adapter =
                        new ArrayAdapter<Medication>(getApplicationContext(), R.layout.single_medic_item, medications);
                adapter.setDropDownViewResource(R.layout.single_medic_item);
                medicationSpinner.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Medication>> call, Throwable t) {
                Log.e("SPINNER ERROR", t.getMessage());
            }
        });
    }


    private void populateMedicationList() {
        prescribedMedList.setAdapter(new MedicationAdapter(prescribedMedList.getContext(), prescribedMedList.getId(), prescribed));
    }

    private void addMedication() {
        if(prescribed.contains(selectedMedication)) {
            Toast.makeText(AddPrescriptionActivity.this, "Already in the list!", Toast.LENGTH_SHORT).show();
        } else {
            prescribed.add(selectedMedication);
            populateMedicationList();
        }
    }

    private void createPrescription() {
        List<Long> prescribedIds = new ArrayList<>();
        if(prescribed.isEmpty()) {
            Toast.makeText(AddPrescriptionActivity.this, "Please prescribe medication!", Toast.LENGTH_SHORT).show();
            return;
        }

        for(Medication m: prescribed) {
            prescribedIds.add(m.getId());
        }

        PrescriptionDTO prescriptionDTO = new PrescriptionDTO.Builder()
                .setMedicId(currentMedic.getId())
                .setPatientId(selectedPatient.getId())
                .setMedicationIds(prescribedIds)
                .setDisease(disease.getText().toString())
                .create();

        Call<Void> prescriptionCall = prescriptionService.prescribe(prescriptionDTO);

        prescriptionCall.enqueue(new Callback<Void> () {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(AddPrescriptionActivity.this, "Prescription created!", Toast.LENGTH_SHORT).show();
                returnToMain();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(AddPrescriptionActivity.this, "Failed to create prescription!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void returnToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("User", currentUser);
        startActivity(intent);
    }
}
