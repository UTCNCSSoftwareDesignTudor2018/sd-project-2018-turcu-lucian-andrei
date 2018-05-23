package com.example.utcn.medpat.service;

import com.example.utcn.medpat.communication.dto.AppointmentDTO;
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
    @Inject
    private MedicService medicService;
    @Inject
    private PatientService patientService;

    private java.text.SimpleDateFormat sdf =
            new java.text.SimpleDateFormat("HH:mm:ss dd-MM-yyyy");

    public boolean makeAppointment(AppointmentDTO appointmentDTO) {
        List<Appointment> appointments = appointmentRepository.findAllByMedicId(appointmentDTO.getMedicId());
        Boolean free = true;

        try {
            Date requestedDate = sdf.parse(appointmentDTO.getDate());
            for (Appointment a : appointments) {
                Date bookedDate = sdf.parse(a.getDate());
                if(bookedDate.getYear() == requestedDate.getYear() &&
                        bookedDate.getMonth() == requestedDate.getMonth() &&
                        bookedDate.getDay() == requestedDate.getDay() &&
                        bookedDate.getHours() == requestedDate.getHours()) {
                    free = false;
                }
            }
        } catch(ParseException e) {
            e.printStackTrace();
        }

        if(free) {
            Appointment appointment = new Appointment();
            appointment.setMedic(medicService.getMedic(appointmentDTO.getMedicId()));
            appointment.setPatient(patientService.getPatient(appointmentDTO.getPatientID()));
            appointment.setLocation(appointmentDTO.getLocation());
            appointment.setDate(appointmentDTO.getDate());
            appointmentRepository.save(appointment);
        }

        return free;
    }

    public List<Appointment> getAppointments(boolean isDoctor, Long id) {
        if(isDoctor) {
            return appointmentRepository.findAllByMedicId(id);
        } else {
            return appointmentRepository.findAllByPatientId(id);
        }
    }

}
