package com.utcn.medpat.dataAccess.service;

import com.utcn.medpat.model.Medic;
import com.utcn.medpat.model.Medication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Lucian on 5/27/2018.
 */

public interface MedicationService {
    @GET("/getMedications")
    Call<List<Medication>> getMedications();
}
