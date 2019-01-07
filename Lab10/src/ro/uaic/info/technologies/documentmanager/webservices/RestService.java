package ro.uaic.info.technologies.documentmanager.webservices;

import ro.uaic.info.technologies.documentmanager.models.Document;
import ro.uaic.info.technologies.documentmanager.repositories.DocumentsRepository;

import javax.ejb.EJB;
import javax.ws.rs.*;
import java.util.List;

@Path("/documents")
public class RestService {

    @EJB
    private DocumentsRepository documentsRepository;

    @GET
    public List<Document> getAllDocuments() {
        return documentsRepository.getAllDocuments();
    }

    @GET
    @Path("{id}")
    public Document getDocumentById(@PathParam("id") Integer id) {
        return documentsRepository.getDocumentById(id);
    }

    @DELETE
    @Path("{id}")
    public void deleteDocument(@PathParam("id") Integer id) {
        documentsRepository.deleteDocumentById(id);
    }

    @POST
    public void addDocument(Document document) {
        documentsRepository.addDocument(document);
    }

}
