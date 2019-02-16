package ro.uaic.info.technologies.documentmanager.webservices;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(
        serviceName = "EBookStoreImplService")
public class ViewDocumentService implements ViewDocumentServiceInterface {

    @WebMethod(operationName = "hello")
    public String businessMethod() {
        return "hello";
    }

}
