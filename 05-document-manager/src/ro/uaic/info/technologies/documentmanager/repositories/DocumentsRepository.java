package ro.uaic.info.technologies.documentmanager.repositories;

import ro.uaic.info.technologies.documentmanager.entities.DocumentsEntity;
import ro.uaic.info.technologies.documentmanager.entities.UsersEntity;
import ro.uaic.info.technologies.documentmanager.models.Document;
import ro.uaic.info.technologies.documentmanager.repositories.publishers.PublisherBean;
import ro.uaic.info.technologies.documentmanager.repositories.specifications.DocumentsRepositorySpecification;
import ro.uaic.info.technologies.documentmanager.utils.EntityConverterUtil;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static ro.uaic.info.technologies.documentmanager.utils.EntityConverterUtil.toDocumentsEntity;

//@Stateless(mappedName = "documents-ejb")
//@Remote(DocumentsRepositorySpecification.class)
@Stateless
@DeclareRoles({"admin", "guest"})
public class DocumentsRepository  {

    @PersistenceContext(unitName = "DocsPU")
    private EntityManager entityManager;

    @Resource
    SessionContext ctx;

    @EJB
    private PublisherBean publisherBean;

    @EJB
    private UserRepository userRepository;

    public void addDocument(Document document) {
        if (ctx.isCallerInRole("admin")) {
            throw new SecurityException("Not allowed!");
        }

        DocumentsEntity documentsEntity = toDocumentsEntity(document);
        UsersEntity usersEntity = userRepository.getAdminEntityById(document.getUser().getId());
        documentsEntity.setUser(usersEntity);
        entityManager.persist(documentsEntity);

        publisherBean.sendDocumentMessage(document);
    }

//    @Override
    public List<Document> getDocumentsByUserId(Integer id) {
        Query query = entityManager.createQuery("SELECT document FROM DocumentsEntity document where document.user.id = :id");
        query.setParameter("id", id);
        List<Document> collect = ((Collection<DocumentsEntity>) query.getResultList()).stream().map(EntityConverterUtil::toDocument).collect(Collectors.toList());
        return collect;
    }

//    @Override
    public List<Document> getAllDocuments() {
        Query query = entityManager.createQuery("SELECT document FROM DocumentsEntity document");

        List<Document> collect = ((Collection<DocumentsEntity>) query.getResultList()).stream().map(EntityConverterUtil::toDocument).collect(Collectors.toList());
        return collect;
    }

//    @Override
    public Document getDocumentById(Integer id) {
        Query query = entityManager.createQuery("SELECT document FROM DocumentsEntity document WHERE document.id=:id");
        query.setParameter("id", id);

        return EntityConverterUtil.toDocument(((DocumentsEntity) query.getSingleResult()));
    }

//    @Override
    public void deleteDocumentById(Integer id) {
        Query query = entityManager.createQuery("SELECT document FROM DocumentsEntity document WHERE document.id=:id");
        query.setParameter("id", id);

        DocumentsEntity documentsEntity = ((DocumentsEntity) query.getSingleResult());
        entityManager.remove(documentsEntity);
    }
}
