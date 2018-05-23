package com.utcn.medpat.dataAccess.service;

import com.utcn.medpat.model.Medic;
import com.utcn.medpat.model.Patient;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Lucian on 5/22/2018.
 */

public interface PersonService {

    @GET("/getMedic")
    Call<Medic> getMedic(@Query("medicId") Long medicId);

    @GET("/getPatient")
    Call<Patient> getPatient(@Query("patientId") Long patientId);
}
