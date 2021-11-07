package com.example.socialnetwork.services;

import com.example.socialnetwork.entities.Message;
import com.example.socialnetwork.entities.User;
import com.example.socialnetwork.entities.dto.MessageDto;
import com.example.socialnetwork.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;


    public Page<MessageDto> messageList(Pageable pageable, String filter, User user) {
        if(filter != null && !filter.isEmpty()) {
            return messageRepository.findByTag(filter, pageable, user);
        } else {
            return messageRepository.findAll(pageable, user);
        }
    }

    public Page<MessageDto> messageListForUser(Pageable pageable, User currentUser, User author) {
        if (currentUser.equals(author)) {
            return messageRepository.findByUser(pageable, author);
        } else {
            return messageRepository.findByUser(pageable, currentUser, author);
        }
    }
}