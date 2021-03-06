package com.utcn.medpat.presentation.fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.utcn.medpat.R;
import com.utcn.medpat.dataAccess.service.PrescriptionService;
import com.utcn.medpat.model.Medic;
import com.utcn.medpat.model.Patient;
import com.utcn.medpat.model.Prescription;
import com.utcn.medpat.model.User;
import com.utcn.medpat.presentation.activity.MainActivity;
import com.utcn.medpat.presentation.activity.ViewPrescriptionActivity;
import com.utcn.medpat.presentation.adapter.PrescriptionMedicAdapter;
import com.utcn.medpat.presentation.adapter.PrescriptionPatientAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lucian on 5/22/2018.
 */

public class PrescriptionFragment extends Fragment{
    private static final String TAG = "PrescriptionFragment";
    private Medic currentMedic;
    private Patient currentPatient;
    private User currentUser;

    private List<Prescription> prescriptionList;
    private ListView prescriptionListView;
    SwipeRefreshLayout swipeLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.prescription_fragment,container,false);

        prescriptionListView = view.findViewById(R.id.prescription_list);
        updateList(prescriptionListView);
       // btnTest.setOnClickListener((v)-> Toast.makeText(getActivity(), "Testing prescription", Toast.LENGTH_SHORT).show());

        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        swipeLayout.setOnRefreshListener(() -> updateList(prescriptionListView));

        prescriptionListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Prescription prescription = (Prescription) parent.getItemAtPosition(position);
                Intent intent = new Intent(view.getContext(), ViewPrescriptionActivity.class);
                intent.putExtra("User", currentUser);
                intent.putExtra("Prescription", prescription);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = view.findViewById(R.id.fab);
        if(currentMedic == null) {
            fab.setVisibility(View.GONE);
        } else {
            fab.setVisibility(View.VISIBLE);
        }

        return view;
    }

    public void setCurrentPerson(Medic medic, Patient patient) {
        if(medic != null) {
            this.currentMedic = medic;
        } else if(patient != null){
            this.currentPatient = patient;
        } else {
            Log.e("PERSON", "Unable to retrieve current person");
        }
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    private void updateList(ListView listView){
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(MainActivity.SERVER_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();

        PrescriptionService prescriptionService = retrofit.create(PrescriptionService.class);
        Call<List<Prescription>> appointmentCall = null;

        if(currentMedic != null) {
            appointmentCall = prescriptionService.getDoctorPrescriptions(currentMedic.getId());
        } else if(currentPatient != null) {
            appointmentCall = prescriptionService.getPatientPrescriptions(currentPatient.getId());
        }

        appointmentCall.enqueue(new Callback<List<Prescription>>() {
            @Override
            public void onResponse(Call<List<Prescription>> call, Response<List<Prescription>> response) {

                prescriptionList = response.body();
                //Log.i(TAG, "Size: "+prescriptionList.size());

                if(currentMedic != null) {
                    listView.setAdapter(new PrescriptionPatientAdapter(listView.getContext(), listView.getId(), prescriptionList));
                } else if(currentPatient != null) {
                    listView.setAdapter(new PrescriptionMedicAdapter(listView.getContext(), listView.getId(), prescriptionList));
                }
                swipeLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Prescription>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                swipeLayout.setRefreshing(false);
            }
        });

    }
}
