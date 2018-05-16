package com.example.utcn.medpat.service;

import com.example.utcn.medpat.persistence.model.Appointment;
import com.example.utcn.medpat.persistence.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public class AppointmentService {

    @Inject
    private AppointmentRepository appointmentRepository;

    private java.text.SimpleDateFormat sdf =
            new java.text.SimpleDateFormat("HH:mm:ss dd-MM-yyyy");

    public boolean makeAppointment(Appointment appointment) {
        List<Appointment> appointments = appointmentRepository.findAllByDoctorId(appointment.getDoctorId());
        Boolean free = true;

        try {
            Date requestedDate = sdf.parse(appointment.getDate());
            for (Appointment a : appointments) {
                Date bookedDate = sdf.parse(appointment.getDate());
                if(bookedDate.getYear() == requestedDate.getYear() &&
                        bookedDate.getDay() == requestedDate.getDay() &&
                        bookedDate.getHours() == requestedDate.getHours()) {
                    free = false;
                }
            }
        } catch(ParseException e) {
            e.printStackTrace();
        }

        if(free) {
            appointmentRepository.save(appointment);
        }

        return free;
    }

}
