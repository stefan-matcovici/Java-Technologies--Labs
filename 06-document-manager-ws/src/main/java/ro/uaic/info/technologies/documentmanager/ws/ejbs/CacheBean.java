package ro.uaic.info.technologies.documentmanager.ws.ejbs;

import ro.uaic.info.technologies.documentmanager.models.Document;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class CacheBean {
    public List<Document> addedDocuments;

    public CacheBean() {
        addedDocuments = new ArrayList<>();
    }

    public void onDocumentAddEvent(@Observes Document document) {
        addedDocuments.add(document);
    }

    public List<Document> getAddedDocuments() {
        return addedDocuments;
    }
}
