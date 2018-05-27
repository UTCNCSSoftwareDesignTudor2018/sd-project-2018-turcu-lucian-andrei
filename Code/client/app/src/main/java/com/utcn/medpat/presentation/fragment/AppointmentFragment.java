package com.utcn.medpat.presentation.fragment;

import android.content.DialogInterface;
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
import android.widget.ListView;
import android.widget.Toast;

import com.utcn.medpat.R;
import com.utcn.medpat.dataAccess.service.AppointmentService;
import com.utcn.medpat.model.Appointment;
import com.utcn.medpat.model.Medic;
import com.utcn.medpat.model.Patient;
import com.utcn.medpat.presentation.activity.MainActivity;
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

    private SwipeRefreshLayout swipeLayout;

    AppointmentService appointmentService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.appointment_fragment,container,false);

        swipeLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        swipeLayout.setOnRefreshListener(() -> updateList(view.findViewById(R.id.appointment_list)));

        ListView appointmentList = view.findViewById(R.id.appointment_list);

        appointmentList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Appointment appointment = (Appointment) parent.getItemAtPosition(position);

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                removeAppointment(appointment);
                                updateList(appointmentList);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Delete appointment?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(MainActivity.SERVER_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();

        appointmentService = retrofit.create(AppointmentService.class);

        updateList(appointmentList);

        FloatingActionButton fab = view.findViewById(R.id.fab);
        if(currentPatient == null) {
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

    private void removeAppointment(Appointment appointment) {
        Call<Void> deleteCall = appointmentService.deleteAppointment(appointment.getId());
        deleteCall.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(getContext(), "Appointment deleted successfully", Toast.LENGTH_SHORT);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(), "Unable to delete appointment", Toast.LENGTH_SHORT);
            }
        });
    }

    private void updateList(ListView listView){
        Call<List<Appointment>> appointmentCall = null;

        if(currentMedic != null) {
            appointmentCall = appointmentService.getDoctorAppointments(currentMedic.getId());
        } else if(currentPatient != null) {
            appointmentCall = appointmentService.getPatientAppointments(currentPatient.getId());
        }

        appointmentCall.enqueue(new Callback<List<Appointment>>() {
            @Override
            public void onResponse(Call<List<Appointment>> call, Response<List<Appointment>> response) {
                appointmentList = response.body();
                Log.i(TAG, "Size: "+appointmentList.size());

                if(currentMedic != null) {
                    listView.setAdapter(new AppointmentPatientAdapter(listView.getContext(), listView.getId(), appointmentList));
                } else if(currentPatient != null) {
                    listView.setAdapter(new AppointmentMedicAdapter(listView.getContext(), listView.getId(), appointmentList));
                }
                swipeLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Appointment>> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                swipeLayout.setRefreshing(false);
            }
        });


    }
}
