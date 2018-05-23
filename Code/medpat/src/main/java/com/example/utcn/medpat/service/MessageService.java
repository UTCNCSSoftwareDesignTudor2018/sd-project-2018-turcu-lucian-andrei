package com.example.utcn.medpat.service;

import com.example.utcn.medpat.communication.dto.MessageDTO;
import com.example.utcn.medpat.persistence.model.Message;
import com.example.utcn.medpat.persistence.repository.MessageRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Service
public class MessageService {

    @Inject
    private MessageRepository messageRepository;
    @Inject
    private UserService userService;

    private java.text.SimpleDateFormat sdf =
            new java.text.SimpleDateFormat("HH:mm:ss dd-MM-yyyy");

    public List<Message> getMessages(String type, String id) {
        if("to".equals(type)) {
            return messageRepository.findAllByToId(id);
        }
        else if("from".equals(type)) {
            return messageRepository.findAllByFromId(id);
        }
        else
            return null;
    }

    public void sendMessage(MessageDTO message) {
        Date dt = new java.util.Date();
        Message messageObj = new Message();
        messageObj.setDate(sdf.format(dt));
        messageObj.setFrom(userService.getUser(message.getFrom()));
        messageObj.setTo(userService.getUser(message.getTo()));
        messageObj.setMessage(message.getMessage());
        messageRepository.save(messageObj);
    }


}
