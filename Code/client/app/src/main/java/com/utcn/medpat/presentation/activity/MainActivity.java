package com.utcn.medpat.presentation.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.TextView;

import com.utcn.medpat.R;
import com.utcn.medpat.dataAccess.service.MessagingService;
import com.utcn.medpat.dataAccess.service.PersonService;
import com.utcn.medpat.model.Medic;
import com.utcn.medpat.model.Patient;
import com.utcn.medpat.model.User;
import com.utcn.medpat.presentation.adapter.SectionsPageAdapter;
import com.utcn.medpat.presentation.fragment.AppointmentFragment;
import com.utcn.medpat.presentation.fragment.MessageFragment;
import com.utcn.medpat.presentation.fragment.PrescriptionFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String SERVER_URL = "http://192.168.43.213:8080";
    private static final String TAG = "MainActivity";

    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;

    private User currentUser;
    private Medic currentMedic;
    private Patient currentPatient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Starting");

        Intent intent = getIntent();
        currentUser = (User) intent.getSerializableExtra("User");

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        setTypeClass(currentUser.getUserType());
        //setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());

        AppointmentFragment aFrag = new AppointmentFragment();
        aFrag.setCurrentPerson(currentMedic, currentPatient);
        adapter.addFragment(aFrag, "Appointments");

        MessageFragment mFrag = new MessageFragment();
        mFrag.setUser(currentUser);
        adapter.addFragment(mFrag, "Messages");

        PrescriptionFragment pFrag = new PrescriptionFragment();
        pFrag.setCurrentPerson(currentMedic, currentPatient);
        adapter.addFragment(pFrag, "Prescriptions");
        viewPager.setAdapter(adapter);
    }

    private void setTypeClass(String usertype) {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(SERVER_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();

        PersonService personService = retrofit.create(PersonService.class);

        if("doctor".equals(usertype)) {
            Call<Medic> medicCall = personService.getMedic(currentUser.getPersonId());
            //currentMedic = medicCall.execute().body();
            medicCall.enqueue(new Callback<Medic>(){
                @Override
                public void onResponse(Call<Medic> call, Response<Medic> response) {

                    currentMedic = response.body();
                    setupViewPager(mViewPager);
                    Log.i("PERSON", currentMedic.getName()+", "+currentUser.getUserType());
                }

                @Override
                public void onFailure(Call<Medic> call, Throwable t) {
                    Log.e("MESSAGES", t.getMessage());
                }
            });
        } else {
            Call<Patient> patientCall = personService.getPatient(currentUser.getPersonId());
            patientCall.enqueue(new Callback<Patient>(){
                @Override
                public void onResponse(Call<Patient> call, Response<Patient> response) {
                    currentPatient = response.body();
                    setupViewPager(mViewPager);
                    Log.i("PERSON", currentPatient.getName()+", "+currentUser.getUserType());
                }

                @Override
                public void onFailure(Call<Patient> call, Throwable t) {
                    Log.e("MESSAGES", t.getMessage());
                }
            });
        }

    }

    public void addAppointment(View view) {
        Intent intent = new Intent(view.getContext(), AddAppointmentActivity.class);
        intent.putExtra("User", currentUser);
        if(currentMedic != null)
            intent.putExtra("Type", currentMedic);
        else if(currentPatient != null)
            intent.putExtra("Type", currentPatient);
        startActivity(intent);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(view.getContext(), SendMessageActivity.class);
        intent.putExtra("User", currentUser);
        startActivity(intent);
    }
}
