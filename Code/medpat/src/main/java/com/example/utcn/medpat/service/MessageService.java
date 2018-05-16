package com.example.utcn.medpat.service;

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

    private java.text.SimpleDateFormat sdf =
            new java.text.SimpleDateFormat("HH:mm:ss dd-MM-yyyy");

    public List<Message> getMessages(String type, Long id) {
        if("to".equals(type)) {
            return messageRepository.findAllByToId(id);
        }
        else if("from".equals(type)) {
            return messageRepository.findAllByFromId(id);
        }
        else
            return null;
    }

    public void sendMessage(Message message) {
        Date dt = new java.util.Date();
        message.setDate(sdf.format(dt));
        messageRepository.save(message);
    }


}
