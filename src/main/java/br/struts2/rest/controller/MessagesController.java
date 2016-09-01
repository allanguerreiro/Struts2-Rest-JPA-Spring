package br.struts2.rest.controller;

import br.struts2.rest.domain.Message;
import br.struts2.rest.service.MessageService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.ValidationAware;
import lombok.Getter;
import lombok.Setter;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Created by allan on 31/08/16.
 */
@Controller
@Scope("prototype")
public class MessagesController extends ActionSupport implements ModelDriven<Object>, ValidationAware {

    private Message message = new Message();

    @Getter
    private String id;
    private Object model;
    private Collection<Message> list = new ArrayList<Message>();

    @Inject
    @Getter
    @Setter
    private MessageService messageService;

    private static Map<String, Message> map;

    {
        if (messageService != null) {
            map = messageService.findAllMap();
        }
    }

    public String create() {
        message.setText("Teste");
        message.setAuthor("Allan");
        messageService.save(message);
        System.out.println("hi");
        return "SUCCESS";
    }

    public String destroy() {
        message = messageService.find(this.id);
        messageService.remove(message);
        return SUCCESS;
    }

    public String show() {
        model = messageService.find(id);
        return "SUCCESS";
    }

    public String update() {
        messageService.save(message);
        return "SUCCESS";
    }

    public HttpHeaders index() {
        model = messageService.findAllMap();
        return new DefaultHttpHeaders("index").disableCaching();
    }

    public void setId(String id) {
        if (id != null) {
            model = messageService.find(id);
        }
        this.id = id;
    }

    public Object getModel() {
        if (!list.isEmpty() && list != null) {
            return list;
        } else {
            return model;
        }
    }
}
