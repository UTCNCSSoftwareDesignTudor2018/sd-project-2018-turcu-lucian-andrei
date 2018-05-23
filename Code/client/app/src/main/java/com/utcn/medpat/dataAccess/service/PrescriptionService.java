package com.utcn.medpat.dataAccess.service;

import com.utcn.medpat.model.Prescription;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Lucian on 5/22/2018.
 */

public interface PrescriptionService {

    @GET("/getPatientPrescriptions")
    Call<List<Prescription>> getPatientPrescriptions(@Query("patientId") Long patientId);

    @GET("/getDoctorPrescriptions")
    Call<List<Prescription>> getDoctorPrescriptions(@Query("doctorId") Long doctorId);
}
