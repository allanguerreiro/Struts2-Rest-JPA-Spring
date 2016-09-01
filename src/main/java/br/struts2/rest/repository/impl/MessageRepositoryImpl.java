package br.struts2.rest.repository.impl;

import br.struts2.rest.domain.Message;
import br.struts2.rest.repository.MessageRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by allan on 31/08/16.
 */
@Repository
public class MessageRepositoryImpl implements MessageRepository {
    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        try {
            this.entityManager = entityManager;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Message> findAll() {
        return entityManager.createQuery("SELECT m FROM Message m").getResultList();
    }

    public Message find(String id) {
        return (Message) entityManager.createQuery("SELECT m FROM Message m WHERE m.id = :id")
                .setParameter("id", Integer.valueOf(id)).getSingleResult();
    }

    @Transactional
    public void save(Message message) {
        try {
            entityManager.persist(message);
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

    @Transactional
    public void remove(Message message) {
        try {
            Object delMessage = entityManager.merge(message);
            entityManager.remove(delMessage);
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

    public Map<String, Message> findAllMap() {
        Map<String, Message> messageMap = new HashMap<String, Message>();
        List<Message> messageSet = new ArrayList<Message>();
        messageSet = entityManager.createQuery("SELECT m FROM Message m").getResultList();
        for (Message message : messageSet) {
            messageMap.put(String.valueOf(message.getId()), message);
        }

        return messageMap;
    }
}
