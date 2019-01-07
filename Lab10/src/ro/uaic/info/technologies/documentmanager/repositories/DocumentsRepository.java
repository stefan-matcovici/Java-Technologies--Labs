package ro.uaic.info.technologies.documentmanager.repositories;

import ro.uaic.info.technologies.documentmanager.entities.DocumentsEntity;
import ro.uaic.info.technologies.documentmanager.entities.PeriodsEntity;
import ro.uaic.info.technologies.documentmanager.models.Document;
import ro.uaic.info.technologies.documentmanager.utils.EntityConverterUtil;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static ro.uaic.info.technologies.documentmanager.utils.EntityConverterUtil.toDocumentsEntity;

@Stateless
public class DocumentsRepository {

    @Inject
    private EntityManager entityManager;

    public void addDocument(Document document) {
        DocumentsEntity documentsEntity = toDocumentsEntity(document);
        entityManager.persist(documentsEntity);
    }

    public List<Document> getAllDocuments() {
        Query query = entityManager.createQuery("SELECT document FROM DocumentsEntity document");

        return ((Collection<DocumentsEntity>) query.getResultList()).stream().map(EntityConverterUtil::toDocument).collect(Collectors.toList());
    }

    public Document getDocumentById(Integer id) {
        Query query = entityManager.createQuery("SELECT document FROM DocumentsEntity document WHERE document.id=:id");
        query.setParameter("id", id);

        return EntityConverterUtil.toDocument(((DocumentsEntity) query.getSingleResult()));
    }

    public void deleteDocumentById(Integer id) {
        Query query = entityManager.createQuery("SELECT document FROM DocumentsEntity document WHERE document.id=:id");
        query.setParameter("id", id);

        DocumentsEntity documentsEntity = ((DocumentsEntity) query.getSingleResult());
        entityManager.remove(documentsEntity);
    }
}
