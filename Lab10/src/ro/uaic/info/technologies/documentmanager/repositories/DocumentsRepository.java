package ro.uaic.info.technologies.documentmanager.repositories;

import ro.uaic.info.technologies.documentmanager.entities.DocumentsEntity;
import ro.uaic.info.technologies.documentmanager.models.Document;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import static ro.uaic.info.technologies.documentmanager.utils.EntityConverterUtil.toDocumentsEntity;

@Stateless
public class DocumentsRepository {

    @Inject
    private EntityManager entityManager;

    public void addDocument(Document document) {
        DocumentsEntity documentsEntity = toDocumentsEntity(document);
        entityManager.persist(documentsEntity);
    }
}
