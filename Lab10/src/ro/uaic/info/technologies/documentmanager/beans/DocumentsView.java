package ro.uaic.info.technologies.documentmanager.beans;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import ro.uaic.info.technologies.documentmanager.interceptors.LoggedIn;
import ro.uaic.info.technologies.documentmanager.models.Document;
import ro.uaic.info.technologies.documentmanager.repositories.DocumentsRepository;
import ro.uaic.info.technologies.documentmanager.services.DocumentService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
@LoggedIn
public class DocumentsView implements Serializable {

    @EJB
    private DocumentsRepository documentsRepository;

    @Inject
    private DocumentService documentService;

    private List<Document> documents;
    private StreamedContent file;

    @PostConstruct
    public void init() {
        documents = documentsRepository.getAllDocuments();
    }

    public StreamedContent getFile(Integer registrationNumber, String name) {
        InputStream stream = new ByteArrayInputStream(documentService.getDocumentByRegistrationNumber(registrationNumber));
        file = new DefaultStreamedContent(stream, FacesContext.getCurrentInstance().getExternalContext().getMimeType(name), name);
        return file;
    }

    public void onDocumentsUpload(@Observes Document document) {
        documents.add(document);
        RequestContext.getCurrentInstance().update(":output-panel:docsTable");
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }
}
