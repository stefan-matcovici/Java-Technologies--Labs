package ro.uaic.info.technologies.documentmanager.ws.ejbs;

import ro.uaic.info.technologies.documentmanager.repositories.specifications.DocumentsRepositorySpecification;
import ro.uaic.info.technologies.documentmanager.models.Document;

import javax.ejb.*;
import java.util.List;

@Stateless
@Remote(DocumentsRepositorySpecification.class)
@TransactionManagement(TransactionManagementType.BEAN)
public class DocumentsRepository implements DocumentsRepositorySpecification {

    @EJB(mappedName = "documents-ejb")
    private DocumentsRepositorySpecification documentsRepository;

    @Override
    public List<Document> getDocumentsByUserId(Integer id) {
        if (id != null) {
            return documentsRepository.getDocumentsByUserId(id);
        }
        return getAllDocuments();
    }

    @Override
    public List<Document> getAllDocuments() {
        return documentsRepository.getAllDocuments();
    }
}
