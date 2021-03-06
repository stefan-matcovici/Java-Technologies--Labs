package ro.uaic.info.technologies.documentmanager.repositories.specifications;

import ro.uaic.info.technologies.documentmanager.models.Document;

import java.util.List;

public interface DocumentsRepositorySpecification {
    List<Document> getDocumentsByUserId(Integer id);

    List<Document> getAllDocuments();

    void addDocument(Document document);

    Document getDocumentById(Integer id);

    void deleteDocumentById(Integer id);
}
