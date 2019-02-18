package ro.uaic.info.technologies.documentmanager.ws.ejbs;

import ro.uaic.info.technologies.documentmanager.models.Document;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.MessageDriven;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.ArrayList;
import java.util.List;

@MessageDriven(mappedName="Documents")
public class SubscriberBean implements MessageListener {

    @Inject
    Event<Document> documentEvent;

    @Override
    public void onMessage(Message message) {
        try {
            Document document = message.getBody(Document.class);
            documentEvent.fire(document);
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
