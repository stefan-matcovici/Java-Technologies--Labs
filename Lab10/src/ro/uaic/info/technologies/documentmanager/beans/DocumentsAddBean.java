package ro.uaic.info.technologies.documentmanager.beans;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import ro.uaic.info.technologies.documentmanager.interceptors.AdminLoggedIn;
import ro.uaic.info.technologies.documentmanager.interceptors.LoggedIn;
import ro.uaic.info.technologies.documentmanager.models.Document;
import ro.uaic.info.technologies.documentmanager.producers.DocumentRegistrationNumber;
import ro.uaic.info.technologies.documentmanager.repositories.DocumentsRepository;
import ro.uaic.info.technologies.documentmanager.services.DocumentService;

import javax.ejb.EJB;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
@LoggedIn
@AdminLoggedIn
public class DocumentsAddBean implements Serializable {

    private UploadedFile file;

    @Inject
    @DocumentRegistrationNumber
    private int registrationNumber;

    @EJB
    private DocumentsRepository documentsRepository;

    @Inject
    @Any
    private Event<Document> documentEvent;

    @Inject
    private DocumentService documentService;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() {
        if(file != null) {
            documentService.uploadDocument(file.getContents(), registrationNumber);
            Document newDocument = new Document(registrationNumber, file.getFileName());
            documentsRepository.addDocument(newDocument);
            documentEvent.fire(newDocument);
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
