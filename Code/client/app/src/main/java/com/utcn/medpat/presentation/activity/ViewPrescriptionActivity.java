package com.utcn.medpat.presentation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.utcn.medpat.R;
import com.utcn.medpat.dataAccess.service.LoginService;
import com.utcn.medpat.dataAccess.service.PrescriptionService;
import com.utcn.medpat.model.Medication;
import com.utcn.medpat.model.Prescription;
import com.utcn.medpat.model.User;
import com.utcn.medpat.presentation.adapter.MedicationAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewPrescriptionActivity extends AppCompatActivity {

    Prescription prescription;
    User currentUser;

    TextView prescribedBy;
    TextView prescribedFor;
    TextView prescribedAt;
    TextView forDisease;
    ListView medList;

    PrescriptionService prescriptionService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_prescription);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        prescription = (Prescription) i.getSerializableExtra("Prescription");
        currentUser = (User) i.getSerializableExtra("User");

        prescribedBy = findViewById(R.id.prescribedBy);
        prescribedFor = findViewById(R.id.prescribedFor);
        prescribedAt = findViewById(R.id.prescribedAt);
        forDisease = findViewById(R.id.forDisease);
        medList = findViewById(R.id.medList);

        prescribedBy.setText("Prescribed by: "+prescription.getMedic().getName());
        prescribedFor.setText("For patient: "+prescription.getPatient().getName());
        prescribedAt.setText("Date: "+prescription.getCreationDate());
        forDisease.setText("Disease: "+prescription.getDisease());
        medList.setAdapter(new MedicationAdapter(medList.getContext(), medList.getId(), prescription.getMedications()));

        FloatingActionButton fab = findViewById(R.id.fab);
        if(currentUser.getUserType().equals("patient")) {
            fab.setVisibility(View.GONE);
        } else {
            fab.setVisibility(View.VISIBLE);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePrescription();
                returnToMain();

            }
        });

    }

    public void returnToMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("User", currentUser);
        startActivity(intent);
    }

    private void deletePrescription() {

        //Retrofit initialization
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(MainActivity.SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        //Service creation
        prescriptionService = retrofit.create(PrescriptionService.class);

        Call<Void> prescriptionCall = prescriptionService.deletePrescription(prescription.getId());
        prescriptionCall.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(ViewPrescriptionActivity.this, "Prescription deleted successfully!", Toast.LENGTH_SHORT);
                returnToMain();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(ViewPrescriptionActivity.this, "Failed to delete prescription!", Toast.LENGTH_SHORT);
            }
        });
    }

}
