package com.example.utcn.medpat.communication;

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

    @RequestMapping(value="/getInbox", method = RequestMethod.GET)
    public List<Message> getInbox(@RequestParam Long toId) {
        if(toId != null)
            return messageService.getMessages("to", toId);
        else
            return null;
    }

    @RequestMapping(value="/getSent", method = RequestMethod.GET)
    public List<Message> getSent(@RequestParam Long fromId) {
        if(fromId != null)
            return messageService.getMessages("from", fromId);
        else
            return null;
    }

    @RequestMapping(value="/sendMessage", method = RequestMethod.POST)
    public void sendMessage(@RequestBody String message) {
        Gson gson = new Gson();
        Message messageObj = gson.fromJson(message, Message.class);

        if(messageObj != null) {
            messageService.sendMessage(messageObj);
            //send notification
        }
    }

    @RequestMapping(value="/makeAppointment", method = RequestMethod.POST)
    public boolean makeAppointment(@RequestBody String appointment) {
        Gson gson = new Gson();
        Appointment appointmentObj = gson.fromJson(appointment, Appointment.class);

        if(appointmentObj != null) {
            return appointmentService.makeAppointment(appointmentObj);
        }
        return false;
    }

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

}

class LoginCredentials {
    private String username;
    private String password;

    public LoginCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
