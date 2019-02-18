package ro.uaic.info.technologies.documentmanager.webservices;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ViewDocumentServiceInterface {

    @WebMethod
    public String businessMethod();
}
