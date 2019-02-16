package ro.uaic.info.technologies.documentmanager.ws;

import ro.uaic.info.technologies.documentmanager.models.Document;
import ro.uaic.info.technologies.documentmanager.repositories.specifications.DocumentsRepositorySpecification;
import ro.uaic.info.technologies.documentmanager.ws.ejbs.CacheBean;

import javax.ejb.EJB;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
@HandlerChain(file = "simple-chain.xml")
public class ViewDocumentService {

    @EJB
    private DocumentsRepositorySpecification documentsRepositorySpecification;

    @EJB
    private CacheBean cacheBean;

    @WebMethod
    public List<Document> getAllDocumentsByUserId(Integer userId) {
        return documentsRepositorySpecification.getDocumentsByUserId(userId);
    }

    @WebMethod
    public List<Document> getAddedDocuments() {
        return cacheBean.getAddedDocuments();
    }

}
