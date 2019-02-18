package ro.uaic.info.technologies.documentmanager.repositories.publishers;

import ro.uaic.info.technologies.documentmanager.models.Document;
import ro.uaic.info.technologies.documentmanager.repositories.specifications.PublisherBeanSpecification;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.Topic;

@Stateless
public class PublisherBean {

    @Resource
    private SessionContext sc;

    @Resource(lookup = "Documents")
    private Topic topic;

    @Inject
    private JMSContext context;

    public void sendDocumentMessage(Document document) {
        context.createProducer().send(topic, document);
    }
}
