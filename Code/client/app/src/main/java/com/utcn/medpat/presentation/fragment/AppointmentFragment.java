package com.utcn.medpat.presentation.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.utcn.medpat.R;
import com.utcn.medpat.dataAccess.service.AppointmentService;
import com.utcn.medpat.model.Appointment;
import com.utcn.medpat.model.Medic;
import com.utcn.medpat.model.Patient;
import com.utcn.medpat.presentation.adapter.AppointmentMedicAdapter;
import com.utcn.medpat.presentation.adapter.AppointmentPatientAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lucian on 5/22/2018.
 */

public class AppointmentFragment extends Fragment {
    private static final String TAG = "AppointmentFragment";
    private Medic currentMedic;
    private Patient currentPatient;
    private List<Appointment> appointmentList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.appointment_fragment,container,false);
        updateList(view.findViewById(R.id.appointment_list));

        //btnTest.setOnClickListener((v)-> Toast.makeText(getActivity(), "Testing appointments", Toast.LENGTH_SHORT).show());

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
    private void updateList(ListView listView){
        final ProgressDialog mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("http://192.168.100.5:8080").
                addConverterFactory(GsonConverterFactory.create()).
                build();

        AppointmentService appointmentService = retrofit.create(AppointmentService.class);
        Call<List<Appointment>> appointmentCall = null;

        if(currentMedic != null) {
            appointmentCall = appointmentService.getDoctorAppointments(currentMedic.getId());
        } else if(currentPatient != null) {
            appointmentCall = appointmentService.getDoctorAppointments(currentPatient.getId());
        }

        appointmentCall.enqueue(new Callback<List<Appointment>>() {
            @Override
            public void onResponse(Call<List<Appointment>> call, Response<List<Appointment>> response) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();

                appointmentList = response.body();
                Log.i(TAG, "Size: "+appointmentList.size());

                if(currentMedic != null) {
                    listView.setAdapter(new AppointmentMedicAdapter(listView.getContext(), listView.getId(), appointmentList));
                } else if(currentPatient != null) {
                    listView.setAdapter(new AppointmentPatientAdapter(listView.getContext(), listView.getId(), appointmentList));
                }
            }

            @Override
            public void onFailure(Call<List<Appointment>> call, Throwable t) {
                if (mProgressDialog.isShowing())
                    mProgressDialog.dismiss();
                Log.e(TAG, t.getMessage());
            }
        });

    }
}
