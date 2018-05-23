package com.example.utcn.medpat.communication;

import com.example.utcn.medpat.communication.dto.LoginCredentials;
import com.example.utcn.medpat.communication.dto.MessageDTO;
import com.example.utcn.medpat.persistence.model.*;
import com.example.utcn.medpat.service.*;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
public class RequestController {

    @Inject
    private UserService userService;

    @Inject
    private MessageService messageService;

    @Inject
    private AppointmentService appointmentService;

    @Inject
    private ArticleService articleService;

    @Inject
    private MedicationService medicationService;

    @Inject
    private PrescriptionService prescriptionService;

    @Inject
    private PatientService patientService;

    @Inject
    private MedicService medicService;

    /**
     * Login methods
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User login(@RequestBody String loginCredentials) {
        Gson gson = new Gson();
        LoginCredentials loginObj = gson.fromJson(loginCredentials, LoginCredentials.class);

        if(loginObj != null &&loginObj.getUsername() != null && loginObj.getPassword() != null) {
            User user = userService.login(loginObj.getUsername(), loginObj.getPassword());
            return user;
        }

        return null;
    }
    /**
     * User methods
     */
    @RequestMapping(value="/getPatients", method = RequestMethod.GET)
    public List<Patient> getPatients() {
        return patientService.getPatients();
    }

    @RequestMapping(value="/getPatient", method = RequestMethod.GET)
    public Patient getPatient(@RequestParam Long patientId) {
        return patientService.getPatient(patientId);
    }


    @RequestMapping(value="/getMedics", method = RequestMethod.GET)
    public List<Medic> getMedics() {
        return medicService.getMedics();
    }

    @RequestMapping(value="/getMedic", method = RequestMethod.GET)
    public Medic getMedic(@RequestParam Long medicId) {
        return medicService.getMedic(medicId);
    }

    /**
     * Messaging methods
     */
    @RequestMapping(value="/getInbox", method = RequestMethod.GET)
    public List<Message> getInbox(@RequestParam String toId) {
        if(toId != null)
            return messageService.getMessages("to", toId);
        else
            return null;
    }

    @RequestMapping(value="/getSent", method = RequestMethod.GET)
    public List<Message> getSent(@RequestParam String fromId) {
        if(fromId != null)
            return messageService.getMessages("from", fromId);
        else
            return null;
    }

    @RequestMapping(value="/sendMessage", method = RequestMethod.POST)
    public void sendMessage(@RequestBody String message) {
        Gson gson = new Gson();
        MessageDTO messageObj = gson.fromJson(message, MessageDTO.class);

        if(messageObj != null) {
            messageService.sendMessage(messageObj);
            //send notification
        }
    }

    /**
     * Appointment methods
     */
    @RequestMapping(value="/makeAppointment", method = RequestMethod.POST)
    public boolean makeAppointment(@RequestBody String appointment) {
        Gson gson = new Gson();
        Appointment appointmentObj = gson.fromJson(appointment, Appointment.class);

        if(appointmentObj != null) {
            return appointmentService.makeAppointment(appointmentObj);
        }
        return false;
    }

    @RequestMapping(value="/getDoctorAppointments", method = RequestMethod.GET)
    public List<Appointment> getDoctorAppointments(@RequestParam Long doctorId) {
        return appointmentService.getAppointments(true, doctorId);
    }

    @RequestMapping(value="/getPatientAppointments", method = RequestMethod.GET)
    public List<Appointment> getPatientAppointments(@RequestParam Long patientId) {
        return appointmentService.getAppointments(false, patientId);
    }


    /**
     * Article methods
     */
    @RequestMapping(value="/postArticle", method = RequestMethod.POST)
    public void postArticle(@RequestBody String article) {
        Gson gson = new Gson();
        Article articleObj = gson.fromJson(article, Article.class);

        if(articleObj != null) {
            articleService.postArticle(articleObj);
        }
    }

    @RequestMapping(value="/getArticles", method = RequestMethod.GET)
    public List<Article> getArticles() {
        return articleService.getArticles();
    }

    @RequestMapping(value="/getArticle", method = RequestMethod.GET)
    public Article getArticle(@RequestParam String title) {
        return articleService.getArticleByTitle(title);
    }

    /**
     * Prescription methods
     */
    @RequestMapping(value="/getPatientPrescriptions", method = RequestMethod.GET)
    public List<Prescription> getPatientPrescriptions(@RequestParam Long pacientId) {
        return prescriptionService.getPatientPrescriptions(pacientId);
    }

    @RequestMapping(value="/getDoctorPrescriptions", method = RequestMethod.GET)
    public List<Prescription> getDoctorPrescriptions(@RequestParam Long doctorId) {
        return prescriptionService.getDoctorPrescriptions(doctorId);
    }

    @RequestMapping(value="/prescribe", method = RequestMethod.POST)
    public void prescribe(@RequestBody String prescription) {
        Gson gson = new Gson();
        Prescription prescriptionObj = gson.fromJson(prescription, Prescription.class);

        if(prescriptionObj != null) {
            prescriptionService.prescribe(prescriptionObj);
        }
    }

}

