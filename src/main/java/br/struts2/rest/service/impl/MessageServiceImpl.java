package br.struts2.rest.service.impl;

import br.struts2.rest.domain.Message;
import br.struts2.rest.repository.MessageRepository;
import br.struts2.rest.service.MessageService;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by allan on 31/08/16.
 */
@Service
@NoArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class MessageServiceImpl implements MessageService {
    private MessageRepository messageRepository;
    private static Map<String, Message> messageMap = new HashMap<String, Message>();

    public List findAll() {
        return new ArrayList(messageRepository.findAll());
    }

    public Message find(String id) {
        return messageRepository.find(id);
    }

    public void save(Message message) {
        messageRepository.save(message);
    }

    public void remove(Message message) {
        messageRepository.remove(message);
    }

    public Map<String, Message> findAllMap() {
        messageMap = messageRepository.findAllMap();
        return messageMap;
    }

    @Inject
    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
}
