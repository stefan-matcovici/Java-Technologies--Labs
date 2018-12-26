package ro.uaic.info.technologies.documentmanager.beans;

import ro.uaic.info.technologies.documentmanager.interceptors.LoggedIn;

import javax.annotation.PostConstruct;
import javax.inject.Named;

@Named
@LoggedIn
public class DocumentsView {

    private String[] documents;

    @PostConstruct
    public void init() {
        documents = new String[]{"ana", "mere"};
    }

    public String[] getDocuments() {
        return documents;
    }

    public void setDocuments(String[] documents) {
        this.documents = documents;
    }
}
