package com.utcn.medpat.dataAccess.service;

import com.utcn.medpat.dataAccess.dto.AppointmentDTO;
import com.utcn.medpat.model.Appointment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Lucian on 5/22/2018.
 */

public interface AppointmentService {

    @GET("/getDoctorAppointments")
    Call<List<Appointment>> getDoctorAppointments(@Query("doctorId") Long doctorId);

    @GET("/getPatientAppointments")
    Call<List<Appointment>> getPatientAppointments(@Query("patientId") Long patientId);

    @POST("/makeAppointment")
    Call<Boolean> makeAppointment(@Body AppointmentDTO appointment);

    @GET("/deleteAppointment")
    Call<Void> deleteAppointment(@Query("appointmentId") Long appointmentId);
}
