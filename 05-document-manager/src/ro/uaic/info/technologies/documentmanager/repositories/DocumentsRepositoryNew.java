package ro.uaic.info.technologies.documentmanager.repositories;

import ro.uaic.info.technologies.documentmanager.models.Document;
import ro.uaic.info.technologies.documentmanager.repositories.specifications.DocumentsRepositorySpecification;

import javax.ejb.*;
import java.util.List;


public class DocumentsRepositoryNew implements DocumentsRepositorySpecification {

//    @EJB(mappedName = "documents-ejb")
    private DocumentsRepositorySpecification documentsRepository;

    @Override
    public List<Document> getDocumentsByUserId(Integer id) {
        return documentsRepository.getDocumentsByUserId(id);
    }

    @Override
    public List<Document> getAllDocuments() {
        return documentsRepository.getAllDocuments();
    }

    @Override
    public void addDocument(Document document) {
        documentsRepository.addDocument(document);
    }

    @Override
    public Document getDocumentById(Integer id) {
        return documentsRepository.getDocumentById(id);
    }

    @Override
    public void deleteDocumentById(Integer id) {
        documentsRepository.deleteDocumentById(id);
    }
}
