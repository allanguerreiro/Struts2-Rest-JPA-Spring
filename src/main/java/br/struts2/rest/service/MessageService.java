package br.struts2.rest.service;

import br.struts2.rest.domain.Message;

import java.util.List;
import java.util.Map;

/**
 * Created by allan on 31/08/16.
 */
public interface MessageService {
    List findAll();
    Message find(String id);
    void save(Message message);
    void remove(Message message);
    Map<String, Message> findAllMap();
}
